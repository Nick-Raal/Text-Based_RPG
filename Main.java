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
    p.setPlayerPos(0, 0);
    Graphics g = new Graphics();
    play.dispAtk();
    Enemy liza = new Enemy("Polonius", 100, 0, 1);
    Enemy king = new Enemy("Kai", 150, 0, 1);
    Enemy[] e = {king};
    Battle b = new Battle(play, e);
    rest(10);
    b.turn();
    //  while(true){
    //    while(!play.getIn()){
    //      System.out.println(p.display());
    //      n1 = Integer.parseInt(s.nextLine());
    //      p.move(n, n1);
    //     rest(10);
    //    }
    //  s.close();
    // }
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
