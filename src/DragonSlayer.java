import java.util.Scanner;
public class DragonSlayer {
    public static int highScore = 0;
    public static int score = 0;
    private Scanner scan = new Scanner(System.in);
    private Room r;
    private Player p;
    private boolean replay;
    public DragonSlayer(){
        replay = true;
    }
    public void play(){
        while (replay){
            System.out.println(mainMenu());
            menuOption();
        }
    }
    public String welcomeMessage(){
        String message = "Welcome, " + p.getName();
        message += "\nYou are about to begin your new adventure after hearing the salary from being an adventurer. You equip a sword inherited by your old man and begin walking into a dungeon well known for their treasure and dragon.";
        message += "\nNo one has conquered it solo yet... will you be the first?";
        return message;
    }
    public String mainMenu(){
        String menu = "Main menu: ";
        menu += "\n(N)ew game";
        menu += "\n(V)iew top score";
        menu += "\n(Q)uit";
        return menu;
    }
    public void menuOption(){
        System.out.print("Choose your option: ");
        String option = scan.nextLine().toLowerCase();
        while (!option.equals("n") && !option.equals("v") && !option.equals("q")){
            System.out.print("Enter a valid option: ");
            option = scan.nextLine().toLowerCase();
        }
        if (option.equals("n")){
            System.out.print("What is your name? ");
            p = new Player(scan.nextLine());
            System.out.println(welcomeMessage());
            r = new Room(p);
            System.out.println(r.roomInfo());
            while (!p.getIsDead() && Room.roomNum < 6){
                turns();
            }
            if (score > highScore){
                highScore = score;
            }
        }else if (option.equals("v")){
            System.out.println("Your current high score is " + highScore);
        }else{
            replay = false;
            System.out.println("Your high score is " + highScore);
        }
    }
    public String options(){
        String options = "Options: ";
        options += "\n(A)ttack";
        options += "\n(U)se health potion";
        options += "\n(S)ee stats";
        options += "\n(R)oom Search";
        options += "\n(N)ext Room";
        return options;
    }
    public void chooseOption(){
        System.out.print("Choose your option: ");
        String option = scan.nextLine().toLowerCase();
        while (!option.equals("a") && !option.equals("u") && !option.equals("s") && !option.equals("r") && !option.equals("n")){
            System.out.print("Enter a valid option: ");
            option = scan.nextLine().toLowerCase();
        }
        if (option.equals("a")){
            System.out.println(r.dragonsLeft());
            System.out.print("Select which dragon to attack: ");
            int num = scan.nextInt();
            while (num < 1 || num > r.getDragons().length){
                System.out.print("Enter a valid option: ");
                num = scan.nextInt();
            }
            p.attack(r.getDragons()[num-1]);
            Room.nextTurn();
        }else if (option.equals("u")){
            p.useHpPot();
            Room.nextTurn();
        }else if (option.equals("s")){
            p.playerInfo();
        }else if (option.equals("r")){
            r.search();
            Room.nextTurn();
        }else{
            nextRoom();
        }
    }
    public void nextRoom(){
        r.checkAllDead();
        if (r.getAllDead()) {
            Room.roomNum++;
            System.out.println("You move on to the next room.");
            r = new Room(p);
            System.out.println(r.roomInfo());
        }else{
            System.out.println("You have not defeated every dragon in the room.");
        }
    }
    public void turns(){
        if (Room.turn % 2 == 0){
            System.out.println(options());
            chooseOption();
        }else{
            r.dragonAttack();
        }
    }
}
