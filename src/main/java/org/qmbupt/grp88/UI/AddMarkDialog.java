package org.qmbupt.grp88.UI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

public class AddMarkDialog extends JDialog {
    public AddMarkDialog(DefaultTableModel model){
        JLabel semester_label = new JLabel("Course Semester:");
        JTextField semester_field = new JTextField();
        JLabel number_label = new JLabel("Course Number:");
        JTextField number_field = new JTextField();
        JLabel name_label = new JLabel("Course Name:");
        JTextField name_field = new JTextField();
        JLabel credit_label = new JLabel("Course Credit:");
        JTextField credit_field = new JTextField();
        JLabel grade_label = new JLabel("Grade:");
        JTextField grade_field = new JTextField();
        semester_field.setPreferredSize(new Dimension(200,50));
        number_field.setPreferredSize(new Dimension(200,50));
        name_field.setPreferredSize(new Dimension(200,50));
        credit_field.setPreferredSize(new Dimension(200,50));
        grade_field.setPreferredSize(new Dimension(200,50));
        this.add(semester_label);
        this.add(semester_field);
        this.add(new JLabel("  "));
        this.add(number_label);
        this.add(number_field);
        this.add(new JLabel("     "));
        this.add(name_label);
        this.add(name_field);
        this.add(new JLabel("     "));
        this.add(credit_label);
        this.add(credit_field);
        this.add(new JLabel("                   "));
        this.add(grade_label);
        this.add(grade_field);
        //add confirm button
        JButton confirm = new JButton("CONFIRM");
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] input = new String[5];
                input[0] = semester_field.getText();
                input[1] = number_field.getText();
                input[2] = name_field.getText();
                input[3] = credit_field.getText();
                input[4] = grade_field.getText();
                model.addRow(input);
                // 保存课程信息到文件
                try (FileWriter writer = new FileWriter("src/main/resources/mark.txt", true)) {
                    writer.write(input[0] + " " + input[1] + " " + input[2] + " " + input[3] + " "+input[4]+"\n");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                semester_field.setText("");
                number_field.setText("");
                name_field.setText("");
                credit_field.setText("");
                grade_field.setText("");
            }
        });
        this.add(confirm);
        // set dialog
        this.setBounds(750,280,335,400);
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.setVisible(true);
    }

}
