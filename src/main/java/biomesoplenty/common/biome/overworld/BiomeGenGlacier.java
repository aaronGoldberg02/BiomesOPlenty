/*******************************************************************************
 * Copyright 2015, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.biome.overworld;

import biomesoplenty.api.block.BOPBlocks;

public class BiomeGenGlacier extends BiomeGenArctic
{    
    public BiomeGenGlacier()
    {
        super();
        
        // clear weights - this biome shouldn't spawn except as arctic sub-biome
        clearWeights();
        
        // terrain - higher than arctic
        this.bopMinHeight = 80;
        this.bopMaxHeight = 95;
        this.setOctaveWeights(1, 1, 3, 1, 1, 0);
        this.avgDirtDepth = 16;
        
        this.setColor(0xB0BBD9);
        
        this.topBlock = BOPBlocks.hard_ice.getDefaultState();
        this.fillerBlock = BOPBlocks.hard_ice.getDefaultState();

    }
}
