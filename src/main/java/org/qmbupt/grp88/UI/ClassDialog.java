package org.qmbupt.grp88.UI;

import org.qmbupt.grp88.Entity.Course;

import javax.swing.*;
import java.awt.*;

public class ClassDialog extends JDialog {
    public ClassDialog(Course course){
        this.setBounds(750,280,500,500);
        this.setLayout(new GridLayout());
        this.setVisible(true);
        JLabel label = new JLabel("Course Details");
        this.add(label,BorderLayout.NORTH);
        JTextArea text = new JTextArea();
        text.setSize(500,100);
        text.setText("Course Number: "+course.getNumber()+"\n"+
                          "Course Name: "+course.getCourse_name()+"\n"+
                          "Course credit: "+course.getCredit()+"\n");
        text.setEnabled(false);
        text.setFont(UIStyle.dialog_font);
        this.add(text,BorderLayout.CENTER);

    }

}
