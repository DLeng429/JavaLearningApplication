package org.qmbupt.grp88.UI;

import javax.swing.*;
import java.awt.*;

public class MainBoard extends JFrame {
    public CardLayout cards = new CardLayout();
    public ContentBoard contentBoard = new ContentBoard(cards);
    public ChooseBoard sidebar = new ChooseBoard(cards,contentBoard);
    public void build(){
        this.setSize(1400,900);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        // set sidebar
        this.getContentPane().add(sidebar, BorderLayout.WEST);
        // set content
        this.getContentPane().add(contentBoard,BorderLayout.CENTER);


    }
}
