package br.edu.ifsul.controle;

import br.edu.ifsul.util.Util;
import br.edu.ifsul.dao.FormatoDAO;
import br.edu.ifsul.modelo.Formato;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean(name = "controleFormato")
@SessionScoped
public class ControleFormato implements Serializable {
    
    private FormatoDAO dao;
    private Formato objeto;
    
    public ControleFormato(){
        dao = new FormatoDAO();
    }
    
    public String listar(){
        return "/privado/formato/listar?faces-redirect=true";
    }
    
    public String novo(){
        objeto = new Formato();
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

    public FormatoDAO getDao() {
        return dao;
    }

    public void setDao(FormatoDAO dao) {
        this.dao = dao;
    }

    public Formato getObjeto() {
        return objeto;
    }

    public void setObjeto(Formato objeto) {
        this.objeto = objeto;
    }
}
