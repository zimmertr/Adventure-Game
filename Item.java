import javax.swing.*;

/***********************************************************************
 * Class Item is used to create new items for the game.
 * 
 * @TJ Zimmerman
 * @1.01
/**********************************************************************/

public class Item
{
    //Strings to hold the name information for the items.
    public String name, description;

    //Int to hold the weight of each items.
    public int weight;

    //Booleans to control the various yes/no options.
    public boolean isEdible, flashlight, destroy;

    /*******************************************************************
     * This constructor moves the values from the input fields to the 
     * variables.
     * @param n: The name of the Item.
     * @param D: The description of the Item.
     * @param W: The int for the weight of the Item.
     * @param E: The boolean for edible.
     * @param F: The boolean for flashlightable.
     * @param des: The boolean for destroyable.
     * 
     * @return none
     ******************************************************************/
    public Item (String n, String d, int w, 
    boolean e, boolean f, boolean des)
    {
        name = n;
        description = d;

        weight = w;

        isEdible = e;
        flashlight = f;
        destroy = des;
    }

    /*******************************************************************
     * This method sets the name of the objects.
     * @param n: The name of the item.
     * @return none
     ******************************************************************/
    public void setName(String n)
    {
        name = n;
    }

    /*******************************************************************
     * This method returns the name of the objects.
     * @param none
     * @return name: name of item.
     ******************************************************************/
    public String getName()
    {
        return name;
    }

    /*******************************************************************
     * This method sets the short description of the items.
     * @param d: Description of the item.
     * @return none
     ******************************************************************/
    public void setDescription(String d)
    {
        description = d;
    }

    /*******************************************************************
     * This method returns the description of the objects.
     * @param none
     * @return description: description of item
     ******************************************************************/
    public String getDescription()
    {
        return description;
    }

    /*******************************************************************
     * This method sets the weight of the objects.
     * @param w: weight of the item.
     * @return none
     ******************************************************************/
    public void setWeight( int w)
    {
        weight = w ;
    }

    /*******************************************************************
     * This method returns the weight of the objects.
     * @param none
     * @return weight: weight of the item.
     ******************************************************************/
    public int getWeight()
    {
        return weight;
    }

    /*******************************************************************
     * This method returns true if an item can be used as a light 
     * source.
     * @param none
     * @return true: if item can be illuminated.
     * @return false: if item cannot be illuminated.
     ******************************************************************/
    public boolean isFlashLight()
    {   
        if (flashlight)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /*******************************************************************
     * This method returns true if the item is edible and false if the
     * item is not edible.
     * @param none
     * @return true: if item is edible.
     * @return false: if item is not edible
     ******************************************************************/
    public boolean isEdible()
    {
        if (isEdible)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /*******************************************************************
     * This method returns true if an item is destroyable.
     * @param none
     * @return true: if item is destroyable.
     * @return false: if item is not destroyable.
     ******************************************************************/
    public boolean isDestroy()
    {
        if (destroy)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /*******************************************************************
     * This Main method is used for testing the creation of items.
     * @param none
     * @param none
     ******************************************************************/
    public static void main(String[] arguements)
    {
        Item item = new Item("Name","Long Name",2,true,false,true);

        JOptionPane.showMessageDialog(null,item.name + "\n" + 
            item.getDescription() + "\n" + item.getWeight() + 
            " pounds.");
    }
}
