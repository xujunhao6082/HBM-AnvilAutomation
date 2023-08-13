package com.xujunhao6082.hbmanvauto.item;

import com.xujunhao6082.hbmanvauto.Main;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class ItemLoader {
    public static class Items{
        public static Item autotool = new AutoTool();
    }

    public ItemLoader(FMLPreInitializationEvent event) {
        GameRegistry.registerItem(Items.autotool, AutoTool.getRegisterName(), Main.MODID);
    }
}
