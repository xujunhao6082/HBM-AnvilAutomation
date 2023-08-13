package com.xujunhao6082.hbmanvauto.block;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockLoader {
    public static class Blocks{
        public static Block autoanvil = new AutoAnvil(Material.anvil);
    }
    public BlockLoader(FMLPreInitializationEvent event) {
        GameRegistry.registerBlock(Blocks.autoanvil, AutoAnvil.getRegisterName());
    }
}
