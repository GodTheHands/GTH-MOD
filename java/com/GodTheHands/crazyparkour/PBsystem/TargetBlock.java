package com.GodTheHands.crazyparkour.PBsystem;

import com.GodTheHands.crazyparkour.block.BlockLoader;
import com.GodTheHands.crazyparkour.common.ConfigLoader;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;

public class TargetBlock {
    protected static Minecraft minecraft = Minecraft.getMinecraft();
    protected static World worldIn = minecraft.thePlayer.worldObj;
    protected static BlockPos blockPos;
    protected static Block targetBlock;
    protected static AxisAlignedBB AABB;

    protected static BlockPos UPosT1;
    protected static Block blockAdjacentUT1;
    protected static AxisAlignedBB AABBUT1;
    protected static BlockPos UPosT2;
    protected static Block blockAdjacentUT2;
    protected static AxisAlignedBB AABBUT2;

    protected static BlockPos XLPosT0H;
    protected static Block blockAdjacentXLT0H;
    protected static AxisAlignedBB AABBXLT0H;
    protected static BlockPos XLPosT1;
    protected static Block blockAdjacentXLT1;
    protected static AxisAlignedBB AABBXLT1;
    protected static BlockPos XLPosT2;
    protected static Block blockAdjacentXLT2;
    protected static AxisAlignedBB AABBXLT2;
    protected static BlockPos XGPosT0H;
    protected static Block blockAdjacentXGT0H;
    protected static AxisAlignedBB AABBXGT0H;
    protected static BlockPos XGPosT1;
    protected static Block blockAdjacentXGT1;
    protected static AxisAlignedBB AABBXGT1;
    protected static BlockPos XGPosT2;
    protected static Block blockAdjacentXGT2;
    protected static AxisAlignedBB AABBXGT2;
    protected static BlockPos ZLPosT0H;
    protected static Block blockAdjacentZLT0H;
    protected static AxisAlignedBB AABBZLT0H;
    protected static BlockPos ZLPosT1;
    protected static Block blockAdjacentZLT1;
    protected static AxisAlignedBB AABBZLT1;
    protected static BlockPos ZLPosT2;
    protected static Block blockAdjacentZLT2;
    protected static AxisAlignedBB AABBZLT2;
    protected static BlockPos ZGPosT0H;
    protected static Block blockAdjacentZGT0H;
    protected static AxisAlignedBB AABBZGT0H;
    protected static BlockPos ZGPosT1;
    protected static Block blockAdjacentZGT1;
    protected static AxisAlignedBB AABBZGT1;
    protected static BlockPos ZGPosT2;
    protected static Block blockAdjacentZGT2;
    protected static AxisAlignedBB AABBZGT2;

    public static void updateStates(BlockPos pos) {
        worldIn = minecraft.thePlayer.worldObj;
        blockPos = new BlockPos(pos);

        //Upper block
        UPosT1 = blockPos.up();
        UPosT2 = UPosT1.up();
        blockAdjacentUT1 = worldIn.getBlockState(UPosT1).getBlock();
        blockAdjacentUT2 = worldIn.getBlockState(UPosT2).getBlock();
        AABBUT1 = blockAdjacentUT1.getCollisionBoundingBox(worldIn, UPosT1, worldIn.getBlockState(UPosT1));
        AABBUT2 = blockAdjacentUT2.getCollisionBoundingBox(worldIn, UPosT2, worldIn.getBlockState(UPosT2));
        //Block position
        XLPosT0H = blockPos.west();
        XLPosT1 = XLPosT0H.up();
        XLPosT2 = XLPosT1.up();

        XGPosT0H = blockPos.east();
        XGPosT1 = XGPosT0H.up();
        XGPosT2 = XGPosT1.up();

        ZLPosT0H = blockPos.north();
        ZLPosT1 = ZLPosT0H.up();
        ZLPosT2 = ZLPosT1.up();

        ZGPosT0H = blockPos.south();
        ZGPosT1 = ZGPosT0H.up();
        ZGPosT2 = ZGPosT1.up();
        //Get block
        targetBlock = worldIn.getBlockState(blockPos).getBlock();

        blockAdjacentXLT0H = worldIn.getBlockState(XLPosT0H).getBlock();
        blockAdjacentXLT1 = worldIn.getBlockState(XLPosT1).getBlock();
        blockAdjacentXLT2 = worldIn.getBlockState(XLPosT2).getBlock();

        blockAdjacentXGT0H = worldIn.getBlockState(XGPosT0H).getBlock();
        blockAdjacentXGT1 = worldIn.getBlockState(XGPosT1).getBlock();
        blockAdjacentXGT2 = worldIn.getBlockState(XGPosT2).getBlock();

        blockAdjacentZLT0H = worldIn.getBlockState(ZLPosT0H).getBlock();
        blockAdjacentZLT1 = worldIn.getBlockState(ZLPosT1).getBlock();
        blockAdjacentZLT2 = worldIn.getBlockState(ZLPosT2).getBlock();

        blockAdjacentZGT0H = worldIn.getBlockState(ZGPosT0H).getBlock();
        blockAdjacentZGT1 = worldIn.getBlockState(ZGPosT1).getBlock();
        blockAdjacentZGT2 = worldIn.getBlockState(ZGPosT2).getBlock();
        //Bounds
        AABB = targetBlock.getCollisionBoundingBox(worldIn, blockPos, worldIn.getBlockState(blockPos));

        AABBXLT0H = blockAdjacentXLT0H.getCollisionBoundingBox(worldIn, XLPosT0H, worldIn.getBlockState(XLPosT0H));
        AABBXLT1 = blockAdjacentXLT1.getCollisionBoundingBox(worldIn, XLPosT1, worldIn.getBlockState(XLPosT1));
        AABBXLT2 = blockAdjacentXLT2.getCollisionBoundingBox(worldIn, XLPosT2, worldIn.getBlockState(XLPosT2));

        AABBXGT0H = blockAdjacentXGT0H.getCollisionBoundingBox(worldIn, XGPosT0H, worldIn.getBlockState(XGPosT0H));
        AABBXGT1 = blockAdjacentXGT1.getCollisionBoundingBox(worldIn, XGPosT1, worldIn.getBlockState(XGPosT1));
        AABBXGT2 = blockAdjacentXGT2.getCollisionBoundingBox(worldIn, XGPosT2, worldIn.getBlockState(XGPosT2));

        AABBZLT0H = blockAdjacentZLT0H.getCollisionBoundingBox(worldIn, ZLPosT0H, worldIn.getBlockState(ZLPosT0H));
        AABBZLT1 = blockAdjacentZLT1.getCollisionBoundingBox(worldIn, ZLPosT1, worldIn.getBlockState(ZLPosT1));
        AABBZLT2 = blockAdjacentZLT2.getCollisionBoundingBox(worldIn, ZLPosT2, worldIn.getBlockState(ZLPosT2));

        AABBZGT0H = blockAdjacentZGT0H.getCollisionBoundingBox(worldIn, ZGPosT0H, worldIn.getBlockState(ZGPosT0H));
        AABBZGT1 = blockAdjacentZGT1.getCollisionBoundingBox(worldIn, ZGPosT1, worldIn.getBlockState(ZGPosT1));
        AABBZGT2 = blockAdjacentZGT2.getCollisionBoundingBox(worldIn, ZGPosT2, worldIn.getBlockState(ZGPosT2));

        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentTranslation("commands.target.success"));
    }
    
    protected static double getMinX() {
        if (AABB == null) {
            return 0.0D;
        }

        double result = 0.0;
        //Glass pane judgement
        if ((targetBlock == Blocks.glass_pane) || (targetBlock == Blocks.stained_glass_pane) || (targetBlock == BlockLoader.lockedGlassPane)) {
            if (AABB.maxX == Math.floor(AABB.maxX) && AABB.minX != Math.floor(AABB.minX))
            {
                result = AABB.minX + 0.0625;
            }
            else {
                result = AABB.minX;
            }
        }
        else {
            result = AABB.minX;
        }
        //Up
        if (AABBUT1 != null) {
            if (shouldBeXNeo(AABBUT1, AABB) && (AABBUT1.minX <= AABB.minX)) {
                if (AABBUT1.maxX + 0.6 > result) {
                    result = AABBUT1.maxX + 0.6;
                }
            }
        }

        if (AABBUT2 != null) {
            if ((AABBUT2.minY < AABB.maxY + 1.8)) {
                if (shouldBeXNeo(AABBUT2, AABB) && (AABBUT2.minX <= AABB.minX)) {
                    if (AABBUT2.maxX + 0.6 > result) {
                        result = AABBUT2.maxX + 0.6;
                    }
                }
            }
        }
        //T0H
        if (AABBXLT0H != null) {
            if (compareYCoord(isFence(blockAdjacentXLT0H), AABBXLT0H, isFence(targetBlock), AABB)) {
                if (shouldBeXNeo(AABBXLT0H, AABB)) {
                    if (AABBXLT0H.maxX + 0.6 > result) {
                        result = AABBXLT0H.maxX + 0.6;
                    }
                }
            }
        }
        //T1
        if (AABBXLT1 != null) {
            if (shouldBeXNeo(AABBXLT1, AABB)) {
                if (AABBXLT1.maxX + 0.6 > result) {
                    result = AABBXLT1.maxX + 0.6;
                }
            }
        }
        //T2
        if (AABBXLT2 != null) {
            if (AABBXLT2.minY < AABB.maxY + 1.8) {
                if (shouldBeXNeo(AABBXLT2, AABB)) {
                    if (AABBXLT2.maxX + 0.6 > result) {
                        result = AABBXLT2.maxX + 0.6;
                    }
                }
            }
        }
        return result - 0.3;
    }
    
    protected static double getMaxX() {
        if (AABB == null) {
            return 0.0D;
        }

        double result = 0.0;

        //Glass pane judgement
        if ((targetBlock == Blocks.glass_pane) || (targetBlock == Blocks.stained_glass_pane) || (targetBlock == BlockLoader.lockedGlassPane)) {
            if (AABB.minX == Math.floor(AABB.minX) && AABB.maxX != Math.floor(AABB.maxX))
            {
                result = AABB.maxX - 0.0625;
            }
            else {
                result = AABB.maxX;
            }
        }
        else {
            result = AABB.maxX;
        }
        //Up
        if (AABBUT1 != null) {
            if (shouldBeXNeo(AABBUT1, AABB) && (AABBUT1.maxX >= AABB.maxX)) {
                if (AABBUT1.minX - 0.6 < result) {
                    result = AABBUT1.minX - 0.6;
                }
            }
        }

        if (AABBUT2 != null) {
            if ((AABBUT2.minY < AABB.maxY + 1.8)) {
                if (shouldBeXNeo(AABBUT2, AABB) && (AABBUT2.maxX >= AABB.maxX)) {
                    if (AABBUT2.minX - 0.6 < result) {
                        result = AABBUT2.minX - 0.6;
                    }
                }
            }
        }
        //T0H
        if (AABBXGT0H != null) {
            if (compareYCoord(isFence(blockAdjacentXGT0H), AABBXGT0H, isFence(targetBlock), AABB)) {
                if (shouldBeXNeo(AABBXGT0H, AABB)) {
                    if (AABBXGT0H.minX - 0.6 < result) {
                        result = AABBXGT0H.minX - 0.6;
                    }
                }
            }
        }
        //T1
        if (AABBXGT1 != null) {
            if (shouldBeXNeo(AABBXGT1, AABB)) {
                if (AABBXGT1.minX - 0.6 < result) {
                    result = AABBXGT1.minX - 0.6;
                }
            }
        }
        //T2
        if (AABBXGT2 != null) {
            if (AABBXGT2.minY < AABB.maxY + 1.8) {
                if (shouldBeXNeo(AABBXGT2, AABB)) {
                    if (AABBXGT2.minX - 0.6 < result) {
                        result = AABBXGT2.minX - 0.6;
                    }
                }
            }
        }

        return result + 0.3;
    }
    
    protected static double getMinY() {
        if (AABB == null) {
            return 0.0D;
        }

        return AABB.minY;
    }
    
    protected static double getMaxY() {
        if (AABB == null) {
            return 0.0D;
        }

        if (isFence(targetBlock)) {
            return AABB.maxY + 0.5;
        }

        return AABB.maxY;
    }
    
    protected static double getMinZ() {
        if (AABB == null) {
            return 0.0D;
        }

        double result = 0.0;

        //Glass pane judgement
        if ((targetBlock == Blocks.glass_pane) || (targetBlock == Blocks.stained_glass_pane) || (targetBlock == BlockLoader.lockedGlassPane)) {
            if (AABB.maxZ == Math.floor(AABB.maxZ) && AABB.minZ != Math.floor(AABB.minZ))
            {
                result = AABB.minZ + 0.0625;
            }
            else {
                result = AABB.minZ;
            }
        }
        else {
            result = AABB.minZ;
        }
        //Up
        if (AABBUT1 != null) {
            if (shouldBeZNeo(AABBUT1, AABB) && (AABBUT1.minZ <= AABB.minZ)) {
                if (AABBUT1.maxZ + 0.6 > result) {
                    result = AABBUT1.maxZ + 0.6;
                }
            }
        }

        if (AABBUT2 != null) {
            if ((AABBUT2.minY < AABB.maxY + 1.8)) {
                if (shouldBeZNeo(AABBUT2, AABB) && (AABBUT2.minZ <= AABB.minZ)) {
                    if (AABBUT2.maxZ + 0.6 > result) {
                        result = AABBUT2.maxZ + 0.6;
                    }
                }
            }
        }
        //T0H
        if (AABBZLT0H != null) {
            if (compareYCoord(isFence(blockAdjacentZLT0H), AABBZLT0H, isFence(targetBlock), AABB)) {
                if (shouldBeZNeo(AABBZLT0H, AABB)) {
                    if (AABBZLT0H.maxZ + 0.6 > result) {
                        result = AABBZLT0H.maxZ + 0.6;
                    }
                }
            }
        }
        //T1
        if (AABBZLT1 != null) {
            if (shouldBeZNeo(AABBZLT1, AABB)) {
                if (AABBZLT1.maxZ + 0.6 > result) {
                    result = AABBZLT1.maxZ + 0.6;
                }
            }
        }
        //T2
        if (AABBZLT2 != null) {
            if (AABBZLT2.minY < AABB.maxY + 1.8) {
                if (shouldBeZNeo(AABBZLT2, AABB)) {
                    if (AABBZLT2.maxZ + 0.6 > result) {
                        result = AABBZLT2.maxZ + 0.6;
                    }
                }
            }
        }
        return result - 0.3;
    }
    
    protected static double getMaxZ() {
        if (AABB == null) {
            return 0.0D;
        }

        double result = 0.0;

        //Glass pane judgement
        if ((targetBlock == Blocks.glass_pane) || (targetBlock == Blocks.stained_glass_pane) || (targetBlock == BlockLoader.lockedGlassPane)) {
            if (AABB.minZ == Math.floor(AABB.minZ) && AABB.maxZ != Math.floor(AABB.maxZ))
            {
                result = AABB.maxZ - 0.0625;
            }
            else {
                result = AABB.maxZ;
            }
        }
        else {
            result = AABB.maxZ;
        }
        //Up
        if (AABBUT1 != null) {
            if (shouldBeZNeo(AABBUT1, AABB) && (AABBUT1.maxZ >= AABB.maxZ)) {
                if (AABBUT1.minZ - 0.6 < result) {
                    result = AABBUT1.minZ - 0.6;
                }
            }
        }

        if (AABBUT2 != null) {
            if ((AABBUT2.minY < AABB.maxY + 1.8)) {
                if (shouldBeZNeo(AABBUT2, AABB) && (AABBUT2.maxZ >= AABB.maxZ)) {
                    if (AABBUT2.minZ - 0.6 < result) {
                        result = AABBUT2.minZ - 0.6;
                    }
                }
            }
        }
        //T0H
        if (AABBZGT0H != null) {
            if (compareYCoord(isFence(blockAdjacentZGT0H), AABBZGT0H, isFence(targetBlock), AABB)) {
                if (shouldBeZNeo(AABBZGT0H, AABB)) {
                    if (AABBZGT0H.minZ - 0.6 < result) {
                        result = AABBZGT0H.minZ - 0.6;
                    }
                }
            }
        }
        //T1
        if(AABBZGT1 != null) {
            if (shouldBeZNeo(AABBZGT1, AABB)) {
                if (AABBZGT1.minZ - 0.6 < result) {
                    result = AABBZGT1.minZ - 0.6;
                }
            }
        }
        //T2
        if (AABBZGT2 != null) {
            if (AABBZGT2.minY < AABB.maxY + 1.8) {
                if (shouldBeZNeo(AABBZGT2, AABB)) {
                    if (AABBZGT2.minZ - 0.6 < result) {
                        result = AABBZGT2.minZ - 0.6;
                    }
                }
            }
        }

        return result + 0.3;
    }

    private static boolean isFence(Block target) {
        return (target == Blocks.acacia_fence || target == Blocks.birch_fence || target == Blocks.dark_oak_fence
        || target == Blocks.jungle_fence || target == Blocks.nether_brick_fence || target == Blocks.oak_fence || target == Blocks.spruce_fence);
    }

    private static boolean compareYCoord(boolean isFence1, AxisAlignedBB AABB1, boolean isFence2, AxisAlignedBB AABB2) {
        if (AABB1 == null) {
            return false;
        }

        double compareY1 = AABB1.maxY + (isFence1 ? 0.5D : 0.0D);
        double compareY2 = AABB2.maxY + (isFence2 ? 0.5D : 0.0D);
        return compareY1 > compareY2;
    }

    private static boolean shouldBeXNeo(AxisAlignedBB AABB1, AxisAlignedBB AABB2) {
        if (AABB1 == null) {
            return false;
        }

        return (AABB1.minZ <= AABB2.minZ) && (AABB1.maxZ >= AABB2.maxZ);
    }

    private static boolean shouldBeZNeo(AxisAlignedBB AABB1, AxisAlignedBB AABB2) {
        if (AABB1 == null) {
            return false;
        }

        return (AABB1.minX <= AABB2.minX) && (AABB1.maxX >= AABB2.maxX);
    }
}