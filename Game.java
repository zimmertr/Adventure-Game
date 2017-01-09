import java.util.*;

/***********************************************************************
 * Class Game is basically where all the logic for the game resides.
 * 
 * @TJ Zimmerman
 * @1.01
 **********************************************************************/
public class Game
{
    //String to hold the constantly updated information message.
    private String message;

    //Room Objects to deal with moving around.
    private Room currentLocation;
    private Room previousLocation;
    //Special Room object that forbids or allows access to a certain 
    //room depending on variables.
    private Room darkRoom;

    //ArrayList to hold your current inventory of items.
    private ArrayList<Item> items;    

    //Checks whether the flashlight is on or not.
    private boolean flashlightOn = false;
    //Checks whether you have backed up already before moving forward.
    private boolean hasBackedUp = false;
    //Checks whether the laptop has been destroyed.
    private boolean laptopDestroyed = false;
    //Checks whether you have been scared to death, or not.
    private boolean scaredDeath = false;
    //Checks whether or not you have collected the laptop, yet.
    private boolean collectedLaptop = false;
    //Checks whether or not your flashlight has been broken, yet.
    private boolean flashlightBroken = false;

    /*******************************************************************
     * Constructor initalizes the variables.
     * @param none
     * @return none
     ******************************************************************/
    public Game()
    {
        createRooms();

        items = new ArrayList<Item>();

        setIntroMessage();

    }

    /*******************************************************************
     * This method is what creates all of the rooms and items in the
     * game. It also sets your current location and which room is the 
     * dark room.
     * @param none
     * @return none
     ******************************************************************/
    private void createRooms()
    {
        //Create new rooms.
        Room outside, a1, a2, b1, b2, c1, c2, d1, d2;

        //Provide information for the new items that are to be created.
        Item table = new Item("table", "Heavy Oak Table", 100,
                false, false, false);
        Item flashlight = new Item("flashlight", "A Blue Flashlight", 2, 
                false, true, true);
        Item book = new Item("book", "Hardcover Book", 3,
                false, false, true); 
        Item snack = new Item("snack", "Snickers Candy Bar", 1,
                true, false, true);
        Item laptop = new Item("laptop", "Dell Laptop", 14,
                false, false, true) ;

        //Provide information for the new rooms that are to be created
        outside = new Room( "Outside", null);
        a1 = new Room("A1 MAK", null);
        a2 = new Room("A2 MAK", null);

        b1 = new Room("B1 MAK and there is an elevator.",table); 
        b2 = new Room("B2 MAK and there is an elevator.",null);

        c1 = new Room("C1 MAK and there is an elevator.",flashlight);
        c2 =new Room("C2 MAK and there is an elevator.",book);

        d1 = new Room( "D1 MAK",snack);
        d2 = new Room( "D2 MAK",laptop);

        //Adds neighbors to the rooms using the hashmap.     
        outside.addNeighbor( "north", a1);
        a1.addNeighbor( "west", c1);
        a1.addNeighbor( "north", b1);
        a1.addNeighbor( "south", outside);

        b1.addNeighbor( "south", a1);
        b1.addNeighbor( "up", b2);

        c1.addNeighbor( "east", a1);
        c1.addNeighbor( "up", c2);
        c1.addNeighbor( "north", d1);

        d1.addNeighbor( "south", c1);

        a2.addNeighbor( "west", c2);
        a2.addNeighbor( "north", b2);

        b2.addNeighbor( "south", a2);
        b2.addNeighbor( "down", b1);
        b2.addNeighbor( "west", d2);

        c2.addNeighbor( "east", a2);
        c2.addNeighbor( "down", c1);
        c2.addNeighbor( "north", d2);

        d2.addNeighbor( "south", c2);
        d2.addNeighbor( "east", b2);

        //Sets up the locations of things in the game.
        previousLocation = null;
        currentLocation = outside;
        darkRoom = d2;

    }

    /*******************************************************************
     * Method sets up the inital message the game greets you with.
     * @param none
     * @return none
     ******************************************************************/
    private void setIntroMessage()
    {
        message = "**********New Game!**********\nWelcome to the " + 
        "game of Adventure!\n\nYou've accidently forgotten your " +
        "laptop inside Mackinaw Hall. Move inside the building" +
        " and scour the halls for your missing\n laptop! Once " +
        "you've found it leave thebuilding again to win!. Careful " +
        "not to drop it, though. Because if you do, then you'll\n" +
        "lose the  game!";
    }

    /*******************************************************************
     * Returns the message to the console to keep you updated.
     * @param none
     * @return message: the current message as updated.
     ******************************************************************/
    public String getMessage()
    {
        return message;
    }

    /*******************************************************************
     * Shows a hint that changes slightly throughout the game.
     * @param none
     * @return none
     ******************************************************************/
    public void help ()
    {
        Item itemTemp = searchInventory("laptop");

        /*
         * If statement checks whether or not the inventory contains the 
         * and outputs a different message depending on the answer.
         */
        if (items.contains(itemTemp))
        {
            message = "**********Hint:**********\nYou have found " +
            "the laptop! Make your way to the exit to win! Careful" +
            "not to break the laptop, though!";

        }
        else if (!items.contains(itemTemp))
        {
            message = "**********Hint:**********\nYou have not yet " +
            "found the laptop! Find the laptop and then leave to win!";

        }
    }

    /*******************************************************************
     * Shows the current room information.
     * @param none
     * @return none
     ******************************************************************/
    public void show ()
    {
        message = "**********Room Info:**********" +
        currentLocation.getLongDescription();
    }

    /*******************************************************************
     * This method controls all direction movements in the game. 
     * @param direction: Direction in which you'll move.
     * @return none.
     ******************************************************************/
    public void move (String direction)
    {
        //Temporary items created to search inventory for certain
        //objects that alter the outcome of pushing the move button.
        Item itemTemp = searchInventory("flashlight");
        Item itemTemp2 = searchInventory("laptop");

        //Sets nextRoom to the room that is the neighbor of your 
        //current room after being given a certain direction.
        Room nextRoom = currentLocation.getNeighbor(direction);

        /*
         * If statement checks whether or not there is a next room,
         * if the next room is a normal room or a dark room,
         * and whether or not certain rooms contain certain items.
         */
        if (nextRoom == null)
        {
            message = "**********Move: " + direction + "**********\n" +
            "You're trying to go " + direction + 
            "but you can't because there's no hallway or room there!";
        } 
        else if (nextRoom != darkRoom && nextRoom != null)
        {

            previousLocation = currentLocation;
            currentLocation = nextRoom;
            nextRoom = previousLocation;

            message = "**********Move: "+direction+"**********\n" +
            "You just went " + direction + ". " + 
            currentLocation.getLongDescription();

            hasBackedUp = false;

        }
        else if (nextRoom == darkRoom && items.contains(itemTemp))
        {
            /*
             * If statement checks whether or not your flashlight is
             * currently turned on our not and relays a different 
             * message respectively.
             */
            if (!flashlightOn)
            {
                message = "**********Move: "+direction+"**********\n" +
                "You're trying to go " + direction + " " + 
                "but you can't because the room is much too dark and the" +
                " light switch isn't working. Try turning on\nyour " +
                " flashlight before entering the room!\nYou're still in " +
                currentLocation.getDescription();
            }
            else if (flashlightOn)
            {
                previousLocation = currentLocation;
                currentLocation = nextRoom;
                nextRoom = previousLocation;

                message = "**********Move: " + direction + "**********\n" +
                "You just went " + direction + ". " + 
                currentLocation.getLongDescription();

                if (items.contains(itemTemp2))
                {
                    collectedLaptop =  true;
                    hasBackedUp = false;
                }
                else if (!items.contains(itemTemp2))
                {
                    hasBackedUp = false;
                }
            }
        }
        else if (nextRoom == darkRoom && !items.contains(itemTemp))
        {
            message = "**********Move: " + direction + "**********\n" +
            "You're trying to go " + direction + 
            "but you can't because the room is much too dark and the " +
            "light switch isn't working. There's an item\nsomewhere in " +
            "these halls with the power to illuminate even the darkest " +
            " rooms! Try finding it and then return here with it\n turned " +
            "on!\nYou're still in " +
            currentLocation.getDescription();
        }
    }

    /*******************************************************************
     *  Inventory Method formats the inventory to be printed out on the 
     *  message line in the gui.
     *  @param none
     *  @return none
     ******************************************************************/
    public void inventory()
    {
        message = "**********Inventory:**********\nCurrent Items: ";
        /*
         * If statement checks if the invintory has items yet, or not.
         */
        if (items.size() == 0)
        {
            message = "**********Inventory:**********\n" +
            "You don't have any items, yet!";

        }
        else 
        {
            /*
             * For Each loop cycles through the arraylist and makes
             * a String that contains all of the currently held items.
             */
            for (Item i: items)
            {
                /*
                 * If Statement checks whether or not there is
                 * more than one item in the array for grammar
                 * purposes.
                 */
                if (items.size() == 1)
                {
                    message += i.description;
                }
                else 
                {
                    message += i.description + ", ";
                }
            }
        }
    }

    /*******************************************************************
     *  Eat Method is how you can eat an inventory item.
     *  @param item: Item to be tested for ability to be eaten and then
     *               probably eaten.
     *  @return none
     ******************************************************************/
    public void eat (String item)
    {
        //Creates temporary value to convert string into item.
        Item itemTemp = searchInventory(item);
        /*
         * If statement checks whether or not the inventory contains
         * an item and if it is edible, or not.
         */
        if (items.contains(itemTemp) && itemTemp.isEdible())
        {
            items.remove(itemTemp);

            message = "**********Eat:**********\nYou have successfully " +
            "eaten the " + item + "!";
        }
        else if (items.contains(itemTemp) && !itemTemp.isEdible())
        {
            message = "**********Eat:**********\nThe " + item + 
            " is not edible!";
        }
        else
        {
            message = "**********Eat:**********\n" +
            "Either you don't have that item or an item was " +
            "not specified!";
        }
    }

    /*******************************************************************
     *  SearchInventory method scans the inventory for items with the 
     *  same name as the provided String
     *  @param name: name of object you want to search for.
     *  @return itemSearch: either null or an item depending on search
     *                      results.
     ******************************************************************/
    private Item searchInventory (String name) 
    {
        //Creates a new item to be returned if it is found in the array.
        Item itemSearch = null;
        /*
         * For Each loop cycles through the inventory to find an item
         * with the same name as the provided string.
         */
        for (Item i: items) 
        {
            if(i.getName().equals(name) || (i.getDescription().equals( name) ))
            {
                itemSearch = i;
            }
        } 

        return itemSearch;
    }

    /*******************************************************************
     *  GameOver method sets up the parameters for losing or winning
     *  the game.
     *  @param none
     *  @return gameOver: Checks to see if game is won, lost, or 
     *                    neither.
     ******************************************************************/
    public boolean gameOver()
    {
        //Creates two temporary items so I can search for their
        //occurance in my inventory.
        Item itemTemp = searchInventory("laptop");
        Item itemTemp2 = searchInventory("flashlight");

        //gameover is initally set to false so that if none of the 
        //conditions are met the game is not over, yet.
        boolean gameOver = false;

        /*
         * If statement checks whether or not the inventory contains
         * certain items, whether or not they're broken, or whether
         * or not you have died from a scare
         */
        if (items.contains(itemTemp) && 
        currentLocation.getDescription() == "Outside")
        {
            message = "**********Game Status: Game Over! You Won!" +
            "**********\nYou have found the laptop and returned " +
            "outside!\nCongratulations, you have won!\n\nTo play " +
            "another game select 'new game' from the file menu at " + 
            "the top of the screen.";

            gameOver = true;
        }
        else if (laptopDestroyed)
        {
            message = "**********Game Status: Game Over! You Lose!" +
            "**********\nThe object of the game was to find your " +
            "missing laptop and then return outside. Since you " +
            "broke your laptop, you lose!\n\nTo play another game " +
            "select 'new game' from the file menu at the top of " +
            "the screen.";

            gameOver = true;
        }
        else if (scaredDeath)
        {
            /*
             * If statements check to see if you have a flashlight
             * in your inventory.
             */
            if (items.contains(itemTemp2))
            {
                message = "**********Game Status: Game Over! You " +
                "Lose!**********\nYou just turned off the light " +
                "while you were in the dark room! You died of " +
                "fright! Since you died, you lose!\n\nTo play " +
                "another\ngame select 'new game' from the " +
                "file menu at the top of the screen.";

                gameOver = true;
            }
            else if (!items.contains(itemTemp2))
            {
                message = "**********Game Status: Game Over! You" +
                "Lose!**********\nYou threw your flashlight at " +
                "the ground and broke it! As a result, the light " +
                "went out and you died of fright! Since you died" +
                "\nyou lose!\n\nTo play another game select " +
                "'new game' from the file menu at the top of the" +
                " screen.";

                gameOver = true;
            }
        }

        else if (flashlightBroken)
        {
            /*
             * If statement checks whether or not you have
             * obtained the laptop, yet.
             */
            if (collectedLaptop)
            {
                /*
                 * If statement checks whether or not you're
                 * currently in the dark room.
                 */
                if (currentLocation == darkRoom)
                {
                    message = "**********Game Status: Game " +
                    "Over! You Lose!**********\nYou threw" +
                    " your flashlight at the ground and broke " +
                    "it! As a result, the light went out and " +
                    "you died of fright! Since you died\nyou " +
                    "lose!\n\nTo play another game select " +
                    "'new game' from the file menu at the " +
                    "top of the screen.";

                    gameOver = true;
                }
            }
            else if (!collectedLaptop)
            {
                message = "**********Game Status: Game " +
                "Over! You Lose!*********\nYou threw " +
                "your flashlight at the ground and " +
                "broke it! Unfortunately you haven't " +
                "found the laptop yet, and you need the" +
                "flashlight to do so! Therefore, you " + 
                "lose!\n\nTo play another game select " +
                "'new game' from the file menu at the " +
                "top of the screen.";

                gameOver = true;
            }
        }

        return gameOver;
    }

    /*******************************************************************
     *  Pickup Method makes it so you can take items out of rooms and
     *  add them to your inventory.
     *  @param none
     *  @return none
     ******************************************************************/
    public void pickup()
    {
        /*
         * If statement checks whether or not your current room has
         * an item in it, or not.
         */
        if (currentLocation.hasItem())
        {
            Item item = currentLocation.removeItem();
            /*
             * If statement checks whether or not you can lift the
             * item and then adds it to your inventory if you cant.
             */
            if (item.weight < 50)
            {
                items.add(item);
                Item itemTemp = searchInventory("laptop");
                /*
                 * If statement checks whether or not you're picking 
                 * up the laptop.
                 */
                if (item == itemTemp)
                {
                    message = "**********Pickup: " + item.name + 
                    "**********\n Congratulations! You are now " +
                    "holding the " + item.name + ".\nLeave the " +
                    "building now to win the game!";
                }
                else
                {
                    message = "**********Pickup: " + item.name + 
                    "**********\n You are now holding the " +
                    item.name + ".";
                }
            }
            else if (item.weight > 50)
            {
                message = "**********Pickup: " + item.name + 
                "**********\n The " + item.name + "is too " +
                "heavy to lift!";
            }
        }
        else
        {
            message = "**********Pickup:**********\n" +
            "There is nothing in this room to take.";
        }

    }

    /*******************************************************************
     *  Leave method makes it so you can leave items in rooms.
     *  @param item: Item to be left in room.
     *  @return none
     ******************************************************************/
    public void leave (String item)
    {
        //Creates temporary item to convert string into item.
        Item itemTemp = searchInventory(item);
        /*
         * If statement checks whether you have the item specified or
         * not.
         */
        if (items.contains(itemTemp))
        {
            /*
             * If statement checks whether or not the current room
             * has an item in it, already.
             */
            if (!currentLocation.hasItem())
            {
                /*
                 * If statement checks whether or not your flashlight
                 * is currently on or off and if the item you're 
                 * trying to leave is the flashlight, respectively.
                 */
                if (flashlightOn && item.equalsIgnoreCase("flashlight"))
                {
                    items.remove(itemTemp);
                    flashlightOn = false;

                    currentLocation.addItem(itemTemp);

                    message = "**********Leave:**********\nThe " + item + 
                    " has been turned off, removed from the inventory" +
                    " and left in the room!";
                }
                else
                {
                    items.remove(itemTemp);

                    currentLocation.addItem(itemTemp);

                    message = "**********Leave:**********\nThe " + item + 
                    " has been removed from the inventory and left in" +
                    " the room!";
                }
            }
            else 
            {
                message = "**********Leave:**********\n" +
                "The room already has an item in it!";
            }
        }
        else
        {
            message = "**********Leave:**********\n" + 
            "Either you don't have that item or an item was " +
            "not specified!";
        }
    }

    /*******************************************************************
     *  LightOn method turns the flashlight on.
     *  @param none
     *  @return none
     ******************************************************************/
    public void lightOn(String item)
    {
        //Creates a temporary item to convert a string into an item.
        Item itemTemp = searchInventory(item);

        /*
         * if statement checks whether or not an item can be turned on 
         * and if you have the flashlight in your inventory. If it sees
         * both then the flashlight is turned on.
         */
        if (items.contains(itemTemp) && item.equalsIgnoreCase("flashlight"))
        {
            message = "**********Light On:**********\nThe " + item + 
            " has been turned on!";

            flashlightOn = true;

        }
        else if (items.contains(itemTemp) &&
        item.equalsIgnoreCase("flashlight") == false)
        {
            message = "**********Light On:**********\nThe " + item + 
            " cannot be used as a light source!";    
        }
        else
        {
            message = "**********Light On:**********\n" +
            "Either you don't have that item or an item was " +
            "not specified!";
        }
    }

    /*******************************************************************
     *  LightOff method turns the flashlight off.
     *  @param item: item to be turned off
     *  @return none
     ******************************************************************/
    public void lightOff(String item)
    {
        //Creates a temporary item to convert the string into an item.
        Item itemTemp = searchInventory(item);

        /*
         * If statement checks whether an item can be turned off, if
         * you have a flashlight, and if you do have a flashlight and
         * if it's turned on it will be turned off.
         */
        if (items.contains(itemTemp) && 
        item.equalsIgnoreCase("flashlight")) 
        {
            /*
             * If statement checks whether or not you're currently in
             * the dark room or not.
             */
            if (currentLocation == darkRoom)
            {
                /*
                 * If statement checks whether or not the flashlight
                 * is currently turned on if you're in the dark room.
                 */
                if (flashlightOn)
                {
                    flashlightOn = false;
                    message = "**********Light Off:**********\nThe " +
                    "item has been turned off!";

                    gameOver();
                    items.remove(itemTemp);
                    scaredDeath = true;

                }
            }
            else if (currentLocation != darkRoom)
            {
                /*
                 * If statement checks whether or not the flashlight
                 * is currently turned on if you're not in teh dark
                 * room.
                 */
                if (flashlightOn)
                {
                    message = "**********Light Off:**********\nThe " + 
                    "item has been turned off!";

                    flashlightOn = false;
                }
                else 
                {
                    message = "**********Light Off:**********\nThe " + 
                    item + " wasn't turned on!";    
                }
            }
        }
        else if (items.contains(itemTemp) && 
        item.equalsIgnoreCase("flashlight") == false)
        {
            message = "**********Light Off:**********\nThat " + item + 
            " isn't making any light!";
        }
        else
        {
            message = "**********Light Off:**********\n" +
            "Either you don't have that item or an item was " +
            "not specified!";
        }
    }

    /*******************************************************************
     *  Destroy method destroys an item in your inventory.
     *  @param item: item to be destroyed.
     *  @return none
     ******************************************************************/
    public void destroy (String item)
    {
        //Temporary item is created to convert a String into an Item.
        Item itemTemp = searchInventory(item);
        /*
         * If statement checks whether or not you have the given item
         * in your inventory.
         */
        if (items.contains(itemTemp))
        {
            /*
             * If statement checks whether or not the item is 
             * destroyable.
             */
            if (itemTemp.isDestroy())
            {
                /*
                 * If statement checks whether or not the given
                 * item is a flashlight.
                 */
                if (item.equalsIgnoreCase("flashlight"))
                {
                    /*
                     * If statement checks whether or not you
                     * have already collected the laptop.
                     */
                    if (collectedLaptop)
                    {
                        /*
                         * If statement checks whether or not
                         * the flashlight is currently on.
                         */
                        if (flashlightOn)
                        {
                            message = "**********Destroy:**********\n" +
                            "The " + item +" has been thrown against " +
                            "the ground, the light turned off and it" +
                            "smashed to pieces! The " + item + " has" +
                            "been\n sucessfully destroyed!";

                            flashlightBroken = true;
                            flashlightOn = false;
                            items.remove(itemTemp);
                            /*
                             * If statement checks if you're currently
                             * in the dark room or not.
                             */
                            if (currentLocation == darkRoom)
                            {
                                gameOver();
                                scaredDeath = true;
                            }
                        }
                        else if (!flashlightOn)
                        {
                            message = "**********Destroy:**********\n" +
                            "The " + item + " has been thrown against" +
                            " the ground and it smashed to pieces! " + 
                            "The " + item + " has been\n sucessfully" +
                            "destroyed!";

                            flashlightBroken = true;
                            flashlightOn = false;
                            items.remove(itemTemp);
                        }
                    }
                    else if (!collectedLaptop)
                    {
                        /*
                         * If statement checks whether or not the 
                         * flashlight is on if you haven't 
                         * collected the laptop.
                         */
                        if (flashlightOn)
                        {
                            message = "**********Destroy:**********\n" +
                            "The " + item + " has been thrown against" +
                            " the ground, the light turned off and it" +
                            "smashed to pieces! The " + item +
                            " has been\n sucessfully destroyed!";

                            flashlightOn = false;
                            flashlightBroken = true;
                            items.remove(itemTemp);
                            gameOver();

                            /*
                             * If statement checks whether or not 
                             * you're currently in teh darkroom.
                             */
                            if (currentLocation == darkRoom)
                            {
                                gameOver();
                                scaredDeath = true;
                            }
                        }
                        else if (!flashlightOn)
                        {
                            message = "**********Destroy:**********\n" +
                            "The " + item +" has been thrown against" +
                            " the ground and it smashed to pieces! " +
                            "The " + item + " has been\n sucessfully" +
                            " destroyed!";

                            flashlightBroken = true;
                            flashlightOn = false;
                            items.remove(itemTemp);
                            gameOver();
                        }
                    }
                }
                else if (item.equalsIgnoreCase("book"))
                {
                    message = "**********Destroy:**********\n" +
                    "The " + item + " has been thrown against the" +
                    "grown and ripped to shreads! The " + 
                    item + " has been sucessfully destroyed!";

                    items.remove(itemTemp);
                }
                else if (item.equalsIgnoreCase("snack"))
                {
                    message = "**********Destroy:**********\n" +
                    "The " + item + " was squished in your hand" +
                    " and thrown at the ground! The " + item + 
                    " has been sucessfully destroyed!";

                    items.remove(itemTemp);
                }
                else if (item.equalsIgnoreCase("laptop"))
                {
                    message = "**********Destroy:**********\n" +
                    "The " + item +" has been thrown against" +
                    "the ground and it smashed to pieces! The " + 
                    item + " has been sucessfully destroyed!";

                    gameOver();

                    items.remove(itemTemp);

                    laptopDestroyed = true;

                }
            }
            else
            {
                message = "**********Destroy:**********\n" +
                "That item cannot be destroyed!";
            }
        }
        else 
        {
            message = "**********Destroy:**********\n" +
            "Either you don't have that item or an item was " +
            "not specified!";
        }
    }

    /*******************************************************************
     *  Backup Method allows you to backup once per movement made.
     *  @param none
     *  @return none
     ******************************************************************/
    public void backup()
    {
        /*
         * If statement checks whether or not you have already
         * backed up.
         */
        if (hasBackedUp)
        {
            message = "**********Backup:**********\nYou have already " +
            "backed up once! You can't do that again! You're still " +
            "in " + currentLocation.getDescription() + ".";
        }

        else if (!hasBackedUp)
        {
            /*
             * If statement checks whether or not there is a location
             * to back up to.
             */
            if (previousLocation == null)
            {
                message = "**********Backup:**********\nYou are " +
                "currently outside! You can't backup anywhere!";
            }
            else if (previousLocation!= null)
            {
                currentLocation = previousLocation;

                hasBackedUp = true;

                message = "**********Backup:**********\nYou quickly " +
                "retreated to the previous room you were in!\n" +
                "Current Room: " + 
                currentLocation.getDescription() + ".";

            }
        }
    }

    /*******************************************************************
     *  Main Method is used to test the functionality of Game.
     *  @param none
     *  @return none
     ******************************************************************/
    public static void main (String args[])
    {
        //Create a new game.
        Game g = new Game();

        System.out.println(g.getMessage());
        g.show();

        System.out.println("\n" + g.getMessage());
        g.move("north");
        System.out.println("\n" + g.getMessage());
        g.move("west");

        System.out.println("\n" + g.getMessage());
        g.pickup();

        System.out.println("\n" + g.getMessage());
        g.lightOn("flashlight");

        System.out.println("\n" + g.getMessage());
        g.move("north");

        g.pickup();        

        System.out.println("\n" + g.getMessage());
        g.eat("snack");

        System.out.println("\n" + g.getMessage());
        g.lightOff("flashlight");

        System.out.println("\n" + g.getMessage());
        g.move("south");

        System.out.println("\n" + g.getMessage());
        g.move("up");

        System.out.println("\n" + g.getMessage());
        g.pickup();

        System.out.println("\n" + g.getMessage());
        g.destroy("book");

        System.out.println("\n" + g.getMessage());
        g.lightOn("flashlight");

        System.out.println("\n" + g.getMessage());
        g.move("north");

        System.out.println("\n" + g.getMessage());
        g.backup();

        System.out.println("\n" + g.getMessage());
        g.move("north");

        System.out.println("\n" + g.getMessage());
        g.pickup();

        System.out.println("\n" + g.getMessage());
        g.move("south");

        System.out.println("\n" + g.getMessage());
        g.help();

        System.out.println("\n" + g.getMessage());
        g.show();

        System.out.println("\n" + g.getMessage());
        g.inventory();

        System.out.println("\n" + g.getMessage());
        g.leave("flashlight");

        System.out.println("\n" + g.getMessage());
        g.move("down");

        System.out.println("\n" + g.getMessage());
        g.move("east");

        System.out.println("\n" + g.getMessage());
        g.move("south");

        System.out.println("\n" + g.getMessage());
        g.gameOver();

        System.out.println("\n" + g.getMessage());
    }

}
