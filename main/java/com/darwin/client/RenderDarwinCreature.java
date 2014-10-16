package com.darwin.client;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.darwin.EntityDarwinCreature;
import com.darwin.ModDarwin;

public class RenderDarwinCreature extends RenderLiving
{

    public final static String TEXTUREBASE = "textures/mobs/darwin";
    public final static String PNG = ".png";
    
    public float scale;
    private ResourceLocation[] textures;
    
    public RenderDarwinCreature(ModelBase modelbase, float shadowSize)
    {
        super(modelbase, shadowSize);
        this.scale = 1.0F;
        textures = new ResourceLocation[EntityDarwinCreature.MAX_TEXTURE_INDEX];
        for (int i=0 ; i < textures.length ; i++){
        	textures[i] = new ResourceLocation(ModDarwin.MODID, TEXTUREBASE+i+PNG);
        }
    }

    @Override
    protected void preRenderCallback(EntityLivingBase entity, float f)
    {
        preRenderScale(entity, f);
    }

    protected void preRenderScale(Entity entity, float f)
    {
        GL11.glScalef(scale, scale, scale);
    }

    protected int getColorMultiplier(EntityLivingBase par1EntityLiving, float par2, float par3)
    {
        return 0x00FF00;
    }
    
    public ResourceLocation getTexture(int index)
    {
    	if (textures.length > index)
    		return textures[index];
    	else 
    		return textures[0];
    }

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		if (entity instanceof EntityDarwinCreature)
			return getTexture(((EntityDarwinCreature) entity).getTextureIndex());
		return getTexture(0);
	}
}
