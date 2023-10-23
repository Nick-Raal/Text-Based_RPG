import java.util.Random;
import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;

public class Place{
  Random r = new Random();
  
  private int x;
  private int y;
  File file;
  //
  //int[][] data = {{r.nextInt(4),r.nextInt(4),r.nextInt(4),r.nextInt(4),r.nextInt(4),r.nextInt(4),r.nextInt(4),r.nextInt(4),r.nextInt(4),r.nextInt(4)},{r.nextInt(4),r.nextInt(4),r.nextInt(4),r.nextInt(4),r.nextInt(4),r.nextInt(4),r.nextInt(4),r.nextInt(4),r.nextInt(4),r.nextInt(4)},{r.nextInt(4),r.nextInt(4),r.nextInt(4),r.nextInt(4),r.nextInt(4),r.nextInt(4),r.nextInt(4),r.nextInt(4),r.nextInt(4),r.nextInt(4)},{r.nextInt(4),r.nextInt(4),r.nextInt(4),r.nextInt(4),r.nextInt(4),r.nextInt(4),r.nextInt(4),r.nextInt(4),r.nextInt(4),r.nextInt(4)},{r.nextInt(4),r.nextInt(4),r.nextInt(4),r.nextInt(4),r.nextInt(4),r.nextInt(4),r.nextInt(4),r.nextInt(4),r.nextInt(4),r.nextInt(4)}};
  int[][] data;
  //determines the chances for bad things to happen
  private int difficulty;
  int playerTile;
  int pppX = 0;
  int pppY = 0;

  public Place(int size, int size1){
      try{
        file = new File("map.dat");
        if(file.createNewFile()){
          data = gen(size, size1, 1);
          setPlayerPos(0, 0);
          update();
        }
        
      }catch(Exception e){
        System.out.println(e);
      }
      initialize();
  }

//  public Place (File f){
//    file = f;
//    initialize();
//  }



  public String display(){
    String s = "";
    for(int y = 0; y < data.length; y++){
      for(int x = 0; x < data[y].length; x++){
        switch(data[y][x]){
          case 1:
            s += Color.GREEN + "■";
            break;
          case 2:
            s += Color.YELLOW + "■";
            break;
          case 3:
            s += Color.RED + "■";
            break;
          case 0:
            s += Color.CYAN + "■";
            break;
          case 4:
            s += Color.WHITE + "■";
            break;
          case 5:
            s += Color.RED + "m";
            break;
          case 6:
            s += Color.PURPLE + "c";
            break;
          case 7:
            s+= Color.YELLOW + "v";
            break;
          case 8:
            s+= Color.WHITE + "c";
            break;
          default:
            System.out.println("print ERROR:" + data[y][x] + Color.RESET);
        }
      }
      s += "\n" + Color.RESET;
    }
    return s;
  }

  //1 = up. 2 = right. 3 = down. 4 = left
  //return to check success
  public boolean move(int dist, int dir){
    switch(dir){
      case 1:
        if(checkTile(x, y + dist)){
           y+=dist;
          setPlayerPos(x, y);
          update();
          return true;
        }else{
          return false;
        }

      case 2:
        if(checkTile(x+dist, y)){
          x+=dist;
          setPlayerPos(x, y);
          update();
          return true;
        }else{
          return false;
        }

      case 3:
        if(checkTile(x, y-dist)){
          y-=dist;
          setPlayerPos(x, y);
          update();
          return true;
        }else{
          return false;
        }

      case 4:
        if(checkTile(x-dist, y)){
          x-=dist;
          setPlayerPos(x, y);
          update();
          return true;
        }else{
          return false;
        }

      default:
        System.out.println("ERROR");
        return false;
    }
  }

  private boolean checkTile(int x, int y){
    if(x < 0 || y < 0){
      return false;
    }else if(y >= data.length){
      return false;
    }else if(x >= data[y].length){
      return false;
    }
    else if(data[y][x] == 0){
      return false;
    }
    return true;
  }
  
  public void setPlayerPos(int x, int y){
    try{
      data[pppY][pppX] = playerTile;
      playerTile = data[y][x];
      data[y][x] = 4;
      pppX = x;
      pppY = y;
    }catch (Exception e){
      System.out.println("pos ERROR " + e);
    }
  }

  // public int enter(Player play){
  //   int type = data[play.getX()][play.getY()];
  //   return type;
  // }

  public int getX(){
    return x;
  }
  public int getY(){
    return y;
  }

  public int getTile(){
    return playerTile;
  }
  private int[][] gen(int size, int size1, int difficulty){

    boolean castle = false;
  
    int[][] map = new int[size][size1];
    for(int i = 0; i < map.length; i++){
      for(int j = 0; j < map.length; j++){
        int n = r.nextInt(7);
        while(n == 4){
          n = r.nextInt(7);
        }
        map[i][j] = n;
      }
    }
    map[0][0] = 2;
    // System.out.println("a thingy");
    for(int i = 0; i < map.length; i++){
      for(int j = 0; j < map[i].length; j++){
        //forest, grassland, evil, mountain, castle, ocean, village
        double[] chances = {0, 0, 0, 0, 0, 0, 0};
        // System.out.println("tile #" + (i+j));
        if( (i > 0 && j > 0) && (i < map.length -1 && j < map[i].length -1)){
          chances = tile(chances, map[i - 1][j], 0.25);
          chances = tile(chances, map[i][j - 1], 0.25);
          chances = tile(chances, map[i+1][j], 0.25);
          chances = tile(chances, map[i][j + 1], 0.25);
        }else if((i > 0 && j == 0) && (i < map.length - 1 && j < map[i].length - 1)){
          chances = tile(chances, map[i -1][j], 0.33);
          chances = tile(chances, map[i + 1][j], 0.33);
          chances = tile(chances, map[i][j +1], 0.33);
        }else if((i == 0 && j > 0) && (i < map.length -1  && j < map[i].length - 1)){
          chances = tile(chances, map[i + 1][j], 0.33);
          chances = tile(chances, map[i][j + 1], 0.33);
          chances = tile(chances, map[i][j - 1], 0.33);
        }else if((i == 0 && j == 0)){
          chances = tile(chances, map[i + 1][j], 0.5);
          chances = tile(chances, map[i][j + 1], 0.5);
        }else if(i == map.length - 1 && j == map[i].length -1){
          chances = tile(chances, map[map.length - 2][map.length - 1], 0.5);
          chances = tile(chances, map[map.length - 1][map.length - 2], 0.5);
        }else if (i == map.length - 1 && j < map[i].length - 1 && j > 0){
          chances = tile(chances, map[i - 1][j], 0.33);
          chances = tile(chances, map[i][j + 1], 0.33);
          chances = tile(chances, map[i][j - 1], 0.33);
        }else if(j == map[i].length - 1 && i < map.length - 1 && i > 0){
          chances = tile(chances, map[i + 1][j], 0.33);
          chances = tile(chances, map[i][j - 1], 0.33);
          chances = tile(chances, map[i - 1][j], 0.33);
        }else if (j == map[i].length - 1 && i == 0){
          chances = tile(chances, map[i][ j -1], 0.5);
          chances = tile(chances, map[i + 1][j], 0.5);
        }else if (j == 0 && i == map.length - 1){
          chances = tile(chances, map[i][j + 1], 0.5);
          chances = tile(chances, map[i - 1][j], 0.5);
        }else{
          System.out.println("GEN ERROR" + " i: " + i + " j: " + j);
        }
        int n = r.nextInt(99) + 1;
        int running = 0;
        for(int k = 0; k < chances.length; k++){
          
          // System.out.print("chances " + chances[k] + " ");
          if(chances[k] != 0){
            // System.out.println("n " + n);
            // System.out.println("n max" + (chances[k] * 100 + running));
            if(n >= running && n < chances[k] * 100 + running){
              // System.out.println("Type: " + (k!= 5 ? (k != 3 ? k + 1 : 3) : 0));
              map[i][j] = (k!= 5 ? (k != 3 ? k + 1 : 3) : 0); 
              if(castle == true && k+1 == 6){
                map[i][j] = 8;
              }else if(k + 1 == 6){
                castle = true;
              }
              //assuming a maximum i and j of 28
              if(i + j >= 18 && !castle){
                if (n +i + j>= 28){
                  map[i][j] = 6;
                  castle = true;
                }
              }
              break;
            }
          }
          // System.out.println("running " + running);
          running += chances[k] * 100;
        }
        // System.out.println();
        //moths? ye :(
      }
    }
    return map;
  }
  private double[] tile(double[]chances, int type, double factor){
    //forest, grassland, evil, mountain, castle, ocean, village
    double[] FC = {0.15, 0.25, 0.20, 0.10, 01, 0, 0.2};
    double[] GC = {0.4, 0.05, 0.1, 0, 0, 0.25, 0.2};
    double[] EC = {0, 0.1, 0.4, 0.25, 0.25, 0, 0};
    double[] MC = {0.25, 0, 0.25, 0.45, 0, 0, 0.05};
    double[] CC = {0, 0, 0.54, 0.44, 0.01, 0, 0.01};
    double[] OC = {0.4, 0.35, 0, 0, 0, 0.25, 0.1};
    double[] VC = {0.5, 0.3, 0, 0.1, 0.01, 0.09, 0};

    if(type == 1){
      for(int i = 0; i < chances.length; i++){
        chances[i] += FC[i] * factor;
      }
    }else if(type == 2){
      for(int i = 0; i < chances.length; i++){
        chances[i] += GC[i] * factor;
      }
    }else if(type ==3){
      for(int i = 0; i < chances.length; i++){
        chances[i] += EC[i] * factor;
      }
    }else if(type == 5){
      for(int i = 0; i < chances.length; i++){
        chances[i] += MC[i] * factor;
      }
    }else if(type == 6){
      for(int i = 0; i < chances.length; i++){
        chances[i] += CC[i] * factor;
      }
    }else if (type == 0){
      for(int i = 0; i < chances.length; i++){
        chances[i] += OC[i] * factor;
      }
    }else if (type == 7){
      for(int i = 0; i < chances.length; i++){
        chances[i] += VC[i] * factor;
      }
    //destroyed castle
    }else if(type == 8){
      for(int i = 0; i < chances.length; i++){
        chances[i] += CC[i] * factor;
      }
    }
    // System.out.println("type " + type);
    // double tC = 0;
    // for(int i = 0; i < chances.length; i++){
    //   // System.out.print("initial chances " + chances[i] + " ");
    //   tC += chances[i];
    // }
    // System.out.println("total chance " + tC + "\n");
    return chances;
  }

  //check the player's current position to see if anything cool things should occur
  //debating adding support for multiple different types of places
  //will do later
  public int checkPos() {
    switch (data[y][x]) {
      case 1:
        //forest chances
        break;
      case 2:
        //grassland chances
        break;
      case 3:
        //evil chances
        // return ((int)(difficulty * Math.random))/difficulty >= 1 ? 1 : 0;
        break;
      default:
        System.out.println("something strange has occured");
    }
    return 1;
  }

  //update the saved map
  public void update(){
    try{
      FileWriter fw = new FileWriter(file, false);
      for(int i = 0; i < data.length; i++){
        for(int j = 0; j<data[i].length; j++){
          fw.write(data[i][j] + ",");
        }
        System.out.println();
        fw.write("\n");
      }
      fw.write(pppX + ","+pppY +","+ playerTile);
      fw.close();
    }catch(Exception e){
      System.out.println(e);
    }

  }

  private void initialize() {
    try{
      Scanner scan = new Scanner(file);
      data = new int[10][20];
      int k=0;
      while(scan.hasNext()){
        String[] s = scan.nextLine().split(",");
        if(s.length == 3){
          pppX = Integer.parseInt(s[0]);
          pppY = Integer.parseInt(s[1]);
          playerTile = Integer.parseInt(s[2]);
          break;
        }
        for(int i = 0; i < s.length; i++){
          data[k][i] = Integer.parseInt(s[i]);
          if(data[k][i] == 4){
            x = i;
            y = k;
          }
        }
        k++;
        
      }
    }catch(Exception e){
      System.out.println(e);
    }
  }

}


//what are you supposed to do???
//is game
//no done yet
//the white dude is ur character that u can take around the map (except across the blue oceans)
//working on a world generator rn