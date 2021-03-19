package br.com.deltafinanceira.managebean.banco;

import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import br.com.deltafinanceira.bean.ImportarExcelBean;
import br.com.deltafinanceira.managebean.contrato.AnexarArquivoMB;
import br.com.deltafinanceira.model.Coeficiente;
import br.com.deltafinanceira.util.Ftp;
import br.com.deltafinanceira.util.Mensagem;

@Named
@ViewScoped
public class ImportacaoCoeficienteMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private UploadedFile file;
	private List<Coeficiente> listaCoeficiente;

	@PostConstruct
	public void init() {

	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public List<Coeficiente> getListaCoeficiente() {
		return listaCoeficiente;
	}

	public void setListaCoeficiente(List<Coeficiente> listaCoeficiente) {
		this.listaCoeficiente = listaCoeficiente;
	}

	
	
	
	
	public void fileUploadListener(FileUploadEvent e) {
		this.file = e.getFile();
		ImportarExcelBean importarExcelBean = new ImportarExcelBean();
		try {
			listaCoeficiente = importarExcelBean.importar(file.getInputstream());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		if (listaCoeficiente == null) {
			listaCoeficiente = new ArrayList<Coeficiente>();
		}
	}


}
