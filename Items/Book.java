import Items.*;
public class Book extends Potion{
  private String body;

  public Book(String name, int value, int rarity, double healthE, double strE, double manaE, String body){
    super(name, value, rarity, healthE, strE, manaE);
    this.body = body;
  }

  public double[] use(){
    System.out.println("TXT:\n" + body);
    return super.use();
  }

  public String getBody(){
    return body;
  }

  public String getFH(){
    return "Ṗ" + super.getName().replaceAll(" ", "_") + " " + super.getValue() + " " + super.getRarity() + " " + super.use()[0] + " " + super.use()[1] + " " + super.use()[2] + " " + body.replaceAll(" ", "_");
  }
}