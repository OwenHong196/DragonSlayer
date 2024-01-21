public class Dragon {
    private int level;
    private int hp;
    private int possibleGoldGain;
    private boolean isDead;
    private int dragNum;
    private Player p;
    public Dragon(int i,Player p){
        this.p = p;
        dragNum = i;
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
        } else if (this.level == 2) {
            possibleGoldGain = 20;
        }else{
            possibleGoldGain = 40;
        }
    }
    public int getHp(){
        return hp;
    }
    public int getDragNum(){
        return dragNum;
    }
    public void attack(){
        int dmg = 0;
        if (level == 1){
            dmg = (int) (Math.random() * 10 + 5);
        }else if (level == 2){
            dmg = (int) (Math.random() * 15 + 10);
        }else{
            dmg = (int) (Math.random() * 20 + 25);
        }
        System.out.println("Dragon " + dragNum + " did " + dmg + " damage to you. You have " + p.getHp() + " health remaining.");
        p.receiveDmg(dmg);
        Room.nextTurn();
    }
    public void receiveDmg(int d){
        hp -= d;
        if (hp < 0){
            isDead = true;
            hp = 0;
            getReward();
        }
    }
    public void getReward(){
        boolean obtainReward = false;
        if (Math.random() <= 0.5){
            int gain = (int) (Math.random() * possibleGoldGain + 5);
            p.setGold(p.getGold() + gain);
            System.out.println("You gained " + gain + " gold!");
            obtainReward = true;
        }
        if (Math.random() <= 0.1){
            double dodge = 0;
            int dmg = (int) (Math.random() * 10 + 10);
            if (Math.random() <= 0.25){
                dodge = Math.random() - 0.7;
                if (dodge < 0){
                    dodge = 0;
                }
            }
            p.getSword().upgrade(dmg,dodge);
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
