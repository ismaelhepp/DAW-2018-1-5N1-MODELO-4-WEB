package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Autor;
import java.io.Serializable;


public class AutorDAO<TIPO> extends DAOGenerico<Autor> implements Serializable {

    public AutorDAO() {
        super();
        classePersistente = Autor.class;
        ordem = "nome";
    }
}