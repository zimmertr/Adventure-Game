import java.util.*;

/**
 * Class Room is used to create new rooms for the game
 * 
 * @TJ Zimmerman
 * @1.01
 */

public class Room
{                                                                           
    //String to hold a description of the room.
    private String description;

    //Item Object to be the newly created item.
    private Item item;

    //Hasmap to add neighbors to your current room.
    private HashMap <String, Room> myNeighbors;

    /*******************************************************************
     * Constructor iniatalizes the variables and the hashmap.
     * @param d: description of room.
     * @param i: item in room.
     * @return none
     ******************************************************************/
    public Room (String d, Item i)
    {
        this.description = d;
        this.item = i;

        myNeighbors = new HashMap <String, Room> ();
    }

    /*******************************************************************
     * Returns the room description.
     * @param none
     * @return description: Return description of room.
     ******************************************************************/
    public String getDescription()
    {
        return description;
    }

    /*******************************************************************
     * Returns the item that was made.
     * @param none
     * @return item: returns the item in the room.
     ******************************************************************/
    public Item getItem()
    {
        return item;
    }

    /*******************************************************************
     * Adds a new item to added to a room.
     * @param i: item to be added to the room.
     * @return none
     ******************************************************************/
    public void addItem (Item i)
    {
        this.item = i;
    }

    /*******************************************************************
     * Returns true if the current room has an item.
     * @param none
     * @return item: current item in the room.
     ******************************************************************/
    public boolean hasItem()
    {
        return item != null;
    }

    /*******************************************************************
     * Adds a neighbor to the hashmap.
     * @param dir: direction that neighbor is.
     * @param r: room name of neighbor.
     * @return none
     ******************************************************************/
    public void addNeighbor (String dir, Room r)
    {
        myNeighbors.put(dir, r);
    }

    /*******************************************************************
     * Returns the neighbor in a certain given direction.
     * @param dir: returns the neighbor in the given direction
     * @return none
     ******************************************************************/
    public Room getNeighbor (String dir)
    {
        return myNeighbors.get(dir);
    }

    /*******************************************************************
     * Removes the item from the current Item object and makes it fresh.
     * @param none
     * @return itemHelper: Blanks out Item so more things can be used 
     *         there. itemHelper takes over item's item.
     ******************************************************************/
    public Item removeItem()
    {
        Item itemHelper = item;
        item = null;

        return itemHelper;
    }

    /*******************************************************************
     * Returns the longdescription of an item.
     * @param none
     * @return: returns what item is in the room if there are any.
     ******************************************************************/
    public String getLongDescription()
    {
        String longDes = "";
        /*
         * Loop checks whether or not there are items in the room and if
         * there are it returns them.
         */
        if (item == null)
        {
            longDes = "\nCurrent Room: " + description + 
            "\n Items in Room: Nothing";
        }
        else if (item != null)
        {
            longDes = "\nCurrent Room: " + description + 
            "\n Items in Room: " + item.description + ".";
        }

        return longDes;
    }

    /*******************************************************************
     * Main Method used to test the functionality of the room.
     * @param none
     * @return none
     ******************************************************************/
    public static void main(String args[])
    {
        Item snack = new Item("Snack", "Snickers Candy Bar", 4,
                true, false, true);

        Room room = new Room("Room 1",snack);
        Room room2 = new Room("Room 2", null);

        System.out.println(room.getDescription());
        System.out.println(room.getItem());
        System.out.println(room.hasItem());

        room.addNeighbor( "south", room2);

        System.out.println(room.getNeighbor("south"));
        System.out.println(room.removeItem());
        System.out.println(room.getLongDescription());
    }
}
