public class Sword {
    private String name;
    private int atkDmg;
    private double dodgeChance;
    public Sword(String name){
        this.name = name;
        atkDmg = 10;
        dodgeChance = 0.2;
    }
    public String getName(){
        return name;
    }
    public int getAtkDmg(){
        return (int) (Math.random() * 10) + atkDmg;
    }
    public double getDodgeChance(){
        return dodgeChance;
    }
    public void upgrade(int dmg, double dodge){
        atkDmg += dmg;
        dodgeChance += dodge;
    }
}
