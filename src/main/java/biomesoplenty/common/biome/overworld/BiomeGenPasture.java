package biomesoplenty.common.biome.overworld;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.enums.BOPFoliage;
import biomesoplenty.api.enums.BOPPlants;
import biomesoplenty.api.generation.GeneratorStage;
import biomesoplenty.common.block.BlockBOPDirt;
import biomesoplenty.common.block.BlockBOPGrass;
import biomesoplenty.common.world.generator.GeneratorFlora;
import biomesoplenty.common.world.generator.GeneratorGrass;
import biomesoplenty.common.world.generator.GeneratorWeighted;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.util.math.BlockPos;

public class BiomeGenPasture extends BOPOverworldBiome
{
    public BiomeGenPasture()
    {
        super("pasture", new PropsBuilder("Pasture").withGuiColour(0xC8E580).withTemperature(0.8F).withRainfall(0.3F));

        // terrain
        this.terrainSettings.avgHeight(64).heightVariation(4, 6);
        
        this.topBlock = BOPBlocks.grass.getDefaultState().withProperty(BlockBOPGrass.VARIANT, BlockBOPGrass.BOPGrassType.SILTY);
        this.fillerBlock = BOPBlocks.dirt.getDefaultState().withProperty(BlockBOPDirt.VARIANT, BlockBOPDirt.BOPDirtType.SILTY);
        
        this.canGenerateVillages = false;
        
        clearWeights();

        // grasses
        GeneratorWeighted grassGenerator = new GeneratorWeighted(0.5F);
        this.addGenerator("grass", GeneratorStage.GRASS, grassGenerator);
        grassGenerator.add("shortgrass", 2, (new GeneratorGrass.Builder()).with(BOPFoliage.SHORTGRASS).create());
        grassGenerator.add("tallgrass", 6, (new GeneratorGrass.Builder()).with(BlockTallGrass.EnumType.GRASS).create());

        this.addGenerator("barley", GeneratorStage.GRASS,(new GeneratorFlora.Builder()).amountPerChunk(45.0F).with(BOPFoliage.BARLEY).create());
    }
    
    @Override
    public int getGrassColorAtPos(BlockPos pos)
    {
        return getModdedBiomeGrassColor(13165952);
    }

    @Override
    public int getFoliageColorAtPos(BlockPos pos)
    {
        return getModdedBiomeFoliageColor(11395195);
    }
}
