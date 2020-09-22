package br.com.gestapromotora.managebean.notificacao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.com.gestapromotora.dao.ClienteDao;
import br.com.gestapromotora.model.Cliente;
import br.com.gestapromotora.util.Formatacao;

@Named
@ViewScoped
public class AniversariantesMB implements Serializable{

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
		ClienteDao clienteDao = new ClienteDao();
		listaCliente = clienteDao.lista("Select c From Cliente c WHERE c.nascimento='" + Formatacao.ConvercaoDataNfe(new Date()) 
				+ "'");
		if (listaCliente == null) {
			listaCliente = new ArrayList<Cliente>();
		}
	}

}
