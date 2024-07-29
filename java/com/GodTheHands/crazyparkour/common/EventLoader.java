package com.GodTheHands.crazyparkour.common;

import com.GodTheHands.crazyparkour.CrazyParkour;
import com.GodTheHands.crazyparkour.PBsystem.OffsetShower;
import com.GodTheHands.crazyparkour.PBsystem.TargetBlock;
import com.GodTheHands.crazyparkour.block.BlockLoader;
import com.GodTheHands.crazyparkour.block.BlockTeleporter.BlockTeleporter;
import com.GodTheHands.crazyparkour.block.BlockTeleporter.TileEntityBlockTeleporter;
import com.GodTheHands.crazyparkour.capability.*;
import com.GodTheHands.crazyparkour.client.KeyLoader;
import com.GodTheHands.crazyparkour.hud.ClickGui;
import com.GodTheHands.crazyparkour.macro.MacroConfigSaver;
import com.GodTheHands.crazyparkour.macro.MacroGuiScreen;
import com.GodTheHands.crazyparkour.macro.MacroRunner;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEndPortalFrame;
import net.minecraft.block.BlockSkull;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraft.world.WorldSettings;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fluids.*;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.EventBus;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EventLoader {
    public EventLoader()
    {
        MinecraftForge.EVENT_BUS.register(this);
        EventLoader.EVENT_BUS.register(this);
    }

    public static final EventBus EVENT_BUS = new EventBus();

    @Cancelable
    public static class PlayerCPEvent extends net.minecraftforge.event.entity.player.PlayerEvent {
        public PlayerCPEvent (EntityPlayer player) {
          super(player);
          IPositionHistory position = player.getCapability(CapabilityLoader.positionHistory, null);
          Vec3 pos = new Vec3(player.posX, player.posY, player.posZ);
          position.setPosition(pos);
          position.setRotationYaw(player.rotationYaw);
          position.setRotationPitch(player.rotationPitch);
          player.worldObj.playSoundAtEntity(player, "crazyparkour:checkpoint.test", 1.0F, 1.0F);
          BlockTeleporter.collPos = new BlockPos(0, 0, 0);
        }
    }

    @Cancelable
    public static class PlayerChangeModeEvent extends net.minecraftforge.event.entity.player.PlayerEvent {
        public PlayerChangeModeEvent (EntityPlayer player) {
            super(player);
            if (player.capabilities.isCreativeMode) {
                player.setGameType(WorldSettings.GameType.ADVENTURE);
            }
            else {
                player.setGameType(WorldSettings.GameType.CREATIVE);
            }

            player.worldObj.playSoundAtEntity(player, "crazyparkour:checkpoint.test", 1.0F, 1.0F);
        }
    }

    @Cancelable
    public static class PlayerTPEvent extends net.minecraftforge.event.entity.player.PlayerEvent {
        public PlayerTPEvent (EntityPlayer player) {
            super(player);
            IPositionHistory position = player.getCapability(CapabilityLoader.positionHistory, null);
            player.setLocationAndAngles(position.getPosition().xCoord, position.getPosition().yCoord, position.getPosition().zCoord, position.getRotationYaw(), position.getRotationPitch());
            //Cancel movement
            player.motionX = player.motionY = player.motionZ = 0;
            player.fallDistance = 0;
            player.worldObj.playSoundAtEntity(player, "crazyparkour:teleporter.test", 1.0F, 1.0F);
        }
    }

    @SubscribeEvent //Do not put anything about checkpoint here because we have done that
    public void onPlayerCPEvent(PlayerCPEvent event) {}

    @SubscribeEvent //Do not put anything about teleport here because we have done that
    public void onPlayerTPEvent(PlayerTPEvent event) {}

    @SubscribeEvent
    public void onPlayerCMEvent(PlayerChangeModeEvent event) {}

    @SubscribeEvent
    public void onAttachCapabilitiesEntity(AttachCapabilitiesEvent.Entity event)
    {
        if (event.getEntity() instanceof EntityPlayer)
        {
            ICapabilitySerializable<NBTTagCompound> provider = new CapabilityPositionHistory.ProviderPlayer();
            event.addCapability(new ResourceLocation(CrazyParkour.MODID + ":" + "position_history"), provider);
        }
    }

    @SubscribeEvent
    public void onAttachCapabilitiesEntity(AttachCapabilitiesEvent.TileEntity event) {
        if (event.getTileEntity() instanceof TileEntityBlockTeleporter) {
            ICapabilitySerializable<NBTTagCompound> provider = new CapabilityPositionStorage.ProviderPlayer();
            event.addCapability(new ResourceLocation(CrazyParkour.MODID + ":" + "position_history"), provider);
        }
    }

    @SubscribeEvent
    public void onPlayerClone(net.minecraftforge.event.entity.player.PlayerEvent.Clone event)
    {
        Capability<IPositionHistory> capability = CapabilityLoader.positionHistory;
        Capability.IStorage<IPositionHistory> storage = capability.getStorage();

        if (event.original.hasCapability(capability, null) && event.entityPlayer.hasCapability(capability, null))
        {
            NBTBase nbt = storage.writeNBT(capability, event.original.getCapability(capability, null), null);
            storage.readNBT(capability, event.entityPlayer.getCapability(capability, null), null, nbt);
        }
    }

    @SubscribeEvent
    public void noHurt(LivingHurtEvent event) {
        event.setCanceled(event.entity instanceof EntityPlayer && ConfigLoader.god_mode);
    }

    @SubscribeEvent
    public void noAttack(LivingAttackEvent event) {
        event.setCanceled(event.entity instanceof EntityPlayer && ConfigLoader.god_mode);
    }

    @SubscribeEvent
    public void noDeath(LivingAttackEvent event) {
        event.setCanceled(event.entity instanceof EntityPlayer && ConfigLoader.god_mode);
    }

    @SubscribeEvent
    public void noBreak(PlayerEvent.BreakSpeed event) {
        event.setCanceled(event.entity instanceof EntityPlayer && ConfigLoader.god_mode && !((EntityPlayer) event.entity).capabilities.isCreativeMode);
    }

    @SubscribeEvent
    public void noAttackTo(AttackEntityEvent event) {
        event.setCanceled(event.entity instanceof EntityPlayer && ConfigLoader.god_mode && !((EntityPlayer) event.entity).capabilities.isCreativeMode);
    }

    @SubscribeEvent
    public void onPlayerRightClick(PlayerInteractEvent event) {
        if (!MinecraftServer.getServer().isSinglePlayer()) {
            return;
        }

        IBlockState thisBlockState;
        Block thisBlock;

        try {
            thisBlockState = event.world.getBlockState(event.pos);
            thisBlock = thisBlockState.getBlock();
        }
        catch (Exception e) {
            return;
        };

        if (event.entityPlayer.capabilities.isCreativeMode && event.entityPlayer.inventory.getCurrentItem() == null && event.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK) {
            if (thisBlock == Blocks.stone_slab)
            {
                event.world.setBlockState(event.pos, thisBlockState.withProperty(BlockSlab.HALF, (thisBlockState.getValue(BlockSlab.HALF) == BlockSlab.EnumBlockHalf.TOP ? BlockSlab.EnumBlockHalf.BOTTOM : BlockSlab.EnumBlockHalf.TOP)));
            }
            else if (thisBlock == Blocks.end_portal_frame)
            {
                if (!event.entityPlayer.isSneaking())
                {
                    event.world.setBlockState(event.pos, thisBlockState.withProperty(BlockEndPortalFrame.FACING, EnumFacing.getFront((thisBlockState.getValue(BlockEndPortalFrame.FACING).getIndex() + 1) % 4 + 2)));
                }
                else
                    {
                        event.world.setBlockState(event.pos, thisBlockState.withProperty(BlockEndPortalFrame.EYE, !(thisBlockState.getValue(BlockEndPortalFrame.EYE))));
                    }
            }
            else if (thisBlock == Blocks.skull)
            {
                if (!event.entityPlayer.isSneaking())
                {
                    event.world.setBlockState(event.pos, thisBlockState.withProperty(BlockSkull.FACING, EnumFacing.getFront((thisBlockState.getValue(BlockSkull.FACING).getIndex() == 5 ? 1 : (thisBlockState.getValue(BlockSkull.FACING).getIndex() + 1)))));
                }
                else
                {
                    ((TileEntitySkull)event.world.getTileEntity(event.pos)).setPlayerProfile(((TileEntitySkull)event.world.getTileEntity(event.pos)).getPlayerProfile());
                }
            }
            else if (event.entityPlayer.isSneaking()) {
                if (thisBlock == BlockLoader.lockedStair) {
                    event.world.setBlockState(event.pos, BlockLoader.lockedStair1.getStateFromMeta(thisBlock.getMetaFromState(thisBlockState)));
                }
                else if (thisBlock == BlockLoader.lockedStair1) {
                    event.world.setBlockState(event.pos, BlockLoader.lockedStair2.getStateFromMeta(thisBlock.getMetaFromState(thisBlockState)));
                }
                else if (thisBlock == BlockLoader.lockedStair2) {
                    event.world.setBlockState(event.pos, BlockLoader.lockedStair.getStateFromMeta(thisBlock.getMetaFromState(thisBlockState)));
                }
            }
        }
    }

    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.modID.equals(CrazyParkour.MODID)) {
            ConfigLoader.load();
        }
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        Property prop;

        if (KeyLoader.enableGodMode.isPressed()) {
            if (MinecraftServer.getServer().isSinglePlayer()) {
                ConfigLoader.god_mode = !ConfigLoader.god_mode;

                prop = ConfigLoader.config.get("gamesettings", "god_mode", false,
                            "Determine whether the player will hurt. ");
                prop.set(ConfigLoader.god_mode);

                ConfigLoader.config.save();
            }
        }
        else if (KeyLoader.toggleSprint.isPressed()) {
            ConfigLoader.toggle_sprint = !ConfigLoader.toggle_sprint;

            prop = ConfigLoader.config.get("gamesettings", "toggle_sprint", false,
                    "Determine whether to use toggle sprint. ");
            prop.set(ConfigLoader.toggle_sprint);

            ConfigLoader.config.save();
        }
        else if (KeyLoader.openHUD.isPressed()) {
            CrazyParkour.instance.hudManager.openConfigScreen();
        }
        else if (KeyLoader.clickGui.isPressed()) {
            Minecraft.getMinecraft().displayGuiScreen(new ClickGui());
        }
        else if (KeyLoader.macroGui.isPressed()) {
            Minecraft.getMinecraft().displayGuiScreen(new MacroGuiScreen());
        }
        else if (KeyLoader.macroRunner.isPressed()) {
            if (MinecraftServer.getServer().isSinglePlayer()) {
                MacroRunner.load(MacroRunner.transformArray(MacroConfigSaver.loadMCSFromFile().passwords, MacroConfigSaver.loadMCSFromFile().dxs));
            }
        }
        else if (KeyLoader.target.isPressed()) {
            TargetBlock.updateStates(Minecraft.getMinecraft().objectMouseOver.getBlockPos());
            OffsetShower.clearPB();
        }
        else if (KeyLoader.autoJudgement.isPressed()) {
            ConfigLoader.auto_judgement = !ConfigLoader.auto_judgement;

            prop = ConfigLoader.config.get("gamesettings", "auto_judgement", false,
                    "Enable/Disable auto judgement. ");
            prop.set(ConfigLoader.auto_judgement);

            ConfigLoader.config.save();
        }
    };

    @SubscribeEvent
    public void onSprint(TickEvent.ClientTickEvent event) {
        if (ConfigLoader.toggle_sprint) {
            KeyBinding.setKeyBindState(Minecraft.getMinecraft().gameSettings.keyBindSprint.getKeyCode(), true);
        }
        else if (KeyLoader.toggleSprint.isKeyDown() && !ConfigLoader.toggle_sprint) {
            KeyBinding.setKeyBindState(Minecraft.getMinecraft().gameSettings.keyBindSprint.getKeyCode(), false);
        }
    }

    @SubscribeEvent
    public void onFillBucket(FillBucketEvent event) {
        BlockPos blockpos = event.target.getBlockPos();
        IBlockState blockState = event.world.getBlockState(blockpos);
        Fluid fluid = FluidRegistry.lookupFluidForBlock(blockState.getBlock());
        if (fluid != null && new Integer(0).equals(blockState.getValue(BlockFluidBase.LEVEL)))
        {
            FluidStack fluidStack = new FluidStack(fluid, FluidContainerRegistry.BUCKET_VOLUME);
            event.world.setBlockToAir(blockpos);
            event.result = FluidContainerRegistry.fillFluidContainer(fluidStack, event.current);
            event.setResult(Event.Result.ALLOW);
        }
    }
}