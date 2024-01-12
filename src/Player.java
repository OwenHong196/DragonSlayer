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
        s = new Sword();
        isDead = false;

    }
    public void setGold(int g){
        gold = g;
    }
    public int getGold(){
        return gold;
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
    public int attack(){
        return s.getAtkDmg();
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
        stats += "\nSword damage: " + s.getAtkDmg();
        stats += "\nDodge chance: " + s.getDodgeChance() * 100 + "%";
        stats += "\nInventory: " + getInventory();
        return stats;
    }
}
