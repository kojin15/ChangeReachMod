package ChangeReach;


import ChangeReach.Server.CommonProxy;
import ChangeReach.Server.PacketHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

/**
 * Created by kojin15.
 */

@Mod(modid = ChangeReachCore.ModID, version = ChangeReachCore.Version, useMetadata = true)
public class ChangeReachCore {
    public static final String ModID = "ChangeReachCore";
    public static final String Version = "1.0.0";

    public static Configuration ChangeReach;

    public static int BlockReach = 10;

    @Mod.Instance("ChangeReachCore")
    public static ChangeReachCore core;

    @SuppressWarnings("WeakerAccess")
    @SidedProxy(clientSide = "ChangeReach.Client.ClientProxy", serverSide = "ChangeReach.Server.CommonProxy")
    public static CommonProxy Proxy;

    @Mod.Metadata
    public ModMetadata metadata;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        Proxy.registerEventHandlers();
        PacketHandler.init();
        //config生成
        ChangeReach = new Configuration(new File("config/ChangeReach.cfg"));
        syncConfig();

    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        Proxy.registerTileEntity();
        Proxy.registerTileEntitySpecialRenderer();
        Proxy.registerRenderer();
        FMLCommonHandler.instance().bus().register(Proxy);

    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }

    private void loadMeta(ModMetadata meta) {
        meta.modId = ModID;
        meta.version = Version;
        meta.authorList.add("kojin15");
    }

    public static void syncConfig() {
        BlockReach = ChangeReach.getInt("BlockReach", Configuration.CATEGORY_GENERAL, 10, 0, 128, "default = 5, Shorter when it is less than 5");
        if(ChangeReach.hasChanged())
            ChangeReach.save();
    }




}