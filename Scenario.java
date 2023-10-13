import java.util.Random;
public class Scenario{
  //the goal of this class is to create a system whereby a place can return a simple situation that can be turned into a battle;
  //furthermore, this could be used to handle other place scenarios, like loot and unique things

  //possible scenarios:
  //1. Battle
  //2. Village
  //3. Loot
  //4. Unique

  double[] scenarioChancesF = {0, 0, 0, 0};
  double[] scenarioChancesG = {0, 0, 0, 0};
  double[] scenarioChancesE = {0, 0, 0, 0};
  double[] scenarioChancesM = {0, 0, 0, 0};
  double[] scenarioChancesC = {0, 0, 0, 0};
  //placeholder where ocean chances would be stored
  double[] scenarioChancesV = {0, 0, 0, 0};

  //this method should return what type of encounter occurs, a different method will handle the internals of the encounter
  public Object scenario(int tileType){
    Random r = new Random();
    int n = r.nextInt(99) + 1;
    int running = 0;
    if(tileType == 1){
      for(int k = 0; k < scenarioChancesF.length; k++){
        if(scenarioChancesF[k] != 0){
          if(n >= running && n < scenarioChancesF[k] * 100 + running){
            return k + 1;
          }
        }
      }
    }else if(tileType == 2){
      for(int k = 0; k < scenarioChancesG.length; k++){
        if(scenarioChancesG[k] != 0){
          if(n >= running && n < scenarioChancesG[k] * 100 + running){
            return k+1;
          }
        }
      
      }
    }else if(tileType == 3){
      for(int k = 0; k < scenarioChancesE.length; k++){
        if(scenarioChancesE[k] != 0){
          if(n >= running && n < scenarioChancesE[k] * 100 + running){
            return k + 1;
          }
        }
      }
    }else if(tileType == 4){
      for(int k = 0; k < scenarioChancesM.length; k++){
        if(scenarioChancesM[k] != 0){
          if(n >= running && n < scenarioChancesM[k] * 100 + running){
            return k + 1;
          }
        }
      }
    }else if(tileType == 5){
      for(int k = 0; k < scenarioChancesC.length; k++){
        if(scenarioChancesC[k] != 0){
          if(n >= running && n < scenarioChancesC[k] * 100 + running){
            return k + 1;
          }
        }
      }
    }
    return null;
  }

  public Battle battleScenario(int tileType){
    i
  }
}