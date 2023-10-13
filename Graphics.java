

public class Graphics{
  public String graphics(int type){
    switch(type){
      case 1:
        return "                             -|             |-\n-|                  [-_-_-_-_-_-_-_-]                  |-\n[-_-_-_-_-]          |             |          [-_-_-_-_-]\n| o   o |           [  0   0   0  ]           | o   o |\n|     |    -|       |           |       |-    |     |\n|     |_-___-___-___-|         |-___-___-___-_|     |\n|  o  ]              [    0    ]              [  o  |\n|     ]   o   o   o  [ _______ ]  o   o   o   [     | ----__________\n_____----- |     ]              [ ||||||| ]              [     |\n|     ]              [ ||||||| ]              [     |\n_-_-|_____]--------------[_|||||||_]--------------[_____|-_-_\n( (__________------------_____________-------------_________) )";
      default:
        break;
    }
    return " ";
  }

  //do cool transitions
  public void transition(){
    
  }

  public String fancyWord(String word){
    String s = "";
    int k = 0;
    for(int i = 0; i<word.length(); i++){
      if(k % 2 == 0){
         s += Color.BLUE + word.substring(i,i+1) + Color.RESET; 
      }else{
        s += Color.PURPLE + word.substring(i,i+1) + Color.RESET; 
      }
      k += word.charAt(i) != ' ' ? 1 : 0;
    }
    return s;
  }
}