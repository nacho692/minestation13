package pia.minestation13;

import net.minecraftforge.common.ModDimension;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod.EventBusSubscriber(modid = "minestation13", bus = Mod.EventBusSubscriber.Bus.MOD)
public class SpaceDimensionRegistrar {

    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * SPACE_DIMENSION contains the automatically injected space_dimension.
     * This object holder is automatically injected with the correct space_dimension once registered.
     */
    @ObjectHolder("minestation13:space_dimension")
    public static final ModDimension SPACE_DIMENSION = null;

    @SubscribeEvent
    public static void onDimensionRegistryEvent(RegistryEvent.Register<ModDimension> event) {
        event.getRegistry().register(new SpaceModDimension().setRegistryName("minestation13:space_dimension"));
        LOGGER.info("Mod dimensions registered.");
    }
}
