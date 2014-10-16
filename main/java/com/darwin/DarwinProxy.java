/**
 *
 */
package com.darwin;

import cpw.mods.fml.common.registry.EntityRegistry;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;

/**
 * @author Manchou
 *
 */
public class DarwinProxy
{
    public void init()
    {
        BiomeGenBase ocean = BiomeGenBase.ocean;
        BiomeGenBase plains = BiomeGenBase.plains;
        BiomeGenBase desert = BiomeGenBase.desert;
        BiomeGenBase extremeHills = BiomeGenBase.extremeHills;
        BiomeGenBase forest = BiomeGenBase.forest;
        BiomeGenBase taiga = BiomeGenBase.taiga;
        BiomeGenBase swampland = BiomeGenBase.swampland;
        BiomeGenBase river = BiomeGenBase.river;
        BiomeGenBase hell = BiomeGenBase.hell;
        BiomeGenBase sky = BiomeGenBase.sky;
        BiomeGenBase frozenOcean = BiomeGenBase.frozenOcean;
        BiomeGenBase frozenRiver = BiomeGenBase.frozenRiver;
        BiomeGenBase icePlains = BiomeGenBase.icePlains;
        BiomeGenBase iceMountains = BiomeGenBase.iceMountains;
        BiomeGenBase mushroomIsland = BiomeGenBase.mushroomIsland;
        BiomeGenBase mushroomIslandShore = BiomeGenBase.mushroomIslandShore;
        BiomeGenBase beach = BiomeGenBase.beach;
        BiomeGenBase desertHills = BiomeGenBase.desertHills;
        BiomeGenBase forestHills = BiomeGenBase.forestHills;
        BiomeGenBase taigaHills = BiomeGenBase.taigaHills;
        BiomeGenBase extremeHillsEdge = BiomeGenBase.extremeHillsEdge;
        BiomeGenBase jungle = BiomeGenBase.jungle;
        BiomeGenBase jungleHills = BiomeGenBase.jungleHills;
        
        EntityRegistry.addSpawn(EntityDarwinCreature.class, 20, 10, 10, EnumCreatureType.creature,
					new BiomeGenBase[]{desert, beach, iceMountains, icePlains, taiga, taigaHills, desertHills, forest, forestHills, plains, extremeHills, extremeHillsEdge, jungle, jungleHills});
    }
}
