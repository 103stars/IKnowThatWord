package myProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * This class is used to create the main frame of the program
 * @author Alejandro Lasso
 * @version v.1.0.0 date:21/11/2021
 */
public class GuiKnowThatWord extends JFrame {
    private JTextArea areaPuntaje;
    private JLabel sesion, palabras, indicadorNivel;
    private JButton si, no, instrucciones, empezar, iniciar;
    private JTextField nombredeUsuario;
    private Escucha escucha;
    private FileManager fileManager;
    private Timer timerRecordar, timerAdivinar, timer1, timer2, timer3;

    /**
     * Constructor of GUI class
     */
    public GuiKnowThatWord() {
        initGUI();
        //Default JFrame configuration
        this.setTitle("I KNOW THAT WORD");
        this.setSize(600, 500);
        //this.pack();
        this.setResizable(false);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(new Color(71,120,177));
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
        sesion.setForeground(new Color(255, 255, 255));

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.PAGE_START;
        constraints.insets = new Insets(0, 0, 100, 0);
        add(sesion, constraints);

        nombredeUsuario = new JTextField();
        nombredeUsuario.setPreferredSize(new Dimension(200, 30));

        nombredeUsuario.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                if (e.getKeyChar()==','){
                    e.consume();
                }
            }
        });
        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(80, 0, 40, 0);
        add(nombredeUsuario, constraints);

        empezar = new JButton("Empezar");
        empezar.addActionListener(escucha);
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.insets = new Insets(0, 0, 0, 0);
        add(empezar, constraints);

        instrucciones = new JButton("Como Jugar");
        instrucciones.setVisible(false);
        instrucciones.addActionListener(escucha);
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.insets = new Insets(30, 0, 30, 0);
        add(instrucciones, constraints);

        si = new JButton("      Si      ");
        si.addActionListener(escucha);
        si.setVisible(false);
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.insets = new Insets(0, 0, 0, 135);
        add(si, constraints);

        no = new JButton("      No      ");
        no.setVisible(false);
        no.addActionListener(escucha);
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.insets = new Insets(0, 115, 0, 0);
        add(no, constraints);
        si.setVisible(false);
        no.setVisible(false);


        Image img = Toolkit.getDefaultToolkit().getImage("src/myProject/resources/bg.png");

        areaPuntaje = new JTextArea(3, 28);
        areaPuntaje.setBorder(BorderFactory.createTitledBorder("Información"));
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.insets = new Insets(160, 0, 0, 0);
        constraints.anchor = GridBagConstraints.CENTER;
        add(areaPuntaje, constraints);
        areaPuntaje.setEditable(false);
        areaPuntaje.setVisible(false);

        iniciar = new JButton("Iniciar");
        iniciar.setVisible(false);
        iniciar.addActionListener(escucha);
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.insets = new Insets(0, 0, 0, 0);
        add(iniciar, constraints);

        palabras = new JLabel("Memoriza las siguientes palabras:");
        palabras.setFont(new Font("Regular", Font.PLAIN, 35));
        palabras.setVisible(false);
        palabras.setForeground(new Color(255, 255, 255));
        constraints.insets = new Insets(100,0,0,0);
        add(palabras, constraints);

        indicadorNivel = new JLabel("");
        indicadorNivel.setFont(new Font("Regular", Font.PLAIN, 30));
        indicadorNivel.setVisible(false);
        indicadorNivel.setForeground(new Color(255, 255, 255));
        constraints.gridx=0;
        constraints.gridy=0;
        constraints.insets = new Insets(0,0,120,0);
        add(indicadorNivel,constraints);


        timer1 = new Timer(300, escucha);
        timer2 = new Timer(300, escucha);
        timer3= new Timer(0,escucha);
        timerAdivinar = new Timer(7000, escucha);
        timerRecordar = new Timer(5000, escucha);

    }

    /**
     * Main process of the Java program
     * @param args Object used in order to send input data from command line when
     *             the program is execute by console.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            GuiKnowThatWord miProjectGUI = new GuiKnowThatWord();
        });
    }

    /**
     * inner class that extends an Adapter Class or implements Listeners used by GUI class
     * adds, functionality to jcomponents
     */
    private class Escucha implements ActionListener {
        private ControlWord controlWord;
        private int counter;

        @Override
        public void actionPerformed(ActionEvent e) {


            if (e.getSource() == instrucciones) {
                JOptionPane.showMessageDialog(null, "En cada nivel al comienzo se mostrará una serie de palabras," +"\n"+
                        "tendrás que memorizarlas, luego se mostrarán mezcladas junto" +"\n"+
                        "con otras palabras, tendrás que decidir cuales pertenecen al" +"\n"+
                        "conjunto inicial. La cantidad de aciertos que necesitas corresponde"+"\n"+ " al 70% del total de las palabras mostradas.");

            } else if (e.getSource() == empezar) {
                //Guarda los datos en un archivo de texto
                fileManager.agregarUsuario(nombredeUsuario.getText(), 1);
                iniciar.setVisible(true);
                instrucciones.setVisible(true);
                nombredeUsuario.setVisible(false);
                empezar.setVisible(false);
                sesion.setVisible(false);

            }
            if (e.getSource() == iniciar) {

                palabras.setText("Memoriza las siguientes palabras:");
                timer1.start();
                timer3.stop();
                indicadorNivel.setVisible(false);
                controlWord = new ControlWord(nombredeUsuario.getText());
                iniciar.setVisible(false);
                si.setVisible(false);
                no.setVisible(false);
                areaPuntaje.setVisible(true);
                instrucciones.setVisible(false);
                palabras.setVisible(true);


                System.out.println("Correctas " + controlWord.getPalabrasCorrectas());
                System.out.println("------------------------------------------");
                System.out.println("Incorrectas " + controlWord.getPalabrasIncorrectas());
                System.out.println("------------------------------------------");
                System.out.println("Combinada " + controlWord.getListaCombinada());
                System.out.println("---------------------------------------------");
                areaPuntaje.setText("Nivel: " + controlWord.getNivel()+"\n"+
                        "Aciertos: 0");

            }
            if (e.getSource() == timer1) {
                timer1.stop();
                timer3.stop();
                timerRecordar.start();
            }
            if (e.getSource() == timerRecordar) {
                if (counter < controlWord.getPalabrasCorrectas().size()) {
                    palabras.setText(controlWord.getPalabrasCorrectas().get(counter));
                    counter++;
                } else {
                    timerRecordar.stop();
                    counter = 0;
                    si.setEnabled(false);
                    no.setEnabled(false);
                    timer2.start();
                }
            }
            if (e.getSource() == timer2) {
                palabras.setText("Preparate");
                si.setVisible(true);
                no.setVisible(true);
                timer2.stop();
                timerAdivinar.start();
                counter=0;
            }
            if (e.getSource() == timerAdivinar){
                si.setEnabled(true);
                no.setEnabled(true);

                if (counter < controlWord.getListaCombinada().size()) {
                    palabras.setText(controlWord.getListaCombinada().get(counter));
                    counter++;
                }else{
                    timerAdivinar.stop();
                    si.setEnabled(false);
                    no.setEnabled(false);
                    controlWord.resultadoNivel();
                    areaPuntaje.setText("Nivel: " + controlWord.getNivel()+"\n"+
                            "Aciertos: "+ controlWord.getAciertos());
                    timer3.start();
                }
            }
            if (e.getSource()==si){
                si.setEnabled(false);
                no.setEnabled(false);
                String palabraMostrada = palabras.getText();

                if(controlWord.getPalabrasCorrectas().contains(palabraMostrada)){
                    controlWord.setAciertos(controlWord.getAciertos()+1);
                    areaPuntaje.setText("Nivel: " + controlWord.getNivel()+"\n"+
                            "Aciertos: "+ controlWord.getAciertos());
                    System.out.println(controlWord.getAciertos());
                }
            }if (e.getSource()==no){
                si.setEnabled(false);
                no.setEnabled(false);
                String palabraMostrada = palabras.getText();
                if(controlWord.getPalabrasIncorrectas().contains(palabraMostrada)) {
                    controlWord.setAciertos(controlWord.getAciertos() + 1);
                    areaPuntaje.setText("Nivel: " + controlWord.getNivel()+"\n"+
                            "Aciertos: "+ controlWord.getAciertos());

                    System.out.println(controlWord.getAciertos());
                }
            }if (e.getSource()==timer3){
                counter=0;
                indicadorNivel.setText("Click en iniciar para intentar el nivel");
                palabras.setVisible(false);
                si.setVisible(false);
                no.setVisible(false);
                areaPuntaje.setVisible(false);
                indicadorNivel.setVisible(true);
                iniciar.setVisible(true);

            }
        }
    }
}