/**
 *
 */
package com.darwin;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;

/**
 * @author Manchou
 *
 */
@Mod(modid = ModDarwin.MODID, name = "Darwin", version = "0.2.3")
//@NetworkMod(
//        clientSideRequired = true,
//        serverSideRequired = true,
//        channels = {DarwinPacketHandler.CHANNEL_ID}, // These channels are automatically registerred for you. nice huh :D unlimited channels here. see [[Packets and channels]]
//        packetHandler = DarwinPacketHandler.class, // Handling recieved packets. Works just like the old interface. see [[Packets and channels]]
////     connectionHandler = yourConnectionHandler.class,  // Handling connection events. Works like the old interface.
//        // And the best for last... :D
//        versionBounds = "[0.2.3]" // VERSION CHECKS!
//)
public class ModDarwin
{
    @SidedProxy(clientSide = "com.darwin.client.DarwinClientProxy", serverSide = "com.darwin.DarwinProxy")
    public static DarwinProxy proxy;
    
    public final static String MODID = "darwin";
    
    public final static boolean debug = false;
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        EntityRegistry.registerGlobalEntityID(EntityDarwinCreature.class, "Creature", EntityRegistry.findGlobalUniqueEntityId(), 0x880000, 0x00FF00);
        proxy.init();
    }
}
