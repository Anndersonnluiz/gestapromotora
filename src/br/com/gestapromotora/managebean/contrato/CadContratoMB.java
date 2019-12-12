package br.com.gestapromotora.managebean.contrato;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.gestapromotora.bean.ControladorCEPBean;
import br.com.gestapromotora.bean.EnderecoBean;
import br.com.gestapromotora.facade.BancoFacade;
import br.com.gestapromotora.facade.ClienteFacade;
import br.com.gestapromotora.facade.OrgaoBancoFacade;
import br.com.gestapromotora.model.Banco;
import br.com.gestapromotora.model.Cliente;
import br.com.gestapromotora.model.Contrato;
import br.com.gestapromotora.model.Dadosbancario;
import br.com.gestapromotora.model.OrgaoBanco;
import br.com.gestapromotora.model.Valorescoeficiente;

@Named
@ViewScoped
public class CadContratoMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Banco> listaBanco;
	private Banco banco;
	private List<OrgaoBanco> listaOrgaoBanco;
	private OrgaoBanco orgaoBanco;
	private Contrato contrato;
	private Cliente cliente;
	private String cpf;
	private Valorescoeficiente valorescoeficiente;
	private Dadosbancario dadosbancario;
	private List<Banco> listaBancoOperacao;
	
	
	@PostConstruct
	public void init() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		contrato = (Contrato) session.getAttribute("contrato");
		orgaoBanco = (OrgaoBanco) session.getAttribute("orgaobanco");
		session.removeAttribute("orgaobanco");
		session.removeAttribute("contrato");
		gerarListaBanco();
		if (contrato == null) {
			contrato = new Contrato();
			cliente = new Cliente();
			dadosbancario = new Dadosbancario();
			valorescoeficiente = new Valorescoeficiente();
			contrato.setDatacadastro(new Date());
		}else {
			cliente = contrato.getCliente();
			banco = orgaoBanco.getBanco();
			gerarListaOrgao();
			valorescoeficiente = contrato.getValorescoeficiente();
			dadosbancario = cliente.getDadosbancario();
		}
	}


	public List<Banco> getListaBanco() {
		return listaBanco;
	}
 

	public void setListaBanco(List<Banco> listaBanco) {
		this.listaBanco = listaBanco;
	}


	public Banco getBanco() {
		return banco;
	}


	public void setBanco(Banco banco) {
		this.banco = banco;
	}


	public List<OrgaoBanco> getListaOrgaoBanco() {
		return listaOrgaoBanco;
	}


	public void setListaOrgaoBanco(List<OrgaoBanco> listaOrgaoBanco) {
		this.listaOrgaoBanco = listaOrgaoBanco;
	}
	
	

	
	
	public OrgaoBanco getOrgaoBanco() {
		return orgaoBanco;
	}


	public void setOrgaoBanco(OrgaoBanco orgaoBanco) {
		this.orgaoBanco = orgaoBanco;
	}


	public Contrato getContrato() {
		return contrato;
	}


	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}


	public Cliente getCliente() {
		return cliente;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


	public String getCpf() {
		return cpf;
	}


	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Valorescoeficiente getValorescoeficiente() {
		return valorescoeficiente;
	}


	public void setValorescoeficiente(Valorescoeficiente valorescoeficiente) {
		this.valorescoeficiente = valorescoeficiente;
	}


	public Dadosbancario getDadosbancario() {
		return dadosbancario;
	}


	public void setDadosbancario(Dadosbancario dadosbancario) {
		this.dadosbancario = dadosbancario;
	}


	public List<Banco> getListaBancoOperacao() {
		return listaBancoOperacao;
	}


	public void setListaBancoOperacao(List<Banco> listaBancoOperacao) {
		this.listaBancoOperacao = listaBancoOperacao;
	}


	public void gerarListaBanco() {
		BancoFacade bancoFacade = new BancoFacade();
		listaBanco = bancoFacade.lista("Select b From Banco b");
		if (listaBanco == null) {
			listaBanco = new ArrayList<Banco>();
		}
		listaBancoOperacao = listaBanco;
	}
	
	
	public void gerarListaOrgao() {
		OrgaoBancoFacade orgaoBancoFacade = new OrgaoBancoFacade();
		listaOrgaoBanco = orgaoBancoFacade.lista("Select o From OrgaoBanco o WHERE o.banco.idbanco=" + banco.getIdbanco());
		if (listaOrgaoBanco == null) {
			listaOrgaoBanco = new ArrayList<OrgaoBanco>();
		}
	}
	
	
	public void buscarCliente() {
		ClienteFacade clienteFacade = new ClienteFacade();
		cliente = clienteFacade.consultarCpf(cpf);
		contrato.setCliente(cliente);
		if (cliente == null) {
			cliente = new Cliente();
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
	
	
	public String selecionarCoeficiente() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("contrato", contrato);
		session.setAttribute("orgaobanco", orgaoBanco);
		return "selecioneCoeficiente";
	}
	
	
	
	
	
	

}
