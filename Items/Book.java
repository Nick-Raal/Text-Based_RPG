import Items.*;
public class Book extends Item{
  private double healthE;
  private double strE;
  private double manaE;
  private String body;

  public Book(String name, int value, int rarity, double healthE, double strE, double manaE, String body){
    super(name, value, rarity);
    this.healthE = healthE;
    this.strE = strE;
    this.manaE = manaE;
    this.body = body;
  }

  public double[] use(){
    double[] u = {healthE, strE, manaE};
    System.out.println("TXT:\n" + body);
    return u;
  }

  public String getFH(){
    return "Ṗ" + super.getName().replaceAll(" ", "_") + " " + super.getValue() + " " + super.getRarity() + " " + healthE + " " + strE + " " + manaE + " " + body;
  }
}