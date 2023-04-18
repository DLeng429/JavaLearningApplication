package org.qmbupt.grp88.UI;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class ContentBoard extends JPanel {
    public ScheduleBoard scheduleBoard = new ScheduleBoard();;
    public GradeBoard gradeBoard = new GradeBoard();
    public CalculatorBoard calculatorBoard = new CalculatorBoard();
    public PathBoard pathBoard = new PathBoard();
    public AchievementBoard achievementBoard = new AchievementBoard();

    public ContentBoard(CardLayout cards){
        this.setLayout(cards);
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.add(scheduleBoard,"scheduleBoard");
        this.add(gradeBoard,"gradeBoard",1);
        this.add(pathBoard,"pathBoard");
        this.add(calculatorBoard,"calculatorBoard");
        this.add(achievementBoard,"achievementBoard");

    }
}
