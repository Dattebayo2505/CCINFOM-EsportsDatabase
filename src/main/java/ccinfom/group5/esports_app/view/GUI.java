package ccinfom.group5.esports_app.view;

import javax.swing.*;
import javax.swing.border.Border;

import ccinfom.group5.esports_app.utils.GUIUtil;

import java.awt.*;
import java.awt.event.ActionListener;

public class GUI {
    
    private JFrame mainFrame;

    private JPanel mainPanel, bannerPanel, lowerPanel, leftLowerPanel, rightLowerPanel,
                   mainViewPanel, 
                   makeTransacPanel, 
                   genReportsPanel;

    private JLabel titleLbl, menuLbl, descLbl;

    private JButton mainViewBtn, makeTransacBtn, genReportsBtn, exitBtn,
                    mainMenuBtn;

    private JTextArea sampleTextArea;

    private String sampleText = "Lorem Ipsum dolorum sit amet Lorem Ipsum dolorum sit amet Lorem Ipsum dolorum sit amet. Lorem Ipsum dolorum sit amet Lorem Ipsum dolorum sit amet Lorem Ipsum dolorum sit amet. Lorem Ipsum dolorum sit amet Lorem Ipsum dolorum sit amet Lorem Ipsum dolorum sit amet. Lorem Ipsum dolorum sit amet Lorem Ipsum dolorum sit amet Lorem Ipsum dolorum sit amet. Lorem Ipsum dolorum sit amet Lorem Ipsum dolorum sit amet Lorem Ipsum dolorum sit amet. Lorem Ipsum dolorum sit amet Lorem Ipsum dolorum sit amet Lorem Ipsum dolorum sit amet. Lorem Ipsum dolorum sit amet Lorem Ipsum dolorum sit amet Lorem Ipsum dolorum sit amet. Lorem Ipsum dolorum sit amet Lorem Ipsum dolorum sit amet Lorem Ipsum dolorum sit amet. Lorem Ipsum dolorum sit amet Lorem Ipsum dolorum sit amet Lorem Ipsum dolorum sit amet. Lorem Ipsum dolorum sit amet Lorem Ipsum dolorum sit amet Lorem Ipsum dolorum sit amet. Lorem Ipsum dolorum sit amet Lorem Ipsum dolorum sit amet Lorem Ipsum dolorum sit amet. Lorem Ipsum dolorum sit amet Lorem Ipsum dolorum sit amet Lorem Ipsum dolorum sit amet. Lorem Ipsum dolorum sit amet Lorem Ipsum dolorum sit amet Lorem Ipsum dolorum sit amet. Lorem Ipsum dolorum sit amet Lorem Ipsum dolorum sit amet Lorem Ipsum dolorum sit amet. Lorem Ipsum dolorum sit amet Lorem Ipsum dolorum sit amet Lorem Ipsum dolorum sit amet. Lorem Ipsum dolorum sit amet Lorem Ipsum dolorum sit amet Lorem Ipsum dolorum sit amet. Lorem Ipsum dolorum sit amet Lorem Ipsum dolorum sit amet Lorem Ipsum dolorum sit amet. Lorem Ipsum dolorum sit amet Lorem Ipsum dolorum sit amet Lorem Ipsum dolorum sit amet. Lorem Ipsum dolorum sit amet Lorem Ipsum dolorum sit amet Lorem Ipsum dolorum sit amet. Lorem Ipsum dolorum sit amet Lorem Ipsum dolorum sit amet Lorem Ipsum dolorum sit amet. Lorem Ipsum dolorum sit amet Lorem Ipsum dolorum sit amet Lorem Ipsum dolorum sit amet. Lorem Ipsum dolorum sit amet Lorem Ipsum dolorum sit amet Lorem Ipsum dolorum sit amet. Lorem Ipsum dolorum sit amet Lorem Ipsum dolorum sit amet Lorem Ipsum dolorum sit amet. Lorem Ipsum dolorum sit amet Lorem Ipsum dolorum sit amet Lorem Ipsum dolorum sit amet. Lorem Ipsum dolorum sit amet Lorem Ipsum dolorum sit amet Lorem Ipsum dolorum sit amet. Lorem Ipsum dolorum sit amet Lorem Ipsum dolorum sit amet Lorem Ipsum dolorum sit amet. Lorem Ipsum dolorum sit amet Lorem Ipsum dolorum sit amet Lorem Ipsum dolorum sit amet. " ;

    public GUI() {

        this.mainFrame = new JFrame();

        mainFrame.setTitle("ESports Tracker - Valorant");
        mainFrame.setSize(1280, 720);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setResizable(false);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents();
        addComponents();

        mainFrame.pack();
        mainFrame.setVisible(true);

        System.out.println("Main Panel: " + mainPanel.getPreferredSize());
        System.out.println("Banner Panel: " + bannerPanel.getPreferredSize());
        System.out.println("Lower Panel: " + lowerPanel.getPreferredSize());
    }

    private void initComponents() {
        // MAIN PAGE
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setPreferredSize(GUIUtil.dimensionConfig(mainFrame.getWidth(), mainFrame.getHeight()));
        mainPanel.setBackground(GUIUtil.cDarkGreen);

        // BANNER IN MAIN PAGE
        bannerPanel = new JPanel();
        
        titleLbl = new JLabel();
        GUIUtil.textConfig(titleLbl, "ESports Tracker - Valorant", "Arial", Font.BOLD, 40, Color.WHITE);
        bannerPanel.setPreferredSize(GUIUtil.dimensionConfig((int)mainPanel.getPreferredSize().getWidth(), 60));
        bannerPanel.setOpaque(false);
        
        // LOWER PANEL
        lowerPanel = new JPanel();
        lowerPanel.setLayout(new GridBagLayout());
        lowerPanel.setPreferredSize(GUIUtil.dimensionConfig((int)mainPanel.getPreferredSize().getWidth()-(int)bannerPanel.getPreferredSize().getWidth(), 
                                                            (int)mainPanel.getPreferredSize().getHeight()-(int)bannerPanel.getPreferredSize().getHeight()));
        lowerPanel.setOpaque(mainPanel.isOpaque());

        // LEFT LOWER PANEL
        leftLowerPanel = new JPanel();
        leftLowerPanel.setLayout(new BoxLayout(leftLowerPanel, BoxLayout.Y_AXIS));
        GUIUtil.gridBagLayoutConfig(lowerPanel, leftLowerPanel, 0, 0, 0.2, 1.0);
        leftLowerPanel.setBorder(GUIUtil.emptyBorderConfig(200, 200, 200, 200));
        leftLowerPanel.setBackground(Color.YELLOW);

        menuLbl = new JLabel();
        GUIUtil.textConfig(menuLbl, "MENU", "Arial", Font.BOLD, 20, Color.BLACK);
        menuLbl.setAlignmentX(Component.CENTER_ALIGNMENT);

        // BUTTONS
        Dimension buttonSize = new Dimension(200, 50);

        mainViewBtn = new JButton("Main View");
        makeTransacBtn = new JButton("Make Transaction");
        genReportsBtn = new JButton("Generate Reports");
        exitBtn = new JButton("Exit");

        mainViewBtn.setMinimumSize(buttonSize);
        mainViewBtn.setPreferredSize(buttonSize);
        mainViewBtn.setMaximumSize(buttonSize);
        mainViewBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        makeTransacBtn.setMinimumSize(buttonSize);
        makeTransacBtn.setPreferredSize(buttonSize);
        makeTransacBtn.setMaximumSize(buttonSize);
        makeTransacBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        genReportsBtn.setMinimumSize(buttonSize);
        genReportsBtn.setPreferredSize(buttonSize);
        genReportsBtn.setMaximumSize(buttonSize);        
        genReportsBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        exitBtn.setMinimumSize(buttonSize); 
        exitBtn.setPreferredSize(buttonSize);
        exitBtn.setMaximumSize(buttonSize);
        exitBtn.setAlignmentX(Component.CENTER_ALIGNMENT);




        // RIGHT LOWER PANEL
        rightLowerPanel = new JPanel();
        GUIUtil.gridBagLayoutConfig(lowerPanel, rightLowerPanel, 1, 0, 0.8, 1.0);
        // rightLowerPanel.setBorder(GUIUtil.emptyBorderConfig(getContentPane().getHeight(), getContentPane().getWidth(), 
        //                                                     getContentPane().getHeight(), getContentPane().getWidth()));
        rightLowerPanel.setBackground(Color.BLUE);

        sampleTextArea = new JTextArea(sampleText);
        sampleTextArea.setLineWrap(true);
        sampleTextArea.setWrapStyleWord(true);
        sampleTextArea.setPreferredSize(buttonSize);


        // MAIN PAGE END



    }

    private void addComponents() {
        bannerPanel.add(titleLbl);
        mainPanel.add(bannerPanel, BorderLayout.NORTH);
        mainPanel.add(lowerPanel, BorderLayout.CENTER);

        lowerPanel.add(leftLowerPanel);
        leftLowerPanel.add(menuLbl);
        leftLowerPanel.add(Box.createVerticalStrut(20));
        leftLowerPanel.add(mainViewBtn);
        leftLowerPanel.add(Box.createVerticalStrut(10));
        leftLowerPanel.add(makeTransacBtn);
        leftLowerPanel.add(Box.createVerticalStrut(10));
        leftLowerPanel.add(genReportsBtn);
        leftLowerPanel.add(Box.createVerticalStrut(100));
        leftLowerPanel.add(exitBtn);


        rightLowerPanel.add(sampleTextArea);

        lowerPanel.add(rightLowerPanel);

        mainFrame.add(mainPanel);
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
