package com.xujunhao6082.hbmanvauto.block;

import com.xujunhao6082.hbmanvauto.item.ItemLoader;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.ArrayList;

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
    @Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune){
        ArrayList<ItemStack> ret = new ArrayList<>();
        ret.add(new ItemStack(this.oldAnvil,1));
        ret.add(new ItemStack(ItemLoader.Items.autotool,1));
        return ret;
    }
}
