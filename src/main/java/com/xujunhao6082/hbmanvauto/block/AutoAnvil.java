package com.xujunhao6082.hbmanvauto.block;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.MinecraftForge;

public class AutoAnvil extends Block {

    protected AutoAnvil(Material material) {
        super(material);
        this.setBlockName(getRegisterName());
        this.setHardness(5.0F);
        this.setResistance(100.0F);
        this.setStepSound(Block.soundTypeAnvil);

    }
    public static String getRegisterName(){
        return "autoanvil";
    }
}
