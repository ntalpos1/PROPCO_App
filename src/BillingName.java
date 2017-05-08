
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dan
 */
public class BillingName {
    int BusinessID;
    String BillingName;
    String Alias;
    String Address;
    String City;
    String Province;
    String PostalCode;
    String ContactName;
    String Phone1;
    String Ext;
    String Phone2;
    String Ext2;
    String Fax;
    String Email;
    
    public BillingName(){
        this(0,"","","","","","","","","","","","","");
    }
    public BillingName(int BusinessID,String BillingName,String Alias,String Address,String City,String Province,String PostalCode,String ContactName,String Phone1,String Ext,String Phone2,String Ext2,String Fax,String Email){
        this.BusinessID = BusinessID;
        this.BillingName = BillingName;
        this.Alias = Alias;
        this.Address = Address;
        this.City = City;
        this.Province = Province;
        this.PostalCode = PostalCode;
        this.ContactName = ContactName;
        this.Phone1 = Phone1;
        this.Ext = Ext;
        this.Phone2 = Phone2;
        this.Ext2 =  Ext2;
        this.Fax = Fax;
        this.Email = Email;
    }
    
    public BillingName(ResultSet rs){
        try{
            this.BusinessID = rs.getInt("BID");
            this.BillingName = rs.getString("Name");
            this.Alias = rs.getString("Alias");
            this.Address = rs.getString("Address");
            this.City = rs.getString("City");
            this.Province = rs.getString("Province");
            this.PostalCode = rs.getString("PostalCode");
            this.ContactName = rs.getString("ContactName");
            this.Phone1 = rs.getString("PrimaryPhone");
            this.Ext = rs.getString("Ext");
            this.Phone2 = rs.getString("SecondaryPhone");
            this.Ext2 = rs.getString("Ext2");
            this.Fax = rs.getString("Fax");
            this.Email = rs.getString("EmailAddress");
        }
        catch(SQLException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
            //if (stmt != null) { stmt.close(); }
            System.out.println("error in Class BillingName"); 
        }
    }
    
    public int getBusinessID(){
        return BusinessID;
    }
    public void setBusinessID(int value){
        BusinessID = value;
    }
    public String getBillingName(){
        return BillingName;
    }
    public void setBillingName(String value){
        BillingName = value;
    }
    public String getAlias(){
        return Alias;
    }
    public void setAlias(String value){
        Alias = value;
    }
    public String setAddress(){
        return Address;
    }
    public void getAddress(String value){
        Address = value;
    }
    public String setCity(){
        return City;
    }
    public void getCity(String value){
        City = value;
    }
    public String setProvince(){
        return Province;
    }
    public void getProvince(String value){
        Province = value;
    }
    public String setPostalCode(){
        return PostalCode;
    }
    public void getPostalCode(String value){
        PostalCode = value;
    }
    public String setContactName(){
        return ContactName;
    }
    public void getContactName(String value){
        ContactName = value;
    }
    public String setPhone1(){
        return Phone1;
    }
    public void getPhone1(String value){
        Phone1 = value;
    }
    public String setExt(){
        return Ext;
    }
    public void getExt(String value){
        Ext = value;
    }
    public String setPhone2(){
        return Phone2;
    }
    public void getPhone2(String value){
        Phone2 = value;
    }
    public String setExt2(){
        return Ext2;
    }
    public void getExt2(String value){
        Ext2 = value;
    }
    public String setFax(){
        return Fax;
    }
    public void getFax(String value){
        Fax = value;
    }
    public String setEmail(){
        return Email;
    }
    public void getEmail(String value){
        Email = value;
    }
}
