import java.util.*;

public class Battle{
  private Player p;
  private Enemy[] e;

  public Battle(Player play, Enemy[] enemy){
    p = play;
    e = enemy;
    p.recover();
    p.full();
  }
  
  public void turn(){
    Main.rest(10);
    Scanner in = new Scanner(System.in);
    double init = 0;
    for(int i = 0; i < e.length; i++){
      if(e[i] != null && e[i].getInitiative() > init){
        init = e[i].getInitiative();
      }
    }
    if(p.getInitiative() >= init){
      display();
      int n = 0;
      while(n <= 0 || n > 3){
        try{
          System.out.println("1: ATTACK \n2: ITEM\n3: RUN\n4: PASS");
          n = Integer.parseInt(in.nextLine());
          Main.rest(10);
        }catch(Exception e){
          System.out.println(e);
        }
      }
      // System.out.println("Nikhil was here");
      Main.rest(10);
      if(n == 1){
        p.dispAtk();
        System.out.println("\nSELECT ATTACK!");
        n = -1;
        
        while((n < 1 || n >p.getWeaponL()) || p.getStr()-p.getWeapon((n != -1 ? n-1 : 0)).getStr() <= 0 || p.getMana() - p.getWeapon((n != -1 ? n-1 : 0)).getMana() <= 0 ){
          n = Integer.parseInt(in.nextLine());
          if(n < 0 || n >p.getWeaponL()){
            
            n = 0;
            exitTurn();
          }
          else if(p.getStr()-p.getWeapon((n-1)).getStr() <= 0 || p.getMana() - p.getWeapon((n-1)).getMana() <= 0){
            System.out.println("Insufficient strength or mana");
          }
        }
        int select = n;
        double[] atk = p.Attack(select-1);
        Main.rest(10);
        for(int  i = 0; i < e.length; i++){
          System.out.println(e[i] != null ? ((i+1) + ": " + e[i]) : "");
        }
        n = -1;
        while(n < 1 || n > e.length){
          System.out.println("Select which enemy to VANQUISH");
          n = Integer.parseInt(in.nextLine());
        }
        double damage = e[n-1].damage(atk[0], (int)atk[1], false);
        String s = e[n-1].getName();
        s+= FileHandler.atkMessage("atk.dat", damage/e[n-1].getHealth());
        s += p.getWeapon(select-1);
        // Main.rest(10);
        System.out.println(s);
        Main.rest(5000);
        enemyTurn();
      }else if(n==2){
        p.dispItm();
        n = -1;
        //while(n < 1 || n > p.getItemL()){
        System.out.println("Select which item to use");
        n = Integer.parseInt(in.nextLine());
        if(n < 1 || n > p.getItemL()){
          n = 0;
          exitTurn();
        }
        //}
        p.useItem(n-1);
        // Main.rest(10);
        System.out.println(p);

        //wait for user input
        in.nextLine();
        
        n=0;
        enemyTurn();
      }else if(n == 3){
        //run chances
        
      }else if (n==4){
        //pass turn
        enemyTurn();
      }    
//      in.close();
    }else{
      enemyTurn();
    }
  }

  public void display(){
    for(int i = 0; i < e.length; i++){
      System.out.println(e[i] != null ? e[i] : "");
    }
    System.out.println("PLACE HOLDER");
    System.out.println(p);
  }

  public void enemyTurn(){
    int k = 0;
    for(int i = 0; i < e.length; i++){
      if(e[i] != null && e[i].getHealth() <= 0){
        p.add(e[i].die());
        p.addE((int)e[i].numDeath()[1]);
        p.addG((int)e[i].numDeath()[0]);
        
        e[i] = null;
        for(int j = i; j < e.length - 1; j++){
          e[j] = e[j+1];
        }
        k++;
      }
    }
    if(k == e.length){
      end();
      return;
    }
    for(int  i = 0; i < e.length; i++){
      System.out.println(e[i] != null ? ((i + 1) +": " +  e[i]) : "");
    }
    System.out.println("EVIL PLACEHOLDER");
    for(int i = 0; i < e.length; i++){
      Random r = new Random();
      int n = r.nextInt(10) + 1;
      if(n < (e[i].getInitiative() * 5)){
        //enemy attack
        System.out.println(e[i].getName() + " attacks");
        int running = 0;
        n = r.nextInt(100) + 1;
        for(int j = 0; j < e[i].getAtkL(); j++){
          if(n >= running && n <= running +e[i].getWeapon(j).getChance()){
            double[] atk = e[i].atk(j);
            p.damage(atk[0], (int)atk[1], false);
          }
          running += e[i].getWeapon(j).getChance();
        }
      }
    }
    Main.rest(10000);
    p.recover();
    turn();
  }
  private void end(){
    System.out.println("YOU WIN");
    System.out.println(p);
    p.dispLvl();
    Main.rest(5000);
    
  }
  public void exitTurn(){
    Main.rest(100);
    turn();
  }
}