package pia.minestation13;

import net.minecraftforge.fml.common.Mod;

/**
 * MineStation13 is the entry point for the Mod.
 * This class is loaded before event subscriptions are registered and blocks, items, dimensions are loaded.
 */
@Mod(MineStation13.MOD_ID)
public class MineStation13 {

    /**
     * MOD_ID defines the identifier for the mod.
     * It should match the value set on mods.toml.
     */
    public static final String MOD_ID = "minestation13";

    /**
     * MineStation13 runs initialization code.
     * It fires before any other method or annotation.
     */
    public MineStation13() {
    }
}
