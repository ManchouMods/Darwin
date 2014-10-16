/**
 *
 */
package com.darwin;

import ibxm.Player;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

/**
 * @author Manchou
 *
 */
public class DarwinPacketHandler implements IPacketHandler
{
    public final static String CHANNEL_ID = "DarwinChan";

    /* (non-Javadoc)
     * @see cpw.mods.fml.common.network.IPacketHandler#onPacketData(net.minecraft.src.NetworkManager, net.minecraft.src.Packet250CustomPayload, cpw.mods.fml.common.network.Player)
     */
    @Override
    public void onPacketData(INetworkManager manager,
            Packet250CustomPayload packet, Player player)
    {
        if (packet.channel.equals(CHANNEL_ID))
        {
            handleGenome(manager, packet, player);
        }
    }

    private void handleGenome(INetworkManager manager,
            Packet250CustomPayload packet, Player player)
    {
        try
        {
            DataInputStream inputStream = new DataInputStream(new ByteArrayInputStream(packet.data));
            long genome = inputStream.readLong();
            int entityId = inputStream.readInt();
            System.out.println("Recieve Genome: " + genome);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
