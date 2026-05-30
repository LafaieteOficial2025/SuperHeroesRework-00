package com.fiskheroesrebirth.mod;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.common.MinecraftForge;
import org.slf4j.Logger;
import com.mojang.logging.LogUtils;

import com.fiskheroesrebirth.mod.fsk.FskEngine;
import com.fiskheroesrebirth.mod.loader.HeroLoader;
import com.fiskheroesrebirth.mod.loader.PowerLoader;
import com.fiskheroesrebirth.mod.loader.SuitLoader;
import com.fiskheroesrebirth.mod.loader.AbilityLoader;
import com.fiskheroesrebirth.mod.events.EventHandler;

@Mod(FiskHeroesRebirth.MOD_ID)
public class FiskHeroesRebirth {

    public static final String MOD_ID = "fiskheroesrebirth";
    public static final Logger LOGGER = LogUtils.getLogger();

    public FiskHeroesRebirth() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        modBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(new EventHandler());

        LOGGER.info("Fisk Heroes Rebirth carregando...");
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            LOGGER.info("Carregando dados...");

            HeroLoader.loadAll();
            PowerLoader.loadAll();
            SuitLoader.loadAll();
            AbilityLoader.loadAll();
            FskEngine.loadAll();

            LOGGER.info("Setup completo!");
        });
    }
}
