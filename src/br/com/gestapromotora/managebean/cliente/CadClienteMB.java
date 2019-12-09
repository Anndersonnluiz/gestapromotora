package br.com.gestapromotora.managebean.cliente;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.gestapromotora.bean.ControladorCEPBean;
import br.com.gestapromotora.bean.EnderecoBean;
import br.com.gestapromotora.facade.ClienteFacade;
import br.com.gestapromotora.facade.DadosBancarioFacade;
import br.com.gestapromotora.model.Cliente;
import br.com.gestapromotora.model.Dadosbancario;

@Named
@ViewScoped
public class CadClienteMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Cliente cliente;
	private Dadosbancario dadosbancario;
	
	
	@PostConstruct
	public void init() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		cliente = (Cliente) session.getAttribute("cliente");
		session.removeAttribute("cliente");
		if (cliente == null) {
			cliente = new Cliente();
			dadosbancario = new Dadosbancario();
		}else {
			buscarDadosBancarios(cliente.getDadosbancario());
		}
	}


	public Cliente getCliente() {
		return cliente;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	
	public Dadosbancario getDadosbancario() {
		return dadosbancario;
	}


	public void setDadosbancario(Dadosbancario dadosbancario) {
		this.dadosbancario = dadosbancario;
	}
	
	
	
	public String cancelar() {
		return "consCliente";
	}
	
	
	public String salvar() {
		ClienteFacade clienteFacade = new ClienteFacade();
		salvarDadosBancarios();
		cliente.setDadosbancario(dadosbancario);
		clienteFacade.salvar(cliente);
		return "consCliente";
	}


	public void salvarDadosBancarios() {
		DadosBancarioFacade dadosBancarioFacade = new DadosBancarioFacade();
		dadosbancario = dadosBancarioFacade.salvar(dadosbancario);
	}
	
	
	public void buscarDadosBancarios(Dadosbancario dadosbancario) {
		DadosBancarioFacade dadosBancarioFacade = new DadosBancarioFacade();
		this.dadosbancario = dadosBancarioFacade.consultar(dadosbancario.getIddadosbancario());
		if (this.dadosbancario == null) {
			this.dadosbancario = new Dadosbancario();
		}
	}
	
	
	public void buscarendereco(){
		ControladorCEPBean cep = new ControladorCEPBean();
		cep.setCep(cliente.getCep());
		EnderecoBean endereco = cep.carregarEndereco();
		if (endereco.getLogradouro() != null) {
			cliente.setBairro(endereco.getBairro());
			cliente.setUfestado(endereco.getUf());
			cliente.setCidade(endereco.getLocalidade());
			cliente.setComplemento(endereco.getComplemento());
			String logradouro = endereco.getLogradouro().substring(endereco.getLogradouro().indexOf(" "), endereco.getLogradouro().length());
	        int posicao = endereco.getLogradouro().length();
	        for (int i = 0; i <= logradouro.length(); i++) {
	            posicao = posicao - 1;
	        }
	        String tipo = endereco.getLogradouro().substring(0, posicao +1);
	        cliente.setLogradouro(logradouro);
	        cliente.setTipologradouro(tipo);
		}
	}
	
	
	
	
	
	
	
	
	

}
