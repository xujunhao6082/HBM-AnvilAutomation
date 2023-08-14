package com.xujunhao6082.hbmanvauto;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.*;
import cpw.mods.fml.relauncher.Side;

public class NetworkLoader {
    public static SimpleNetworkWrapper instance = NetworkRegistry.INSTANCE.newSimpleChannel(Main.MODID);

    private static int nextID = 0;

    public static <REQ extends IMessage, REPLY extends IMessage> void registerMessage(
            Class<? extends IMessageHandler<REQ, REPLY>> messageHandler, Class<REQ> requestMessageType, Side side) {
        instance.registerMessage(messageHandler, requestMessageType, nextID++, side);
    }
}