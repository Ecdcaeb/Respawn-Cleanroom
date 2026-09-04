package mods.Hileb.respawn;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
public class RRMod {

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        Constants.LOG.info("Hello From {} v{}!", Reference.MOD_NAME, Reference.VERSION);
        CommonClass.init();
    }
}
