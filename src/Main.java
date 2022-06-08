import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main{
    public static void main(String[] args){

        Graph g = setUpGame();
        Player p = new Player("player");
        p.setCurrentRoom((g.getRoom("hall")));
        String response = "";
        Scanner s = new Scanner(System.in);

        System.out.println("Type \"go <roomname>\" to go to a room. " +
                "\n Type \"look\" to see all the rooms you could go to. " +
                "\n Type \"add <roomname>\" to add a room. " +
                "\n Type \"what\" to see what items are in the room you are in. " +
                "\n Type \"take <itemname>\" to take the item. + " +
                "\n Type \"drop <itemname>\" to drop the item. + " +
                "\n Type \"see\" to see all the entities in the room you are in. + " +
                "\n Type \"quit\" to quit.");

        do{
            System.out.println("You are in the " + p.getCurrentRoomName());
            System.out.println("What do you want to do? >");
            for(int i = 0; i < Graph.allEntities.size(); i++){
                GenericEntity entity = Graph.allEntities.get(i);
                System.out.println(entity.getName());
                entity.move();
            }
            response = s.nextLine();
            if(response.startsWith("go")){
                String roomname = response.substring(3);
                if(Graph.doesRoomAlreadyExist(roomname)){
                    Graph.Room nextRoom = p.getCurrentRoom().getNeighbor(roomname);
                    p.setCurrentRoom(nextRoom);
                }
                else{
                    System.out.println("That room doesn't exist.");
                }
            }

            else if(response.startsWith("look")){
                System.out.println(p.getCurrentRoom().getNeighborNames());
            }

            else if(response.startsWith("add")){
                if(setUpGame().doesRoomAlreadyExist(response.substring(3))){
                    //if the room doesn't already exist we can add it to the map
                    String roomname = response.substring(3);
                    setUpGame().addRoom(roomname);
                }
            }

            else if(response.startsWith("quit")){
                System.out.println("Thanks for playing!");
            }

            else if(response.startsWith("what")){
                System.out.println(p.getCurrentRoom().displayItems());
            }

            else if(response.startsWith("take")){
                p.addItemToInventory(response.substring(5));
            }

            else if(response.startsWith("drop")) {
                p.removeItemFromInventory(response.substring(5));
            }

            else if(response.startsWith("see")){
                System.out.println(p.currentRoom.displayEntities());
            }
        }
        while(!response.equals("quit"));
    }

    private static Graph setUpGame(){
        Graph g = new Graph();
        Graph.Room hall = new Graph.Room("hall");
        Graph.Room closet = new Graph.Room("closet");
        Graph.Room dungeon = new Graph.Room("dungeon");
        Graph.Room bedroom = new Graph.Room("bedroom");
        Graph.Room livingRoom = new Graph.Room("livingRoom");
        Graph.Room bathroom = new Graph.Room("bathroom");


        g.addRoom(hall);
        g.addRoom(closet);
        g.addRoom(dungeon);
        g.addRoom(bedroom);
        g.addRoom(livingRoom);
        g.addRoom(bathroom);

        ArrayList<Graph.Room> allRooms = new ArrayList<Graph.Room>(Arrays.asList(hall, closet, dungeon, bedroom, livingRoom, bathroom));
        g.connectAllRooms(allRooms);

        hall.addItem("toothpaste");
        //GenericEntity chicken = new Chicken("chicken","a regular chicken",hall);
        //GenericEntity chicken1 = new Chicken("chicken","a regular chicken",hall);
        GenericEntity chicken2 = new Chicken("chicken","a regular chicken",hall);
       // hall.addEntity(chicken);
        //hall.addEntity(chicken1);
        hall.addEntity(chicken2);

        closet.addItem("book");
        GenericEntity popstar = new PopStar("Ariana Grande", "a famous popstar",closet);
        closet.addEntity(popstar);

        dungeon.addItem("skull");
        GenericEntity wumpus = new Wumpus("wumpus","a special creature",bathroom);
        dungeon.addEntity(wumpus);

        return g;
    }
}
