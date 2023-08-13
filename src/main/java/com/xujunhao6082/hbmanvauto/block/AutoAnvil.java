package com.xujunhao6082.hbmanvauto.block;

import com.xujunhao6082.hbmanvauto.item.ItemLoader;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

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
    public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int metaData){
        if(!world.isRemote){
            world.spawnEntityInWorld(new EntityItem(world,x,y,z,
                    new ItemStack(this.oldAnvil,1)));
            world.spawnEntityInWorld(new EntityItem(world,x,y,z,
                    new ItemStack(ItemLoader.Items.autotool,1)));
        }
    }
}
