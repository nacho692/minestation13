package pia.minestation13.dimension;


import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.provider.BiomeProviderType;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.ChunkGeneratorType;
import net.minecraft.world.gen.EndGenerationSettings;

import javax.annotation.Nullable;
import java.util.Random;

/**
 * SpaceDimension defines a dimension representing space.
 * It's currently really basic and it's mostly a proof of concept before a better implementation.
 */
public class SpaceDimension extends Dimension {

    public static final BlockPos SPAWN = new BlockPos(100, 50, 0);;

    public SpaceDimension(World world, DimensionType dimensionType) {
        super(world, dimensionType, 1.0F);
    }

    /**
     * createChunkGenerator defines the generation for this dimension.
     * @return A ChunkGenerator
     */
    @Override
    public ChunkGenerator<?> createChunkGenerator() {
        EndGenerationSettings spaceGenSettings = ChunkGeneratorType.FLOATING_ISLANDS.createSettings();
        spaceGenSettings.setDefaultBlock(Blocks.COBBLESTONE.getDefaultState());
        spaceGenSettings.setDefaultFluid(Blocks.WATER.getDefaultState());
        spaceGenSettings.setSpawnPos(SPAWN);
        return ChunkGeneratorType.FLOATING_ISLANDS
                .create(this.world,
                        BiomeProviderType.FIXED
                                .create(BiomeProviderType.FIXED.func_226840_a_(this.world.getWorldInfo())
                                        .setBiome(Biomes.BADLANDS)), spaceGenSettings);

    }

    @Nullable
    @Override
    public BlockPos findSpawn(ChunkPos chunkPosIn, boolean checkValid) {
        final Random random = new Random(this.world.getSeed());
        final BlockPos blockpos = new BlockPos(chunkPosIn.getXStart() + random.nextInt(15), 0,
                chunkPosIn.getZEnd() + random.nextInt(15));
        return this.world.getGroundAboveSeaLevel(blockpos).getMaterial().blocksMovement() ? blockpos : null;
    }

    @Nullable
    @Override
    public BlockPos findSpawn(int posX, int posZ, boolean checkValid) {
        return this.findSpawn(new ChunkPos(posX >> 4, posZ >> 4), checkValid);
    }

    @Override
    public float calculateCelestialAngle(long worldTime, float partialTicks) {
        return 0;
    }

    @Override
    public boolean isSurfaceWorld() {
        return false;
    }

    @Override
    public Vec3d getFogColor(float celestialAngle, float partialTicks) {
        return new Vec3d(0, 0, 0);
    }

    @Override
    public boolean canRespawnHere() {
        return true;
    }

    @Override
    public boolean doesXZShowFog(int x, int z) {
        return false;
    }

    @Override
    public BlockPos getSpawnCoordinate() {
        return SPAWN;
    }
}
