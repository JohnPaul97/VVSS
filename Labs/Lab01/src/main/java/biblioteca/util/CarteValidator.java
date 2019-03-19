package biblioteca.util;

import biblioteca.model.Carte;

public class CarteValidator {

    private static final String TITLE_REGEX = "[a-zA-Z0-9]+";
    private static final String STRING_REGEX = "[a-zA-Z]+";
    private static final String NUMBER_REGEX = "0-9|[1-9][0-9]+";

    public static void validateCarte(Carte c) throws Exception {
        if (c.getCuvinteCheie() == null) {
            throw new Exception("Lista cuvinte cheie vida!");
        }
        if (c.getReferenti() == null) {
            throw new Exception("Lista autori vida!");
        }
        if (!isValidTitle(c.getTitlu())) {
            throw new Exception("Titlu invalid!");
        }
        for (String s : c.getReferenti()) {
            if (!isOKString(s)) {
                throw new Exception("Autor invalid!");
            }
        }
        for (String s : c.getCuvinteCheie()) {
            if (!isOKString(s)) {
                throw new Exception("Cuvant cheie invalid!");
            }
        }
        if (!isOKString(c.getEditura())) {
            throw new Exception("Editura invalida!");
        }
    }

	public static boolean isNumber(String s){
		return s.matches(NUMBER_REGEX);
	}

    public static boolean isOKString(String s){
        String[] infos = s.split(" ");

        for(String info: infos)
            if(!info.matches(STRING_REGEX))
                return false;

        return true;
    }

    public static boolean isValidTitle(String title) {
        String[] infos = title.split(" ");

        for(String info: infos)
            if(!info.matches(TITLE_REGEX))
                return false;

        return true;
    }

}
