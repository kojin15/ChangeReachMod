package ChangeReach.Server;

import ChangeReach.Event.MouseHandlingEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

/**
 * Created by kojin15.
 */
public class CommonProxy {

    public int getNewRenderType() {
        return -1;
    }

    @SuppressWarnings("unused")
    public World getClientWorld() {
        return null;
    }

    public EntityPlayer getClientPlayer() {
        return null;
    }

    public void registerEventHandlers() {

    }

    public final void registerTileEntity() {

    }

    public void registerRenderer() {

    }

    public void registerTileEntitySpecialRenderer() {

    }

    public void setExtraReach(EntityLivingBase entity, float reach) {
        if ((entity instanceof EntityPlayerMP)) {
            ((EntityPlayerMP) entity).theItemInWorldManager.setBlockReachDistance(Math.max(5.0D, ((EntityPlayerMP) entity).theItemInWorldManager.getBlockReachDistance() + reach));
        }
    }

}
