import java.lang.Thread;
import java.util.*;
class Main {
  static int n = 1;
  static int n1;
  public static void main(String[] args)
  {
    Scanner s = new Scanner(System.in);
    Place p = new Place(15);
    Player play = new Player();      
    p.setPlayerPos(0, 0);
    Graphics g = new Graphics();
    while(true){
      while(!play.getIn()){
        System.out.println(p.display());
        n1 = Integer.parseInt(s.nextLine());
        p.move(n, n1);
        // rest(10);
      }
      
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
