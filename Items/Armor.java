package Items;

public class Armor extends Item {
  private double armor;
  private int type;
  public Armor(String name, int value, int rarity){
    super(name,value, rarity);
  }

  public Armor(String name, double armor, int type){
    super(name, 0, 0);
    this.type = type;
    this.armor = armor;
  }
  
  public double getArmor(){
    return armor;
  }

  public int getType(){
    return type;
  }
}