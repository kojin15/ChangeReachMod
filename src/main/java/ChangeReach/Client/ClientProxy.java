package ChangeReach.Client;

import ChangeReach.Event.ExtendedPlayerReach;
import ChangeReach.Event.MouseHandlingEvent;
import ChangeReach.kojin15sLib;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.ReflectionHelper;
import ChangeReach.PlayerController;
import ChangeReach.IExtendedPlayerController;
import ChangeReach.Server.CommonProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraft.world.WorldSettings;
import net.minecraftforge.common.MinecraftForge;

/**
 * Created by kojin15.
 */
public class ClientProxy extends CommonProxy {

    @Override
    public int getNewRenderType() {
        return RenderingRegistry.getNextAvailableRenderId();
    }

    @Override
    public World getClientWorld() {
        return FMLClientHandler.instance().getClient().theWorld;
    }

    public EntityPlayer getClientPlayer() {
        return Minecraft.getMinecraft().thePlayer;
    }

    @Override
    public void registerEventHandlers() {
        super.registerEventHandlers();
        MinecraftForge.EVENT_BUS.register(new MouseHandlingEvent());
        FMLCommonHandler.instance().bus().register(new MouseHandlingEvent());
        MinecraftForge.EVENT_BUS.register(new ExtendedPlayerReach());
        FMLCommonHandler.instance().bus().register(new ExtendedPlayerReach());
    }

    @Override
    public void registerRenderer() {

    }

    @Override
    public void registerTileEntitySpecialRenderer() {

    }

    @Override
    public void setExtraReach(EntityLivingBase entity, float reach) {
        super.setExtraReach(entity, reach);
        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayer player = mc.thePlayer;
        if (entity == player) {
            if (!(mc.playerController instanceof IExtendedPlayerController)) {
                //ここがないとクラッシュする
                WorldSettings.GameType type = (WorldSettings.GameType) ReflectionHelper.getPrivateValue(PlayerControllerMP.class, mc.playerController, kojin15sLib.CURRENT_GAME_TYPE);
                NetHandlerPlayClient net = (NetHandlerPlayClient) ReflectionHelper.getPrivateValue(PlayerControllerMP.class, mc.playerController, kojin15sLib.NET_CLIENT_HANDLER);
                PlayerController controller = new PlayerController(mc, net);
                boolean isFlying = player.capabilities.isFlying;
                boolean allowFlying = player.capabilities.allowFlying;
                controller.setGameType(type);
                player.capabilities.isFlying = isFlying;
                player.capabilities.allowFlying = allowFlying;
                mc.playerController = controller;
            }
            ((IExtendedPlayerController) mc.playerController).setReachDistanceExtension(reach);
        }
    }

}


