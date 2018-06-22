package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Catalogo;
import java.io.Serializable;

public class CatalogoDAO<TIPO> extends DAOGenerico<Catalogo> implements Serializable {
    
    public CatalogoDAO() {
        super();
        classePersistente = Catalogo.class;
        ordem = "nome";
    }
}