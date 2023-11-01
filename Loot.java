import Items.Item;
import java.util.ArrayList;
import java.util.Scanner;

public class Loot {
    //the class to handle the random loot chances
    //WIP
    private ArrayList<Item> contents;

    public Loot(double difficulty){
        contents = new ArrayList<Item>();
        for(int i = 0; i < (6 - (int)(difficulty * Math.random())); i++){
            contents.add(FileHandler.createLootItem("loot.dat", 1));
        }
    }

    public ArrayList<Item> loot(){
        Scanner in = new Scanner(System.in);
        System.out.println(this);
        in.nextLine();
        return contents;
    }

    public String toString(){
        String s = "CONTENTS:\n";
        for(int i = 0; i < contents.size(); i++){
            s+= contents.get(i) + "\n";
        }
        return s;
    }
}
