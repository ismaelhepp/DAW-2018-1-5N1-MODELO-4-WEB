package br.edu.ifsul.dao;

import java.io.Serializable;
import br.edu.ifsul.modelo.Livraria;

/**
 *
 * @author Ismael Felipe Hepp
 */
public class LivrariaDAO<TIPO> extends DAOGenerico<Livraria> implements Serializable {

    public LivrariaDAO() {
        super();
        classePersistente = Livraria.class;
        ordem = "nome";
    }
}
