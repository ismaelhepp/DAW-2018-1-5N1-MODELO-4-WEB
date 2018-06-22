package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Idioma;
import java.io.Serializable;

public class IdiomaDAO<TIPO> extends DAOGenerico<Idioma> implements Serializable {
    public IdiomaDAO() {
        super();
        classePersistente = Idioma.class;
        ordem = "nome";
    }
}