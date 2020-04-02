package pia.minestation13.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraftforge.registries.ObjectHolder;
import pia.minestation13.MineStation13;

/**
 * CoffeeMachineBlock represents the coffee dispenser block.
 * It is an horizontal single block structure.
 */
@ObjectHolder(MineStation13.MOD_ID)
public class CoffeeMachineBlock extends HorizontalBlock {

    public CoffeeMachineBlock(Properties properties) {
        super(properties);
        this.setDefaultState(this.getDefaultState().with(HORIZONTAL_FACING, Direction.NORTH));
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(HORIZONTAL_FACING, context.getPlacementHorizontalFacing().getOpposite());
    }

    @Override
    protected void fillStateContainer(final StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
        builder.add(HORIZONTAL_FACING);
    }
}
