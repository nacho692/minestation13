package pia.minestation13.init;

import net.minecraft.world.World;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.ModDimension;
import net.minecraftforge.registries.ObjectHolder;
import pia.minestation13.dimension.SpaceDimension;

import java.util.function.BiFunction;

/**
 * SpaceModDimension generates SpaceDimensions.
 * ModDimensions are a Forge wrapper around the vanilla dimension framework; if you intend to have a variety of
 * dimensions with the same worldgen behaviour but different variations or pseudoseeds, you can use one ModDimension
 * to dole out Dimensions.
 */
public class ModSpaceDimension extends ModDimension {
    /**
     * SPACE_DIMENSION contains the automatically injected space_dimension.
     * This object holder is automatically injected with the correct space_dimension once registered.
     */
    @ObjectHolder("minestation13:space_dimension")
    public static final ModDimension SPACE_DIMENSION = null;

    @Override
    public BiFunction<World, DimensionType, ? extends Dimension> getFactory() {
        return SpaceDimension::new;
    }
}
