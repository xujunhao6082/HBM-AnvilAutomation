package com.xujunhao6082.hbmanvauto.item;

import com.hbm.blocks.ITooltipProvider;
import com.hbm.main.MainRegistry;
import com.xujunhao6082.hbmanvauto.Main;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

import java.util.List;

public class AutoTool extends Item implements ITooltipProvider {
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
    @Override
    @SuppressWarnings("unchecked")
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean bool) {
        list.add(EnumChatFormatting.GOLD + "Shift+Right to automate the Anvil.");
    }
}
