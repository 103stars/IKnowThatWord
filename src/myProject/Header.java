package myProject;

import javax.swing.*;
import java.awt.*;

/**
 * This class is a kind of JLabel used to create a personalized Header for the project
 * @autor Paola-J Rodriguez-C paola.rodriguez@correounivalle.edu.co
 * @version @version v.1.0.0 date:21/11/2021
 */
public class Header extends JLabel {
    public Header(String titulo, Color colorFondo){
        this.setText(titulo);
        this.setBackground(colorFondo);
        this.setForeground(Color.WHITE);
        this.setFont(new Font(Font.DIALOG, Font.ITALIC,20 ));
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setOpaque(true);

    }
}
