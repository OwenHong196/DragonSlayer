public class Room {
    public static int roomNum = 1;
    private String name;
    private String description;
    private boolean hpPot;
    private boolean searched;
    private Player p;
    private Dragon[] dragons;
    public Room(Player p){
        this.p = p;
        roomInfo();
        searched = false;
        int numDrags = (int) (Math.random() * 3 + 1);
        if (roomNum == 1){
            dragons = new Dragon[2];
            for (int i = 0; i < dragons.length; i ++){
                dragons[i] = new Dragon();
            }
        }else{
            dragons = new Dragon[numDrags];
            for (int i = 0; i < dragons.length; i ++){
                dragons[i] = new Dragon();
            }
        }
        if (Math.random() <=0.25){
            hpPot = true;
        }else{
            hpPot = false;
        }
    }
    public void roomInfo(){
        if (roomNum == 1){
            name = "entrance";
            description = "Two dragons stands in the way of your new journey";
        }else if (roomNum == 2){
            name = "";
            description = "";
        }else if (roomNum == 3){
            name = "";
            description = "";
        }else if (roomNum == 4){
            name = "Sisyphus' staircase";
            description = "An endless staircase going up... ";
            description += "\n*you flashback to a guy pushing the giant rock*";
        }else if (roomNum == 5){
            name = "¿the end?";
            description = "A dark room with ominous eyes staring at you";
        }
    }
}
