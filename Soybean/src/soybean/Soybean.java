/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soybean;

/**
 *
 * @author User
 */
// Soybean.java = using a ComboBox

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class Soybean
{
    GuiOperations guiOperations;
    
    DefaultTableModel mTableModel;
    DefaultTableModel mTableModelSecond;
    JTable table;
    JTable tableSecond;

    int selectedId;
    int selectedTableRow;

    public Soybean()  
    {
        guiOperations = new GuiOperations(this);
    }

    public void createTable()
    {
      	try
	{
            Connection 	con;         // connection object 
            // a MySQL statement
            Statement stmt;
            // a MySQL query
            String query;
            // the results from a MySQL query
            ResultSet rs;

            // 2 dimension array to hold table contents
            // it holds temp values for now
            Object rowData[][] = {{"Row1-Column1", "Row1-Column2", "Row1-Column3",
                                                       "Row1-Column4", "Row1-Column5", "Row1-Column6", "Row1-Column7"}};
            // array to hold column names
            Object columnNames[] = {"ID", "Serial Number", "Name", "Price", "Kgs", "Total Asset", "Checked"};

            // create a table model and table based on it
            mTableModel = new DefaultTableModel(rowData, columnNames);
            table = new JTable(mTableModel) {
                public Component prepareRenderer(TableCellRenderer r, int rw, int col) {			
                    Component c = super.prepareRenderer(r, rw, col);
                    c.setBackground(Color.WHITE);
                    if (rw % 2 == 0) {
                        c.setBackground(Color.GREEN);
                    }

                    return c;
                }

                private static final long serialVersionUID = 1L;

                /*@Override
                public Class getColumnClass(int column) {
                return getValueAt(0, column).getClass();
                }*/
                @Override
                public Class getColumnClass(int column) {
                    switch (column) {
                        case 0:
                            return String.class;
                        case 1:
                            return String.class;
                        case 2:
                            return String.class;
                        case 6:
                            return Boolean.class;
                                            default:
                                                    return getValueAt(0, column).getClass();
                    }
                }
            };


            con = DriverManager
                .getConnection("jdbc:mysql://localhost/prototype?"
                    + "user=root&password=labadmin&" + "useSSL=false&amp;"); 
            // run the desired query
            query = "SELECT id, serial_number, name, price, kgs, price * kgs, checked FROM soybean";
            // make a statement with the server
            stmt = con.createStatement();
            // execute the query and return the result
            rs = stmt.executeQuery(query);

            // remove the temp row
            mTableModel.removeRow(0);

            // create a temporary object array to hold the result for each row
            Object[] rows;

            Locale indonesian = new Locale("id", "ID");
            NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(indonesian);
            NumberFormat decimalFormat = NumberFormat.getNumberInstance(indonesian);

            DecimalFormat y_format = new DecimalFormat("###,###.##");
            // for each row returned
            while (rs.next()) {
                // add the values to the temporary row
                rows = new Object[]{rs.getString(1), rs.getString(2), rs.getString(3),
                                                              currencyFormat.format(rs.getInt(4)),
                                                              decimalFormat.format(rs.getInt(5)),
                                                              currencyFormat.format(rs.getInt(6)),
                                                              rs.getBoolean(7)};
                // add the temp row to the table
                mTableModel.addRow(rows);
            }
        }  
        catch(SQLException ex) 
        { 
          System.err.println("SQLException: " + ex.getMessage()); 
        }
    }

    public void createTableSecond()
    {
      	try
	{
            Connection 	con;         // connection object 
            // a MySQL statement
            Statement stmt;
            // a MySQL query
            String query;
            // the results from a MySQL query
            ResultSet rs;

            // 2 dimension array to hold table contents
            // it holds temp values for now
            Object rowData[][] = {{"Row1-Column1", "Row1-Column2", "Row1-Column3",
                                                       "Row1-Column4"}};
            // array to hold column names
            Object columnNames[] = {"ID", "Price", "Kgs", "Total Asset"};

            // create a table model and table based on it
            mTableModelSecond = new DefaultTableModel(rowData, columnNames);
            tableSecond = new JTable(mTableModelSecond) {
                public Component prepareRenderer(TableCellRenderer r, int rw, int col) {			
                    Component c = super.prepareRenderer(r, rw, col);
                    c.setBackground(Color.WHITE);
                    if (rw % 2 == 0) {
                        c.setBackground(Color.GREEN);
                    }

                    return c;
                }

                private static final long serialVersionUID = 1L;

                /*@Override
                public Class getColumnClass(int column) {
                return getValueAt(0, column).getClass();
                }*/
                @Override
                public Class getColumnClass(int column) {
                    switch (column) {
                        case 0:
                            return String.class;
                        case 1:
                            return String.class;
                        case 2:
                            return String.class;
                        case 6:
                            return Boolean.class;
                                            default:
                                                    return getValueAt(0, column).getClass();
                    }
                }
            };

            con = DriverManager
                      .getConnection("jdbc:mysql://localhost/prototype?"
                              + "user=root&password=labadmin&" + "useSSL=false&amp;"); 
            // run the desired query
            query = "SELECT id, price, kgs, price * kgs FROM soybean";
            // make a statement with the server
            stmt = con.createStatement();
            // execute the query and return the result
            rs = stmt.executeQuery(query);

            // remove the temp row
            mTableModelSecond.removeRow(0);

            // create a temporary object array to hold the result for each row
            Object[] rows;

            Locale indonesian = new Locale("id", "ID");
            NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(indonesian);
            NumberFormat decimalFormat = NumberFormat.getNumberInstance(indonesian);

            DecimalFormat y_format = new DecimalFormat("###,###.##");
            // for each row returned
            while (rs.next()) {
                // add the values to the temporary row
                rows = new Object[]{rs.getString(1),
                                                              currencyFormat.format(rs.getInt(2)),
                                                              decimalFormat.format(rs.getInt(3)),
                                                              currencyFormat.format(rs.getInt(4))};
                // add the temp row to the table
                mTableModelSecond.addRow(rows);
            }                
        }  
        catch(SQLException ex) 
        { 
          System.err.println("SQLException: " + ex.getMessage()); 
        }
    }

    public void insertIntoTable()
    {
      	try
	{
            Connection 	con;         // connection object 
            // a MySQL statement
            PreparedStatement preparedStmt;
            // a MySQL query
            String query;
            // the results from a MySQL query
            ResultSet rs;
            // generated primary key
            int key = 0;

            Locale indonesian = new Locale("id", "ID");
            NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(indonesian);
            NumberFormat decimalFormat = NumberFormat.getNumberInstance(indonesian);

            con = DriverManager
                      .getConnection("jdbc:mysql://localhost/prototype?"
                              + "user=root&password=labadmin&" + "useSSL=false&amp;");

            // run the desired query
            query = "INSERT INTO soybean (serial_number, name, price, kgs, checked)"
                    + " VALUES (?, ?, ?, ?, ?)";

            // create insert prepared statement with the server
            preparedStmt = con.prepareStatement(query,
                                    Statement.RETURN_GENERATED_KEYS);
            preparedStmt.setInt(1, Integer.parseInt(
                                    guiOperations.txtSerialNumber.getText()));
            preparedStmt.setString(2, guiOperations.txtName.getText());
            preparedStmt.setDouble(3, Double.parseDouble(
                                    guiOperations.txtPrice.getText()));
            preparedStmt.setDouble(4, Double.parseDouble(
                                    guiOperations.txtKgs.getText()));
            preparedStmt.setBoolean(5, false);

            // execute the prepared statement
            preparedStmt.execute();

            // get generated key(s)
            rs = preparedStmt.getGeneratedKeys();
            if (rs != null && rs.next())
            {
                key = rs.getInt(1);
                System.out.println("Generated key: " + key);
            }
            else
            {
                System.out.println("No generated key.");
            }

            con.close();

            // create a temporary object array to hold the result for each row
            Object[] rowsNew = new Object[]{key,
                                         guiOperations.txtSerialNumber.getText(),
                                         guiOperations.txtName.getText(),
                                         currencyFormat.format(Double.parseDouble(
                                            guiOperations.txtPrice.getText())),
                                         decimalFormat.format(Double.parseDouble(
                                            guiOperations.txtKgs.getText())),
                                         currencyFormat.format(Double.parseDouble(
                                            guiOperations.txtPrice.getText()) * Double.parseDouble(
                                            guiOperations.txtKgs.getText())),
                                         false};
            mTableModel.addRow(rowsNew);
            mTableModelSecond.addRow(rowsNew);
        }  
        catch(SQLException ex)
        { 
          System.err.println("SQLException: " + ex.getMessage()); 
        }
    }

    public void updateTable()
    {
      	try
	{
            Connection 	con;         // connection object 
            // a MySQL statement
            PreparedStatement preparedStmt;
            // a MySQL query
            String query;
            // the results from a MySQL query
            ResultSet rs;

            Locale indonesian = new Locale("id", "ID");
            NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(indonesian);
            NumberFormat decimalFormat = NumberFormat.getNumberInstance(indonesian);

            con = DriverManager
                      .getConnection("jdbc:mysql://localhost/prototype?"
                              + "user=root&password=labadmin&" + "useSSL=false&amp;");

            // run the desired query
            query = "UPDATE soybean"
                    + " SET serial_number = ?, name = ?, price = ?, kgs = ?, checked = ?"
                    + " WHERE id = ?";

            // create update prepared statement with the server
            preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(1, Integer.parseInt(
                                    guiOperations.txtSerialNumber.getText()));
            preparedStmt.setString(2, guiOperations.txtName.getText());
            preparedStmt.setDouble(3, Double.parseDouble(
                                    guiOperations.txtPrice.getText()));
            preparedStmt.setDouble(4, Double.parseDouble(
                                    guiOperations.txtKgs.getText()));
            preparedStmt.setBoolean(5, false);
            preparedStmt.setInt(6, selectedId);

            // execute the prepared statement
            preparedStmt.executeUpdate();

            con.close();

            // update values in table view
            mTableModel.setValueAt(guiOperations.txtSerialNumber.getText(), selectedTableRow, 1);
            mTableModel.setValueAt(guiOperations.txtName.getText(), selectedTableRow, 2);
            mTableModel.setValueAt(currencyFormat.format(Double.parseDouble(
                                            guiOperations.txtPrice.getText())),
                                            selectedTableRow, 3);
            mTableModel.setValueAt(decimalFormat.format(Double.parseDouble(
                                            guiOperations.txtKgs.getText())),
                                            selectedTableRow, 4);
            mTableModel.setValueAt(currencyFormat.format(Double.parseDouble(
                                            guiOperations.txtPrice.getText())
                                            * Double.parseDouble(
                                            guiOperations.txtKgs.getText())),
                                            selectedTableRow, 5);

            mTableModelSecond.setValueAt(currencyFormat.format(Double.parseDouble(
                                            guiOperations.txtPrice.getText())),
                                            selectedTableRow, 1);
            mTableModelSecond.setValueAt(decimalFormat.format(Double.parseDouble(
                                            guiOperations.txtKgs.getText())),
                                            selectedTableRow, 2);
            mTableModelSecond.setValueAt(currencyFormat.format(Double.parseDouble(
                                            guiOperations.txtPrice.getText())
                                            * Double.parseDouble(
                                            guiOperations.txtKgs.getText())),
                                            selectedTableRow, 3);
        }  
        catch(SQLException ex)
        { 
            System.err.println("SQLException: " + ex.getMessage()); 
        }
    }

    public void deleteFromTable()
    {
      	try
	{
            Connection 	con;         // connection object 
            // a MySQL statement
            PreparedStatement preparedStmt;
            // a MySQL query
            String query;
            // the results from a MySQL query
            ResultSet rs;

            Locale indonesian = new Locale("id", "ID");
            NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(indonesian);
            NumberFormat decimalFormat = NumberFormat.getNumberInstance(indonesian);

            con = DriverManager
                      .getConnection("jdbc:mysql://localhost/prototype?"
                              + "user=root&password=labadmin&" + "useSSL=false&amp;");

            // run the desired query
            query = "DELETE FROM soybean WHERE id = ?";

            // create insert prepared statement with the server
            preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(1, selectedId);

            // execute the prepared statement
            preparedStmt.execute();

            con.close();

            //mTableModel.removeRow(5);
            mTableModel.removeRow(selectedTableRow);
            mTableModelSecond.removeRow(selectedTableRow);
        }  
        catch(SQLException ex)
        { 
            System.err.println("SQLException: " + ex.getMessage()); 
        }
    }

    public void searchTable(String text)
    {
      	try
	{
            Connection 	con;         // connection object 
            // a MySQL statement
            PreparedStatement preparedStmt;
            // a MySQL query
            String query;
            // the results from a MySQL query
            ResultSet rs;

            Locale indonesian = new Locale("id", "ID");
            NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(indonesian);
            NumberFormat decimalFormat = NumberFormat.getNumberInstance(indonesian);

            con = DriverManager
                      .getConnection("jdbc:mysql://localhost/prototype?"
                              + "user=root&password=labadmin&" + "useSSL=false&amp;");

            // run the desired query
            query = "SELECT id, serial_number, name, price, kgs, price * kgs, checked FROM soybean WHERE name LIKE '%" + text + "%'";

            // create insert prepared statement with the server
            preparedStmt = con.prepareStatement(query);
            //preparedStmt.setString(1, text);

            // execute the prepared statement
            rs = preparedStmt.executeQuery();


                            // remove the temp row
            //mTableModel.removeRow(0);

            // create a temporary object array to hold the result for each row
            Object[] rows;

            DecimalFormat y_format = new DecimalFormat("###,###.##");
            // for each row returned
            while (rs.next()) {
                // add the values to the temporary row
                rows = new Object[]{rs.getString(1), rs.getString(2), rs.getString(3),
                                                              currencyFormat.format(rs.getInt(4)),
                                                              decimalFormat.format(rs.getInt(5)),
                                                              currencyFormat.format(rs.getInt(6)),
                                                              rs.getBoolean(7)};
                // add the temp row to the table
                mTableModel.addRow(rows);
            }

            con.close();
        }  
        catch(SQLException ex)
        { 
          System.err.println("SQLException: " + ex.getMessage()); 
        }
    }

    public void tableMouseClicked()
    {
        table.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                selectedTableRow = table.getSelectedRow();
                selectedId = Integer.parseInt(mTableModel.getValueAt(selectedTableRow, 0).toString());
            }
        });
    }
  
    /**
     * This is the top of the application.  Of course
     * the application would normally be more substantial
     * and could involve sending messages to a server.
     */
    public void doApplication(String enteredData)
    {
        /*
        StringBuffer sb = new StringBuffer(enteredData);
        Person       me = new Person();

        System.out.println("---address label---");
        me.update(sb);
        sb.setLength(0);
        me.formatEnvelope(sb);
        System.out.println(sb);
        */

            /*
            List<String> numdata = new ArrayList<String>();
            for (int count = 0; count < model.getRowCount(); count++){
                    numdata.add(model.getValueAt(count, 0).toString());
            }
            */

            /*
            System.out.println("Storing data");

            // the mysql insert statement
        String query = " INSERT INTO soybean (id, serial_number, name, price, kgs, checked)"
          + " values (?, ?, ?, ?, ?, ?)";

        // create the mysql insert preparedstatement
        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setString (1, "Barney");
        preparedStmt.setString (2, "Rubble");
        preparedStmt.setDate   (3, startDate);
        preparedStmt.setBoolean(4, false);
        preparedStmt.setInt    (5, 5000);

        // execute the preparedstatement
        preparedStmt.execute();
            */
    }

    /**
     * get data from the gui
     */
    public String getDataGui(String sep)
    {
        StringBuffer temp = new StringBuffer("");

        System.out.println("in getDataGui()");
        temp.append(sep);
        temp.append( ( (String) guiOperations.salutationBox.getSelectedItem() ) + sep);
        temp.append(guiOperations.givenNameField.getText() + sep);
        temp.append(guiOperations.familyNameField.getText() + sep);
        temp.append(guiOperations.addressField.getText() + sep);
        return temp.toString();

    } // end of getDataGui()

    /**
     *
     */
    public void resetDataGui()
    {
        //----------------------------------------- 
        // local variables (in order of usage) 
        //----------------------------------------- 
        String		  url;         // url of dataSource used to make connection 
        Connection 	con;         // connection object 
        Statement 	stmt;        // re-usable SQL statement object 
        String 		  queryString; // SQL command for executeQuery() 
        ResultSet   rs;          // (whole) result of query 
        int q1 = 0;    // to be displayed in GUI
        String q2 = "";    // to be displayed in GUI

        System.out.println("Running program: QueryActor"); 
        //----------------------------------------------------------------- 
        // load the driver (name provided by driver supplier) 
        //----------------------------------------------------------------- 
        try 
        { 
            System.out.println("Loading driver"); 
            //Class.forName("myDriver.ClassName"); 
            //Class.forName("sun.jdbc.odbc.JdbcOdbcDriver"); 
            Class.forName("com.mysql.jdbc.Driver"); 
        }  
        catch(java.lang.ClassNotFoundException e) 
        { 
            System.err.print("ClassNotFoundException: "); 
            System.err.println(e.getMessage()); 
        } 

        //----------------------------------------------- 
        // create and execute one or more SQL statements 
        //----------------------------------------------- 
        try 
        { 
            //------------------------------------------------ 
            // set up the URL and make the connection object 
            //------------------------------------------------ 
            // set up for DataSource "Access" 
            // which is an MS-Access database ... *.mdb registred with ODBC 
            // url = "jdbc:mySubprotocol:myDataSource"; 
            // url = "jdbc:odbc:Seneca"; 

            // con = DriverManager.getConnection(url, "myLogin", "mypassword"); 
            // System.out.println("Connecting to " + url); 
            // MS Access does not seem to care about login and password 
            // con = DriverManager.getConnection(url, "Ignore", "Ignore");
            con = DriverManager
                .getConnection("jdbc:mysql://localhost/prototype?"
                    + "user=root&password=password"); 

            //------------------------------------------------ 
            // create the (reusable) SQL statement object 
            //------------------------------------------------ 
            System.out.println("Making statement object"); 
            stmt = con.createStatement(); 

            //------------------------------------------------ 
            // supply and execute one SQL statement 
            //------------------------------------------------ 
            System.out.println("Querying table Soybean"); 
            queryString = "SELECT count(*) FROM Soybean "; 
            rs = stmt.executeQuery(queryString); 
            while (rs.next()) 
            { 
                String c1 = rs.getString(1); 
                System.out.println("Record Count is: " + c1); 
            } 

            //------------------------------------------------ 
            // supply and execute one SQL statement 
            //------------------------------------------------ 
            System.out.println("Querying table Soybean"); 
            queryString = "SELECT id, name FROM soybean"; 
            rs = stmt.executeQuery(queryString); 
            System.out.println(""); 
            System.out.println(pad("ID", 25) + "Name"); 
            System.out.println(pad("====", 25) + pad("====", 25)); 
            while (rs.next()) 
            { 
                int c1 = rs.getInt("id"); 
                String c2 = rs.getString("name"); 
                q1 = c1;
                q2 = c2;
                System.out.println(pad(Integer.toString(c1), 25) + pad(c2, 25)); 
            }  
            System.out.println(""); 

            //------------------------------------------------ 
            // close the statement object 
            //------------------------------------------------ 
            System.out.println("closing statement object"); 
            stmt.close(); 

            //------------------------------------------------ 
            // close the connection 
            //------------------------------------------------ 
            System.out.println("Closing connection"); 
            con.close(); 

        }  
        catch(SQLException ex) 
        { 
            System.err.println("SQLException: " + ex.getMessage()); 
        }

        System.out.println("in resetDataGui()");
        guiOperations.salutationBox.setSelectedIndex(9);                       // default is Mr.
        guiOperations.givenNameField.setText(Integer.toString(q1));
        guiOperations.familyNameField.setText(q2);
        guiOperations.addressField.setText("");
    } // end of resetDataGui()

    private static String pad( String str, int width) 
    { 
        int     k = 0; 
        String  temp = new String(str); 

        while (temp.length() < width) 
        { 
            temp = temp + " "; 
        }  
        return temp; 
    } // end of pad() 

    /**
     * the main() method ... because this is an application
     */
    public static void main(String[] args)
    {
        try
        {
            UIManager.setLookAndFeel(
            UIManager.getCrossPlatformLookAndFeelClassName());
        }
        catch (Exception e)
        {
            // this is a do-nothing catch-clause.  This is a technique
            // for ignoring exceptions as the occur.  You should use this
            //  with care!
        }

	SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                    new Soybean();
            }
	});
    } // end of main()
} // end of class Soybean