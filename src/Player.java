public class Player {
    private String name;
    private int maxHp;
    private int hp;
    private int gold;
    private boolean hasHpPot;
    private Sword s;
    private boolean isDead;
    private String[] inventory;

    public Player(String name){
        this.name = name;
        maxHp = 100;
        hp = 100;
        gold = 0;
        hasHpPot = true;
        s = new Sword("basic sword");
        isDead = false;

    }
    public boolean getIsDead(){
        return isDead;
    }
    public String getName(){
        return name;
    }
    public Sword getSword(){
        return s;
    }
    public int getHp(){
        return hp;
    }
    public void setHp(int hp){
        this.hp = hp;
    }
    public int getMaxHp(){
        return maxHp;
    }
    public void setGold(int g){
        gold = g;
    }
    public int getGold(){
        return gold;
    }
    public boolean getHasHpPot(){
        return hasHpPot;
    }
    public int percentHp(){
        return Math.round(hp / maxHp);
    }
    public void obtainHpPot(){
        hasHpPot = true;
    }
    public String getInventory(){
        String str = "";
        for (String item : inventory){
            str += item + ", ";
        }
        str = str.substring(0, str.length() - 2);
        return str;
    }
    public void attack(Dragon d){
        int dmg = s.getAtkDmg();
        d.receiveDmg(dmg);
        System.out.println("You dealt " + dmg + " damage to dragon " + d.getDragNum());
        System.out.println("Dragon " + d.getDragNum() + " has " + d.getHp() + " health left");
    }
    public void receiveDmg(int d){
        if (Math.random() < s.getDodgeChance()){
            System.out.println("You dodged the attack!");
        }else {
            hp -= d;
            if (hp < 0) {
                isDead = true;
            }
        }
    }
    public void useHpPot(Room room){
        hp += (maxHp - hp) / 2;
    }
    public void useHpPot(){
        if (hasHpPot){
            hasHpPot = false;
            hp += (maxHp - hp) / 2;
        }else{
            System.out.println("You don't have a health potion!");
        }
    }
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
    public String playerInfo(){
        String stats = "Player name: " + name;
        stats += "\nHp: " + hp + " / " + maxHp;
        stats += "\nGold: " + gold;
        stats += "\nHas hp potion: " + hasHpPot;
        stats += "\nSword name: " + s.getName();
        stats += "\nSword damage: " + s.getAtkDmg();
        stats += "\nDodge chance: " + s.getDodgeChance() * 100 + "%";
        stats += "\nInventory: " + getInventory();
        return stats;
    }
}
