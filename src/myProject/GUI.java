package myProject;


import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * This class is used for ...
 * @autor Paola-J Rodriguez-C paola.rodriguez@correounivalle.edu.co
 * @version v.1.0.0 date:21/11/2021
 */
public class GUI extends JFrame {

    private Header headerProject;
    private JPanel login, juego, panelLogin;
    private JLabel sesion;
    private JButton si, no, instrucciones, jugar;
    private JTextField usuario;
    private String usuarioTexto;
    private Escucha escucha;
    private ArrayList<String> palabrasOK, palabrasNotOK;

    /**
     * Constructor of GUI class
     */
    public GUI(){
        initGUI();

        //Default JFrame configuration
        this.setTitle("I KNOW THAT WORD");
        this.setSize(720,450);
        //this.pack();
        this.setResizable(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * This method is used to set up the default JComponent Configuration,
     * create Listener and control Objects used for the GUI class
     */
    private void initGUI() {
        //Set up JFrame Container's Layout
        //Create Listener Object and Control Object
        //Set up JComponents
        this.getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        escucha = new Escucha();


        sesion = new JLabel("Ingresa tu nombre");
        sesion.setFont(new Font("Regular", Font.PLAIN, 25));
        constraints.gridx=0;
        constraints.gridy=1;
        constraints.anchor=GridBagConstraints.PAGE_START;
        constraints.insets = new Insets(0,0,100,0);
        add(sesion,constraints);



        usuario = new JTextField();
        usuario.setPreferredSize(new Dimension(220, 30));
        constraints.gridy=1;
        constraints.anchor=GridBagConstraints.CENTER;
        constraints.insets = new Insets(100,0,0,0);
        add(usuario,constraints);

        jugar = new JButton("Empezar");
        jugar.addActionListener(escucha);

        constraints.gridx=0;
        constraints.gridy=2;
        constraints.insets = new Insets(0,0,0,135);
        add(jugar,constraints);

        instrucciones = new JButton("Como Jugar");
        instrucciones.addActionListener(escucha);
        constraints.gridx=0;
        constraints.gridy=2;
        constraints.insets = new Insets(30,115,30,0);
        add(instrucciones,constraints);


    }

    /**
     * Main process of the Java program
     * @param args Object used in order to send input data from command line when
     *             the program is execute by console.
     */
    public static void main(String[] args){
        EventQueue.invokeLater(() -> {
            GUI miProjectGUI = new GUI();
        });
    }

    /**
     * inner class that extends an Adapter Class or implements Listeners used by GUI class
     */
    private class Escucha implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource()==instrucciones){
                JOptionPane.showMessageDialog(null, "En este juego tendrás que recordar las palabras que se mostrarán al comienzo y luego decidir si" + "\n"+
                        "pertenecen al conjunto de palabras que se mostrarán después, el truco está en que tambien van " + "\n"+"a aparecer palabras que no están incluidas, basado en tu memoria decidirás si estas palabras" + "\n"+
                        "están incluidas en el conjunto inicial, entre mas alto sea el nivel más tendrás que memorizar y" + "\n"+"más palabras erroneas se mostrarán");
            }else{
                usuario.getText();
                jugar.setVisible(false);
                instrucciones.setVisible(false);
                sesion.setVisible(false);
                usuario.setVisible(false);
            }
        }
    }
}
