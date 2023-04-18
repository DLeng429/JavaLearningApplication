package org.qmbupt.grp88.UI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class GradeBoard extends JPanel {
    public DefaultTableModel model;
    public GradeBoard(){
        this.setLayout(new BorderLayout());
        JPanel search_bar = new JPanel();
        search_bar.setLayout(new FlowLayout());
        JTextField search_field = new JTextField();
        search_field.setPreferredSize(new Dimension(100,20));
        JButton search_button = new JButton("search");
        search_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str = search_field.getText();
                search_field.setText("");
            }
        });
        search_bar.add(search_field);
        search_bar.add(search_button);
        this.add(search_bar,BorderLayout.NORTH);
        String col_name[] = {"Semester","Course Number","Course Name","Course Credit","Grade"};
        model = new DefaultTableModel(col_name,0);
        JTable table = new JTable(model);
        JTableHeader head = table.getTableHeader();
        head.setPreferredSize(new Dimension(head.getWidth(), 35));
        head.setFont(new Font("楷体", Font.PLAIN, 18));
        table.setRowHeight(25);
        table.setFont(UIStyle.table_font);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setEnabled(false);
        this.add(scrollPane,BorderLayout.CENTER);
        // create bottom panel
        JPanel bottom_panel = new JPanel();
        JButton add_button = new JButton("ADD");
        JButton delete_button = new JButton("DELETE");
        bottom_panel.add(add_button);
        bottom_panel.add(delete_button);
        // 添加按钮点击事件
        add_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {new AddMarkDialog(model);}
        });

        // 删除按钮点击事件
        delete_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rowCount = model.getRowCount();
                if (rowCount > 0) {
                    model.removeRow(rowCount - 1);

                    // 删除文件中最后一行
                    try {
                        RandomAccessFile file = new RandomAccessFile("src/main/resources/mark.txt", "rw");
                        long length = file.length();
                        if (length > 0) {
                            byte b;
                            do {
                                length -= 1;
                                file.seek(length);
                                b = file.readByte();
                            } while (length > 0 && b != 10);
                            if (length > 0) {
                                file.setLength(length);
                            } else {
                                file.setLength(0);
                            }
                        }
                        file.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        this.add(bottom_panel,BorderLayout.SOUTH);
    }

    // 更新课程列表
    public void updateCourseList() {
        // 先清空表格
        model.setRowCount(0);

        // 从文件中读取课程信息
        try (FileReader reader = new FileReader("src/main/resources/mark.txt")) {
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(" ");
                model.addRow(parts);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
