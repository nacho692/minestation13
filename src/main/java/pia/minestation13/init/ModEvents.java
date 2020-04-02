package pia.minestation13.init;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ModDimension;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistryEntry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pia.minestation13.MineStation13;
import pia.minestation13.block.CoffeeMachineBlock;
import pia.minestation13.item.CoffeeMachineItem;

/**
 * ModEvents handles subscription to MOD events.
 * MOD events include mostly registering events. The argument type on the subscriptions defines which event we are
 * subscribed to, so even if the event is not used, the argument is required.
 */
@Mod.EventBusSubscriber(modid = MineStation13.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEvents {

    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * onDimensionRegistryEvent registers the space dimension.
     * @param event The event fired when a dimensions should be registered.
     */
    @SubscribeEvent
    public static void onDimensionRegistryEvent(RegistryEvent.Register<ModDimension> event) {
        event.getRegistry().register(new ModSpaceDimension().setRegistryName(MineStation13.MOD_ID, "space_dimension"));
    }

    /**
     * onRegisterBlocks registers the mod blocks.
     * @param event The event fired when blocks should be registered.
     */
    @SubscribeEvent
    public static void onRegisterBlocks(RegistryEvent.Register<Block> event) {
        CoffeeMachineBlock coffeMachine = new CoffeeMachineBlock(
                Block.Properties.create(Material.ANVIL)
                        .hardnessAndResistance(-1.0F, 360000F));
        event.getRegistry().registerAll(setup(coffeMachine, "coffee_machine"));
    }

    /**
     * onRegisterItems registers the mod items.
     * @param event The event fired when items should be registered.
     */
    @SubscribeEvent
    public static void onRegisterItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(
                setup(new CoffeeMachineItem(ModBlocks.COFFEE_MACHINE,
                                new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP)),
                        "coffee_machine")
        );
    }

    private static <T extends IForgeRegistryEntry<T>> T setup(final T entry, final String name) {
        return entry.setRegistryName(new ResourceLocation(MineStation13.MOD_ID, name));
    }
}
