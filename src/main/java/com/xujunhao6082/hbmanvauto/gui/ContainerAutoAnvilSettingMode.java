package com.xujunhao6082.hbmanvauto.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;

public class ContainerAutoAnvilSettingMode extends Container {


    public int tier;

    public ContainerAutoAnvilSettingMode(InventoryPlayer inventory, int tier) {
        this.tier = tier;
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 9; j++) {
                this.addSlotToContainer(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18 + 56));
            }
        }

        for(int i = 0; i < 9; i++) {
            this.addSlotToContainer(new Slot(inventory, i, 8 + i * 18, 142 + 56));
        }

    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return true;
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int par2) {
        ItemStack var3 = null;
        Slot var4 = (Slot) this.inventorySlots.get(par2);

        if(var4 != null && var4.getHasStack()) {
            ItemStack var5 = var4.getStack();
            var3 = var5.copy();

            if(par2 == 2) {

                if(!this.mergeItemStack(var5, 3, this.inventorySlots.size(), true)) {
                    return null;
                }
                var4.onSlotChange(var5, var3);

            } else if(par2 <= 1) {
                if(!this.mergeItemStack(var5, 3, this.inventorySlots.size(), true)) {
                    return null;
                } else {
                    var4.onPickupFromSlot(player, var5);
                }
            } else {

                if(!this.mergeItemStack(var5, 0, 2, false))
                    return null;
            }

            if(var5.stackSize == 0) {
                var4.putStack(null);
            } else {
                var4.onSlotChanged();
            }

            var4.onPickupFromSlot(player, var5);
        }

        return var3;
    }

    @Override
    public void onContainerClosed(EntityPlayer player) {
        super.onContainerClosed(player);
    }
}
