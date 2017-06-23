package br.edu.ifsul.controle;

import br.edu.ifsul.dao.AnimalDAO;
import br.edu.ifsul.dao.ResgateDAO;
import br.edu.ifsul.modelo.Animal;
import br.edu.ifsul.modelo.ProcedimentoPosResgate;
import br.edu.ifsul.modelo.Resgate;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;

import javax.inject.Named;

/**
 *
 * @author Isabela
 */
@Named(value = "controleResgate")
@SessionScoped
public class ControleResgate implements Serializable {

    @EJB
    private ResgateDAO<Resgate> dao;
    private Resgate objeto;
    private Boolean editando;
    @EJB
    private AnimalDAO<Animal> daoAnimal;
    @EJB
//    private UsuarioDAO<Usuario> daoUsuario;
    private Boolean editandoProcedimentoPosResgate;
    private ProcedimentoPosResgate procedimentoPosResgate;
    private Boolean novoProcedimentoPosResgate;

    public ControleResgate() {
        editando = false;
        editandoProcedimentoPosResgate = false;
    }

    public String listar() {
        editando = false;
        return "/privado/resgate/listar?faces-redirect=true";
    }

    public void novo() {
        editando = true;
        editandoProcedimentoPosResgate = false;
        objeto = new Resgate();
    }

    public void alterar(Integer id) {
        try {
            objeto = dao.getObjectById(id);
            editando = true;
            editandoProcedimentoPosResgate = false;
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
            editandoProcedimentoPosResgate = false;
        } catch (Exception e) {
            Util.mensagemErro("Erro ao persistir: " + Util.getMensagemErro(e));
        }
    }

    public void novoProcedimentoPosResgate() {
        procedimentoPosResgate = new ProcedimentoPosResgate();
        editandoProcedimentoPosResgate = true;
        novoProcedimentoPosResgate = true;
    }

    public void salvarProcedimentoPosResgate() {
        if (procedimentoPosResgate.getId() == null) {
            if (novoProcedimentoPosResgate) {
                objeto.adicionarProcedimento(procedimentoPosResgate);
            }
        }
        editandoProcedimentoPosResgate = false;
        Util.mensagemInformacao("Procedimento  persistido com sucesso!");
    }

    public void alterarProcedimento(int index) {
        procedimentoPosResgate = objeto.getProcedimentos().get(index);
        editandoProcedimentoPosResgate = true;
        novoProcedimentoPosResgate = false;
    }

    public void excluirProcedimentoPosResgate(int index) {
        objeto.removerProcedimento(index);
        Util.mensagemInformacao("Procedimento removido com sucesso!");
    }

    public Resgate getObjeto() {
        return objeto;
    }

    public void setObjeto(Resgate objeto) {
        this.objeto = objeto;
    }

    public Boolean getEditando() {
        return editando;
    }

    public void setEditando(Boolean editando) {
        this.editando = editando;
    }

    public ResgateDAO<Resgate> getDao() {
        return dao;
    }

    public void setDao(ResgateDAO<Resgate> dao) {
        this.dao = dao;
    }

    public AnimalDAO<Animal> getDaoAnimal() {
        return daoAnimal;
    }

    public void setDaoAnimal(AnimalDAO<Animal> daoAnimal) {
        this.daoAnimal = daoAnimal;
    }

//    public UsuarioDAO<Usuario> getDaoUsuario() {
//        return daoUsuario;
//    }
//
//    public void setDaoUsuario(UsuarioDAO<Usuario> daoUsuario) {
//        this.daoUsuario = daoUsuario;
//    }

    public Boolean getEditandoProcedimentoPosResgate() {
        return editandoProcedimentoPosResgate;
    }

    public void setEditandoProcedimentoPosResgate(Boolean editandoProcedimentoPosResgate) {
        this.editandoProcedimentoPosResgate = editandoProcedimentoPosResgate;
    }

    public ProcedimentoPosResgate getProcedimentoPosResgate() {
        return procedimentoPosResgate;
    }

    public void setProcedimentoPosResgate(ProcedimentoPosResgate procedimentoPosResgate) {
        this.procedimentoPosResgate = procedimentoPosResgate;
    }

    public Boolean getNovoProcedimentoPosResgate() {
        return novoProcedimentoPosResgate;
    }

    public void setNovoProcedimentoPosResgate(Boolean novoProcedimentoPosResgate) {
        this.novoProcedimentoPosResgate = novoProcedimentoPosResgate;
    }
}
