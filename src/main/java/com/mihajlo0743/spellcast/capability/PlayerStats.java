package com.mihajlo0743.spellcast.capability;

public class PlayerStats  implements IStats
{
    public int Health = 100;
    public int Shield = 100;
    public int MaxHealth = 100;
    public int MaxShield = 100;
    public float Mana = 100;
    public int MaxMana = 100;
    public boolean Lock = false;

    public PlayerStats(){
        Health = 100;
        Shield = 100;
        Mana = 100;

    }

    @Override
    public void setShieldMax(byte max, byte val) {
        MaxShield = max;
        Shield = val;
    }

    @Override
    public void changeShield(int i) {
        Shield= clamp(Shield+i, MaxShield);
    }

    @Override
    public void setMaxMana(int i) {
        MaxMana = i;
        Mana = i;
    }

    @Override
    public void changeMana(float i) {
        Mana = clamp(Mana+i, MaxMana);
    }

    @Override
    public void changeHP(int i) {
        Health = clamp(Health+i,MaxHealth);
    }

    @Override
    public int getShield() {
        return Shield;
    }

    @Override
    public int getMaxShld() {
        return MaxShield;
    }

    @Override
    public int getHealth() {
        return Health;
    }

    @Override
    public float getMana() {
        return Mana;
    }

    @Override
    public int getMaxMana() {
        return MaxMana;
    }

    @Override
    public boolean isLocked() {
        return Lock;
    }

    @Override
    public void Lock(boolean state) {
        Lock = state;
    }

    @Override
    public void set(int Shield, int MaxSh, int Health, int MaxMana) {
        this.Shield = Shield;
        this.MaxShield = MaxSh;
        this.Health = Health;
        setMaxMana(MaxMana);
    }

    public static int clamp(int val, int max) {
    return Math.max(0, Math.min(max, val));
}
    public static float clamp(float val, int max) {
        return Math.max(0, Math.min(max, val));
    }
}
