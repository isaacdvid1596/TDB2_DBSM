/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbms;

import java.sql.*;

import org.apache.derby.jdbc.EmbeddedDriver;

import java.awt.Window;
import java.util.ArrayList;
import java.util.Set;
import java.util.Vector;
import java.util.Properties;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;



/**
 *
 * @author JulioD
 */
public class DBConnect {
    
    private String currentuser,currentpass;
    Connection con = null;
    PreparedStatement st = null; //used to execute query with params
    ResultSet rs =null; //java object contains results of query execution
    
    //DBConnect constructor takes user and password as params
    
    public DBConnect(String username,String password) throws Exception
    {
        currentuser=username;
        currentpass=password;
    }
    
    //connect function
    
    public void connect() throws Exception
    {
       Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
//       con = DriverManager.getConnection("C:\\Users\\JulioD\\trialdb");
         con = DriverManager.getConnection("jdbc:derby:C:\\Users\\JulioD\\AppData\\Roaming\\NetBeans\\8.2\\derby\\mydb", currentuser, currentuser);          
    }
    
    //disconnect function
    
    public void disconnect() throws Exception
    {
        con.close();
    }
    
    // \connect
    

    

    
    
    //create functions for each type of query
    
    
    // tables
    
 
     public String createTable(String values)
    {
        String SQLCommand = String.format("CREATE TABLE %s;",values);
        return SQLCommand;
    }
    
    public String updateTable(String tableName, String columnName, String newData)
    {
        String sqlCommand = String.format("ALTER TABLE %s ALTER COLUMN %s %s;", tableName,columnName,newData);
        return sqlCommand;
    }
    
    public String deleteTable(String tableName)
    {
        String sqlCommand = String.format("DROP TABLE %s", tableName);
        return sqlCommand;
    }    
          
    public String displayAllTables()
    {
        String SQLCommand = String.format("SELECT * FROM SYS.SYSTABLES");
        return SQLCommand;
    }
    
    
    public String displayTable(String schemaName , String tableName)
    {
        String SQLCommand = String.format("SELECT * FROM %s.%s;",schemaName,tableName);
        return SQLCommand;
    }
    
   
    // /tables
    
    
    // Triggers
    
    public String displayAllTriggers()
    {
        String SQLCommand = String.format("SELECT * FROM SYS.SYSTRIGGERS");
        return SQLCommand;
    }
    
    public String createTrigger()
    {
        String SQLCommand = String.format("");
        return SQLCommand;
    }
     
    public String deleteTrigger(String triggerName)
    {
        String SQLCommand = String.format("DROP TRIGGER %s",triggerName);
        return SQLCommand;
    }
    
    
    // \Triggers
    
    // Index
    
    public String displayAllIndexes()
    {
        String SQLCommand = String.format("SELECT * FROM SYS.SYSCONGLOMERATES");
        return SQLCommand;
    }
    
    public String createIndex(String indexName, String tableName , String colName)
    {
        String SQLCommand = String.format("CREATE INDEX %s on %s(%s);",indexName,tableName,colName);
        return SQLCommand;
    }
    
    public String updateIndex(String oldIndex, String newIndex)
    {
        String SQLCommand = String.format("ALTER INDEX %s RENAME TO %s;",oldIndex,newIndex);
        return SQLCommand;
    }
    
    public String deleteIndex(String indexName)
    {
        String SQLCommand=String.format("DROP INDEX %s;",indexName);
        return SQLCommand;
    }
    
    // \Index
    
    
    //Users
    
    
    public String displayUser()
    {
        String SQLCommand = String.format("SELECT * FROM SYS.SYSUSERS");
        return SQLCommand;
    }
    
    
    
    // \Users
    
    //Schema
    
    public String displaySchema()
    {
        String SQLCommand = String.format("SELECT * FROM SYS.SYSSCHEMAS");
        return SQLCommand;
    }
    
    public String createSchema(String schemaName , String userName)
    {
        String SQLCommand = String.format("CREATE SCHEMA %s AUTHORIZATION %s;");
        return SQLCommand;
    }
        
    
    public String updateSchema(String oldSchema , String newSchema)
    {
        String SQLCommand = String.format("ALTER SCHEMA %s RENAME to %s;",oldSchema , newSchema);
        return SQLCommand;
    }
    
    public String deleteSchema(String schemaName)
    {
        String SQLCommand = String.format("DROP SCHEMA %s;",schemaName);
        return SQLCommand;
    }
                
    
    // \Schema
    
    //Views
    
    public String displayViews()
    {
        String SQLCommand = String.format("SELECT * FROM SYS.SYSVIEWS");
        return SQLCommand;
    }
    
    public String displayChecks()
    {
        String SQLCommand = String.format("SELECT * FROM SYS.SYSCHECKS");
        return SQLCommand;
    }
    
    public String createView(String viewName,String tableName)
    {
        String SQLCommand 
                = String.format("CREATE VIEW %s AS SELECT * FROM %s",viewName,tableName);
        return SQLCommand;
    }
    
    public String updateView(String viewName)
    {
        String SQLCommand = String.format("ALTER VIEW %s RECOMPILE;",viewName);
        return SQLCommand;
    }
    
    public String deleteView(String viewName)
    {
        String SQLCommand = String.format("DROP VIEW %s",viewName);
        return SQLCommand;
    }
    // \Views
    
    // DDL
    
    public String displayTableDDL(String tableName)
    {
            String SQLCommand = String.format("SCRIPT SIMPLE TABLE %s",tableName);
            return SQLCommand;
    }
    
    public String displayIndexDDL()
    {
            String SQLCommand = String.format("SCRIPT SIMPLE TABLE %;");
            return SQLCommand;
    }
    
    public String displayTriggerDDL()
    {
            String SQLCommand = String.format("SCRIPT SIMPLE TABLE %;");
            return SQLCommand;
    }
    
    public String displayUserDBDDL(String schemaName)
    {
        String SQLCommand = String.format("SCRIPT SIMPLE SCHEMA %s;",schemaName);
        return SQLCommand;
    }
    
    public String displayViewDDL()
    {
        String SQLCommand = String.format("SCRIPT SIMPLE TABLE %;");
        return SQLCommand; 
    }
    // \DDL
    
    //stored procedures and functions
    
    public String displayProceduresFunctions()
    {
        String SQLCommand = String.format("SELECT * FROM SYS.SYSALIASES");
        return SQLCommand;
    }
    
    //function for table fill
    
    public void populateTable(JTable mainTable, String SQLCommad) throws Exception
    {
        
        //create vector of vector of objects
        
        
        Vector<Vector<Object>>  data = new Vector<Vector<Object>>();
        Vector columns = new Vector();
        st = con.prepareStatement(SQLCommad);
        rs = st.executeQuery();
        ResultSetMetaData metadata = rs.getMetaData();
        
        //create vector type string of columnNames and assign each one of them the info from the metadata in the db
        Vector <String> colNames = new Vector<String>();
        int colcount = metadata.getColumnCount();
        
        
        //for cycle to fill columnsnames with metadata and add the info to each of the indexes on the loop
        for(int column=1 ; column<=colcount ; column++)
        {
            colNames.add(metadata.getColumnName(column));
            columns.add(colNames.get(column-1));    
        }
        
        //while there is a result set . next , keep creating vectors and add info to them
        while(rs.next())
        {
            Vector<Object> vector = new Vector<Object>();
            for(int columnIndex =1 ; columnIndex <= colcount;columnIndex++)
            {
                vector.add(rs.getObject(columnIndex));
            }
            data.add(vector);
        }
        
        //This is an implementation of TableModel that uses a Vector of Vectors to store the cell value objects.

        DefaultTableModel tableModel = new DefaultTableModel(data,columns);
        mainTable.setModel(tableModel);
        
        st.close();
        rs.close();
    }
    

    
    public void executeSQL(String SQLCommand) throws Exception
    {
        st = con.prepareStatement(SQLCommand);
        st.execute();
        st.close();
    }
    
    
    
    
    
}
