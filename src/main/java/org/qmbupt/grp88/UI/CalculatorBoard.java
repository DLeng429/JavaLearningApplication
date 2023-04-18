package org.qmbupt.grp88.UI;

import org.qmbupt.grp88.Controller.Calculation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CalculatorBoard extends JPanel {

    public CalculatorBoard(){
        this.setBackground(Color.white);
        this.setLayout(new GridLayout(2,1));

        JPanel GPA_bar = new JPanel();
        GPA_bar.setLayout(new FlowLayout());
        JTextField GPA_field = new JTextField();
        GPA_field.setPreferredSize(new Dimension(400, 100));
        JButton GPA_button = new JButton("calculate GPA");

        JPanel degree_bar = new JPanel();
        degree_bar.setLayout(new FlowLayout());
        JTextField degree_field = new JTextField();
        degree_field.setPreferredSize(new Dimension(400, 100));
        JButton degree_button = new JButton("calculate degree");

        GPA_bar.add(GPA_field);
        GPA_bar.add(GPA_button);
        degree_bar.add(degree_field);
        degree_bar.add(degree_button);
        this.add(GPA_bar);
        this.add(degree_bar);

        GPA_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try (FileReader fileReader = new FileReader("src/main/resources/mark.txt")) {
                    ArrayList<Integer> marks = new ArrayList<>();
                    ArrayList<Integer> credits = new ArrayList<>();

                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String line;
                    while((line = bufferedReader.readLine()) != null) {
                        String[] parts = line.split(" ");
                        marks.add(Integer.parseInt(parts[4]));
                        credits.add(Integer.parseInt(parts[3]));
                    }

                    GPA_field.setText("Your synthesis GPA is:\t" + String.format("%.2f",
                            Calculation.calculateGPA(credits, marks)) + "/4.0");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        degree_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try (FileReader fileReader = new FileReader("src/main/resources/mark.txt")) {
                    ArrayList<Integer> semesters = new ArrayList<>();
                    ArrayList<Integer> marks = new ArrayList<>();

                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String line;
                    while((line = bufferedReader.readLine()) != null) {
                        String[] parts = line.split(" ");
                        marks.add(Integer.valueOf(parts[4]));
                        if (parts[0].equals("2020-2021-1")) { semesters.add(1); }
                        else if (parts[0].equals("2020-2021-2")) { semesters.add(2); }
                        else if (parts[0].equals("2021-2022-1")) { semesters.add(3); }
                        else if (parts[0].equals("2021-2022-2")) { semesters.add(4); }
                        else if (parts[0].equals("2022-2023-1")) { semesters.add(5); }
                        else if (parts[0].equals("2022-2023-2")) { semesters.add(6); }
                        else if (parts[0].equals("2023-2024-1")) { semesters.add(7); }
                        else if (parts[0].equals("2023-2024-2")) { semesters.add(8); }
                    }

                    degree_field.setText("Your QMUL academic degree of honor currently is:\t"
                            + Calculation.calculateDegree(semesters, marks));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}
