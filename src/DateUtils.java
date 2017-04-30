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

    public static String now() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
        return sdf.format(cal.getTime());
    }
    public static Object makeObj(final String item)  {
            return new Object() { public String toString() { return item; } };
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
