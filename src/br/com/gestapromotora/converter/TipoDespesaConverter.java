package br.com.gestapromotora.converter;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.gestapromotora.model.Tipodespesa;

@FacesConverter(value = "TipoDespesaConverter")
public class TipoDespesaConverter implements Converter{

	
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		@SuppressWarnings("unchecked")
		List<Tipodespesa> listaTipoDespesa = (List<Tipodespesa>) arg1.getAttributes().get("listaTipoDespesa");
	    if (listaTipoDespesa != null) {
	        for (Tipodespesa tipodespesa : listaTipoDespesa) {
	            if (tipodespesa.getDescricao().equalsIgnoreCase(arg2)) {
	                return tipodespesa;
	            }
	        }
	    } else {
	    	Tipodespesa tipodespesa = new Tipodespesa();
	        return tipodespesa;
	    }
	    Tipodespesa tipodespesa = new Tipodespesa();
	    return tipodespesa;
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		if (arg2.toString().equalsIgnoreCase("0")) {
	        return "Selecione";
	    } else {
	    	Tipodespesa tipodespesa = (Tipodespesa) arg2;
	        return tipodespesa.getDescricao();
	    }
	}
}
