import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class register {
    JFrame register;
    JPanel main;
    JPanel main2;
    JLabel username;
    JLabel password;
    JTextField Username;
    JPasswordField Password;
    JButton reg;

    public void init(){
        register = new JFrame("注册");
        main = new JPanel();
        username = new JLabel("用户名");
        password = new JLabel("密码");
        Username = new JTextField(10);
        Password = new JPasswordField(10);
        main2 = new JPanel();
        reg = new JButton("注册");

        reg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection conn = new SQLConnection().connection();
                    PreparedStatement ps = conn.prepareStatement("INSERT INTO users (username, password, type) VALUES (?,?,?)");
                    ps.setObject(1, Username.getText());
                    ps.setObject(2, MD5Util.getMD5Str(String.valueOf(Password.getPassword())));
                    ps.setObject(3, "3");
                    int n = ps.executeUpdate();
                    JOptionPane.showMessageDialog(null,"注册成功","成功",JOptionPane.INFORMATION_MESSAGE);
                    register.setVisible(false);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        main.add(username);
        main.add(Username);
        main.add(password);
        main.add(Password);
        main2.add(reg);

        register.setVisible(true);
        register.setLocationRelativeTo(null);
        register.setSize(400,110);
        register.add(main, BorderLayout.CENTER);
        register.add(main2, BorderLayout.SOUTH);
    }
}
