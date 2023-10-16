import Items.*;


import java.util.ArrayList;

public class Player{

  // private int x = 0;
  // private int y = 0;
  private String name = "Nemo";
  private boolean in;
  private ArrayList<Item> inventory = new ArrayList<Item>();
  private double health;
  private double healthMax = 100;
  private double atkMod = 1;
  private double defMod = 1;
  private double initiative = 1;
  
  private int level = 1;
  private int gold = 100;
  private long exp = 10;

  //the maximum strength and mana a player can have
  private double strMax = 100;
  private double manaMax = 100;

  //how much strength and mana the player instantaneously has
  private double str = 100;
  private double mana = 100;
  //how much the player recovers each turn
  private double manaR = 3;
  private double strR = 3;
  
  //head, torso, legs
  //bludgeon, piercing, magic
  private Armor[] armor;

  public Player(){
    //test items
    inventory.add(new Weapon("Zorg, The Destroyer of Fools", 450, 3, 35, 3, 3, 5, ""));
    inventory.add( new Weapon("Adamantium Spear", 450, 2, 25, 1,3 ,0, "the"));
//    inventory.add(new Item("Potion of Major Healing", 50, 2, 10, 0, 0));
    inventory.add(new Potion("Root of Fervor", 10, 1, 0, 5, 0));
    inventory.add( new Weapon("relief", 450, 2, 250, 1,2 ,2, ""));
    full();
  }

  public void recover(){
    str += strR;
    mana += manaR;
  }
  
  // //1 = up. 2 = right. 3 = down. 4 = left
  // public void move(int dist, int dir){
  //   switch(dir){
  //     case 1:
  //       y+=dist;
  //       break;
  //     case 2:
  //       x+=dist;
  //       break;
  //     case 3:
  //       y-=dist;
  //       break;
  //     case 4:
  //       x-=dist;
  //       break;
  //     default:
  //       System.out.println("ERROR");     
  //   }
  // }

  // public int getX(){
  //   return x;
  // }
  // public int getY(){/
  //   return y;
  // }

  //displays all the player's possible attacks
  public void dispAtk(){
    System.out.println("Attacks:");
    int n = 0;
    for(int i = 0; i < inventory.size(); i++){
      if(inventory.get(i) instanceof Weapon){
        n++;

        System.out.print(Color.RESET + n + ":");
        System.out.println((((Weapon)inventory.get(i)).getType() == 3 ? Color.PURPLE : ((Weapon)inventory.get(i)).getType() == 2 ? Color.RED : ((Weapon)inventory.get(i)).getType() == 1 ? Color.YELLOW : Color.RESET) + " " + inventory.get(i).getName());
        System.out.print(Color.RED + "\tDMG: " + ((Weapon)inventory.get(i)).getDamage());
        System.out.print((((Weapon)inventory.get(i)).getStr() != 0 ? Color.RESET + " | " + Color.YELLOW + "STR: " + ((Weapon)inventory.get(i)).getStr()+ Color.RESET + " | " : "") );
    System.out.print(((Weapon)inventory.get(i)).getStr() == 0 && ((Weapon)inventory.get(i)).getMana() != 0 ? Color.RESET + " | " : "");
        System.out.println(((Weapon)inventory.get(i)).getMana() != 0 ? Color.CYAN + "MANA: "+((Weapon)inventory.get(i)).getMana() : "");
      }
    }
    System.out.println("\n" + Color.YELLOW + "STR: " + str + Color.RESET + " | " + Color.CYAN + "MANA: " + mana + Color.RESET);
  }

  //first value is damage, second is type
  public double[] Attack(int select){
    double[] attack = new double[2];
    int k = 0;
    for(int i = 0; i < inventory.size(); i++){
      if((inventory.get(i) instanceof Weapon)){
        if(k == select){
          select = i;
          break;
        }
        k++;
      }
    }
    str -= ((Weapon)inventory.get(select)).getStr();
    mana -= ((Weapon)inventory.get(select)).getMana();
    attack[0] = k >= 0 ? ((Weapon)inventory.get(select)).getDamage() * atkMod : 0;
    attack[1] = ((Weapon)inventory.get(select)).getType();
    str-=((Weapon)inventory.get(select)).getStr();
    mana-=((Weapon)inventory.get(select)).getMana();
    return attack;
  }

  public void damage(double atk, int type, boolean ignoreArmor){
    if(ignoreArmor){
      health -= atk;
    }else{
      for(int i = 0; i < armor.length; i++){
        health -= Math.max(0, armor[i].getType() == type ? atk/3 - 1.5 * armor[i].getArmor() *defMod: atk/3 - 1.0*armor[i].getArmor()*defMod);
      }
    }
  }

  public void dispItm(){
    // System.out.println(inventory.get(0).getName() + " " + !(inventory.get(0) instanceof Weapon));
    System.out.println(Color.RESET+ "Items: ");
    int k = 0;
    for(int i = 0; i < inventory.size(); i++){
      if(!(inventory.get(i) instanceof Weapon) && !(inventory.get(i) instanceof Armor)){
        k++;
        System.out.println(Color.RESET + (k) + ": " + inventory.get(i).getName());
        if(inventory.get(i) instanceof Potion){

          System.out.print("\t" + (((Potion)inventory.get(i)).use()[0] != 0 ? Color.RED + "HP: " + ((Potion)inventory.get(i)).use()[0] : ""));
          System.out.print(Color.RESET + (((Potion)inventory.get(i)).use()[0] != 0 ? " | " : "") + Color.YELLOW + (((Potion)inventory.get(i)).use()[1] != 0 ? " STR: " + ((Potion)inventory.get(i)).use()[1] : ""));
          // System.out.println(((Color.RESET + (((Potion)inventory.get(i)).use()[0] != 0 && ((Potion)inventory.get(i)).use()[1] != 0 ? " | " : "") + Color.CYAN + ((Potion)inventory.get(i)).use()[2] != 0 ? " MANA: " + ((Potion)inventory.get(i)).use()[2] : "")));

        }
      }
    }
    System.out.print(Color.RESET);
  }

  public void dispInven(){
    for(int i = 0; i < inventory.size(); i++){
      System.out.println(inventory.get(i));
    }
  }
  public int getInvenL(){
    return inventory.size();
  }


  public int getItemL(){
    int k =0;
    for(int i = 0; i < inventory.size(); i++){
    if(!(inventory.get(i) instanceof Weapon) && !(inventory.get(i) instanceof Armor)){
        k++;
      }
    }
    return k;
  }
  public boolean getIn(){
    return in;
  }
  public double getInitiative(){
    return initiative;
  }

  public Weapon getWeapon(int select){
    int k = 0;
    for(int i = 0; i < inventory.size(); i++){
      if((inventory.get(i) instanceof Weapon)){
        if(k == select){
          select = i;
          break;
        }
        // System.out.println("running: " + k);
        k++;
      }
    }
    // System.out.println(select);
    // System.out.println(inventory);
    // System.out.println(k);
    return (Weapon)inventory.get(select);
  }

  public int getWeaponL(){
    int k = 0;
    for(int i = 0; i < inventory.size(); i++){
      if(inventory.get(i) instanceof Weapon){
        k++;
      }
    }
    return k;
  }
  public double getStr(){
    return str;
  }
  public double getMana(){
    return mana;
  }
  public void add(Item item){
    inventory.add(item);
  }
  public void add(ArrayList<Item> items){
    for(int i = 0; i <items.size(); i++){
      inventory.add(items.get(i));
    }
  }
  public void add(Item[] items){
    for(int i = 0; i < items.length; i++){
      inventory.add(items[i]);
    }
  }
  public void addG(int g){
    gold += g;
  }
  public void addE(int e){
    exp += e;
    if(((int)Math.log(1+(Math.E -1) * exp)) / level > level){
      //levelup
      atkMod += 0.05;
      defMod += 0.05;
      initiative += 0.05;
      level++;
      exp = 0;
      Graphics g = new Graphics();
      System.out.println(g.fancyWord("LVL UP"));
      dispLvl();
    }
  }

  public void dispLvl(){
    System.out.println(Color.RESET + "LVL: " + level);
    System.out.println(Color.BLUE + "EXP: " + exp);
    System.out.println(Color.RESET + "PROGRESS:");
    for(int i = 0; i < 10; i++){
      if(i<(Math.log(1+(Math.E -1) * exp) / level)*10){
        System.out.print(Color.GREEN + "■");
      }else{
        System.out.print(Color.RED + "■");
      }
    }
    System.out.println(Color.RESET);
  }
  
  public void useItem(int select){
    int k = 0;
    for(int i = 0; i < inventory.size(); i++){
      if(!(inventory.get(i) instanceof Weapon) && !(inventory.get(i) instanceof Armor)){

        if(k == select){
          select = i;
          break;
        }
        k++;
      }
    }
    // System.out.println(inventory.get(select));
    // System.out.println(select);
    if(inventory.get(select) instanceof Potion || inventory.get(select) instanceof Book){
      health += inventory.get(select).use()[0];
      str += inventory.get(select).use()[1];
      mana += inventory.get(select).use()[2];
      inventory.remove(select);
    }
  }

  public String toString(){
    return name + "\n" + Color.RED + "HP: " + health+ "\n" + Color.YELLOW + "STR: " + str + Color.RESET + " | " + Color.CYAN + "MANA: " + mana + Color.RESET;
  }

    public int getGold() {
      return gold;
    }

  public Item getItem(int i) {
    return inventory.get(i);
  }

  public void removeItem(int i) {
    inventory.remove(i);
  }

  public void full() {

    str = str <= strMax ? strMax : str;
    mana = mana <= manaMax ? manaMax : mana;
    health = health <= healthMax ? healthMax : health;
  }
}