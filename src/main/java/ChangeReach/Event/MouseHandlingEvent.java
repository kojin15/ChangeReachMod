package ChangeReach.Event;

import ChangeReach.ChangeReachCore;
import ChangeReach.Server.Packet.MouseClickEventPacket;
import ChangeReach.Server.PacketHandler;
import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Timer;
import net.minecraft.util.Vec3;

import java.util.List;

/**
 * Created by kojin15.
 */
public class MouseHandlingEvent {

    public static Minecraft mc = Minecraft.getMinecraft();
    private static Timer timer = ObfuscationReflectionHelper.getPrivateValue(Minecraft.class, mc, 16);
    private static MouseOverSpecialReach MOSR;

    @SubscribeEvent
    public void mouseHandlingEvent(InputEvent.MouseInputEvent event) {
        if (mc.gameSettings.keyBindAttack.getIsKeyPressed() && mc.thePlayer != null) {
            changeObjectMouseOver(mc.thePlayer, event);
        }
    }

    private void changeObjectMouseOver(EntityPlayer player, InputEvent.MouseInputEvent event) {
        //指定した距離のポインタ取得
        MovingObjectPosition MOP = MOSR.getMouseOverSpecialReach(player, (double)(ChangeReachCore.BlockReach + 5), timer.renderPartialTicks);
        if (MOP != null) {
            if (MOP.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY) {
                Entity pointedEntity = MOP.entityHit;
                if (pointedEntity instanceof EntityLivingBase || pointedEntity instanceof EntityItemFrame) {
                    //サーバーに攻撃したEntityとかを送るとこ
                    PacketHandler.INSTANCE.sendToServer(new MouseClickEventPacket(pointedEntity));
                }
            }

        }


    }

}
