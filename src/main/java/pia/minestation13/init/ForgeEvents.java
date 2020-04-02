package pia.minestation13.init;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.world.RegisterDimensionsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import pia.minestation13.MineStation13;

/**
 * ForgeEvents handles subscription to FORGE events.
 * FORGE events are mostly hooks on minecraft. The argument type on the subscriptions defines which event we are
 * subscribed to, so even if the event is not used, the argument is required.
 */
@Mod.EventBusSubscriber(modid = MineStation13.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeEvents {
    /**
     * DIMENSION_TYPE_SP is the resource locator for the custom space dimension.
     */
    public static final ResourceLocation DIMENSION_TYPE_SP = new ResourceLocation(MineStation13.MOD_ID, "space_dimension");

    /**
     * onEntitySpawnFirst is fired whenever a new entity appears on a ServerWorld.
     * It can either be a player entity or a monster. This is the event used to move the player to the space dimension.
     * @param event The event fired when the entity spawns.
     */
    @SubscribeEvent
    public static void onEntitySpawnFirst(final EntityJoinWorldEvent event) {
        Entity e = event.getEntity();
        if (!e.world.isRemote()) {
            if (e instanceof ServerPlayerEntity) {
                ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity) e;
                DimensionType dim = DimensionType.byName(DIMENSION_TYPE_SP);
                if (!serverPlayerEntity.getSpawnDimension().equals(dim)) {
                    serverPlayerEntity.setSpawnDimenion(dim);
                    teleportPlayer(serverPlayerEntity, dim);
                }
            }
        }
    }

    /**
     * registerDimensions registers the space dimension on the server.
     * @param event The event fired when the server registers dimensions.
     */
    @SubscribeEvent
    public static void registerDimensions(final RegisterDimensionsEvent event) {
        if (DimensionType.byName(DIMENSION_TYPE_SP) == null) {
            DimensionManager.registerDimension(DIMENSION_TYPE_SP, ModSpaceDimension.SPACE_DIMENSION, null, true);
        }
    }


    private static void teleportPlayer(ServerPlayerEntity player, DimensionType destinationType) {
        ServerWorld nextWorld = player.getServer().getWorld(destinationType);
        BlockPos spawnPoint = nextWorld.getSpawnPoint();
        nextWorld.getChunk(spawnPoint);	// make sure the chunk is loaded
        player.teleport(nextWorld, spawnPoint.getX(), spawnPoint.getY(), spawnPoint.getZ(), player.rotationYaw, player.rotationPitch);
    }
}
