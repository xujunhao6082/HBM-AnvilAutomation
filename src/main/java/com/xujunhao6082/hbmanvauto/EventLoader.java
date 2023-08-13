package com.xujunhao6082.hbmanvauto;

import com.xujunhao6082.hbmanvauto.item.ItemLoader.Items;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

public class EventLoader {
    public EventLoader()
    {
        MinecraftForge.EVENT_BUS.register(this);
    }
    @SubscribeEvent
    public void onPlayerClick(PlayerInteractEvent event){
        if(event.entityPlayer.inventory.getCurrentItem().getItem().equals(Items.autotool)){
            Entity tnt = new EntityTNTPrimed(event.entity.worldObj,event.x,event.y,event.z,null);
            event.entity.worldObj.spawnEntityInWorld(tnt);
        }
    }
}