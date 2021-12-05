import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import static javax.swing.JOptionPane.*;

public class addpeople {
    JFrame addpeople;
    JPanel main;
    JPanel main2;
    JLabel id;
    JLabel address;
    JLabel homepeople;
    JLabel healthstatus;
    JLabel edustatus;
    JLabel whypoor;
    JLabel poormark;
    JLabel adddate;
    JLabel loginid;
    JLabel poorusername;
    JLabel poorpassword;
    JLabel poorprojectid;
    JTextField ID;
    JTextField ADDRESS;
    JTextField HOMEPEOPLE;
    JTextField HEALTHSTATUS;
    JTextField EDUSTATUS;
    JTextField WHYPOOR;
    JComboBox POORMARK;
    JTextField ADDDATE;
    JTextField LOGINID;
    JTextField POORUSERNAME;
    JPasswordField POORPASSWORD;
    JTextField POORPROJECTID;
    JButton add;
    JButton cancel;

    String Poormark = "true";

    public void add(){
        addpeople = new JFrame("添加贫困户");
        main = new JPanel();
        main2 = new JPanel();
        id = new JLabel("贫困户编号 ");
        address = new JLabel("详细地址 ");
        homepeople = new JLabel("家庭人口数 ");
        healthstatus = new JLabel("健康状况 ");
        edustatus = new JLabel("教育程度 ");
        whypoor = new JLabel("致贫因素 ");
        poormark = new JLabel("贫困标记 ");
        adddate = new JLabel("添加日期 ");
        loginid = new JLabel("帮扶人员编号 ");
        poorusername = new JLabel("贫困户账号 ");
        poorpassword = new JLabel("贫困户密码");
        poorprojectid = new JLabel("帮扶项目编号 ");
        ID = new JTextField();
        ADDRESS = new JTextField();
        HOMEPEOPLE = new JTextField();
        HEALTHSTATUS = new JTextField();
        EDUSTATUS = new JTextField();
        WHYPOOR = new JTextField();
        String[] poormarks = new String[] {"true","false"};
        POORMARK = new JComboBox(poormarks);
        Date Date = new Date();
        SimpleDateFormat date = new SimpleDateFormat("YYYY-MM-DD");
        ADDDATE = new JTextField(date.format(Date));
        LOGINID = new JTextField();
        POORUSERNAME = new JTextField();
        POORPASSWORD = new JPasswordField();
        POORPROJECTID = new JTextField();
        add = new JButton("添加");
        cancel = new JButton("取消");

        ID.setColumns(20);
        ADDRESS.setColumns(20);
        HOMEPEOPLE.setColumns(20);
        HEALTHSTATUS.setColumns(20);
        EDUSTATUS.setColumns(20);
        WHYPOOR.setColumns(20);
        POORMARK = new JComboBox(poormarks);
        ADDDATE.setColumns(20);
        LOGINID.setColumns(20);
        POORUSERNAME.setColumns(20);
        POORPASSWORD.setColumns(20);
        POORPROJECTID.setColumns(20);

        main.add(id);
        main.add(ID);
        main.add(address);
        main.add(ADDRESS);
        main.add(homepeople);
        main.add(HOMEPEOPLE);
        main.add(healthstatus);
        main.add(HEALTHSTATUS);
        main.add(edustatus);
        main.add(EDUSTATUS);
        main.add(whypoor);
        main.add(WHYPOOR);
        main.add(adddate);
        main.add(ADDDATE);
        main.add(loginid);
        main.add(LOGINID);
        main.add(poorusername);
        main.add(POORUSERNAME);
        main.add(poorpassword);
        main.add(POORPASSWORD);
        main.add(poorprojectid);
        main.add(POORPROJECTID);
        main.add(poormark);
        POORMARK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Poormark = (String) POORMARK.getSelectedItem();
            }
        });
        main.add(POORMARK);

        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Id = ID.getText();
                String Address = ADDRESS.getText();
                String Homepeople = HOMEPEOPLE.getText();
                String Healthstatus = HEALTHSTATUS.getText();
                String Edustatus = EDUSTATUS.getText();
                String Whypoor = WHYPOOR.getText();
                String Adddate = ADDDATE.getText();
                String Loginid = LOGINID.getText();
                String Poorusername = POORUSERNAME.getText();
                String Poorpassword = String.valueOf(POORPASSWORD.getPassword());
                String Poorprojectid = POORPROJECTID.getText();

                try {
                    Connection conn = new SQLConnection().connection();
                    try {
                        PreparedStatement ps = conn.prepareStatement("INSERT INTO people (id, address, homepeople, healthstatus, edustatus, whypoor, adddate, loginid, poorusername, poorpassword, poorprojectid, poormark) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
                        ps.setObject(1, Id);
                        ps.setObject(2, Address);
                        ps.setObject(3, Homepeople);
                        ps.setObject(4, Healthstatus);
                        ps.setObject(5, Edustatus);
                        ps.setObject(6, Whypoor);
                        ps.setObject(7, Adddate);
                        ps.setObject(8, Loginid);
                        ps.setObject(9, Poorusername);
                        ps.setObject(10, Poorpassword);
                        ps.setObject(11, Poorprojectid);
                        ps.setObject(12, Poormark);
                        int n = ps.executeUpdate();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                JOptionPane.showMessageDialog(null,"添加成功","提示",INFORMATION_MESSAGE);
                addpeople.setVisible(false);
            }
        });
        main2.add(add);
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addpeople.setVisible(false);
            }
        });
        main2.add(cancel);

        addpeople.add(main, BorderLayout.CENTER);
        addpeople.add(main2, BorderLayout.SOUTH);
        addpeople.setVisible(true);
        addpeople.setSize(370,450);
        addpeople.setLocationRelativeTo(null);
        addpeople.setResizable(false);
    }
}
