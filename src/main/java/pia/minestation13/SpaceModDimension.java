package pia.minestation13;

import net.minecraft.world.World;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.ModDimension;

import java.util.function.BiFunction;

/**
 * SpaceModDimension generates SpaceDimensions.
 * ModDimensions are a Forge wrapper around the vanilla dimension framework; if you intend to have a variety of
 * dimensions with the same worldgen behaviour but different variations or pseudoseeds, you can use one ModDimension
 * to dole out Dimensions.
 */
public class SpaceModDimension extends ModDimension {
    @Override
    public BiFunction<World, DimensionType, ? extends Dimension> getFactory() {
        return SpaceDimension::new;
    }
}
