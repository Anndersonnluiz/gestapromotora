package br.com.gestapromotora.converter;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.gestapromotora.model.Tipoarquivo;


@FacesConverter(value = "TipoArquivoConverter")
public class TipoArquivoConverter implements Converter{

	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		@SuppressWarnings("unchecked")
		List<Tipoarquivo> listaTipoArquivo = (List<Tipoarquivo>) arg1.getAttributes().get("listaTipoArquivo");
	    if (listaTipoArquivo != null) {
	        for (Tipoarquivo tipoarquivo : listaTipoArquivo) {
	            if (tipoarquivo.getDescricao().equalsIgnoreCase(arg2)) {
	                return tipoarquivo;
	            }
	        } 
	    } else {
	    	Tipoarquivo tipoarquivo = new Tipoarquivo();
	        return tipoarquivo;
	    }
	    Tipoarquivo tipoarquivo = new Tipoarquivo();
	    return tipoarquivo;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		if (arg2.toString().equalsIgnoreCase("0")) {
	        return "Selecione";
	    } else {
	    	Tipoarquivo tipoarquivo = (Tipoarquivo) arg2;
	        return tipoarquivo.getDescricao();
	    }
	}
}
