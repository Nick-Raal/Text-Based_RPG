import Items.*;
import java.io.*;
import java.util.Collections;
import java.util.Scanner;

import java.util.ArrayList;
import java.util.List;

public class Player{


  File file;
  // private int x = 0;
  // private int y = 0;
  private String name;
  private boolean in;
  private ArrayList<Item> inventory = new ArrayList<Item>();
  private double health;
  private double healthMax = 50;
  private double atkMod = 1;
  private double defMod = 1;
  private double initiative = 1;
  
  private int level = 1;
  private int gold = 100;
  private long exp = 0;

  //the maximum strength and mana a player can have
  private double strMax = 10;
  private double manaMax = 10;

  //how much strength and mana the player instantaneously has
  private double str = 10;
  private double mana = 10;
  //how much the player recovers each turn
  private double manaR = 2;
  private double strR = 2;
  
  //head, torso, legs
  //bludgeon, piercing, magic
  private Armor[] armor = new Armor[4];

  //the game's difficulty
  private double difficulty = 1;

  public Player(String namey){
    try{
      file = new File("player.dat");
      if(file.createNewFile()){
        name = namey;
        inventory.add(new Weapon("Training Sword", 1, 5, 10, 2, 0, 3, "the"));
        //create default armor
        armor[0] = new Armor("Training Helm", 1, 1, 0, 5, 1);
        armor[1] = new Armor("Training Chestplate", 1 , 1, 1, 5, 1);
        armor[2] = new Armor("Training Leggings", 1 , 1, 2, 5, 1);
        armor[3] = new Armor("Training Boots", 1 , 1, 3, 5, 1);
        update();
      }else{
        initialize();
      }
      // armor[0] = new Armor("Training Helm", 5 , 1, 0, 5, 1);
      // armor[1] = new Armor("Training Chestplate", 5 , 1, 1, 5, 1);
      // armor[2] = new Armor("Training Leggings", 5 , 1, 2, 5, 1);
      // armor[3] = new Armor("Training Boots", 5 , 1, 30, 5, 1);
    }catch(Exception e){
      System.out.println(e);
    }
    

    //starting items
//    inventory.add(new Weapon("Training Sword", 1, 5, 5, 2, "the"));
    
    //test items
//    inventory.add(new Weapon("Zorg, The Destroyer of Fools", 450, 3, 35, 3, 3, 5, ""));
//    inventory.add( new Weapon("Adamantium Spear", 450, 2, 25, 1,3 ,0, "the"));
////    inventory.add(new Item("Potion of Major Healing", 50, 2, 10, 0, 0));
//    inventory.add(new Potion("Root of Fervor", 10, 1, 0, 5, 0));
//    inventory.add( new Weapon("relief", 450, 2, 250, 1,2 ,2, ""));
//    inventory.add(new Armor("Armoury Chestplate", 29, 2, 1));
//    armor = new Armor[]{new Armor("Chain Helm", 5, 1, 0), new Armor("Chain Chestplate", 5, 1, 1), new Armor("Chain Leggings", 5, 1, 2), new Armor("Chain Boots", 5, 1, 3)};
  
    full();
  }

  public void recover(){
    str += strR;
    mana += manaR;
  }

  public void updater(){
    update();
  }

  //displays all the player's possible attacks
  public void dispAtk(){
    System.out.println("Attacks:");
    int n = 0;
    List<Weapon> attacks = new ArrayList<Weapon>();
    for(int i = 0; i < inventory.size(); i++){
      if(inventory.get(i) instanceof Weapon){
        if(!attacks.contains(inventory.get(i))){
          n++;
          System.out.print(Color.RESET + n + ":");
              System.out.println((((Weapon)inventory.get(i)).getType() == 3 ? Color.PURPLE : ((Weapon)inventory.get(i)).getType() == 2 ? Color.RED : ((Weapon)inventory.get(i)).getType() == 1 ? Color.YELLOW : Color.RESET) + " " + inventory.get(i).getName());
              System.out.print(Color.RED + "\tDMG: " + ((Weapon)inventory.get(i)).getDamage());
              System.out.print((((Weapon)inventory.get(i)).getStr() != 0 ? Color.RESET + " | " + Color.YELLOW + "STR: " + ((Weapon)inventory.get(i)).getStr()+ Color.RESET + " | " : "") );
          System.out.print(((Weapon)inventory.get(i)).getStr() == 0 && ((Weapon)inventory.get(i)).getMana() != 0 ? Color.RESET + " | " : "");
              System.out.println(((Weapon)inventory.get(i)).getMana() != 0 ? Color.CYAN + "MANA: "+((Weapon)inventory.get(i)).getMana() : "");
          attacks.add((Weapon)inventory.get(i));
        }
      }
    }
    System.out.println("\n" + Color.YELLOW + "STR: " + str + Color.RESET + " | " + Color.CYAN + "MANA: " + mana + Color.RESET);
  }

  //first value is damage, second is type
  public double[] Attack(int select){
    double[] attack = new double[2];
    str -= (getWeapon(select)).getStr();
    mana -= (getWeapon(select)).getMana();
    attack[0] = (getWeapon(select)).getDamage() * atkMod;
    attack[1] = (getWeapon(select)).getType();
    return attack;
  }

  public double damage(double atk, int type, boolean ignoreArmor){
    if(ignoreArmor){
      health -= atk * 4;
      return atk;
    }else{
      double total = 0;
      for(int i = 0; i < armor.length; i++){
        health -= Math.max(0, armor[i].getType() == type ? atk/4 - 1.5 * armor[i].getArmor() *defMod: atk/4 - 1.0*armor[i].getArmor()*defMod);
        total += Math.max(0, armor[i].getType() == type ? atk/4 - 1.5 * armor[i].getArmor() *defMod: atk/4 - 1.0*armor[i].getArmor()*defMod);
      }
      return total;
    }
  }

  //need to improve to include new item types
  public void dispItm(){
    // System.out.println(inventory.get(0).getName() + " " + !(inventory.get(0) instanceof Weapon));
    System.out.println(Color.RESET+ "Items: ");
    int k = 0;
    for(int i = 0; i < inventory.size(); i++){
      if(!(inventory.get(i) instanceof Weapon) && !(inventory.get(i) instanceof Armor)){
        k++;
        System.out.println(Color.RESET + (k) + ": " + inventory.get(i).getName());
        if(inventory.get(i) instanceof Potion || inventory.get(i) instanceof Book){

          System.out.print("\t" + (((Potion)inventory.get(i)).use()[0] != 0 ? Color.RED + "HP: " + ((Potion)inventory.get(i)).use()[0] : ""));
          System.out.print(Color.RESET + (((Potion)inventory.get(i)).use()[0] != 0 ? " | " : "") + Color.YELLOW + (((Potion)inventory.get(i)).use()[1] != 0 ? " STR: " + ((Potion)inventory.get(i)).use()[1] : ""));
          // System.out.print(((Color.RESET + ((((Potion)inventory.get(i)).use()[0] != 0 && ((Potion)inventory.get(i)).use()[1] != 0 ? " | " : "") + Color.CYAN + ((Potion)inventory.get(i)).use()[2] != 0 ? " MANA: " + ((Potion)inventory.get(i)).use()[2] : "")));
          if(inventory.get(i) instanceof Book){
            System.out.print(((Book)inventory.get(i)).getBody());
          }
        }
      }
      System.out.println();
    }
    System.out.print(Color.RESET);
  }

  public void dispInven(){
    for(int i = 0; i < inventory.size(); i++){
      System.out.println((i + 1) + ": " + inventory.get(i));
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
    List<Weapon> attacks = new ArrayList<Weapon>();
    for(int i = 0; i < inventory.size(); i++){
      if((inventory.get(i) instanceof Weapon) && !attacks.contains((Weapon)inventory.get(i))){
        if(k == select){
          select = i;
          break;
        }
        attacks.add((Weapon)inventory.get(i));
        // System.out.println("running: " + k);
        k++;
      }
    }
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
    inventory.addAll(items);
  }
  public void add(Item[] items){
    Collections.addAll(inventory, items);
  }
  public void addG(int g){
    gold += g;
  }
  public void addE(int e){
    exp += e;
    if(exp >= (4 * Math.pow(level, 3))/5){
      //levelup
      atkMod += 0.05;
      defMod += 0.05;
      initiative += 0.05;
      Graphics g = new Graphics();
      System.out.println(g.fancyWord("LVL UP"));
      //every three levels, increase strength and mana recovery
      if(level % 3 == 0){
        strR++;
        manaR++;
        System.out.println("NEW MANA RECOVERY: "+strR+" | NEW MANA RECOVERY: " + manaR);
      }
      //every two levels, increasse max health
      if(level % 2 == 0){
        healthMax += 5;
        System.out.println("NEW HEALTH MAX: " + healthMax);
      }
      level++;
      exp = 0;
      dispLvl();
    }
  }

  public void dispLvl(){
    System.out.println(Color.RESET + "LVL: " + level);
    System.out.println(Color.BLUE + "EXP: " + exp);
    System.out.println(Color.RESET + "PROGRESS:");
    for(int i = 0; i < 10; i++){
      if(i<(int)(((4 * Math.pow(level, 3))/5)/exp)){
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

    if(inventory.get(select) instanceof Potion || inventory.get(select) instanceof Book){
      health += inventory.get(select).use()[0];
      str += inventory.get(select).use()[1];
      mana += inventory.get(select).use()[2];
      inventory.remove(select);
    }
  }

  public void equip(int select){
    int k = 0;
    for(int i = 0; i < inventory.size(); i++){
      if(inventory.get(i) instanceof Armor) {
        if (k == select) {
          select = i;
          break;
        }
        k++;
      }
    }
    Armor arm = (Armor)inventory.get(select);
    Armor temp = armor[arm.getSlot()];
    armor[arm.getSlot()] = arm;
    inventory.add(temp);
    inventory.remove(select);
  }

  public void dispArm(){
    System.out.println(Color.RESET + "ARMOR:");
    int k = 0;
    for(int i = 0; i < inventory.size(); i++) {
      if (inventory.get(i) instanceof Armor) {
        k++;
        System.out.println(Color.RESET + (k) + ": "+ Color.BLUE + ((((Armor) inventory.get(i)).getSlot() == 0) ? "Head: " : (((Armor) inventory.get(i)).getSlot() == 1 ? "Torso: " : (((Armor) inventory.get(i)).getSlot() == 2 ? "Legs: " : (((Armor) inventory.get(i)).getSlot() == 3 ? "Feet: " : "")))) + ((Armor) inventory.get(i)).getName() + " | AMR: " + ((Armor) inventory.get(i)).getArmor() + " | TYPE: " + (((Armor) inventory.get(i)).getType() == 1 ? "bludgeon" : (((Armor) inventory.get(i)).getType() == 2 ? "piercing" : (((Armor) inventory.get(i)).getType() == 3 ? "magic" : ""))));
      }
    }
    System.out.println(Color.RESET);
  }
  public void dispCArm(){
    System.out.println(Color.RESET + "Currently Equipped Armor:");
    System.out.println("Total Armor: " + (armor[0].getArmor() + armor[1].getArmor() + armor[2].getArmor() + armor[3].getArmor()));
    for(int i = 0; i < armor.length; i++){
      System.out.println(Color.BLUE + (i == 0 ? "Head: " : (i== 1 ? "Torso: " : (i == 2 ? "Legs: " : (i == 3 ? "Feet: " : ""))))  + armor[i].getName() + " | AMR: " + armor[i].getArmor() + " | TYPE: " + (armor[i].getType() == 1 ? "bludgeon" : (armor[i].getType() == 2 ? "piercing" : (armor[i].getType() == 3 ? "magic" : ""))));
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

    str = Math.max(str, strMax);
    mana = Math.max(mana, manaMax);
    health = Math.max(health, healthMax);
  }

  public int getArmorL() {
    int k = 0;
    for(int i = 0; i < inventory.size(); i++){
      if(inventory.get(i) instanceof Armor){
        k++;
      }
    }
    return k;
  }

  public void initialize(){
    try{
      Scanner s = new Scanner(file);
      name = s.nextLine();
      health = Double.parseDouble(s.nextLine());
      healthMax = Double.parseDouble(s.nextLine());
      atkMod = Double.parseDouble(s.nextLine());
      defMod = Double.parseDouble(s.nextLine());
      initiative = Double.parseDouble(s.nextLine());
      level = Integer.parseInt(s.nextLine());
      gold = Integer.parseInt(s.nextLine());
      exp = Long.parseLong(s.nextLine());
      strMax = Double.parseDouble(s.nextLine());
      manaMax = Double.parseDouble(s.nextLine());
      str = Double.parseDouble(s.nextLine());
      mana = Double.parseDouble(s.nextLine());
      strR = Double.parseDouble(s.nextLine());
      manaR = Double.parseDouble(s.nextLine());
      difficulty = Double.parseDouble(s.nextLine());

      armor[0] = (Armor)FileHandler.createItemS(s.nextLine());
      armor[1] = (Armor)FileHandler.createItemS(s.nextLine());
      armor[2] = (Armor)FileHandler.createItemS(s.nextLine());
      armor[3] = (Armor)FileHandler.createItemS(s.nextLine());

      //create a check system to cast objects
      while(s.hasNext()){
        Object itm =FileHandler.createItemS(s.nextLine());
        
        if(itm instanceof Weapon){
          inventory.add((Weapon)itm);
        }else if(itm instanceof Potion){
          inventory.add((Potion)itm);
        }else if(itm instanceof Armor){
          inventory.add((Armor)itm);
        }else if(itm instanceof Book){
          inventory.add((Book)itm);
        }else{
          inventory.add((Item)itm);
        }
      }
      s.close();
    }catch(Exception e){
      System.out.println("initialization error " + e);
    }
  }
  public void update(){
    try{
      FileWriter fw = new FileWriter(file, false);
      fw.write(name + "\n");
      fw.write(health + "\n");
      fw.write(healthMax + "\n");
      fw.write(atkMod + "\n");
      fw.write(defMod + "\n");
      fw.write(initiative + "\n");
      fw.write(level + "\n");
      fw.write(gold + "\n");
      fw.write(exp + "\n");
      fw.write(strMax + "\n");
      fw.write(manaMax + "\n");
      fw.write(str + "\n");
      fw.write(mana + "\n");
      fw.write(strR + "\n");
      fw.write(manaR + "\n");
      fw.write(difficulty + "\n");
      fw.write(armor[0].getFH() + "\n");
      fw.write(armor[1].getFH() + "\n");
      fw.write(armor[2].getFH() + "\n");
      fw.write(armor[3].getFH() + "\n");
      for(int i = 0; i < inventory.size(); i++){
        fw.write(inventory.get(i).getFH() + (i < inventory.size() - 1 ? "\n" : ""));
      }
      fw.close();
    }catch(Exception e){
      System.out.println("PLAY ERR " + e.getMessage());
    }
  }

  public File getFile() {
    return file;
  }

  public void setName(String nextLine) {
    name = nextLine;
  }

  public double getDifficulty(){
    return difficulty;
  }
}