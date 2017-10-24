/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package topicselector;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

/**
 *
 * @author Rustem
 */
public class MainFrame extends javax.swing.JFrame {

    /**
     * Creates new form MainFrame
     */
    public MainFrame(String title) {
        super(title);
        initComponents();
        setResizable(false);
        //Set the window's coordinates to the screan's coordinates
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        toButton = new javax.swing.JButton();
        toLabel = new javax.swing.JTextField();
        fromButton = new javax.swing.JButton();
        fromLabel = new javax.swing.JTextField();
        selectButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        toButton.setText("Upload to");
        toButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toButtonActionPerformed(evt);
            }
        });

        fromButton.setText("Select names & topics");
        fromButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fromButtonActionPerformed(evt);
            }
        });

        fromLabel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fromLabelActionPerformed(evt);
            }
        });

        selectButton.setText("Select Topics Randomly");
        selectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(fromButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(toButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(fromLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE)
                            .addComponent(toLabel)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(108, 108, 108)
                        .addComponent(selectButton, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fromButton, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fromLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(toButton, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(toLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(selectButton, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fromLabelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fromLabelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fromLabelActionPerformed

    private void selectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectButtonActionPerformed
        try {
            MainWorker worker = new MainWorker(this.fromLabel.getText(), this.toLabel.getText());
        } catch (IOException ex) {
            //In a case FileIO is not responding
            showMessage("Something went wrong with FileIO\nPlease contact us", "Error");
            return;
        } catch (InvalidFormatException ex) {
            //In a case the file type is invalid
            showMessage("Only Excel(.xlsx) files are supported", "Error");
            return;
        } catch (NotExcelFileException ex) {
            //In a case the file isn't a Microsoft Excel file (.xlsx)
            showMessage("Only Excel(.xlsx) files are supported", "Error");
            return;
        }
        showMessage("Passwords are generated and uploaded to the file Successfully", "Info");
    }//GEN-LAST:event_selectButtonActionPerformed
    private void showMessage(String txt, String title){
        JOptionPane.showMessageDialog(this, txt, title, JOptionPane.INFORMATION_MESSAGE);
    }
    private void fromButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fromButtonActionPerformed
        JFileChooser fileChooser = new JFileChooser();
          fileChooser.setDialogTitle("Choose a file to read words");
          int status = fileChooser.showOpenDialog(null);
          if(status == JFileChooser.APPROVE_OPTION)
          {
              this.fromLabel.setText(fileChooser.getSelectedFile().getAbsolutePath());
          }
    }//GEN-LAST:event_fromButtonActionPerformed

    private void toButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toButtonActionPerformed
        JFileChooser fileChooser = new JFileChooser();
          fileChooser.setDialogTitle("Choose a file to write dictionary");
          int status = fileChooser.showOpenDialog(null);
          if(status == JFileChooser.APPROVE_OPTION)
          {
              this.toLabel.setText(fileChooser.getSelectedFile().getAbsolutePath());
          }
    }//GEN-LAST:event_toButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton fromButton;
    private javax.swing.JTextField fromLabel;
    private javax.swing.JButton selectButton;
    private javax.swing.JButton toButton;
    private javax.swing.JTextField toLabel;
    // End of variables declaration//GEN-END:variables
}