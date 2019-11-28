package Game;

/*
*
*@author Pierce Miller 
*@studentid 14872510
*
*/

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBC {

    private Connection conn = null;
    //String url = "jdbc:derby:enemyDB;create=true";  
    private String url = "jdbc:derby://localhost:1527/enemyDB";
    private String username = "pokemoon";
    private String password = "password";
    private Statement statement;
    private Boolean tableExists = false;
    private ResultSet rs;

    public void enemyDB() {

        try {

            conn = DriverManager.getConnection(url, username, password);
            statement = conn.createStatement();
            
            checkTableExisting("ENEMY");
            statement.executeUpdate("CREATE TABLE ENEMY (ENEMYID INT, MOON VARCHAR(20), DAMAGE INT)");
            statement.executeUpdate("INSERT INTO ENEMY VALUES (6, 'CHAIZARD', 55),(111, 'RAYHORN', 43),"
                    + "(252, 'TREEKOO', 30),"
                    + "(255, 'TORCHICK', 30),"
                    + "(373, 'SULAMANCE', 50),"
                    + "(248, 'TIRANITA', 60)");

        } catch (Throwable ex) {
            System.err.println("SQL Exception: " + ex.getMessage());
        }
    }

    public void trainerDB() {

        try {
            
            this.checkTableExisting("TRAINERS");
            
            statement.executeUpdate("CREATE TABLE TRAINERS (NAME VARCHAR(20), AGE INT)");
            statement.executeUpdate("INSERT INTO TRAINERS VALUES ('Calvin', 21),"
                    + "('Ben', 20),"
                    + "('Wonde', 21),"
                    + "('Sean', 21)");

        } catch (Throwable ex) {
            System.err.println("SQL Exception: " + ex.getMessage());
        }
    }

    private void checkTableExisting(String newTableName) {

        try {

            System.out.println("checking for existing tables.... ");
            String[] types = {"TABLE"};

            DatabaseMetaData dbmd = conn.getMetaData();
            ResultSet rsDBMeta = dbmd.getTables(null, null, null, null);
            Statement dropStatement = null;

            while (rsDBMeta.next()) {

                String tableName = rsDBMeta.getString("TABLE_NAME");

                System.out.println("found: " + tableName);
                if (tableName.compareToIgnoreCase(newTableName) == 0) {

                    System.out.println(tableName + "  needs to be deleted");
                    String sqlDropTable = "DROP TABLE " + newTableName;
                    dropStatement = conn.createStatement();
                    dropStatement.executeUpdate(sqlDropTable);

                    System.out.println("table deleted");
                    tableExists = true;
                }
            }
            if (rsDBMeta != null) {
                rsDBMeta.close();
            }
            if (dropStatement != null) {
                dropStatement.close();
            }

        } catch (SQLException ex) {
            System.err.println("SQL Exception: " + ex.getMessage());
        }
    }
}
