
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dan
 */
public class ServiceRequest {
    private static int ServReqNr;
    private static int CustID;
    private static int BusinessID;
    private static String BundleName;
    private static String PO;
    private static String Freq;
    private static String ServiceType;
    private static String Payment;
    private static String DaySched;
    private static String CalendarDate;
    private static String CreationDate;
    private static String UpdateDate;
    private static int user_id;
    
    
    public ServiceRequest(){
        //this(0,0,0,"","","","","","","","","",0);
    }
    public ServiceRequest(int user_id){
        //this(0,0,0,"","","","","","","","","",user_id);
        this.user_id = user_id;
    }
    public ServiceRequest(int ServReqNr,int CustID,int BusinessID){
        //this(ServReqNr,CustID,BusinessID,"","","","","","","","","",0);
        this.ServReqNr = ServReqNr;
    }                             
    public ServiceRequest(int CustID,int BusinessID,String BundleName,String PO,String Freq,String ServiceType,String Payment,String DaySched,String CalendarDate,String CreationDate,String UpdateDate,int user_id){
        this.CustID = CustID;
        this.BusinessID = BusinessID;
        this.BundleName = BundleName;
        this.PO = PO;
        this.Freq = Freq;
        this.ServiceType = ServiceType;
        this.Payment = Payment;
        this.DaySched = DaySched;
        this.CalendarDate = CalendarDate;
        this.CreationDate = DateUtils.now_date_time();
        this.UpdateDate = DateUtils.now_date_time();
    }
    
    public int SelectService_Request_Table(){
        //need to retrieve ServiceReqNr
        String sqlStmt = null;
        ResultSet rs = null;
        int my_value = 0;
        sqlStmt = "Select ServiceReqNr from Service_Request WHERE User_id='" 
                + this.user_id + "' AND ActivityCount = 0";
        try{
            rs = SQLConnection.getRecordSet(sqlStmt);
            System.out.println("ServiceReqNr is: " + rs.getInt("ServiceReqNr"));
            my_value = rs.getInt("ServiceReqNr");
        }
        catch(SQLException ex){
            
        }
        return my_value;
    }
    
    public void LoadArray(){
        Object[] my_array = new Object[12];
        my_array[0] = this.ServReqNr;
        my_array[1] = this.CustID;
        my_array[2] = this.BusinessID;
        my_array[3] = this.BundleName;
        my_array[4] = this.PO;
        my_array[5] = this.Freq;
        my_array[6] = this.ServiceType;
        my_array[7] = this.Payment;
        my_array[8] = this.DaySched;
        my_array[9] = this.CalendarDate;
        my_array[10] = this.CreationDate;
        my_array[11] = this.UpdateDate;
        my_array[12] = this.user_id;
    }
    public static void main(String[] args){
        ServiceRequest an_instance = new ServiceRequest();
        an_instance.LoadArray();
    }
    
    
  /*  
    public void start(Object[] sr_array){
        CustID = Integer.parseInt(sr_array[0].toString());
        BusinessID = Integer.parseInt(sr_array[1].toString());
        DeptID = Integer.parseInt(sr_array[2].toString());
        PO = sr_array[3].toString();
        Freq = sr_array[4].toString();
        ServiceType = sr_array[5].toString();
        Payment = sr_array[6].toString();
        DaySched = sr_array[7].toString();
        CalendarDate = sr_array[8].toString();
        CreationDate = DateUtils.now_date_time();
        UpdateDate = DateUtils.now_date_time();
        //user_id = Integer.parseInt(sr_array[11].toString());
    }
*/

    public int getServReqNr(){
        return ServReqNr;
    }
    public void setServReqNr(int value){
        ServReqNr = value;
    }
    public int getCustID(){
        return CustID;
    }
    public void setCustID(int value){
        CustID = value;
    }
    public int getBusinessID(){
        return BusinessID;
    }
    public void setBusinessID(int value){
        BusinessID = value;
    }
    public String getBundleName(){
        return BundleName;
    }
    public void setBundleName(String value){
        BundleName = value;
    }
    public String getPO(){
        return PO;
    }
    public void setPO(String value){
        PO = value;
    }
    public String getFreq(){
        return Freq;
    }
    public void setFreq(String value){
        Freq = value;
    }
    public String getServiceType(){
        return ServiceType;
    }
    public void setServiceType(String value){
        ServiceType = value;
    }
    public String getPayment(){
        return Payment;
    }
    public void setPayment(String value){
        Payment = value;
    }
    public String getDaySched(){
        return DaySched;
    }
    public void setDaySched(String value){
        DaySched = value;
    }
    public String getCalendarDate(){
        return CalendarDate;
    }
    public void setCalendarDate(String value){
        CalendarDate = value;
    }
    public String getCreationDate(){
        return CalendarDate;
    }
    public void setCreationDate(String value){
        CreationDate = value;
    }
    public String getUpdateDate(){
        return UpdateDate;
    }
    public void setUpdateDate(String value){
        UpdateDate = value;
    }
    public int getuser_id(){
        return user_id;
    }
    public void setuser_id(int value){
        user_id = value;
    }
}
