/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soybean;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.*;


/**
 *
 * @author Andrie Tanusetiawan
 */
public class GuiOperations implements ActionListener {
    
    //==========static variables and constants=========
    //=================================================

    // Declare constants for Button text
    private static final String RESET = " Reset";
    private static final String OK    = "  OK  ";
    private static final String ADD   = "  Add ";
    private static final String UPDATE = "Update";
    private static final String DELETE = "Delete";
    private static final String SEARCH = "Search";

    //==============instance variables=================
    //=================================================
    private String sep = "~";

    private String[] salutations =
    {
        "Col.",  "Dr.",   "Frau",  "Herr.",  "M.",
        "Maj.",  "Miss",  "Mlle.", "Mme.",   "Mr.",  
        "Mrs.",  "Ms.",   "Prof.",  "Rev."
    };

    // define components

    JTextField txtSerialNumber, txtName, txtPrice, txtKgs;
    JTextField txtSearch;
    JLabel lblSerialNumber, lblName, lblPrice, lblKgs;

  
    JLabel salutationLabel  = new JLabel("ID", JLabel.LEFT);
    JLabel givenNameLabel   = new JLabel("Serial Number", JLabel.LEFT);
    JLabel familyNameLabel  = new JLabel("Name", JLabel.LEFT);
    JLabel addressLabel     = new JLabel("Price", JLabel.LEFT);
    JLabel kgsLabel         = new JLabel("Kgs", JLabel.LEFT);

    JComboBox  salutationBox    = new JComboBox(salutations); 
    JTextField givenNameField   = new JTextField(10);
    JTextField familyNameField  = new JTextField(20);
    JTextField addressField     = new JTextField(30);

    JButton okButton         = new JButton(OK);
    JButton resetButton      = new JButton(RESET);
    JButton addButton        = new JButton(ADD);
    JButton updateButton     = new JButton(UPDATE);
    JButton deleteButton     = new JButton(DELETE);
    JButton searchButton     = new JButton(SEARCH);

    // define containers
    JPanel personPane = new JPanel();
    JPanel buttonPane = new JPanel();
    JPanel contentPane = new JPanel();
    JPanel tablePane = new JPanel();

    JPanel salMiniPane = new JPanel();
    JPanel salutationPane = new JPanel();
    JPanel namesPane = new JPanel();
    JPanel familyPane = new JPanel();
    JPanel givenPane = new JPanel();
    
    JPanel searchPane = new JPanel();
    
    Soybean soybean;
    
    public GuiOperations(Soybean soy)
    {
        	      //---- Create the top-level container and add contents to it.
//    JFrame frame = new JFrame("PersonDataEntry5");
//    PersonDataEntry5 app = new PersonDataEntry5();

    /*
      create the components that go on the container
    */
//    Component contents = app.createComponents();
    /*
      add the components to their container (the content pane)
      in this case, to the center section of a border layout
    */
//    frame.getContentPane().add(contents, BorderLayout.CENTER);
    /*
      because the container is a Frame (which inherits from Window) ...
      set the action to be taken when the X (top right of frame)
      is clicked
    */
//    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    /*
      Cause the the Frame (which inherits from Window) to be visible
      Note that this is done in a different way for Applets
      (which inherit from Panel, not Window)
    */
//    frame.pack();
//    frame.setVisible(true);


        soybean = soy;

        //---- Create the top-level container and add contents to it.
        JFrame frame = new JFrame("Soybean");
    
        //    Component components = createComponents();
        //frame.getContentPane().add(components, BorderLayout.NORTH);
    
        /*
            create the components that go on the container
        */
        Component contents = createButtons();
        Component inputComponents = createInputComponents();
        JPanel paneComponents = new JPanel();

        paneComponents.add(inputComponents, BorderLayout.WEST);
        paneComponents.add(contents, BorderLayout.EAST);

        /*
            add the components to their container (the content pane)
            in this case, to the center section of a border layout
        */
        frame.getContentPane().add(paneComponents, BorderLayout.SOUTH);
	
        /*
            add the components to their container (the content pane)
            in this case, to the center section of a border layout
        */
	frame.getContentPane().add(createSideBar(), BorderLayout.LINE_START);

        /*
            because the container is a Frame (which inherits from Window) ...
            set the action to be taken when the X (top right of frame)
            is clicked
        */
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /*
            Cause the the Frame (which inherits from Window) to be visible
            Note that this is done in a different way for Applets
            (which inherit from Panel, not Window)
        */
        frame.pack();
        frame.setVisible(true);

    
        soy.createTable();
    
        // create the gui
        //    JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JScrollPane scrollPane = new JScrollPane(soy.table);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setSize(1000, 500);
        frame.setVisible(true);

        soy.createTableSecond();
    
        // create the gui
        //    JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JScrollPane scrollPaneSecond = new JScrollPane(soy.tableSecond);
        frame.add(scrollPaneSecond, BorderLayout.CENTER);
        frame.setSize(1600, 1000);
        frame.setVisible(true);
                
        tablePane.add(scrollPane, BorderLayout.NORTH);
        tablePane.add(scrollPaneSecond, BorderLayout.SOUTH);
        frame.getContentPane().add(tablePane, BorderLayout.CENTER);
    
        soy.tableMouseClicked();
    }
    
    /**
     *
     */
    public void actionPerformed(ActionEvent e)
    {
        String command = "";
        String data    = "";

        System.out.println("In actionPerformed");
        command = e.getActionCommand();
        System.out.println(command);

        if (command.equals(OK))
        {
            System.out.println("OK button pressed");
            System.out.flush();
            data = soybean.getDataGui(sep);
            System.out.println(data);
	  
            // force lose focus on any table cell to commit any edit, even if partial
            if (soybean.table.getCellEditor() != null)
                    soybean.table.getCellEditor().stopCellEditing();

            //--- this is the top of the application to use the data
            soybean.doApplication(data);
            //--- end of the application
	  
            int rowCount = soybean.table.getRowCount();
            int columnCount = soybean.table.getColumnCount();
            for(int i = 0; i < rowCount; i++) {
                for(int j = 0; j < columnCount; j++) {
                    Object cellValue = soybean.table.getValueAt(i, j);
                    //now display this value graphically.
                    System.out.println(cellValue.toString());
                }
            }
	}

        if (command.equals(RESET))
        {
            System.out.println("Reset button pressed");
            System.out.flush();
            soybean.resetDataGui();
        }
    
        if (command.equals(ADD))
        {
          System.out.println("Reset button pressed");
          System.out.flush();
          soybean.insertIntoTable();
        }

        if (command.equals(UPDATE))
        {
            System.out.println("Update button pressed");
            System.out.flush();
            soybean.updateTable();
        }

        if (command.equals(DELETE))
        {
            System.out.println("Delete button pressed");
            System.out.flush();
            soybean.deleteFromTable();
        }

        if (command.equals(SEARCH))
        {
            System.out.println("Search button pressed");
            System.out.flush();
            soybean.searchTable(txtSearch.getText());
        }
    } // end of actionPerformed()

    /**
     * create the input components and containers
     */
    private Component createInputComponents()
    {
        JPanel textFieldsPane = new JPanel();
        textFieldsPane.setLayout(new GridBagLayout());

        txtSerialNumber = new JTextField(30);
        txtName = new JTextField(30);
        txtPrice = new JTextField(40);
        txtKgs = new JTextField(40);
        lblSerialNumber = new JLabel("Serial Number: ");
        lblName = new JLabel("Name: ");
        lblPrice = new JLabel("Price: ");
        lblKgs = new JLabel("Kgs: ");

        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.insets = new Insets(10, 10, 10, 10);

        gc.gridx = 0;
        gc.gridy = 0;
        textFieldsPane.add(lblSerialNumber, gc);

        gc.gridx = 1;
        gc.gridy = 0;
        textFieldsPane.add(txtSerialNumber, gc);

        gc.gridx = 0;
        gc.gridy = 1;
        textFieldsPane.add(lblName, gc);

        gc.gridx = 1;
        gc.gridy = 1;
        textFieldsPane.add(txtName, gc);
        
        gc.gridx = 0;
        gc.gridy = 2;
        textFieldsPane.add(lblPrice, gc);

        gc.gridx = 1;
        gc.gridy = 2;
        textFieldsPane.add(txtPrice, gc);

        gc.gridx = 0;
        gc.gridy = 3;
        textFieldsPane.add(lblKgs, gc);

        gc.gridx = 1;
        gc.gridy = 3;
        textFieldsPane.add(txtKgs, gc);

        textFieldsPane.setVisible(true);
        
        return textFieldsPane;
    }
  
  
    /**
     * create the components and containers
     */
    private Component createComponents()
    {
        /*
      //---layout managers
      personPane.setLayout(new BoxLayout(personPane, BoxLayout.Y_AXIS));
      salutationPane.setLayout(new BoxLayout(salutationPane, BoxLayout.X_AXIS));
        salMiniPane.setLayout(new BoxLayout(salMiniPane, BoxLayout.Y_AXIS));
      namesPane.setLayout(new BoxLayout(namesPane, BoxLayout.X_AXIS));
        givenPane.setLayout(new BoxLayout(givenPane, BoxLayout.Y_AXIS));
        familyPane.setLayout(new BoxLayout(familyPane, BoxLayout.Y_AXIS));
        */

      
        //---layout managers
        personPane.setLayout(new BoxLayout(personPane, BoxLayout.Y_AXIS));
        salutationPane.setLayout(new BoxLayout(salutationPane, BoxLayout.X_AXIS));
        salMiniPane.setLayout(new BoxLayout(salMiniPane, BoxLayout.Y_AXIS));
        namesPane.setLayout(new BoxLayout(namesPane, BoxLayout.Y_AXIS));
        givenPane.setLayout(new BoxLayout(givenPane, BoxLayout.Y_AXIS));
        familyPane.setLayout(new BoxLayout(familyPane, BoxLayout.Y_AXIS));

        //---colours (for testing)
        salMiniPane.setBackground(Color.cyan);
        salutationPane.setBackground(Color.pink);
        namesPane.setBackground(Color.yellow);
        familyPane.setBackground(Color.orange);
        givenPane.setBackground(Color.magenta);
        personPane.setBackground(Color.green);

        //personPane.setAlignmentX(Component.LEFT_ALIGNMENT);   // affects all alignment
        //salutationPane.setAlignmentX(Component.RIGHT_ALIGNMENT);   // align containers and labels 

        salutationBox.setAlignmentX(Component.LEFT_ALIGNMENT);   // align box and label 
        salutationBox.setEditable(true);                         // editable 
        salutationBox.setSelectedIndex(9);                       // default is Mr.

        salMiniPane.add(Box.createRigidArea(new Dimension(0, 20)));
        salMiniPane.add(salutationLabel);
        salMiniPane.add(salutationBox);

        salutationPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        salutationPane.add(Box.createRigidArea(new Dimension(0, 20)));
        salutationPane.add(salMiniPane);
        salutationPane.add(Box.createHorizontalGlue());

        givenPane.add(Box.createRigidArea(new Dimension(0, 20)));
        givenPane.add(givenNameLabel);
        givenPane.add(givenNameField);

        familyPane.add(Box.createRigidArea(new Dimension(0, 20)));
        familyPane.add(familyNameLabel);
        familyPane.add(familyNameField);
        familyPane.add(addressLabel);
        familyPane.add(addressField);

        namesPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        namesPane.add(Box.createRigidArea(new Dimension(0,20)));
        namesPane.add(givenPane);
        namesPane.add(familyPane);

        personPane.add(Box.createRigidArea(new Dimension(0, 20)));
        personPane.add(salutationPane);
        personPane.add(namesPane);
        personPane.add(Box.createRigidArea(new Dimension(0, 20)));


        //configure and layout the buttons on a panel with flow layout
        okButton.setActionCommand(OK);
        okButton.setMnemonic(KeyEvent.VK_O);
        resetButton.setActionCommand(RESET);
        resetButton.setMnemonic(KeyEvent.VK_R);
        buttonPane.add(okButton);
        buttonPane.add(resetButton);

        //Put the panels in another panel with border layout
        contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        contentPane.setBackground(Color.blue);
        contentPane.setLayout(new BorderLayout(10, 10));
        contentPane.add(personPane, BorderLayout.CENTER);
        contentPane.add(buttonPane, BorderLayout.SOUTH);

        //Add the Listeners
        okButton.addActionListener(this);
        resetButton.addActionListener(this);

        return contentPane;
    } // end of createComponents()
  
    /**
     * create buttons
     */
    private Component createButtons()
    {
	//configure and layout the buttons on a panel with flow layout
        okButton.setActionCommand(OK);
        okButton.setMnemonic(KeyEvent.VK_O);
        resetButton.setActionCommand(RESET);
        resetButton.setMnemonic(KeyEvent.VK_R);
        addButton.setActionCommand(ADD);
        addButton.setMnemonic(KeyEvent.VK_ADD);
        deleteButton.setActionCommand(DELETE);
        deleteButton.setMnemonic(KeyEvent.VK_DELETE);
        searchButton.setActionCommand(SEARCH);
        searchButton.setMnemonic(KeyEvent.VK_FIND);
        buttonPane.add(okButton);
        buttonPane.add(resetButton);
        buttonPane.add(addButton);
        buttonPane.add(updateButton);
        buttonPane.add(deleteButton);
        
        txtSearch = new JTextField(50);
        searchPane.setLayout(new BorderLayout(10, 10));
        searchPane.add(txtSearch, BorderLayout.NORTH);
        searchPane.add(searchButton, BorderLayout.SOUTH);

        contentPane.add(buttonPane, BorderLayout.NORTH);
        contentPane.add(searchPane, BorderLayout.EAST);

        //Add the Listeners
        okButton.addActionListener(this);
        resetButton.addActionListener(this);
        addButton.addActionListener(this);
        updateButton.addActionListener(this);
        deleteButton.addActionListener(this);
        searchButton.addActionListener(this);

	return contentPane;
    }

    private JMenuBar createSideBar()
    {
        //Where the GUI is created:
	JMenuBar menuBar;
	JMenu menu, submenu;
	JMenuItem menuItem;
	JRadioButtonMenuItem rbMenuItem;
	JCheckBoxMenuItem cbMenuItem;

	//Create the menu bar.
	menuBar = new JMenuBar();

	//Build the first menu.
	menu = new JMenu("A Menu");
	menu.setMnemonic(KeyEvent.VK_A);
	menu.getAccessibleContext().setAccessibleDescription(
			"The only menu in this program that has menu items");
	menuBar.add(menu);

	//a group of JMenuItems
	menuItem = new JMenuItem("A text-only menu item",
							 KeyEvent.VK_T);
	menuItem.setAccelerator(KeyStroke.getKeyStroke(
			KeyEvent.VK_1, ActionEvent.ALT_MASK));
	menuItem.getAccessibleContext().setAccessibleDescription(
			"This doesn't really do anything");
	menu.add(menuItem);

	menuItem = new JMenuItem("Both text and icon",
                                    new ImageIcon("images/middle.gif"));
	menuItem.setMnemonic(KeyEvent.VK_B);
	menu.add(menuItem);

	menuItem = new JMenuItem(new ImageIcon("images/middle.gif"));
	menuItem.setMnemonic(KeyEvent.VK_D);
	menu.add(menuItem);

	//a group of radio button menu items
	menu.addSeparator();
	ButtonGroup group = new ButtonGroup();
	rbMenuItem = new JRadioButtonMenuItem("A radio button menu item");
	rbMenuItem.setSelected(true);
	rbMenuItem.setMnemonic(KeyEvent.VK_R);
	group.add(rbMenuItem);
	menu.add(rbMenuItem);

	rbMenuItem = new JRadioButtonMenuItem("Another one");
	rbMenuItem.setMnemonic(KeyEvent.VK_O);
	group.add(rbMenuItem);
	menu.add(rbMenuItem);

	//a group of check box menu items
	menu.addSeparator();
	cbMenuItem = new JCheckBoxMenuItem("A check box menu item");
	cbMenuItem.setMnemonic(KeyEvent.VK_C);
	menu.add(cbMenuItem);

	cbMenuItem = new JCheckBoxMenuItem("Another one");
	cbMenuItem.setMnemonic(KeyEvent.VK_H);
	menu.add(cbMenuItem);

	//a submenu
	menu.addSeparator();
	submenu = new JMenu("A submenu");
	submenu.setMnemonic(KeyEvent.VK_S);

	menuItem = new JMenuItem("An item in the submenu");
	menuItem.setAccelerator(KeyStroke.getKeyStroke(
			KeyEvent.VK_2, ActionEvent.ALT_MASK));
	submenu.add(menuItem);

	menuItem = new JMenuItem("Another item");
	submenu.add(menuItem);
	menu.add(submenu);

	//Build second menu in the menu bar.
	menu = new JMenu("Another Menu");
	menu.setMnemonic(KeyEvent.VK_N);
	menu.getAccessibleContext().setAccessibleDescription(
			"This menu does nothing");
	menuBar.add(menu);
	
	return menuBar;
    }
}
