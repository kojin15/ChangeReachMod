package ChangeReach.Server;

/**
 * Created by kojin15.
 */
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import ChangeReach.Server.Packet.MouseClickEventPacket;
import ChangeReach.Server.Packet.MouseClickEventPacketHandler;

public class PacketHandler {
    /*MOD固有のSimpleNetworkWrapperを取得。
    * 文字列は他のMODと被らないようにMOD_IDを指定しておくと良い*/
    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel("samplemod");

    public static void init() {
        /*Messageクラスの登録。
        * 第一引数：IMessageHandlerクラス
        * 第二引数：送るMessageクラス
        * 第三引数：登録番号。255個まで
        * 第四引数：ClientとServerのどちらに送るか。送り先*/
        INSTANCE.registerMessage(MouseClickEventPacketHandler.class, MouseClickEventPacket.class, 0, Side.SERVER);
    }
}
