package com.xujunhao6082.hbmanvauto;

import net.minecraft.init.Blocks;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;

@Mod(modid = com.xujunhao6082.hbmanvauto.Main.MODID, version = com.xujunhao6082.hbmanvauto.Main.VERSION,
        dependencies = "required-after:hbm@[1.0.27X4200,)")
public class Main {
    public static final String MODID = "hbmanvauto";
    public static final String VERSION = "1.0";

    @EventHandler
    public void init(FMLInitializationEvent event) {
        // some example code
    }
}
