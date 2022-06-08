import java.util.ArrayList;
import java.util.List;

public class Graph {
    private static List<Room> nodes;
    public static ArrayList<GenericEntity> allEntities = new ArrayList<>();
    public static Room playerRoom;
    public Graph(){

        nodes = new ArrayList<>();
    }

    public void addRoom(String name) {
        nodes.add(new Room(name));
    }

    public void addRoom(Room r) {
        nodes.add(r);
    }

    //directed = one way
    public void addDirectedEdge(String name1, String name2) {
        Room n1 = getRoom(name1);
        Room n2 = getRoom(name2);
        n1.addNeighbor(n2);
    }

    //undirected = two way
    public void addUndirectedEdge(String name1, String name2) {
        Room n1 = getRoom(name1);
        Room n2 = getRoom(name2);
        n1.addNeighbor(n2);
        n2.addNeighbor(n1);
    }

    public void addUndirectedEdge(Room name1, Room name2) {
        name1.addNeighbor(name2);
        name2.addNeighbor(name1);
    }

    public static boolean doesRoomAlreadyExist(String name){
        for(int i = 0; i < nodes.size(); i++){
            if(nodes.get(i).getName().equals(name)){
                return true; //it already exists
            }
        }
        return false; //it doesn't exist yet
    }

    public Room getRoom(String name) {
        if(name == null){
            return null;
        }
        for(int i = 0; i < nodes.size(); i++){
            Room n = nodes.get(i);
            if(n.getName().equals(name)){
                return n;
            }
        }
        return null;
    }

    public void connectAllRooms(ArrayList<Room> roomList) {
        for(int i = 0; i < roomList.size(); i++){
            for(int j = i+1; j < roomList.size(); j++){
                addUndirectedEdge(roomList.get(i), roomList.get(j));
            }
        }
    }


    //Node class begins
    public static class Room {
        private String name;
        private ArrayList<Room> neighbors;
        ArrayList<Item> items = new ArrayList<>(); // all the items in the room
        ArrayList<GenericEntity> entities = new ArrayList<>(); //all the entities in a room

        Room(String name){
            neighbors = new ArrayList<>();
            this.name = name;
        }

        public static Room getRandomRoom() {
            int val = (int) (Math.random() * nodes.size());
            return nodes.get(val);
        }

        public static Room nonPlayerRoom(String BannedRoom) {
            for(int i = 0; i < nodes.size(); i++){
                if(!nodes.get(i).getName().equals(BannedRoom)){
                    return nodes.get(i);
                }
            }
            return null; //RETURNING NULL
        }


        private void addNeighbor(String futureNeighbor){
            if(futureNeighbor != null){
                neighbors.add(new Room(futureNeighbor));
            }
        }

        public void addNeighbor(Room futureNeighbor){
            if(futureNeighbor != null){
                neighbors.add(futureNeighbor);
            }
        }

        public String getNeighborNames(){
            String names = "";
            for(int i = 0; i < neighbors.size(); i++){
                names = names + neighbors.get(i).getName();
                if( i < neighbors.size()-1){
                    names = names + ", ";
                }
            }
            return names;
        }

        public Room getNeighbor(String name){
            if(name == null){
                return null;
            }

            for(int i = 0; i < neighbors.size(); i++){
                Room cNode = neighbors.get(i);
                if(cNode.getName().equals(name)){
                    return cNode;
                }
            }
            return null;
        }

        public String displayItems(){
            String itemList = "";
            for(int i = 0; i < items.size(); i++){
                String nameOfItem = items.get(i).getName();
                itemList += nameOfItem + " ";
            }
            if(itemList.length() == 0){
                String noItemsMessage = "There are no items in this room.";
                return noItemsMessage;
            }
            return itemList;
        }

        public void addItem(String name){
            Item item = new Item(name);
            items.add(item);
        }

        public void removeItem(String name){
            for(int i = 0; i < items.size(); i++){
               if(items.get(i).getName().equals(name)){
                   items.remove(i);
               }
               else{ ///the item isn't in the room so there's nothing to remove
                   return;
               }
            }
        }

        public void addEntity(GenericEntity g){
            entities.add(g);
            allEntities.add(g);
        }

        public void removeEntity(GenericEntity g){
            entities.remove(g);
            allEntities.remove(g);
        }

        public String displayEntities(){
            String entityList = "";
            for(int i = 0; i < entities.size(); i++){
                String nameOfEntity = entities.get(i).getName();
                entityList += nameOfEntity + " ";
            }
            if(entityList.length() == 0){
                String noEntityMessage = "There are no entities in this room.";
                return noEntityMessage;
            }
            return entityList;
        }

        public String getName(){
            return name;
        }

    }
}
