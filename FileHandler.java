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
    // System.out.println(sh.getString());
    int k = 0;
    for(int i = 0; i < s.length(); i++){
      if(s.charAt(i) == 'ẅ'){
        k++;
      }else if(s.charAt(i) == 'ä'){
        break;
      }
    }
    // System.out.println("k: " + k);
    Weapon[] attack = new Weapon[k];
    k = 0;
    
    while(k < attack.length){
      attack[k] = (Weapon)createItemS(sh);
      k++;
    }
    // System.out.println(sh.getString());
    // for(int i = 0; i < s.length(); i++){
    //   if(s.charAt(i) == 'ẅ'){
    //     String wName = s.substring(i+1, s.indexOf(" "));
    //     s = s.substring(s.indexOf(" ") + 1);
    //     double wDamage = Double.parseDouble(s.substring(0, s.indexOf(" ")));
    //     s = s.substring(s.indexOf(" ") + 1);
    //     int wType = Integer.parseInt(s.substring(0, s.indexOf(" ")));
    //     s = s.substring(s.indexOf(" ") + 1);
    //     attack[k] = new Weapon(wName, wDamage, wType);
    //     k++;
    //     i = 0;
    //   }
    // }
    Armor[] armor = new Armor[4];
    k =0;
    while (k<4){

      // System.out.println("ind:" + s.indexOf(" ") + " i: " + i);
      // String aName = s.substring(1, s.indexOf(" "));
      // s = s.substring(s.indexOf(" ") + 1);
      // double aArmor = Double.parseDouble(s.substring(0, s.indexOf(" ")));
      // s = s.substring(s.indexOf(" ") + 1);
      // int aType = Integer.parseInt(s.substring(0, (s.indexOf(" ") != -1 ? s.indexOf(" ") : s.length())));
      // s = s.substring(s.indexOf(" ") + 1);
      
      // armor[k] = new Armor(aName, aArmor, aType);
      armor[k] = (Armor)createItemS(sh);
      k++;
    }
    s = sh.getString();
    //create a system to account for drops
    // System.out.println(Color.RED + s);
    double gold = Double.parseDouble(s.substring(0, s.indexOf(" ")));
    s = s.substring(s.indexOf(" ") + 1);
    double exp = Double.parseDouble(s.substring(0, s.indexOf(" ")));
    s = s.substring(s.indexOf(" ") + 1);
    
    ArrayList<Item> drops = new ArrayList<Item>();
    sh.setString(s);
    while(sh.getString().indexOf("ḃ") != -1 || sh.getString().indexOf("ẅ") != -1 || sh.getString().indexOf("Ṗ") != -1){
      drops.add(createItemS(sh));
    }
    // System.out.println(s);
    return new Enemy(name, health, init, atkMod, attack, armor, gold, exp, drops);
  }
  public static Item createItem(String path){
    String s = "";
    try{
      List<String> lines = Files.readAllLines(Paths.get(path));
      Random random = new Random();
      int randomIndex = random.nextInt(lines.size());
      s = lines.get(randomIndex);
    }catch(Exception e){
      System.out.println(e);
    }
    
    return createItemS(new StringHandler(s));
  }

  public static Item createItemS(StringHandler data){
    String s = data.getString();
    if(s.charAt(0) == 'ẅ'){
      String wName = s.substring(1, s.indexOf(" "));
      s = s.substring(s.indexOf(" ") + 1);
      // System.out.println(s);
      wName = wName.replaceAll("_", " ");
      double wDamage = Double.parseDouble(s.substring(0, s.indexOf(" ")));
      s = s.substring(s.indexOf(" ") + 1);
        // System.out.println(s);
      int wType = Integer.parseInt(s.substring(0, s.indexOf(" ")));
      s = s.substring(s.indexOf(" ") + 1);
        // System.out.println(s);
      try{
        int rarity = Integer.parseInt(s.substring(0, s.indexOf(" ")));
        s = s.substring(s.indexOf(" ") + 1);
        // System.out.println(s);
        int value = Integer.parseInt(s.substring(0, (s.indexOf(" ") != -1 ? s.indexOf(" ") : s.length())));
        s = s.substring(s.indexOf(" ") + 1);
        String demo = s.substring(0,(s.indexOf(" ") != -1 ? s.indexOf(" ") : s.length()));
        data.setString(s);
        return new Weapon(wName, rarity, value, wDamage, wType, demo);
      }catch(NumberFormatException e){
        data.setString(s);
        return new Weapon(wName, wDamage, wType);
      }
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
      data.setString(s);
      try{
        int aSlot = Integer.parseInt(s.substring(0, s.indexOf(" ")));
        s =s.substring(s.indexOf(" ") + 1);
        data.setString(s);
        return new Armor(aName, aArmor, aType, aSlot);
      }catch(Exception e){
        return new Armor(aName, aArmor, aType);
      }
    }
    return null;
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
}