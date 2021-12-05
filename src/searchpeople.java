import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class searchpeople {
    JFrame search;
    JPanel main;
    DefaultTableModel dtm;
    JTable list;
    JLabel searchtext;
    JComboBox type;
    JTextField input;
    JButton Search;

    public void search(String item, String SQLName, String TYPE, String searchvalve){
        String[] Type = new String[]{"贫困户用户名", "贫困户编号"};
        search = new JFrame("查询");
        main = new JPanel();
        list = new JTable();
        searchtext = new JLabel("搜索");
        type = new JComboBox(Type);
        input = new JTextField(10);
        Search = new JButton("搜索");

        main.add(searchtext);
        main.add(type);
        main.add(input);

        Search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(type.getSelectedItem());
                if (type.getSelectedItem().equals("贫困户用户名")) {
                    search.setVisible(false);
                    new searchpeople().search("*", "people", "poorusername", input.getText());
                    }
                if (type.getSelectedItem().equals("贫困户编号")) {
                    search.setVisible(false);
                    new searchpeople().search("*","people", "id", input.getText());
                    }
                }
        });
        main.add(Search);

        Vector rowData = table.getRows(item, SQLName, TYPE, searchvalve);
        Vector columnNames = table.getHead(item, SQLName, TYPE, searchvalve);
        dtm = new DefaultTableModel(rowData,columnNames) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        list = new JTable(dtm);
        JScrollPane move = new JScrollPane(list);

        search.setVisible(true);
        search.setLocationRelativeTo(null);
        search.setSize(1000,500);
        search.add(main, BorderLayout.NORTH);
        search.add(move, BorderLayout.CENTER);
    }
}
