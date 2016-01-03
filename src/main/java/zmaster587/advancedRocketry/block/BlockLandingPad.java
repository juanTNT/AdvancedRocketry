package zmaster587.advancedRocketry.block;

import zmaster587.advancedRocketry.api.Configuration;
import zmaster587.advancedRocketry.api.dimension.DimensionManager;
import zmaster587.advancedRocketry.api.stations.SpaceObject;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BlockLandingPad extends Block {

	public BlockLandingPad(Material mat) {
		super(mat);
	}

	
	@Override
	public void onBlockPlacedBy(World world, int x,
			int y, int z, EntityLivingBase player,
			ItemStack items) {
		super.onBlockPlacedBy(world, x, y, z,
				player, items);
		
		if(!world.isRemote && world.provider.dimensionId == Configuration.spaceDimId) {
			SpaceObject spaceObj = DimensionManager.getSpaceManager().getSpaceStationFromBlockCoords(x, z);
			spaceObj.addLandingPad(x, z);
		}
	}
	
	@Override
	public void onBlockPreDestroy(World world, int x,
			int y, int z, int oldMeta) {
		super.onBlockPreDestroy(world, x, y, z,
				oldMeta);
		
		if(world.provider.dimensionId == Configuration.spaceDimId) {
			SpaceObject spaceObj = DimensionManager.getSpaceManager().getSpaceStationFromBlockCoords(x, z);
			spaceObj.removeLandingPad(x, z);
		}
	}
}
