package br.com.gestapromotora.converter;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.gestapromotora.model.OrgaoBanco;


@FacesConverter(value = "OrgaoBancoConverter")
public class OrgaoBancoConverter implements Converter{

	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		@SuppressWarnings("unchecked")
		List<OrgaoBanco> listaOrgao = (List<OrgaoBanco>) arg1.getAttributes().get("listaOrgao");
	    if (listaOrgao != null) {
	        for (OrgaoBanco orgao : listaOrgao) {
	            if (orgao.getNome().equalsIgnoreCase(arg2)) {
	                return orgao;
	            }
	        }
	    } else {
	    	OrgaoBanco orgao = new OrgaoBanco();
	        return orgao;
	    }
	    OrgaoBanco orgao = new OrgaoBanco();
	    return orgao;
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		if (arg2.toString().equalsIgnoreCase("0")) {
	        return "Selecione";
	    } else {
	    	OrgaoBanco orgao = (OrgaoBanco) arg2;
	        return orgao.getNome();
	    }
	}
}
