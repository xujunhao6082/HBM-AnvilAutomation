package com.xujunhao6082.hbmanvauto;

import com.hbm.blocks.machine.NTMAnvil;
import com.xujunhao6082.hbmanvauto.item.ItemLoader.Items;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.world.WorldSettings;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

public class EventLoader {
    public EventLoader() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onPlayerClick(PlayerInteractEvent event) {
        if(!event.world.isRemote){
            ItemStack item = event.entityPlayer.inventory.getCurrentItem();
            if(item==null){
                return;
            }
            if (item.getItem().equals(Items.autotool) &&
                    event.action.equals(PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK)) {
                Block b = event.world.getBlock(event.x, event.y, event.z),nb;
                if (b instanceof NTMAnvil) {
                    if(!((EntityPlayerMP)event.entityPlayer).
                            theItemInWorldManager.getGameType().equals(WorldSettings.GameType.CREATIVE)){
                        event.entityPlayer.inventory.decrStackSize(
                                event.entityPlayer.inventory.currentItem, 1);
                    }
                    event.world.setBlock(event.x, event.y, event.z,
                            Block.getBlockFromName(Main.MODID+":"+b.getUnlocalizedName()+"_auto"));
                }
            }
        }
    }
}