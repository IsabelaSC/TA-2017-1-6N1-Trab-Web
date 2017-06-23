package br.edu.ifsul.controle;

import br.edu.ifsul.dao.AnimalDAO;
import br.edu.ifsul.dao.RacaDAO;
import br.edu.ifsul.modelo.Animal;
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
@Named(value = "controleAnimal")
@SessionScoped
public class ControleAnimal implements Serializable {

    @EJB
    private AnimalDAO<Animal> dao;
    private Animal objeto;
    @EJB
    private RacaDAO<Raca> daoRaca;
    private Boolean editando;
//    @EJB
//    private ResgateDAO<Resgate> daoResgate;
//    private Resgate permissao;
//    private Boolean editandoResgate;

    public ControleAnimal() {
        editando = false;
//        editandoResgate = false;
    }

    public String listar() {
        editando = false;
        return "/privado/animal/listar?faces-redirect=true";
    }

    public void novo() {
        objeto = new Animal();
        editando = true;
//        editandoResgate = false;
    }

    public void alterar(Integer id) {
        try {
            objeto = dao.getObjectById(id);
            editando = true;
//            editandoResgate = false;
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
            editando = false;
        } catch (Exception e) {
            Util.mensagemErro("Erro ao persistir objeto: "
                    + Util.getMensagemErro(e));
        }
    }
//    
//    public void novaResgate() {
//        editandoResgate = true;
//    }

//    public void salvarResgate() {
//        if (!objeto.getPermissoes().contains(permissao)) {
//            objeto.getPermissoes().add(permissao);
//            Util.mensagemInformacao("Permissão adicionada com sucesso!");
//        } else {
//            Util.mensagemErro("Usuário já possui esta permissão!");
//        }
//        editandoResgate = false;
//    }
//    public void removerResgate(Resgate obj) {
//        objeto.getPermissoes().remove(obj);
//        Util.mensagemInformacao("Permissão removida com sucesso!");
//    }
    public AnimalDAO<Animal> getDao() {
        return dao;
    }

    public void setDao(AnimalDAO<Animal> dao) {
        this.dao = dao;
    }

    public Animal getObjeto() {
        return objeto;
    }

    public void setObjeto(Animal objeto) {
        this.objeto = objeto;
    }

    public RacaDAO<Raca> getDaoRaca() {
        return daoRaca;
    }

    public void setDaoRaca(RacaDAO<Raca> daoRaca) {
        this.daoRaca = daoRaca;
    }

    public Boolean getEditando() {
        return editando;
    }

    public void setEditando(Boolean editando) {
        this.editando = editando;
    }

//    public ResgateDAO<Resgate> getDaoResgate() {
//        return daoResgate;
//    }
//    
//    public void setDaoResgate(ResgateDAO<Resgate> daoResgate) {
//        this.daoResgate = daoResgate;
//    }
//    public Resgate getResgate() {
//        return permissao;
//    }
//    public void setResgate(Resgate permissao) {
//        this.permissao = permissao;
//    }
//    public Boolean getEditandoResgate() {
//        return editandoResgate;
//    }
//    
//    public void setEditandoResgate(Boolean editandoResgate) {
//        this.editandoResgate = editandoResgate;
//    }
}
