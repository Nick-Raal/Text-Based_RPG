import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

import java.util.ArrayList;

import Items.*;


public class FileHandler{
  public static Enemy createEnemy(String path){
    String s = "";
    try{
        List<String> lines = Files.readAllLines(Paths.get(path));
        Random random = new Random();
        int randomIndex = random.nextInt(lines.size());
        s = lines.get(randomIndex);
    }catch(Exception e){
      System.out.println(e);
    }

    //find the basic qualities of an enemy
    String name = s.substring(0, s.indexOf(" "));
    s = s.substring(s.indexOf(" ") + 1);
    name = name.replaceAll("_", " ");
    double health = Double.parseDouble(s.substring(0, s.indexOf(" ")));
    s = s.substring(s.indexOf(" ") + 1);
    double init = Double.parseDouble(s.substring(0, s.indexOf(" ")));
    s = s.substring(s.indexOf(" ") + 1);
    double atkMod = Double.parseDouble(s.substring(0, s.indexOf(" ")));
    s = s.substring(s.indexOf(" ") + 1);
    StringHandler sh = new StringHandler (s);
    int k = 0;

    //count the number of weapons;
    for(int i = 0; i < s.length(); i++){
      if(s.charAt(i) == 'ẅ'){
        k++;
      }else if(s.charAt(i) == 'ä'){
        break;
      }
    }

    //create an array to hold all the enemies' weapons
    Weapon[] attack = new Weapon[k];
    k = 0;
    
    while(k < attack.length){
      attack[k] = (Weapon)createItemS(sh);
      k++;
    }

    //create an array to hold armor
    Armor[] armor = new Armor[4];
    k = 0;
    while (k<4){
      armor[k] = (Armor)createItemS(sh);
      k++;
    }
    s = sh.getString();
    
    //drops system
    double gold = Double.parseDouble(s.substring(0, s.indexOf(" ")));
    s = s.substring(s.indexOf(" ") + 1);
    double exp = Double.parseDouble(s.substring(0, (s.indexOf(" ") != -1 ? s.indexOf(" ") : s.length())));
   

    if(s.length() > 2){
      s = s.substring(s.indexOf(" ") + 1);
      ArrayList<Item> drops = new ArrayList<Item>();
      sh.setString(s);
      while(sh.getString().indexOf("ḃ") != -1 || sh.getString().indexOf("ẅ") != -1 || sh.getString().indexOf("Ṗ") != -1){
        drops.add(createItemS(sh));
      }

      //array that holds the drop chances
      double[] dropC = new double[drops.size()];
      k = 0;
      s = sh.getString();
      while(k < dropC.length){
        dropC[k] = Double.parseDouble(s.substring(0, (s.indexOf(" ") != -1 ? s.indexOf(" ") : s.length())));
        s = s.substring((s.indexOf(" ") != -1 ? s.indexOf(" ") + 1 : 0));
        k++;
      }

      return new Enemy(name, health, init, atkMod, attack, armor, gold, exp, drops, dropC);
    }else{
      //this may return an error
      return new Enemy(name, health, init, atkMod, attack, armor, gold, exp);
    }
  }
  public static Item createItem(String path){
    String s = "";
    try{
      List<String> lines = Files.readAllLines(Paths.get(path));
      Random random = new Random();
      int randomIndex = random.nextInt(lines.size());
      s = lines.get(randomIndex);
    }catch(Exception e){
      // System.out.println(e);
    }
    
    return createItemS(new StringHandler(s));
  }

  public static Item createItemS(StringHandler data){
    String s = data.getString();
    if(s.charAt(0) == 'ẅ'){
      String wName = s.substring(1, s.indexOf(" "));
      s = s.substring(s.indexOf(" ") + 1);
      wName = wName.replaceAll("_", " ");
      double wDamage = Double.parseDouble(s.substring(0, s.indexOf(" ")));
      s = s.substring(s.indexOf(" ") + 1);
      int wType = Integer.parseInt(s.substring(0, s.indexOf(" ")));
      s = s.substring(s.indexOf(" ") + 1);
      int rarity = Integer.parseInt(s.substring(0, s.indexOf(" ")));
      s = s.substring(s.indexOf(" ") + 1);
      int value = Integer.parseInt(s.substring(0, (s.indexOf(" ") != -1 ? s.indexOf(" ") : s.length())));
      s = s.substring(s.indexOf(" ") + 1);
      String demo = s.substring(0,(s.indexOf(" ") != -1 ? s.indexOf(" ") : s.length()));
      s = s.substring(s.indexOf(" ") + 1);
      data.setString(s);
      return new Weapon(wName, rarity, value, wDamage, wType, demo);
    }else if(s.charAt(0) == 'Ṗ'){
      String name = s.substring(1, s.indexOf(" "));
      s = s.substring(s.indexOf(" ") + 1);
      name = name.replaceAll("_", " ");
      int value = Integer.parseInt(s.substring(0, s.indexOf(" ")));
      s = s.substring(s.indexOf(" ") + 1);
      int rarity = Integer.parseInt(s.substring(0, s.indexOf(" ")));
      s = s.substring(s.indexOf(" ") + 1);
      double healthE = Double.parseDouble(s.substring(0, s.indexOf(" ")));
      s = s.substring(s.indexOf(" ") + 1);
      double strE = Double.parseDouble(s.substring(0, s.indexOf(" ")));
      s = s.substring(s.indexOf(" ") + 1);
      double manaE = Double.parseDouble(s.substring(0, (s.indexOf(" ") != -1 ? s.indexOf(" ") : s.length())));
      data.setString(s);
      return new Potion(name, value, rarity, healthE, strE, manaE);
    }else if(s.charAt(0) == 'ḃ'){
      String name = s.substring(1, s.indexOf(" "));
      s = s.substring(s.indexOf(" ") + 1);
      name = name.replaceAll("_", " ");
      int value = Integer.parseInt(s.substring(0, s.indexOf(" ")));
      s = s.substring(s.indexOf(" ") + 1);
      int rarity = Integer.parseInt(s.substring(0, s.indexOf(" ")));
      s = s.substring(s.indexOf(" ") + 1);
      double healthE = Double.parseDouble(s.substring(0, s.indexOf(" ")));
      s = s.substring(s.indexOf(" ") + 1);
      double strE = Double.parseDouble(s.substring(0, s.indexOf(" ")));
      s = s.substring(s.indexOf(" ") + 1);
      double manaE = Double.parseDouble(s);
      s = s.substring(s.indexOf(" ") + 1);
      String body = s.substring(0, (s.indexOf(" ") != -1 ? s.indexOf(" ") : s.length()));
      body = body.replaceAll("_", " ");
      data.setString(s);
      return new Book(name, value, rarity, healthE, strE, manaE, body);
    }else if(s.charAt(0) == 'ä'){
      String aName = s.substring(1, s.indexOf(" "));
      s = s.substring(s.indexOf(" ") + 1);
      double aArmor = Double.parseDouble(s.substring(0, s.indexOf(" ")));
      s = s.substring(s.indexOf(" ") + 1);
      int aType = Integer.parseInt(s.substring(0, (s.indexOf(" ") != -1 ? s.indexOf(" ") : s.length())));
      s = s.substring(s.indexOf(" ") + 1);
      int aSlot = Integer.parseInt(s.substring(0, s.indexOf(" ")));
      s =s.substring(s.indexOf(" ") + 1);
      int value = Integer.parseInt(s.substring(0, s.indexOf(" ")));
      s =s.substring(s.indexOf(" ") + 1);
      int rarity = Integer.parseInt(s.substring(0, (s.indexOf(" ") != -1 ? s.indexOf(" ") : s.length())));
      s = s.substring((s.indexOf(" ") != -1 ? s.indexOf(" ") + 1 : 0));
      data.setString(s);
      return new Armor(aName.replaceAll("_", " "), aArmor, aType, aSlot, value, rarity);
    }
    return null;
  }

  public static Item createItemS(String s){
    return createItemS(new StringHandler(s));
  }

  public static String nameVillage(String path){
    String s = "";
      try{
        List<String> lines = Files.readAllLines(Paths.get(path));
        Random random = new Random();
        int randomIndex = random.nextInt(lines.size());
        s = lines.get(randomIndex);
      }catch(Exception e){
        System.out.println(e);
      }

      return s;
  }

  //ratio is equivalent to enemy damage taken / enemy health
  public static String atkMessage(String path, double ratio){
    String s = "";
    //the greatest line that will be retrieved
    double maxRand = 1;
    if (ratio <= 0.25){
      maxRand = 0.5;
    }
    try{
      List<String> lines = Files.readAllLines(Paths.get(path));
      Random random = new Random();
      int randomIndex = random.nextInt((int)(lines.size() *  maxRand));
      s = lines.get(randomIndex);
    }catch(Exception e){
      System.out.println("ATK MESSAGE " + e);
    }
    return s;
  }
}

