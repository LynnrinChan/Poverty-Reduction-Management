import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.*;

public class login {
    JFrame login;
    JPanel banner;
    JPanel logintext;
    JPanel buttons;
    JLabel username;
    JLabel USERNAMEICON;
    JLabel password;
    JLabel PASSWORDICON;
    JLabel BANNERLOGO;
    JTextField USERNAME;
    JPasswordField PASSWORD;
    JButton loginbt;
    JButton register;
    JButton reset;
    JButton exit;
    BufferedImage bannerlogo;
    BufferedImage usernameicon;
    BufferedImage passwordicon;

    public String Username = "";
    public String Password = "";

    public void logincheck() throws SQLException {
        String sqlusername = "null";
        String sqlpassword = "null";

        int sqltype = 0;
        try (Connection conn = new SQLConnection().connection()) {
            try (Statement stmt = conn.createStatement()) {
                try (ResultSet rs = stmt.executeQuery("SELECT username,password,type FROM users WHERE username='"+Username+"'")) {
                    while (rs.next()) {
                        sqlusername = rs.getString(1);
                        sqlpassword = rs.getString(2);
                        sqltype = rs.getInt(3);
                    }
                }
            }
        }
        if (Password.equals(sqlpassword)){
            login.setVisible(false);
            new mainmenu().menu(Username,sqltype);
        }
        else {
            JOptionPane.showMessageDialog(null,"密码错误！","错误",JOptionPane.ERROR_MESSAGE);
        }
    }

    public void init(){
        login = new JFrame("扶贫管理系统-登录界面");
        banner = new JPanel();
        logintext = new JPanel();
        buttons = new JPanel();
        username = new JLabel("用户名: ");
        password = new JLabel("密码: ");
        USERNAME = new JTextField();
        PASSWORD = new JPasswordField();
        loginbt = new JButton("登录");
        register = new JButton("注册");
        reset = new JButton("重置");
        exit = new JButton("退出");

        // 为用户名密码输入添加监听
        USERNAME.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                Username = USERNAME.getText();
            }

            @Override
            public void keyPressed(KeyEvent e) {
                Username = USERNAME.getText();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                Username = USERNAME.getText();
            }
        });

        PASSWORD.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                Password = MD5Util.getMD5Str(String.valueOf(PASSWORD.getPassword()));
            }

            @Override
            public void keyPressed(KeyEvent e) {
                Password = MD5Util.getMD5Str(String.valueOf(PASSWORD.getPassword()));
            }

            @Override
            public void keyReleased(KeyEvent e) {
                Password = MD5Util.getMD5Str(String.valueOf(PASSWORD.getPassword()));
            }
        });

        // 设置登录按钮操作
        loginbt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    logincheck();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        // 设置注册按钮操作
        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new register().init();
            }
        });

        // 设置重置按钮操作
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login.setVisible(false);
                new login().init();
            }
        });

        // 设置退出按钮操作
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
            }
        });

        // 设置 Banner Logo
        try {
            bannerlogo = ImageIO.read(new File("bannerlogo.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        BANNERLOGO = new JLabel(new ImageIcon(bannerlogo));

        // 设置用户名 icon
        try {
            usernameicon = ImageIO.read(new File("username.jpeg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        USERNAMEICON = new JLabel(new ImageIcon(usernameicon));

        // 设置密码 icon
        try {
            passwordicon = ImageIO.read(new File("password.jpeg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        PASSWORDICON = new JLabel(new ImageIcon(passwordicon));

        USERNAME.setColumns(10);
        PASSWORD.setColumns(10);

        banner.add(BANNERLOGO);

        logintext.add(USERNAMEICON);
        logintext.add(username);
        logintext.add(USERNAME);
        logintext.add(PASSWORDICON);
        logintext.add(password);
        logintext.add(PASSWORD);

        buttons.add(loginbt);
        buttons.add(register);
        buttons.add(reset);
        buttons.add(exit);

        login.setVisible(true);
        login.setSize(500,220);
        login.setResizable(false);
        login.setLocationRelativeTo(null);
        login.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        login.add(banner, BorderLayout.NORTH);
        login.add(logintext, BorderLayout.CENTER);
        login.add(buttons, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        new login().init();
    }
}
