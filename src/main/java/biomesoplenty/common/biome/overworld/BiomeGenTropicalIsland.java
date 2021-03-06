/*******************************************************************************
 * Copyright 2015-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.biome.overworld;

import biomesoplenty.api.biome.BOPBiomes;
import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.block.BlockQueries;
import biomesoplenty.api.enums.BOPFlowers;
import biomesoplenty.api.enums.BOPFoliage;
import biomesoplenty.api.enums.BOPTrees;
import biomesoplenty.api.enums.BOPWoods;
import biomesoplenty.api.generation.GeneratorStage;
import biomesoplenty.common.block.BlockBOPFlatPlant;
import biomesoplenty.common.block.BlockBOPLeaves;
import biomesoplenty.common.world.generator.GeneratorFlora;
import biomesoplenty.common.world.generator.GeneratorGrass;
import biomesoplenty.common.world.generator.GeneratorWaterside;
import biomesoplenty.common.world.generator.GeneratorWeighted;
import biomesoplenty.common.world.generator.tree.GeneratorPalmTree;
import biomesoplenty.common.world.generator.tree.GeneratorTwigletTree;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockOldLeaf;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.entity.passive.EntityParrot;
import net.minecraft.init.Blocks;
 
public class BiomeGenTropicalIsland extends BOPOverworldBiome
{
    public BiomeGenTropicalIsland()
    {
        super("tropical_island", new PropsBuilder("Tropical Island").withTemperature(0.95F).withRainfall(1.0F).withGuiColour(2211330));

        // terrain
        this.terrainSettings.avgHeight(64).heightVariation(5, 40).octaves(0, 1, 2, 2, 1, 0).sidewaysNoise(0.2D);
    
        if (BOPBiomes.white_beach.isPresent())
        {
            this.beachBiomeLocation = ((BOPOverworldBiome)BOPBiomes.white_beach.get()).getResourceLocation();
        }
        
        this.canSpawnInBiome = true;
        this.canGenerateVillages = false;
        this.canGenerateRivers = false;
        
        this.skyColor = 507391;
        this.fogColor = 0xB5F8FF;
        
        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableCreatureList.add(new SpawnListEntry(EntityParrot.class, 40, 1, 2));
        
        clearWeights();
        
        // sand
        this.addGenerator("sand", GeneratorStage.SAND_PASS2, (new GeneratorWaterside.Builder()).amountPerChunk(10).maxRadius(7).with(BOPBlocks.white_sand.getDefaultState()).create());
        
        // trees
        GeneratorWeighted treeGenerator = new GeneratorWeighted(25.0F);
        this.addGenerator("trees", GeneratorStage.TREE, treeGenerator);
        treeGenerator.add("palm", 4, (new GeneratorPalmTree.Builder()).log(BOPWoods.PALM).leaves(BlockBOPLeaves.paging.getVariantState(BOPTrees.PALM).withProperty(BlockOldLeaf.DECAYABLE, Boolean.valueOf(false))).create());
        treeGenerator.add("jungle_twiglet", 2, (new GeneratorTwigletTree.Builder()).minHeight(2).maxHeight(2).log(BlockPlanks.EnumType.JUNGLE).leaves(BlockPlanks.EnumType.JUNGLE).trunkFruit(Blocks.COCOA.getDefaultState()).create());
        
        // grasses
        GeneratorWeighted grassGenerator = new GeneratorWeighted(3.5F);
        this.addGenerator("grass", GeneratorStage.GRASS, grassGenerator);
        grassGenerator.add("shortgrass", 1, (new GeneratorGrass.Builder()).with(BOPFoliage.SHORTGRASS).create());
        grassGenerator.add("tallgrass", 2, (new GeneratorGrass.Builder()).with(BlockTallGrass.EnumType.GRASS).create());
        
        //other plants
        this.addGenerator("melons", GeneratorStage.FLOWERS, (new GeneratorFlora.Builder()).amountPerChunk(0.015625F).placeOn(this.topBlock).with(Blocks.MELON_BLOCK.getDefaultState()).create());
        
        // flowers
        GeneratorWeighted flowerGenerator = new GeneratorWeighted(2.0F);
        this.addGenerator("flowers", GeneratorStage.FLOWERS, flowerGenerator);
        flowerGenerator.add("hibiscus", 6, (new GeneratorFlora.Builder().with(BOPFlowers.PINK_HIBISCUS).create()));
        flowerGenerator.add("blue_hydrangeas", 2, (new GeneratorFlora.Builder().with(BOPFlowers.BLUE_HYDRANGEA).create()));
        flowerGenerator.add("dandelion", 1, (new GeneratorFlora.Builder().with(BlockFlower.EnumFlowerType.DANDELION).create()));
        flowerGenerator.add("poppy", 1, (new GeneratorFlora.Builder().with(BlockFlower.EnumFlowerType.POPPY).create()));
    }
}
