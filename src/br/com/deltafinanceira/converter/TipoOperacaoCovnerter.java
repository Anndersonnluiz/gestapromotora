package br.com.deltafinanceira.converter;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.deltafinanceira.model.Tipooperacao;

@FacesConverter(value = "TipoOperacaoConverter")
public class TipoOperacaoCovnerter implements Converter{

	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		@SuppressWarnings("unchecked")
		List<Tipooperacao> listaBanco = (List<Tipooperacao>) arg1.getAttributes().get("listaTipoOperacao");
	    if (listaBanco != null) {
	        for (Tipooperacao tipooperacao : listaBanco) {
	            if (tipooperacao.getDescricao().equalsIgnoreCase(arg2)) {
	                return tipooperacao;
	            }
	        }
	    } else {
	    	Tipooperacao tipooperacao = new Tipooperacao();
	        return tipooperacao;
	    }
	    Tipooperacao banco = new Tipooperacao();
	    return banco;
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		if (arg2.toString().equalsIgnoreCase("0")) {
	        return "Selecione";
	    } else {
	    	Tipooperacao tipooperacao = (Tipooperacao) arg2;
	        return tipooperacao.getDescricao();
	    }
	}
}
