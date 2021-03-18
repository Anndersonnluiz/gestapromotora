package br.com.deltafinanceira.converter;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.deltafinanceira.model.Coeficiente;

@FacesConverter(value = "CoeficienteConverter")
public class CoeficienteConverter implements Converter{

	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		@SuppressWarnings("unchecked")
		List<Coeficiente> listaCoeficiente = (List<Coeficiente>) arg1.getAttributes().get("listaCoeficiente");
	    if (listaCoeficiente != null) {
	        for (Coeficiente coeficiente : listaCoeficiente) {
	            if (coeficiente.getNometabela().equalsIgnoreCase(arg2)) {
	                return coeficiente;
	            }
	        }
	    } else {
	    	Coeficiente coeficiente = new Coeficiente();
	        return coeficiente;
	    }
	    Coeficiente coeficiente = new Coeficiente();
	    return coeficiente;
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		if (arg2.toString().equalsIgnoreCase("0")) {
	        return "Selecione";
	    } else {
	    	Coeficiente coeficiente = (Coeficiente) arg2;
	        return coeficiente.getNometabela();
	    }
	}
}
