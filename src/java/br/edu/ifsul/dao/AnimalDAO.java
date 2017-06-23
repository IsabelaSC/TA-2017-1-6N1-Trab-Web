package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Animal;
import java.io.Serializable;
import javax.ejb.Stateful;
import javax.persistence.Query;

/**
 *
 * @author Isabela
 */
@Stateful
public class AnimalDAO<T> extends DAOGenerico<Animal> implements Serializable {

    public AnimalDAO() {
        super();
        super.classePersistente = Animal.class;
    }

    public T getObjectById(String id) throws Exception {
        return (T) em.find(classePersistente, id);
    }

    public Animal localizaAnimalPorIdade(String idade) {
        Query query = em.createQuery("from Animal where upper(animal) = :idade");
        query.setParameter("animal", idade.toUpperCase());
        Animal obj = (Animal) query.getSingleResult();
        return obj;
    }

}
