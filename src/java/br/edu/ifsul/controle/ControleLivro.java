package br.edu.ifsul.controle;

import br.edu.ifsul.dao.CatalogoDAO;
import br.edu.ifsul.dao.FormatoDAO;
import br.edu.ifsul.dao.IdiomaDAO;
import br.edu.ifsul.util.Util;
import br.edu.ifsul.dao.LivroDAO;
import br.edu.ifsul.modelo.Livro;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "controleLivro")
@SessionScoped
public class ControleLivro implements Serializable {

    private LivroDAO dao;
    private IdiomaDAO idiomaDAO;
    private FormatoDAO formatoDAO;
    private CatalogoDAO catalogoDAO;
    private Livro objeto;

    public ControleLivro() {
        dao = new LivroDAO();
        idiomaDAO = new IdiomaDAO();
        formatoDAO = new FormatoDAO();
        catalogoDAO = new CatalogoDAO();
    }

    public String listar() {
        return "/privado/livro/listar?faces-redirect=true";
    }

    public String novo() {
        objeto = new Livro();
        return "formulario?faces-redirect=true";
    }

    public String salvar() {
        if (dao.salvar(objeto)) {
            Util.mensagemInformacao(dao.getMensagem());
            return "listar?faces-redirect=true";
        } else {
            Util.mensagemErro(dao.getMensagem());
            return "formulario?faces-redirect=true";
        }
    }

    public String cancelar() {
        return "listar?faces-redirect=true";
    }

    public String editar(String isbn) {
        objeto = dao.localizar(isbn);
        return "formulario?faces-redirect=true";
    }

    public void remover(String isbn) {
        objeto = dao.localizar(isbn);
        if (dao.remover(objeto)) {
            Util.mensagemInformacao(dao.getMensagem());
        } else {
            Util.mensagemErro(dao.getMensagem());
        }
    }

    public LivroDAO getDao() {
        return dao;
    }

    public void setDao(LivroDAO dao) {
        this.dao = dao;
    }

    public Livro getObjeto() {
        return objeto;
    }

    public void setObjeto(Livro objeto) {
        this.objeto = objeto;
    }

    public IdiomaDAO getIdiomaDAO() {
        return idiomaDAO;
    }

    public void setIdiomaDAO(IdiomaDAO idiomaDAO) {
        this.idiomaDAO = idiomaDAO;
    }

    public FormatoDAO getFormatoDAO() {
        return formatoDAO;
    }

    public void setFormatoDAO(FormatoDAO formatoDAO) {
        this.formatoDAO = formatoDAO;
    }

    public CatalogoDAO getCatalogoDAO() {
        return catalogoDAO;
    }

    public void setCatalogoDAO(CatalogoDAO catalogoDAO) {
        this.catalogoDAO = catalogoDAO;
    }
}
