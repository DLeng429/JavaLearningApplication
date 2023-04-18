package org.qmbupt.grp88.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChooseBoard extends JPanel {
    public ChooseBoard(CardLayout cards,ContentBoard contentBoard){
        // set sidebar which have 5 buttons to choose the functionality
        this.setLayout(new GridLayout(5,1,0,0));
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.setBackground(Color.white);
        JButton schedule_button = new JButton("Schedule");
        JButton mark_button = new JButton("Marks");
        JButton calculator_button = new JButton("Calculator");
        JButton diagram_button = new JButton("Path Diagram");
        JButton achievement_button = new JButton("Achievement");
        // set sidebar button color
        schedule_button.setBackground(Color.white);
        mark_button.setBackground(Color.white);
        calculator_button.setBackground(Color.white);
        diagram_button.setBackground(Color.white);
        achievement_button.setBackground(Color.white);
        // set sidebar font
        schedule_button.setFont(UIStyle.sidebar_font);
        mark_button.setFont(UIStyle.sidebar_font);
        calculator_button.setFont(UIStyle.sidebar_font);
        diagram_button.setFont(UIStyle.sidebar_font);
        achievement_button.setFont(UIStyle.sidebar_font);
        // set sidebar border
        schedule_button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mark_button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        calculator_button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        diagram_button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        achievement_button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        // add buttons into sidebar panel
        this.add(schedule_button);
        this.add(mark_button);
        this.add(calculator_button);
        this.add(diagram_button);
        this.add(achievement_button);
        // add the listener
        schedule_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cards.show(contentBoard,"scheduleBoard");
            }
        });
        mark_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cards.show(contentBoard,"gradeBoard");
                GradeBoard gb = (GradeBoard) contentBoard.getComponent(1);
                gb.updateCourseList();
            }
        });
        calculator_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cards.show(contentBoard,"calculatorBoard");
            }
        });
        diagram_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cards.show(contentBoard,"pathBoard");
            }
        });
        achievement_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cards.show(contentBoard,"achievementBoard");
            }
        });
    }
}
