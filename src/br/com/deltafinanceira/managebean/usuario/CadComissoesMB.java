package br.com.deltafinanceira.managebean.usuario;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.com.deltafinanceira.model.Tipooperacao;
import br.com.deltafinanceira.model.Usuariocomissao;

@Named
@ViewScoped
public class CadComissoesMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private List<Tipooperacao> listaTipooperacao;
	private Usuariocomissao usuariocomissao;
	
	
	
	@PostConstruct
	public void init() {
		
	}



	public List<Tipooperacao> getListaTipooperacao() {
		return listaTipooperacao;
	}



	public void setListaTipooperacao(List<Tipooperacao> listaTipooperacao) {
		this.listaTipooperacao = listaTipooperacao;
	}



	public Usuariocomissao getUsuariocomissao() {
		return usuariocomissao;
	}



	public void setUsuariocomissao(Usuariocomissao usuariocomissao) {
		this.usuariocomissao = usuariocomissao;
	}
	
	
	
	
	
	

}
