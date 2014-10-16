/**
 *
 */
package com.darwin.client;

import com.darwin.DarwinProxy;
import com.darwin.EntityDarwinCreature;

import cpw.mods.fml.client.registry.RenderingRegistry;

/**
 * @author Manchou
 *
 */
public class DarwinClientProxy extends DarwinProxy
{
    @Override
    public void init()
    {
        super.init();
        RenderingRegistry.registerEntityRenderingHandler(EntityDarwinCreature.class, new RenderDarwinCreature(new ModelDarwinCreature(0F), 0.5F));
    }
}
