package com.xujunhao6082.hbmanvauto.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class AutoAnvil extends Block {
    public final Block oldAnvil;
    public AutoAnvil(Material material,Block oldAnvil){
        super(material);
        this.oldAnvil = oldAnvil;
        this.setBlockName(getRegisterName().substring(5));
        this.setHardness(5.0F);
        this.setResistance(100.0F);
        this.setStepSound(Block.soundTypeAnvil);
    }
    public String getRegisterName(){
        return this.oldAnvil.getUnlocalizedName()+"_auto";
    }
}
