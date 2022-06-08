public class Item {
    private String name = "";
    private String description = "";

    public Item(String name, String description){
        this.name = name;
        this.description = description;
    }

    public Item(String name){
        this.name = name;
        this.description = "";
    }

    public String getName(){
        return this.name;
    }

    public void setName(String input){
        this.name = input;
    }

    public String getDescription(){
        return this.description;
    }

    public void setDescription(String input){
        this.description = input;
    }

}
