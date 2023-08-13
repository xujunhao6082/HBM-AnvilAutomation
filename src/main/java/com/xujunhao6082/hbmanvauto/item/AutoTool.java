package com.xujunhao6082.hbmanvauto.item;

import com.hbm.main.MainRegistry;
import com.xujunhao6082.hbmanvauto.Main;
import net.minecraft.item.Item;
public class AutoTool extends Item {
    public AutoTool()
    {
        super();
        this.setUnlocalizedName("autotool");
        this.setTextureName(Main.MODID+":"+getRegisterName());
        this.setCreativeTab(MainRegistry.consumableTab);
        this.setMaxStackSize(1);
    }
    public static String getRegisterName(){
        return "autotool";
    }
}
