package br.edu.ifsul.controle;

import br.edu.ifsul.dao.AutorDAO;
import br.edu.ifsul.dao.CatalogoDAO;
import br.edu.ifsul.dao.FormatoDAO;
import br.edu.ifsul.dao.IdiomaDAO;
import br.edu.ifsul.util.Util;
import br.edu.ifsul.dao.LivroDAO;
import br.edu.ifsul.modelo.Autor;
import br.edu.ifsul.modelo.Livro;
import br.edu.ifsul.modelo.Idioma;
import br.edu.ifsul.modelo.Catalogo;
import br.edu.ifsul.modelo.Formato;
import br.edu.ifsul.util.UtilRelatorios;

import java.io.Serializable;
import java.util.HashMap;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "controleLivro")
@ViewScoped
public class ControleLivro implements Serializable {

    private LivroDAO<Livro> dao;
    private IdiomaDAO<Idioma> idiomaDAO;
    private FormatoDAO<Formato> formatoDAO;
    private CatalogoDAO<Catalogo> catalogoDAO;
    private Livro objeto;
    private Boolean edicao;
    private AutorDAO<Autor> autorDAO;
    private Autor autor;

    public ControleLivro() {
        dao = new LivroDAO();
        idiomaDAO = new IdiomaDAO();
        formatoDAO = new FormatoDAO();
        catalogoDAO = new CatalogoDAO();
        autorDAO = new AutorDAO<>();
    }

    public void imprimeRelatorioLivros() {
        HashMap hashMap = new HashMap();
        UtilRelatorios.imprimeRelatorio("relatorioLivros", hashMap,
                dao.getListaTodos());
    }

    public void adicionarAutor() {
        if (autor != null) {
            if (!objeto.getAutores().contains(autor)) {
                objeto.getAutores().add(autor);
                Util.mensagemInformacao("Autor adicionado com sucesso!");
            } else {
                Util.mensagemErro("Autor já existe na lista!");
            }
        } else {
            Util.mensagemErro("Selecione um autor!");
        }
    }

    public void removerAutor(int index) {
        Autor obj = objeto.getAutores().get(index);
        objeto.getAutores().remove(obj);
        Util.mensagemInformacao("Autor removido com sucesso!");
    }

    public String listar() {
        return "/privado/livro/listar?faces-redirect=true";
    }

    public void novo() {
        objeto = new Livro();
        this.edicao = false;
    }

    public void salvar() {
        boolean persistiu;

        if (objeto.getIsbn() == null) {
            persistiu = dao.persist(objeto);
        } else {
            persistiu = dao.merge(objeto);
        }

        if (persistiu) {
            Util.mensagemInformacao(dao.getMensagem());
//            return "listar?faces-redirect=true";
        } else {
            Util.mensagemErro(dao.getMensagem());
//            return "formulario?faces-redirect=true";
        }
    }

//    public String cancelar() {
//        return "listar?faces-redirect=true";
//    }
    public void editar(String isbn) {
        objeto = dao.localizar(isbn);
        this.edicao = true;
//        return "formulario?faces-redirect=true";
    }

    public void remover(String isbn) {
        objeto = dao.localizar(isbn);
        if (dao.remove(objeto)) {
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

    public Boolean getEdicao() {
        return edicao;
    }

    public void setEdicao(Boolean edicao) {
        this.edicao = edicao;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public AutorDAO<Autor> getAutorDAO() {
        return autorDAO;
    }

    public void setAutorDAO(AutorDAO<Autor> autorDAO) {
        this.autorDAO = autorDAO;
    }
}
