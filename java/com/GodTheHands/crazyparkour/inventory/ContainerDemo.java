package com.GodTheHands.crazyparkour.inventory;

import com.GodTheHands.crazyparkour.block.BlockLoader;
import com.GodTheHands.crazyparkour.common.ConfigLoader;
import com.GodTheHands.crazyparkour.item.ItemLoader;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerDemo extends Container {
    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return new ItemStack(ItemLoader.parkourCollector).isItemEqual(playerIn.getCurrentEquippedItem());
    }

    private ItemStackHandler items = new ItemStackHandler(54);

    protected Slot slotIndex0;
    protected Slot slotIndex1;
    protected Slot slotIndex2;
    protected Slot slotIndex3;
    protected Slot slotIndex4;
    protected Slot slotIndex5;
    protected Slot slotIndex6;
    protected Slot slotIndex7;
    protected Slot slotIndex8;
    protected Slot slotIndex9;
    protected Slot slotIndex10;
    protected Slot slotIndex11;
    protected Slot slotIndex12;
    protected Slot slotIndex13;
    protected Slot slotIndex14;
    protected Slot slotIndex15;
    protected Slot slotIndex16;
    protected Slot slotIndex17;
    protected Slot slotIndex18;
    protected Slot slotIndex19;
    protected Slot slotIndex20;
    protected Slot slotIndex21;
    protected Slot slotIndex22;
    protected Slot slotIndex23;
    protected Slot slotIndex24;
    protected Slot slotIndex25;
    protected Slot slotIndex26;
    protected Slot slotIndex27;
    protected Slot slotIndex28;
    protected Slot slotIndex29;
    protected Slot slotIndex30;
    protected Slot slotIndex31;
    protected Slot slotIndex32;
    protected Slot slotIndex33;
    protected Slot slotIndex34;
    protected Slot slotIndex35;
    protected Slot slotIndex36;
    protected Slot slotIndex37;
    protected Slot slotIndex38;

    public ContainerDemo(EntityPlayer player) {
        super();

        this.addSlotToContainer(slotIndex0 = new SlotItemHandler(items, 0, 8, 18){
            {
                this.putStack(new ItemStack(Blocks.stained_hardened_clay, 1, ConfigLoader.colorForHardenedClay));
            }
        });

        this.addSlotToContainer(slotIndex1 = new SlotItemHandler(items, 1, 26, 18){
            {
                this.putStack(new ItemStack(ItemLoader.bucketWater, 1));
            }
        });

        this.addSlotToContainer(slotIndex2 = new SlotItemHandler(items, 2, 44, 18){
            {
                this.putStack(new ItemStack(ItemLoader.bucketLava, 1));
            }
        });

        this.addSlotToContainer(slotIndex3 = new SlotItemHandler(items, 3, 62, 18){
            {
                this.putStack(new ItemStack(BlockLoader.lockedBed, 1));
            }
        });

        this.addSlotToContainer(slotIndex4 = new SlotItemHandler(items, 4, 80, 18){
            {
                this.putStack(new ItemStack(Blocks.web, 1));
            }
        });

        this.addSlotToContainer(slotIndex5 = new SlotItemHandler(items, 5, 98, 18){
            {
                this.putStack(new ItemStack(BlockLoader.noHeadPiston, 1));
            }
        });

        this.addSlotToContainer(slotIndex6 = new SlotItemHandler(items, 6, 116, 18){
            {
                this.putStack(new ItemStack(BlockLoader.pistonArm, 1));
            }
        });

        this.addSlotToContainer(slotIndex7 = new SlotItemHandler(items, 7, 134, 18){
            {
                this.putStack(new ItemStack(Blocks.stone_slab, 1));
            }
        });

        this.addSlotToContainer(slotIndex8 = new SlotItemHandler(items, 8, 152, 18){
            {
                this.putStack(new ItemStack(BlockLoader.lockedLadder, 1));
            }
        });

        this.addSlotToContainer(slotIndex9 = new SlotItemHandler(items, 9, 8, 36){
            {
                this.putStack(new ItemStack(BlockLoader.lockedSnow, 1));
            }
        });

        this.addSlotToContainer(slotIndex10 = new SlotItemHandler(items, 10, 26, 36){
            {
                this.putStack(new ItemStack(BlockLoader.noHurtCactus, 1));
            }
        });

        this.addSlotToContainer(slotIndex11 = new SlotItemHandler(items, 11, 44, 36){
            {
                this.putStack(new ItemStack(BlockLoader.lockedFence, 1));
            }
        });

        this.addSlotToContainer(slotIndex12 = new SlotItemHandler(items, 12, 62, 36){
            {
                this.putStack(new ItemStack(Blocks.soul_sand, 1));
            }
        });


        this.addSlotToContainer(slotIndex13 = new SlotItemHandler(items, 13, 80, 36){
            {
                this.putStack(new ItemStack(BlockLoader.lockedGlassPane, 1));
            }
        });

        this.addSlotToContainer(slotIndex14 = new SlotItemHandler(items, 14, 98, 36){
            {
                this.putStack(new ItemStack(BlockLoader.lockedVine, 1));
            }
        });

        this.addSlotToContainer(slotIndex15 = new SlotItemHandler(items, 15, 116, 36){
            {
                this.putStack(new ItemStack(BlockLoader.lockedFenceGate, 1));
            }
        });

        this.addSlotToContainer(slotIndex16 = new SlotItemHandler(items, 16, 134, 36){
            {
                this.putStack(new ItemStack(BlockLoader.infinityCake, 1));
            }
        });

        this.addSlotToContainer(slotIndex17 = new SlotItemHandler(items, 17, 152, 36){
            {
                this.putStack(new ItemStack(BlockLoader.lockedWaterlily, 1));
            }
        });

        this.addSlotToContainer(slotIndex18 = new SlotItemHandler(items, 18, 8, 54){
            {
                this.putStack(new ItemStack(BlockLoader.lockedEnchantingTable, 1));
            }
        });

        this.addSlotToContainer(slotIndex19 = new SlotItemHandler(items, 19, 26, 54){
            {
                this.putStack(new ItemStack(BlockLoader.lockedBrewingStand, 1));
            }
        });

        this.addSlotToContainer(slotIndex20 = new SlotItemHandler(items, 20, 44, 54){
            {
                this.putStack(new ItemStack(BlockLoader.lockedCauldron, 1));
            }
        });

        this.addSlotToContainer(slotIndex21 = new SlotItemHandler(items, 21, 62, 54){
            {
                this.putStack(new ItemStack(Blocks.end_portal_frame, 1));
            }
        });

        this.addSlotToContainer(slotIndex22 = new SlotItemHandler(items, 22, 80, 54){
            {
                this.putStack(new ItemStack(BlockLoader.lockedDragonEgg, 1));
            }
        });

        this.addSlotToContainer(slotIndex23 = new SlotItemHandler(items, 23, 98, 54){
            {
                this.putStack(new ItemStack(BlockLoader.lockedChest, 1));
            }
        });

        this.addSlotToContainer(slotIndex24 = new SlotItemHandler(items, 24, 116, 54){
            {
                this.putStack(new ItemStack(BlockLoader.lockedCobblestoneWall, 1));
            }
        });


        this.addSlotToContainer(slotIndex25 = new SlotItemHandler(items, 25, 134, 54){
            {
                this.putStack(new ItemStack(BlockLoader.lockedFlowerPot, 1));
            }
        });

        this.addSlotToContainer(slotIndex26 = new SlotItemHandler(items, 26, 152, 54){
            {
                this.putStack(new ItemStack(Items.skull, 1));
            }
        });

        this.addSlotToContainer(slotIndex27 = new SlotItemHandler(items, 27, 8, 72){
            {
                this.putStack(new ItemStack(BlockLoader.lockedAnvil, 1));
            }
        });

        this.addSlotToContainer(slotIndex28 = new SlotItemHandler(items, 28, 26, 72){
            {
                this.putStack(new ItemStack(BlockLoader.lockedDaylightDetector, 1));
            }
        });

        this.addSlotToContainer(slotIndex29 = new SlotItemHandler(items, 29, 44, 72){
            {
                this.putStack(new ItemStack(BlockLoader.lockedHopper, 1));
            }
        });

        this.addSlotToContainer(slotIndex30 = new SlotItemHandler(items, 30, 62, 72){
            {
                this.putStack(new ItemStack(BlockLoader.lockedTrapdoor, 1));
            }
        });

        this.addSlotToContainer(slotIndex31 = new SlotItemHandler(items, 31, 80, 72){
            {
                this.putStack(new ItemStack(BlockLoader.lockedCarpet, 1));
            }
        });

        this.addSlotToContainer(slotIndex32 = new SlotItemHandler(items, 32, 98, 72){
            {
                this.putStack(new ItemStack(Blocks.packed_ice, 1));
            }
        });

        this.addSlotToContainer(slotIndex33 = new SlotItemHandler(items, 33, 116, 72){
            {
                this.putStack(new ItemStack(Blocks.slime_block, 1));
            }
        });

        this.addSlotToContainer(slotIndex34 = new SlotItemHandler(items, 34, 134, 72){
            {
                this.putStack(new ItemStack(BlockLoader.lockedCocoa, 1));
            }
        });

        this.addSlotToContainer(slotIndex35 = new SlotItemHandler(items, 35, 152, 72){
            {
                this.putStack(new ItemStack(BlockLoader.lockedRepeater, 1));
            }
        });

        this.addSlotToContainer(slotIndex36 = new SlotItemHandler(items, 36, 8, 90){
            {
                this.putStack(new ItemStack(BlockLoader.lockedStair, 1));
            }
        });

        this.addSlotToContainer(slotIndex37 = new SlotItemHandler(items, 37, 26, 90){
            {
                this.putStack(new ItemStack(BlockLoader.lockedStair1, 1));
            }
        });

        this.addSlotToContainer(slotIndex38 = new SlotItemHandler(items, 38, 44, 90){
            {
                this.putStack(new ItemStack(BlockLoader.lockedStair2, 1));
            }
        });

        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot(player.inventory, 9 + j + i * 9, 8 + j * 18, 140 + i * 18));
            }
        }

        for (int i = 0; i < 9; ++i) {
            this.addSlotToContainer(new Slot(player.inventory, i, 8 + i * 18, 198));
        }
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
    {
        Slot slot = inventorySlots.get(index);

        if (slot == null || !slot.getHasStack())
        {
            return null;
        }

        ItemStack newStack = slot.getStack(), oldStack = newStack.copy();

        boolean isMerged = false;

        if (index >= 0 && index <= 38)
        {
            isMerged = mergeItemStack(newStack, 39, 75, true);
        }
        else if (index >= 39 && index <= 65)
        {
            isMerged = !slotIndex0.getHasStack() && mergeItemStack(newStack, 0, 1, false)
                    || !slotIndex1.getHasStack() && mergeItemStack(newStack, 1, 2, false)
                    || !slotIndex2.getHasStack() && mergeItemStack(newStack, 2, 3, false)
                    || !slotIndex3.getHasStack() && mergeItemStack(newStack, 3, 4, false)
                    || !slotIndex4.getHasStack() && mergeItemStack(newStack, 4, 5, false)
                    || !slotIndex5.getHasStack() && mergeItemStack(newStack, 5, 6, false)
                    || !slotIndex6.getHasStack() && mergeItemStack(newStack, 6, 7, false)
                    || !slotIndex7.getHasStack() && mergeItemStack(newStack, 7, 8, false)
                    || !slotIndex8.getHasStack() && mergeItemStack(newStack, 8, 9, false)
                    || !slotIndex9.getHasStack() && mergeItemStack(newStack, 9, 10, false)
                    || !slotIndex10.getHasStack() && mergeItemStack(newStack, 10, 11, false)
                    || !slotIndex11.getHasStack() && mergeItemStack(newStack, 11, 12, false)
                    || !slotIndex12.getHasStack() && mergeItemStack(newStack, 12, 13, false)
                    || !slotIndex13.getHasStack() && mergeItemStack(newStack, 13, 14, false)
                    || !slotIndex14.getHasStack() && mergeItemStack(newStack, 14, 15, false)
                    || !slotIndex15.getHasStack() && mergeItemStack(newStack, 15, 16, false)
                    || !slotIndex16.getHasStack() && mergeItemStack(newStack, 16, 17, false)
                    || !slotIndex17.getHasStack() && mergeItemStack(newStack, 17, 18, false)
                    || !slotIndex18.getHasStack() && mergeItemStack(newStack, 18, 19, false)
                    || !slotIndex19.getHasStack() && mergeItemStack(newStack, 19, 20, false)
                    || !slotIndex20.getHasStack() && mergeItemStack(newStack, 20, 21, false)
                    || !slotIndex21.getHasStack() && mergeItemStack(newStack, 21, 22, false)
                    || !slotIndex22.getHasStack() && mergeItemStack(newStack, 22, 23, false)
                    || !slotIndex23.getHasStack() && mergeItemStack(newStack, 23, 24, false)
                    || !slotIndex24.getHasStack() && mergeItemStack(newStack, 24, 25, false)
                    || !slotIndex25.getHasStack() && mergeItemStack(newStack, 25, 26, false)
                    || !slotIndex26.getHasStack() && mergeItemStack(newStack, 26, 27, false)
                    || !slotIndex27.getHasStack() && mergeItemStack(newStack, 27, 28, false)
                    || !slotIndex28.getHasStack() && mergeItemStack(newStack, 28, 29, false)
                    || !slotIndex29.getHasStack() && mergeItemStack(newStack, 29, 30, false)
                    || !slotIndex30.getHasStack() && mergeItemStack(newStack, 30, 31, false)
                    || !slotIndex31.getHasStack() && mergeItemStack(newStack, 31, 32, false)
                    || !slotIndex32.getHasStack() && mergeItemStack(newStack, 32, 33, false)
                    || !slotIndex33.getHasStack() && mergeItemStack(newStack, 33, 34, false)
                    || !slotIndex34.getHasStack() && mergeItemStack(newStack, 34, 35, false)
                    || !slotIndex35.getHasStack() && mergeItemStack(newStack, 35, 36, false)
                    || !slotIndex36.getHasStack() && mergeItemStack(newStack, 36, 37, false)
                    || !slotIndex37.getHasStack() && mergeItemStack(newStack, 37, 38, false)
                    || !slotIndex38.getHasStack() && mergeItemStack(newStack, 38, 39, false)
                    || mergeItemStack(newStack, 66, 75, false);
        }
        else if (index >= 66 && index <= 74)
        {
            isMerged = !slotIndex0.getHasStack() && mergeItemStack(newStack, 0, 1, false)
                    || !slotIndex1.getHasStack() && mergeItemStack(newStack, 1, 2, false)
                    || !slotIndex2.getHasStack() && mergeItemStack(newStack, 2, 3, false)
                    || !slotIndex3.getHasStack() && mergeItemStack(newStack, 3, 4, false)
                    || !slotIndex4.getHasStack() && mergeItemStack(newStack, 4, 5, false)
                    || !slotIndex5.getHasStack() && mergeItemStack(newStack, 5, 6, false)
                    || !slotIndex6.getHasStack() && mergeItemStack(newStack, 6, 7, false)
                    || !slotIndex7.getHasStack() && mergeItemStack(newStack, 7, 8, false)
                    || !slotIndex8.getHasStack() && mergeItemStack(newStack, 8, 9, false)
                    || !slotIndex9.getHasStack() && mergeItemStack(newStack, 9, 10, false)
                    || !slotIndex10.getHasStack() && mergeItemStack(newStack, 10, 11, false)
                    || !slotIndex11.getHasStack() && mergeItemStack(newStack, 11, 12, false)
                    || !slotIndex12.getHasStack() && mergeItemStack(newStack, 12, 13, false)
                    || !slotIndex13.getHasStack() && mergeItemStack(newStack, 13, 14, false)
                    || !slotIndex14.getHasStack() && mergeItemStack(newStack, 14, 15, false)
                    || !slotIndex15.getHasStack() && mergeItemStack(newStack, 15, 16, false)
                    || !slotIndex16.getHasStack() && mergeItemStack(newStack, 16, 17, false)
                    || !slotIndex17.getHasStack() && mergeItemStack(newStack, 17, 18, false)
                    || !slotIndex18.getHasStack() && mergeItemStack(newStack, 18, 19, false)
                    || !slotIndex19.getHasStack() && mergeItemStack(newStack, 19, 20, false)
                    || !slotIndex20.getHasStack() && mergeItemStack(newStack, 20, 21, false)
                    || !slotIndex21.getHasStack() && mergeItemStack(newStack, 21, 22, false)
                    || !slotIndex22.getHasStack() && mergeItemStack(newStack, 22, 23, false)
                    || !slotIndex23.getHasStack() && mergeItemStack(newStack, 23, 24, false)
                    || !slotIndex24.getHasStack() && mergeItemStack(newStack, 24, 25, false)
                    || !slotIndex25.getHasStack() && mergeItemStack(newStack, 25, 26, false)
                    || !slotIndex26.getHasStack() && mergeItemStack(newStack, 26, 27, false)
                    || !slotIndex27.getHasStack() && mergeItemStack(newStack, 27, 28, false)
                    || !slotIndex28.getHasStack() && mergeItemStack(newStack, 28, 29, false)
                    || !slotIndex29.getHasStack() && mergeItemStack(newStack, 29, 30, false)
                    || !slotIndex30.getHasStack() && mergeItemStack(newStack, 30, 31, false)
                    || !slotIndex31.getHasStack() && mergeItemStack(newStack, 31, 32, false)
                    || !slotIndex32.getHasStack() && mergeItemStack(newStack, 32, 33, false)
                    || !slotIndex33.getHasStack() && mergeItemStack(newStack, 33, 34, false)
                    || !slotIndex34.getHasStack() && mergeItemStack(newStack, 34, 35, false)
                    || !slotIndex35.getHasStack() && mergeItemStack(newStack, 35, 36, false)
                    || !slotIndex36.getHasStack() && mergeItemStack(newStack, 36, 37, false)
                    || !slotIndex37.getHasStack() && mergeItemStack(newStack, 37, 38, false)
                    || !slotIndex38.getHasStack() && mergeItemStack(newStack, 38, 39, false)
                    || mergeItemStack(newStack, 39, 66, false);
        }

        if (!isMerged)
        {
            return null;
        }

        if (newStack.stackSize == 0)
        {
            slot.putStack(null);
        }
        else
        {
            slot.onSlotChanged();
        }

        slot.onPickupFromSlot(playerIn, newStack);

        return oldStack;
    }
}
