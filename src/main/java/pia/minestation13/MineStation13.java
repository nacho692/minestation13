package pia.minestation13;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.ModDimension;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.RegisterDimensionsEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("minestation13")
public class MineStation13 {
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();
    public static final ResourceLocation DIMENSION_TYPE_SP = new ResourceLocation("minestation", "space_dimension");

    public MineStation13() {
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onPlayerClone(PlayerEvent.Clone event) {
        if(event.getPlayer() instanceof ServerPlayerEntity) {
            event.getPlayer().setSpawnDimenion(DimensionType.byName(DIMENSION_TYPE_SP));
        }
    }

    @SubscribeEvent
    public void onEntitySpawnFirst(EntityJoinWorldEvent event) {
        Entity e = event.getEntity();
        if (!e.world.isRemote()) {
            if (e instanceof ServerPlayerEntity) {
                ((ServerPlayerEntity) e).setSpawnDimenion(DimensionType.byName(DIMENSION_TYPE_SP));
            }
        }
    }

    @SubscribeEvent
    public void registerDimensions(final RegisterDimensionsEvent event) {
        if (DimensionType.byName(DIMENSION_TYPE_SP) == null) {
            DimensionManager.registerDimension(DIMENSION_TYPE_SP, SpaceDimensionRegistrar.SPACE_DIMENSION, null, true);
        }
    }

    public static void teleportPlayer(ServerPlayerEntity player, DimensionType destinationType, BlockPos destinationPos)
    {
        ServerWorld nextWorld = player.getServer().getWorld(destinationType);
        nextWorld.getChunk(destinationPos);	// make sure the chunk is loaded
        player.teleport(nextWorld, destinationPos.getX(), destinationPos.getY(), destinationPos.getZ(), player.rotationYaw, player.rotationPitch);
    }

}
