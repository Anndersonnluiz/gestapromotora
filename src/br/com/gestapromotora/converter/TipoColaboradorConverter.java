package br.com.gestapromotora.converter;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.gestapromotora.model.Tipocolaborador;

@FacesConverter(value = "TipoColaboradorConverter")
public class TipoColaboradorConverter implements Converter{

	
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		@SuppressWarnings("unchecked")
		List<Tipocolaborador> listaTipoColaborador = (List<Tipocolaborador>) arg1.getAttributes().get("listaTipoColaborador");
	    if (listaTipoColaborador != null) {
	        for (Tipocolaborador tipocolaborador : listaTipoColaborador) {
	            if (tipocolaborador.getDescricao().equalsIgnoreCase(arg2)) {
	                return tipocolaborador;
	            }
	        }
	    } else {
	    	Tipocolaborador tipocolaborador = new Tipocolaborador();
	        return tipocolaborador;
	    }
	    Tipocolaborador tipocolaborador = new Tipocolaborador();
	    return tipocolaborador;
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		if (arg2.toString().equalsIgnoreCase("0")) {
	        return "Selecione";
	    } else {
	    	Tipocolaborador tipocolaborador = (Tipocolaborador) arg2;
	        return tipocolaborador.getDescricao();
	    }
	}

}
