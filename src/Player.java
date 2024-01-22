public class Player {
    private String name;
    private int maxHp;
    private int hp;
    private int gold;
    private boolean hasHpPot;
    private Sword s;
    private boolean isDead;
    private String[] inventory;
    private boolean dodged;

    public Player(String name){
        this.name = name;
        maxHp = 100;
        hp = 100;
        gold = 0;
        hasHpPot = true;
        s = new Sword("basic sword");
        isDead = false;
        inventory = new String[0];
        dodged = false;
    }

    //getters / setters
    public boolean getIsDead(){
        return isDead;
    }
    public String getName(){
        return name;
    }
    public Sword getSword(){
        return s;
    }
    public boolean isDodged(){
        return dodged;
    }
    public int getHp(){
        return hp;
    }
    public int getGold(){
        return gold;
    }
    public boolean getHasHpPot(){
        return hasHpPot;
    }
    public String getInventory(){
        String str = "";
        for (String item : inventory){
            str += item + ", ";
        }
        if (str.isEmpty()) {
            str += "none";
        }else{
            str = str.substring(0, str.length() - 2);
        }
        return str;
    }
    public void setHp(int hp){
        this.hp = hp;
    }
    public void setGold(int g){
        gold = g;
    }
    public void obtainHpPot(){
        hasHpPot = true;
    }

    //calculates percent health player has left
    public int percentHp(){
        return (int) Math.round(hp * 1.0 / maxHp * 100);
    }

    //determines player damage towards dragon
    public void attack(Dragon d){
        int dmg = s.getAtkDmg();
        if (Math.random() <= 0.2){
            int crit = (int) (Math.random() * 10 + 1);
            System.out.println("You hit it's eye, doing critical damage");
            dmg += crit;
        }
        d.receiveDmg(dmg);
        System.out.println("You dealt " + Colors.WHITE + dmg + " damage " + Colors.RESET + "to dragon " + d.getDragNum());
        System.out.println("Dragon " + d.getDragNum() + " has " + Colors.RED + d.getHp() + " health left" + Colors.RESET);
        if (d.getHp() == 0){
            d.getReward();
            try {
                Thread.sleep(3000);  // 2000 milliseconds, or 2 seconds
            } catch (Exception e) {
                System.out.println("error");
            }
            Colors.clearScreen();
        }
    }

    //determines if player dodges and the damage received
    public void receiveDmg(Dragon drag,int d){
        dodged = false;
        if (Math.random() < s.getDodgeChance()){
            System.out.println(Colors.BLUE + "You dodged the attack!" + Colors.RESET);
            dodged = true;
        }else {
            hp -= d;
            if (hp <= 0) {
                isDead = true;
                hp = 0;
            }
            System.out.println("Dragon " + drag.getDragNum() + " did " + Colors.WHITE + d + " damage" + Colors.RESET + " to you.");
        }
        System.out.println("You have " + Colors.RED + hp + " health remaining." + Colors.RESET);
        if (hp == 0){
            String str = "You have died ";
            str += new String(Character.toChars(0x1F480));
            System.out.println(str);
            try {
                Thread.sleep(2000);  // 2000 milliseconds, or 2 seconds
            } catch (Exception e) {
                System.out.println("error");
            }
            Colors.clearScreen();
        }
    }

    //calculates the health gained from health potion
    public void useHpPot(Room room){
        int heal = (maxHp - hp) / 2;
        hp += heal;
        System.out.println("You healed " + Colors.RED + heal + " health!" + Colors.RESET + " You now have " + Colors.RED + hp + " health." + Colors.RESET);
    }
    public void useHpPot(){
        if (hasHpPot){
            hasHpPot = false;
            int hpGain = (maxHp - hp) / 2;
            hp += hpGain;
            System.out.println("You healed " + Colors.RED + hpGain + " health." + Colors.RESET + " You now have " + Colors.RED + hp + " health." + Colors.RESET);
        }else{
            System.out.println("You don't have a health potion!");
        }
    }
    /* TO BE IMPLEMENTED IN THE FUTURE (maybe)
    public void addItem(String item){
        String[] newInventory = new String[inventory.length + 1];
        for (int i = 0; i < inventory.length; i ++){
            newInventory[i] = inventory[i];
        }
        newInventory[inventory.length] = item;
        inventory = newInventory;
    }
    public void deleteItem(String item){
        for (int i = 0; i < inventory.length; i ++){
            if (inventory[i].equals(item)){
                inventory[i] = null;
            }
        }
        String newInventory[] = new String[inventory.length - 1];
        int idx = 0;
        for (String i : inventory){
            if (i != null){
                newInventory[idx] = i;
                idx ++;
            }
        }
        inventory = newInventory;
    }
     */

    //returns player stats
    public void playerInfo(){
        String stats = Colors.PURPLE + "Player name: " + name + Colors.RESET;
        stats += "\n" + Colors.RED + "Health: " + hp + " / " + maxHp + Colors.RESET;
        stats += "\n" + Colors.YELLOW + "Gold: " + gold + Colors.RESET;
        stats += "\nHas health potion: " + hasHpPot;
        stats += "\nSword name: " + s.getName();
        stats += "\nSword damage: " + s.getAtkDmg();
        stats += Colors.BLUE + "\nDodge chance: " + s.getDodgeChance() * 100 + "%" + Colors.RESET;
        stats += "\nInventory: " + getInventory();
        System.out.println(stats);
    }
}
