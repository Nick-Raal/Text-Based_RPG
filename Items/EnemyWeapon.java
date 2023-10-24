public class EnemyWeapon extends Weapon{

  // the cance that an enemy will use the weapon
  double chance;

  public EnemyWeapon(String name, double damage, int type, double chance){
    super(name, damage, type);
    this.chance = chance;
  }
}