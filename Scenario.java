import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
public class Scenario{
  //the goal of this class is to create a system whereby a place can return a simple situation that can be turned into a battle;
  //furthermore, this could be used to handle other place scenarios, like loot and unique things

  Player p;
  private ArrayList<Village> vlgs = new ArrayList<Village>();
  private File vlgFile = new File("vlgs.dat");
  //possible scenarios:
  //1. Battle
  //2. Village
  //3. Loot
  //4. Unique

  double[] scenarioChancesF = {0.2, 0, 0, 0};
  double[] scenarioChancesG = {0.2, 0.0, 0, 0};
  double[] scenarioChancesE = {0.5, 0.0, 0.0, 0};
  double[] scenarioChancesM = {0.3, 0, 0, 0};
  double[] scenarioChancesC = {0, 0, 0, 0};
  //placeholder where ocean chances would be stored
  double[] scenarioChancesV = {0, 1.0, 0, 0};

  public Scenario(Player p){
    this.p = p;
    initializeList();
  }

  //this method should return what type of encounter occurs, a different method will handle the internals of the encounter
  public Object scenario(Player p, int tileType, double difficulty, Place play){
    
    Random r = new Random();
    int n = r.nextInt(99) + 1;
    int running = 0;
    if(tileType == 1){
      for(int k = 0; k < scenarioChancesF.length; k++){
        if(scenarioChancesF[k] != 0){
          if(n >= running && n < scenarioChancesF[k] * 100 + running){
            switch (k){
              case 0:
                return battleScenario(p, tileType, difficulty);
              case 1:
                for(int i = 0; i < vlgs.size(); i++){
                  if(vlgs.get(i).getX() == play.getX() && vlgs.get(i).getY() == play.getY()){
                    // System.out.println("extant");
                    return vlgs.get(i);
                  }
                }
                vlgs.add(new Village(p, play.getX(), play.getY()));
                try{
                  FileWriter fw = new FileWriter(vlgFile, true);
                  fw.write(vlgs.get(vlgs.size() - 1).getFile().getName() + "\n");
                  fw.close();
                }catch(Exception e){
                  System.out.println("error");
                }
                // System.out.println("new");
                return vlgs.get(vlgs.size() - 1);
              case 2:
                return new Loot(difficulty);
              default:
//                 System.out.println("something fishy ha occurre");
                break;
            }
          }
        }
        running += scenarioChancesF[k] * 100;
      }
    }else if(tileType == 2){
      for(int k = 0; k < scenarioChancesG.length; k++){
        if(scenarioChancesG[k] != 0){
          if(n >= running && n < scenarioChancesG[k] * 100 + running){
            switch (k){
              case 0:
                return battleScenario(p, tileType, difficulty);
              case 1:
                for(int i = 0; i < vlgs.size(); i++){
                  if(vlgs.get(i).getX() == play.getX() && vlgs.get(i).getY() == play.getY()){
                    // System.out.println("extant");
                    return vlgs.get(i);
                  }
                }
                vlgs.add(new Village(p, play.getX(), play.getY()));
                try{
                  FileWriter fw = new FileWriter(vlgFile, true);
                  fw.write(vlgs.get(vlgs.size() - 1).getFile().getName() + "\n");
                  fw.close();
                }catch(Exception e){
                  System.out.println("error");
                }
                // System.out.println("new");
                return vlgs.get(vlgs.size() - 1);
              case 2:
                return new Loot(difficulty);
              default:
//                 System.out.println("something fishy ha occurre");
                break;
            }
          }
        }
        running += scenarioChancesG[k] * 100;
      }
    }else if(tileType == 3){
       for(int k = 0; k < scenarioChancesE.length; k++){
         if(scenarioChancesE[k] != 0){
           if(n >= running && n < scenarioChancesE[k] * 100 + running){
             switch (k){
               case 0:
                 return battleScenario(p, tileType, difficulty);
               case 1:
                 for(int i = 0; i < vlgs.size(); i++){
                   if(vlgs.get(i).getX() == play.getX() && vlgs.get(i).getY() == play.getY()){
                     // System.out.println("extant");
                     return vlgs.get(i);
                   }
                 }
                 vlgs.add(new Village(p, play.getX(), play.getY()));
                 try{
                   FileWriter fw = new FileWriter(vlgFile, true);
                   fw.write(vlgs.get(vlgs.size() - 1).getFile().getName() + "\n");
                   fw.close();
                 }catch(Exception e){
                   System.out.println("error");
                 }
                 // System.out.println("new");
                 return vlgs.get(vlgs.size() - 1);
               case 2:
                 return new Loot(difficulty);
               default:
//                 System.out.println("something fishy ha occurre");
                 break;
             }
           }
         }
         running += scenarioChancesE[k] * 100;
      }
    }else if(tileType == 5){
      for(int k = 0; k < scenarioChancesM.length; k++){
        if(scenarioChancesM[k] != 0){
          if(n >= running && n < scenarioChancesM[k] * 100 + running){
            switch (k){
              case 0:
                return battleScenario(p, tileType, difficulty);
              case 1:
                for(int i = 0; i < vlgs.size(); i++){
                  if(vlgs.get(i).getX() == play.getX() && vlgs.get(i).getY() == play.getY()){
                    // System.out.println("extant");
                    return vlgs.get(i);
                  }
                }
                vlgs.add(new Village(p, play.getX(), play.getY()));
                try{
                  FileWriter fw = new FileWriter(vlgFile, true);
                  fw.write(vlgs.get(vlgs.size() - 1).getFile().getName() + "\n");
                  fw.close();
                }catch(Exception e){
                  System.out.println("error");
                }
                // System.out.println("new");
                return vlgs.get(vlgs.size() - 1);
              case 2:
                return new Loot(difficulty);
              default:
//                 System.out.println("something fishy ha occurre");
                break;
            }
          }
        }
      }
    }else if(tileType == 6){
      for(int k = 0; k < scenarioChancesC.length; k++) {
        if (scenarioChancesC[k] != 0) {
          if (n >= running && n < scenarioChancesC[k] * 100 + running) {
            switch (k) {
              case 0:
                return battleScenario(p, tileType, difficulty);
              case 1:
                for (int i = 0; i < vlgs.size(); i++) {
                  if (vlgs.get(i).getX() == play.getX() && vlgs.get(i).getY() == play.getY()) {
                    // System.out.println("extant");
                    return vlgs.get(i);
                  }
                }
                vlgs.add(new Village(p, play.getX(), play.getY()));
                try {
                  FileWriter fw = new FileWriter(vlgFile, true);
                  fw.write(vlgs.get(vlgs.size() - 1).getFile().getName() + "\n");
                  fw.close();
                } catch (Exception e) {
                  System.out.println("error");
                }
                // System.out.println("new");
                return vlgs.get(vlgs.size() - 1);
              case 2:
                return new Loot(difficulty);
              default:
//                 System.out.println("something fishy ha occurre");
                break;
            }
          }
        }
      }
    }else if(tileType == 7){
      for(int k = 0; k < scenarioChancesV.length; k++) {
        if (scenarioChancesV[k] != 0) {
          if (n >= running && n < scenarioChancesV[k] * 100 + running) {
            switch (k) {
              case 0:
                return battleScenario(p, tileType, difficulty);
              case 1:
                for (int i = 0; i < vlgs.size(); i++) {
                  if (vlgs.get(i).getX() == play.getX() && vlgs.get(i).getY() == play.getY()) {
                    // System.out.println("extant");
                    return vlgs.get(i);
                  }
                }
                vlgs.add(new Village(p, play.getX(), play.getY()));
                try {
                  FileWriter fw = new FileWriter(vlgFile, true);
                  fw.write(vlgs.get(vlgs.size() - 1).getFile().getName() + "\n");
                  fw.close();
                } catch (Exception e) {
                  System.out.println("error");
                }
                // System.out.println("new");
                return vlgs.get(vlgs.size() - 1);
              case 2:
                return new Loot(difficulty);
              default:
//                 System.out.println("something fishy ha occurre");
                break;
            }
          }
        }
      }
    }else if (tileType == 8){
      //unique ruined castle thingy
    }
    return null;
  }

  //add a difficulty scaling system
  public Battle battleScenario(Player p, int tileType, int difficulty){
    File enemyFile = new File("Enemies/eenmy.dat");
    switch(tileType){
      case 1:
        enemyFile = new File("Enemies/genmy.dat");
        break;
      case 2:
        enemyFile = new File("Enemies/enmy.dat");
        break;
      case 3:
        enemyFile = new File("Enemies/eenmy.dat");
        break;
      default:
        enemyFile = new File("Enemies/eenmy.dat");
        break;
    }
    int numEnmies = 0 + (int)(Math.random() * (difficulty)) + 1;
    Enemy[] e = new Enemy[numEnmies];
    for(int i = 0; i< numEnmies; i++){

      e[i] = FileHandler.createEnemy(new File("Enemies/eenmy.dat").getPath(), difficulty);
    }
    return new Battle(p, e);
  }

  //the issue lies here
  public void initializeList(){
    try{
      Scanner scan = new Scanner(vlgFile);
      
      while(scan.hasNext()){
        String s = scan.nextLine();

        //redundant
        if(!s.equals("")){
          File fileName = new File(s);
          vlgs.add(new Village(p, fileName));
        }
      }
      scan.close();
    }catch(Exception e){
      System.out.println("ISSUE: " + e.getMessage());
    }
  }
}