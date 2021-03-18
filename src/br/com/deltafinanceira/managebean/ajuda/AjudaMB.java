package br.com.deltafinanceira.managebean.ajuda;

import br.com.deltafinanceira.facade.AjudaFacade;
import br.com.deltafinanceira.model.Ajuda;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class AjudaMB implements Serializable {
  private static final long serialVersionUID = 1L;
  
  private List<Ajuda> listaAjuda;
  
  @PostConstruct
  public void init() {
    gerarListaAjuda();
  }
  
  public List<Ajuda> getListaAjuda() {
    return this.listaAjuda;
  }
  
  public void setListaAjuda(List<Ajuda> listaAjuda) {
    this.listaAjuda = listaAjuda;
  }
  
  public void gerarListaAjuda() {
    AjudaFacade ajudaFacade = new AjudaFacade();
    this.listaAjuda = ajudaFacade.lista("Select a From Ajuda a");
    if (this.listaAjuda == null)
      this.listaAjuda = new ArrayList<>(); 
  }
}
