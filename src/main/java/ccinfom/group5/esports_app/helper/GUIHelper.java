package ccinfom.group5.esports_app.helper;

import java.awt.*;
import javax.swing.*;

public class GUIHelper {

    public static final Color cDarkGreen = new Color(0, 102, 0);

    public static void  gridBagLayoutConfig(JPanel panel, Component component, int gridx, 
        int gridy, double weightx, double weighty) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.weightx = weightx;
        gbc.weighty = weighty;
        gbc.fill = GridBagConstraints.BOTH;
        panel.add(component, gbc);
    }


    public static void textConfig(Component component, String text, String fontName, int fontStyle, int fontSize, Color color) {
        Font font = new Font(fontName, fontStyle, fontSize);
        component.setFont(font);
        component.setForeground(color);
        
        if (component instanceof JLabel) {
            ((JLabel) component).setText(text);
        }
        else if (component instanceof JTextArea) {
            ((JTextArea) component).setText(text);
        }
        else if (component instanceof JButton) {
            ((JButton) component).setText(text);
        } 
        else {
            throw new IllegalArgumentException("Unsupported component type");
        }
    }
}
