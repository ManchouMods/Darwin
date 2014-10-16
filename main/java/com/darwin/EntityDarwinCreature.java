/**
 *
 */
package com.darwin;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBreakDoor;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import cpw.mods.fml.relauncher.Side;

/**
 * @author Manchou
 *
 */
public class EntityDarwinCreature extends EntityAnimal implements IEntityAdditionalSpawnData
{
    
    public final static int MAX_TEXTURE_INDEX = 12;
    
    /**
     * @param par1World
     */
    public EntityDarwinCreature(World world)
    {
        super(world);
        initialize();

    	if (FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER)
    		this.setSize(0.9F, computeHeight());
    	else 
    		this.setSize(1.0F, 1.0F);
    }
    
    @Override
    protected void entityInit() {
    	super.entityInit();
        dataWatcher.addObject(20, new Integer(0));
        dataWatcher.addObject(21, new Integer(0));
        dataWatcher.addObject(22, new Integer(0));
        dataWatcher.addObject(23, new Integer(0));
        dataWatcher.addObject(24, new Integer(0));
        dataWatcher.addObject(25, new Integer(0));
    }
    
    public void initialize(){
    	if (FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER)
        {
            
            int randomNb = rand.nextInt(7);

            switch (randomNb)
            {
                case 0:
                    GenomeUtils.genomeCow(this);
                    break;

                case 1:
                    GenomeUtils.genomeEnderman(this);
                    break;

                case 2:
                    GenomeUtils.genomePig(this);
                    break;

                case 3:
                    GenomeUtils.genomeSlime(this);
                    break;

                case 4:
                    GenomeUtils.genomeZombie(this);
                    break;

                case 5:
                    GenomeUtils.genomeSquirtle(this);
                    break;

                case 6:
                    GenomeUtils.genomePikachu(this);
                    break;

                default:
                    break;
            }

            this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(computeMaxHealth());
            setHealth(getMaxHealth());
            this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(computeMoveSpeed());
            addTasks();
        }
    }
    
    protected float computeHeight(){
    	float height = 0.8F;
    	if (getLegLength()== 1)
    		height += 0.3F;
    	if (getLegLength()== 2)
    		height += 0.6F;
    	if (getLegLength()== 3)
    		height += 1.7F;
    	
    	height += getBodyRotation()/255F;
    	if (ModDarwin.debug) System.out.println("getLegLength= "+getLegLength());
    	if (ModDarwin.debug) System.out.println("getBodyRotation= "+getBodyRotation());
    	if (ModDarwin.debug) System.out.println("Height= "+height);
    	return height;
    }
    
    protected float computeMoveSpeed()
    {
        float bonusSpeed = (3 - getBody());

        if (getBodyRotation() < 10F && getLegLength() == getArmLength())
        {
            bonusSpeed += 3F;
        }
        else if (getBodyRotation() > 200F & getLegLength() > 0)
        {
            bonusSpeed += (3 - getArmLength());
        }

        float moveSpeed = 0.07F * (1F + (float)getLegLength() + (float)(getArmLength() / 2F) + bonusSpeed);
        if (ModDarwin.debug) System.out.println("MoveSpeed = " + moveSpeed);
//        moveSpeed = 0.23F;
        return moveSpeed;
    }
    
    double getMoveSpeed(){
    	return this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue();
    }

    protected void applyEntityAttributes()
    {
            super.applyEntityAttributes();
            // Max Health - default 20.0D - min 0.0D - max Double.MAX_VALUE
//            this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(10D);
//            // Follow Range - default 32.0D - min 0.0D - max 2048.0D
//            this.getEntityAttribute(SharedMonsterAttributes.followRange).setAttribute(32.0D);
//            // Knockback Resistance - default 0.0D - min 0.0D - max 1.0D
//            this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setAttribute(0.0D);
//            // Movement Speed - default 0.699D - min 0.0D - max Double.MAX_VALUE
//            this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(1D);
//            // Attack Damage - default 2.0D - min 0.0D - max Doubt.MAX_VALUE
//            this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(1D);
    }
    
    protected void addTasks()
    {
        int i = 0;
        int j = 0;

        if (getGeneStrongestAllele(3, 0, 1, true) > 0)
        {
            this.getNavigator().setAvoidsWater(true);
        }

        if (getGeneStrongestAllele(3, 1, 1, true) > 0)
        {
            this.getNavigator().setAvoidSun(true);
        }

        if (getGeneStrongestAllele(3, 2, 1, true) > 0)
        {
            this.getNavigator().setCanSwim(true);
            tasks.addTask(i++, new EntityAISwimming(this));
        }

        if (getGeneStrongestAllele(3, 3, 1, true) > 0)
        {
            this.getNavigator().setBreakDoors(true);
            this.tasks.addTask(i++, new EntityAIBreakDoor(this));
        }

        if (getGeneStrongestAllele(3, 4, 1, true) > 0)
        {
            this.tasks.addTask(i++, new EntityAIPanic(this, this.getMoveSpeed()));
        }

        this.tasks.addTask(i++, new EntityAIMate(this, this.getMoveSpeed()*0.5D));
        this.tasks.addTask(i++, new EntityAITempt(this, this.getMoveSpeed(), Items.wheat, false));
        this.tasks.addTask(i++, new EntityAIFollowParent(this, this.getMoveSpeed()*0.5D));

        if (getGeneStrongestAllele(3, 5, 1, true) > 0)
        {
            this.tasks.addTask(i++, new EntityAIMoveTowardsRestriction(this, this.getMoveSpeed()));
        }

        if (getGeneStrongestAllele(3, 6, 1, true) > 0)
        {
            this.tasks.addTask(i++, new EntityAIMoveThroughVillage(this, this.getMoveSpeed(), false));
        }

        if (getGeneStrongestAllele(3, 7, 1, true) > 0)
        {
            this.targetTasks.addTask(j++, new EntityAIHurtByTarget(this, false));
        }

//        if (getGeneStrongestAllele(3, 8, 1, true) > 0)
//        {
//            this.tasks.addTask(i++, new EntityAIAttackOnCollide(this, EntityPlayer.class, this.getMoveSpeed(), false));
//            this.targetTasks.addTask(j++, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
//        }

        if (getGeneStrongestAllele(3, 9, 1, true) > 0)
        {
            this.tasks.addTask(i++, new EntityAIAttackOnCollide(this, EntityVillager.class, this.getMoveSpeed(), true));
            this.targetTasks.addTask(j, new EntityAINearestAttackableTarget(this, EntityVillager.class, 0, false));
        }

        if (getGeneStrongestAllele(3, 10, 1, true) > 0)
        {
            this.tasks.addTask(i++, new EntityAIAttackOnCollide(this, EntityMob.class, this.getMoveSpeed(), true));
            this.targetTasks.addTask(j, new EntityAINearestAttackableTarget(this, EntityMob.class, 0, false, true));
        }

        if (getGeneStrongestAllele(3, 11, 1, true) > 0)
        {
            this.tasks.addTask(i++, new EntityAIAttackOnCollide(this, EntityTameable.class, this.getMoveSpeed(), true));
            this.targetTasks.addTask(j, new EntityAINearestAttackableTarget(this, EntityTameable.class, 0, false, true));
        }

        if (getGeneStrongestAllele(3, 12, 1, true) > 0)
        {
            this.tasks.addTask(i++, new EntityAIAttackOnCollide(this, EntitySheep.class, this.getMoveSpeed(), true));
            this.targetTasks.addTask(j, new EntityAINearestAttackableTarget(this, EntitySheep.class, 0, false, true));
        }

        if (getGeneStrongestAllele(3, 13, 1, true) > 0)
        {
            this.tasks.addTask(i++, new EntityAIAttackOnCollide(this, EntityCow.class, this.getMoveSpeed(), true));
            this.targetTasks.addTask(j, new EntityAINearestAttackableTarget(this, EntityCow.class, 0, false, true));
        }

        if (getGeneStrongestAllele(3, 14, 1, true) > 0)
        {
            this.tasks.addTask(i++, new EntityAIAttackOnCollide(this, EntityPig.class, this.getMoveSpeed(), true));
            this.targetTasks.addTask(j, new EntityAINearestAttackableTarget(this, EntityPig.class, 0, false, true));
        }

        if (getGeneStrongestAllele(3, 15, 1, true) > 0)
        {
            this.tasks.addTask(i++, new EntityAIAttackOnCollide(this, EntityChicken.class, this.getMoveSpeed(), true));
            this.targetTasks.addTask(j, new EntityAINearestAttackableTarget(this, EntityChicken.class, 0, false, true));
        }

        this.tasks.addTask(i++, new EntityAIWander(this, this.getMoveSpeed()));
        this.tasks.addTask(i++, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(i, new EntityAILookIdle(this));
        isImmuneToFire = (getGeneStrongestAllele(3, 16, 1, true) > 0);
    }

    /**
     * Returns true if the newer Entity AI code should be run
     */
    @Override
    public boolean isAIEnabled()
    {
        return true;
    }
    
    private boolean previousIsChild = false;
    
    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    @Override
    public void onLivingUpdate() {
    	
    	if (   width == 1.0F // refresh hitbox on client side
    		|| isChild() != previousIsChild){// refresh hitbox if the mob is getting adult
    		this.setSize(0.9F, computeHeight());
    	}
    	previousIsChild = isChild();
    	
    	
        if (getGeneStrongestAllele(3, 17, 1, true) > 0)
        {
            if (this.worldObj.isDaytime() && !this.worldObj.isRemote)
            {
                float var1 = this.getBrightness(1.0F);

                if (var1 > 0.5F && this.worldObj.canBlockSeeTheSky(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ)) && this.rand.nextFloat() * 30.0F < (var1 - 0.4F) * 2.0F)
                {
                    this.setFire(8);
                }
            }
        }

        if (getGeneStrongestAllele(3, 18, 1, true) > 0)
        {
            if (this.isWet())
            {
                this.attackEntityFrom(DamageSource.drown, 1);
            }
        }

        super.onLivingUpdate();
    }

    @Override
    public boolean canBreatheUnderwater()
    {
        return (getGeneStrongestAllele(3, 19, 1, true) > 0);
    }

    /**
     * Takes a coordinate in and returns a weight to determine how likely this creature will try to path to the block.
     * Args: x, y, z
     */
    @Override
    public float getBlockPathWeight(int par1, int par2, int par3)
    {
        if (getGeneStrongestAllele(3, 1, 1, true) > 0)
        {
            return 0.5F - this.worldObj.getLightBrightness(par1, par2, par3);
        }

        return super.getBlockPathWeight(par1, par2, par3);
    }

//	private void sendGenome(){
//
//		if (FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER) {
//			ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
//			DataOutputStream outputStream = new DataOutputStream(bos);
//			try {
//				outputStream.writeLong(getGenome());
//				outputStream.writeInt(entityId);
//
//				PacketGenome packet = new PacketGenome(
//						DarwinPacketHandler.CHANNEL_ID,
//						bos.toByteArray());
//
//				MinecraftServer mcServer = FMLCommonHandler.instance().getMinecraftServerInstance();
//		        mcServer.getConfigurationManager().sendPacketToAllPlayers(packet);
//
//			} catch (Exception ex) {
//				ex.printStackTrace();
//			}
//		}
//	}

    /* (non-Javadoc)
     * @see net.minecraft.src.EntityAnimal#spawnBabyAnimal(net.minecraft.src.EntityAnimal)
     */
//	@Override
    public EntityAnimal spawnBabyAnimal(EntityAnimal var1)
    {
        EntityDarwinCreature parent = (EntityDarwinCreature) var1;
        EntityDarwinCreature baby = new EntityDarwinCreature(worldObj);
        baby.setChromosome11(this.crossOverAndMutate(1));
        baby.setChromosome12(parent.crossOverAndMutate(1));
        baby.setChromosome21(this.crossOverAndMutate(2));
        baby.setChromosome22(parent.crossOverAndMutate(2));
        return baby;
    }

    @Override
    public EntityAgeable createChild(EntityAgeable var1)
    {
        return this.spawnBabyAnimal((EntityAnimal) var1);
    }

    public float computeMaxHealth()
    {
        try
        {
            return 9 * (getBody() + 1);
        }
        catch (Exception e)
        {
            // not initialized yet
            return 10;
        }
    }

    public int computeAttackStrength()
    {
        return getGeneFromBoth(3, 20, 3) + 1;
    }

    @Override
    protected void attackEntity(Entity par1Entity, float par2)
    {
        if (par1Entity instanceof EntityDarwinCreature)
        {
            super.attackEntity(par1Entity, par2);
        }
        else
        {
            if (this.attackTime <= 0 && par2 < 2.0F && par1Entity.boundingBox.maxY > this.boundingBox.minY && par1Entity.boundingBox.minY < this.boundingBox.maxY)
            {
                this.attackTime = 20;
                this.attackEntityAsMob(par1Entity);
            }
        }
    }

    @Override
    public boolean attackEntityAsMob(Entity par1Entity)
    {
        int attackStrength = computeAttackStrength();

        if (this.isPotionActive(Potion.damageBoost))
        {
            attackStrength += 3 << this.getActivePotionEffect(Potion.damageBoost).getAmplifier();
        }

        if (this.isPotionActive(Potion.weakness))
        {
            attackStrength -= 2 << this.getActivePotionEffect(Potion.weakness).getAmplifier();
        }

        return par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this), attackStrength);
    }

    /**
     * Reads and returns the gene from a pair of chromosomes.
     * It actually returns the average of the genes from both genes.
     *
     * @param chromosome the chromosome number
     * @param pos the position of the gene on the chromosome
     * @param maxValue should be a 2^n-1 value and is used as a mask
     * @return the int value of the gene
     */
    protected int getGeneFromBoth(int chromosome, int pos, int maxValue)
    {
        switch (chromosome)
        {
            case 1:
                return ((getChromosome11() >> pos & maxValue) + (getChromosome12() >> pos & maxValue)) / 2;

            case 2:
                return ((getChromosome21() >> pos & maxValue) + (getChromosome22() >> pos & maxValue)) / 2;

            case 3:
                return ((getChromosome31() >> pos & maxValue) + (getChromosome32() >> pos & maxValue)) / 2;

            default:
                return 0;
        }
    }

    /**
     * Reads and returns the gene from one chromosome of a pair.
     *
     * @param chromosome the chromosome number
     * @param pos the position of the gene on the chromosome
     * @param maxValue should be a 2^n-1 value and is used as a mask
     * @param random whether gets from a random chromosome or always the same
     * @return the int value of the gene
     */
    protected int getGeneFromOne(int chromosome, int pos, int maxValue, boolean random)
    {
        switch (chromosome)
        {
            case 1:
                if (rand.nextBoolean() & random)
                {
                    return (getChromosome11() >> pos & maxValue);
                }
                else
                {
                    return (getChromosome12() >> pos & maxValue);
                }

            case 2:
                if (rand.nextBoolean() & random)
                {
                    return (getChromosome21() >> pos & maxValue);
                }
                else
                {
                    return (getChromosome22() >> pos & maxValue);
                }

            case 3:
                if (rand.nextBoolean() & random)
                {
                    return (getChromosome31() >> pos & maxValue);
                }
                else
                {
                    return (getChromosome32() >> pos & maxValue);
                }

            default:
                return 0;
        }
    }

    protected int getGeneStrongestAllele(int chromosome, int pos, int maxValue, boolean biggest)
    {
        int allele1 = 0;
        int allele2 = 0;

        switch (chromosome)
        {
            case 1:
                allele1 = (getChromosome11() >> pos & maxValue);
                allele2 = (getChromosome12() >> pos & maxValue);
                break;

            case 2:
                allele1 = (getChromosome21() >> pos & maxValue);
                allele2 = (getChromosome22() >> pos & maxValue);
                break;

            case 3:
                allele1 = (getChromosome31() >> pos & maxValue);
                allele2 = (getChromosome32() >> pos & maxValue);
                break;

            default:
                return 0;
        }

        if (biggest)
        {
            return Math.max(allele1, allele2);
        }
        else
        {
            return Math.min(allele1, allele2);
        }
    }

    public int getHead()
    {
        if (this.isChild())
        {
            return 0;
        }

        return getGeneFromBoth(2, 0, 3);
    }

    public int getLegLength()
    {
        if (this.isChild())
        {
            return 0;
        }

        return getGeneFromBoth(2, 2, 3);
    }

    public int getArmLength()
    {
        if (this.isChild())
        {
            return 0;
        }

        return getGeneFromBoth(2, 4, 3);
    }

    public int getTail()
    {
        if (this.isChild())
        {
            return 0;
        }

        return getGeneFromBoth(2, 6, 3);
    }

    public int getSnout()
    {
        return getGeneFromBoth(2, 8, 3);
    }

    public int getEars()
    {
        return getGeneFromBoth(2, 10, 3);
    }

    public int getBody()
    {
        if (this.isChild())
        {
            return 1;
        }

        return getGeneFromBoth(2, 12, 3);
    }

    public float getBodyRotation()
    {
        if (this.isChild())
        {
            return 0;
        }

        return getGeneFromBoth(2, 16, 255);
    }

    @Override
    protected Item getDropItem()
    {
        try {
			int drop = getGeneFromOne(2, 24, 255, true) % 126;
			return Item.getItemById(drop + 256);
		} catch (Exception e) {
			return Item.getItemById(0);
		}
    }

    /**
     * Gets the texture index to display depending on the genome.
     * @return the int texture index
     */
    public int getTextureIndex()
    {
        int index = getGeneFromOne(1, 0, 15, false) % MAX_TEXTURE_INDEX;
        return index;
    }

    public float red()
    {
        return (float)getGeneFromBoth(1, 8, 255) / 255.0F;
    }

    public float green()
    {
        return (float)getGeneFromBoth(1, 16, 255) / 255.0F;
    }

    public float blue()
    {
        return (float)getGeneFromBoth(1, 24, 255) / 255.0F;
    }

    @Override
    public void writeSpawnData(ByteBuf data)
    {
//		data.writeLong(getGenome());
    	if (ModDarwin.debug) System.out.println("writeSpawnData ");
    }

    @Override
    public void readSpawnData(ByteBuf data)
    {
//		setGenome(data.readLong());
    	if (ModDarwin.debug) System.out.println("readSpawnData ");
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound par1nbtTagCompound)
    {
        super.readEntityFromNBT(par1nbtTagCompound);
        setChromosome11(par1nbtTagCompound.getInteger("Chrom11"));
        setChromosome12(par1nbtTagCompound.getInteger("Chrom12"));
        setChromosome21(par1nbtTagCompound.getInteger("Chrom21"));
        setChromosome22(par1nbtTagCompound.getInteger("Chrom22"));
        setChromosome31(par1nbtTagCompound.getInteger("Chrom31"));
        setChromosome32(par1nbtTagCompound.getInteger("Chrom32"));
        computeMoveSpeed();

//        if (!par1nbtTagCompound.hasKey("Health"))
//        {
//            this.health = this.getMaxHealth();
//        }
//        else
//        {
//            this.health = par1nbtTagCompound.getShort("Health");
//        }
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound par1nbtTagCompound)
    {
        super.writeEntityToNBT(par1nbtTagCompound);
        par1nbtTagCompound.setInteger("Chrom11", this.getChromosome11());
        par1nbtTagCompound.setInteger("Chrom12", this.getChromosome12());
        par1nbtTagCompound.setInteger("Chrom21", this.getChromosome21());
        par1nbtTagCompound.setInteger("Chrom22", this.getChromosome22());
        par1nbtTagCompound.setInteger("Chrom31", this.getChromosome31());
        par1nbtTagCompound.setInteger("Chrom32", this.getChromosome32());
    }

    /**
     * @param chromosome the chromosome to set
     */
    protected void setChromosome11(int chromosome)
    {
        dataWatcher.updateObject(20, chromosome);
    }

    /**
     * @return the chromosome
     */
    public int getChromosome11()
    {
        return dataWatcher.getWatchableObjectInt(20);
    }

    /**
     * @param chromosome the chromosome to set
     */
    protected void setChromosome12(int chromosome)
    {
        dataWatcher.updateObject(21, chromosome);
    }

    /**
     * @return the chromosome
     */
    public int getChromosome12()
    {
        return dataWatcher.getWatchableObjectInt(21);
    }

    /**
     * @param chromosome the chromosome to set
     */
    protected void setChromosome21(int chromosome)
    {
        dataWatcher.updateObject(22, chromosome);
    }

    /**
     * @return the chromosome
     */
    public int getChromosome21()
    {
        return dataWatcher.getWatchableObjectInt(22);
    }

    /**
     * @param chromosome the chromosome to set
     */
    protected void setChromosome22(int chromosome)
    {
        dataWatcher.updateObject(23, chromosome);
    }

    /**
     * @return the chromosome
     */
    public int getChromosome22()
    {
        return dataWatcher.getWatchableObjectInt(23);
    }

    /**
     * @param chromosome the chromosome to set
     */
    protected void setChromosome31(int chromosome)
    {
        dataWatcher.updateObject(24, chromosome);
    }

    /**
     * @return the chromosome
     */
    public int getChromosome31()
    {
        return dataWatcher.getWatchableObjectInt(24);
    }

    /**
     * @param chromosome the chromosome to set
     */
    protected void setChromosome32(int chromosome)
    {
        dataWatcher.updateObject(25, chromosome);
    }

    /**
     * @return the chromosome
     */
    public int getChromosome32()
    {
        return dataWatcher.getWatchableObjectInt(25);
    }

    public int crossOverAndMutate(int chromosome)
    {
        int chrom1 = 0;
        int chrom2 = 0;

        switch (chromosome)
        {
            case 1:
                chrom1 = getChromosome11();
                chrom2 = getChromosome12();
                break;

            case 2:
                chrom1 = getChromosome21();
                chrom2 = getChromosome22();
                break;

            default:
                break;
        }

        int chromRet = 0;

        for (int i = 0 ; i < 32 ; i++)
        {
            int random = rand.nextInt(100);

            if (random > 1)
            {
                chromRet += (chrom1 >> i & 1) << i;
            }
            else if (random > 51)
            {
                chromRet += (chrom2 >> i & 1) << i;
            }
            else
            {
                chromRet += (random) << i;    // possible mutation
            }
        }

        return chromRet;
    }

    @Override
    public void setGrowingAge(int par1)
    {
        // To shortcut the age set by the procreate method
        // and make breeding available faster
        if (par1 == 6000)
        {
            super.setGrowingAge(800);
        }
        else if (par1 == -24000)
        {
            super.setGrowingAge(-1000);
        }
        else
        {
            super.setGrowingAge(par1);
        }
    }
}
