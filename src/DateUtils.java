/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author dan
 */
public class DateUtils {
    public static final String DATE_FORMAT_NOW = "yyyy-MM-dd"; // HH:mm:ss";
    ResultSet rs = null;
    Integer DupSR = 0;
    String sqlStmt = "";
    Statement stmt ;

    public static String now_date_time() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date my_date = new Date();
        //return sdf.format(cal.getTime());
        return sdf.format(my_date);
        //DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        //Date date = new Date();
        //System.out.println(dateFormat.format(date)); //2016/11/16 12:08:43
    }
    public static String now_date() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
        Date my_date = new Date();
        //return sdf.format(cal.getTime());
        return sdf.format(my_date);
        //DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        //Date date = new Date();
        //System.out.println(dateFormat.format(date)); //2016/11/16 12:08:43
    }
    
    public static String Calendar_date(Date my_date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        return sdf.format(my_date);
    }
    public static Object makeObj(final String item)  {
            return new Object() { public String toString() { return item; } };
    }
    
    public Object avoid_null(Object field_value){
        //String my_string;
        //my_string = (field_value == null) ? "": field_value.toString();
        return (field_value == null) ? "": field_value;//.toString();
        //return my_string;
    }
/*    The code to delete a row from a table using the DefaultTableModel would be:
Action delete = new AbstractAction()
{
    public void actionPerformed(ActionEvent e)
    {
        JTable table = (JTable)e.getSource();
        int modelRow = Integer.valueOf( e.getActionCommand() );
        ((DefaultTableModel)table.getModel()).removeRow(modelRow);
    }
};
 
ButtonColumn buttonColumn = new ButtonColumn(table, delete, 2);
buttonColumn.setMnemonic(KeyEvent.VK_D);
  */  
    
    
}
