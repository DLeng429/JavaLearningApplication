package org.qmbupt.grp88.UI;

import org.qmbupt.grp88.Entity.Course;
import org.qmbupt.grp88.Entity.CourseList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.io.*;
import java.util.ArrayList;

public class ScheduleBoard extends JPanel{
    public JPanel center = new JPanel();
    public JPanel west = new JPanel();
    public JPanel east = new JPanel();
    public JPanel south = new JPanel();
    public JPanel north = new JPanel();
    public CourseList courseList = new CourseList();
    public ScheduleBoard(){
        this.setLayout(new BorderLayout());
        this.add(center,BorderLayout.CENTER);
        this.add(west,BorderLayout.WEST);
        this.add(east,BorderLayout.EAST);
        this.add(south,BorderLayout.SOUTH);
        this.add(north,BorderLayout.NORTH);
        west.setBackground(Color.white);
        east.setBackground(Color.white);
        south.setBackground(Color.white);
        north.setBackground(Color.white);
        center.setLayout(new GridLayout(9,8,0,0));
        center.setBackground(Color.white);
        // add (0,0) button
        JButton first = new JButton();
        first.setEnabled(false);
        center.add(first);
        // set title of schedule
        String date[] = {"Mon","Tue","Wed","Thur","Fri","Set","Sun"};
        for(int i=0;i<7;i++){
            JButton jb = new JButton(date[i]);
            jb.setBackground(Color.white);
            jb.setEnabled(false);
            center.add(jb);
        }
        // create schedule
        String class_line[] = {"8:00-9:35","9:50-11:25","11:30-12:15","13:00-14:35","14:45-16:25","16:35-18:10","18:30-19:15","19:20-20:55"};
        for(int i=0;i<8;i++){
            JButton right_side = new JButton();
            right_side.setEnabled(false);
            right_side.setBackground(Color.white);
            right_side.setText(class_line[i]);
            center.add(right_side);
            for(int j=0;j<7;j++){
                JButton jb = new JButton();
                jb.setBackground(Color.white);
                center.add(jb);
            }
        }

        try {
            this.updateSchedule();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // set dialog
        for(int i=0;i<center.getComponentCount();i++){
            Component component = center.getComponent(i);
            JButton jb =(JButton) component;
            String Class_name = jb.getText();
            for(int j=0;j<courseList.size();j++){
                Course course = (Course)courseList.get(j);
                if(Class_name.equals(course.getCourse_name())){
                    jb.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            new ClassDialog(course);
                        }
                    });
                }
            }
        }

    }

    public void updateSchedule() throws IOException{
        File file = null;
        FileInputStream fi = null;
        BufferedReader bufferedReader = null;
        try {
            file = new File("src/main/resources/Schedule.txt");
            fi = new FileInputStream(file);
            bufferedReader = new BufferedReader(new InputStreamReader(fi));
            String str;
            while ((str = bufferedReader.readLine()) != null){
                int Week = (int)str.charAt(0)-48;
                int Class_No = (int)str.charAt(1)-48;
                String Class_name = str.substring(2);
                Component comp = center.getComponent(8*(Class_No)+Week);
                JButton jb = (JButton)comp;
                jb.setText(Class_name);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (fi != null){
                fi.close();
            }
        }
    }

}
