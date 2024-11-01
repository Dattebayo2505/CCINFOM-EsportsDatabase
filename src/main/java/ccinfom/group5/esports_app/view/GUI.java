package ccinfom.group5.esports_app.view;

import javax.swing.*;

import ccinfom.group5.esports_app.utils.GUIUtil;

import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    
    private JPanel mainPanel, bannerPanel, lowerPanel, leftLowerPanel, rightLowerPanel,
            mainViewPanel, makeTransacPanel, genReportsPanel;

    private JLabel titleLbl, menuLbl, descLbl;

    private JButton mainViewBtn, makeTransacBtn, genReportsBtn, exitBtn,
                    mainMenuBtn;


    public GUI()  {
        setTitle("ESports Tracker - Valorant");
        setSize(1280, 720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents();
        addComponents();


    }

    private void initComponents() {

        // MAIN PAGE
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(GUIUtil.cDarkGreen);

        // BANNER IN MAIN PAGE
        titleLbl = new JLabel();
        bannerPanel = new JPanel();

        GUIUtil.textConfig(titleLbl, "ESports Tracker - Valorant", "Arial", Font.BOLD, 40, Color.WHITE);
        bannerPanel.setOpaque(false);

        // LOWER PANEL
        lowerPanel = new JPanel();
        lowerPanel.setBackground(Color.WHITE);


        // LEFT LOWER PANEL
        menuLbl = new JLabel();
        leftLowerPanel = new JPanel();
        GUIUtil.gridBagLayoutConfig(lowerPanel, leftLowerPanel, 0, 0, 0.5, 1.0);

        GUIUtil.textConfig(menuLbl, "MENU", "Arial", Font.BOLD, 20, Color.BLACK);



        // RIGHT LOWER PANEL
        rightLowerPanel = new JPanel();
        GUIUtil.gridBagLayoutConfig(lowerPanel, rightLowerPanel, 1, 0, 0.5, 1.0);


        // MAIN PAGE END



    }

    private void addComponents() {
        bannerPanel.add(titleLbl);
        mainPanel.add(bannerPanel, BorderLayout.NORTH);
        mainPanel.add(lowerPanel, BorderLayout.CENTER);

        lowerPanel.add(leftLowerPanel);
        leftLowerPanel.add(menuLbl);

        lowerPanel.add(rightLowerPanel);

        add(mainPanel);
    }

    public void addMainViewBtnListener(ActionListener listener) {
        mainViewBtn.addActionListener(listener);
        makeTransacBtn.addActionListener(listener);
        genReportsBtn.addActionListener(listener);
        exitBtn.addActionListener(listener);
    }

    public JButton getMainViewBtn() {
        return mainViewBtn;
    }
    public JButton getMakeTransacBtn() {
        return makeTransacBtn;
    }
    public JButton getGenReportsBtn() {
        return genReportsBtn;
    }
    public JButton getExitBtn() {
        return exitBtn;
    }


}
