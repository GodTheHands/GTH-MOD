package com.GodTheHands.crazyparkour.capability;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class CapabilityPositionStorage {
    public static class Storage implements Capability.IStorage<IPositionStorage> {
        @Override
        public NBTBase writeNBT(Capability<IPositionStorage> capability, IPositionStorage instance, EnumFacing side) {
            NBTTagList list = new NBTTagList();
            NBTTagCompound compound = new NBTTagCompound();

            if ((instance.getX()) != null) {
                compound.setString("x", instance.getX());
                compound.setString("y", instance.getY());
                compound.setString("z", instance.getZ());
                compound.setString("yaw", instance.getYaw());
                compound.setString("pitch", instance.getPitch());
            }
            list.appendTag(compound);
            return list;
        }

        @Override
        public void readNBT(Capability<IPositionStorage> capability, IPositionStorage instance, EnumFacing side,
                            NBTBase nbt) {
            NBTTagList list = (NBTTagList) nbt;
            NBTTagCompound compound = list.getCompoundTagAt(0);
            if (!compound.hasNoTags()) {
                instance.setX(compound.getString("x"));
                instance.setY(compound.getString("y"));
                instance.setZ(compound.getString("z"));
                instance.setYaw(compound.getString("yaw"));
                instance.setPitch(compound.getString("pitch"));
            }
        }
    }

    public static class Implementation implements IPositionStorage {
        String savedX = "0", savedY = "0", savedZ = "0", yaw = "0", pitch = "0";

        @Override
        public String getX() {
            if (savedX.isEmpty()) {
                savedX = "0";
            }
            return savedX;
        }

        @Override
        public String getY() {
            if (savedY.isEmpty()) {
                savedY = "0";
            }
            return savedY;
        }

        @Override
        public String getZ() {
            if (savedZ.isEmpty()) {
                savedZ = "0";
            }
            return savedZ;
        }

        @Override
        public String getYaw() {
            if (yaw.isEmpty()) {
                yaw = "0";
            }
            return yaw;
        }

        @Override
        public String getPitch() {
            if (pitch.isEmpty()) {
                pitch = "0";
            }
            return pitch;
        }

        @Override
        public void setX(String x) {
            this.savedX = x;
        }

        @Override
        public void setY(String y) {
            this.savedY = y;
        }

        @Override
        public void setZ(String z) {
            this.savedZ = z;
        }

        @Override
        public void setYaw(String yaw) {
            this.yaw = yaw;
        }

        @Override
        public void setPitch(String pitch) {
            this.pitch = pitch;
        }
    }

    public static class ProviderPlayer implements ICapabilitySerializable<NBTTagCompound> {
        private IPositionStorage histories = new Implementation();
        private Capability.IStorage<IPositionStorage> storage = CapabilityLoader.positionStorage.getStorage();

        @Override
        public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
            return CapabilityLoader.positionStorage.equals(capability);
        }

        @Override
        public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
            if (CapabilityLoader.positionStorage.equals(capability))
            {
                @SuppressWarnings("unchecked")
                T result = (T) histories;
                return result;
            }
            return null;
        }

        @Override
        public NBTTagCompound serializeNBT() {
            NBTTagCompound compound = new NBTTagCompound();
            compound.setTag("histories", storage.writeNBT(CapabilityLoader.positionStorage, histories, null));
            return compound;
        }

        @Override
        public void deserializeNBT(NBTTagCompound compound) {
            NBTTagList list = (NBTTagList) compound.getTag("histories");
            storage.readNBT(CapabilityLoader.positionStorage, histories, null, list);
        }
    }
}
