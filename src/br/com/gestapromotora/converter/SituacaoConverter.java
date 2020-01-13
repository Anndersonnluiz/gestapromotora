package br.com.gestapromotora.converter;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.gestapromotora.model.Situacao;

@FacesConverter(value = "SituacaoConverter")
public class SituacaoConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		@SuppressWarnings("unchecked")
		List<Situacao> listaSituacao = (List<Situacao>) arg1.getAttributes().get("listaSituacao");
	    if (listaSituacao != null) {
	        for (Situacao Situacao : listaSituacao) {
	            if (Situacao.getDescricao().equalsIgnoreCase(arg2)) {
	                return Situacao;
	            }
	        } 
	    } else {
	    	Situacao Situacao = new Situacao();
	        return Situacao;
	    }
	    Situacao Situacao = new Situacao();
	    return Situacao;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		if (arg2.toString().equalsIgnoreCase("0")) {
	        return "Selecione";
	    } else {
	    	Situacao Situacao = (Situacao) arg2;
	        return Situacao.getDescricao();
	    }
	}
}
