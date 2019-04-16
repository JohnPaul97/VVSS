package biblioteca.repository.repo;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import biblioteca.model.Carte;
import biblioteca.repository.repoInterfaces.CartiRepoInterface;

public class CartiRepoFile implements CartiRepoInterface {

    private String file = "cartiBD.dat";

    private static Carte getCarteFromString(String carte) {
        Carte c = new Carte();
        String[] atr = carte.split(";");
        String[] referenti = atr[1].split(",");
        String[] cuvCheie = atr[4].split(",");

        c.setTitlu(atr[0]);

        for (String s : referenti) {
            c.adaugaReferent(s);
        }
        c.setAnAparitie(Integer.parseInt(atr[2]));
        c.setEditura(atr[3]);
        for (String s : cuvCheie) {
            c.adaugaCuvantCheie(s);
        }

        return c;
    }

    public CartiRepoFile() {
        URL location = CartiRepoFile.class.getProtectionDomain().getCodeSource().getLocation();
        System.out.println(location.getFile());
    }

    @Override
    public void adaugaCarte(Carte c) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(file, true));
            bw.write(c.toString());
            bw.newLine();

            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Carte> getCarti() {
        List<Carte> lc = new ArrayList<Carte>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = br.readLine()) != null) {
                lc.add(getCarteFromString(line));
            }

            br.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return lc;
    }

    @Override
    public void modificaCarte(Carte nou, Carte vechi) {
        // TODO Auto-generated method stub

    }

    @Override
    public void stergeCarte(Carte c) {

    }

    @Override
    public List<Carte> cautaCarte(String ref) {
        List<Carte> carti = getCarti();
        List<Carte> cartiGasite = new ArrayList<Carte>();
        int i = 0;
        while (i < carti.size()) {
            boolean flag = false;
            List<String> lref = carti.get(i).getReferenti();
            int j = 0;
            while (j < lref.size()) {
                if (lref.get(j).toLowerCase().contains(ref.toLowerCase())) {
                    flag = true;
                    break;
                }
                j++;
            }
            if (flag == true) {
                cartiGasite.add(carti.get(i));
            }
            i++;
        }
        return cartiGasite;
    }

    @Override
    public List<Carte> getCartiOrdonateDinAnul(int an) {
        List<Carte> lc = getCarti();
        List<Carte> lca = new ArrayList<Carte>();
        for (Carte c : lc) {
            if (c.getAnAparitie() == an) {
                lca.add(c);
            }
        }

        Collections.sort(lca, new Comparator<Carte>() {

            @Override
            public int compare(Carte a, Carte b) {
                if (a.getAnAparitie() == b.getAnAparitie()) {
                    return a.getTitlu().compareTo(b.getTitlu());
                } else if (a.getAnAparitie() < b.getAnAparitie()) {
                    return -1;
                } else {
                    return 1;
                }
            }

        });

        return lca;
    }

}
