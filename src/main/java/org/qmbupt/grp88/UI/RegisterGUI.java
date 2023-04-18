package org.qmbupt.grp88.UI;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class RegisterGUI extends JFrame implements ActionListener {
    private JLabel nameLabel, emailLabel, passwordLabel, confirmLabel;
    private JTextField nameField, emailField;
    private JPasswordField passwordField, confirmField;
    private JButton registerButton, clearButton;

    public RegisterGUI() {
        super("Registration Form");

        // set up the GUI components
        nameLabel = new JLabel("Name:");
        nameField = new JTextField(20);
        emailLabel = new JLabel("Email:");
        emailField = new JTextField(20);
        passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(20);
        confirmLabel = new JLabel("Confirm Password:");
        confirmField = new JPasswordField(20);
        registerButton = new JButton("Register");
        clearButton = new JButton("Clear");

        // set up the layout
        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.insets = new Insets(5, 5, 5, 5);
        gc.gridx = 0;
        gc.gridy = 0;
        add(nameLabel, gc);
        gc.gridx = 1;
        add(nameField, gc);
        gc.gridx = 0;
        gc.gridy = 1;
        add(emailLabel, gc);
        gc.gridx = 1;
        add(emailField, gc);
        gc.gridx = 0;
        gc.gridy = 2;
        add(passwordLabel, gc);
        gc.gridx = 1;
        add(passwordField, gc);
        gc.gridx = 0;
        gc.gridy = 3;
        add(confirmLabel, gc);
        gc.gridx = 1;
        add(confirmField, gc);
        gc.gridx = 0;
        gc.gridy = 4;
        add(registerButton, gc);
        gc.gridx = 1;
        add(clearButton, gc);

        // set up action listeners for buttons
        registerButton.addActionListener(this);
        clearButton.addActionListener(this);

        // set up window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setVisible(true);
    }

    // handle button clicks
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == registerButton) {
            String name = nameField.getText();
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());
            String confirm = new String(confirmField.getPassword());

            if (!password.equals(confirm)) {
                JOptionPane.showMessageDialog(this, "Passwords do not match!");
                return;
            }
            //

            try {
                // 读取JSON文件
                BufferedReader reader = new BufferedReader(new FileReader("data.json"));
                StringBuilder jsonString = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    jsonString.append(line);
                }
                reader.close();

                // 解析JSON数组
                JSONArray jsonArray = JSON.parseArray(jsonString.toString());

                // 添加新注册数据
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("username", name);
                jsonObject.put("password", password);
                jsonObject.put("email", email);
                jsonArray.add(jsonObject);

                // 写入JSON文件
                FileWriter writer = new FileWriter("data.json");
                JSON.writeJSONStringTo(jsonArray, writer);
                writer.flush();
                writer.close();

                JOptionPane.showMessageDialog(this, "Registration successful!");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }


            //JOptionPane.showMessageDialog(this, "Registration successful!");
         else if (e.getSource() == clearButton) {
            nameField.setText("");
            emailField.setText("");
            passwordField.setText("");
            confirmField.setText("");
        }
    }

}