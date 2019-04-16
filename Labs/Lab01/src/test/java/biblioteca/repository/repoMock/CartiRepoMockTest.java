package biblioteca.repository.repoMock;

import biblioteca.model.Carte;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class CartiRepoMockTest {

    CartiRepoMock repoMock = null;
    private Carte carte = new Carte();

    @Before
    public void init(){
        carte.setTitlu("Titul");
        carte.setAnAparitie(1997);
        carte.setReferenti(Collections.singletonList("Autor"));
        carte.setEditura("Humanitas");
        carte.setCuvinteCheie(Collections.singletonList("cuvant cheie"));

        repoMock = new CartiRepoMock();
    }

    //F02
    @Test
    public void givenExistentAutor_whenRetrievingCarti_thenOk() throws Exception {
        repoMock.adaugaCarte(carte);

        List<Carte> carti = repoMock.cautaCarte("Autor");

        assertBooksEquals(carti.get(0),carte);
    }

    @Test
    public void givenInexistentCarti_whenRetrievingCarti_thenNoCarti() throws Exception {
        List<Carte> carti = repoMock.cautaCarte("Autor");
        assertEquals(carti.size(),0);
    }

    @Test
    public void givenInexistentAutor_whenRetrievingCarti_thenNoCarti() throws Exception {
        repoMock.adaugaCarte(carte);

        List<Carte> carti = repoMock.cautaCarte("Autor2");

        assertEquals(carti.size(),0);
    }

    @Test
    public void givenCarteWithNullAutor_whenRetrievingCarti_thenNoCarti() throws Exception {
        carte.setReferenti(new ArrayList<String>());
        repoMock.adaugaCarte(carte);

        List<Carte> carti = repoMock.cautaCarte("Autor");

        assertEquals(carti.size(),0);
    }

    public void assertBooksEquals(Carte b1, Carte b2) {
        assertEquals(b1.getTitlu(), b2.getTitlu());
        assertEquals(b1.getEditura(), b2.getEditura());
        assertEquals(b1.getReferenti(), b2.getReferenti());
        assertEquals(b1.getAnAparitie(), b2.getAnAparitie());
        assertEquals(b1.getCuvinteCheie(), b2.getCuvinteCheie());
    }

}