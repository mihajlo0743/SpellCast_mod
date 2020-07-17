package com.mihajlo0743.spellcast;

import com.mihajlo0743.spellcast.blocks.ModBlocks;
import com.mihajlo0743.spellcast.blocks.Tech_block;
import com.mihajlo0743.spellcast.items.DashRune;
import com.mihajlo0743.spellcast.items.Rune;
import com.mihajlo0743.spellcast.setup.ClientProxy;
import com.mihajlo0743.spellcast.setup.IProxy;
import com.mihajlo0743.spellcast.setup.ModSetup;
import com.mihajlo0743.spellcast.setup.ServerProxy;
import net.minecraft.block.Block;
import net.minecraft.entity.MobEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Rarity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import top.theillusivec4.curios.api.imc.CurioIMCMessage;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("spellcast")
public class Spellcast
{
    public static IProxy proxy = DistExecutor.runForDist(()->()->new ClientProxy(), ()->()->new ServerProxy());
    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();
    public static ModSetup setup = new ModSetup();
    public static final String MODID = "spellcast";

    public Spellcast() {
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the enqueueIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        // Register the doClientStuff method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        setup.init();
        proxy.init();
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // do something that can only be done on the client
        LOGGER.info("Got game settings {}", event.getMinecraftSupplier().get().gameSettings);

    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        InterModComms.sendTo("curios", "register_type", ()-> new CurioIMCMessage("spellrune").setEnabled(true).setSize(1).setHidden(false));
        InterModComms.sendTo("curios", "register_type", ()-> new CurioIMCMessage("spellbelt").setEnabled(true).setSize(1).setHidden(false));
        InterModComms.sendTo("curios", "register_type", ()-> new CurioIMCMessage("spellamulet").setEnabled(true).setSize(1).setHidden(false));
        InterModComms.sendTo("curios", "register_type", ()-> new CurioIMCMessage("spellboots").setEnabled(true).setSize(1).setHidden(false));
        // some example code to dispatch IMC to another mod
        InterModComms.sendTo("spellcast", "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
    }

    private void processIMC(final InterModProcessEvent event)
    {
        // some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m->m.getMessageSupplier().get()).
                collect(Collectors.toList()));
    }
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> event) {
            event.getRegistry().register(new Tech_block());
        }
        @SubscribeEvent
        public static void onItemsRegistry(final RegistryEvent.Register<Item> event) {
            Item.Properties props = new Item.Properties().group(setup.itemGroup);
            //event.getRegistry().register(new BlockItem(ModBlocks.TECH_BLOCK, props.rarity(Rarity.EPIC)).setRegistryName("tech_block"));
            event.getRegistry().registerAll(
                    new DashRune(),
                    new Rune(Rarity.COMMON, "placeholder_rune"),
                    new BlockItem(ModBlocks.TECH_BLOCK, props.rarity(Rarity.EPIC)).setRegistryName("tech_block")
            );
            LOGGER.debug(MobEntity.getSlotForItemStack(new Rune(Rarity.COMMON, "placeholder_rune").getDefaultInstance()));
        }

    }
}
