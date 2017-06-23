package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Responsavel;
import java.io.Serializable;
import javax.ejb.Stateful;
import javax.persistence.Query;

/**
 *
 * @author Isabela
 */
@Stateful
public class ResponsavelDAO<T> extends DAOGenerico<Responsavel> implements Serializable {

    public ResponsavelDAO() {
        super();
        super.classePersistente = Responsavel.class;
    }

    @Override
    public Responsavel getObjectById(Integer id) throws Exception {
        Responsavel obj = (Responsavel) em.find(classePersistente, id);
        /// inicializando a lista para não lazy inicialization exception
        obj.getResponsaveisResgates().size();
        return obj;
    }

    public Responsavel localizaPorNomeResponsavel(String nome) {
        Query query = em.createQuery("from Responsavel where upper(responsavel) = :nome");
        query.setParameter("nome", nome.toUpperCase());
        Responsavel obj = (Responsavel) query.getSingleResult();
        obj.getResponsaveisResgates().size();
        return obj;
    }

}
