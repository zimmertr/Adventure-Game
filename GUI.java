import java.awt.*;
import javax.swing.*;
import javax.swing.text.*;
import java.awt.event.*;
import java.util.*;

/***********************************************************************
 * Class GUI is used to create an interface for the game
 * 
 * @TJ Zimmerman
 * @1.01
 **********************************************************************/
public class GUI
{
    /** the analyzer that doe all the real work */
    Game myGame;

    /** Buttons to initiate each action */
    private JButton north;
    private JButton east;
    private JButton west;
    private JButton south;

    private JButton up;
    private JButton down;
    private JButton backup;

    private JButton help;
    private JButton show;
    private JButton pickup;
    private JButton eat;
    private JButton leave;
    private JButton inventory;
    private JButton lightOn;
    private JButton lightOff;
    private JButton destroy;
    private JButton newGame;


    /** GUI frame */
    JFrame myGUI;

    JTextArea results;

    /** menu items */
    JMenuBar menus;

    JMenu fileMenu;

    JMenuItem quitItem, newItem;

    /*******************************************************************
     *GUI Method sets up the layout of the GUI.
     *@param none
     *@return none
     ******************************************************************/
    public GUI(){
        // establish the frame and customize window.
        myGUI = new JFrame();
        myGUI.setSize(1000,300);
        myGUI.setTitle("Adventure Game!");    

        // create display area and customize it.
        results = new JTextArea(30,67);
        results.setFont (new Font ("Helvetica", Font.PLAIN, 16));
        results.setForeground( Color.BLUE );

        JScrollPane scrollPane = new JScrollPane(results);
        myGUI.add(BorderLayout.CENTER, scrollPane);

        DefaultCaret caret = (DefaultCaret)results.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        // instantiate each button here
        help = new JButton(" Show Hint  ");
        show = new JButton(" Room Info  ");
        pickup = new JButton("Pickup Item" );
        eat = new JButton("  Eat Item  " ) ;
        leave = new JButton(" Drop Item  ");
        inventory = new JButton(" Inventory  " );
        destroy = new JButton("Break Item ");
        lightOn = new JButton("  Light On  " );
        lightOff = new JButton("  Light Off " );
        newGame = new JButton("New Game");

        north = new JButton("North");
        south = new JButton("South");
        east = new JButton("East");
        west = new JButton("West");
        up = new JButton("Up");
        down = new JButton("Down");
        backup = new JButton("Backup");

        // Create a JPanel and customize it.
        JPanel actionPanel = new JPanel();
        Font labelFont = new Font("Arial", Font.BOLD,15);  
        actionPanel.setLayout(new BoxLayout(actionPanel, 
                BoxLayout.Y_AXIS));
        actionPanel.setFont(labelFont);
        actionPanel.setBorder (BorderFactory.createTitledBorder
            ("The Actions:"));
        myGUI.add(BorderLayout.EAST, actionPanel);

        //Creates new JLabels and customizes them.
        JLabel actions = new JLabel("Actions Buttons: ");
        JLabel specialActions = new JLabel("Special Actions: ");

        actions.setFont(labelFont);
        specialActions.setFont(labelFont);

        //adds buttons to the actionPanel.
        actionPanel.add(Box.createRigidArea(new Dimension(0,20)));
        actionPanel.add(actions);
        actionPanel.add(Box.createRigidArea(new Dimension(0,15)));
        actionPanel.add(help);
        actionPanel.add(Box.createRigidArea(new Dimension(0,15)));
        actionPanel.add(show);
        actionPanel.add(Box.createRigidArea(new Dimension(0,15)));
        actionPanel.add(pickup);
        actionPanel.add(Box.createRigidArea(new Dimension(0,15)));
        actionPanel.add(leave);
        actionPanel.add(Box.createRigidArea(new Dimension(0,15)));
        actionPanel.add(inventory);
        actionPanel.add(Box.createRigidArea(new Dimension(0,50)));
        actionPanel.add(specialActions);
        actionPanel.add(Box.createRigidArea(new Dimension(0,15)));
        actionPanel.add(eat);
        actionPanel.add(Box.createRigidArea(new Dimension(0,15)));
        actionPanel.add(lightOn);
        actionPanel.add(Box.createRigidArea(new Dimension(0,15)));
        actionPanel.add(lightOff);
        actionPanel.add(Box.createRigidArea(new Dimension(0,15)));
        actionPanel.add(destroy);
        actionPanel.add(Box.createRigidArea(new Dimension(0,15)));
        actionPanel.add(newGame);
        actionPanel.add(Box.createRigidArea(new Dimension(0,15)));

        //Creates a new JPanel and customizes it.
        JPanel directionPanel = new JPanel();
        directionPanel.setLayout(new BoxLayout(directionPanel, 
                BoxLayout.X_AXIS));
        directionPanel.setBorder(BorderFactory.createTitledBorder
            ("The Directions:"));

        //add direction panel
        myGUI.add(BorderLayout.SOUTH, directionPanel);

        //Create new JLabels and customize them.
        JLabel directions = new JLabel("Direction Buttons: ");
        JLabel specialDirections = new JLabel("Special Directions: ");
        directions.setFont(labelFont);
        specialDirections.setFont(labelFont);

        //add buttons to direcitonPanel.
        directionPanel.add(directions);
        directionPanel.add(Box.createRigidArea(new Dimension(15,0)));
        directionPanel.add(north);
        directionPanel.add(Box.createRigidArea(new Dimension(15,0)));
        directionPanel.add(south);
        directionPanel.add(Box.createRigidArea(new Dimension(15,0)));
        directionPanel.add(west);
        directionPanel.add(Box.createRigidArea(new Dimension(15,0)));
        directionPanel.add(east);
        directionPanel.add(Box.createRigidArea(new Dimension(50,0)));
        directionPanel.add(specialDirections);
        directionPanel.add(up);
        directionPanel.add(Box.createRigidArea(new Dimension(15,0)));
        directionPanel.add(down);
        directionPanel.add(Box.createRigidArea(new Dimension(15,0)));
        directionPanel.add(backup);

        // register the listeners
        ButtonListener listener = new ButtonListener();

        help.addActionListener(listener);
        show.addActionListener(listener);
        pickup.addActionListener(listener);
        eat.addActionListener(listener);
        inventory.addActionListener(listener);
        leave.addActionListener(listener);
        destroy.addActionListener(listener);
        lightOn.addActionListener(listener);
        lightOff.addActionListener(listener);
        newGame.addActionListener(listener);

        north.addActionListener(listener);
        south.addActionListener(listener);
        east.addActionListener(listener);
        west.addActionListener(listener);
        up.addActionListener(listener);
        down.addActionListener(listener);
        backup.addActionListener(listener);

        // set up File menu and customize it.
        Font menuFont = new Font("Arial",Font.PLAIN,12);
        fileMenu = new JMenu("File Menu");
        quitItem = new JMenuItem("Quit Game");
        newItem = new JMenuItem("New Game");
        quitItem.setFont(menuFont);
        newItem.setFont(menuFont);

        //add the menus to the file menu.
        fileMenu.add(newItem);
        fileMenu.add(quitItem);
        menus = new JMenuBar();
        myGUI.setJMenuBar(menus);
        menus.add(fileMenu);

        // register the menu items with the action listener
        quitItem.addActionListener(listener);
        newItem.addActionListener(listener);

        //Set GUI to visable.
        myGUI.setVisible(true);
        myGUI.pack();

        //create a new game.
        newGame();
    }

    /*******************************************************************
     * ButtonListener Class helps make it so buttons actually do things.
     ******************************************************************/
    private class ButtonListener implements ActionListener
    {
        /***************************************************************
         * Actionperformed Method makes things happen when you press 
         * buttons
         * @param e: the action that occurred.
         * @return none
         **************************************************************/
        public void actionPerformed(ActionEvent e) 
        {
            //create the JComponent.
            JComponent source = (JComponent) e.getSource();
            /*
             * If statements check if buttons have been pressed
             * and call methods if they have. Occasionally
             * prompts the user for input, too.
             */
            if ( source == quitItem)
            {
                System.exit( 1 );
            }
            else if (source == newItem)
            {
                newGame();
                return;
            }
            else if (source == show)
            {
                myGame.show();
            }
            else if (source == help)
            {
                myGame.help();
            }
            else if (source == pickup)
            {
                myGame.pickup();
            }
            else if (source == eat)
            {
                String toEat = JOptionPane.showInputDialog(null,
                        "Eat what?");

                myGame.eat(toEat);
            }
            else if (source == lightOn)
            {
                String toLightOn = JOptionPane.showInputDialog(null,
                        "Turn on what?");

                myGame.lightOn(toLightOn);
            }
            else if (source == lightOff)
            {
                String toLightOff = JOptionPane.showInputDialog(null,
                        "Turn off what?");

                myGame.lightOff(toLightOff);
            }
            else if (source == leave)
            {
                String toLeave = JOptionPane.showInputDialog(null,
                        "Drop what?");

                myGame.leave(toLeave);
            }
            else if (source == inventory)
            {
                myGame.inventory();
            }
            else if (source == destroy)
            {
                String toDestroy = JOptionPane.showInputDialog(null,
                        "Destroy what?");

                myGame.destroy(toDestroy);
            }
            else if (source == north)
            {
                myGame.move("north");
            }
            else if (source == south)
            {
                myGame.move("south");
            }
            else if (source == east)
            {
                myGame.move("east");
            }
            else if (source == west)
            {
                myGame.move("west");
            }
            else if (source == up)
            {
                myGame.move("up");
            }
            else if (source == down)
            {
                myGame.move("down");
            }
            else if (source == backup)
            {
                myGame.backup();
            }
            else if (source == newGame)
            {
                results.setText("");
                newGame();
                return;
            }
            /*
             * If statement checks whether or not the game is over.
             * If it is, it calls the gameover method.
             */
            if (myGame.gameOver())
            {
                gameOver();
            }

            //add results to the current message.
            results.append("\n\n" + myGame.getMessage());
        }
    }

    /***************************************************************
     * gameOver method sets all buttons to disabled.
     * @param none
     * @return none
     **************************************************************/
    private void gameOver()
    {
        results.setVisible(false);
        help.setEnabled(false);
        pickup.setEnabled(false);
        eat.setEnabled(false);
        leave.setEnabled(false);
        inventory.setEnabled(false);
        show.setEnabled(false);

        destroy.setEnabled(false);
        lightOn.setEnabled(false);
        lightOff.setEnabled(false);

        north.setEnabled(false);
        south.setEnabled(false);
        east.setEnabled(false);
        west.setEnabled(false);

        up.setEnabled(false);
        down.setEnabled(false);
        backup.setEnabled(false);

    }

    /***************************************************************
     * New game wipes the info field, blanks inventory, sets all .
     * buttons to enabled, etc.
     * @param none
     * @return none
     **************************************************************/
    private void newGame()
    {
        myGame = new Game();

        results.setText(myGame.getMessage() );        

        results.setVisible(true);
        help.setEnabled(true);
        pickup.setEnabled(true);
        eat.setEnabled(true);
        leave.setEnabled(true);
        inventory.setEnabled(true);
        show.setEnabled(true);

        destroy.setEnabled(true);
        lightOn.setEnabled(true);
        lightOff.setEnabled(true);

        north.setEnabled(true);
        south.setEnabled(true);
        east.setEnabled(true);
        west.setEnabled(true);

        up.setEnabled(true);
        down.setEnabled(true);
        backup.setEnabled(true);
    }

    /***************************************************************
     * Main Method is used to create a new GUI for the game.
     * @param none
     * @return none
     **************************************************************/
    public static void main(String args[])
    {
        new GUI();

    }

}
