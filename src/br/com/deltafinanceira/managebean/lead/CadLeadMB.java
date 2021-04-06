package br.com.deltafinanceira.managebean.lead;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.deltafinanceira.bean.ControladorCEPBean;
import br.com.deltafinanceira.bean.EnderecoBean;
import br.com.deltafinanceira.facade.BancoFacade;
import br.com.deltafinanceira.facade.ClienteFacade;
import br.com.deltafinanceira.facade.DadosBancarioFacade;
import br.com.deltafinanceira.facade.LeadFacade;
import br.com.deltafinanceira.facade.TipoOperacaoFacade;
import br.com.deltafinanceira.model.Banco;
import br.com.deltafinanceira.model.Cliente;
import br.com.deltafinanceira.model.Dadosbancario;
import br.com.deltafinanceira.model.Lead;
import br.com.deltafinanceira.model.Tipooperacao;
import br.com.deltafinanceira.util.Formatacao;
import br.com.deltafinanceira.util.Mensagem;
import br.com.deltafinanceira.util.UsuarioLogadoMB;

@Named
@ViewScoped
public class CadLeadMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private UsuarioLogadoMB usuarioLogadoMB;
	private Lead lead;
	private Cliente cliente;
	private List<Banco> listaBanco;
	private Dadosbancario dadosbancario;
	private Banco bancoDadosBancario;
	private List<Tipooperacao> listaTipoOperacao;
	private Tipooperacao tipooperacao;
	private String cpf;

	@PostConstruct
	public void init() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		this.lead = (Lead) session.getAttribute("lead");
		session.removeAttribute("lead");
		gerarListaBanco();
		gerarListaTipoOperacao();
		if (lead == null) {
			lead = new Lead();
			lead.setSituacao(1);
			lead.setCorsituacao("#1E90FF");
			dadosbancario = new Dadosbancario();
			cliente = new Cliente();
			cliente.setUsuario(usuarioLogadoMB.getUsuario());
		} else {
			this.cliente = this.lead.getCliente();
			cpf = this.cliente.getCpf();
			buscarDadosBancarios(this.cliente.getDadosbancario());
			this.bancoDadosBancario = this.dadosbancario.getBanco();
			if (lead.getIdoperacao() > 0) {
				TipoOperacaoFacade tipoOperacaoFacade = new TipoOperacaoFacade();
				tipooperacao = tipoOperacaoFacade.consultar(lead.getIdoperacao());
			}
		}
	}

	public Lead getLead() {
		return lead;
	}

	public void setLead(Lead lead) {
		this.lead = lead;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Banco> getListaBanco() {
		return listaBanco;
	}

	public void setListaBanco(List<Banco> listaBanco) {
		this.listaBanco = listaBanco;
	}

	public Dadosbancario getDadosbancario() {
		return dadosbancario;
	}

	public void setDadosbancario(Dadosbancario dadosbancario) {
		this.dadosbancario = dadosbancario;
	}

	public Banco getBancoDadosBancario() {
		return bancoDadosBancario;
	}

	public void setBancoDadosBancario(Banco bancoDadosBancario) {
		this.bancoDadosBancario = bancoDadosBancario;
	}

	public UsuarioLogadoMB getUsuarioLogadoMB() {
		return usuarioLogadoMB;
	}

	public void setUsuarioLogadoMB(UsuarioLogadoMB usuarioLogadoMB) {
		this.usuarioLogadoMB = usuarioLogadoMB;
	}

	public List<Tipooperacao> getListaTipoOperacao() {
		return listaTipoOperacao;
	}

	public void setListaTipoOperacao(List<Tipooperacao> listaTipoOperacao) {
		this.listaTipoOperacao = listaTipoOperacao;
	}

	public Tipooperacao getTipooperacao() {
		return tipooperacao;
	}

	public void setTipooperacao(Tipooperacao tipooperacao) {
		this.tipooperacao = tipooperacao;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void salvarDadosBancarios() {
		DadosBancarioFacade dadosBancarioFacade = new DadosBancarioFacade();
		this.dadosbancario.setBanco(this.bancoDadosBancario);
		this.dadosbancario = dadosBancarioFacade.salvar(this.dadosbancario);
	}

	public void buscarDadosBancarios(Dadosbancario dadosbancario) {
		DadosBancarioFacade dadosBancarioFacade = new DadosBancarioFacade();
		this.dadosbancario = dadosBancarioFacade.consultar(dadosbancario.getIddadosbancario().intValue());
		if (this.dadosbancario == null)
			this.dadosbancario = new Dadosbancario();
	}

	public void buscarendereco() {
		ControladorCEPBean cep = new ControladorCEPBean();
		cep.setCep(this.cliente.getCep());
		EnderecoBean endereco = cep.carregarEndereco();
		if (endereco.getLogradouro() != null) {
			this.cliente.setBairro(endereco.getBairro());
			this.cliente.setUfestado(endereco.getUf());
			this.cliente.setCidade(endereco.getLocalidade());
			this.cliente.setComplemento(endereco.getComplemento());
			String logradouro = endereco.getLogradouro().substring(endereco.getLogradouro().indexOf(" "),
					endereco.getLogradouro().length());
			int posicao = endereco.getLogradouro().length();
			for (int i = 0; i <= logradouro.length(); i++)
				posicao--;
			String tipo = endereco.getLogradouro().substring(0, posicao + 1);
			this.cliente.setLogradouro(logradouro);
			this.cliente.setTipologradouro(tipo);
		}
	}

	public void gerarListaBanco() {
		BancoFacade bancoFacade = new BancoFacade();
		this.listaBanco = bancoFacade.lista("Select b From Banco b WHERE b.visualizar=true ORDER BY b.nome");
		if (this.listaBanco == null)
			this.listaBanco = new ArrayList<>();
	}

	public String salvar() {
		LeadFacade leadFacade = new LeadFacade();
		ClienteFacade clienteFacade = new ClienteFacade();
		if (this.bancoDadosBancario == null || this.bancoDadosBancario.getIdbanco() == null) {
			BancoFacade bancoFacade = new BancoFacade();
			List<Banco> listaBanco = bancoFacade.lista("Select b From Banco b where b.visualizar=true");
			if (listaBanco == null)
				listaBanco = new ArrayList<>();
			this.bancoDadosBancario = listaBanco.get(0);
		}
		salvarDadosBancarios();
		this.cliente.setDadosbancario(this.dadosbancario);
		if (this.cliente.getNascimento() == null)
			this.cliente.setNascimento(new Date());
		String diames = "" + Formatacao.getDiaData(this.cliente.getNascimento())
				+ (Formatacao.getMesData(this.cliente.getNascimento()) + 1);
		this.cliente.setDiames(Integer.parseInt(diames));
		this.cliente.setCpf(cpf);
		cliente = clienteFacade.salvar(this.cliente);
		lead.setCliente(cliente);
		lead.setTipooperacao(tipooperacao.getApelido());
		lead.setIdoperacao(tipooperacao.getIdtipooperacao());
		leadFacade.salvar(lead);
		return "consLead";
	}

	public void gerarListaTipoOperacao() {
		TipoOperacaoFacade tipoOperacaoFacade = new TipoOperacaoFacade();
		this.listaTipoOperacao = tipoOperacaoFacade.lista("Select t From Tipooperacao t Order By t.descricao");
		if (this.listaTipoOperacao == null)
			this.listaTipoOperacao = new ArrayList<>();
	}

	public void buscarCliente() {
		ClienteFacade clienteFacade = new ClienteFacade();
		cliente = clienteFacade.consultarCpf(this.cpf);
		if (this.cliente == null) {
			this.cliente = new Cliente();
			this.dadosbancario = new Dadosbancario();
			this.cliente.setUsuario(this.usuarioLogadoMB.getUsuario());
		} else if (this.cliente != null
				&& this.cliente.getUsuario().getIdusuario() != this.usuarioLogadoMB.getUsuario().getIdusuario()) {
			Mensagem.lancarMensagemWarn("Este cliente pertence ao corretor(a): ", this.cliente.getUsuario().getNome());
			this.cliente = new Cliente();
			this.dadosbancario = new Dadosbancario();
			this.cliente.setUsuario(this.usuarioLogadoMB.getUsuario());
		}
	}
	
	
	public String cancelar() {
		return "consLead";
	}

}
