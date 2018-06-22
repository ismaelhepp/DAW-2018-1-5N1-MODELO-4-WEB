package br.edu.ifsul.controle;

import br.edu.ifsul.dao.CatalogoDAO;
import br.edu.ifsul.util.Util;
import br.edu.ifsul.dao.LivrariaDAO;
import br.edu.ifsul.modelo.Livraria;
import br.edu.ifsul.modelo.Catalogo;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Ismael Felipe Hepp
 */
@ManagedBean(name = "controleLivraria")
@ViewScoped
public class ControleLivraria implements Serializable {
    private LivrariaDAO<Livraria> dao;
    private CatalogoDAO<Catalogo> daoCatalogo;
    private Livraria objeto;
    private Catalogo catalogo;
    private Boolean novoCatalogo;

    public ControleLivraria() {
        dao = new LivrariaDAO<>();
    }

    public void novoCatalogo() {
        setCatalogo(new Catalogo());
        setNovoCatalogo((Boolean) true);
    }

    public void alterarCatalogo(int index) {
        setCatalogo(getObjeto().getCatalogos().get(index));
        setNovoCatalogo((Boolean) false);
    }

    public void salvarCatalogo() {
        if (getNovoCatalogo()) {
            getObjeto().adicionarCatalogo(getCatalogo());
        }
        Util.mensagemInformacao("Catálogo atualizado com sucesso!");
    }

    public void removerCatalogo(int index) {
        getObjeto().removerCatalogo(index);
        Util.mensagemInformacao("Catálogo removido com sucesso!");
    }

    public String listar() {
        return "/privado/livraria/listar?faces-redirect=true";
    }

//    public String novo() {
    public void novo() {
        setObjeto(new Livraria());
//        return "formulario?faces-redirect=true";
    }

//    public String salvar() {
    public void salvar() {
        boolean persistiu;

        if (getObjeto().getId() == null) {
            persistiu = getDao().persist(getObjeto());
        } else {
            persistiu = getDao().merge(getObjeto());
        }

        if (persistiu) {
            Util.mensagemInformacao(getDao().getMensagem());
//            return "listar?faces-redirect=true";
        } else {
            Util.mensagemErro(getDao().getMensagem());
//            return "formulario?faces-redirect=true";
        }
    }

//    public String cancelar() {
//        return "listar?faces-redirect=true";
//    }
//    public String editar(Integer id) {
    public void editar(Integer id) {
        setObjeto(getDao().localizar(id));
//        return "formulario?faces-redirect=true";
    }

    public void remover(Integer id) {
        setObjeto(getDao().localizar(id));
        if (getDao().remove(getObjeto())) {
            Util.mensagemInformacao(getDao().getMensagem());
        } else {
            Util.mensagemErro(getDao().getMensagem());
        }
    }

    public LivrariaDAO<Livraria> getDao() {
        return dao;
    }

    public void setDao(LivrariaDAO<Livraria> dao) {
        this.dao = dao;
    }

    public Livraria getObjeto() {
        return objeto;
    }

    public void setObjeto(Livraria objeto) {
        this.objeto = objeto;
    }

    public CatalogoDAO<Catalogo> getDaoCatalogo() {
        return daoCatalogo;
    }

    public void setDaoCatalogo(CatalogoDAO<Catalogo> daoCatalogo) {
        this.daoCatalogo = daoCatalogo;
    }

    public Catalogo getCatalogo() {
        return catalogo;
    }

    public void setCatalogo(Catalogo catalogo) {
        this.catalogo = catalogo;
    }

    public Boolean getNovoCatalogo() {
        return novoCatalogo;
    }

    public void setNovoCatalogo(Boolean novoCatalogo) {
        this.novoCatalogo = novoCatalogo;
    }
}
