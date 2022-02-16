package myProject;

import java.util.List;

public class ControlWord {

    private String user;
    private int nivel;
    private FileManager fileManager;
    private List<String> palabrasCorrectas, palbarasIncorrectas;

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
        this.palbarasIncorrectas = fileManager.lecturaFile("src/myProject/diccionario/wordsNok.txt",nivel);
    }

}