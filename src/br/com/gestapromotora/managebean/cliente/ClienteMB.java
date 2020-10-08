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
	private String nomeCliente;
	private String cpf;
	
	
	
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
	
	
	
	public String getNomeCliente() {
		return nomeCliente;
	}



	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}



	public String getCpf() {
		return cpf;
	}



	public void setCpf(String cpf) {
		this.cpf = cpf;
	}



	public void gerarListaCliente() {
		ClienteFacade clienteFacade = new ClienteFacade();
		listaCliente = clienteFacade.lista("Select c From Cliente c ORDER BY c.nome");
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
	
	
	
	public void pesquisar() {
		String sql = "Select c From Cliente c WHERE c.nome like '%"+ nomeCliente +"%' and c.cpf like '%"+ cpf +"%'";
		sql = sql + " ORDER BY c.nome";
		ClienteFacade clienteFacade = new ClienteFacade();
		listaCliente = clienteFacade.lista(sql);
		if (listaCliente == null) {
			listaCliente = new ArrayList<Cliente>();
		}
	}
	
	public void limpar() {
		gerarListaCliente();
		nomeCliente = "";
		cpf = "";
	}
	
	
	
	
 
}
