package br.com.gestapromotora.managebean.contrato.portabilidade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.event.RowEditEvent;

import br.com.gestapromotora.facade.ContratoFacade;
import br.com.gestapromotora.model.Contrato;
import br.com.gestapromotora.util.Mensagem;

@Named
@ViewScoped
public class PortabilidadeMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Contrato> listaContrato;
	private String situacao;
	
	
	
	@PostConstruct
	public void init() {
		gerarListaPortabilidade();
	}



	public List<Contrato> getListaContrato() {
		return listaContrato;
	}



	public void setListaContrato(List<Contrato> listaContrato) {
		this.listaContrato = listaContrato;
	}
	
	
	public String getSituacao() {
		return situacao;
	}



	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}



	public void gerarListaPortabilidade() {
		ContratoFacade contratoFacade = new ContratoFacade();
		listaContrato = contratoFacade.lista("Select c From Contrato c WHERE c.tipooperacao.descricao like '%Portabilidade%'");
		if (listaContrato == null) {
			listaContrato = new ArrayList<Contrato>();
		}
	}
	
	
	public void salvarSituacao(RowEditEvent event) {
		Contrato contrato = (Contrato) event.getObject();
		ContratoFacade contratoFacade = new ContratoFacade();
		contrato.setSituacao(situacao);
		contrato = contratoFacade.salvar(contrato);
		Mensagem.lancarMensagemInfo("Salvo com sucesso", "");
		gerarListaPortabilidade();
	}
	
	
	
	
	
	

}
