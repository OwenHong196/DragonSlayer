import java.util.Scanner;
public class DragonSlayer {
    public static int highScore = 0;
    public static int score;
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

    //message to welcome the user
    public String welcomeMessage(){
        String message = Colors.PURPLE + "Welcome, " + p.getName();
        message += "\nYou are about to begin your new adventure after hearing the salary from being an adventurer.";
        message += "\nYou equip a sword inherited by your old man and begin walking into a dungeon well known for their treasure and dragon.";
        message += "\nNo one has conquered it solo yet... will you be the first?" + Colors.RESET;
        return message;
    }

    //main menu display
    public String mainMenu(){
        String menu = Colors.BLACK + "Main menu: ";
        menu += "\n(N)ew game";
        menu += "\n(V)iew top score";
        menu += "\n(Q)uit" + Colors.RESET;
        return menu;
    }

    //decides action based on user input during menu
    public void menuOption(){
        System.out.print("Choose your option: ");
        String option = scan.nextLine().toLowerCase();
        while (!option.equals("n") && !option.equals("v") && !option.equals("q")){
            System.out.print("Enter a valid option: ");
            option = scan.nextLine().toLowerCase();
        }
        if (option.equals("n")){
            score = 0;
            Room.roomNum = 1;
            System.out.print("What is your name? ");
            p = new Player(scan.nextLine());
            System.out.println(welcomeMessage());
            try {
                Thread.sleep(5000);  // 2000 milliseconds, or 2 seconds
            } catch (Exception e) {
                System.out.println("error");
            }
            Colors.clearScreen();
            r = new Room(p);
            System.out.println(r.roomInfo());
            while (!p.getIsDead() && Room.roomNum < 6){
                turns();
            }
            if (Room.roomNum == 6){
                score += 200;
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

    //options display
    public String options(){
        String options = Colors.BLACK + "Options: ";
        options += "\n(A)ttack";
        options += "\n(U)se health potion";
        options += "\n(S)ee stats";
        options += "\n(D)ragon stats";
        options += "\n(R)oom Search";
        options += "\n(N)ext Room" + Colors.RESET;
        return options;
    }

    //decides action based on user input during menu
    public void chooseOption(){
        System.out.print("Choose your option: ");
        String option = scan.nextLine().toLowerCase();
        while (!option.equals("a") && !option.equals("u") && !option.equals("s") && !option.equals("r") && !option.equals("n") && !option.equals("d")){
            System.out.print("Enter a valid option: ");
            option = scan.nextLine().toLowerCase();
        }
        Colors.clearScreen();
        if (option.equals("a")){
            if (r.getAllDead()){
                System.out.println("There are no more dragons alive");
            }else {
                System.out.println(r.dragonsLeft());
                System.out.print("Select which dragon to attack: ");
                int num = -1;
                while (num == -1) {
                    try {
                        num = scan.nextInt();
                        while (num < 1 || num > r.getDragons().length || r.getDragons()[num - 1].getIsDead()) {
                            if (num >= 1 && num <= r.getDragons().length && r.getDragons()[num - 1].getIsDead()) {
                                System.out.println("The dragon is already dead");
                                System.out.println(r.dragonsLeft());
                                System.out.print("Choose another dragon to attack: ");
                                num = scan.nextInt();
                            } else {
                                try {
                                    System.out.print("Enter a valid option: ");
                                    num = scan.nextInt();
                                } catch (Exception e) {
                                    System.out.println("Not an option, try again");
                                    scan.nextLine();
                                    num = -1;
                                }
                            }
                        }
                    } catch (Exception e) {
                        System.out.print("Not an option, try again: ");
                        scan.nextLine();
                        num = -1;
                    }
                }
                scan.nextLine();
                p.attack(r.getDragons()[num - 1]);
                Room.nextTurn();
            }
        }else if (option.equals("u")){
            p.useHpPot();
            Room.nextTurn();
        }else if (option.equals("s")){
            Colors.clearScreen();
            p.playerInfo();
            try {
                Thread.sleep(3000);  // 2000 milliseconds, or 2 seconds
            } catch (Exception e) {
                System.out.println("error");
            }
            Colors.clearScreen();
        }else if (option.equals("r")){
            r.search();
            Room.nextTurn();
        }else if (option.equals("d")){
            Colors.clearScreen();
            r.dragonStats();
            try {
                Thread.sleep(2500);  // 2000 milliseconds, or 2 seconds
            } catch (Exception e) {
                System.out.println("error");
            }
            Colors.clearScreen();
        }else{
            nextRoom();
        }
    }

    //determines if player or dragon does an action
    public void turns(){
        if (Room.turn % 2 == 0){
            System.out.println(options());
            chooseOption();
        }else{
            r.checkAllDead();
            if (r.getAllDead()){
                Room.nextTurn();
            }else{
                r.dragonAttack();
                try {
                    Thread.sleep(2000);  // 2000 milliseconds, or 2 seconds
                } catch (Exception e) {
                    System.out.println("error");
                }
                Colors.clearScreen();
            }
        }
    }
    //moves to next room if possible
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
}
