public class Dragon {
    private int level;
    private int hp;
    private int possibleGoldGain;
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
        if (this.level == 1){
            possibleGoldGain = 10;
        } else if (this.level == 1) {
            possibleGoldGain = 20;
        }else{
            possibleGoldGain = 40;
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
        boolean obtainReward = false;
        if (Math.random() <= 0.5){
            int gain = (int) (Math.random() * possibleGoldGain + 5);
            p.setGold(p.getGold() + gain);
            System.out.println("You gained " + gain + " gold!");
            obtainReward = true;
        }
        if (Math.random() <= 0.1){
            System.out.println("You received an upgrade!");
            obtainReward = true;
        }
        if (Math.random() <= 0.5){
            int hpGain = ((1 - (int) (p.percentHp() / 100.0)) / 5) * p.getMaxHp();
            p.setHp(p.getHp() + hpGain);
            System.out.println("You healed " + hpGain + " hp");
            obtainReward = true;
        }
        if (!obtainReward){
            String str = "You found nothing ";
            str += new String(Character.toChars(0x1F622));
            System.out.println(str);
        }
    }
}
