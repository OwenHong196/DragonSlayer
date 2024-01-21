import java.util.Scanner;
public class Room {
    public static int roomNum = 1;
    private String name;
    private String description;
    private boolean hpPot;
    private boolean searched;
    private Player p;
    private Dragon[] dragons;
    private boolean allDead;
    public static int turn;
    private Scanner scan = new Scanner(System.in);
    public Room(Player p){
        turn = 0;
        this.p = p;
        searched = false;
        int numDrags = (int) (Math.random() * 3 + 1);
        if (roomNum == 1){
            dragons = new Dragon[2];
            for (int i = 0; i < dragons.length; i ++){
                dragons[i] = new Dragon(i+1,p);
            }
        }else{
            dragons = new Dragon[numDrags];
            for (int i = 0; i < dragons.length; i ++){
                dragons[i] = new Dragon(i+1,p);
            }
        }
        if (Math.random() <=0.25){
            hpPot = true;
        }else{
            hpPot = false;
        }
        allDead = false;
    }
    public static void nextTurn(){
        turn ++;
    }
    public boolean getAllDead(){
        return allDead;
    }
    public Dragon[] getDragons(){
        return dragons;
    }
    public String roomInfo(){
        if (roomNum == 1){
            name = "Entrance";
            description = "Two dragons stands in the way of your new journey";
        }else if (roomNum == 2){
            name = "Library";
            description = "The smell of burnt paper fills the room";
        }else if (roomNum == 3){
            name = "Chess Room";
            description = "A room of large sized chess pieces, likely used by giant creatures";
        }else if (roomNum == 4){
            name = "Sisyphus' Staircase";
            description = "An endless staircase going up... ";
            description += "\n*you flashback to a guy pushing the giant rock*";
        }else if (roomNum == 5){
            name = "¿The End?";
            description = "A dark room with ominous eyes staring at you";
        }
        String info = "Room " + roomNum + " : " + name;
        info += "\n" + description;
        info += "\nYou encountered " + dragons.length + " dragons!";
        return info;
    }
    public void search(){
        if (!searched){
            if (hpPot){
                System.out.println("You found a hp potion!");
                System.out.print("Do you want to (U)se or (S)ave it? ");
                String option = scan.nextLine().toLowerCase();
                while (!option.equals("u") && !option.equals("s")){
                    System.out.print("Enter a valid option: ");
                    option = scan.nextLine().toLowerCase();
                }
                if (option.equals("u")){
                    System.out.println("You used the hp potion.");
                    p.useHpPot(this);
                }else{
                    if (p.getHasHpPot()){
                        System.out.println("You already have a hp potion so you used it instead.");
                        p.useHpPot(this);
                    }else{
                        p.obtainHpPot();
                    }
                }
            }else{
                System.out.println("You found nothing.");
            }
        }else{
            System.out.println("You already searched this room!");
        }
    }
    public String dragonsLeft(){
        String list = "Dragons remaining:";
        for (int i = 1; i <= dragons.length; i ++){
            if (dragons[i-1] != null){
                list += "\nDragon " + i;
            }
        }
        return list;
    }
    public void checkAllDead(){
        boolean check = true;
        for (Dragon d : dragons){
            if (d != null){
                check = false;
            }
        }
        if (check == true){
            allDead = true;
        }
    }
    public void dragonAttack(){
        int idx = 0;
        while (idx < dragons.length && dragons[idx] == null){
            idx ++;
        }
        if (dragons[idx] != null) {
            dragons[idx].attack();
        }else{
            System.out.println("All dragons are defeated.");
        }
    }
}
