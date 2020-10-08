package br.com.gestapromotora.managebean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.gestapromotora.dao.MetaFaturamentoAnualDao;
import br.com.gestapromotora.dao.MetaFaturamentoMensalDao;
import br.com.gestapromotora.dao.RankingVendasAnualDao;
import br.com.gestapromotora.dao.RankingVendasDao;
import br.com.gestapromotora.facade.HistoricoComissaoFacade;
import br.com.gestapromotora.model.Historicocomissao;
import br.com.gestapromotora.model.Metafaturamentoanual;
import br.com.gestapromotora.model.Metafaturamentomensal;
import br.com.gestapromotora.model.Rankingvendas;
import br.com.gestapromotora.model.Rankingvendasanual;
import br.com.gestapromotora.util.Formatacao;
import br.com.gestapromotora.util.UsuarioLogadoMB;

@Named
@ViewScoped
public class DashBoardMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private UsuarioLogadoMB usuarioLogadoMB;
	private Rankingvendas primeiroMes;
	private Rankingvendas segundoMes;
	private Rankingvendas terceiroMes;
	private Rankingvendasanual primeiroAno;
	private Rankingvendasanual segundoAno;
	private Rankingvendasanual terceiroAno;
	private List<Metafaturamentomensal>	listaMetaMensal;
	private List<Metafaturamentoanual> listaMetaAnual;
	private String mesAtual;
	private Metafaturamentomensal janeiro;
	private Metafaturamentomensal fevereiro;
	private Metafaturamentomensal marco;
	private Metafaturamentomensal abril;
	private Metafaturamentomensal maio;
	private Metafaturamentomensal junho;
	private Metafaturamentomensal julho;
	private Metafaturamentomensal agosto;
	private Metafaturamentomensal setembro;
	private Metafaturamentomensal outubro;
	private Metafaturamentomensal novembro;
	private Metafaturamentomensal dezembro;
	private float valorPagar;
	private float valorReceber;
	private float fatutamento;
	private int mesatual;
	
	
	
	
	
	@PostConstruct
	public void init() {
		listarMetaMensal();
		listarMetaAnual();
		gerarRankingMensal();
		gerarRankingAnual();
		int mes = Formatacao.getMesData(new Date()) + 1;
		mesAtual = Formatacao.nomeMes(mes);
	}

	public Rankingvendas getPrimeiroMes() {
		return primeiroMes;
	}

	public void setPrimeiroMes(Rankingvendas primeiroMes) {
		this.primeiroMes = primeiroMes;
	}

	public Rankingvendas getSegundoMes() {
		return segundoMes;
	}

	public void setSegundoMes(Rankingvendas segundoMes) {
		this.segundoMes = segundoMes;
	}

	public Rankingvendas getTerceiroMes() {
		return terceiroMes;
	}

	public void setTerceiroMes(Rankingvendas terceiroMes) {
		this.terceiroMes = terceiroMes;
	}

	public Rankingvendasanual getPrimeiroAno() {
		return primeiroAno;
	}

	public void setPrimeiroAno(Rankingvendasanual primeiroAno) {
		this.primeiroAno = primeiroAno;
	}

	public Rankingvendasanual getSegundoAno() {
		return segundoAno;
	}

	public void setSegundoAno(Rankingvendasanual segundoAno) {
		this.segundoAno = segundoAno;
	}

	public Rankingvendasanual getTerceiroAno() {
		return terceiroAno;
	}

	public void setTerceiroAno(Rankingvendasanual terceiroAno) {
		this.terceiroAno = terceiroAno;
	}

	public List<Metafaturamentomensal> getListaMetaMensal() {
		return listaMetaMensal;
	}

	public void setListaMetaMensal(List<Metafaturamentomensal> listaMetaMensal) {
		this.listaMetaMensal = listaMetaMensal;
	}

	public List<Metafaturamentoanual> getListaMetaAnual() {
		return listaMetaAnual;
	}

	public void setListaMetaAnual(List<Metafaturamentoanual> listaMetaAnual) {
		this.listaMetaAnual = listaMetaAnual;
	}
	
	
	public String getMesAtual() {
		return mesAtual;
	}

	public void setMesAtual(String mesAtual) {
		this.mesAtual = mesAtual;
	}
	

	public Metafaturamentomensal getJaneiro() {
		return janeiro;
	}

	public void setJaneiro(Metafaturamentomensal janeiro) {
		this.janeiro = janeiro;
	}

	public Metafaturamentomensal getFevereiro() {
		return fevereiro;
	}

	public void setFevereiro(Metafaturamentomensal fevereiro) {
		this.fevereiro = fevereiro;
	}

	public Metafaturamentomensal getMarco() {
		return marco;
	}

	public void setMarco(Metafaturamentomensal marco) {
		this.marco = marco;
	}

	public Metafaturamentomensal getAbril() {
		return abril;
	}

	public void setAbril(Metafaturamentomensal abril) {
		this.abril = abril;
	}

	public Metafaturamentomensal getMaio() {
		return maio;
	}

	public void setMaio(Metafaturamentomensal maio) {
		this.maio = maio;
	}

	public Metafaturamentomensal getJunho() {
		return junho;
	}

	public void setJunho(Metafaturamentomensal junho) {
		this.junho = junho;
	}

	public Metafaturamentomensal getJulho() {
		return julho;
	}

	public void setJulho(Metafaturamentomensal julho) {
		this.julho = julho;
	}

	public Metafaturamentomensal getAgosto() {
		return agosto;
	}

	public void setAgosto(Metafaturamentomensal agosto) {
		this.agosto = agosto;
	}

	public Metafaturamentomensal getSetembro() {
		return setembro;
	}

	public void setSetembro(Metafaturamentomensal setembro) {
		this.setembro = setembro;
	}

	public Metafaturamentomensal getOutubro() {
		return outubro;
	}

	public void setOutubro(Metafaturamentomensal outubro) {
		this.outubro = outubro;
	}

	public Metafaturamentomensal getNovembro() {
		return novembro;
	}

	public void setNovembro(Metafaturamentomensal novembro) {
		this.novembro = novembro;
	}

	public Metafaturamentomensal getDezembro() {
		return dezembro;
	}

	public void setDezembro(Metafaturamentomensal dezembro) {
		this.dezembro = dezembro;
	}
	
	
	

	public float getFatutamento() {
		return fatutamento;
	}

	public void setFatutamento(float fatutamento) {
		this.fatutamento = fatutamento;
	}

	public int getMesatual() {
		return mesatual;
	}

	public void setMesatual(int mesatual) {
		this.mesatual = mesatual;
	}

	public float getValorPagar() {
		return valorPagar;
	}

	public void setValorPagar(float valorPagar) {
		this.valorPagar = valorPagar;
	}

	public float getValorReceber() {
		return valorReceber;
	}

	public void setValorReceber(float valorReceber) {
		this.valorReceber = valorReceber;
	}

	public void listarMetaMensal() {
		MetaFaturamentoMensalDao metaFaturamentoMensalDao = new MetaFaturamentoMensalDao();
		listaMetaMensal = metaFaturamentoMensalDao.lista("Select m From Metafaturamentomensal m WHERE "
				 + " m.ano=" + Formatacao.getAnoData(new Date()));
		if (listaMetaMensal == null) {
			listaMetaMensal = new ArrayList<Metafaturamentomensal>();
		}
		for (int i = 0; i < listaMetaMensal.size(); i++) {
			if (listaMetaMensal.get(i).getMes() == 1) {
				janeiro = listaMetaMensal.get(i);
			}else if (listaMetaMensal.get(i).getMes() == 2) {
				fevereiro =listaMetaMensal.get(i);
			}else if (listaMetaMensal.get(i).getMes() == 3) {
				marco = listaMetaMensal.get(i);
			}else if (listaMetaMensal.get(i).getMes() == 4) {
				abril = listaMetaMensal.get(i);
			}else if (listaMetaMensal.get(i).getMes() == 5) {
				maio = listaMetaMensal.get(i);
			}else if (listaMetaMensal.get(i).getMes() == 6) {
				junho = listaMetaMensal.get(i);
			}else if (listaMetaMensal.get(i).getMes() == 7) {
				julho = listaMetaMensal.get(i);
			}else if (listaMetaMensal.get(i).getMes() == 8) {
				agosto = listaMetaMensal.get(i);
			}else if (listaMetaMensal.get(i).getMes() == 9) {
				setembro = listaMetaMensal.get(i);
			}else if (listaMetaMensal.get(i).getMes() == 10) {
				outubro = listaMetaMensal.get(i);
			}else if (listaMetaMensal.get(i).getMes() == 11) {
				novembro = listaMetaMensal.get(i);
			}else {
				dezembro = listaMetaMensal.get(i);
			}
		}
		faturamentoMensal();
	}
	
	
	public void listarMetaAnual() {
		MetaFaturamentoAnualDao metaFaturamentoAnualDao = new MetaFaturamentoAnualDao();
		listaMetaAnual = metaFaturamentoAnualDao.lista("Select m From Metafaturamentoanual m WHERE m.ano="
				+ Formatacao.getAnoData(new Date()));
		if (listaMetaAnual == null) {
			listaMetaAnual = new ArrayList<Metafaturamentoanual>();
		}
	}
	
	
	
	public void gerarRankingMensal() {
		RankingVendasDao rankingVendasDao = new RankingVendasDao();
		List<Rankingvendas> listaRanking = rankingVendasDao.lista("Select r From Rankingvendas r WHERE r.mes="
				+ (Formatacao.getMesData(new Date()) + 1) + " AND r.ano=" + Formatacao.getAnoData(new Date())
				+ " ORDER BY r.valorvenda DESC");
		if (listaRanking == null) {
			listaRanking = new ArrayList<Rankingvendas>();
		}
		
		for (int i = 0; i < listaRanking.size(); i++) {
			if (i== 0) {
				primeiroMes = listaRanking.get(i);
				if (primeiroMes == null) {
					primeiroMes = new Rankingvendas();
				}
			}else if(i == 1) {
				segundoMes = listaRanking.get(i);
				if (segundoMes == null) {
					segundoMes = new Rankingvendas();
				}
			}else if(i == 2) {
				terceiroMes = listaRanking.get(i);
				if (terceiroMes == null) {
					terceiroMes = new Rankingvendas();
				}
			}
		}
	}
	
	
	public void gerarRankingAnual() {
		RankingVendasAnualDao rankingVendasDao = new RankingVendasAnualDao();
		List<Rankingvendasanual> listaRanking = rankingVendasDao.lista("Select r From Rankingvendasanual r WHERE "
				+ " r.ano=" + Formatacao.getAnoData(new Date())
				+ " ORDER BY r.valorvenda DESC");
		if (listaRanking == null) {
			listaRanking = new ArrayList<Rankingvendasanual>();
		}
		
		for (int i = 0; i < listaRanking.size(); i++) {
			if (i== 0) {
				primeiroAno = listaRanking.get(i);
				if (primeiroAno == null) {
					primeiroAno = new Rankingvendasanual();
				}
			}else if(i == 1) {
				segundoAno = listaRanking.get(i);
				if (segundoAno == null) {
					segundoAno = new Rankingvendasanual();
				}
			}else if(i == 2) {
				terceiroAno = listaRanking.get(i);
				if (terceiroAno == null) {
					terceiroAno = new Rankingvendasanual();
				}
			}
		}
	}
	
	
	public void faturamentoMensal() {
		mesatual = Formatacao.getMesData(new Date()) + 1;
		HistoricoComissaoFacade historicoComissaoFacade = new HistoricoComissaoFacade();
		String sql = "Select h From Historicocomissao h Where h.tipo<>'Pago' and h.contrato.situacao.identificador<>6"  ;
		if (!usuarioLogadoMB.getUsuario().isAcessogeral()) {
			sql = sql + " and h.usuario.idusuario=" + usuarioLogadoMB.getUsuario().getIdusuario();
		}
		List<Historicocomissao> lista = historicoComissaoFacade.lista(sql);
		if (lista == null) {
			lista = new ArrayList<Historicocomissao>();
		}
		for (int i = 0; i < lista.size(); i++) {
			if (usuarioLogadoMB.getUsuario().isAcessogeral()) {
				fatutamento = fatutamento + lista.get(i).getCmdbruta();
			}else {
				fatutamento = fatutamento + lista.get(i).getCmsliq();
			}
			if (lista.get(i).getContrato().getSituacao().getIdsituacao() == 16) {
				valorPagar = valorPagar + lista.get(i).getCmsliq();
				valorReceber = valorReceber + lista.get(i).getCmdbruta();
			}
		}
	}
	
	
	public String listagemFaturamento() {
		return "consPagamentoComissao";
	}

	
	
	
	
	
	
	

}
