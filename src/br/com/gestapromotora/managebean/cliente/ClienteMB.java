package br.com.gestapromotora.managebean.cliente;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.gestapromotora.facade.ClienteFacade;
import br.com.gestapromotora.model.Cliente;

@Named
@ViewScoped
public class ClienteMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Cliente> listaCliente;
	
	
	
	@PostConstruct
	public void init() {
		gerarListaCliente();
	}



	public List<Cliente> getListaCliente() {
		return listaCliente;
	}



	public void setListaCliente(List<Cliente> listaCliente) {
		this.listaCliente = listaCliente;
	}
	
	
	
	public void gerarListaCliente() {
		ClienteFacade clienteFacade = new ClienteFacade();
		listaCliente = clienteFacade.lista("Select c From Cliente c");
		if (listaCliente == null) {
			listaCliente = new ArrayList<Cliente>();
		}
	}
	
	
	public String novoCliente() {
		return "cadCliente";
	}
	
	
	public String editar(Cliente cliente) {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("cliente", cliente);
		return "cadCliente";
	}
	
	
	
	
	
 
}
