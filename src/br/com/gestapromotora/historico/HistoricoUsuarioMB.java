package br.com.gestapromotora.historico;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.com.gestapromotora.facade.HistoricoUsuarioFacade;
import br.com.gestapromotora.facade.UsuarioFacade;
import br.com.gestapromotora.model.Contrato;
import br.com.gestapromotora.model.Historicousuario;
import br.com.gestapromotora.model.Usuario;
import br.com.gestapromotora.util.Formatacao;

@Named
@ViewScoped
public class HistoricoUsuarioMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Usuario usuario;
	private List<Usuario> listaUsuario;
	private Date dataini;
	private Date datafin;
	private List<Historicousuario> listaHistorico;
	private Contrato contrato;
	
	
	
	@PostConstruct
	public void init() {
		gerarListaUsuario();
		gerarHistorico();
	}



	public Usuario getUsuario() {
		return usuario;
	}



	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}



	public List<Usuario> getListaUsuario() {
		return listaUsuario;
	}



	public void setListaUsuario(List<Usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}



	public Date getDataini() {
		return dataini;
	}



	public void setDataini(Date dataini) {
		this.dataini = dataini;
	}



	public Date getDatafin() {
		return datafin;
	}



	public void setDatafin(Date datafin) {
		this.datafin = datafin;
	}



	public List<Historicousuario> getListaHistorico() {
		return listaHistorico;
	}



	public void setListaHistorico(List<Historicousuario> listaHistorico) {
		this.listaHistorico = listaHistorico;
	}
	
	
	
	
	public Contrato getContrato() {
		return contrato;
	}



	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}



	public void gerarListaUsuario() {
		UsuarioFacade usuarioFacade = new UsuarioFacade();
		listaUsuario = usuarioFacade.listar("Select u From Usuario u order by u.nome");
		if (listaUsuario == null) {
			listaUsuario = new ArrayList<Usuario>();
		}
	}
	
	
	public void gerarHistorico() {
		HistoricoUsuarioFacade historicoUsuarioFacade = new HistoricoUsuarioFacade();
		listaHistorico = historicoUsuarioFacade.lista("Select h From Historicousuario h Where h.datacadastro='" 
				+ Formatacao.ConvercaoDataSql(new Date()) + "'");
		if (listaHistorico == null) {
			listaHistorico = new ArrayList<Historicousuario>();
		}
	}
	
	
	
	public void pesquisar() {
		HistoricoUsuarioFacade historicoUsuarioFacade = new HistoricoUsuarioFacade();
		String sql = "Select h From Historicousuario h Where h.descricao like '%%'";
		if (usuario != null && usuario.getIdusuario() != null) {
			sql = sql + " and h.usuario.idusuario=" + usuario.getIdusuario();
		}
		
		if (dataini != null && datafin != null) {
			sql = sql + " and h.datacadastro>='" + Formatacao.ConvercaoDataSql(dataini) + 
					"' and h.datacadastro<='" + Formatacao.ConvercaoDataSql(datafin) + "'";
		}
		
		listaHistorico = historicoUsuarioFacade.lista(sql);
		if (listaHistorico == null) {
			listaHistorico = new ArrayList<Historicousuario>();
		}
	}
	
	
	public void limpar() {
		usuario = null;
		datafin = null;
		dataini = null;
		gerarHistorico();
	}
	
	
	
	
	

}
