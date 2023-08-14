package com.xujunhao6082.hbmanvauto.block;

import api.hbm.energy.IEnergyUser;
import com.hbm.inventory.RecipesCommon;
import com.hbm.inventory.recipes.anvil.AnvilRecipes;
import com.hbm.inventory.recipes.anvil.AnvilRecipes.AnvilConstructionRecipe;
import com.hbm.tileentity.TileEntityMachineBase;
import com.hbm.util.InventoryUtil;
import com.xujunhao6082.hbmanvauto.gui.ContainerAutoAnvilSettingMode;
import cpw.mods.fml.common.network.simpleimpl.*;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;

public class TileEntityAutoAnvil extends TileEntityMachineBase implements IEnergyUser {
    public static int from = 18;
    public static int size = 9;
    public static int PowerReduce = 100;
    public long power;
    public static final long maxPower = 100000L;
    public AnvilConstructionRecipe recipe;
    public boolean recipeChanged = false;
    public Task task = null;

    public TileEntityAutoAnvil(int scount) {
        super(scount);
        setCustomName(I18n.format("container.autoanvil_storage"));
    }

    @Override
    public String getName() {
        return "container.autoanvil";
    }

    @Override
    public boolean isItemValidForSlot(int i, ItemStack itemStack) {
        return true;
    }

    @Override
    public boolean canExtractItem(int i, ItemStack itemStack, int j) {
        return true;
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int p_94128_1_) {
        return new int[]{0, 1, 2, 3, 4, 5};
    }

    public void networkUnpack(NBTTagCompound data) {
        this.power = data.getLong("power");
        this.recipe = AnvilRecipes.getConstruction().get(data.getInteger("recipe"));
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        this.power = nbt.getLong("power");
        this.recipe = AnvilRecipes.getConstruction().get(nbt.getInteger("recipe"));
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setLong("power", power);
        nbt.setInteger("recipe", AnvilRecipes.getConstruction().indexOf(recipe));
    }

    public void setPower(long i) {
        this.power = i;
    }

    public long getPower() {
        return this.power;
    }

    public long getMaxPower() {
        return maxPower;
    }

    @Override
    public void updateEntity() {
        if (!worldObj.isRemote) {
            if (task != null) {
                task.update();
                if(recipeChanged){
                    task.cancel();
                }
                if (task.input == null) {
                    task = null;
                }
            } else if (recipe != null) {
                ItemStack[] inv = new ItemStack[27];
                boolean make = true;
                ItemStack placeholder=new ItemStack(Blocks.air,0);
                for (int i = 0; i < 27; i++) {
                    inv[i] = this.getStackInSlot(i);
                    if(inv[i]!=null){
                        inv[i]=inv[i].copy();
                    }else {
                        inv[i]=placeholder;
                    }
                }
                for (int i = 0; i < recipe.input.size(); i++) {
                    make &= InventoryUtil.doesInventoryHaveAStack(
                            inv, recipe.input.get(i), false, false);
                }
                if (make) {
                    RecipesCommon.AStack stack;
                    ItemStack[] out, in = new ItemStack[recipe.input.size()];
                    int consumedStacks, requiredStacks;
                    for (int i = 0; i < recipe.input.size(); i++) {
                        stack = recipe.input.get(i);
                        consumedStacks = 0;
                        requiredStacks = stack.stacksize;
                        for (ItemStack itemStack : inv) {
                            if (consumedStacks > requiredStacks) {
                                break;
                            }
                            if (itemStack != null && stack.matchesRecipe(itemStack, true)) {
                                int toConsume = Math.min(itemStack.stackSize, requiredStacks - consumedStacks);
                                itemStack.stackSize -= toConsume;
                                consumedStacks += toConsume;
                                if (in[i] == null) {
                                    in[i] = new ItemStack(itemStack.getItem(), 0);
                                }
                                in[i].stackSize += toConsume;
                            }
                        }
                    }
                    for (int i = 0; i < 27; i++) {
                        if(inv[i]!=placeholder){
                            this.setInventorySlotContents(i, inv[i]);
                        }
                    }
                    out = new ItemStack[recipe.output.size()];
                    for (int i = 0; i < recipe.output.size(); i++) {
                        out[i] = recipe.output.get(i).stack;
                    }
                    task = new Task(in, out);
                }
            }
            if (recipeChanged) {
                recipeChanged = false;
            }
        }
    }

    public class Task {
        private int process = 0;
        public ItemStack[] input;
        private final ItemStack[] output;

        public Task(ItemStack[] input, ItemStack[] output) {
            this.input = input;
            this.output = output;
        }

        public void update() {
            power -= PowerReduce;
            process++;
            if (process > 10) {
                input = null;
                ItemStack stack;
                int delta;
                for (ItemStack itemStack : output) {
                    for (int i = 0; i < size; i++) {
                        if (slots[i + from] == null) {
                            stack = new ItemStack(itemStack.getItem());
                            stack.stackSize = 0;
                        } else {
                            stack = slots[i + from];
                        }
                        delta = stack.getItem().getItemStackLimit(stack) - stack.stackSize;
                        if (stack.getItem().equals(itemStack.getItem()) && delta > 0) {
                            if (itemStack.stackSize > delta) {
                                itemStack.stackSize -= delta;
                                stack.stackSize += delta;
                            } else {
                                stack.stackSize += itemStack.stackSize;
                                itemStack = null;
                            }
                            slots[i + from] = stack;
                        }
                        if (itemStack == null)
                            break;
                    }
                    if (itemStack != null) {
                        Entity out = new EntityItem(worldObj,
                                xCoord, yCoord + 1, zCoord, itemStack);
                        worldObj.spawnEntityInWorld(out);
                    }
                }
            }
        }

        public void cancel() {
            for (ItemStack item : input) {
                Entity out = new EntityItem(worldObj,
                        xCoord, yCoord + 1, zCoord, item);
                worldObj.spawnEntityInWorld(out);
            }
            input = null;
        }
    }

    public static class RecipeSyncMessage implements IMessage {
        public int recipeIndex;
        public int x, y, z;

        public RecipeSyncMessage(AnvilRecipes.AnvilConstructionRecipe recipe, int tx, int ty, int tz) {
            this.recipeIndex = AnvilRecipes.getConstruction().indexOf(recipe);
            x = tx;
            y = ty;
            z = tz;
        }

        @SuppressWarnings("unused")
        public RecipeSyncMessage() {
        }

        public void fromBytes(ByteBuf buf) {
            this.recipeIndex = buf.readInt();
            this.x = buf.readInt();
            this.y = buf.readInt();
            this.z = buf.readInt();
        }

        public void toBytes(ByteBuf buf) {
            buf.writeInt(this.recipeIndex);
            buf.writeInt(this.x);
            buf.writeInt(this.y);
            buf.writeInt(this.z);
        }

        public static class Handle implements IMessageHandler<RecipeSyncMessage, IMessage> {
            public IMessage onMessage(RecipeSyncMessage message, MessageContext ctx) {
                if (message.recipeIndex < 0 || message.recipeIndex >= AnvilRecipes.getConstruction().size())
                    return null;
                EntityPlayer p = ctx.getServerHandler().playerEntity;
                if (!(p.openContainer instanceof ContainerAutoAnvilSettingMode))
                    return null;
                ContainerAutoAnvilSettingMode anvil = (ContainerAutoAnvilSettingMode) p.openContainer;
                AnvilConstructionRecipe recipe = AnvilRecipes.getConstruction().get(message.recipeIndex);
                if (!recipe.isTierValid(anvil.tier)) {
                    if (recipe.tierUpper < 0) {
                        p.addChatComponentMessage(new ChatComponentText(
                                I18n.format("chat.setting_failed_tier.text",
                                        recipe.tierLower, recipe.tierUpper)));
                    } else {
                        p.addChatComponentMessage(new ChatComponentText(
                                I18n.format("chat.setting_failed_tier.text",
                                        recipe.tierLower, "inf")));
                    }
                    return null;
                }
                p.inventoryContainer.detectAndSendChanges();
                TileEntity tile = p.worldObj.getTileEntity(message.x, message.y, message.z);
                if ((tile instanceof TileEntityAutoAnvil)) {
                    ((TileEntityAutoAnvil) tile).recipe = recipe;
                    ((TileEntityAutoAnvil) tile).recipeChanged = true;
                }
                return null;
            }
        }
    }

}
