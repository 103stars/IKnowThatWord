package myProject;

import javax.swing.*;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    private FileReader fileReader;
    private BufferedReader input;
    private FileWriter fileWriter;
    private BufferedWriter output;
    private GuiKnowThatWord GUI;


    /**
     * Picks elements for correct, incorrect and mixed lists
     * @param rutaArchivo
     * @param nivel
     * @return
     */
    public List<String> lecturaFile(String rutaArchivo, int nivel) {

        List<String> palabras = new ArrayList<>();
        int cantidadPalabras = nivel * 10;
        String texto = "";

        try {
            fileReader = new FileReader(rutaArchivo);
            input = new BufferedReader(fileReader);
            String line = null;
            int contador = 0;
            while((line = input.readLine())!= null){
                palabras.add(line);
                contador++;
                if(contador >= cantidadPalabras){
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return palabras;
    }


    /**
     * Reads file user.txt to check if a player already exists, otherwise a new user will be created. Starting at level 1
     * @param linea
     * @param nivel
     */
    public void agregarUsuario(String linea, int nivel){
        try {
            Path p = Paths.get("src/myProject/diccionario/user.txt");
            File init = new File(p.toUri());
            File tmp = new File(init.getAbsolutePath() + ".tmp");

            fileReader = new FileReader(init);
            fileWriter = new FileWriter(tmp);
            output = new BufferedWriter(fileWriter);
            input  = new BufferedReader(fileReader);

            String txt = null;

            boolean isProcesado = false;
            while((txt = input.readLine()) != null){
                if(!txt.split(",")[0].equals(linea)){
                    output.write(txt);
                    output.newLine();
                } else if(!isProcesado){
                    output.write(txt);
                    output.newLine();

                    isProcesado = true;
                    JOptionPane.showMessageDialog(null,"Bienvenido de vuelta "+ linea +"!" + "\n"+
                            "Vas en el nivel "+txt.split(",")[1]);
                }
            }
            input.close();
            if(!isProcesado){
                output.write(linea + "," + nivel);
                output.newLine();
            }

            output.close();
            if(init.delete()){

                System.out.println("se borra");
            }
            tmp.renameTo(init);

        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Similar to the last method, this time it works as an updater if a player beats a level
     * @param linea
     * @param nivel
     */
    public void actualizarUsuario(String linea, int nivel){
        try {
            Path p = Paths.get("src/myProject/diccionario/user.txt");
            File init = new File(p.toUri());
            File tmp = new File(init.getAbsolutePath() + ".tmp");

            fileReader = new FileReader(init);
            fileWriter = new FileWriter(tmp);
            output = new BufferedWriter(fileWriter);
            input  = new BufferedReader(fileReader);

            String txt = null;

            boolean isProcesado = false;
            while((txt = input.readLine()) != null){
                if(!txt.split(",")[0].equals(linea)){
                    output.write(txt);
                    output.newLine();
                } else if(!isProcesado){
                    output.write(linea + "," + nivel);
                    output.newLine();

                    isProcesado = true;
                    JOptionPane.showMessageDialog(null,"El nivel actual es el "+
                            nivel);
                }
            }
            input.close();
            if(!isProcesado){
                output.write(linea + "," + nivel);
                output.newLine();
            }

            output.close();
            if(init.delete()){

                System.out.println("se borra");
            }
            tmp.renameTo(init);

        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * gets the username value, used to check for an existing player
     * @param usuario
     * @return username
     */
    public String retornaUsuario(String usuario){
        String texto = null;
        try {
            Path p = Paths.get("src/myProject/diccionario/user.txt");
            File init = new File(p.toUri());

            fileReader = new FileReader(init);
            input  = new BufferedReader(fileReader);

            while ((texto = input.readLine()) != null){
                if(texto.split(",")[0].equals(usuario)){
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return texto;
    }

}