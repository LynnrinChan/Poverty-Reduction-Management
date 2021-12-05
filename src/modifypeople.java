import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;

import static javax.swing.JOptionPane.INFORMATION_MESSAGE;

public class modifypeople {
    JFrame modify;
    JFrame modify2;
    JPanel main;
    JPanel main2;
    JTable Table;
    DefaultTableModel dtm;
    JLabel search;
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
    JTextField SEARCH;
    JButton set;
    JButton Search;
    JButton cancel;

    public void Modify2() {
        modify2 = new JFrame("修改");
        main2 = new JPanel();
        JPanel main3 = new JPanel();
        JButton set = new JButton("修改");
        main2.add(id);
        main2.add(ID);
        main2.add(address);
        main2.add(ADDRESS);
        main2.add(homepeople);
        main2.add(HOMEPEOPLE);
        main2.add(healthstatus);
        main2.add(HEALTHSTATUS);
        main2.add(edustatus);
        main2.add(EDUSTATUS);
        main2.add(whypoor);
        main2.add(WHYPOOR);
        main2.add(adddate);
        main2.add(ADDDATE);
        main2.add(loginid);
        main2.add(LOGINID);
        main2.add(poorusername);
        main2.add(POORUSERNAME);
        main2.add(poorpassword);
        main2.add(POORPASSWORD);
        main2.add(poorprojectid);
        main2.add(POORPROJECTID);
        main2.add(poormark);
        main2.add(POORMARK);

        set.addActionListener(new ActionListener() {
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
                String Poormark = (String) POORMARK.getSelectedItem();

                try {
                    Connection conn = new SQLConnection().connection();
                    try {
                        PreparedStatement ps = conn.prepareStatement("UPDATE people SET id=?, address=?, homepeople=?, healthstatus=?, edustatus=?, whypoor=?, adddate=?, loginid=?, poorusername=?, poorpassword=?, poorprojectid=?, poormark=? WHERE id=?");
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
                        ps.setObject(13, SEARCH.getText());
                        int n = ps.executeUpdate();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                JOptionPane.showMessageDialog(null,"修改成功","提示",INFORMATION_MESSAGE);
                modify2.setVisible(false);
                modify.setVisible(false);
                new modifypeople().modify();
            }
        });
        main3.add(set);

        modify2.add(main2, BorderLayout.CENTER);
        modify2.add(main3, BorderLayout.SOUTH);
        modify2.setVisible(true);
        modify2.setSize(370,450);
        modify2.setLocationRelativeTo(null);
        modify2.setResizable(false);
    }

    public void modify(){
        modify = new JFrame("修改");
        main = new JPanel();
        search = new JLabel("请输入需要修改的贫困户编号");
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
        ID = new JTextField(20);
        ADDRESS = new JTextField(20);
        HOMEPEOPLE = new JTextField(20);
        HEALTHSTATUS = new JTextField(20);
        EDUSTATUS = new JTextField(20);
        WHYPOOR = new JTextField(20);
        String[] poormarks = new String[] {"true","false"};
        POORMARK = new JComboBox(poormarks);
        ADDDATE = new JTextField(20);
        LOGINID = new JTextField(20);
        POORUSERNAME = new JTextField(20);
        POORPASSWORD = new JPasswordField(20);
        POORPROJECTID = new JTextField(20);
        SEARCH = new JTextField(10);
        set = new JButton("修改");
        Search = new JButton("搜索");
        cancel = new JButton("取消");

        Vector rowData = table.getRows("*", "people", "all", null);
        Vector columnNames = table.getHead("*", "people", "all", null);
        dtm = new DefaultTableModel(rowData,columnNames) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        Table = new JTable(dtm);
        JScrollPane move = new JScrollPane(Table);

        main.add(search);
        main.add(SEARCH);

        Search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection conn = new SQLConnection().connection();
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT * FROM people WHERE id="+SEARCH.getText());
                    while (rs.next()) {
                        ID.setText(rs.getString(1));
                        ADDRESS.setText(rs.getString(2));
                        HOMEPEOPLE.setText(rs.getString(3));
                        HEALTHSTATUS.setText(rs.getString(4));
                        EDUSTATUS.setText(rs.getString(5));
                        WHYPOOR.setText(rs.getString(6));
                        ADDDATE.setText(rs.getString(7));
                        LOGINID.setText(rs.getString(8));
                        POORUSERNAME.setText(rs.getString(9));
                        POORPASSWORD.setText(rs.getString(10));
                        POORPROJECTID.setText(rs.getString(11));
                    }
                    Modify2();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            }
        });
        main.add(Search);
        modify.add(main, BorderLayout.NORTH);
        modify.add(move, BorderLayout.CENTER);
        modify.setVisible(true);
        modify.setSize(1000,500);
        modify.setLocationRelativeTo(null);
        modify.setResizable(false);


    }
}
