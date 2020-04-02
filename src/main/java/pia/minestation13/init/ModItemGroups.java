package pia.minestation13.init;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import pia.minestation13.MineStation13;
import pia.minestation13.item.CoffeeMachineItem;

import java.util.function.Supplier;

/**
 * ModItemGroups define the grouping of items.
 * Items may or may not belong to an ItemGroup. It is a minecraft concept, when in creative mode items are
 * grouped by their ItemGroup.
 */
public class ModItemGroups extends ItemGroup {

    /**
     * MOD_ITEM_GROUP defines the main and only Item Group for MS13.
     */
    public static final ItemGroup MOD_ITEM_GROUP = new ModItemGroups(
            MineStation13.MOD_ID, () -> new ItemStack(ModItems.COFFEE_MACHINE));

    private final Supplier<ItemStack> iconSupplier;

    /**
     * ModItemGroup create a new item group for the Mod. It is private because it's only meant to be used by static
     * internal variables.
     * We don't expect to have many item groups.
     * @param name The item group name.
     * @param iconSupplier Supplies an icon. It will be shown as the group's icon.
     */
    private ModItemGroups(final String name, final Supplier<ItemStack> iconSupplier) {
        super(name);
        this.iconSupplier = iconSupplier;
    }

    @Override
    public ItemStack createIcon() {
        return iconSupplier.get();
    }
}
