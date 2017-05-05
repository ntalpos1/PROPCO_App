import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SQLConnection {

    public static Statement stmt;
    public static PreparedStatement pst;
    public static Connection conn;
    
    public static void establishConnection(){
        //Connection conn;
        //Statement stmt;
        System.out.println("establishconnection");
        try{
            Class.forName("com.mysql.jdbc.Driver"); 
            System.out.println("class found default");
        }
        catch (ClassNotFoundException ex) {
            Logger.getLogger(ServiceReceipt.class.getName()).log(Level.SEVERE, null, ex);
        }
        try{
            //conn = DriverManager.getConnection("jdbc:mysql://192.168.1.123/PROPCO_test","dan","Zackmera");
            //conn = DriverManager.getConnection("jdbc:mysql://192.168.0.140/PROPCO_test","dan","Zackmera");
            conn = DriverManager.getConnection("jdbc:mysql://192.168.0.21/PROPCO_test","dan","Zackmera");
            stmt = conn.createStatement();
            //pst = conn.prepareStatement("");
            System.out.println("establishConnection statement created");
        }
        catch (SQLException ex) {
            Logger.getLogger(ServiceReceipt.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    public static ResultSet getRecordSet(String sqlStmt){
        System.out.println("getRecordSet " + sqlStmt);
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery(sqlStmt);
            rs.next();
        }
        catch(SQLException ex) {
            Logger.getLogger(ServiceReceipt.class.getName()).log(Level.SEVERE, null, ex);
            //if (stmt != null) { stmt.close(); }
            System.out.println("error");      
            }
        return rs;
    }
    
    public static String return_value(String sqlStmt,String return_field){
    System.out.println("getRecordSet " + sqlStmt);
        ResultSet rs = null;
        String my_value="";
        try {
            rs = stmt.executeQuery(sqlStmt);
            rs.next();
            my_value = rs.getString(return_field);
        }
        catch(SQLException ex) {
            Logger.getLogger(ServiceReceipt.class.getName()).log(Level.SEVERE, null, ex);
            //if (stmt != null) { stmt.close(); }
            System.out.println("error");      
            }
        return my_value;
    }
    
    public static ResultSet getMultipleRecordsRS(String sqlStmt){
        System.out.println("getMultipleRecordsRS " + sqlStmt);
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery(sqlStmt);
            //rs.next();
        }
        catch(SQLException ex) {
            Logger.getLogger(ServiceReceipt.class.getName()).log(Level.SEVERE, null, ex);
            //if (stmt != null) { stmt.close(); }
            System.out.println("getMultipleRecordsRS error");      
            }
        return rs;
    }
    public static int updateRecordSet(String sqlStmt){
        //System.out.println("updateRecordSet " + sqlStmt);
        int update = 0;
        try {
            update = stmt.executeUpdate(sqlStmt);
        }
        catch(SQLException ex) {
            Logger.getLogger(ServiceReceipt.class.getName()).log(Level.SEVERE, null, ex);
            //if (stmt != null) { stmt.close(); }
            System.out.println("error");      
        }
        return update;
    }
    public static void insertRecordSet(String sqlStmt){
        //System.out.println("insertRecordSet " + sqlStmt);
        //PreparedStatement pst = null;
        //ResultSet rs = null;
        try {
            Statement statement = conn.createStatement();
            System.out.println("1");
            statement.executeUpdate(sqlStmt);
            System.out.println("2");
            System.out.println("Data Inserted");
            

            //pst = conn.prepareStatement(sqlStmt);
            //pst.executeUpdate();
            //rs = stmt.executeQuery(sqlStmt);
        }
        catch(SQLException ex) {
            Logger.getLogger(ServiceReceipt.class.getName()).log(Level.SEVERE, null, ex);
            //if (stmt != null) { stmt.close(); }
            System.out.println("insert error");      
        }
        //return rs;
    }
 
    public static void viewTable(Connection con, String QueryReq)
    throws SQLException {

    Statement stmt = null;
    String query = QueryReq;

    try {
        stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            String coffeeName = rs.getString("COF_NAME");
            int supplierID = rs.getInt("SUP_ID");
            float price = rs.getFloat("PRICE");
            int sales = rs.getInt("SALES");
            int total = rs.getInt("TOTAL");
            System.out.println(coffeeName + "\t" + supplierID +
                               "\t" + price + "\t" + sales +
                               "\t" + total);
        }
    } catch (SQLException e ) {
        //JDBCTutorialUtilities.printSQLException(e);
        System.out.println("error");
    } finally {
        if (stmt != null) { stmt.close(); }
    }
}
     
    public Connection getConnection() throws SQLException {

    Connection conn = null;
    Properties connectionProps = new Properties();
    connectionProps.put("user", "dan");
    connectionProps.put("password", "Mexico1970");
    connectionProps.put("serverName","192.168.0.140");
    //connectionProps.put("serverName","192.168.1.123");
    //connectionProps.put("portNumber","");
    
    //conn = DriverManager.getConnection("jdbc:mysql://192.168.1.123", connectionProps); 
    conn = DriverManager.getConnection("jdbc:mysql://192.168.0.140", connectionProps);
            //+ ":" + this.portNumber + "/",                   connectionProps);
    
    System.out.println("Connected to database");
    return conn;
}
}