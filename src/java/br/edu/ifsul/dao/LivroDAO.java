package br.edu.ifsul.dao;

import br.edu.ifsul.jpa.EntityManagerUtil;
import br.edu.ifsul.modelo.Livro;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;

public class LivroDAO implements Serializable {

    private String mensagem = "";
    private EntityManager em;
    
    public LivroDAO(){
        em = EntityManagerUtil.getEntityManager();
    }
    
    public List<Livro> getLista(){
        return em.createQuery("from Livro order by titulo").getResultList();
    }
    
    public boolean salvar(Livro obj){
        try {
            em.getTransaction().begin();
            if(obj.getIsbn()== null){
                em.persist(obj);
            } else {
                em.merge(obj);
            }
            em.getTransaction().commit();
            mensagem = "Objeto foi persistido com sucesso!";
            return true;
        } catch (Exception e){
            if (em.getTransaction().isActive() == false){
                em.getTransaction().begin();
            }
            em.getTransaction().rollback();
            mensagem = "Erro ao persistir objeto: " +
                    Util.getMensagemErro(e);
            return false;
        }
    }
    
    public boolean remover(Livro obj){
        try {
            em.getTransaction().begin();
            em.remove(obj);
            em.getTransaction().commit();
            mensagem = "Objeto removido com sucesso!";
            return true;
        } catch (Exception e){
            if (em.getTransaction().isActive() == false){
                em.getTransaction().begin();
            }
            em.getTransaction().rollback();
            mensagem = "Erro ao remover objeto: " +
                    Util.getMensagemErro(e);
            return false;
        }
    }   
    
    public Livro localizar(Object id){
        return em.find(Livro.class, id);
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
}
