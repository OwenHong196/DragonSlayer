public class Sword {
    private int atkDmg;
    private double dodgeChance;
    public Sword(){
        atkDmg = 10;
        dodgeChance = 0.2;
    }
    public int getAtkDmg(){
        return atkDmg;
    }
    public double getDodgeChance(){
        return dodgeChance;
    }
    public void upgrade(int dmg, double dodge){
        atkDmg += dmg;
        dodgeChance += dodge;
    }
}
