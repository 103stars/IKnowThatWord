package myProject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class ControlWord {

    private String user;
    private int nivel;
    private FileManager fileManager;
    private List<String> palabrasCorrectas, palabrasIncorrectas;

    public ControlWord () {

    }

    public ControlWord (String user) {
        this.user = user;
        this.fileManager = new FileManager();
        inicializar();

    }

    public void inicializar(){
        String infoUser = this.fileManager.retornaUsuario(this.user);
        String nivelUsuario = infoUser.split(",")[1];
        this.nivel = Integer.parseInt(nivelUsuario);
        this.palabrasCorrectas = fileManager.lecturaFile("src/myProject/diccionario/wordsOk.txt",nivel);
        this.palabrasIncorrectas = fileManager.lecturaFile("src/myProject/diccionario/wordsNok.txt",nivel);


    }

    public List<String> mixPalabras(){
        List<String> listaCombinada = new ArrayList<>();
        listaCombinada.addAll(palabrasCorrectas);
        listaCombinada.addAll(palabrasIncorrectas);
        Collections.shuffle(listaCombinada);
        return  listaCombinada;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public FileManager getFileManager() {
        return fileManager;
    }

    public void setFileManager(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    public List<String> getPalabrasCorrectas() {
        return palabrasCorrectas;
    }

    public void setPalabrasCorrectas(List<String> palabrasCorrectas) {
        this.palabrasCorrectas = palabrasCorrectas;
    }

    public List<String> getPalabrasIncorrectas() {
        return palabrasIncorrectas;
    }

    public void setPalabrasIncorrectas(List<String> palabrasIncorrectas) {
        this.palabrasIncorrectas = palabrasIncorrectas;
    }
}