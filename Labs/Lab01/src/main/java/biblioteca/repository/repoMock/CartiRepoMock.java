package biblioteca.repository.repoMock;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import biblioteca.model.Carte;
import biblioteca.repository.repoInterfaces.CartiRepoInterface;

public class CartiRepoMock implements CartiRepoInterface {

    private List<Carte> carti;

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

    public CartiRepoMock() {
        carti = new ArrayList<Carte>();

        carti.add(getCarteFromString("Povesti;Mihai Eminescu,Ion Caragiale,Ion Creanga;1973;Corint;povesti,povestiri"));
        carti.add(getCarteFromString("Poezii;Sadoveanu;1973;Corint;poezii"));
        carti.add(getCarteFromString("Enigma Otiliei;George Calinescu;1948;Litera;enigma,otilia"));
        carti.add(getCarteFromString("Dale carnavalului;Caragiale Ion;1948;Litera;caragiale,carnaval"));
        carti.add(getCarteFromString("Intampinarea crailor;Mateiu Caragiale;1948;Litera;mateiu,crailor"));
        carti.add(getCarteFromString("Test;Calinescu,Tetica;1992;Pipa;am,casa"));

    }

    @Override
    public void adaugaCarte(Carte c) {
        carti.add(c);
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
    public List<Carte> getCarti() {
        return carti;
    }

    @Override
    public void modificaCarte(Carte nou, Carte vechi) {
        // TODO Auto-generated method stub

    }

    @Override
    public void stergeCarte(Carte c) {
        // TODO Auto-generated method stub

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
                if (a.getTitlu().compareTo(b.getTitlu()) == 0) {
                    return a.getReferenti().get(0).compareTo(b.getReferenti().get(0));
                }

                return a.getTitlu().compareTo(b.getTitlu());
            }

        });

        return lca;
    }

}
