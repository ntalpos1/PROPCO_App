/*
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JOptionPane;
import javax.swing.JDialog;
import javax.swing.JTextField;
import java.beans.*; //property change stuff
import java.awt.*;
import java.awt.event.*;
 
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dan.Mera
 */
/*
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

*/
import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
	 
public class MyJDialog extends JDialog {
    String CustomerType = "";
    private javax.swing.JRadioButton rbAskNew;
    private javax.swing.JRadioButton rbExisting;
    
    private static final long serialVersionUID = 1L;
    
    public MyJDialog(JFrame parent, String title, String message) {
        super(parent, title);
        System.out.println("creating the window..");
        // set the position of the window
        Point p = new Point(400, 400);
        setLocation(p.x, p.y);
        // Create radio buttons
//        JPanel rbPane = new JPanel();
//        JRadioButton rbAskNew = new javax.swing.JRadioButton();
//        JRadioButton rbExisting = new javax.swing.JRadioButton();
//        ButtonGroup bgCustType = new ButtonGroup( );
//        bgCustType.add(rbAskNew);
//        bgCustType.add(rbExisting);
//        rbPane.add(bgCustType);
        // Create a message
        JPanel messagePane = new JPanel();
        messagePane.add(new JLabel(message));
        // get content pane, which is usually the
        // Container of all the dialog's components.
        getContentPane().add(messagePane);

        // Create a YES and a NO buttons
        JPanel buttonPane = new JPanel();
        JButton buttonYes = new JButton("YES");
        JButton buttonNo = new JButton("NO");
        buttonPane.add(buttonYes);
        buttonPane.add(buttonNo);
        // set action listener on the button
//        buttonYes.addActionListener(new MyActionListener());
//        buttonNo.addActionListener(new MyActionListener());
         buttonYes.addActionListener(new MyActionListener(){
          public void actionPerformed(ActionEvent e) {
            System.out.println("dbla yes..");
            buttonYesActionPerformed(e);
        }     
    });
        buttonNo.addActionListener(new MyActionListener()
        {
          public void actionPerformed(ActionEvent e) {
            System.out.println("dbla no..");
            buttonNoActionPerformed(e);
        }     
    });
        getContentPane().add(buttonPane, BorderLayout.PAGE_END);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
    }


    // override the createRootPane inherited by the JDialog, to create the rootPane.
    // create functionality to close the window when "Escape" button is pressed
    public JRootPane createRootPane() {
        JRootPane rootPane = new JRootPane();
        KeyStroke stroke = KeyStroke.getKeyStroke("ESCAPE");
        Action action = new AbstractAction() {

            private static final long serialVersionUID = 1L;

            public void actionPerformed(ActionEvent e) {
                System.out.println("escaping..");
                setVisible(false);
                dispose();
            }
        };
        InputMap inputMap = rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(stroke, "ESCAPE");
        rootPane.getActionMap().put("ESCAPE", action);
        return rootPane;
    }

    
      
    
        // an action listener to be used when an action is performed
    // (e.g. button is pressed)
    class MyActionListener implements ActionListener {
       
        //close and dispose of the window.
        public void buttonYesActionPerformed(ActionEvent e) {                                          
//        ServiceReceipt srFace = new ServiceReceipt();
//        srFace.setSize(1200,900);
//        srFace.setLocationRelativeTo(null);
//        srFace.setResizable(false);
        dispose();
//        srFace.setVisible(true);
        ServiceReceipt.CustomerType="New";
        System.out.println("yes clicked");
          
    }
        public void buttonNoActionPerformed(ActionEvent e) {                                          
//            ServiceReceipt srFace = new ServiceReceipt();
//            srFace.setSize(1200,900);
//            srFace.setLocationRelativeTo(null);
//            srFace.setResizable(false);
            dispose();
//            srFace.setVisible(true);
            ServiceReceipt.CustomerType="Existing";
            System.out.println("no clicked");
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("disposing the window..");
            
        }   
    }        
} 
 
/*
    public static void main(String[] a) {
        MyJDialog dialog = new MyJDialog(new JFrame(), "hello JCGs", "This is a JDialog example");
        // set the size of the window
        dialog.setSize(300, 150);
    }*/

