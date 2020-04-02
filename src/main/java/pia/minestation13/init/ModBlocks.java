package pia.minestation13.init;

import net.minecraft.block.Block;
import net.minecraftforge.registries.ObjectHolder;
import pia.minestation13.MineStation13;

/**
 * ModBlocks lists the mod block instances.
 * They are automatically injected after registration due to the {@link ObjectHolder} annotation.
 * The name of the variable must match the resource location defined when registering the block.
 */
@ObjectHolder(MineStation13.MOD_ID)
public class ModBlocks {
    public static final Block COFFEE_MACHINE = null;
}
