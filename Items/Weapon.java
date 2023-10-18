package Items;

import Items.Item;

public class Weapon extends Item {
  private double damage;
  private int type;
  private double manaC;
  private double strC;
  private String demo;

  public Weapon(String name, double damage, int type){
    super(name, 0, 0);
    this.damage = damage;
    this.type = type;
    strC = 0;
    manaC = 0;
  }
  
  public Weapon(String name, int rarity, int value, double damage, int type){
    super(name, rarity, value);
    this.setRarity(rarity);;
    this.setValue(value);
    this.damage = damage;
    this.type = type;
    strC = 0;
    manaC = 0;
  }

  public Weapon(String name, int rarity, int value, double damage, int type, double manaC, double strC){
    super(name, rarity, value);
    this.damage = damage;
    this.type = type;
    this.strC = strC;
    this.manaC = manaC;
  }
  public Weapon(String name, int rarity, int value, double damage, int type, double manaC, double strC, String demo){
    super(name, rarity, value);
    this.damage = damage;
    this.type = type;
    this.strC = strC;
    this.manaC = manaC;
    this.demo = demo;
  }

  public Weapon(String name, int rarity, int value, double damage, int type, String demo) {
    super(name, rarity, value);
    this.damage = damage;
    this.type = type;
    this.demo = demo;
  }

  public double getDamage(){
    return damage;
  }

  public int getType(){
    return type;
  }
  public double getStr(){
    return strC;
  }
  public double getMana(){
    return manaC;
  }
  public String toString(){
    return (demo != null ? demo : "" )+ " " + this.getName();
  }

  public String getFH(){
    return "ẅ" + super.getName().replaceAll(" ", "_") + " " + super.getValue() + " " +super.getRarity() + " " + type + " " + damage  + " " +demo;
  }
}