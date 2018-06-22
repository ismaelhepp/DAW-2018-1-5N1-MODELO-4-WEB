package br.edu.ifsul.controle;

import br.edu.ifsul.util.Util;
import br.edu.ifsul.dao.CatalogoDAO;
import br.edu.ifsul.dao.LivrariaDAO;
import br.edu.ifsul.modelo.Catalogo;
import br.edu.ifsul.modelo.Livraria;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Ismael Felipe Hepp
 */
@ManagedBean(name = "controleCatalogo")
@ViewScoped
public class ControleCatalogo implements Serializable {
    private CatalogoDAO<Catalogo> dao;
    private LivrariaDAO<Livraria> daoLivraria;
    private Catalogo objeto;

    public ControleCatalogo() {
        dao = new CatalogoDAO<>();
        daoLivraria = new LivrariaDAO<>();
    }

    public String listar() {
        return "/privado/catalogo/listar?faces-redirect=true";
    }

    public void novo() {
        setObjeto(new Catalogo());
    }

    public void salvar() {
        boolean persistiu;

        if (getObjeto().getId() == null) {
            persistiu = getDao().persist(getObjeto());
        } else {
            persistiu = getDao().merge(getObjeto());
        }

        if (persistiu) {
            Util.mensagemInformacao(getDao().getMensagem());
        } else {
            Util.mensagemErro(getDao().getMensagem());
        }
    }

    public void editar(Integer id) {
        setObjeto(getDao().localizar(id));
    }

    public void remover(Integer id) {
        setObjeto(getDao().localizar(id));
        if (getDao().remove(getObjeto())) {
            Util.mensagemInformacao(getDao().getMensagem());
        } else {
            Util.mensagemErro(getDao().getMensagem());
        }
    }

    public CatalogoDAO<Catalogo> getDao() {
        return dao;
    }

    public void setDao(CatalogoDAO<Catalogo> dao) {
        this.dao = dao;
    }

    public Catalogo getObjeto() {
        return objeto;
    }

    public void setObjeto(Catalogo objeto) {
        this.objeto = objeto;
    }

    public LivrariaDAO<Livraria> getDaoLivraria() {
        return daoLivraria;
    }

    public void setDaoLivraria(LivrariaDAO<Livraria> daoLivraria) {
        this.daoLivraria = daoLivraria;
    }
}