
package br.edu.ifsul.converters;

import java.io.Serializable;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Isabela
 */
@FacesConverter(value = "converterSimNao")
public class ConverterSimNao implements Converter, Serializable{

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        return string.equals("Sim");
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        Boolean b = (Boolean) o;
        return b ? "Sim" : "Não";
    }

}
