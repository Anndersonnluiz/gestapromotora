package br.com.deltafinanceira.managebean.usuario;

import br.com.deltafinanceira.facade.BancoFacade;
import br.com.deltafinanceira.facade.DadosBancarioFacade;
import br.com.deltafinanceira.facade.DepartamentoFacade;
import br.com.deltafinanceira.facade.HistoricoComissaoFacade;
import br.com.deltafinanceira.facade.TipoColaboradorFacade;
import br.com.deltafinanceira.facade.TipoOperacaoFacade;
import br.com.deltafinanceira.facade.UsuarioComissaoFacade;
import br.com.deltafinanceira.facade.UsuarioFacade;
import br.com.deltafinanceira.model.Banco;
import br.com.deltafinanceira.model.Dadosbancario;
import br.com.deltafinanceira.model.Departamento;
import br.com.deltafinanceira.model.Historicocomissao;
import br.com.deltafinanceira.model.Tipocolaborador;
import br.com.deltafinanceira.model.Tipooperacao;
import br.com.deltafinanceira.model.Usuario;
import br.com.deltafinanceira.model.Usuariocomissao;
import br.com.deltafinanceira.util.Mensagem;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

@Named
@ViewScoped
public class CadUsuarioMB implements Serializable {
	private static final long serialVersionUID = 1L;

	private Usuario usuario;

	private Dadosbancario dadosbancario;

	private Tipocolaborador tipocolaborador;

	private List<Tipocolaborador> listaTipoColaborador;

	private Banco bancoDadosBancario;

	private List<Banco> listaBanco;

	private List<Departamento> listaDepartamento;

	private Departamento departamento;

	private List<Tipooperacao> listaTipoOperacao;

	private List<Usuariocomissao> listaUsuarioComissao;

	private Usuariocomissao usuariocomissao;

	private Tipooperacao tipooperacao;

	@PostConstruct
	public void init() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		this.usuario = (Usuario) session.getAttribute("usuario");
		this.departamento = (Departamento) session.getAttribute("departamento");
		session.removeAttribute("usuario");
		session.removeAttribute("departamento");
		gerarListaBanco();
		if (this.usuario == null) {
			this.usuario = new Usuario();
			this.dadosbancario = new Dadosbancario();
			this.usuario.setDescricaoativo("check");
			listaUsuarioComissao = new ArrayList<Usuariocomissao>();
		} else {
			buscarDadosBancarios(this.usuario);
			this.tipocolaborador = this.usuario.getTipocolaborador();
			this.bancoDadosBancario = this.dadosbancario.getBanco();
			gerarListaComissoes();
		}
		usuariocomissao = new Usuariocomissao();
		gerarListaTipoOperacao();
		gerarListaTipoColaborador();
		gerarListaDepartamento();
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Dadosbancario getDadosbancario() {
		return this.dadosbancario;
	}

	public void setDadosbancario(Dadosbancario dadosbancario) {
		this.dadosbancario = dadosbancario;
	}

	public Tipocolaborador getTipocolaborador() {
		return this.tipocolaborador;
	}

	public void setTipocolaborador(Tipocolaborador tipocolaborador) {
		this.tipocolaborador = tipocolaborador;
	}

	public List<Tipocolaborador> getListaTipoColaborador() {
		return this.listaTipoColaborador;
	}

	public void setListaTipoColaborador(List<Tipocolaborador> listaTipoColaborador) {
		this.listaTipoColaborador = listaTipoColaborador;
	}

	public Banco getBancoDadosBancario() {
		return this.bancoDadosBancario;
	}

	public void setBancoDadosBancario(Banco bancoDadosBancario) {
		this.bancoDadosBancario = bancoDadosBancario;
	}

	public List<Banco> getListaBanco() {
		return this.listaBanco;
	}

	public void setListaBanco(List<Banco> listaBanco) {
		this.listaBanco = listaBanco;
	}

	public synchronized List<Departamento> getListaDepartamento() {
		return this.listaDepartamento;
	}

	public synchronized void setListaDepartamento(List<Departamento> listaDepartamento) {
		this.listaDepartamento = listaDepartamento;
	}

	public synchronized Departamento getDepartamento() {
		return this.departamento;
	}

	public synchronized void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public List<Tipooperacao> getListaTipoOperacao() {
		return listaTipoOperacao;
	}

	public void setListaTipoOperacao(List<Tipooperacao> listaTipoOperacao) {
		this.listaTipoOperacao = listaTipoOperacao;
	}

	public List<Usuariocomissao> getListaUsuarioComissao() {
		return listaUsuarioComissao;
	}

	public void setListaUsuarioComissao(List<Usuariocomissao> listaUsuarioComissao) {
		this.listaUsuarioComissao = listaUsuarioComissao;
	}

	public Usuariocomissao getUsuariocomissao() {
		return usuariocomissao;
	}

	public void setUsuariocomissao(Usuariocomissao usuariocomissao) {
		this.usuariocomissao = usuariocomissao;
	}

	public Tipooperacao getTipooperacao() {
		return tipooperacao;
	}

	public void setTipooperacao(Tipooperacao tipooperacao) {
		this.tipooperacao = tipooperacao;
	}

	public void gerarListaTipoColaborador() {
		TipoColaboradorFacade tipoColaboradorFacade = new TipoColaboradorFacade();
		this.listaTipoColaborador = tipoColaboradorFacade.listar("Select t From Tipocolaborador t");
		if (this.listaTipoColaborador == null)
			this.listaTipoColaborador = new ArrayList<>();
	}

	public void buscarDadosBancarios(Usuario usuario) {
		DadosBancarioFacade dadosBancarioFacade = new DadosBancarioFacade();
		this.dadosbancario = dadosBancarioFacade.consultar(usuario.getDadosbancario().getIddadosbancario().intValue());
		if (this.dadosbancario == null)
			this.dadosbancario = new Dadosbancario();
	}

	public String salvar() {
		if (validarDados()) {
			UsuarioFacade usuarioFacade = new UsuarioFacade();
			this.usuario.setTipocolaborador(this.tipocolaborador);
			this.usuario.setDepartamento(this.departamento);
			salvarDadosBancarios();
			this.usuario.setDadosbancario(this.dadosbancario);
			if (this.usuario.getIdusuario() == null) {
				this.usuario.setAtivo(true);
				this.usuario.setSenha("t+lL5RPpboxFzSPRYideWhLr3pEApCXE683X+k3NiXw=");
				this.usuario = usuarioFacade.salvar(this.usuario);
			} else {
				this.usuario = usuarioFacade.salvar(this.usuario);
			}
			salvarComissao();
			FacesContext fc = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
			session.setAttribute("departamento", this.departamento);
			session.setAttribute("filial", this.departamento.getFilial());
			return "consUsuario";
		}
		return "";
	}

	public boolean validarDados() {
		if (this.departamento == null || this.departamento.getIddepartamento() == null) {
			Mensagem.lancarMensagemInfo("Selecione o Departamento", "");
			return false;
		}
		if (this.tipocolaborador == null || this.tipocolaborador.getIdtipocolaborador() == null) {
			Mensagem.lancarMensagemInfo("Selecione o Tipo de Colaborador", "");
			return false;
		}
		return true;
	}

	public void salvarDadosBancarios() {
		DadosBancarioFacade dadosBancarioFacade = new DadosBancarioFacade();
		if (this.bancoDadosBancario == null || this.bancoDadosBancario.getIdbanco() == null) {
			BancoFacade bancoFacade = new BancoFacade();
			List<Banco> listaBanco = bancoFacade.lista("Select b From Banco b");
			if (listaBanco == null)
				listaBanco = new ArrayList<>();
			this.bancoDadosBancario = listaBanco.get(0);
		}
		this.dadosbancario.setBanco(this.bancoDadosBancario);
		this.dadosbancario = dadosBancarioFacade.salvar(this.dadosbancario);
	}

	public String cancelar() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("departamento", this.departamento);
		session.setAttribute("filial", this.departamento.getFilial());
		return "consUsuario";
	}

	public void gerarListaBanco() {
		BancoFacade bancoFacade = new BancoFacade();
		this.listaBanco = bancoFacade.lista("Select b From Banco b WHERE b.visualizar=true ORDER BY b.codigo");
		if (this.listaBanco == null)
			this.listaBanco = new ArrayList<>();
	}

	public void gerarListaDepartamento() {
		DepartamentoFacade departamentoFacade = new DepartamentoFacade();
		this.listaDepartamento = departamentoFacade.lista(
				"Select d From Departamento d Where d.filial.idfilial=" + this.departamento.getFilial().getIdfilial());
		if (this.listaDepartamento == null)
			this.listaDepartamento = new ArrayList<>();
	}

	public void gerarListaTipoOperacao() {
		TipoOperacaoFacade tipoOperacaoFacade = new TipoOperacaoFacade();
		this.listaTipoOperacao = tipoOperacaoFacade.lista("Select t From Tipooperacao t Where t.ativo=true Order By t.descricao");
		if (this.listaTipoOperacao == null)
			this.listaTipoOperacao = new ArrayList<>();
	}

	public void gerarListaComissoes() {
		UsuarioComissaoFacade usuarioComissaoFacade = new UsuarioComissaoFacade();
		listaUsuarioComissao = usuarioComissaoFacade
				.listar("Select u From Usuariocomissao u Where u.usuario.idusuario=" + usuario.getIdusuario());
		if (listaUsuarioComissao == null) {
			listaUsuarioComissao = new ArrayList<Usuariocomissao>();
		}
	}

	public void adicionarComissao() {
		if (tipooperacao != null && tipooperacao.getIdtipooperacao() != null) {
			usuariocomissao.setTipooperacao(tipooperacao);
			listaUsuarioComissao.add(usuariocomissao);
			usuariocomissao = new Usuariocomissao();
		} else {
			Mensagem.lancarMensagemInfo("Selecione o Tipo de Operação", "Comissionamento");
		}
	}

	public void excluirComissao(String slinha) {
		int linha = Integer.parseInt(slinha);
		Usuariocomissao usuariocomissao = listaUsuarioComissao.get(linha);
		if (usuariocomissao.getIdusuariocomissao() != null) {
			UsuarioComissaoFacade usuarioComissaoFacade = new UsuarioComissaoFacade();
			usuarioComissaoFacade.excluir(usuariocomissao.getIdusuariocomissao());
		}
		listaUsuarioComissao.remove(linha);
		Mensagem.lancarMensagemInfo("Excluido com sucesso!!", "");
	}

	public void salvarComissao() {
		UsuarioComissaoFacade ususComissaoFacade = new UsuarioComissaoFacade();
		for (int i = 0; i < listaUsuarioComissao.size(); i++) {
			listaUsuarioComissao.get(i).setUsuario(usuario);
			ususComissaoFacade.salvar(listaUsuarioComissao.get(i));
		}
	}

	public void recalcularComissoes(Usuariocomissao usuariocomissao) {
		HistoricoComissaoFacade historicoComissaoFacade = new HistoricoComissaoFacade();
		List<Historicocomissao> listaComissoes = historicoComissaoFacade
				.lista("Select h From Historicocomissao h " + "Where h.contrato.tipooperacao.idtipooperacao="
						+ usuariocomissao.getTipooperacao().getIdtipooperacao() + " AND h.usuario.idusuario="
						+ usuario.getIdusuario() + " AND h.contrato.situacao.idsituacao<>16");
		if (listaComissoes == null) {
			listaComissoes = new ArrayList<Historicocomissao>();
		}
		for (int i = 0; i < listaComissoes.size(); i++) {
			if (listaComissoes.get(i).getContrato().getParcelaspagas() > 12
					&& listaComissoes.get(i).getContrato().getTipooperacao().getIdtipooperacao().intValue() == 1) {
				listaComissoes.get(i)
						.setCmsliq(listaComissoes.get(i).getContrato().getValorquitar() * usuariocomissao.getComissaocorretor() / 100.0F);
				listaComissoes.get(i).setComissaototal(listaComissoes.get(i).getCmdbruta() + listaComissoes.get(i).getCmsliq());
			} else if (listaComissoes.get(i).getContrato().getTipooperacao().getIdtipooperacao().intValue() != 1) {
				listaComissoes.get(i)
						.setCmsliq(listaComissoes.get(i).getContrato().getValorcliente() * usuariocomissao.getComissaocorretor() / 100.0F);
				listaComissoes.get(i).setComissaototal(listaComissoes.get(i).getCmdbruta() + listaComissoes.get(i).getCmsliq());
			}
		}
	}

}
