public class Player {
    private String name;
    private int hp;
    private int gold;
    private boolean hasHpPot;
    private Sword s;
    public Player(String name){
        this.name = name;
        hp = 100;
        gold = 0;
        hasHpPot = true;
        s = new Sword();
    }
    public void attack(){

    }
    public void receiveDmg(){

    }
    public void useHpPot(){
        if (hasHpPot){
            hasHpPot = false;
            hp += 20;
        }else{
            System.out.println("You don't have a health potion!");
        }
    }
    public String playerInfo(){
        String stats = "Player name: " + name;
        stats += "\nHp: " + hp;
        stats += "\nGold: " + gold;
        stats += "\nHas hp potion: " + hasHpPot;
        stats += "\nSword damage: " + s.getAtkDmg();
        stats += "\nDodge chance: " + s.getDodgeChance() * 100 + "%";
        return stats;
    }
}
