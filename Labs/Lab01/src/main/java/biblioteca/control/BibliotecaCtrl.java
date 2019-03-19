package biblioteca.control;


import java.util.List;

import biblioteca.model.Carte;
import biblioteca.repository.repoInterfaces.CartiRepoInterface;
import biblioteca.util.CarteValidator;

public class BibliotecaCtrl {

    private CartiRepoInterface cr;

    public BibliotecaCtrl(CartiRepoInterface cr) {
        this.cr = cr;
    }

    public void adaugaCarte(Carte c) throws Exception {
        CarteValidator.validateCarte(c);
        cr.adaugaCarte(c);
    }

    public void modificaCarte(Carte nou, Carte vechi) throws Exception {
        cr.modificaCarte(nou, vechi);
    }

    public void stergeCarte(Carte c) throws Exception {
        cr.stergeCarte(c);
    }

    public List<Carte> cautaCarte(String autor) throws Exception {
        CarteValidator.isOKString(autor);
        return cr.cautaCarte(autor);
    }

    public List<Carte> getCarti() throws Exception {
        return cr.getCarti();
    }

    public List<Carte> getCartiOrdonateDinAnul(String an) throws Exception {
        if (!CarteValidator.isNumber(an)) {
            throw new Exception("Nu e numar!");
        }
        return cr.getCartiOrdonateDinAnul(Integer.parseInt(an));
    }

}
