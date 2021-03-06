/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.awt.Color;
import javax.swing.JLabel;
import java.util.ArrayList;

/**
 *
 * @author 532424
 */
public class GUI extends javax.swing.JFrame {
    // author is Preet Mistry
    // created on October 24, 2017
    // last update October 29, 2017
    // tictactoe game
    ScoreBoard scoreBoard;// scoreboard window from ScoreBoard class
    // in the same package
    
    // characters for current "X" and "O" players
    String X = "X";
    String O = "O";
    // set X and O characters from outside
    public void setX(String val) {
        String prev = X;// store previous X character
        X = val;
        // update the entire board
        updateBoard(prev, X);
    }
    public void setO(String val) {
        String prev = O; // store previous O character
        O = val;
        // update entire board
        updateBoard(prev, O);
    }
    // update board from previous char to new char
    void updateBoard(String prev, String curr) {
        // loop through entire board to check for previous characters
        for (int i = 0; i < 3; i++) {
            for (int c = 0; c < 3; c++) {
                JLabel box = board[i][c]; // current box being polled
                if (box.getText().equals(prev)) {
                    box.setText(curr); // set to new char if previous char detected
                }
            }
        }
    }    
    
    // array of the entire board
    JLabel [][] board;
    // false if player 2 turn
    boolean plyr1Turn = true;
    
    // false if plyr2 is X and plyr1 is O
    boolean plyr1X = true;
    // return if player 1 is X
    public boolean isPlyr1X() {
        return plyr1X;
    }
    
    boolean gameActive = true; // if game is active    
    
    // will check a row or column
    // the row or column to be checked will have negative one as the value
    boolean checkLine (String u, int pos[]) {
        JLabel [] checked = new JLabel[3];
         // array of all the boxes checked
        boolean isSame = false;
        if (pos[0] == -1) { // check the column if row is negative one
            int column = pos[1];
            for (int i = 0; i < 3; i++) {
                checked[i] = board[i][column];
                // check the element of the TicTacToe
                if (checked[i].getText().equals(u)) {
                    isSame = true;
                } else {
                    // break out if any element isn't same as the string entered
                    isSame = false;
                    break;
                }
            }
            
        } else { // check the row if not
            int row = pos[0];
            for (int i = 0; i < 3; i++) {
                checked[i] = board[row][i];
                // check the element of the TicTacToe
                if (checked[i].getText().equals(u)) {
                    isSame = true;
                } else {
                    // break out if any element isn't same as the string entered
                    isSame = false;
                    break;
                }
            }
        }
        
        
        return isSame;
    }
    // check each diagonal for three in a row
    boolean checkDiagonals(String u) {
        boolean isSame = false; // if any three in a row diagonal exists
        // check top left to bottom right
        for (int i = 0; i < 3; i++) {
            if (board[i][i].getText().equals(u)) {
                isSame = true;
            } else {
                isSame = false;
                break;
            }
        }
        // and check bottom left to top right
        if (!isSame) {
        for (int i = 0; i < 3; i++) {
             if (board[i][2 - i].getText().equals(u)) {
                 isSame = true;
             } else {
                   isSame = false;
                   break;
                }
            }
        }
        
        return isSame;
    }
    // check if a player won (zero if no one won)
    int whoWon() {
        // booleans that store which character won
        boolean xWon = false;
        boolean oWon = false;
        // check rows and columns for X
        for (int i = 0; i < 3; i++) {
            // current position to be checked
            int pos[] = {-1,i};
            xWon |= checkLine(X, pos);
            // change pos for next check 
            pos[0] = i;
            pos[1] = -1;
            xWon |= checkLine(X, pos);
            // check if X has won before continuing
            if (xWon) {
                break;
            }
        }
        // check rows and columns for O
        // (only if X did not win)
        if (!xWon) {
        for (int i = 0; i <3; i++) {
                // current position to be checked
                int pos[] = {-1,i};
                oWon |= checkLine(O, pos);
                // change pos for next check
                pos[0] = i;
                pos[1] = -1;
                oWon |= checkLine(O, pos);
                // check if X has won before continuing
                if (oWon) {
                    break;
                }
            }
        }
        xWon |= checkDiagonals(X); // check diagonals for X
        oWon |= checkDiagonals(O); // check diagonals for O
        // assign proper player to each letter
        int x;
        int y;
        if (plyr1X) {
            x = 1;
            y = 2;
        } else {
            x = 2;
            y = 1;
        }
        // check who won
        if (xWon) {
            // return player that is x
            return x;
        } else if (oWon) {
            // return player that is y
            return y;
        } else {
            // no one won yet, check if all labels are full
            int cells = 0;
            for (int i = 0; i < 3; i++) {
                for (int c = 0; c <3; c++) {
                    // check if not empty
                    if (!board[i][c].getText().equals("")) {
                        cells++;
                    }
                }
            }
            // if its full, return 4
            if (cells == 9) {
                return 4;
            }
            return 0;
        }
    }    
    
    //State [][] states = new State[3][3];
    /**
     * Creates new form GUI
     */
    public GUI() {
        initComponents();
        // add all the JLabel boxes to the board array
        board = new JLabel[3][3];
        board[0][0] = box1;
        board[0][1] = box2;
        board[0][2] = box3;
        board[1][0] = box4;
        board[1][1] = box5;
        board[1][2] = box6;
        board[2][0] = box7;
        board[2][1] = box8;
        board[2][2] = box9;
        
        scoreBoard = new ScoreBoard(); // make a scoreboard
        
        // randomize player turn
        int rand = (int) Math.floor(Math.random() * 2);
        if (rand == 1) {
            plyr1Turn = true;
        } else {
            plyr1Turn = false;
        }
        updatePlyrLab();
        
        opaque();// set all labels opaque
        
        
        getContentPane().setBackground(Color.cyan);
        // offset the frame
        setLocation(250,250);
    }
    // set opaque from outside
    public void opaque() {
        // set all labels opaque
        for (int i = 0; i < 3; i++) {
            for (int c = 0; c< 3; c++) {
                board[i][c].setOpaque(true);
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        box1 = new javax.swing.JLabel();
        box2 = new javax.swing.JLabel();
        box4 = new javax.swing.JLabel();
        box3 = new javax.swing.JLabel();
        box5 = new javax.swing.JLabel();
        box6 = new javax.swing.JLabel();
        box7 = new javax.swing.JLabel();
        box9 = new javax.swing.JLabel();
        box8 = new javax.swing.JLabel();
        lblPlyr = new javax.swing.JLabel();
        lblPlyrOut = new javax.swing.JLabel();
        lblOut = new javax.swing.JLabel();
        btnNewRound = new javax.swing.JButton();
        chkPlyr2AI = new javax.swing.JCheckBox();
        mnu = new javax.swing.JMenuBar();
        mnuFile = new javax.swing.JMenu();
        mnuFileClose = new javax.swing.JMenuItem();
        mnuGame = new javax.swing.JMenu();
        mnuGameSwitchChar = new javax.swing.JMenuItem();
        mnuGameSetChar = new javax.swing.JMenuItem();
        mnuGameNewRound = new javax.swing.JMenuItem();
        mnuGameSwitch4x4 = new javax.swing.JMenuItem();
        mnuGameReset = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Tic Tac Toe");
        setBackground(new java.awt.Color(242, 240, 240));

        box1.setBackground(new java.awt.Color(254, 254, 254));
        box1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        box1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        box1.setToolTipText("");
        box1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        box1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                box1MouseClicked(evt);
            }
        });

        box2.setBackground(new java.awt.Color(254, 254, 254));
        box2.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        box2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        box2.setToolTipText("");
        box2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        box2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                box2MouseClicked(evt);
            }
        });

        box4.setBackground(new java.awt.Color(254, 254, 254));
        box4.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        box4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        box4.setToolTipText("");
        box4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        box4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                box4MouseClicked(evt);
            }
        });

        box3.setBackground(new java.awt.Color(254, 254, 254));
        box3.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        box3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        box3.setToolTipText("");
        box3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        box3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                box3MouseClicked(evt);
            }
        });

        box5.setBackground(new java.awt.Color(254, 254, 254));
        box5.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        box5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        box5.setToolTipText("");
        box5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        box5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                box5MouseClicked(evt);
            }
        });

        box6.setBackground(new java.awt.Color(254, 254, 254));
        box6.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        box6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        box6.setToolTipText("");
        box6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        box6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                box6MouseClicked(evt);
            }
        });

        box7.setBackground(new java.awt.Color(254, 254, 254));
        box7.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        box7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        box7.setToolTipText("");
        box7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        box7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                box7MouseClicked(evt);
            }
        });

        box9.setBackground(new java.awt.Color(254, 254, 254));
        box9.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        box9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        box9.setToolTipText("");
        box9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        box9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                box9MouseClicked(evt);
            }
        });

        box8.setBackground(new java.awt.Color(254, 254, 254));
        box8.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        box8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        box8.setToolTipText("");
        box8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        box8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                box8MouseClicked(evt);
            }
        });

        lblPlyr.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblPlyr.setText("Player Turn:");

        lblPlyrOut.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblPlyrOut.setText("1");

        lblOut.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblOut.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        btnNewRound.setText("New Round");
        btnNewRound.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewRoundActionPerformed(evt);
            }
        });

        chkPlyr2AI.setText("Player 2 is AI");
        chkPlyr2AI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkPlyr2AIActionPerformed(evt);
            }
        });

        mnu.setBackground(new java.awt.Color(1, 1, 1));

        mnuFile.setText("File");

        mnuFileClose.setText("Close");
        mnuFileClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuFileCloseActionPerformed(evt);
            }
        });
        mnuFile.add(mnuFileClose);

        mnu.add(mnuFile);

        mnuGame.setText("Game");

        mnuGameSwitchChar.setText("Switch Players' Characters");
        mnuGameSwitchChar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuGameSwitchCharActionPerformed(evt);
            }
        });
        mnuGame.add(mnuGameSwitchChar);

        mnuGameSetChar.setText("Set Player's Characters");
        mnuGameSetChar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuGameSetCharActionPerformed(evt);
            }
        });
        mnuGame.add(mnuGameSetChar);

        mnuGameNewRound.setText("New Round");
        mnuGameNewRound.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuGameNewRoundActionPerformed(evt);
            }
        });
        mnuGame.add(mnuGameNewRound);

        mnuGameSwitch4x4.setText("Switch to 4x4");
        mnuGameSwitch4x4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuGameSwitch4x4ActionPerformed(evt);
            }
        });
        mnuGame.add(mnuGameSwitch4x4);

        mnuGameReset.setText("Reset Game");
        mnuGameReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuGameResetActionPerformed(evt);
            }
        });
        mnuGame.add(mnuGameReset);

        mnu.add(mnuGame);

        setJMenuBar(mnu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblPlyr)
                .addGap(45, 45, 45))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblOut, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(box4, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(box5, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(box7, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(box8, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(box9, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(box6, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(box1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(box2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(box3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblPlyrOut, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnNewRound, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(chkPlyr2AI, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(37, 37, 37))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(lblPlyr)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(box2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(box1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(box3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(box4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(box6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(box5, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblPlyrOut, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(chkPlyr2AI)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(box7, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(box8, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(box9, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addComponent(btnNewRound)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblOut, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mnuFileCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuFileCloseActionPerformed
        System.exit(0);
    }//GEN-LAST:event_mnuFileCloseActionPerformed

    private void box3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_box3MouseClicked
        boxClick(box3); // handle box click with this method
    }//GEN-LAST:event_box3MouseClicked
    // ai method
    void runAI() {
        ArrayList<JLabel> blankLabs = new ArrayList(); // list of empty labels
        // find all the elements in the array that are empty
        for (int i = 0; i < 3; i++) {
            for (int c = 0; c < 3; c++) {
                JLabel box = board[i][c];// current box being checked
                if (box.getText().equals("")) {
                    blankLabs.add(box);// add to the arraylist if its blank
                }
            }
        }
        // only run if there is any elements in array
        if (blankLabs.size() > 0) {
            // select an element at random by generating random number
            int rand = (int) Math.floor(Math.random() * blankLabs.size());
            // run boxClick with this element selected at random
            boxClick(blankLabs.get(rand));
        }
    }
    // function that handles when a box is clicked on
    void boxClick(JLabel box) {
        if (gameActive) {
            // check if not occupied before making modifications
            if (box.getText().equals("")) {
                // use this simple algorithm to decide whether to make it
                // X or O
                if (plyr1Turn == plyr1X) {
                    box.setText(X);
                } else {
                    box.setText(O);
                }
                // toggle the turn (because turn must change when each player goes)
                plyr1Turn = !plyr1Turn;
                updatePlyrLab();
            }  
            // check who won
            int player = whoWon();
            if (player != 0) {
                // if whoWon() returned a player (aka not zero)
                // then the game has ended
                gameActive = false;
            }
            if (player == 1) {
                lblOut.setText("Player 1 won!");
                scoreBoard.addScore(1, 1);// increment the scoreboard
            } else if (player == 2) {
                lblOut.setText("Player 2 won!");
                scoreBoard.addScore(2, 1);// increment the scoreboard
            } else if (player == 4) {
                lblOut.setText("All full, no winner!");
            }
            // run ai if it's player 2's turn and ai is selected to run
            if (!plyr1Turn && chkPlyr2AI.isSelected()) {
                runAI();
            }
            
        }
        
        
    }
    // update player label
    void updatePlyrLab() {
        // check whose turn it is
        if (plyr1Turn) {
            lblPlyrOut.setText("1");
        } else {
            lblPlyrOut.setText("2");
        }
    }
    
    private void box1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_box1MouseClicked
        boxClick(box1); // handle box click with this method
    }//GEN-LAST:event_box1MouseClicked

    private void box2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_box2MouseClicked
        boxClick(box2); // handle box click with this method
    }//GEN-LAST:event_box2MouseClicked

    private void box4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_box4MouseClicked
        boxClick(box4); // handle box click with this method
    }//GEN-LAST:event_box4MouseClicked

    private void box5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_box5MouseClicked
        boxClick(box5); // handle box click with this method
    }//GEN-LAST:event_box5MouseClicked

    private void box6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_box6MouseClicked
        boxClick(box6); // handle box click with this method
    }//GEN-LAST:event_box6MouseClicked

    private void box7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_box7MouseClicked
        boxClick(box7); // handle box click with this method
    }//GEN-LAST:event_box7MouseClicked

    private void box8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_box8MouseClicked
        boxClick(box8); // handle box click with this method
    }//GEN-LAST:event_box8MouseClicked

    private void box9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_box9MouseClicked
        boxClick(box9); // handle box click with this method
    }//GEN-LAST:event_box9MouseClicked

    private void btnNewRoundActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewRoundActionPerformed
        reset();
        // run ai if ai is set to be true
        // and its the ai's turn (player 2 is ai if its on)
        if (!plyr1Turn && chkPlyr2AI.isSelected()) {
            runAI();
        }
    }//GEN-LAST:event_btnNewRoundActionPerformed

    private void mnuGameNewRoundActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuGameNewRoundActionPerformed
        reset();
        // run ai if ai is set to be true
        // and its the ai's turn (player 2 is ai if its on)
        if (!plyr1Turn && chkPlyr2AI.isSelected()) {
            runAI();
        }
    }//GEN-LAST:event_mnuGameNewRoundActionPerformed

    private void mnuGameResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuGameResetActionPerformed
        // this will both reset the current round and the score board
        reset();
        // reset character for each player
        plyr1X = true;
        // randomize player turn
        int rand = (int) Math.floor(Math.random() * 2);
        if (rand == 1) {
            plyr1Turn = true;
        } else {
            plyr1Turn = false;
        }
        updatePlyrLab();
        // reset scoreboard
        scoreBoard.setScore(1, 0);
        scoreBoard.setScore(2, 0);
        // reset ai
        chkPlyr2AI.setSelected(false);
    }//GEN-LAST:event_mnuGameResetActionPerformed

    private void mnuGameSwitchCharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuGameSwitchCharActionPerformed
        // toggle the boolean that switches the characters
        plyr1X = !plyr1X;
        // scan the entire tictactoe board and switch the characters
        for (int i = 0; i < 3; i++) {
            for (int c = 0; c< 3; c++) {
                // current label to be checked
                JLabel label = board[i][c];
                String txt = label.getText(); // text from the label
                // make sure the field is not empty before cehcking
                if (!txt.equals("")) {
                    // check for X
                    if (txt.equals(X)) {
                        label.setText(O); // change to other letter
                    } // check for o if not x
                    else if (txt.equals(O)) {
                        label.setText(X); // change to other letter
                    }
                }
            }
        }
    }//GEN-LAST:event_mnuGameSwitchCharActionPerformed

    private void mnuGameSetCharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuGameSetCharActionPerformed
        // open a PromptforChar window to handle char setting
        new PromptforChar(this);
    }//GEN-LAST:event_mnuGameSetCharActionPerformed

    private void mnuGameSwitch4x4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuGameSwitch4x4ActionPerformed
        // make a new 4x4 window
        GUI4x4 gui4x4 = new GUI4x4(this, scoreBoard);
        gui4x4.setVisible(true);
        // set this window to be invisible for now
        setVisible(false);        
        // reset this window (newRound)
        reset();
    }//GEN-LAST:event_mnuGameSwitch4x4ActionPerformed

    private void chkPlyr2AIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkPlyr2AIActionPerformed
        // run ai if ai is set to be true
        // and its the ai's turn (player 2 is ai if its on)
        if (!plyr1Turn && chkPlyr2AI.isSelected()) {
            runAI();
        }
    }//GEN-LAST:event_chkPlyr2AIActionPerformed
    
    // reset method
    void reset() {
        // reset everything
        plyr1X = true;
        gameActive = true;
        // player turn wont be reset so the one who lost will
        // be playing
        // clear the board
        for (int i = 0; i < 3; i++) {
            for (int c = 0; c < 3; c++) {
                board[i][c].setText("");
                board[i][c].setOpaque(false);
            }
        }
        // set msg out back to nothing
        lblOut.setText("");
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel box1;
    private javax.swing.JLabel box2;
    private javax.swing.JLabel box3;
    private javax.swing.JLabel box4;
    private javax.swing.JLabel box5;
    private javax.swing.JLabel box6;
    private javax.swing.JLabel box7;
    private javax.swing.JLabel box8;
    private javax.swing.JLabel box9;
    private javax.swing.JButton btnNewRound;
    private javax.swing.JCheckBox chkPlyr2AI;
    private javax.swing.JLabel lblOut;
    private javax.swing.JLabel lblPlyr;
    private javax.swing.JLabel lblPlyrOut;
    private javax.swing.JMenuBar mnu;
    private javax.swing.JMenu mnuFile;
    private javax.swing.JMenuItem mnuFileClose;
    private javax.swing.JMenu mnuGame;
    private javax.swing.JMenuItem mnuGameNewRound;
    private javax.swing.JMenuItem mnuGameReset;
    private javax.swing.JMenuItem mnuGameSetChar;
    private javax.swing.JMenuItem mnuGameSwitch4x4;
    private javax.swing.JMenuItem mnuGameSwitchChar;
    // End of variables declaration//GEN-END:variables
}
