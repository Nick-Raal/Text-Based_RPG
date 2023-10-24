import Items.*;

import java.util.*;

public class Enemy{
  private String name;
  private double health;
  private Weapon attack[];
  private double initiative = 0;
  private double atkMod = 1;
  private Armor[] armor;
 private Item[] drops;
  private double[] dropC;
  private int dropG;
  private int dropE;

  public Enemy(String name, double health, double initiative, double atkMod){
    this.name = name;
    this.health = health;
    this.initiative = initiative;
    this.atkMod = atkMod;
  }

  public Enemy(String name, double health, double initiative, double atkMod, Weapon[] attack, Armor[] armor){
    this.name = name;
    this.health = health;
    this.initiative = initiative;
    this.atkMod = atkMod;
    this.attack = attack;
    this.armor = armor;
  }

  public Enemy(String name, double health, double initiative, double atkMod, EnemyWeapon[] attack, Armor[] armor, double dropG, double dropE, ArrayList<Item> drops, double[] dropsC){
    this.name = name;
    this.health = health;
    this.initiative = initiative;
    this.atkMod = atkMod;
    this.attack = attack;
    this.armor = armor;
    this.dropG = (int)dropG;
    this.dropE = (int)dropE;
    this.drops = new Item[drops.size()];
    for(int i =0; i< drops.size(); i++){
      this.drops[i] = drops.get(i);
    }
    this.dropC = dropsC;
  }

  public Enemy(String name, double health, double initiative, double atkMod, EnemyWeapon[] attack, Armor[] armor, double dropG, double dropE){
    this.name = name;
    this.health = health;
    this.initiative = initiative;
    this.atkMod = atkMod;
    this.attack = attack;
    this.armor = armor;
    this.dropG = (int)dropG;
    this.dropE = (int)dropE;
    this.drops = null;
    this.dropC = null;
  }
  public double[] atk(int select){
    double[] attack = new double[2];
    attack[0] =  this.attack[select].getDamage() * atkMod;
    attack[1] = this.attack[select].getType();
    return attack;
  }
  
  public double damage(double atk, int type, boolean ignoreArmor){

    //the total amount of damage that the enemy has taken;
    double total = 0;
    if(ignoreArmor){
      health -= atk;
      return atk;
    }else{
      for(int i = 0; i < armor.length; i++){
        double damage = Math.max(0, armor[i].getType() == type ? atk/3 - 1.5 * armor[i].getArmor() : atk/3 - 1.5*armor[i].getArmor());
        health -= damage;
        total += damage;
      }
      return total;
    }
  }

  public double getInitiative(){
    return initiative;
  }

  public String toString(){

    // String s = "";
    // for(int i = 0; i < attack.length; i++){
    //   s += (i+1) + ": " + attack[i].getName() + "\n";
    // }
    // s += "Armor\n" ;
    // for(int i = 0; i < armor.length; i++){
    //   s += (i+1) + ": " + armor[i].getName() +" "+ armor[i].getArmor() + " " +armor[i].getType() +"\n";
    // }
    
    
    return name + " (HP: " + health + ")";
    
  }

  public String getName(){
    return name;
  }

  public double getHealth() {
    return health;
  }

  public ArrayList<Item> die(){
    if(drops == null){
      return null;
    }
    ArrayList<Item> d = new ArrayList<Item>();
    System.out.println(name + " was vanquished");
    Random r = new Random();
    int n = r.nextInt(99) + 1;
    int running = 0;
    for(int i = 0; i < drops.length; i++){
      if(n >= (running) && n < dropC[i] * 100 + (running)){
        d.add(drops[i]);
      }
      running += dropC[i]*100;
    }
    return d;
  }
  //returns exp and gold from an enemy's death
  public double[] numDeath(){
    Random r = new Random();
    double[] numD = {(r.nextDouble() / 2 + 0.75) * dropG, (r.nextDouble() / 2 + 0.75) * dropE};
    return numD;
  }


    public int getAtkL() {
      return attack.length;
    }
}