package ChangeReach.Event;

import ChangeReach.ChangeReachCore;
import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Timer;
import net.minecraft.util.Vec3;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;

import java.util.List;

/**
 * Created by kojin15.
 */
public class ExtendedPlayerReach {

    public static Minecraft mc = Minecraft.getMinecraft();
    private static Timer timer = ObfuscationReflectionHelper.getPrivateValue(Minecraft.class, mc, 16);
    private static MouseOverSpecialReach MOSR;

    @SubscribeEvent
    public void PlayerTickEventSubscriber(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.START) {
            MovingObjectPosition MOP = MOSR.getMouseOverSpecialReach(event.player, (double)(ChangeReachCore.BlockReach + 5), timer.renderPartialTicks);
            if (MOP != null) {
                //攻撃と同時にブロック破壊を防ぐために一度短くする
                if (MOP.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY) {
                    ChangeReachCore.Proxy.setExtraReach((EntityLivingBase)event.player, -5.0F);
                }
                //configの値は分かりやすくするために0を最低にしてあるから元の値(最低は-5)に戻す
                if (MOP.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
                    ChangeReachCore.Proxy.setExtraReach((EntityLivingBase)event.player, (float)ChangeReachCore.BlockReach - 5.0F);
                }
            }
        }
    }

}
