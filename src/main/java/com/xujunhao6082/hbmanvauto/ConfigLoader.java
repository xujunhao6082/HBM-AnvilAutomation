package com.xujunhao6082.hbmanvauto;

import com.xujunhao6082.hbmanvauto.block.TileEntityAutoAnvil;
import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

public class ConfigLoader {
    private static Logger logger;
    public static void load(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        Configuration config = new Configuration(event.getSuggestedConfigurationFile());
        config.load();
        logger.info("Loading config. ");
        TileEntityAutoAnvil.from = config.get("AnvilOutputArea", "from", 18,
                "First Output slot", 0, 27).getInt();
        TileEntityAutoAnvil.size = config.get("AnvilOutputArea", "size", 9,
                "Size of output area", 0, 27 - TileEntityAutoAnvil.from).getInt();
        TileEntityAutoAnvil.powerReduce = config.get("Machine", "autoAnvilNeedPowerLow", 100,
                "Power that AutoAnvil need(Low)").getInt();
        TileEntityAutoAnvil.powerReduce += (long) config.get("Machine", "autoAnvilNeedPowerHigh", 0,
                "Power that AutoAnvil need(High)", 0, 0x7FFFFFFF).getInt() <<32;
        TileEntityAutoAnvil.maxPower = config.get("Machine", "autoAnvilMaxPowerLow", 100000,
                "Power that AutoAnvil can store(Low)").getInt();
        TileEntityAutoAnvil.maxPower += (long) config.get("Machine", "autoAnvilMaxPowerHigh", 0,
                "Power that AutoAnvil can store(High)", 0, 0x7FFFFFFF).getInt() <<32;
        config.save();
        logger.info("Loaded Successful");
    }
    @SuppressWarnings("unused")
    public static Logger logger() {
        return logger;
    }
}