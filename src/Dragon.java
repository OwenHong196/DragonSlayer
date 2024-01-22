public class Dragon {
    private int level;
    private int maxHp;
    private int hp;
    private int possibleGoldGain;
    private boolean isDead;
    private int dragNum;
    private Player p;
    private int[] damageRange;
    public Dragon(int i,Player p){
        this.p = p;
        dragNum = i;
        level = (int) (Math.random() * 3 + 1);
        isDead = false;
        damageRange = new int[2];
        updateDragon();
    }

    // getters/setters
    public boolean getIsDead(){
        return isDead;
    }
    public int getLevel(){
        return level;
    }
    public int getHp(){
        return hp;
    }
    public int getDragNum(){
        return dragNum;
    }
    public void setLevel(int l){
        level = l;
    }

    //calculates the damage to player
    public void attack(){
        if (Math.random() <= 0.1){
            System.out.println(Colors.RED + "The dragon missed his fireball" + Colors.RESET);
        }else {
            int dmg = 0;
            if (level == 1) {
                dmg = (int) (Math.random() * 10 + 5);
            } else if (level == 2) {
                dmg = (int) (Math.random() * 10 + 10);
            } else {
                dmg = (int) (Math.random() * 15 + 15);
            }
            p.receiveDmg(this, dmg);
        }
        Room.nextTurn();
    }

    //dragon takes damage
    public void receiveDmg(int d){
        hp -= d;
        if (hp <= 0){
            isDead = true;
            hp = 0;
            DragonSlayer.score += 50 * level;
        }
    }

    //player gains loot from dragon kill
    public void getReward(){
        boolean obtainReward = false;
        if (Math.random() <= 0.5){
            int gain = (int) (Math.random() * possibleGoldGain + 5);
            p.setGold(p.getGold() + gain);
            System.out.println("You gained " + Colors.YELLOW + gain + " gold!" + Colors.RESET);
            DragonSlayer.score += gain * 10;
            obtainReward = true;
        }
        if (Math.random() <= 0.3){
            double dodge = 0;
            int dmg = (int) (Math.random() * 10 + 10);
            if (Math.random() <= 0.25){
                dodge = Math.round((Math.random() - 0.7) * 100) / 100.0;
                if (dodge < 0){
                    dodge = 0;
                }
            }
            p.getSword().upgrade(dmg,dodge);
            System.out.println("You received an upgrade!");
            System.out.println("You gained " + dmg + " attack power");
            System.out.println("You gained " + dodge * 100 + "% dodge chance");
            obtainReward = true;
        }
        int hpGain = ((100 - p.percentHp()) / 5);
        p.setHp(p.getHp() + hpGain);
        System.out.println("You healed " + Colors.RED + hpGain + " health" + Colors.RESET);
        if (!obtainReward){
            String str = "You found nothing ";
            str += new String(Character.toChars(0x1F622));
            System.out.println(str);
        }

    }

    //updates dragon stats
    public void updateDragon(){
        if (level == 1){
            maxHp = 50;
            hp = 50;
        }else if (level == 2){
            maxHp = 100;
            hp = 100;
        }else{
            maxHp = 125;
            hp = 125;
        }
        if (this.level == 1){
            possibleGoldGain = 10;
        } else if (this.level == 2) {
            possibleGoldGain = 20;
        }else{
            possibleGoldGain = 40;
        }
        if (this.level == 1){
            damageRange[0] = 5;
            damageRange[1] = 15;
        } else if (this.level == 2) {
            damageRange[0] = 10;
            damageRange[1] = 20;
        }else{
            damageRange[0] = 15;
            damageRange[1] = 30;
        }
    }

    //returns dragon stats
    public String stats(){
        String stats = Colors.PURPLE + "Dragon " + dragNum + ":" + Colors.RESET;
        stats += "\nLevel: " + level;
        stats += "\n" + Colors.RED + "Health: " + hp + "/" + maxHp + Colors.RESET;
        stats +=  Colors.WHITE + "\nDamage: " + damageRange[0] + "-" + damageRange[1] + Colors.RESET;
        return stats;
    }
}
