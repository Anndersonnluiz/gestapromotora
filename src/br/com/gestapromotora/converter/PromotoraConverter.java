package br.com.gestapromotora.converter;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.gestapromotora.model.Promotora;


@FacesConverter(value = "PromotoraConverter")
public class PromotoraConverter implements Converter{

	
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		@SuppressWarnings("unchecked")
		List<Promotora> listaPromotora = (List<Promotora>) arg1.getAttributes().get("listaPromotora");
	    if (listaPromotora != null) {
	        for (Promotora promotora : listaPromotora) {
	            if (promotora.getDescricao().equalsIgnoreCase(arg2)) {
	                return promotora;
	            }
	        }
	    } else {
	    	Promotora promotora = new Promotora();
	        return promotora;
	    }
	    Promotora promotora = new Promotora();
	    return promotora;
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		if (arg2.toString().equalsIgnoreCase("0")) {
	        return "Selecione";
	    } else {
	    	Promotora promotora = (Promotora) arg2;
	        return promotora.getDescricao();
	    }
	}
}
