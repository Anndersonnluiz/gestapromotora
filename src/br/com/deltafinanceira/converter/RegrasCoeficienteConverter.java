package br.com.deltafinanceira.converter;

import br.com.deltafinanceira.model.Regrascoeficiente;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("RegrasCoeficienteConverter")
public class RegrasCoeficienteConverter implements Converter {
  public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
    List<Regrascoeficiente> listaRegrasValores = (List<Regrascoeficiente>)arg1.getAttributes().get("listaRegrasValores");
    if (listaRegrasValores != null) {
      for (Regrascoeficiente regrascoeficiente1 : listaRegrasValores) {
        if (regrascoeficiente1.getNometabela().equalsIgnoreCase(arg2))
          return regrascoeficiente1; 
      } 
    } else {
      Regrascoeficiente regrascoeficiente1 = new Regrascoeficiente();
      return regrascoeficiente1;
    } 
    Regrascoeficiente regrascoeficiente = new Regrascoeficiente();
    return regrascoeficiente;
  }
  
  public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
    if (arg2.toString().equalsIgnoreCase("0"))
      return "Selecione"; 
    Regrascoeficiente regrascoeficiente = (Regrascoeficiente)arg2;
    return regrascoeficiente.getNometabela();
  }
}
