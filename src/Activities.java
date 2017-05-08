/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dan
 */
public class Activities {
    private static int numberOfActivities = 0;
    private int id;
    int ServiceReqNr;
    int ActivityNr;
    String WO;
    String Location;
    String Notes;
    int PestsID;
    String Status;
    String CreationDate;
    String UpdateDate;
    int user_id;
        
    public Activities(){
        
    }
    public Activities(int ActivityNr,String WO,String Location,String Notes,int PestsID,String Status,String CreationDate,String UpdateDate,int user_id){
        //this.ServiceReqNr = ServiceReqNr;
        this.ActivityNr = ActivityNr;
        this.WO = WO;
        this.Location =Location ;
        this.Notes = Notes;
        this.PestsID = PestsID;
        this.Status = Status;
        this.CreationDate = DateUtils.now_date_time();
        this.UpdateDate = DateUtils.now_date_time();
        this.user_id = user_id;
        
        id = ++numberOfActivities;
    }
    
    public void LoadActivity(){
        this.ServiceReqNr = ServiceReqNr;
        this.ActivityNr = ActivityNr;
        this.WO = WO;
        this.Location =Location ;
        this.Notes = Notes;
        this.PestsID = PestsID;
        this.Status = Status;
        this.CreationDate = DateUtils.now_date_time();
        this.UpdateDate = DateUtils.now_date_time();
        this.user_id = user_id;
    }
    
    public static void main(String[] args){
        Activities an_instance = new Activities();
        an_instance.LoadActivity();
    }
    
    
    public int getID() {
        return id;
    }
    public static int numberOfActivities() {
        return numberOfActivities;
    }
    public int getServiceReqNr(){
        return ServiceReqNr;
    }
    public void setServiceReqNr(int value){
        ServiceReqNr = value;
    }
    public int getActivityNr(){
        return ActivityNr;
    }
    public void setActivityNr(int value){
        ActivityNr = value;
    }
    public String getStatus(){
        return Status;
    }
    public void setWO(String value){
        WO = value;
    }
    public String getWO(){
        return WO;
    }
    public void setLocation(String value){
        Location = value;
    }
    public String getLocation(){
        return Location;
    }
    public void setNotes(String value){
        Notes = value;
    }
    public String getNotes(){
        return Notes;
    }
    public void setPestsID(int value){
        PestsID = value;
    }
    public int getPestsID(){
        return PestsID;
    }
    public void setStatus(String value){
        Status = value;
    }
    public String getCreationDate(){
        return CreationDate;
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
