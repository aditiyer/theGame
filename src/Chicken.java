public class Chicken extends GenericEntity{


    public Chicken(String n, String d, Graph.Room r) {
        super(n, d, r);
    }

    @Override
    public void move() {
        //chickens move randomly
        getRoom().removeEntity(this);
        Graph.Room nextRoom = Graph.Room.getRandomRoom();
        setRoom(nextRoom);
        System.out.println("Chicken moved to " + nextRoom.getName());
    }


}
