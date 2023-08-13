package com.xujunhao6082.hbmanvauto;

import com.hbm.items.ModItems;
import com.xujunhao6082.hbmanvauto.block.BlockLoader;
import com.xujunhao6082.hbmanvauto.item.ItemLoader;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CommonProxy {
    public void preInit(FMLPreInitializationEvent event) {
        new BlockLoader(event);
        new ItemLoader(event);
    }

    public void init(FMLInitializationEvent event) {
        new EventLoader();
        GameRegistry.addShapedRecipe(new ItemStack(ItemLoader.Items.autotool),
                "@*@","@*@","@#@",
                '@', Item.getItemById(265),
                '#',Item.getItemFromBlock(Block.getBlockById(42)),
                '*', ModItems.motor
        );
    }

    public void postInit(FMLPostInitializationEvent event) {

    }
}
