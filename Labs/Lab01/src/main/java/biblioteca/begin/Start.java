package biblioteca.begin;

import java.io.IOException;

import biblioteca.control.BibliotecaCtrl;
import biblioteca.repository.repo.CartiRepoFile;
import biblioteca.repository.repoInterfaces.CartiRepoInterface;
import biblioteca.view.Consola;

//functionalitati
//F01.	 adaugarea unei noi carti (titlu, autori, an aparitie, editura, cuvinte cheie);
//F02.	 cautarea cartilor scrise de un anumit autor (sau parti din numele autorului);
//F03.	 afisarea cartilor din biblioteca care au aparut intr-un anumit an, ordonate alfabetic dupa titlu si autori.


public class Start {

    public static void main(String[] args) {
        CartiRepoInterface cr = new CartiRepoFile();
        BibliotecaCtrl bc = new BibliotecaCtrl(cr);
        Consola c = new Consola(bc);
        try {
            c.executa();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
