package br.edu.ifsul.controle;

import br.edu.ifsul.dao.RacaDAO;
import br.edu.ifsul.modelo.Raca;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;

import javax.inject.Named;

/**
 *
 * @author Isabela
 */
@Named(value = "controleRaca")
@SessionScoped
public class ControleRaca implements Serializable {

    @EJB
    private RacaDAO<Raca> dao;
    private Raca objeto;
    private Boolean editando;

    public ControleRaca() {
        editando = false;
    }

    public String listar() {
        editando = false;
        return "/privado/raca/listar?faces-redirect=true";
    }

    public void novo() {
        editando = true;
        objeto = new Raca();
    }

    public void alterar(Integer id) {
        try {
            objeto = dao.getObjectById(id);
            editando = true;
        } catch (Exception e) {
            Util.mensagemErro("Erro ao recuperar objeto: " + Util.getMensagemErro(e));
        }

    }

    public void excluir(Integer id) {
        try {
            objeto = dao.getObjectById(id);
            dao.remove(objeto);
            Util.mensagemInformacao("Objeto removido com sucesso!");
        } catch (Exception e) {
            Util.mensagemErro("Erro a remover objeto: " + Util.getMensagemErro(e));
        }
    }

    public void salvar() {
        try {
            if (objeto.getId() == null) {
                dao.persist(objeto);
            } else {
                dao.merge(objeto);
            }
            Util.mensagemInformacao("Sucesso ao persistir objeto");
            editando = false;
        } catch (Exception e) {
            Util.mensagemErro("Erro ao persistir: " + Util.getMensagemErro(e));
        }
    }

    public Raca getObjeto() {
        return objeto;
    }

    public void setObjeto(Raca objeto) {
        this.objeto = objeto;
    }

    public Boolean getEditando() {
        return editando;
    }

    public void setEditando(Boolean editando) {
        this.editando = editando;
    }

    public RacaDAO<Raca> getDao() {
        return dao;
    }

    public void setDao(RacaDAO<Raca> dao) {
        this.dao = dao;
    }
}
