package org.qmbupt.grp88.UI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.net.URL;
import java.nio.channels.FileChannel;

public class AchievementBoard extends JPanel implements ActionListener {
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton addButton;
    private JButton deleteButton;
    private JButton firstBtn, secBtn, threeBtn;
    private int currentPageNum = 1;
    private int pageSize = 5;

    public AchievementBoard() {
        this.setLayout(new BorderLayout());

        // Set up the table
        tableModel = new DefaultTableModel(new Object[]{"Title", "Time", "Award", "Action"}, 0);
        table = new JTable(tableModel);
        table.setRowHeight(35);
        table.getColumnModel().getColumn(3).setCellRenderer(new TableCellRendererButton());
        table.getColumnModel().getColumn(3).setCellEditor(new TableCellEditorButton());
        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                int rowIndex = table.getSelectedRow();
                int selectedColumn = table.getSelectedColumn();
                if (selectedColumn == 3) {

                    String title = (String) table.getModel().getValueAt(rowIndex, 0);
                    Class<? extends AchievementBoard> aClass = AchievementBoard.this.getClass();
                    String prefix = "/" + title + ".jpeg";
                    URL resource = aClass.getResource(prefix);
                    if (resource == null) {
                        resource = aClass.getResource("/" + title + ".jpg");
                    }
                    String file = resource.getFile();
                    new ImageDialog(AchievementBoard.this, "show image", file).setVisible(true);
                }


            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        // Set up the scroll pane and add it to the frame
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 0, 1200, 800);
        add(scrollPane,BorderLayout.CENTER);

        addButton = new JButton("Add");
        addButton.setPreferredSize(new Dimension(100,50));
        addButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        addButton.addActionListener(this);
        deleteButton = new JButton("Delete");
        deleteButton.setPreferredSize(new Dimension(100,50));
        deleteButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        deleteButton.addActionListener(this);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.setLayout(new FlowLayout());
        add(buttonPanel,BorderLayout.SOUTH);
        // Load the data from file
        loadData();
        setVisible(true);
    }


    private void loadData() {
        // Load the data from file
        try {
            FileReader reader = new FileReader("src/main/resources/achievements.txt");
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            int recordStart = (currentPageNum - 1) * pageSize + 1;
            int lineNum = 1;
            tableModel.getDataVector().clear();
            int index = 0;
            // Object[][] data = new Object[5][4];
            while ((line = bufferedReader.readLine()) != null) {

                String[] parts = line.split(",");
                tableModel.addRow(parts);

                lineNum++;
            }

            bufferedReader.close();
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
        table.updateUI();
    }

    private void saveTableData() {
        // Save the data to file

        String filePath="src/main/resources/achievements.txt";
        try {
            // true表示不覆盖原来的内容，而是加到文件的后面。若要覆盖原来的内容，直接省略这个参数就好
            BufferedWriter out = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(filePath,false)));
            String a="";
            for (int i=0;i<tableModel.getRowCount();i++) {
                String title = (String) tableModel.getValueAt(i, 0);
                String time = (String) tableModel.getValueAt(i, 1);
                String award = (String) tableModel.getValueAt(i, 2);

                if (tableModel.getRowCount() - 1 == i) {
                    a = a + title + "," + time + "," + award;
                } else {
                    a = a + title + "," + time + "," + award + "\r\n";
                }

            }
            out.write(a);
            out.close();
        } catch (IOException ex) {
            System.err.println("Error writing to file: " + ex.getMessage());
        }

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            if (addButton.getText().equals("Add")) {
                tableModel.addRow(new Object[]{"", "", ""});
                addButton.setText("save");
            } else {
                saveTableData();
                addButton.setText("Add");
                // 创建一个JFrame组件为parent组件
                // 创建一个默认打开用户文件夹的问价选择器
                JFileChooser chooser = new JFileChooser();
                int flag = chooser.showOpenDialog(this);
                //若选择了文件，则打印选择了什么文件
                if (flag == JFileChooser.APPROVE_OPTION) {

                    File file = new File(chooser.getSelectedFile().getPath());
                    try {
                        copyFileUsingFileChannels(file, new File("resources\\" + tableModel.getValueAt(tableModel.getRowCount() - 1, 0) + ".jpeg"));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }

        } else if (e.getSource() == deleteButton) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                tableModel.removeRow(selectedRow);
                saveTableData();
            }

        }
    }
    private static void copyFileUsingFileChannels(File source, File dest) throws IOException {
        FileChannel inputChannel = null;
        FileChannel outputChannel = null;
        try {
            inputChannel = new FileInputStream(source).getChannel();
            outputChannel = new FileOutputStream(dest).getChannel();
            outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
        } finally {
            inputChannel.close();
            outputChannel.close();
        }
    }

}