package com.stek101.projectzulu.common.mobs.entitydefaults;

import java.util.HashSet;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.config.Configuration;

import com.stek101.projectzulu.common.api.CustomMobData;
import com.stek101.projectzulu.common.core.ConfigHelper;
import com.stek101.projectzulu.common.core.DefaultProps;
import com.stek101.projectzulu.common.core.entitydeclaration.EntityProperties;
import com.stek101.projectzulu.common.core.entitydeclaration.SpawnableDeclaration;
import com.stek101.projectzulu.common.mobs.entity.EntityPZBat;
import com.stek101.projectzulu.common.mobs.models.ModelPZBat;
import com.stek101.projectzulu.common.mobs.renders.RenderGenericLiving;
import com.stek101.projectzulu.common.mobs.renders.RenderWrapper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PZBatDeclaration extends SpawnableDeclaration {

    public PZBatDeclaration() {
        super("PZBat", 65, EntityPZBat.class, EnumCreatureType.monster);
        setSpawnProperties(10, 5, 4, 4);
        setRegistrationProperties(128, 3, true);
        setDropAmount(0, 1);

        eggColor1 = (16 << 16) + (16 << 8) + 255;
        eggColor2 = (161 << 16) + (161 << 8) + 255;
    }

    @Override
    public void outputDataToList(Configuration config, CustomMobData customMobData) {
        ConfigHelper.configDropToMobData(config, "MOB CONTROLS." + mobName, customMobData, Items.leather, 0, 8);
        customMobData.entityProperties = new EntityProperties(6f, 1.0f, 0.3f, 100f, 0.0f, 32.0f, 30.0f, 10D).createFromConfig(config, mobName);
        super.outputDataToList(config, customMobData);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public RenderWrapper getEntityrender(Class<? extends EntityLivingBase> entityClass) {
        return new RenderGenericLiving(new ModelPZBat(), 0.5f, new ResourceLocation(DefaultProps.mobKey,
                "textures/pzbat.png"));
    }

    @Override
    public HashSet<String> getDefaultBiomesToSpawn() {
        HashSet<String> defaultBiomesToSpawn = new HashSet<String>();
        defaultBiomesToSpawn.add(BiomeGenBase.forest.biomeName);
        defaultBiomesToSpawn.add(BiomeGenBase.forestHills.biomeName);
        defaultBiomesToSpawn.add("Autumn Woods");
        defaultBiomesToSpawn.add("Birch Forest");
        defaultBiomesToSpawn.add("Forested Hills");
        defaultBiomesToSpawn.add("Forested Island");
        defaultBiomesToSpawn.add("Green Hills");
        defaultBiomesToSpawn.add("Redwood Forest");
        defaultBiomesToSpawn.add("Lush Redwoods");
        defaultBiomesToSpawn.add("Temperate Rainforest");
        defaultBiomesToSpawn.add("Woodlands");

        HashSet<String> nonFrozenForest = new HashSet<String>();
        nonFrozenForest.addAll(typeToArray(Type.FOREST));
        nonFrozenForest.addAll(typeToArray(Type.PLAINS));
        nonFrozenForest.removeAll(typeToArray(Type.FROZEN));
        defaultBiomesToSpawn.addAll(nonFrozenForest);

        return defaultBiomesToSpawn;
    }
}