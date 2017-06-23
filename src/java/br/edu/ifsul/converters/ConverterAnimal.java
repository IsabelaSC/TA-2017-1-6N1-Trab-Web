package br.edu.ifsul.converters;

import br.edu.ifsul.modelo.Animal;
import java.io.Serializable;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Isabela
 */
@FacesConverter(value = "converterAnimal")
public class ConverterAnimal implements Serializable, Converter {

    @PersistenceContext(unitName = "TA-2017-1-6N1-Trab-WebPU")
    private EntityManager em;

    // converte da tela para o objeto
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string == null || string.equals("Selecione um registro")) {
            return null;
        }
        return em.find(Animal.class, Integer.parseInt(string));
    }

    // converte do objeto para a tela
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o == null) {
            return null;
        }
        Animal obj = (Animal) o;
        return obj.getId().toString();
    }

}
