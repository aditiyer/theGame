import java.util.ArrayList;

public class Player {
    private String name;
    private ArrayList<Item> items = new ArrayList<>();
    Graph.Room placeholder = new Graph.Room("placeholder");
    Graph.Room currentRoom = placeholder;

    public Player(String n){
        name = n;
    }

    public void addItemToInventory(String name){
        String itemsInMyRoom = currentRoom.displayItems();
        if(itemsInMyRoom.contains(name)){
            Item i = new Item(name);
            items.add(i);
            System.out.println("You took the " + name);
            //We have to also remove it from the room we're in
            currentRoom.removeItem(name);
        }
        else{ //what we're trying to pick up isn't in the room
            System.out.println("You can't pick that up, it doesn't exist.");
        }
    }

    public void removeItemFromInventory(String name){
        for(int i = 0; i < items.size(); i++){
            if(items.get(i).getName().equals(name)){ //it is in our inventory
                items.remove(i);
                System.out.println("You dropped the " + name + " in the " + currentRoom.getName());
                //we also have to put it back in the room we're currently in
                currentRoom.addItem(name);
            }
        }
    }

    public String displayInventory(){
        String allItemsInTheInventory = "";
        for(int i = 0; i < items.size(); i++){
            allItemsInTheInventory += items.get(i) + " ";
        }
        return allItemsInTheInventory;
    }

    public Graph.Room getCurrentRoom(){
        return currentRoom;
    }

    public String getCurrentRoomName(){
        return currentRoom.getName();
    }

    public void setCurrentRoom(Graph.Room r){
        moveToRoom(r);
        currentRoom = r;
        Graph.playerRoom = r;
    }

    public boolean moveToRoom(Graph.Room r){
        //if the room we're trying to move to is adjacent to our current room
        if(currentRoom.getNeighborNames().contains(r.getName())){
            Graph.playerRoom = r;
            currentRoom = r;
            return true;
        }
        else{
            return false;
        }
    }

}
