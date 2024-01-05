public class Dragon {
    private int level;
    private int hp;
    private int dmg;
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
        if (level == 1){
            dmg = 10;
        }else if (level == 2){
            dmg = 15;
        }else{
            dmg = 20;
        }
    }
}
