/**
 *
 */
package com.darwin.client;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;

import org.lwjgl.opengl.GL11;

import com.darwin.EntityDarwinCreature;

/**
 * @author Manchou
 *
 */
public class ModelDarwinCreature extends ModelBase
{
    public ModelRenderer head;
    public ModelRenderer head1;
    public ModelRenderer head2;
    public ModelRenderer head3;
    public ModelRenderer body;
    public ModelRenderer body0;
    public ModelRenderer body1;
    public ModelRenderer body2;
    public ModelRenderer body3;
    public ModelRenderer leg11;
    public ModelRenderer leg12;
    public ModelRenderer leg13;
    public ModelRenderer leg21;
    public ModelRenderer leg22;
    public ModelRenderer leg23;
    public ModelRenderer arm11;
    public ModelRenderer arm12;
    public ModelRenderer arm13;
    public ModelRenderer arm21;
    public ModelRenderer arm22;
    public ModelRenderer arm23;
    public ModelRenderer tail1;
    public ModelRenderer tail2;
    public ModelRenderer tail3;
    public ModelRenderer snout1;
    public ModelRenderer snout2;
    public ModelRenderer snout3;
    public ModelRenderer ears1;
    public ModelRenderer ears2;
    public ModelRenderer ears3;
    protected float field_78145_g = 8.0F;
    protected float field_78151_h = 4.0F;

    // internal data for rotation points
    protected int height;
    protected int bodyWidth;
    protected int bodyLength;
    protected int headWidth;
    protected int headLength;
    protected float offsetArm = 0;

    public ModelDarwinCreature(float par2)
    {
        this.textureWidth = 64;
        this.textureHeight = 64;
        setTextureOffset("body.biped", 		14, 46);
        setTextureOffset("body.pig", 		8, 38);
        setTextureOffset("body.cow", 		4, 36);
        setTextureOffset("body.cube", 		0, 30);
        setTextureOffset("leg.short", 		48, 24);
        setTextureOffset("leg.normal", 		48, 0);
        setTextureOffset("leg.long", 		50, 2);
        setTextureOffset("tail.main", 		32, 0);
        setTextureOffset("head.big", 		0, 0);
        setTextureOffset("head.medium", 	2, 2);
        setTextureOffset("head.small", 		3, 3);
        setTextureOffset("snout.small", 	9, 24);
        setTextureOffset("snout.medium", 	5, 20);
        setTextureOffset("snout.big", 		0, 16);
        setTextureOffset("ears.small",	 	0, 30);
        setTextureOffset("ears.medium", 	24, 17);
        setTextureOffset("ears.big", 		23, 16);
        body0 = new ModelRenderer(this, "body");
        body0.addBox("biped", -4.0F, -11.0F, -1.5F, 8, 12, 4);// biped creeper
        body1 = new ModelRenderer(this, "body");
        body1.addBox("pig", -5.0F, -15.0F, -2.0F, 10, 16, 8);// pig
        body2 = new ModelRenderer(this, "body");
        body2.addBox("cow", -6.0F, -17.0F, -2.0F, 12, 18, 10);// cow
        body3 = new ModelRenderer(this, "body");
        body3.addBox("cube", -8.0F, -15.0F, -3.0F, 16, 16, 16);// ???
        body0.setRotationPoint(0.0F, 15F, 7.0F);
        body1.setRotationPoint(0.0F, 15F, 7.0F);
        body2.setRotationPoint(0.0F, 15F, 7.0F);
        body3.setRotationPoint(0.0F, 15F, 7.0F);
        body = body0;
        head1 = new ModelRenderer(this, "head");
        head1.addBox("small", -3.0F, -3.0F, -5.0F, 6, 6, 6);// sheep with no wool
        head2 = new ModelRenderer(this, "head");
        head2.addBox("medium", -4.0F, -4.0F, -5.0F, 8, 8, 6);// cow
        head3 = new ModelRenderer(this, "head");
        head3.addBox("big", -4.0F, -4.0F, -7.0F, 8, 8, 8);// biped creeper pig
        head = head1;
        snout1 = new ModelRenderer(this, "snout");
        snout1.addBox("small", -2.0F, 0.0F, -8.0F, 4, 3, 3);// pig
        head1.addChild(snout1);
        head2.addChild(snout1);
        head3.addChild(snout1);
        snout2 = new ModelRenderer(this, "snout");
        snout2.addBox("medium", -3.0000001F, -2.0F, -8.0F, 6, 4, 6);// gorilla
        head1.addChild(snout2);
        head2.addChild(snout2);
        head3.addChild(snout2);
        setRotation(snout2, (float) Math.toRadians(15), 0, 0);
        snout3 = new ModelRenderer(this, "snout");
        snout3.addBox("big", -3.0000001F, -0.0F, -14.0F, 6, 3, 11);// crocodile?
        head1.addChild(snout3);
        head2.addChild(snout3);
        head3.addChild(snout3);
        ears1 = new ModelRenderer(this, "ears");// cow horns
        ears1.addBox("small", -5.0F, -5.0F, -3.0F, 1, 3, 1);
        ears1.addBox("small", 4.0F, -5.0F, -3.0F, 1, 3, 1);
        ears1.addBox("small", -4.0F, -3.0F, -3.0F, 1, 1, 1);
        ears1.addBox("small", 3.0F, -3.0F, -3.0F, 1, 1, 1);
        head1.addChild(ears1);
        head2.addChild(ears1);
        head3.addChild(ears1);
        ears2 = new ModelRenderer(this, "ears");// mouse
        ears2.addBox("medium", -6.0F, -5.0F, -2.0F, 4, 3, 1);
        ears2.addBox("medium", 2.0F, -5.0F, -2.0F, 4, 3, 1);
        head1.addChild(ears2);
        head2.addChild(ears2);
        head3.addChild(ears2);
        ears3 = new ModelRenderer(this, "ears");// rabbit
        ears3.addBox("big", -3.0F, -9.0F, -3.0F, 2, 6, 2);
        ears3.addBox("big", 1.0F, -9.0F, -3.0F, 2, 6, 2);
        head1.addChild(ears3);
        head2.addChild(ears3);
        head3.addChild(ears3);
        leg11 = new ModelRenderer(this, "leg");
        leg11.addBox("short", -2.0F, 0.0F, -2.0F, 4, 6, 4);
        leg11.setRotationPoint(-3.0F, 15F, 7.0F);
        leg12 = new ModelRenderer(this, "leg");
        leg12.addBox("normal", -2.0F, 0.0F, -2.0F, 4, 12, 4);
        leg12.setRotationPoint(-3.0F, 15F, 7.0F);
        leg13 = new ModelRenderer(this, "leg");
        leg13.addBox("long", -2.0F, 0.0F, -2.0F, 2, 30, 2);
        leg13.setRotationPoint(-3.0F, 15F, 7.0F);
        leg21 = new ModelRenderer(this, "leg");
        leg21.addBox("short", -2.0F, 0.0F, -2.0F, 4, 6, 4);
        leg21.setRotationPoint(3.0F, 15F, 7.0F);
        leg22 = new ModelRenderer(this, "leg");
        leg22.addBox("normal", -2.0F, 0.0F, -2.0F, 4, 12, 4);
        leg22.setRotationPoint(3.0F, 15F, 7.0F);
        leg23 = new ModelRenderer(this, "leg");
        leg23.addBox("long", -2.0F, 0.0F, -2.0F, 2, 30, 2);
        leg23.setRotationPoint(3.0F, 15F, 7.0F);
        arm11 = new ModelRenderer(this, "leg");
        arm11.addBox("short", -2.0F, 0.0F, -2.0F, 4, 6, 4);
        body0.addChild(arm11);
        body1.addChild(arm11);
        body2.addChild(arm11);
        body3.addChild(arm11);
        arm12 = new ModelRenderer(this, "leg");
        arm12.addBox("normal", -2.0F, 0.0F, -2.0F, 4, 12, 4);
        body0.addChild(arm12);
        body1.addChild(arm12);
        body2.addChild(arm12);
        body3.addChild(arm12);
        arm13 = new ModelRenderer(this, "leg");
        arm13.addBox("long", -2.0F, 0.0F, -2.0F, 2, 30, 2);
        body0.addChild(arm13);
        body1.addChild(arm13);
        body2.addChild(arm13);
        body3.addChild(arm13);
        arm21 = new ModelRenderer(this, "leg");
        arm21.addBox("short", -2.0F, 0.0F, -2.0F, 4, 6, 4);
        body0.addChild(arm21);
        body1.addChild(arm21);
        body2.addChild(arm21);
        body3.addChild(arm21);
        arm22 = new ModelRenderer(this, "leg");
        arm22.addBox("normal", -2.0F, 0.0F, -2.0F, 4, 12, 4);
        body0.addChild(arm22);
        body1.addChild(arm22);
        body2.addChild(arm22);
        body3.addChild(arm22);
        arm23 = new ModelRenderer(this, "leg");
        arm23.addBox("long", -2.0F, 0.0F, -2.0F, 2, 30, 2);
        body0.addChild(arm23);
        body1.addChild(arm23);
        body2.addChild(arm23);
        body3.addChild(arm23);
        tail1 = new ModelRenderer(this, "tail");
        tail1.addBox("main", -1F, 0F, -2F, 2, 6, 2);
        tail1.setRotationPoint(0.0F, 13F, 7.0F);
        tail2 = new ModelRenderer(this, "tail");
        tail2.addBox("main", -2F, 0F, -4F, 4, 9, 4);
        tail2.setRotationPoint(0.0F, 13F, 7.0F);
        tail3 = new ModelRenderer(this, "tail");
        tail3.addBox("main", -3F, 0F, -2F, 6, 12, 2);
        tail3.setRotationPoint(0.0F, 13F, 7.0F);
    }

    /**
     * Sets the models various rotation angles.
     */
    @Override
    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity)
    {
    	super.setRotationAngles(par1, par2, par3, par4, par5, par6, entity);
        body.setRotationPoint(0.0F, 23 - height, 6.0F);
        tail1.setRotationPoint(0.0F, 21 - height, 7.0F);
        tail2.setRotationPoint(0.0F, 21 - height, 7.0F);
        tail3.setRotationPoint(0.0F, 21 - height, 7.0F);
        int xOffsetLeg = (bodyWidth / 2) - 2;
        leg11.setRotationPoint(-xOffsetLeg, 24 - height, 7.0F);
        leg12.setRotationPoint(-xOffsetLeg, 24 - height, 7.0F);
        leg13.setRotationPoint(-xOffsetLeg, 24 - height, 7.0F);
        leg21.setRotationPoint(xOffsetLeg, 24 - height, 7.0F);
        leg22.setRotationPoint(xOffsetLeg, 24 - height, 7.0F);
        leg23.setRotationPoint(xOffsetLeg + 2, 24 - height, 7.0F);
        head.rotateAngleX = par5 / (180F / (float)Math.PI);
        head.rotateAngleY = par4 / (180F / (float)Math.PI);
        leg11.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
        leg12.rotateAngleX = leg11.rotateAngleX;
        leg13.rotateAngleX = leg11.rotateAngleX;
        leg21.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 1.4F * par2;
        leg22.rotateAngleX = leg21.rotateAngleX;
        leg23.rotateAngleX = leg21.rotateAngleX;
        arm11.setRotationPoint(-xOffsetLeg - offsetArm, -bodyLength + 3F, -1.0F + offsetArm);
        arm11.rotateAngleX = leg21.rotateAngleX - ((float)Math.PI / 2F);
        arm12.setRotationPoint(-xOffsetLeg - offsetArm, -bodyLength + 3F, 0.3F + offsetArm);
        arm12.rotateAngleX = arm11.rotateAngleX;
        arm13.setRotationPoint(-xOffsetLeg - offsetArm / 2, -bodyLength + 3F, 0.3F + offsetArm);
        arm13.rotateAngleX = arm11.rotateAngleX;
        arm21.setRotationPoint(xOffsetLeg + offsetArm, -bodyLength + 3F, -1.0F + offsetArm);
        arm21.rotateAngleX = leg11.rotateAngleX - ((float)Math.PI / 2F);
        arm22.setRotationPoint(xOffsetLeg + offsetArm, -bodyLength + 3F, 0.3F + offsetArm);
        arm22.rotateAngleX = arm21.rotateAngleX;
        arm23.setRotationPoint(2 + xOffsetLeg + offsetArm / 2, -bodyLength + 3F, 0.3F + offsetArm);
        arm23.rotateAngleX = arm21.rotateAngleX;
        tail1.rotateAngleX = 1.15F;// 66 degrees
        tail2.rotateAngleX = tail1.rotateAngleX;
        tail3.rotateAngleX = tail1.rotateAngleX;
    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    /**
     * Sets the models various rotation angles then renders the model.
     */
    @Override
    public void render(Entity par1Entity, float par2, float par3, float par4,
            float par5, float par6, float par7)
    {
        setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
        EntityDarwinCreature entity = (EntityDarwinCreature) par1Entity;
        renderDarwinCreature(entity, par2, par3, par4, par5, par6, par7);
    }

    protected void renderDarwinCreature(EntityDarwinCreature entity, float par2, float par3, float par4,
            float par5, float par6, float par7)
    {
        body.render(par7);
        tail1.render(par7);
        tail2.render(par7);
        tail3.render(par7);
        leg11.render(par7);
        leg12.render(par7);
        leg13.render(par7);
        leg21.render(par7);
        leg21.render(par7);
        leg22.render(par7);
        leg23.render(par7);
        head.render(par7);
    }

    @Override
    public void setLivingAnimations(EntityLivingBase par1EntityLiving, float par2,
            float par3, float par4)
    {
        super.setLivingAnimations(par1EntityLiving, par2, par3, par4);
        setLivingAnimations((EntityDarwinCreature) par1EntityLiving);
    }

    protected void setLivingAnimations(EntityDarwinCreature entity)
    {
        GL11.glColor4f(entity.red(), entity.green(), entity.blue(), 1F);
        float pi = (float) Math.PI;
        float alpha = ((pi / 2F) * entity.getBodyRotation() / 255.0F);

        switch (entity.getBody())
        {
            case 0:
                body = body0;
                bodyWidth = 8;
                bodyLength = 12;
                break;

            case 1:
                body = body1;
                bodyWidth = 10;
                bodyLength = 16;
                break;

            case 2:
                body = body2;
                bodyWidth = 12;
                bodyLength = 18;
                break;

            case 3:
                body = body3;
                bodyWidth = 16;
                bodyLength = 16;
                break;

            default:
                break;
        }

        body.rotateAngleX = pi / 2F - alpha;
        offsetArm = 4 * entity.getBodyRotation() / 255F;

        switch (entity.getHead())
        {
            case 0:
                head = head1;
                head.isHidden = true;
                headWidth = 0;
                headLength = 0;
                break;

            case 1:
                head = head1;
                head.isHidden = false;
                headWidth = 6;
                headLength = 6;
                break;

            case 2:
                head = head2;
                head.isHidden = false;
                headWidth = 8;
                headLength = 6;
                break;

            case 3:
                head = head3;
                head.isHidden = false;
                headWidth = 8;
                headLength = 8;
                break;

            default:
                break;
        }

        leg11.isHidden = entity.getLegLength() != 1;
        leg12.isHidden = entity.getLegLength() != 2;
        leg13.isHidden = entity.getLegLength() != 3;
        leg21.isHidden = entity.getLegLength() != 1;
        leg22.isHidden = entity.getLegLength() != 2;
        leg23.isHidden = entity.getLegLength() != 3;

        switch (entity.getLegLength())
        {
            case 0:
                height = 0;
                break;

            case 1:
                height = 6;
                break;

            case 2:
                height = 12;
                break;

            case 3:
                height = 30;
                break;

            default:
                break;
        }

        head.setRotationPoint(0F, (float)(-(bodyLength - 4) * Math.sin(alpha) + 17F - height), (float)(10F - (bodyLength + 2) * Math.cos(alpha)));
        arm11.isHidden = entity.getArmLength() != 1;
        arm12.isHidden = entity.getArmLength() != 2;
        arm13.isHidden = entity.getArmLength() != 3;
        arm21.isHidden = entity.getArmLength() != 1;
        arm22.isHidden = entity.getArmLength() != 2;
        arm23.isHidden = entity.getArmLength() != 3;
        tail1.isHidden = entity.getTail() != 1;
        tail2.isHidden = entity.getTail() != 2;
        tail3.isHidden = entity.getTail() != 3;
        snout1.isHidden = entity.getSnout() != 1;
        snout2.isHidden = entity.getSnout() != 2;
        snout3.isHidden = entity.getSnout() != 3;
        ears1.isHidden = entity.getEars() != 1;
        ears2.isHidden = entity.getEars() != 2;
        ears3.isHidden = entity.getEars() != 3;
    }
}
