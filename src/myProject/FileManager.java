package myProject;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileManager {
    private FileReader fileReader;
    private BufferedReader input;
    private FileWriter fileWriter;
    private BufferedWriter output;


    public String lecturaFile() {
        String texto="";

        try {
            fileReader = new FileReader("src/myProject/files/fileText.txt");
            input = new BufferedReader(fileReader);
            String line = input.readLine();
            while(line!=null){
                texto+=line;
                texto+="\n";
                line=input.readLine();
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
        return texto;
    }

    public void escribirTexto(String linea, int nivel){
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
                } else {
                    output.write(linea + "," + nivel);
                    output.newLine();
                    isProcesado = true;
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

}