package Items;

public class Item{

  private String name;
  private int value;
  private int rarity;


  public Item(String name, int value, int rarity){
    this.name = name;
    this.value = value;
    this.rarity = rarity;
  }


  public String getName(){
    return name;
  }

  public int getValue(){
      return value;
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
    System.out.println("NOTHING HAPPENED");
    return null;
  }

  public String toString(){
    return name + " " + value + " " + rarity;
  }

  public int getRarity() {
    return rarity;
  }

  public String getFH(){
    return name + " " + value + " " + rarity;
  }
}