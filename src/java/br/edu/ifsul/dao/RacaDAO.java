package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Raca;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author Isabela
 */
@Stateful
public class RacaDAO<T> extends DAOGenerico<Raca> implements Serializable {

    public RacaDAO() {
        super();
        super.classePersistente = Raca.class;
    }
}
