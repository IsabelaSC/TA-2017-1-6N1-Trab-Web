package br.edu.ifsul.controle;

import br.edu.ifsul.dao.ResgateDAO;
import br.edu.ifsul.dao.ResponsavelDAO;
import br.edu.ifsul.modelo.Resgate;
import br.edu.ifsul.modelo.Responsavel;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;

import javax.inject.Named;

/**
 *
 * @author Isabela
 */
@Named(value = "controleResponsavel")
@SessionScoped
public class ControleResponsavel implements Serializable {

    @EJB
    private ResponsavelDAO<Responsavel> dao;
    private Responsavel objeto;
    @EJB
    private ResgateDAO<Resgate> daoResgate;
    private Resgate resgate;
    private Boolean editandoResgate;

    public ControleResponsavel() {
        editandoResgate = false;
    }

    public String listar() {
        return "/privado/responsavel/listar?faces-redirect=true";
    }

    public void novo() {
        objeto = new Responsavel();
        editandoResgate = false;
    }

    public void alterar(Integer id) {
        try {
            objeto = dao.getObjectById(id);
            editandoResgate = false;
        } catch (Exception e) {
            Util.mensagemErro("Erro ao recuperar objeto: "
                    + Util.getMensagemErro(e));
        }
    }

    public void excluir(Integer id) {
        try {
            objeto = dao.getObjectById(id);
            dao.remove(objeto);
            Util.mensagemInformacao("Objeto removido com sucesso!");

        } catch (Exception e) {
            Util.mensagemErro("Erro ao remover objeto: "
                    + Util.getMensagemErro(e));
        }
    }

    public void salvar() {
        try {
            if (objeto.getId() == null) {
                dao.persist(objeto);
            } else {
                dao.merge(objeto);
            }
            Util.mensagemInformacao("Objeto persistido com sucesso!");
        } catch (Exception e) {
            Util.mensagemErro("Erro ao persistir objeto: "
                    + Util.getMensagemErro(e));
        }
    }

//----------------********************-------------------------
    public void novoResgate() {
        editandoResgate = true;
    }

    public void salvarResgate() {
        if (!objeto.getResponsaveisResgates().contains(resgate)) {
            objeto.getResponsaveisResgates().add(resgate);
            Util.mensagemInformacao("Resgate adicionado com sucesso!");
        } else {
            Util.mensagemErro("Este resgate já contem este responsavel!");
        }
        editandoResgate = false;
    }

    public void removerResgate(Resgate obj) {
        objeto.getResponsaveisResgates().remove(obj);
        Util.mensagemInformacao("Resgate removida com sucesso!");
    }
    //-------------------*****************--------------------------

    public ResponsavelDAO<Responsavel> getDao() {
        return dao;
    }

    public void setDao(ResponsavelDAO<Responsavel> dao) {
        this.dao = dao;
    }

    public Responsavel getObjeto() {
        return objeto;
    }

    public void setObjeto(Responsavel objeto) {
        this.objeto = objeto;
    }

    public ResgateDAO<Resgate> getDaoResgate() {
        return daoResgate;
    }

    public void setDaoResgate(ResgateDAO<Resgate> daoResgate) {
        this.daoResgate = daoResgate;
    }

    public Resgate getResgate() {
        return resgate;
    }

    public void setResgate(Resgate resgate) {
        this.resgate = resgate;
    }

    public Boolean getEditandoResgate() {
        return editandoResgate;
    }

    public void setEditandoResgate(Boolean editandoResgate) {
        this.editandoResgate = editandoResgate;
    }

}
