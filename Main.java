import java.lang.Thread;
import java.util.*;
class Main {
  static int n = 1;
  static int n1;
  public static void main(String[] args)
  {
    Scanner s = new Scanner(System.in);
    Place p = new Place(10, 20);
    Player play = new Player();      
    Scenario scen = new Scenario();
    p.setPlayerPos(0, 0);
    Graphics g = new Graphics();

      while(true){
        while(!play.getIn()) {
          System.out.println(p.display());
          n1 = Integer.parseInt(s.nextLine());
          if (p.move(n, n1)) {
            Object o = scen.scenario(play, p.getTile(), 1);
            if (o instanceof Battle) {
              System.out.println("ENEMY ENCOUNTERED!");
              s.nextLine();
              Battle b = (Battle) o;
              b.turn();
            } else if (o instanceof Village) {
              System.out.println("VILLAGE ENCOUNTERED");
              s.nextLine();
              Village v = (Village) o;
              v.village();
            } else if (o instanceof Loot) {
              System.out.println("CHEST DISCOVERED");
              s.nextLine();
              Loot l = (Loot) o;
              play.add(l.loot());
            }
            rest(10);
          }
        }
      s.close();
     }
  }
  
  
  public static void rest(long time){
    try {
      Thread.sleep(time);   
    }
    catch (Exception e) {
      System.out.println(e);
    }
    System.out.print("\033[H\033[2J");  
    System.out.flush();
  }
}
