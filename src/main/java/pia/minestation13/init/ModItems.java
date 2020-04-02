package pia.minestation13.init;

import net.minecraft.item.Item;
import net.minecraftforge.registries.ObjectHolder;
import pia.minestation13.MineStation13;

/**
 * ModBlocks lists the mod item instances.
 * They are automatically injected after registration due to the {@link ObjectHolder} annotation.
 * The name of the variable must match the resource location defined when registering the item.
 */
@ObjectHolder(MineStation13.MOD_ID)
public class ModItems {
    public static final Item COFFEE_MACHINE = null;
}
