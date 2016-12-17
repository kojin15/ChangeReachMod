package ChangeReach.Server.Packet;

/**
 * Created by kojin15.
 */
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class MouseClickEventPacketHandler implements IMessageHandler<MouseClickEventPacket, IMessage> {
    @Override
    public IMessage onMessage(MouseClickEventPacket message, MessageContext ctx) {
        EntityPlayer player = ctx.getServerHandler().playerEntity;
        Entity targetEntity = message.getEntityFromId(player.worldObj);
        player.attackTargetEntityWithCurrentItem(targetEntity);
        return null;
    }
}
