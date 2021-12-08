import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;

public class projectmanager {
    JFrame project;
    JPanel main;
    DefaultTableModel dtm;
    JTable list;
    JButton add;
    JButton remove;
    String item = "*";
    String SQLName = "project";
    String TYPE = "all";

    public void addproject(){
        JFrame jf = new JFrame("添加贫困项目");
        JPanel jp = new JPanel();
        JLabel proid = new JLabel("帮扶项目编号");
        JTextField Proid = new JTextField(10);
        JLabel proname = new JLabel("帮扶项目名称");
        JTextField Proname = new JTextField(10);
        JButton add = new JButton("添加");

        jp.add(proid);
        jp.add(Proid);
        jp.add(proname);
        jp.add(Proname);

        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection conn = new SQLConnection().connection();
                    PreparedStatement ps = conn.prepareStatement("INSERT INTO project (projectid, projectname) VALUES (?,?)");
                    ps.setObject(1, Proid.getText());
                    ps.setObject(2, Proname.getText());
                    int n = ps.executeUpdate();
                    JOptionPane.showMessageDialog(null,"注册成功","成功",JOptionPane.INFORMATION_MESSAGE);
                    jf.setVisible(false);
                    project.setVisible(false);
                    new projectmanager().init();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        jp.add(add);

        jf.setVisible(true);
        jf.setLocationRelativeTo(null);
        jf.setSize(290,150);
        jf.add(jp);
    }

    public void init(){
        project = new JFrame("扶贫项目管理");
        main = new JPanel();
        add = new JButton("添加");
        remove = new JButton("移除");

        Vector rowData = table.getRows(item, SQLName, TYPE, null);
        Vector columnNames = table.getHead(item, SQLName, TYPE, null);
        dtm = new DefaultTableModel(rowData,columnNames) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        list = new JTable(dtm);
        JScrollPane move = new JScrollPane(list);

        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addproject();
            }
        });
        main.add(add);

        remove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String test = JOptionPane.showInputDialog(null,"请输入要删除的扶贫项目编号","移除扶贫项目",JOptionPane.QUESTION_MESSAGE);
                if (test == null)
                    System.out.println("Do nothing");
                else {
                    try {
                        Connection conn = new SQLConnection().connection();
                        PreparedStatement ps = conn.prepareStatement("DELETE FROM project WHERE projectid=?");
                        ps.setObject(1, test);
                        int n = ps.executeUpdate();
                        JOptionPane.showMessageDialog(null,"删除成功","成功",JOptionPane.INFORMATION_MESSAGE);
                        project.setVisible(false);
                        new projectmanager().init();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        main.add(remove);

        project.setVisible(true);
        project.setLocationRelativeTo(null);
        project.setSize(1000,500);
        project.add(main, BorderLayout.NORTH);
        project.add(move, BorderLayout.CENTER);
    }
}
