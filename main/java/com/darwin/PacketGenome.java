/**
 *
 */
package com.darwin;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLCommonHandler;

/**
 * @author sebastien
 *
 */
public class PacketGenome extends Packet250CustomPayload
{
    /**
     *
     */
    public PacketGenome()
    {
        super();
    }

    /**
     * @param par1Str
     * @param par2ArrayOfByte
     */
    public PacketGenome(String par1Str, byte[] par2ArrayOfByte)
    {
        super(par1Str, par2ArrayOfByte);
    }

    /**
     * Passes this Packet on to the NetHandler for processing.
     */
    @Override
    public void processPacket(NetHandler par1NetHandler)
    {
        try
        {
            DataInputStream inputStream = new DataInputStream(new ByteArrayInputStream(data));
            long genome = inputStream.readLong();
            int entityId = inputStream.readInt();
            System.out.println("Receive Genome: " + genome + " for entity #" + entityId);
            FMLClientHandler handler = (FMLClientHandler) FMLCommonHandler.instance().getSidedDelegate();
            EntityDarwinCreature entity = (EntityDarwinCreature) handler.getClient().theWorld.getEntityByID(entityId);
//			if (entity != null)
//				entity.setGenome(genome);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
