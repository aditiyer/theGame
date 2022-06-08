public class Wumpus extends GenericEntity{
    public Wumpus(String n, String d, Graph.Room r) {
        super(n, d, r);
    }

    @Override
    public void move() {
        //Wumpus moves away from the Player
        String NoNoRoom = Graph.playerRoom.getName();
        Graph.Room nextRoom = Graph.Room.nonPlayerRoom(NoNoRoom);
        System.out.println("Wumpus moved to " + nextRoom.getName());
    }
}
