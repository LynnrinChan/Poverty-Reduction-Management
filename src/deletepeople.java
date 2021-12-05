import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;

public class deletepeople {
    JFrame delete;
    JPanel main;
    JLabel text;
    JTextField input;
    JTable list;
    DefaultTableModel dtm;
    JButton Del;

    public void delete(){
        delete = new JFrame("删除");
        main = new JPanel();
        text = new JLabel("请输入要删除掉贫困户编号");
        input = new JTextField(20);
        Del = new JButton("删除");

        Vector rowData = table.getRows("*", "people", "all", null);
        Vector columnNames = table.getHead("*", "people", "all", null);
        dtm = new DefaultTableModel(rowData,columnNames) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        list = new JTable(dtm);
        JScrollPane move = new JScrollPane(list);

        main.add(text);
        main.add(input);

        Del.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int select = JOptionPane.showConfirmDialog(null,"您确定要删除 "+ input.getText() +" 的信息吗", "确定吗？", JOptionPane.YES_NO_OPTION);
                if (select==0) {
                    try {
                        Connection conn = new SQLConnection().connection();
                        PreparedStatement ps = conn.prepareStatement("DELETE FROM people WHERE id=?");
                        ps.setObject(1, input.getText());
                        int n = ps.executeUpdate();
                        JOptionPane.showMessageDialog(null,"删除成功","成功",JOptionPane.INFORMATION_MESSAGE);
                        delete.setVisible(false);
                        new deletepeople().delete();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        main.add(Del);
        delete.add(main, BorderLayout.NORTH);
        delete.add(move, BorderLayout.CENTER);
        delete.setVisible(true);
        delete.setSize(1000,500);
        delete.setLocationRelativeTo(null);

    }
}
