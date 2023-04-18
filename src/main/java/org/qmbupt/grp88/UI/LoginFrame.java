package org.qmbupt.grp88.UI;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LoginFrame extends JFrame implements ActionListener {

    private JPanel panel;
    private JLabel userLabel, passwordLabel;
    private JTextField userText;
    private JPasswordField passwordText;
    private JButton loginButton, registerButton;

    public LoginFrame() {
        super("登录界面");

        panel = new JPanel();
        panel.setLayout(null);

        userLabel = new JLabel("username:");
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);

        userText = new JTextField(20);
        userText.setBounds(100, 20, 165, 25);
        panel.add(userText);

        passwordLabel = new JLabel("password:");
        passwordLabel.setBounds(10, 50, 80, 25);
        panel.add(passwordLabel);

        passwordText = new JPasswordField(20);
        passwordText.setBounds(100, 50, 165, 25);
        panel.add(passwordText);

        loginButton = new JButton("Login");
        loginButton.setBounds(10, 80, 90, 25);
        loginButton.addActionListener(this);
        panel.add(loginButton);

        registerButton = new JButton("Register");
        registerButton.setBounds(180, 80, 90, 25);
        registerButton.addActionListener(this);
        panel.add(registerButton);

        add(panel);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String username = userText.getText();
            String password = new String(passwordText.getPassword());

            String jsonString = "";

            try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/data.json"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    jsonString += line;
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            JSONArray jsonArray = JSON.parseArray(jsonString);
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String savedUsername = jsonObject.getString("username");
                String savedPassword = jsonObject.getString("password");
                if (username.equals(savedUsername) && password.equals(savedPassword)) {
                    JOptionPane.showMessageDialog(this, "Login successful!");
                    // 关闭登录窗口
                    dispose();
                    //跳转到主界面
                    MainBoard sb = new MainBoard();
                    sb.build();
                    // 关闭登录窗口

                    return;
                }
            }

            JOptionPane.showMessageDialog(this, "Login failed!","False", JOptionPane.ERROR_MESSAGE);
        }
        else if (e.getSource() == registerButton) {
              JOptionPane.showMessageDialog(null, "welcome register！");
              new RegisterGUI();
    }}

}