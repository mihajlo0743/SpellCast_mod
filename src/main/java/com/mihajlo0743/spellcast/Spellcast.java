package com.mihajlo0743.spellcast;

import com.mihajlo0743.spellcast.blocks.ModBlocks;
import com.mihajlo0743.spellcast.blocks.Tech_block;
import com.mihajlo0743.spellcast.items.Amulet;
import com.mihajlo0743.spellcast.items.Belt;
import com.mihajlo0743.spellcast.items.Boots;
import com.mihajlo0743.spellcast.items.gauntlets.AirGauntlet;
import com.mihajlo0743.spellcast.items.gauntlets.FireGntl;
import com.mihajlo0743.spellcast.items.gauntlets.LightningGntl;
import com.mihajlo0743.spellcast.items.runes.DashRune;
import com.mihajlo0743.spellcast.items.runes.InvisRune;
import com.mihajlo0743.spellcast.items.runes.PlaceholderRune;
import com.mihajlo0743.spellcast.items.runes.SpringstepRune;
import com.mihajlo0743.spellcast.setup.*;
import com.mihajlo0743.spellcast.tiles.VaultTile;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Rarity;
import net.minecraft.nbt.IntNBT;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
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

import javax.security.auth.login.Configuration;
import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("spellcast")
public class Spellcast
{
    public static IProxy proxy = DistExecutor.runForDist(()->()->new ClientProxy(), ()->()->new ServerProxy());
    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();
    public static ModSetup setup = new ModSetup();
    public static Configuration config;
    public static final String MODID = "spellcast";
    public static final String VERSION = "0.2.32.25";

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
        Reg.init();
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
        event.getServer().func_71218_a(DimensionType.OVERWORLD).getGameRules().write().put("test", new IntNBT(0));
    }
    @SubscribeEvent
    public void OnStartedS(EntityJoinWorldEvent event){
        //setup.stats = Minecraft.getInstance().player.getCapability(StatsProvider.STATS_CAP).orElse(StatsProvider.STATS_CAP.getDefaultInstance());
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
        public static void onTileReg(final RegistryEvent.Register<TileEntityType<?>> event){
            event.getRegistry().register(TileEntityType.Builder.create(VaultTile::new, ModBlocks.TECH_BLOCK).build(null).setRegistryName(Spellcast.MODID, "vault_tile"));
        }
        @SubscribeEvent
        public static void onItemsRegistry(final RegistryEvent.Register<Item> event) {
            Item.Properties props = new Item.Properties().group(setup.itemGroup);
            //event.getRegistry().register(new BlockItem(ModBlocks.TECH_BLOCK, props.rarity(Rarity.EPIC)).setRegistryName("tech_block"));
            event.getRegistry().registerAll(
                    new DashRune(),
                    new PlaceholderRune(),
                    new InvisRune(),
                    new SpringstepRune(),
                    new BlockItem(ModBlocks.TECH_BLOCK, props.rarity(Rarity.EPIC)).setRegistryName("tech_block"),
                    new Amulet(Rarity.COMMON, 200, "common_amulet"),
                    new Amulet(Rarity.UNCOMMON, 300, "uncommon_amulet"),
                    new Amulet(Rarity.RARE, 500, "rare_amulet"),
                    new Amulet(Rarity.EPIC, 900, "epic_amulet"),
                    new Belt(Rarity.COMMON, 200, "common_belt"),
                    new Belt(Rarity.UNCOMMON, 300, "uncommon_belt"),
                    new Belt(Rarity.RARE, 500, "rare_belt"),
                    new Belt(Rarity.EPIC, 900, "epic_belt"),
                    new Boots(Rarity.COMMON, 200, "common_boots"),
                    new Boots(Rarity.UNCOMMON, 300, "uncommon_boots"),
                    new Boots(Rarity.RARE, 500, "rare_boots"),
                    new Boots(Rarity.EPIC, 900, "epic_boots"),
                    new FireGntl(),
                    new LightningGntl(),
                    new AirGauntlet()
            );
        }

    }
}
