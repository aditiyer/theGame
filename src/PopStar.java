public class PopStar extends GenericEntity{
    public PopStar(String n, String d, Graph.Room r) {
        super(n, d, r);
    }

    @Override
    public void move() {
        //PopStar moves towards from the Player
        Graph.Room nextRoom = Graph.playerRoom;
        setRoom(nextRoom);
        System.out.println("ARIANA moved to " + nextRoom.getName());

    }
}
