package com.xujunhao6082.hbmanvauto.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerAutoAnvilStorageMode extends Container {
    private final IInventory lowerChestInventory;
    private final int numRows;
    public ContainerAutoAnvilStorageMode(IInventory p_i1806_1_, IInventory p_i1806_2_)
    {
        this.lowerChestInventory = p_i1806_2_;
        this.numRows = p_i1806_2_.getSizeInventory() / 9;
        p_i1806_2_.openInventory();
        int i = (this.numRows - 4) * 18;
        int j;
        int k;

        for (j = 0; j < this.numRows; ++j)
        {
            for (k = 0; k < 9; ++k)
            {
                this.addSlotToContainer(new Slot(p_i1806_2_, k + j * 9, 8 + k * 18, 18 + j * 18));
            }
        }

        for (j = 0; j < 3; ++j)
        {
            for (k = 0; k < 9; ++k)
            {
                this.addSlotToContainer(new Slot(p_i1806_1_, k + j * 9 + 9, 8 + k * 18, 103 + j * 18 + i));
            }
        }

        for (j = 0; j < 9; ++j)
        {
            this.addSlotToContainer(new Slot(p_i1806_1_, j, 8 + j * 18, 161 + i));
        }
    }

    public boolean canInteractWith(EntityPlayer p_75145_1_)
    {
        return this.lowerChestInventory.isUseableByPlayer(p_75145_1_);
    }
    public ItemStack transferStackInSlot(EntityPlayer p_82846_1_, int p_82846_2_)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(p_82846_2_);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (p_82846_2_ < this.numRows * 9)
            {
                if (!this.mergeItemStack(itemstack1, this.numRows * 9, this.inventorySlots.size(), true))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 0, this.numRows * 9, false))
            {
                return null;
            }

            if (itemstack1.stackSize == 0)
            {
                slot.putStack(null);
            }
            else
            {
                slot.onSlotChanged();
            }
        }

        return itemstack;
    }
    public void onContainerClosed(EntityPlayer p_75134_1_)
    {
        super.onContainerClosed(p_75134_1_);
        this.lowerChestInventory.closeInventory();
    }
    @SuppressWarnings("unused")
    public IInventory getLowerChestInventory()
    {
        return this.lowerChestInventory;
    }
}
