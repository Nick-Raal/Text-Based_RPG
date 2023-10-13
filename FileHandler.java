import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class FileHandler{
  public static Enemy createEnemy(String path){
    String s;
    try{
        List<String> lines = Files.readAllLines(Paths.get(path));
        Random random = new Random();
        int randomIndex = random.nextInt(lines.size());
        s = lines.get(randomIndex);
    }catch(Exception e){
      System.out.println(e);
    }
    String name = s.substring(0, s.indexOf(" "));
    s = s.substring(s.indexOf(" ") + 1);
    double health = Double.parseDouble(s.substring(0, s.indexOf(" ")));
    s = s.substring(s.indexOf(" ") + 1);
    double init = Double.parseDouble(s.substring(0, s.indexOf(" ")));
    s = s.substring(s.indexOf(" ") + 1);
    double atkMod = Double.parseDouble(s.substring(0, s.indexOf(" ")));
    s = s.substring(s.indexOf(" ") + 1);
    int k = 0;
    for(int i = 0; i < s.length(); s++){
      if(s.charAt(i) == 'ẅ'){
        k++;
      }
    }
    Weapon[] attack = new Weapon[k];
    k = 0;
    for(int i = 0; i < s.length(); s++){
      if(s.charAt(i) == 'ẅ'){
        attack[k] = new Weapon(s.substring(i + 1, s.indexOf(" ") + 1), Double.parseDouble(s.substring(s.indexOf(" ")));
        k++;
      }
    }
  }
}