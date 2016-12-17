package ChangeReach;

import ChangeReach.IExtendedPlayerController;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.client.network.NetHandlerPlayClient;

/**
 * Created by kojin15.
 */
public class PlayerController extends PlayerControllerMP
        implements IExtendedPlayerController {
    private float distance = 0.0F;

    public PlayerController(Minecraft p_i45062_1_, NetHandlerPlayClient p_i45062_2_) {
        super(p_i45062_1_, p_i45062_2_);
    }

    public float getBlockReachDistance() {
        return super.getBlockReachDistance() + this.distance;
    }

    public void setReachDistanceExtension(float f) {
        this.distance = f;
    }

    public float getReachDistanceExtension() {
        return this.distance;
    }
}
