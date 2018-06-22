package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Formato;
import java.io.Serializable;

public class FormatoDAO<TIPO> extends DAOGenerico<Formato> implements Serializable {

    public FormatoDAO() {
        super();
        classePersistente = Formato.class;
        ordem = "nome";
    }
}