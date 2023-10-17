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
    System.out.println(g.fancyWord(" oOoOOoOOo                             o.oOOOo.                          o\n     o                                  o     o                         O\n     o                   O              O     O                         o\n     O                  oOo             oOooOO.                         o\n     o     .oOo. o   O   o   ooooooooo  o     `O .oOoO' .oOo  .oOo. .oOoO\n     O     OooO'  OoO    O              O      o O   o  `Ooo. OooO' o   O\n     O     O      o o    o              o     .O o   O      O O     O   o\n     o'    `OoO' O   O   `oO            `OooOO'  `OoO'o `OoO' `OoO' `OoO'o\n\n\n `OooOOo.  OooOOo.   .oOOOo.\n  o     `o O     `O .O     o\n  O      O o      O o\n  o     .O O     .o O\n  OOooOO'  oOooOO'  O   .oOOo\n  o    o   o        o.      O\n  O     O  O         O.    oO\n  O      o o'         `OooO'                                              "));
    System.out.println();
    System.out.println("created by Nicholas Raal");
    System.out.println("press enter to start or help for a list of commands");
    s.nextLine();
      while(true){
        while(!play.getIn()) {
          System.out.println(p.display());
          String input = s.nextLine();
          if(input.toUpperCase().charAt(0) == 'E'){
            System.out.println(play);
            play.dispItm();
            play.dispArm();
            play.dispAtk();
            System.out.println("CURRENT ARMOR:");
            play.dispArm();
            play.dispLvl();
            System.out.println("1: USE ITEM\n2: EQUIP ARMOR");
            int n = Integer.parseInt(s.nextLine());
            if(n == 1){
              Main.rest(10);
              play.dispItm();
              n = -1;
              while(n < 1 || n > play.getItemL()){
                System.out.println("Select which item to use");
                n = Integer.parseInt(s.nextLine());
              }
              play.useItem(n-1);
              System.out.println(play);
              //wait for user input
              s.nextLine();
            }else if(n == 2){
              Main.rest(10);
              play.dispCArm();
              play.dispArm();
              n = -1;
              while(n < 1 || n > play.getArmorL()){
                System.out.println("Select which armor to equip");
                n = Integer.parseInt(s.nextLine());
              }
              play.equip(n-1);
              play.dispCArm();
              s.nextLine();
            }
          }else{
            try{
              n1 = Integer.parseInt(input);
            }catch(Exception e){
             System.out.println("impossible input string");
            }
            if (p.move(n, n1)) {
              Object o = scen.scenario(play, p.getTile(), 1, p);
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
