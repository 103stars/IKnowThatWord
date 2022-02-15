package myProject;


import java.util.ArrayList;
import javax.swing.*;
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
    private JTextArea areaPuntaje;
    private JLabel sesion;
    private JButton si, no, instrucciones, jugar;
    private JTextField nombredeUsuario;
    private String usuarioTexto;
    private Escucha escucha;
    private ArrayList<String> palabrasOK, palabrasNotOK;
    private FileManager fileManager;
    public static String usuariohola;


    /**
     * Constructor of GUI class
     */
    public GUI(){
        initGUI();
        //Default JFrame configuration
        this.setTitle("I KNOW THAT WORD");
        this.setSize(720,450);
        //this.pack();
        this.setResizable(false);
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
        fileManager = new FileManager();




        sesion = new JLabel("Ingresa tu nombre");
        sesion.setFont(new Font("Regular", Font.PLAIN, 25));

        constraints.gridx=0;
        constraints.gridy=1;
        constraints.anchor=GridBagConstraints.PAGE_START;
        constraints.insets = new Insets(0,0,100,0);
        add(sesion,constraints);



        nombredeUsuario = new JTextField();
        nombredeUsuario.setPreferredSize(new Dimension(220, 30));


        constraints.gridy=1;
        constraints.anchor=GridBagConstraints.CENTER;
        constraints.insets = new Insets(100,0,0,0);
        add(nombredeUsuario,constraints);

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

        si = new JButton("      Si      ");
        si.addActionListener(escucha);
        constraints.gridx=0;
        constraints.gridy=2;
        constraints.insets = new Insets(150,0,0,135);
        add(si,constraints);

        no = new JButton("      No      ");
        no.addActionListener(escucha);
        constraints.gridx=0;
        constraints.gridy=2;
        constraints.insets = new Insets(150,115,0,0);
        add(no,constraints);
        si.setVisible(false);
        no.setVisible(false);

        areaPuntaje = new JTextArea(4,28);
        areaPuntaje.setBorder(BorderFactory.createTitledBorder("Información"));
        areaPuntaje.setText("text");
        constraints.gridx=0;
        constraints.gridy=2;
        constraints.gridwidth=1;
        constraints.insets = new Insets(280,0,0,0);
        constraints.anchor=GridBagConstraints.CENTER;
        add(areaPuntaje, constraints);
        areaPuntaje.setEditable(false);
        areaPuntaje.setVisible(false);

        usuariohola = (nombredeUsuario.getText());

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
            if(e.getSource()==instrucciones){
                JOptionPane.showMessageDialog(null, "En cada nivel al comienzo se mostrarán una serie de palabras, tendrás que memorizarlas y decir");
            }else {
                //Guarda los datos en un archivo de texto
                fileManager.escribirTexto(nombredeUsuario.getText(),1);
                nombredeUsuario.setText("");

                nombredeUsuario.setVisible(false);
                jugar.setVisible(false);
                sesion.setVisible(false);

                instrucciones.setVisible(false);

                si.setVisible(true);
                no.setVisible(true);
                areaPuntaje.setVisible(true);
        }
    }}
}
