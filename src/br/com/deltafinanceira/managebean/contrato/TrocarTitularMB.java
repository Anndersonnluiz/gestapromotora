package br.com.deltafinanceira.managebean.contrato;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.com.deltafinanceira.facade.ContratoFacade;
import br.com.deltafinanceira.facade.HistoricoComissaoFacade;
import br.com.deltafinanceira.facade.RankingVendasAnualFacade;
import br.com.deltafinanceira.facade.RankingVendasFacade;
import br.com.deltafinanceira.facade.RegrasCoeficienteFacade;
import br.com.deltafinanceira.facade.UsuarioFacade;
import br.com.deltafinanceira.model.Contrato;
import br.com.deltafinanceira.model.Historicocomissao;
import br.com.deltafinanceira.model.Rankingvendas;
import br.com.deltafinanceira.model.Rankingvendasanual;
import br.com.deltafinanceira.model.Regrascoeficiente;
import br.com.deltafinanceira.model.Usuario;
import br.com.deltafinanceira.util.Formatacao;
import br.com.deltafinanceira.util.Mensagem;

@Named
@ViewScoped
public class TrocarTitularMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Usuario> listaUsuario;
	private Usuario usuario;
	private Contrato contrato;
	private String senha;
	private Regrascoeficiente regracoeficiente;
	private Usuario usuarioAtual;
	private List<Historicocomissao> listaHistoricoComissao;
	private boolean selecionarTodos;
	
	
	
	@PostConstruct
	public void init() {
		gerarListaUsuario();
	}



	public List<Usuario> getListaUsuario() {
		return listaUsuario;
	}



	public void setListaUsuario(List<Usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}



	public Usuario getUsuario() {
		return usuario;
	}



	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}



	public Contrato getContrato() {
		return contrato;
	}



	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}
	
	
	public String getSenha() {
		return senha;
	}



	public void setSenha(String senha) {
		this.senha = senha;
	}



	public Regrascoeficiente getRegracoeficiente() {
		return regracoeficiente;
	}



	public void setRegracoeficiente(Regrascoeficiente regracoeficiente) {
		this.regracoeficiente = regracoeficiente;
	}



	public Usuario getUsuarioAtual() {
		return usuarioAtual;
	}



	public void setUsuarioAtual(Usuario usuarioAtual) {
		this.usuarioAtual = usuarioAtual;
	}



	public List<Historicocomissao> getListaHistoricoComissao() {
		return listaHistoricoComissao;
	}



	public void setListaHistoricoComissao(List<Historicocomissao> listaHistoricoComissao) {
		this.listaHistoricoComissao = listaHistoricoComissao;
	}



	public boolean isSelecionarTodos() {
		return selecionarTodos;
	}



	public void setSelecionarTodos(boolean selecionarTodos) {
		this.selecionarTodos = selecionarTodos;
	}



	public void gerarListaUsuario() {
		UsuarioFacade usuarioFacade = new UsuarioFacade();
		listaUsuario = usuarioFacade.listar("Select u From Usuario u order by u.nome");
		if (listaUsuario == null) {
			listaUsuario = new ArrayList<Usuario>();
		}
	}
	
	
	public String salvar() {
		HistoricoComissaoFacade historicoComissaoFacade = new HistoricoComissaoFacade();
		ContratoFacade contratoFacade = new ContratoFacade();
		if (usuario != null) {
			for (int i = 0; i < listaHistoricoComissao.size(); i++) {
				if (listaHistoricoComissao.get(i).isSelecionado()) {
					listaHistoricoComissao.get(i).setUsuario(usuario);
					listaHistoricoComissao.get(i).getContrato().setUsuario(usuario);
					contratoFacade.salvar(listaHistoricoComissao.get(i).getContrato());
					historicoComissaoFacade.salvar(listaHistoricoComissao.get(i));
				}
			}
		}else {
			Mensagem.lancarMensagemInfo("Informe o novo titular dos contratos", "");
			return "";
		}
		return "dashboard";
	}
	
	
	public String cancelar() {
		return "dashboard";
	}
	
	public void descontarRankingMensal() {
		RankingVendasFacade rankingVendasFacade = new RankingVendasFacade();
		Rankingvendas rankingvendas;
		int mes = Formatacao.getMesData(new Date()) + 1;
		int ano = Formatacao.getAnoData(new Date());
		List<Rankingvendas> listaRanking = rankingVendasFacade.lista("Select r From Rankingvendas r WHERE r.mes=" + mes
				+ " AND r.ano=" + ano + " AND r.usuario.idusuario=" + contrato.getUsuario().getIdusuario());
		if (listaRanking != null && listaRanking.size() > 0) {
			rankingvendas = listaRanking.get(0);
			if (contrato.getParcelaspagas() > 12 && contrato.getTipooperacao().getIdtipooperacao() == 1) {
				rankingvendas.setValorvenda(rankingvendas.getValorvenda()
						- (contrato.getValorquitar() * (regracoeficiente.getFlatrecebidaregra() / 100)));
			} else {
				rankingvendas.setValorvenda(rankingvendas.getValorvenda()
						- (contrato.getValoroperacao() * (regracoeficiente.getFlatrecebidaregra() / 100)));
			}
			rankingVendasFacade.salvar(rankingvendas);
		}
	}
	
	public void gerarRankingMensal() {
		RankingVendasFacade rankingVendasFacade = new RankingVendasFacade();
		Rankingvendas rankingvendas;
		int mes = Formatacao.getMesData(new Date()) + 1;
		int ano = Formatacao.getAnoData(new Date());
		List<Rankingvendas> listaRanking = rankingVendasFacade.lista("Select r From Rankingvendas r WHERE r.mes=" + mes
				+ " AND r.ano=" + ano + " AND r.usuario.idusuario=" + usuario.getIdusuario());
		if (listaRanking != null && listaRanking.size() > 0) {
			rankingvendas = listaRanking.get(0);
			
		}else {
			rankingvendas = new Rankingvendas();
			rankingvendas.setAno(ano);
			rankingvendas.setUsuario(usuario);
		}
		if (contrato.getParcelaspagas() > 12 && contrato.getTipooperacao().getIdtipooperacao() == 1) {
			rankingvendas.setValorvenda(rankingvendas.getValorvenda()
					+ (contrato.getValorquitar() * (regracoeficiente.getFlatrecebidaregra() / 100)));
		} else {
			rankingvendas.setValorvenda(rankingvendas.getValorvenda()
					+ (contrato.getValoroperacao() * (regracoeficiente.getFlatrecebidaregra() / 100)));
		}
		rankingVendasFacade.salvar(rankingvendas);
	}
	
	
	public void descontarRankingAnual() {
		RankingVendasAnualFacade rankingVendasFacade = new RankingVendasAnualFacade();
		Rankingvendasanual rankingvendas;
		int ano = Formatacao.getAnoData(new Date());
		List<Rankingvendasanual> listaRanking = rankingVendasFacade.lista("Select r From Rankingvendasanual r WHERE "
				+ " r.ano=" + ano + " AND r.usuario.idusuario=" + contrato.getUsuario().getIdusuario());
		if (listaRanking != null && listaRanking.size() > 0) {
			rankingvendas = listaRanking.get(0);
			if (contrato.getParcelaspagas() > 12 && contrato.getTipooperacao().getIdtipooperacao() == 1) {
				rankingvendas.setValorvenda(rankingvendas.getValorvenda()
						- (contrato.getValorquitar() * (regracoeficiente.getFlatrecebidaregra() / 100)));
			} else {
				rankingvendas.setValorvenda(rankingvendas.getValorvenda()
						- (contrato.getValoroperacao() * (regracoeficiente.getFlatrecebidaregra() / 100)));
			}
			rankingVendasFacade.salvar(rankingvendas);
		} 
	}
	
	public void gerarRankingAnual() {
		RankingVendasAnualFacade rankingVendasFacade = new RankingVendasAnualFacade();
		Rankingvendasanual rankingvendas;
		int ano = Formatacao.getAnoData(new Date());
		List<Rankingvendasanual> listaRanking = rankingVendasFacade.lista("Select r From Rankingvendasanual r WHERE "
				+ " r.ano=" + ano + " AND r.usuario.idusuario=" + usuario.getIdusuario());
		if (listaRanking != null && listaRanking.size() > 0) {
			rankingvendas = listaRanking.get(0);
		} else {
			rankingvendas = new Rankingvendasanual();
			rankingvendas.setAno(ano);
			rankingvendas.setUsuario(usuario);
		}
		if (contrato.getParcelaspagas() > 12 && contrato.getTipooperacao().getIdtipooperacao() == 1) {
			rankingvendas.setValorvenda(rankingvendas.getValorvenda()
					+ (contrato.getValorquitar() * (regracoeficiente.getFlatrecebidaregra() / 100)));
		} else {
			rankingvendas.setValorvenda(rankingvendas.getValorvenda()
					+ (contrato.getValoroperacao() * (regracoeficiente.getFlatrecebidaregra() / 100)));
		}
		rankingVendasFacade.salvar(rankingvendas);
	}

	public void alterarComissao() {
		HistoricoComissaoFacade historicoComissaoFacade = new HistoricoComissaoFacade();
		List<Historicocomissao> lista = historicoComissaoFacade.lista("Select h From Historicocomissao h WHERE "
				+ "h.contrato.idcontrato=" + contrato.getIdcontrato());
		if (lista != null && lista.size() > 0) {
			Historicocomissao historicocomissao = lista.get(0);
			historicocomissao.setUsuario(usuario);
			historicoComissaoFacade.salvar(historicocomissao);
		}
	}
	
	
	public void buscarRegraCoeficiente() {
		RegrasCoeficienteFacade regrasCoeficienteFacade = new RegrasCoeficienteFacade();
		regracoeficiente = regrasCoeficienteFacade.consultar(contrato.getIdregracoeficiente());
	}

	
	public void buscarContratos() {
		if (usuarioAtual != null) {
			HistoricoComissaoFacade historicoComissaoFacade = new HistoricoComissaoFacade();
			listaHistoricoComissao = historicoComissaoFacade.lista("SELECT h From Historicocomissao h WHERE"
			 		+ " h.contrato.usuario.idusuario=" + usuarioAtual.getIdusuario());
			if (listaHistoricoComissao == null) {
				listaHistoricoComissao = new ArrayList<Historicocomissao>();
			}
		}
	}
	
	
	public void selecionarTodosContratos() {
		if (selecionarTodos) {
			selecionarTodos = false;
		}else {
			selecionarTodos = true;
		}
		for (int i = 0; i < listaHistoricoComissao.size(); i++) {
			listaHistoricoComissao.get(i).setSelecionado(selecionarTodos);
		}
	}
	
	
	
	
	

}
