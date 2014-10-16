/**
 *
 */
package com.darwin;

import java.util.Random;

import net.minecraft.init.Items;
import net.minecraft.item.Item;

/**
 * @author Manchou
 *
 */
public class GenomeUtils
{
    public static int Chromosome3PassiveAnimal 		= 21;
    public static int Chromosome3HostileZombie 		= 2229167;
    public static int Chromosome3HostileMob 		= 2098053;
    public static int Chromosome3HostileEnderman 	= 3408295;

    public static int createChromosome1(int texture, int red, int green, int blue)
    {
        return texture + (red << 8) + (green << 16) + (blue << 24);
    }

    public static int createChromosome2(int Head, int Leg, int Arm, int Tail, int Snout, int Ears, int Body, int ArmPosition, int bodyRotation, int drop)
    {
        return (Head << 0) + (Leg << 2) + (Arm << 4) + (Tail << 6) + (Snout << 8)
                + (Ears << 10) + (Body << 12) + (bodyRotation << 16) + (drop << 24);
    }

    protected static void genomePig(EntityDarwinCreature entity)
    {
        int texture = 0;
        int red = 0xee;// 0 to 255
        int green = 0x9e;
        int blue = 0x9e;
        entity.setChromosome11(createChromosome1(texture, red, green, blue));
        entity.setChromosome12(createChromosome1(texture, red, green, blue));
        int Head = 3;// 0 to 3
        int Leg = 1;
        int Arm = 1;
        int Tail = 0;
        int Snout = 1;
        int Ears = 0;
        int Body = 1;
        int ArmPosition = 0;
        int bodyRotation = 0;
        int drop = getDropGeneFromItem(Items.porkchop);
        int chromosme2 = createChromosome2(Head, Leg, Arm, Tail, Snout, Ears, Body, ArmPosition, bodyRotation, drop);
        entity.setChromosome21(chromosme2);
        entity.setChromosome22(chromosme2);
        entity.setChromosome31(Chromosome3PassiveAnimal);
        entity.setChromosome32(Chromosome3PassiveAnimal);
    }

    protected static void genomeSlime(EntityDarwinCreature entity)
    {
        int texture = 6;
        int red = 0x7e;// 0 to 255
        int green = 0xbf;
        int blue = 0x6e;
        entity.setChromosome11(createChromosome1(texture, red, green, blue));
        entity.setChromosome12(createChromosome1(texture, red, green, blue));
        int Head = 0;// 0 to 3
        int Leg = 0;
        int Arm = 0;
        int Tail = 0;
        int Snout = 0;
        int Ears = 0;
        int Body = 3;
        int ArmPosition = 0;
        int bodyRotation = 0;
        int drop = getDropGeneFromItem(Items.slime_ball);
        int chromosme2 = createChromosome2(Head, Leg, Arm, Tail, Snout, Ears, Body, ArmPosition, bodyRotation, drop);
        entity.setChromosome21(chromosme2);
        entity.setChromosome22(chromosme2);
        entity.setChromosome31(Chromosome3HostileMob);
        entity.setChromosome32(Chromosome3HostileMob);
    }

    protected static void genomeCow(EntityDarwinCreature entity)
    {
        int texture = 3;
        int red = 0xaf;// 0 to 255
        int green = 0x8b;
        int blue = 0x62;
        entity.setChromosome11(createChromosome1(texture, red, green, blue));
        entity.setChromosome12(createChromosome1(texture, red, green, blue));
        int Head = 2;// 0 to 3
        int Leg = 2;
        int Arm = 2;
        int Tail = 0;
        int Snout = 0;
        int Ears = 1;
        int Body = 1;
        int ArmPosition = 0;
        int bodyRotation = 0;
        int drop = getDropGeneFromItem(Items.beef);
        entity.setChromosome21(createChromosome2(Head, Leg, Arm, Tail, Snout, Ears, Body, ArmPosition, bodyRotation, drop));
        drop = getDropGeneFromItem(Items.beef);
        entity.setChromosome22(createChromosome2(Head, Leg, Arm, Tail, Snout, Ears, Body, ArmPosition, bodyRotation, drop));
        entity.setChromosome31(Chromosome3PassiveAnimal);
        entity.setChromosome32(Chromosome3PassiveAnimal);
    }

    protected static void genomeZombie(EntityDarwinCreature entity)
    {
        int texture = 4;
        int red = 0x79;// 0 to 255
        int green = 0x9c;
        int blue = 0x65;
        entity.setChromosome11(createChromosome1(texture, red, green, blue));
        entity.setChromosome12(createChromosome1(texture, red, green, blue));
        int Head = 3;// 0 to 3
        int Leg = 2;
        int Arm = 2;
        int Tail = 0;
        int Snout = 0;
        int Ears = 0;
        int Body = 0;
        int ArmPosition = 0;
        int bodyRotation = 250;
        int drop = getDropGeneFromItem(Items.rotten_flesh);
        entity.setChromosome21(createChromosome2(Head, Leg, Arm, Tail, Snout, Ears, Body, ArmPosition, bodyRotation, drop));
        drop = getDropGeneFromItem(Items.rotten_flesh);
        entity.setChromosome22(createChromosome2(Head, Leg, Arm, Tail, Snout, Ears, Body, ArmPosition, bodyRotation, drop));
        entity.setChromosome31(Chromosome3HostileZombie);
        entity.setChromosome32(Chromosome3HostileZombie);
    }

    protected static void genomeEnderman(EntityDarwinCreature entity)
    {
        int texture = 2;
        int red = 0xc5;// 0 to 255
        int green = 0x49;
        int blue = 0xff;
        entity.setChromosome11(createChromosome1(texture, red, green, blue));
        entity.setChromosome12(createChromosome1(texture, red, green, blue));
        int Head = 3;// 0 to 3
        int Leg = 3;
        int Arm = 3;
        int Tail = 0;
        int Snout = 0;
        int Ears = 0;
        int Body = 0;
        int ArmPosition = 0;
        int bodyRotation = 250;
        int drop = getDropGeneFromItem(Items.ender_pearl);
        entity.setChromosome21(createChromosome2(Head, Leg, Arm, Tail, Snout, Ears, Body, ArmPosition, bodyRotation, drop));
        drop = getDropGeneFromItem(Items.ender_pearl);
        entity.setChromosome22(createChromosome2(Head, Leg, Arm, Tail, Snout, Ears, Body, ArmPosition, bodyRotation, drop));
        entity.setChromosome31(Chromosome3HostileEnderman);
        entity.setChromosome32(Chromosome3HostileEnderman);
    }

    protected static void genomeSquirtle(EntityDarwinCreature entity)
    {
        int texture = 9;
        int red = 0x77;// 0 to 255
        int green = 0xdf;
        int blue = 0xff;
        entity.setChromosome11(createChromosome1(texture, red, green, blue));
        entity.setChromosome12(createChromosome1(texture, red, green, blue));
        int Head = 3;// 0 to 3
        int Leg = 1;
        int Arm = 1;
        int Tail = 3;
        int Snout = 0;
        int Ears = 0;
        int Body = 2;
        int ArmPosition = 0;
        int bodyRotation = 210;
        int drop = getDropGeneFromItem(null);
        entity.setChromosome21(createChromosome2(Head, Leg, Arm, Tail, Snout, Ears, Body, ArmPosition, bodyRotation, drop));
        drop = getDropGeneFromItem(null);
        entity.setChromosome22(createChromosome2(Head, Leg, Arm, Tail, Snout, Ears, Body, ArmPosition, bodyRotation, drop));
        entity.setChromosome31(2622628);
        entity.setChromosome32(2622628);
    }

    protected static void genomePikachu(EntityDarwinCreature entity)
    {
        int texture = 10;
        int red = 0xf7;// 0 to 255
        int green = 0xe0;
        int blue = 0x6f;
        entity.setChromosome11(createChromosome1(texture, red, green, blue));
        entity.setChromosome12(createChromosome1(texture, red, green, blue));
        int Head = 3;// 0 to 3
        int Leg = 1;
        int Arm = 1;
        int Tail = 3;
        int Snout = 2;
        int Ears = 3;
        int Body = 1;
        int ArmPosition = 0;
        int bodyRotation = 0;
        int drop = getDropGeneFromItem(Items.redstone);
        entity.setChromosome21(createChromosome2(Head, Leg, Arm, Tail, Snout, Ears, Body, ArmPosition, bodyRotation, drop));
        entity.setChromosome22(createChromosome2(Head, Leg, Arm, Tail, Snout, Ears, Body, ArmPosition, bodyRotation, drop));
        entity.setChromosome31(2098309);
        entity.setChromosome32(2098309);
    }

    protected static void genomeRandom(EntityDarwinCreature entity)
    {
        Random rand = entity.worldObj.rand;
        entity.setChromosome11(rand.nextInt());
        entity.setChromosome12(rand.nextInt());
        entity.setChromosome21(rand.nextInt());
        entity.setChromosome22(rand.nextInt());
        entity.setChromosome31(rand.nextInt());
        entity.setChromosome32(rand.nextInt());
    }
    
    public static int getDropGeneFromItem(Item item){
    	if (item==null)
    		return 0;
    	return (Item.getIdFromItem(item) - 256) & 255;
    }
    
    
}
