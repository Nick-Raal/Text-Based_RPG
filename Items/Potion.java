package Items;

public class Potion extends Item{
    private double healthE;
    private double strE;
    private double manaE;
    public Potion(String name, int value, int rarity, double healthE, double strE, double manaE){
        super(name, value, rarity);
        this.healthE = healthE;
        this.strE = strE;
        this.manaE = manaE;
    }
    public double[] use(){
        double[] u = {healthE, strE, manaE};
        return u;
    }
}
