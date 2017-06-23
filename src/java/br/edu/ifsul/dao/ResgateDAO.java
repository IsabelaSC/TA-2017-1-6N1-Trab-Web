package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Resgate;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author Isabela
 */
@Stateful
public class ResgateDAO<T> extends DAOGenerico<Resgate> implements Serializable {

    public ResgateDAO() {
        super();
        super.setClassePersistente(Resgate.class);
        super.setOrdem("data");
    }

    @Override
    public Resgate getObjectById(Integer id) throws Exception {
        Resgate obj = (Resgate) super.getEm().find(super.getClassePersistente(), id);
        obj.getProcedimentos().size();
        return obj;
    }

}
