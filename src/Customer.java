
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
public class Customer  {
    int CustID;
    String CustName;
    String Notes;
    String Address;
    String AddressNotes;
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
    Boolean Bundle;
    String BundleName;
    String Department;
    String ContractNr;
    int userID;
    
    public Customer(){
        this(0,"","","","","","","","","","","","","","","","","",0);
    }
    
    public Customer(ResultSet rs){
        
        try{
            this.CustID = rs.getInt("CID");
            this.CustName = rs.getString("CustomerName");
            this.Notes = rs.getString("Notes");
            this.Address = rs.getString("Address");
            this.AddressNotes = rs.getString("AddressNotes");
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
            this.Bundle = false;
            this.BundleName = rs.getString("BundleName");
            if (!this.BundleName.isEmpty()) {this.Bundle = true;}
            this.Department = rs.getString("DepartmentName");
            this.ContractNr = rs.getString("ContractNr");
            this.userID = rs.getInt("UID");
        }
        catch(SQLException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
            //if (stmt != null) { stmt.close(); }
            System.out.println("error in Class Customer"); 
        }
    }
    
    public Customer(int CustID,String Name,String Notes1,String Street,String ExtraInfo, String CityName, String Prov, String PC, String Contact,String Ph1,String Ext1,String Ph2,String ext2,String fax, String email,String bundlename,String DeptName,String ContrNr,int userID){
        this.CustID = CustID;
        this.CustName = Name;
        this.Notes = Notes1;
        this.Address = Street;
        this.AddressNotes = ExtraInfo;
        this.City = CityName;
        this.Province = Prov;
        this.PostalCode = PC;
        this.ContactName = Contact;
        this.Phone1 = Ph1;
        this.Ext = Ext1;
        this.Phone2 = Ph2;
        this.Ext2 = ext2;
        this.Fax = fax;
        this.Email = email;
        if (bundlename != null) {
           this.Bundle = false; 
        }
        this.BundleName = bundlename;
        this.Department = DeptName;
        this.ContractNr = ContrNr;
        this.userID = userID;
    }
    
    public int getCustID(){
        return CustID;
    }
    public void setCustID(int value){
        CustID = value;
    }
    public String getCustName(){
        return CustName;
    }
    public void setCustName(String value){
        CustName = value;
    }
    public String getNotes(){
        return Notes;
    }
    public void setNotes(String value){
        Notes = value;
    }
    public String setAddress(){
        return Address;
    }
    public void getAddress(String value){
        Address = value;
    }
    public String setAddressNotes(){
        return AddressNotes;
    }
    public void getAddressNotes(String value){
        AddressNotes = value;
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
    public Boolean setBundle(){
        return Bundle;
    }
    public void getBundle(Boolean value){
        Bundle = value;
    }
    public String setBundleName(){
        return BundleName;
    }
    public void getBundleName(String value){
        BundleName = value;
    }
    public String setDepartment(){
        return Department;
    }
    public void getDepartment(String value){
        Department = value;
    }
    public String setContractNr(){
        return ContractNr;
    }
    public void getContractNr(String value){
        ContractNr = value;
    }
    public int setuserID(){
        return userID;
    }
    public void getuserID(int value){
        userID = value;
    }
}
