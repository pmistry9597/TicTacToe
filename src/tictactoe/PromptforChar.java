/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author ubuntu
 */
public class PromptforChar extends javax.swing.JFrame {
    boolean plyr1X;// which player is "X" (false if player 2 is X)
    // main form
    GUI main;
    // main form if 4x4
    GUI4x4 main4x4;
    /**
     * Creates new form PromptforChar
     */
    public PromptforChar(GUI gui) {
        initComponents();
        
        // set main gui
        main = gui;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);// show yourself!
    }
    
    public PromptforChar(GUI4x4 gui) {
        initComponents();
        
        // set main gui
        main4x4 = gui;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);// show yourself!
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblPlyr1 = new javax.swing.JLabel();
        lblPlyr2 = new javax.swing.JLabel();
        txtPlyr1 = new javax.swing.JTextField();
        txtPlyr2 = new javax.swing.JTextField();
        btnSet = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("New Char for Players");

        lblPlyr1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblPlyr1.setText("Player 1 Character:");

        lblPlyr2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblPlyr2.setText("Player 2 Character:");

        btnSet.setText("Set Chars");
        btnSet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSetActionPerformed(evt);
            }
        });

        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblPlyr2)
                            .addComponent(lblPlyr1))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtPlyr1, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                            .addComponent(txtPlyr2)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnCancel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSet)))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPlyr1)
                    .addComponent(txtPlyr1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPlyr2)
                    .addComponent(txtPlyr2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSet)
                    .addComponent(btnCancel))
                .addGap(33, 33, 33))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSetActionPerformed
        String plyr1 = txtPlyr1.getText(); // get text entered for player1 char
        String plyr2 = txtPlyr2.getText(); // get text entered for player2 char
        String output = ""; // output string for alerts
        boolean goAhead = true; // true if criteria is satisfied for entered
        // strings
        
        // check if the entered strings are one character long and nothing else
        int multofLength = plyr1.length() * plyr2.length(); // the result
        // should only be one character long
        if (multofLength != 1) {
            goAhead &= false; // cannot go forward with current entered values
            // set output
            output = "Players' characters can only be one character long!";
        }
        // ensure both strings are not equal
        if (plyr1.equals(plyr2)) {
            // check if output has already been added
            if (!goAhead) {
                // append to the string
                output += "\n\nPlayer's characters have to be different!";
            } else {
                // set new string
                output = "Player's characters have to be different!";
                goAhead &= false;// cannot go forward with current entered values
            }
        }
        
        // ensure criteria has been met before setting chars
        if (goAhead) {
            // check which player is "X" and which is "O"
            // then set appropriate character for each
            if (plyr1X) {
                if (main == null) {
                    main4x4.setX(plyr1);
                    main4x4.setO(plyr2);
                } else {
                    main.setX(plyr1);
                    main.setO(plyr2);
                }
            } else {
                if (main == null) {
                    main4x4.setX(plyr2);
                    main4x4.setO(plyr1);
                } else {
                    main.setX(plyr2);
                    main.setO(plyr1);
                }
            }
            dispose(); // close since no longer needed
        } else {
            // display error box
            JOptionPane.showMessageDialog(null, output);
        }
        // output the errors in the else statement
        
    }//GEN-LAST:event_btnSetActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnSet;
    private javax.swing.JLabel lblPlyr1;
    private javax.swing.JLabel lblPlyr2;
    private javax.swing.JTextField txtPlyr1;
    private javax.swing.JTextField txtPlyr2;
    // End of variables declaration//GEN-END:variables
}
