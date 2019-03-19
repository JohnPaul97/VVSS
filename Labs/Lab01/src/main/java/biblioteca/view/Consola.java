package biblioteca.view;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import biblioteca.control.BibliotecaCtrl;
import biblioteca.model.Carte;

public class Consola {

    private String numarRegex = "[1-9][0-9]{0,}";
    private String anRegex = "[10-9]+";

    private BufferedReader console;
    private BibliotecaCtrl bc;

    public Consola(BibliotecaCtrl bc) {
        this.bc = bc;
    }

    public void executa() throws IOException {

        console = new BufferedReader(new InputStreamReader(System.in));

        int opt = -1;
        while (opt != 0) {

            switch (opt) {
                case 1:
                    adaugaCarte();
                    break;
                case 2:
                    cautaCartiDupaAutor();
                    break;
                case 3:
                    afiseazaCartiOrdonateDinAnul();
                    break;
                case 4:
                    afiseazaToateCartile();
                    break;
            }

            printMenu();
            String line;
            do {
                System.out.println("Introduceti un nr:");
                line = console.readLine();
            } while (!line.matches("[0-4]"));
            opt = Integer.parseInt(line);
        }
    }

    private void printMenu() {
        System.out.println("\n\n\n");
        System.out.println("Evidenta cartilor dintr-o biblioteca");
        System.out.println("     1. Adaugarea unei noi carti");
        System.out.println("     2. Cautarea cartilor scrise de un anumit autor");
        System.out.println("     3. Afisarea cartilor din biblioteca care au aparut intr-un anumit an, ordonate alfabetic dupa titlu si autori");
        System.out.println("     4. Afisarea toturor cartilor");
        System.out.println("     0. Exit");
    }

    private void adaugaCarte() {
        Carte c = new Carte();
        try {
            System.out.println("\n\n\n");

            System.out.println("Titlu:");
            c.setTitlu(console.readLine());

            String line;
            do {
                System.out.println("An aparitie:");
                line = console.readLine();
            } while (!line.matches(anRegex));
            c.setAnAparitie(Integer.parseInt(line));

            do {
                System.out.println("Nr. de referent:");
                line = console.readLine();
            } while (!line.matches(numarRegex));
            int nrReferenti = Integer.parseInt(line);
            for (int i = 1; i <= nrReferenti; i++) {
                System.out.println("Autor " + i + ": ");
                c.adaugaReferent(console.readLine());
            }

            System.out.println("Editura:");
            c.setEditura(console.readLine());

            do {
                System.out.println("Nr. de cuvinte cheie:");
                line = console.readLine();
            } while (!line.matches(numarRegex));
            int nrCuv = Integer.parseInt(line);
            for (int i = 1; i <= nrCuv; i++) {
                System.out.println("Cuvant " + i + ": ");
                c.adaugaCuvantCheie(console.readLine());
            }

            bc.adaugaCarte(c);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void afiseazaToateCartile() {
        System.out.println("\n\n\n");
        try {
            for (Carte c : bc.getCarti()) {
                System.out.println(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void cautaCartiDupaAutor() {

        System.out.println("\n\n\n");
        System.out.println("Autor:");
        try {
            for (Carte c : bc.cautaCarte(console.readLine())) {
                System.out.println(c);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void afiseazaCartiOrdonateDinAnul() {
        System.out.println("\n\n\n");

        try {
            String line;
            do {
                System.out.println("An aparitie:");
                line = console.readLine();
            } while (!line.matches(numarRegex));
            for (Carte c : bc.getCartiOrdonateDinAnul(line)) {
                System.out.println(c);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
