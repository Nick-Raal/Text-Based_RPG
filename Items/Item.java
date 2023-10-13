package Items;

public class Item{

  private String name;
  private int value;
  private int rarity;
  private double healthE;
  private double strE;
  private double manaE;

  public Item(String name, int value, int rarity){
    this.name = name;
    this.value = value;
    this.rarity = rarity;
  }

  public Item(String name, int value, int rarity, double healthE, double strE, double manaE){
    this.name = name;
    this.value = value;
    this.rarity = rarity;
    this.healthE = healthE;
    this.strE = strE;
    this.manaE = manaE;
  }
  public String getName(){
    return name;
  }

  

  // public double getDamage(){
  //   return 0;
  // }
  // public double getType(){
  //   return 1;
  // }

  public void setValue(int value){
    this.value = value;
  }

  public void setRarity(int rarity){
    this.rarity = rarity;
  }

  public void setName(String name){
    this.name = name;
  }

  public double[] use(){
    double[] u = {healthE, strE, manaE};
    return u;
  }

  public String toString(){
    return name + " " + value + " " + rarity;
  }
}