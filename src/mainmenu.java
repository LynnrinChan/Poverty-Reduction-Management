import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class mainmenu {
    JFrame menu;
    JPanel main;
    JMenuBar menubar;
    JMenu nowuser;
    JMenu functions;
    JMenuItem switchuser;
    JMenuItem exit;
    JMenuItem searchpeople;
    JMenuItem addpeople;
    JMenuItem modifypeople;
    JMenuItem removepeople;
    MenuBar menubarmac;
    JLabel LOGO;

    BufferedImage bannerlogo;

    String TYPE = null;

    public void menu(String username, int type){
        if (type==1)
            TYPE = "超级用户";
        else if (type==2)
            TYPE = "管理员";
        else
            TYPE = "普通用户";

        menu = new JFrame("扶贫管理系统-主界面 权限等级: "+TYPE);
        main = new JPanel();
        menubar = new JMenuBar();
        nowuser = new JMenu("当前用户: "+username);
        functions = new JMenu("功能");
        switchuser = new JMenuItem("切换用户");
        exit = new JMenuItem("退出");
        searchpeople = new JMenuItem("搜索");
        addpeople = new JMenuItem("添加");
        modifypeople = new JMenuItem("修改");
        removepeople = new JMenuItem("删除");
        LOGO = new JLabel();

        // 设置 Logo
        try {
            bannerlogo = ImageIO.read(new File("bannerlogo.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        LOGO = new JLabel(new ImageIcon(bannerlogo));

        // JFrame 配置
        menu.setVisible(true);
        menu.setSize(500,500);
        menu.setLocationRelativeTo(null);
        menu.setResizable(false);
        menu.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        menu.setMenuBar(menubarmac);

        menu.add(menubar, BorderLayout.NORTH);
        menu.add(main, BorderLayout.CENTER);

        // JPanel 配置
        main.add(LOGO);

        // 菜单栏配置 (窗口内)
        menubar.add(nowuser);
        menubar.add(functions);

        switchuser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menu.setVisible(false);
                new login().init();
            }
        });
        nowuser.add(switchuser);

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
            }
        });
        nowuser.add(exit);

        addpeople.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new addpeople().add();
            }
        });

        searchpeople.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new searchpeople().search("*", "people", "all", null);
            }
        });

        modifypeople.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new modifypeople().modify();
            }
        });

        removepeople.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new deletepeople().delete();
            }
        });

        if (TYPE.equals("管理员") || TYPE.equals("超级用户")) {
            functions.add(addpeople);
            functions.add(searchpeople);
            functions.add(modifypeople);
            functions.add(removepeople);
        }
        if (TYPE.equals("普通用户")) {
            functions.add(searchpeople);
        }
    }
}
