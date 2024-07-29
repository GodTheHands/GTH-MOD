package com.GodTheHands.crazyparkour.capability;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class CapabilityPositionHistory {
    public static class Storage implements Capability.IStorage<IPositionHistory> {
        @Override
        public NBTBase writeNBT(Capability<IPositionHistory> capability, IPositionHistory instance, EnumFacing side) {
            NBTTagList list = new NBTTagList();
            NBTTagCompound compound = new NBTTagCompound();

            if ((instance.getPosition()) != null) {
                compound.setDouble("x", instance.getPosition().xCoord);
                compound.setDouble("y", instance.getPosition().yCoord);
                compound.setDouble("z", instance.getPosition().zCoord);
                compound.setFloat("yaw", instance.getRotationYaw());
                compound.setFloat("pitch", instance.getRotationPitch());
            }
            list.appendTag(compound);
            return list;
        }

        @Override
        public void readNBT(Capability<IPositionHistory> capability, IPositionHistory instance, EnumFacing side,
                            NBTBase nbt) {
            NBTTagList list = (NBTTagList) nbt;
            NBTTagCompound compound = list.getCompoundTagAt(0);
            if (!compound.hasNoTags()) {
                instance.setPosition(new Vec3(compound.getDouble("x"), compound.getDouble("y"), compound.getDouble("z")));
                instance.setRotationYaw(compound.getFloat("yaw"));
                instance.setRotationPitch(compound.getFloat("pitch"));
            }
        }
    }

    public static class Implementation implements IPositionHistory {
        static Double savedX, savedY, savedZ;
        static Float yaw, pitch;

        @Override
        public Vec3 getPosition() {
            if (savedX == null) {
                savedX = 0.0D;
            }

            if (savedY == null) {
                savedY = 0.0D;
            }

            if (savedZ == null) {
                savedZ = 0.0D;
            }
            return new Vec3(savedX, savedY, savedZ);
        }

        @Override
        public float getRotationYaw() {
            if (yaw == null) {
                yaw = 0.0F;
            }
            return yaw;
        }

        @Override
        public float getRotationPitch() {
            if (pitch == null) {
                pitch = 0.0F;
            }
            return pitch;
        }

        @Override
        public void setPosition(Vec3 position) {
            savedX = position.xCoord;
            savedY = position.yCoord;
            savedZ = position.zCoord;
        }

        @Override
        public void setRotationYaw(float yaw) {
            this.yaw = yaw;
        }

        @Override
        public void setRotationPitch(float pitch) {
            this.pitch = pitch;
        }
    }

    public static class ProviderPlayer implements ICapabilitySerializable<NBTTagCompound> {
        private IPositionHistory positionState = new Implementation();
        private Capability.IStorage<IPositionHistory> storage = CapabilityLoader.positionHistory.getStorage();

        @Override
        public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
            return CapabilityLoader.positionHistory.equals(capability);
        }

        @Override
        public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
            if (CapabilityLoader.positionHistory.equals(capability))
            {
                @SuppressWarnings("unchecked")
                T result = (T) positionState;
                return result;
            }
            return null;
        }

        @Override
        public NBTTagCompound serializeNBT() {
            NBTTagCompound compound = new NBTTagCompound();
            compound.setTag("position", storage.writeNBT(CapabilityLoader.positionHistory, positionState, null));
            return compound;
        }

        @Override
        public void deserializeNBT(NBTTagCompound compound) {
            NBTTagList list = (NBTTagList) compound.getTag("position");
            storage.readNBT(CapabilityLoader.positionHistory, positionState, null, list);
        }
    }
}
