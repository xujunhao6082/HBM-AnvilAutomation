package com.xujunhao6082.hbmanvauto;

import com.xujunhao6082.hbmanvauto.block.BlockLoader;
import com.xujunhao6082.hbmanvauto.item.ItemLoader;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {
    public void preInit(FMLPreInitializationEvent event) {
        new BlockLoader(event);
        new ItemLoader(event);
    }

    public void init(FMLInitializationEvent event) {
        new EventLoader();
    }

    public void postInit(FMLPostInitializationEvent event) {

    }
}
