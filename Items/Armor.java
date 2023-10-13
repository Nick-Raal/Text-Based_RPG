package Items;

public class Armor extends Item {
  private double armor;
  private int type;
  public Armor(String name, int value, int rarity){
    super(name,value, rarity);
  }
  public double getArmor(){
    return armor;
  }

  public int getType(){
    return type;
  }
}