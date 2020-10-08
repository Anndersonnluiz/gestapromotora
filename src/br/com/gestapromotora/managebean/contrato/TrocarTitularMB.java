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

import br.com.gestapromotora.facade.ContratoFacade;
import br.com.gestapromotora.facade.HistoricoComissaoFacade;
import br.com.gestapromotora.facade.RankingVendasAnualFacade;
import br.com.gestapromotora.facade.RankingVendasFacade;
import br.com.gestapromotora.facade.RegrasCoeficienteFacade;
import br.com.gestapromotora.facade.UsuarioFacade;
import br.com.gestapromotora.model.Contrato;
import br.com.gestapromotora.model.Historicocomissao;
import br.com.gestapromotora.model.Rankingvendas;
import br.com.gestapromotora.model.Rankingvendasanual;
import br.com.gestapromotora.model.Regrascoeficiente;
import br.com.gestapromotora.model.Usuario;
import br.com.gestapromotora.util.Formatacao;

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
	
	
	
	@PostConstruct
	public void init() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		contrato = (Contrato) session.getAttribute("contrato");
		session.removeAttribute("contrato");
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



	public void gerarListaUsuario() {
		UsuarioFacade usuarioFacade = new UsuarioFacade();
		listaUsuario = usuarioFacade.listar("Select u From Usuario u");
		if (listaUsuario == null) {
			listaUsuario = new ArrayList<Usuario>();
		}
	}
	
	
	public String salvar() {
		ContratoFacade contratoFacade = new ContratoFacade();
		if (usuario != null && senha.equalsIgnoreCase(contrato.getSenha())) {
			buscarRegraCoeficiente();
			descontarRankingMensal();
			gerarRankingMensal();
			descontarRankingAnual();
			gerarRankingAnual();
			alterarComissao();
			contrato.setUsuario(usuario);
			contrato = contratoFacade.salvar(contrato);
			return "consContrato";
		}
		return "";
	}
	
	
	public String cancelar() {
		return "consContrato";
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

	
	
	
	
	
	
	

}
