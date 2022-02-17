package myProject;


import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.stream.Stream;


/**
 * This class is used for ...
 * @autor Paola-J Rodriguez-C paola.rodriguez@correounivalle.edu.co
 * @version v.1.0.0 date:21/11/2021
 */
public class GUI extends JFrame {
    ;
    private JTextArea areaPuntaje;
    private JLabel sesion, palabras;
    private JButton si, no, instrucciones, jugar, empezar;
    private JTextField nombredeUsuario;
    private Escucha escucha;
    private FileManager fileManager;
    public static String usuariohola;
    private Timer timerRecordar, timerAdivinar, timer1, timer2, timer3;

    /**
     * Constructor of GUI class
     */
    public GUI(){
        initGUI();
        //Default JFrame configuration
        this.setTitle("I KNOW THAT WORD");
        this.setSize(720,600);
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
        constraints.insets = new Insets(80,0,40,0);
        add(nombredeUsuario,constraints);

        jugar = new JButton("Empezar");
        jugar.addActionListener(escucha);
        constraints.gridx=0;
        constraints.gridy=2;
        constraints.insets = new Insets(0,0,0,0);
        add(jugar,constraints);

        instrucciones = new JButton("Como Jugar");
        instrucciones.setVisible(false);
        instrucciones.addActionListener(escucha);
        constraints.gridx=0;
        constraints.gridy=2;
        constraints.insets = new Insets(30,0,30,0);
        add(instrucciones,constraints);

        si = new JButton("      Si      ");
        si.addActionListener(escucha);
        si.setVisible(false);
        constraints.gridx=0;
        constraints.gridy=2;
        constraints.insets = new Insets(150,0,0,135);
        add(si,constraints);

        no = new JButton("      No      ");
        no.setVisible(false);
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

        empezar = new JButton("Iniciar");
        empezar.setVisible(false);
        empezar.addActionListener(escucha);
        constraints.gridx=0;
        constraints.gridy=0;
        constraints.gridwidth=1;
        constraints.insets = new Insets(0,0,0,0);
        add(empezar,constraints);

        usuariohola = (nombredeUsuario.getText());
        palabras = new JLabel("Memoriza las siguientes palabras:");
        palabras.setFont(new Font("Regular", Font.PLAIN, 35));
        palabras.setVisible(false);
        //constraints.insets = new Insets(280,0,0,0);
        add(palabras,constraints);



        timer1 = new Timer(300,escucha);
        timer2 = new Timer(300,escucha);
        timerAdivinar = new Timer(500,escucha);
        timerRecordar = new Timer(100,escucha);

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
    private class Escucha implements ActionListener{
        private ControlWord controlWord;
        private int counter;
        //private String usuarioString;

        @Override
        public void actionPerformed(ActionEvent e) {

            if(e.getSource()==instrucciones){
                JOptionPane.showMessageDialog(null, "En cada nivel al comienzo se mostrarán una serie de palabras, tendrás que memorizarlas y decir");

            }else if (e.getSource()==jugar){
                //Guarda los datos en un archivo de texto


                fileManager.actualizarUsuario(nombredeUsuario.getText(),1);
                //nombredeUsuario.setText("");


                empezar.setVisible(true);
                instrucciones.setVisible(true);
                nombredeUsuario.setVisible(false);
                jugar.setVisible(false);
                sesion.setVisible(false);



            }if (e.getSource()==empezar){

                timer1.start();
                controlWord = new ControlWord(nombredeUsuario.getText());
                empezar.setVisible(false);
                si.setVisible(false);
                no.setVisible(false);
                areaPuntaje.setVisible(true);
                instrucciones.setVisible(false);
                palabras.setVisible(true);

                System.out.println("Correctas " + controlWord.getPalabrasCorrectas());
                System.out.println("------------------------------------------");
                System.out.println("Incorrectas " + controlWord.getPalabrasIncorrectas());
                System.out.println("------------------------------------------");
                System.out.println("Combinada "+controlWord.mixPalabras());


            }if(e.getSource()==timer1){
                timer1.stop();
                timerRecordar.start();
            }if (e.getSource()==timerRecordar){
                if(counter<controlWord.getPalabrasCorrectas().size()){
                    palabras.setText(controlWord.getPalabrasCorrectas().get(counter));
                    counter++;

                }else{
                    timerRecordar.stop();
                    counter = 0;
                    palabras.setText("yop");
                    si.setVisible(true);
                    no.setVisible(true);
                    si.setEnabled(false);
                    no.setEnabled(false);
                    System.out.println("recordar termina");
                    timer2.start();
                }

            }if (e.getSource()==timer2){
                palabras.setText("Preparate");
                timer2.stop();
                timerAdivinar.start();
            }

            if (e.getSource()==timerAdivinar){
                si.setEnabled(true);
                no.setEnabled(true);
                if(counter<controlWord.mixPalabras().size()){
                    palabras.setText(controlWord.mixPalabras().get(counter));
                    counter++;
                }
            }
        }
    }
}