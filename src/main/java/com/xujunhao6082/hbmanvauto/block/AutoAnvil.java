package com.xujunhao6082.hbmanvauto.block;

import com.hbm.blocks.machine.NTMAnvil;
import com.hbm.main.MainRegistry;
import com.hbm.tileentity.IGUIProvider;
import com.xujunhao6082.hbmanvauto.Main;
import com.xujunhao6082.hbmanvauto.gui.*;
import com.xujunhao6082.hbmanvauto.item.ItemLoader;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.ArrayList;

public class AutoAnvil extends Block implements IGUIProvider {
    public final Block oldAnvil;

    public AutoAnvil(Material material, Block oldAnvil) {
        super(material);
        this.oldAnvil = oldAnvil;
        this.setBlockName(getRegisterName().substring(5));
        this.setHardness(5.0F);
        this.setResistance(100.0F);
        this.setStepSound(Block.soundTypeAnvil);
        this.setBlockTextureName(Main.MODID + ":autoanvil");
    }

    public String getRegisterName() {
        return this.oldAnvil.getUnlocalizedName() + "_auto";
    }

    @Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        ArrayList<ItemStack> ret = new ArrayList<>();
        ret.add(new ItemStack(this.oldAnvil, 1));
        ret.add(new ItemStack(ItemLoader.Items.autotool, 1));
        return ret;
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z,
                                    EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        if (world.isRemote) {
            return true;
        } else if (!player.isSneaking()) {
            FMLNetworkHandler.openGui(player, MainRegistry.instance, 0, world, x, y, z);
            return true;
        } else if (player.inventory.getCurrentItem() == null) {
            FMLNetworkHandler.openGui(player, MainRegistry.instance, 1, world, x, y, z);
            return true;
        }
        return false;
    }

    @Override
    public Container provideContainer(int i, EntityPlayer entityPlayer, World world, int x, int y, int z) {
        return (i == 0) ? new ContainerAutoAnvilChangeMode(entityPlayer.inventory,
                ((NTMAnvil)((AutoAnvil)world.getBlock(x, y, z)).oldAnvil).tier)
                : null;
    }

    @Override
    public GuiScreen provideGUI(int i, EntityPlayer entityPlayer, World world, int x, int y, int z) {
        return (i == 0) ? new GUIAutoAnvilChangeMode(entityPlayer.inventory,
                ((NTMAnvil)((AutoAnvil)world.getBlock(x, y, z)).oldAnvil).tier)
                : null;
    }
}
