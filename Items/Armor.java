package Items;

public class Armor extends Item {
  private double armor;
  private int type;
  //0 = head, 1 = chest, 2 = legs, 3 = feet
  private int slot;
  public Armor(String name, int value, int rarity){
    super(name,value, rarity);
  }
  
  public Armor(String name, double armor, int type){
    super(name, 0, 0);
    this.type = type;
    this.armor = armor;
  }

  public Armor(String name, double armor, int type, int slot){
    super(name, 0, 0);
    this.type = type;
    this.armor = armor;
    this.slot = slot;
  }

  public Armor getObj(){
    return this;
  }
  public double getArmor(){
    return armor;
  }

  public int getType(){
    return type;
  }

  public int getSlot(){
    return slot;
  }

  public String getFH(){
    return "ä" + super.getName() + " " + armor + " "+ type+ " " + slot;
  }
}