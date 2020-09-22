package br.com.gestapromotora.managebean.contrato;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class AnexarArquivoMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@PostConstruct
	public void init() {
		
	}
	
	
	public String voltar() {
		return "consContrato";
	}

}
