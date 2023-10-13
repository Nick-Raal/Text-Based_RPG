import Items.Armor;
import Items.Item;
import Items.Weapon;

import java.util.*;

public class Enemy{
  private String name;
  private double health;
  private Weapon attack[];
  private double initiative = 0;
  private double atkMod = 1;
  private Armor[] armor;
  private Item[] drops = {new Item("magic potion of ultimate magic", 150, 3, 0, 0, 100)};
  private double[] dropC = {1.0};
  private int dropG = 100;
  private int dropE = 10000;

  public Enemy(String name, double health, double initiative, double atkMod){
    this.name = name;
    this.health = health;
    this.initiative = initiative;
    this.atkMod = atkMod;
  }

  
  
  public double[] Attack(int select){
    double[] attack = new double[2];
    attack[0] =  this.attack[select].getDamage() * atkMod;
    attack[1] = this.attack[select].getType();
    return attack;
  }
  
  public void damage(double atk, int type, boolean ignoreArmor){
    if(ignoreArmor){
      health -= atk;
    }else{
      for(int i = 0; i < armor.length; i++){
        health -= Math.max(0, armor[i].getType() == type ? atk/3 - 1.5 * armor[i].getArmor() : atk/3 - 1.5*armor[i].getArmor());
      }
    }
  }

  public double getInitiative(){
    return initiative;
  }

  public String toString(){
    return name + " (HP:" + health + ") ";
  }

  public String getName(){
    return name;
  }

  public double getHealth() {
    return health;
  }

  public ArrayList<Item> die(){
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

  
}