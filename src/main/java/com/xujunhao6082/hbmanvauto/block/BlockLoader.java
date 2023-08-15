package com.xujunhao6082.hbmanvauto.block;

import com.xujunhao6082.hbmanvauto.Main;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import com.hbm.blocks.ModBlocks;

public class BlockLoader {
    public static class Blocks{
        public static Block[] autoanvil = new AutoAnvil[]{
                new AutoAnvil(Material.iron,ModBlocks.anvil_iron),
                new AutoAnvil(Material.iron,ModBlocks.anvil_lead),
                new AutoAnvil(Material.iron,ModBlocks.anvil_steel),
                new AutoAnvil(Material.iron,ModBlocks.anvil_meteorite),
                new AutoAnvil(Material.iron,ModBlocks.anvil_starmetal),
                new AutoAnvil(Material.iron,ModBlocks.anvil_ferrouranium),
                new AutoAnvil(Material.iron,ModBlocks.anvil_bismuth),
                new AutoAnvil(Material.iron,ModBlocks.anvil_schrabidate),
                new AutoAnvil(Material.iron,ModBlocks.anvil_dnt),
                new AutoAnvil(Material.iron,ModBlocks.anvil_osmiridium),
                new AutoAnvil(Material.iron,ModBlocks.anvil_murky)
        };
    }
    public BlockLoader(FMLPreInitializationEvent ignoredEvent) {
        for (Block block:Blocks.autoanvil) {
            GameRegistry.registerBlock(block, ((AutoAnvil)block).getRegisterName());
        }
        GameRegistry.registerTileEntity(TileEntityAutoAnvil.class,
                "tileentity_autoanvil");

    }
}
