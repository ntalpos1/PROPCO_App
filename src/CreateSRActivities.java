
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dan
 */
public class CreateSRActivities extends JFrame {


    /**
     * Creates new form CreateSRActivities
     */
    
    ResultSet rs = null;
    Integer DupSR = 0;
    Integer crt_count = 1;
    String sqlStmt = "";
    Statement stmt ;
    String FollowupCnt = "";
    boolean PastDate = false;
    JTable table = new JTable();
    JComboBox cmbPestType = new JComboBox();
    JScrollPane pane = new JScrollPane();
    JPanel pnlTable = new JPanel();
    JPanel pnlMore = new JPanel();
    JPanel pnlActions = new JPanel();
    JPanel pnlActivities = new JPanel();
    JButton btnMore = new JButton("More Rows...");
    JButton btnCancel = new JButton("Cancel");
    JButton btnCompleteReq  = new JButton("Complete Request");    
    private String[] columnNames=new String[3];
    private Object[][] dataValues=new String[3][];
    JTextField textBox=new JTextField();
    myTableModel model;
    
    public CreateSRActivities() {
        
        setTitle("Service Request Activities");
        setSize(800,600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //initComponents();
        System.out.println("===CreateSRActivities===");
        
        pnlTable.setLayout(new BorderLayout());
        
        columnNames = new String[] {"WO#","UNIT/AREA","Notes","PEST TYPE"};
        dataValues=new Object[][] {{"","","","Select PESTS"},{"","","","Select PESTS"},{"","","","Select PESTS"},{"","","","Select PESTS"},{"","","","Select PESTS"},};
        
        myTableModel model=new myTableModel();
        table.setRowHeight(30);
        //table.setShowVerticalLines(true);
        table.setShowGrid(true);
        table.setAutoResizeMode(4);
        
        table.setModel(model);
        TableColumn colWO=table.getColumnModel().getColumn(0);
        TableColumn colPestType=table.getColumnModel().getColumn(3);
        
        colWO.setCellEditor(new DefaultCellEditor(textBox));
        colPestType.setCellEditor(new DefaultCellEditor(cmbPestType));
        table.setCellSelectionEnabled(true);
        
        ListSelectionModel cellSelectionModel = table.getSelectionModel();
        cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                String selectedData = null;
                String collectData = "";
                
                int[] selectedRow = table.getSelectedRows();
                int[] selectedColumns = table.getSelectedColumns();

                for (int i = 0; i < selectedRow.length; i++) {
                    for (int j = 0; j < selectedColumns.length; j++) {
                        //if (j>0){
                        //    selectedData = (String) table.getValueAt(selectedRow[i], selectedColumns[j-1]);
                        //}
                        //else{
                            selectedData = (String) table.getValueAt(selectedRow[i], selectedColumns[j]);
                            if (i>0){
                                if (collectData.isEmpty()){
                                    System.out.println("collectData.isEmpty");
                                    collectData = (String) table.getValueAt(selectedRow[i-1], selectedColumns[j]) + ";";
                                }
                                else{
                                    collectData = collectData + (String) table.getValueAt(selectedRow[i-1], selectedColumns[j]) + ";";
                                }
                            }
                        //}
                    }
                }
                System.out.println("Selected: " + selectedData);
                System.out.println("Collected: " + collectData);
            }

        });
        
        /*
        public void changeSelection(    int row, int column, boolean toggle, boolean extend){
            super.changeSelection(row, column, toggle, extend);
 
            if (editCellAt(row, column)){
                Component editor = getEditorComponent();
                editor.requestFocusInWindow();
            }
        }
        */
        
        pnlTable.add(pane);
        pnlTable.setSize(800,400);
        
        pnlMore.add(btnMore);
        pnlMore.setSize(800,100);
        btnMore.setBounds(350,400,100,30);
        
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        TableColumnAdjuster tca = new TableColumnAdjuster(table);
        tca.adjustColumns();

        textBox.addKeyListener(new KeyAdapter(){
            public void keyTyped(KeyEvent e){
                System.out.println("keyTyped");
                if(!Character.isDigit(e.getKeyChar()) && e.getKeyChar() !=KeyEvent.VK_BACK_SPACE){
                    //if(e.getKeyChar() == KeyEvent.VK_TAB  || e.getKeyChar() == KeyEvent.VK_ENTER ){
                    System.out.println("keyTyped if");
                    //checkduplicate();
                    textBox.setEditable(false);
                    textBox.setBackground(Color.RED);
                    //JOptionPane.showMessageDialog(null,"String Type Entry Not Allowed");
                }
                else{
                    System.out.println("keyTyped else");
                    textBox.setEditable(true);
                }
            }
        });
        
        
        //table.getColumnModel().getColumn(0).setCellRenderer(new ErrorCellRenderer());
        
        table.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseClicked(java.awt.event.MouseEvent e){
                //System.out.println("mouseClicked");
            }
        });
        
        //fill the combobox
        fillcombo();
        
        //table.getColumnModel().getColumn(0).setPreferredWidth(25);
        //table.getColumnModel().getColumn(1).setPreferredWidth(40);
        //table.getColumnModel().getColumn(2).setPreferredWidth(50);
        
        //table.getSelectionModel().addListSelectionListener(new MyListener());
        
        
        pane.setViewportView(table);
        
        //pnlTable.add(pane);
        //pnlTable.setBounds(0,0,400,200);
        pnlActions.setLayout(new BorderLayout());
        pnlActions.add(btnCancel,BorderLayout.WEST);
        btnCancel.setBounds(50,0,100,30);
        btnCompleteReq.setBounds(250,0,100,30);
        pnlActions.add(btnCompleteReq,BorderLayout.EAST);
        //pnlActions.setBounds(200,0,400,200);
        pnlActivities.setLayout(new BorderLayout());
        pnlActivities.add(pnlTable,BorderLayout.NORTH);
        pnlActivities.add(pnlMore,BorderLayout.CENTER);
        pnlActivities.add(pnlActions,BorderLayout.SOUTH);
        
        getContentPane().add(pnlActivities);
                
        setVisible(true);
        setResizable(false);
        
        
        table.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                //System.out.println("mouse event");
            }
        });
        
        btnMore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Vector rowData = null;
                //model.addRow(new Object[][] {{"","","","Choose"},});
                model.addRow(rowData);
                table.validate();
            }
        });
        
        //table.addKeyListener(new KeyListener()); 
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });
        
        btnCompleteReq.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCompleteReqActionPerformed(evt);
            }
        });
        //System.out.println("finished module");          
    }

    public class myTableModel extends DefaultTableModel{
        myTableModel(){
            super(dataValues,columnNames);
        }
        public boolean isCellEditable(int row,int cols){return true;}
    }

    public void fillcombo(){
        //fill the combobox
        
        try{
            sqlStmt = "select * from Pests ";
            //tblName = "Business_Details";
            rs = SQLConnection.getRecordSet(sqlStmt);
            while (rs.next()){
                //get available pests
                cmbPestType.addItem((String) (rs.getString("PestsType")));               
            }
            cmbPestType.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    //JOptionPane.showMessageDialog(null,cmbPestType.getSelectedItem());
                    System.out.println("in the combo " + cmbPestType.getSelectedItem());
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
        }
        catch (SQLException ex) {
            Logger.getLogger(ServiceReceipt.class.getName()).log(Level.SEVERE, null, ex);
            //if (stmt != null) { stmt.close(); }
            System.out.println("error");      
        }
    }
    /*
    private static class MyListener implements ListSelectionListener {

    @Override
    public void valueChanged(ListSelectionEvent e) {
        //TableModel model = CreateSRActivities.table.getModel();
        //System.out.println("value of cell is: " + model.getValueAt(0, 0));
        System.out.println("mylistener " + e.getFirstIndex());
    }
}*/
    
    
    private void btnCompleteReqActionPerformed(java.awt.event.ActionEvent evt) {
        //save all table records in the db
        //create an array in case is needed for followups
        String activities[][] = null;
        
        //if(! Unit.isEmpty())        
        int rows_count = table.getRowCount();
        //activities = new String[rows_count];
        String PestsID = "";
        activities = new String [rows_count][10];
        for(int row = 0; row<rows_count; row++){
            
            for(int j=0;j<6;j++){
                activities[row][j] = ServiceReceipt.ServiceReqNr;
                Integer ActivityNr = row+1;
                activities[row][j] = ActivityNr.toString();
                activities[row][j] = (String)avoid_null(table.getValueAt(row,0));
                activities[row][j] = (String)avoid_null(table.getValueAt(row, 1));
                activities[row][j] = (String)avoid_null(table.getValueAt(row, 2));
                //try{
                //to change to return the value 
                String return_field = "PestsID"; 
                String sqlStmt = "Select " + return_field + "from Pests WHERE PestsType = '" + (String)avoid_null(table.getValueAt(row, 3)) + "'";
                PestsID = SQLConnection.return_value(sqlStmt,return_field);
                //PestsID = rs.getInt("PestsID");
                System.out.println("PestsID=" + PestsID);
                
                activities[row][j] = PestsID.toString();
                activities[row][j] = "I";
                activities[row][j] = DateUtils.now_date_time();
                activities[row][j] = DateUtils.now_date_time();
                activities[row][j] = frmLogin.user_id.toString();        
            }
        }
        String Table_Fields = "(ServiceReqNr,ActivityNr,WONr,Unit,Notes,PestsID,ActivityStatus,Creation_Date,Update_Date,User_id)";
        String stmt_Values = "(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        insert_activities();
        FollowupDlg followup_dlg = new FollowupDlg();
        followup_dlg.init();
        followup_dlg.dispose();
        dispose();
    }
    
    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {
        System.out.println("btnCancelActionPerformed");
        MainMenu regFace = new MainMenu();
	regFace.setSize(700,800);
	regFace.setLocationRelativeTo(null);
	regFace.setResizable(false);
	dispose();
	regFace.setVisible(true);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 408, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 563, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public class FollowupDlg extends JFrame implements ActionListener{
        JLabel lblQuestion = new JLabel("Choose how many follow-ups services to create?");
        JTextField txtFollowupCount = new JTextField("0");
        JComboBox cmbFollowup = new JComboBox();
        JPanel AskPanel = new JPanel();
        JButton BtnCancel = new JButton("Cancel");
        JButton BtnOK = new JButton("OK");
                
        private JDialog Dlg = new JDialog((ServiceReceipt) null,"Input",true);
        
        
        public void init()
        {
            Container DlgCntnr = Dlg.getContentPane();
            cmbFollowup.setMaximumRowCount(10);
            cmbFollowup.setModel(new javax.swing.DefaultComboBoxModel<>(new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 }));
            DlgCntnr.setLayout(new FlowLayout());
            DlgCntnr.add(lblQuestion);
            //DlgCntnr.add(txtFollowupCount);
            DlgCntnr.add(cmbFollowup);
            
            AskPanel.add(BtnCancel);
            AskPanel.add(BtnOK);
            DlgCntnr.add(AskPanel);
            
            BtnCancel.addActionListener(this);
            BtnOK.addActionListener(this);
            //cmbFollowup.addActionListener(this);
            //Dlg.setBounds(220, 160, 220, 170);
            Dlg.setSize(330, 100);
            Dlg.setLocationRelativeTo(null);
            Dlg.show();
        }
        public void dispose()
        {
            Dlg.dispose();
        }
        public void actionPerformed(ActionEvent e1)
            {
            if(e1.getSource() == BtnOK){
                //Dlg.setSize(250, 170);
                //check if value is numeric
                FollowupCnt = cmbFollowup.getSelectedItem().toString() ;
                //FollowupCnt.intValue(txtFollowupCount getText());
                System.out.println("actioned performed followup count = " + FollowupCnt);

                int crt_count=1;
                System.out.println("PastDate outside of loop" + PastDate);
                do
                    //for(int count=1;count<=Integer.parseInt(FollowupCnt);count++){
                    {    
                    FollowupDatesDlg follow_dates = new FollowupDatesDlg();
                    PastDate=false;
                    follow_dates.init();
                    System.out.println("PastDate in loop after init " + PastDate);
                    //check if the date was in the future and reset counter if not
                    if(PastDate==true) {
                        System.out.println("in here??");
                        crt_count=crt_count-1;
                    }
                    crt_count++;
                    follow_dates.dispose();
                    System.out.println("followup counter is: " + crt_count);

                    Dlg.dispose();
                }
                while (crt_count<=Integer.parseInt(FollowupCnt));
            }            
            else if(e1.getSource() == BtnCancel){
                //save the records now in here!!!
                Dlg.dispose();
                MainMenu regFace = new MainMenu();
                regFace.setSize(700,800);
                regFace.setLocationRelativeTo(null);
                regFace.setResizable(false);
                dispose();
                regFace.setVisible(true);
            }
         }
    }
    
    public class FollowupDatesDlg  extends JFrame implements ActionListener{
        //create a list of numerals and get the numeral according to the number in FollowupCnt
        String[] arr = {"first", "second", "third", "fourth","fifth","sixth","seventh","eighth","ninth","tenth"};
        
        JLabel lblQuestion = new JLabel("Please select the date for the " + arr[crt_count] +" follow-up");
        com.toedter.calendar.JCalendar calFollowup = new com.toedter.calendar.JCalendar();
        
        JPanel AskPanel = new JPanel();
        //JButton BtnCancel = new JButton("Cancel");
        JButton BtnOK = new JButton("OK");
                
        private JDialog Dlg = new JDialog((ServiceReceipt) null,"Input",true);
        
        
        public void init()
        {
            Container DlgCntnr = Dlg.getContentPane();
            DlgCntnr.setLayout(new FlowLayout());
            DlgCntnr.add(lblQuestion);
            DlgCntnr.add(calFollowup);
                        
            //AskPanel.add(BtnCancel);
            AskPanel.add(BtnOK);
            DlgCntnr.add(AskPanel);
            
            //BtnCancel.addActionListener(this);
            BtnOK.addActionListener(this);
            Dlg.setSize(410, 300);
            Dlg.setLocationRelativeTo(null);
            Dlg.show();
        }
        public void dispose()
        {
            Dlg.dispose();
        }
        public void actionPerformed(ActionEvent e1)
            {
                if(e1.getSource() == BtnOK){
                    //Dlg.setSize(250, 170);
                    //check if value is numeric
                    //FollowupCnt.intValue(txtFollowupCount getText());
                    System.out.println("followup count 1 = " + FollowupCnt);
                    //calFollowup.setSelectableDateRange(calFollowup.getTodayButtonText() , calFollowup.getMaxSelectableDate() );
                    //System.out.println("date is: " + calFollowup.getCalendar().toString());
                    //date1.before(date2) 
                    Calendar cal = Calendar.getInstance();
                    if (calFollowup.getCalendar().before(Calendar.getInstance())){
                        JOptionPane.showMessageDialog(null,"You cannot select a date in the past","Wrong Date!",JOptionPane.ERROR_MESSAGE);
                        //reset the counter
                        PastDate=true;
                    }
                    else{
                        PastDate=false;
                        System.out.println("date/time selected is: " + calFollowup.getCalendar().getTime().toString());
                        //System.out.println("date/time now is: "  + Calendar.getInstance());
                    }
                    //if(todayDate.after(historyDate) && todayDate.before(futureDate)) {
                        // In between
                    //}
                    System.out.println("date/time is: " + calFollowup.getCalendar().getTime().toString());
                    System.out.println("PastDate in action performed" + PastDate);
                    //save activity in the db
                    Dlg.dispose();
                    }
                /*
                else if(e1.getSource() == BtnCancel){
                        //needs to save and then get you to main
                        Dlg.dispose();
                    } */
         }
    }
    
    public static void main(String args[]){
           CreateSRActivities SRActivities = new CreateSRActivities();
           SRActivities.setVisible(true);
    }
    /*
    public class ErrorCellRenderer extends DefaultTableCellRenderer {

    private static final long serialVersionUID = 1L;

    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
                row, column);

        //System.out.println("getTableCellRendererComponent" + value.toString() + ";");
        if(!value.toString().isEmpty() && checkduplicate(value.toString())){
            //if(value.toString().contains("@") || value.toString().contains("$") || value.toString().contains("#")) {
            component.setBackground(Color.RED);
        } else {
            component.setBackground(Color.WHITE);
        }

        return component;
    }
*/
    public boolean checkduplicate(String cell_value) {
            boolean dup_count = false;
            System.out.println("checkduplicate");
            if (Integer.parseInt(ServiceReceipt.BID) >0){
                String sqlStmt = "Select count(*) as num, a.ServiceReqNr as ServiceReq from Service_Request a, Service_Request_Activities b " 
                               + "where b.WONr = '" + cell_value 
                               + "' and a.ServiceReqNr = b.ServiceReqNr and b.BID = '" + Integer.parseInt(ServiceReceipt.BID) + "'";
                
                try{
                    rs = SQLConnection.getRecordSet(sqlStmt);
                    System.out.println("checkduplicate" + rs.getInt("num"));
                    if (rs.getInt("num") != 0){
                        DupSR = rs.getInt("ServiceReq");
                        WODuplicateDlg WODup = new WODuplicateDlg();
                        WODup.init();
                        WODup.dispose();
                        dup_count = true; 
                    }
                }
                catch (SQLException ex) {
                    Logger.getLogger(ServiceReceipt.class.getName()).log(Level.SEVERE, null, ex);
                    //if (stmt != null) { stmt.close(); }
                    System.out.println("error");      
                }
            }
        return dup_count;
    }
    public class WODuplicateDlg extends JFrame implements ActionListener{
        //JLabel Lbl = new JLabel("Please input the number of followup SR's to create?");
        JLabel lblQuestion = new JLabel("This WO# was used previously in SR# ");
        JTextField txtSRNr = new JTextField("0");
        //JComboBox cmbFollowup = new JComboBox();
        JPanel AskPanel = new JPanel();
        //JButton BtnClear = new JButton("Clear");
        JButton BtnOK = new JButton("OK");
                
        private JDialog Dlg = new JDialog((ServiceReceipt) null,"Input",true);
        
        
        public void init()
        {
            Container DlgCntnr = Dlg.getContentPane();
            //cmbFollowup.setMaximumRowCount(10);
            //cmbFollowup.setModel(new javax.swing.DefaultComboBoxModel<>(new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 }));
            DlgCntnr.setLayout(new FlowLayout());
            DlgCntnr.add(lblQuestion);
            DlgCntnr.add(txtSRNr);
            //DlgCntnr.add(cmbFollowup);
            
            //AskPanel.add(BtnClear);
            AskPanel.add(BtnOK);
            DlgCntnr.add(AskPanel);
            
            //BtnClear.addActionListener(this);
            BtnOK.addActionListener(this);
            //cmbFollowup.addActionListener(this);
            //Dlg.setBounds(220, 160, 220, 170);
            Dlg.setSize(330, 100);
            Dlg.setLocationRelativeTo(null);
            Dlg.show();
        }
        public void dispose()
        {
            Dlg.dispose();
        }
        public void actionPerformed(ActionEvent e1)
            {
                if(e1.getSource() == BtnOK){
                    //Dlg.setSize(250, 170);
                    //check if value is numeric
                    //FollowupCnt = cmbFollowup.getSelectedItem().toString() ;
                    //FollowupCnt.intValue(txtFollowupCount getText());
                    System.out.println("SR# = " + DupSR);
                    Dlg.dispose();
                    }
                //else if(e1.getSource() == BtnClear){
                    //clear the field
                    //ServiceReceiptP2.txtWO1.setText(TOOL_TIP_TEXT_KEY);
                //    Dlg.dispose();
                 //   }
         }
    }
    
    //private Object makeObj(final String item)  {
    //        return new Object() { public String toString() { return item; } };
    //    }
    
    public Object avoid_null(Object field_value){
        //String my_string;
        //my_string = (field_value == null) ? "": field_value.toString();
        return (field_value == null) ? "": field_value;//.toString();
        //return my_string;
    }
    public void insert_activities(){
        try{
            int rows_count = table.getRowCount();
            sqlStmt = "INSERT INTO Service_Request_activity_test "
                                   + "(ServiceReqNr,ActivityNr,WONr,Unit,Notes,PestsID,ActivityStatus,Creation_Date,Update_Date,User_id) "
                                   + "values (?,?,?,?,?,?,?,?,?,?)";
            System.out.println(sqlStmt);
            PreparedStatement stmt = SQLConnection.conn.prepareStatement(sqlStmt);
            
            for(int row = 0; row<rows_count; row++)
            {
                Integer ActivityNr = row+1;
                /*
                String WONr = (String)table.getValueAt(row, 0);
                String Unit = (String)table.getValueAt(row, 1);
                String Notes = (String)table.getValueAt(row, 2);
                String PestType = (String)table.getValueAt(row, 3);
                */
                String WONr = (String)avoid_null(table.getValueAt(row,0));
                String Unit = (String)avoid_null(table.getValueAt(row, 1));
                String Notes = (String)avoid_null(table.getValueAt(row,3));
                String PestType = (String)avoid_null(table.getValueAt(row,4));
                System.out.println("WONr:"+ WONr + ";Unit:" + Unit + ";Notes:" + Notes + ";PestType:" + PestType);
                
                /*        
                if(! Unit.isEmpty()){
                    stmt.setInt(1,Integer.parseInt(ServiceReceipt.ServiceReqNr));
                    stmt.setInt(2,ActivityNr);
                    stmt.setString(3, WONr);
                    stmt.setString(4, Unit);
                    stmt.setString(5, Notes);
                    String sqlStmt1 = "Select PestsID from Pests WHERE PestsType = '" + PestType + "'";
                    rs = SQLConnection.getRecordSet(sqlStmt1);
                    Integer PestsID = rs.getInt("PestsID");
                    System.out.println("PestsID=" + PestsID);
                    stmt.setInt(6, PestsID);
                    stmt.setString(7,"I");//I-Initial,S-Sent,R-Received,C-completed,X-Cancelled
                    stmt.setString(8,DateUtils.now_date_time());
                    stmt.setString(9,DateUtils.now_date_time());
                    stmt.setInt(10,frmLogin.user_id);
                    
                    stmt.addBatch();
                }*/
            }
            //stmt.executeBatch();
            //SQLConnection.conn.commit();
            //Update ActivityCount in ServiceRequest table
            
        }
        catch(Exception e){
            //error here!!!
            //JOptionPane.showMessageDialog(this,e.getMessage());
        }                                                
   }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
