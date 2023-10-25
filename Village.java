import Items.Item;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class Village {
    //WIP
    //where items can be bought and sold at a merchant
    //ideally other functionality like
    //inns
    //mages (to learn spells)
    //just general variety to keep the game interesting

    private ArrayList<Item> wares = new ArrayList<>();
    private String name;
    private int gold = (int)Math.random()*2000;
    private File file;
    private Player p;

    private int x;
    private int y;

    public Village(Player p, int x, int y){
        
        name = FileHandler.nameVillage("vlg.dat");
        this.p = p;
        for(int i = 0; i < (int)(Math.random() * 3) + 1; i++) {
            wares.add(FileHandler.createItem("itm.dat"));
        }
      this.x = x;
      this.y = y;
      try{
        file = new File(name.replaceAll("\\s", "") + ".dat");
        file.createNewFile();
        update();
      }catch(Exception e){
        System.out.println(e.getMessage());
      }
    }

  public Village(Player p, File f){
    this.p = p;
    file = f;
    try{
      Scanner s = new Scanner(file);
      name = s.nextLine();
      gold = Integer.parseInt(s.nextLine());
      x = Integer.parseInt(s.nextLine());
      y = Integer.parseInt(s.nextLine());
      // String spring = s.nextLine();
      // System.out.println("nxt: " + spring);
      while(s.hasNext()){
        String spring = s.nextLine();
        wares.add(FileHandler.createItemS(spring));
      }
      s.close();
    }catch(Exception e){
      System.out.println("VI: " + e);
    }
  }
  
    public Item buyItem(int select){
        Item itm = wares.get(select);
        gold += (int)itm.getValue() * 1.1;
        return itm;
    }
    public void sellItem(Item itm){
        wares.add(itm);
        gold -= itm.getValue();
    }
    public void inn(Player p){

    }

    public void village(){
        Main.rest(10);
        System.out.println("WELCOME TO " + name);
        System.out.println(this);
        int n = 0;
        Scanner in = new Scanner(System.in);
        while(n < 3){
            System.out.println("1: BUY\n2: SELL\n3: EXIT");
            try{
                n = Integer.parseInt(in.nextLine());
            }catch(Exception e) {
                System.out.println("ERROR");
            }
            if(n==1){
                Main.rest(10);
                int select = 0;
                    System.out.println("FOR SALE:");
                    System.out.println(this);
                    try{
                        select = Integer.parseInt(in.nextLine());
                    }catch(Exception e){
                        System.out.println("ERROR");
                    }
              if(select < 1 || select > wares.size()){
                village();
              }
                if(wares.get(select - 1).getValue() <= p.getGold()){
                    p.add(buyItem(select - 1));
                    p.addG(-1 * wares.get(select - 1).getValue());
                    wares.remove(select - 1);
                }
                System.out.println(p);
                p.dispInven();
            } else if (n == 2) {
                Main.rest(10);
                System.out.println("WHAT DO YOU WANT TO SELL?");
                p.dispInven();
                int select = -1;

                  try{
                      select = Integer.parseInt(in.nextLine());
                  }catch(Exception e){
                      System.out.println("ERROR");
                  }

              if(select < 1 || select > p.getInvenL()){
                village();
              }
                if(p.getItem(select - 1).getValue() <= gold){
                    sellItem(p.getItem(select - 1));
                    p.addG(p.getItem(select - 1).getValue());
                    p.removeItem(select - 1);
                }
            }else{
              return;
            }
        }
        //in.close();
        update();
        return;
    }

    public String toString(){
        String s = "";
        for(int i = 0; i < wares.size(); i++){
            s += wares.get(i).getName() + " | G: " + wares.get(i).getValue() + " | R: " + wares.get(i).getRarity() + "\n";
        }
        return s;
    }

  public int getX(){
    return x;
  }
  public int getY(){
    return y;
  }

  public String getName(){
    return name;
  }
  public File getFile(){
    return file;
  }

  private void update(){
    try{
      FileWriter fw = new FileWriter(file, false);
      fw.write(name + "\n" + gold + "\n" + x + "\n" + y + "\n");
      for(int i = 0; i < wares.size(); i++){
        //write item
        fw.write(wares.get(i).getFH() + "\n");
      }
      fw.close();
      }catch(Exception e){
        System.out.println(e.getMessage());
      }
    }
  
}
