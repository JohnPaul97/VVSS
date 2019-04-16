package biblioteca.control;

import biblioteca.model.Carte;
import biblioteca.repository.repo.CartiRepoFile;
import biblioteca.repository.repoMock.CartiRepoMock;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BibliotecaCtrlTest {

    private static final String TITLE = "Titlu";
    private static final int YEAR = 1997;

    private Carte carte = new Carte();

    private BibliotecaCtrl bibliotecaCtrl = new BibliotecaCtrl(new CartiRepoFile());

    @Before
    public void setup() {
        carte.setTitlu(TITLE);
        carte.setAnAparitie(YEAR);
        carte.setReferenti(Collections.singletonList("Autor"));
        carte.setEditura("Humanitas");
        carte.setCuvinteCheie(Collections.singletonList("cuvant cheie"));

        bibliotecaCtrl = new BibliotecaCtrl(new CartiRepoMock());
    }

    @After
    public void tearDown() throws Exception {
        bibliotecaCtrl.stergeCarte(carte);
    }

    @Test
    public void givenValidTitleAndYear_whenAddingCarte_carteIsSuccesufullyAdded() throws Exception {
        bibliotecaCtrl.adaugaCarte(carte);

        List<Carte> carti = bibliotecaCtrl.cautaCarte(carte.getReferenti().get(0));

        assertBooksEquals(carti.get(carti.size() - 1), carte);
    }

    @Test
    public void givenTitleHaving255Characters_whenAddingCarte_carteIsAdded() throws Exception {
        carte.setTitlu(RandomStringUtils.random(255, true, false));
        bibliotecaCtrl.adaugaCarte(carte);

        List<Carte> carti = bibliotecaCtrl.cautaCarte(carte.getReferenti().get(0));

        assertBooksEquals(carti.get(carti.size() - 1), carte);
    }

    @Test(expected = Exception.class)
    public void givenTitleHaving256Characters_whenAddingCarte_exceptionIsThrown() throws Exception {
        carte.setTitlu(RandomStringUtils.random(256, true, false));
        bibliotecaCtrl.adaugaCarte(carte);
    }

    @Test(expected = Exception.class)
    public void givenInvalidTitle_whenAddingCarte_exceptionIsThrown() throws Exception {
        carte.setTitlu("");
        bibliotecaCtrl.adaugaCarte(carte);
    }

    @Test(expected = Exception.class)
    public void givenInvalidYear_whenAddingCarte_exceptionIsThrown() throws Exception {
        carte.setAnAparitie(-1);
        bibliotecaCtrl.adaugaCarte(carte);
    }

    @Test
    public void givenYear0_whenAddingCarte_carteIsAdded() throws Exception {
        carte.setAnAparitie(0);
        bibliotecaCtrl.adaugaCarte(carte);

        List<Carte> carti = bibliotecaCtrl.cautaCarte(carte.getReferenti().get(0));

        assertBooksEquals(carti.get(carti.size() - 1), carte);
    }

    @Test
    public void givenYear1_whenAddingCarte_carteIsAdded() throws Exception {
        carte.setAnAparitie(1);
        bibliotecaCtrl.adaugaCarte(carte);

        List<Carte> carti = bibliotecaCtrl.cautaCarte(carte.getReferenti().get(0));

        assertBooksEquals(carti.get(carti.size() - 1), carte);
    }

    @Test
    public void givenYear2071_whenAddingCarte_carteIsAdded() throws Exception {
        carte.setAnAparitie(2071);
        bibliotecaCtrl.adaugaCarte(carte);

        List<Carte> carti = bibliotecaCtrl.cautaCarte(carte.getReferenti().get(0));

        assertBooksEquals(carti.get(carti.size() - 1), carte);
    }

    @Test
    public void givenYear2070_whenAddingCarte_carteIsAdded() throws Exception {
        carte.setAnAparitie(2070);
        bibliotecaCtrl.adaugaCarte(carte);

        List<Carte> carti = bibliotecaCtrl.cautaCarte(carte.getReferenti().get(0));

        assertBooksEquals(carti.get(carti.size() - 1), carte);
    }

    @Test(expected = Exception.class)
    public void givenYear2072_whenAddingCarte_exceptionIsThrown() throws Exception {
        carte.setAnAparitie(2072);
        bibliotecaCtrl.adaugaCarte(carte);
    }




    public void assertBooksEquals(Carte b1, Carte b2) {
        assertEquals(b1.getTitlu(), b2.getTitlu());
        assertEquals(b1.getEditura(), b2.getEditura());
        assertEquals(b1.getReferenti(), b2.getReferenti());
        assertEquals(b1.getAnAparitie(), b2.getAnAparitie());
        assertEquals(b1.getCuvinteCheie(), b2.getCuvinteCheie());
    }

}
