/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

/**
 *
 * @author 532424
 */
public class ScoreBoard extends javax.swing.JFrame {
    // scores of player 1 and 2
    int scorePlyr1 = 0;
    int scorePlyr2 = 0;
    
    public ScoreBoard() {
        super();
        initComponents();
        setVisible(true); // set visible
        
        // make labels opaque
        plyr1Out.setOpaque(true);
        plyr2Out.setOpaque(true);
    }
    // update scoreboard
    void updateScore() {
        plyr1Out.setText(Integer.toString(scorePlyr1));
        plyr2Out.setText(Integer.toString(scorePlyr2));
    }
    // set score of any player
    public void setScore(int plyr, int score) {
        // set appropriate score
        if (plyr == 1) {
            scorePlyr1 = score;
        } else if (plyr == 2) {
            scorePlyr2 = score;
        }
        updateScore();
    }
    // add score to player
    public void addScore(int plyr, int increment) {
        // set appropriate player's score
        if (plyr == 1) {
            scorePlyr1 += increment;
        } else if (plyr == 2) {
            scorePlyr2 += increment;
        }
        updateScore();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitle = new javax.swing.JLabel();
        plyr1 = new javax.swing.JLabel();
        plyr1Out = new javax.swing.JLabel();
        plyr2 = new javax.swing.JLabel();
        plyr2Out = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Score Board");

        lblTitle.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblTitle.setText("Players' Scores");

        plyr1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        plyr1.setText("Player 1 Score:");

        plyr1Out.setBackground(new java.awt.Color(254, 254, 254));
        plyr1Out.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        plyr1Out.setText("0");
        plyr1Out.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        plyr2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        plyr2.setText("Player 2 Score:");

        plyr2Out.setBackground(new java.awt.Color(254, 254, 254));
        plyr2Out.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        plyr2Out.setText("0");
        plyr2Out.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(plyr2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(plyr2Out, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblTitle)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(plyr1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(plyr1Out, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblTitle)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(plyr1)
                    .addComponent(plyr1Out, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(plyr2)
                    .addComponent(plyr2Out, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel plyr1;
    private javax.swing.JLabel plyr1Out;
    private javax.swing.JLabel plyr2;
    private javax.swing.JLabel plyr2Out;
    // End of variables declaration//GEN-END:variables
}
