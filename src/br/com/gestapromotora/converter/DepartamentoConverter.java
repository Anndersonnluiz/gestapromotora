package br.com.gestapromotora.converter;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.gestapromotora.model.Departamento;


@FacesConverter(value = "DepartamentoConverter")
public class DepartamentoConverter implements Converter{

	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		@SuppressWarnings("unchecked")
		List<Departamento> listaDepartamento = (List<Departamento>) arg1.getAttributes().get("listaDepartamento");
	    if (listaDepartamento != null) {
	        for (Departamento departamento : listaDepartamento) {
	            if (departamento.getDescricao().equalsIgnoreCase(arg2)) {
	                return departamento;
	            }
	        }
	    } else {
	    	Departamento departamento = new Departamento();
	        return departamento;
	    }
	    Departamento departamento = new Departamento();
	    return departamento;
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		if (arg2.toString().equalsIgnoreCase("0")) {
	        return "Selecione";
	    } else {
	    	Departamento departamento = (Departamento) arg2;
	        return departamento.getDescricao();
	    }
	}
}
