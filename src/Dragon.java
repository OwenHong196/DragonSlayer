public class Dragon {
    private int level;
    private int hp;
    private boolean isDead;
    public Dragon(){
        level = (int) (Math.random() * 3 + 1);
        isDead = false;
        if (level == 1){
            hp = 100;
        }else if (level == 2){
            hp = 150;
        }else{
            hp = 200;
        }
    }
    public void attack(Player p){
        int dmg = 0;
        if (level == 1){
            dmg = (int) (Math.random() * 10 + 5);
        }else if (level == 2){
            dmg = (int) (Math.random() * 15 + 10);
        }else{
            dmg = (int) (Math.random() * 20 + 25);
        }
        p.receiveDmg(dmg);
    }
    public void receiveDmg(int d){
        hp -= d;
        if (hp < 0){
            isDead = true;
            hp = 0;
        }
    }
    public void getReward(Player p){
        if (Math.random() <= 0.5){
            p.set
        }
    }
}
