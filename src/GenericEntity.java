public abstract class GenericEntity implements Entity{
    private String name = " ";
    private String description = " ";
    private Graph.Room currentRoom;

    public GenericEntity(String n,String d, Graph.Room currRoom){
        this.name = n;
        this.description = d;
        this.currentRoom = currRoom;
    }

    public Graph.Room getRoom(){
        return currentRoom;
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public void setRoom(Graph.Room nextRoom) {
        currentRoom = nextRoom;
    }

    public abstract void move();

}
