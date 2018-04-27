package br.edu.ifsul.controle;

import br.edu.ifsul.util.Util;
import br.edu.ifsul.dao.AutorDAO;
import br.edu.ifsul.modelo.Autor;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean(name = "controleAutor")
@SessionScoped
public class ControleAutor implements Serializable {
    
    private AutorDAO dao;
    private Autor objeto;
    
    public ControleAutor(){
        dao = new AutorDAO();
    }
    
    public String listar(){
        return "/privado/autor/listar?faces-redirect=true";
    }
    
    public String novo(){
        objeto = new Autor();
        return "formulario?faces-redirect=true";
    }
    
    public String salvar(){
        if(dao.salvar(objeto)){
            Util.mensagemInformacao(dao.getMensagem());
            return "listar?faces-redirect=true";
        } else {
            Util.mensagemErro(dao.getMensagem());
            return "formulario?faces-redirect=true";
        }
    }
    
    public String cancelar(){
        return "listar?faces-redirect=true";
    }
    
    public String editar(Integer id){
        objeto = dao.localizar(id);
        return "formulario?faces-redirect=true";
    }
    
    public void remover(Integer id){
        objeto = dao.localizar(id);
        if (dao.remover(objeto)){
            Util.mensagemInformacao(dao.getMensagem());
        } else {
            Util.mensagemErro(dao.getMensagem());
        }
    }

    public AutorDAO getDao() {
        return dao;
    }

    public void setDao(AutorDAO dao) {
        this.dao = dao;
    }

    public Autor getObjeto() {
        return objeto;
    }

    public void setObjeto(Autor objeto) {
        this.objeto = objeto;
    }
}
