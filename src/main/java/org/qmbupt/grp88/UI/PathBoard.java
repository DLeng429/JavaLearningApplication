package org.qmbupt.grp88.UI;

import javax.swing.*;

public class PathBoard extends JPanel {
    public PathBoard(){
        JLabel course_map=new JLabel(new ImageIcon("src/main/resources/course_map.png"));
        this.add(course_map);
        course_map.setBounds(0, 150, 700, 500);
        this.setVisible(true);
    }
}
