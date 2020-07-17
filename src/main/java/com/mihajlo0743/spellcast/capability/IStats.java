package com.mihajlo0743.spellcast.capability;

public interface IStats {

    public void setShieldMax(byte max, byte val);

    public void changeShield(int i);

    public void setMaxMana(int i);
    public void changeMana(float i);
    public void changeHP(int i);

    public int getShield();
    public int getMaxShld();
    public int getHealth();
    public float getMana();
    public int getMaxMana();

    public boolean isLocked();
    public void Lock(boolean state);

    public void set(int Shield, int MaxSh, int Health, int MaxMana);
}
