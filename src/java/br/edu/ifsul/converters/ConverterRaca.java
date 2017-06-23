package br.edu.ifsul.converters;

import br.edu.ifsul.modelo.Raca;
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
@FacesConverter(value = "converterRaca")
public class ConverterRaca implements Converter, Serializable {
    
    @PersistenceContext(unitName = "TA-2017-1-6N1-Trab-WebPU")
    private EntityManager em;    

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string == null || string.equals("Selecione um registro")){
            return null;
        }
        return em.find(Raca.class, Integer.parseInt(string));
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o == null){
            return null;
        }
        Raca obj = (Raca) o;
        return obj.getId().toString();
    }
    

}
