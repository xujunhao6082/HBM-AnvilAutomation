package com.xujunhao6082.hbmanvauto;

import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;

@Mod(modid = Main.MODID,
        name = Main.NAME,
        version = Main.VERSION,
        dependencies = "required-after:hbm@[1.0.27 BETA (4200,)",
        acceptedMinecraftVersions = "1.7.10"
)
public class Main {
    @SidedProxy(clientSide = "com.xujunhao6082.hbmanvauto.ClientProxy",
            serverSide = "com.xujunhao6082.hbmanvauto.CommonProxy")
    public static CommonProxy proxy;
    public static final String MODID = "hbmanvauto";
    public static final String NAME = "AutoAnvil For HBM-NTM";
    //version = "v" + rawVersion
    public static final String VERSION = "v1.0";
    @Mod.Instance(Main.MODID)
    public static Main instance;
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }
    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }
}
