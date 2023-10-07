public class Player{

  // private int x = 0;
  // private int y = 0;
  private boolean in;
  private Item[] inventory = new Item[20];
  public int health;
  
  // //1 = up. 2 = right. 3 = down. 4 = left
  // public void move(int dist, int dir){
  //   switch(dir){
  //     case 1:
  //       y+=dist;
  //       break;
  //     case 2:
  //       x+=dist;
  //       break;
  //     case 3:
  //       y-=dist;
  //       break;
  //     case 4:
  //       x-=dist;
  //       break;
  //     default:
  //       System.out.println("ERROR");     
  //   }
  // }

  // public int getX(){
  //   return x;
  // }
  // public int getY(){
  //   return y;
  // }
  public boolean getIn(){
    return in;
  }
}