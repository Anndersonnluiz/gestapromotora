package br.com.gestapromotora.managebean.contrato;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import br.com.gestapromotora.dao.TipoArquivoDao;
import br.com.gestapromotora.facade.ContratoArquivoFacade;
import br.com.gestapromotora.model.Contrato;
import br.com.gestapromotora.model.Contratoarquivo;
import br.com.gestapromotora.model.Tipoarquivo;
import br.com.gestapromotora.util.Ftp;
import br.com.gestapromotora.util.Mensagem;

@Named
@ViewScoped
public class AnexarArquivoMB implements Serializable{
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<String> listaNomeArquivo;
	private UploadedFile file;
	private boolean arquivoEnviado;
	private String tipoDocumento;
	private List<Tipoarquivo> listaTipoArquivo;
	private Contratoarquivo contratoarquivo;
	private Contrato contrato;
	private List<Contratoarquivo> listaContratoArquivo;
	private Tipoarquivo tipoarquivo;
	private StreamedContent fileDownload;
	
	
	@PostConstruct
	public void init() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		contrato = (Contrato) session.getAttribute("contrato");
		session.removeAttribute("contrato");
		gerarListaTipoArquivo();
		gerarListaContratoAquivo();
	}
	
	
	
	
	public List<String> getListaNomeArquivo() {
		return listaNomeArquivo;
	}




	public void setListaNomeArquivo(List<String> listaNomeArquivo) {
		this.listaNomeArquivo = listaNomeArquivo;
	}




	public UploadedFile getFile() {
		return file;
	}




	public void setFile(UploadedFile file) {
		this.file = file;
	}




	public boolean isArquivoEnviado() {
		return arquivoEnviado;
	}




	public void setArquivoEnviado(boolean arquivoEnviado) {
		this.arquivoEnviado = arquivoEnviado;
	}




	public String getTipoDocumento() {
		return tipoDocumento;
	}




	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}




	public List<Tipoarquivo> getListaTipoArquivo() {
		return listaTipoArquivo;
	}




	public void setListaTipoArquivo(List<Tipoarquivo> listaTipoArquivo) {
		this.listaTipoArquivo = listaTipoArquivo;
	}




	public Contratoarquivo getContratoarquivo() {
		return contratoarquivo;
	}




	public void setContratoarquivo(Contratoarquivo contratoarquivo) {
		this.contratoarquivo = contratoarquivo;
	}
	
	

	public Contrato getContrato() {
		return contrato;
	}




	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}




	public List<Contratoarquivo> getListaContratoArquivo() {
		return listaContratoArquivo;
	}




	public void setListaContratoArquivo(List<Contratoarquivo> listaContratoArquivo) {
		this.listaContratoArquivo = listaContratoArquivo;
	}

	
	


	public Tipoarquivo getTipoarquivo() {
		return tipoarquivo;
	}




	public void setTipoarquivo(Tipoarquivo tipoarquivo) {
		this.tipoarquivo = tipoarquivo;
	}


	
	
	


	public StreamedContent getFileDownload() {
		return fileDownload;
	}




	public void setFileDownload(StreamedContent fileDownload) {
		this.fileDownload = fileDownload;
	}




	public String voltar() {
		return "consContrato";
	}
	
	
	public void fileUploadListener(FileUploadEvent e) {
		this.file = e.getFile();
		salvarArquivoFTP();
		if (arquivoEnviado) {
			String nome = e.getFile().getFileName();
			try {
				nome = new String(nome.getBytes(Charset.defaultCharset()), "UTF-8");
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
			if (listaNomeArquivo == null) {
				listaNomeArquivo = new ArrayList<String>();
			}
			listaNomeArquivo.add(nome);
		}
	}
	public boolean salvarArquivoFTP() {
		String msg = "";
		//FtpDadosFacade ftpDadosFacade = new FtpDadosFacade();
		//Ftpdados dadosFTP = null;
//		try {
//			dadosFTP = ftpDadosFacade.getFTPDados();
//		} catch (SQLException ex) {
//			Logger.getLogger(CadArquivoMB.class.getName()).log(Level.SEVERE, null, ex);
//			mostrarMensagem(ex, "Erro", "");
//		}
//		if (dadosFTP == null) {
//			return false;
//		}
		Ftp ftp = new Ftp("sistemadeltafinanceira.acessotemporario.net", 
				"deltafinanceiraftp@sistemadeltafinanceira.acessotemporario.net", "780920**Delta");
		try {
			if (!ftp.conectar()) {
				Mensagem.lancarMensagemInfo("Erro", "conectar FTP");
				return false;
			}
		} catch (IOException ex) {
			Logger.getLogger(AnexarArquivoMB.class.getName()).log(Level.SEVERE, null, ex);
			Mensagem.lancarMensagemInfo("Erro", "conectar FTP");
		}    
		try {
			String nomeArquivoFTP = "" +  contrato.getIdcontrato();
			arquivoEnviado = ftp.enviarArquivoDOCS(file, nomeArquivoFTP, "");
			if (arquivoEnviado) {
				msg = "Arquivo: " + nomeArquivoFTP + " enviado com sucesso";
			}else{
				msg = " Erro no nome do arquivo";
			}
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(msg, ""));
			ftp.desconectar();
			return true;
		} catch (IOException ex) {
			Logger.getLogger(AnexarArquivoMB.class.getName()).log(Level.SEVERE, null, ex);
			Mensagem.lancarMensagemInfo("Erro Salvar Arquivo", "" + ex);
		}
		try {
			ftp.desconectar();
		} catch (IOException ex) {
			Logger.getLogger(AnexarArquivoMB.class.getName()).log(Level.SEVERE, null, ex);
			Mensagem.lancarMensagemInfo("Erro", "desconectar FTP");
		}
		return false;
	}
	
	
	
	public void gerarListaTipoArquivo() {
		TipoArquivoDao tipoArquivoDao = new TipoArquivoDao();
		listaTipoArquivo = tipoArquivoDao.listar("Select t From Tipoarquivo t");
		if (listaTipoArquivo == null) {
			listaTipoArquivo = new ArrayList<Tipoarquivo>();
		}
	}
	
	
	
	public void salvar() {
		if (arquivoEnviado) {
			try {
				if (tipoarquivo != null && tipoarquivo.getIdtipoarquivo() != null) {
					contratoarquivo = new Contratoarquivo();
					contratoarquivo.setDataupload(new Date());
					contratoarquivo.setNomearquivo(contrato.getIdcontrato() + "_"
							+ new String(file.getFileName().trim().getBytes("ISO-8859-1"), "UTF-8"));
					contratoarquivo.setContrato(contrato);
					contratoarquivo.setTipoarquivo(tipoarquivo);
					ContratoArquivoFacade contratoArquivoFacade = new ContratoArquivoFacade();
					contratoarquivo = contratoArquivoFacade.salvar(contratoarquivo);
					if (listaContratoArquivo == null) {
						listaContratoArquivo = new ArrayList<Contratoarquivo>();
					}
					listaContratoArquivo.add(contratoarquivo);
					contratoarquivo = new Contratoarquivo();
					listaNomeArquivo = new ArrayList<String>();
					file = null;
					Mensagem.lancarMensagemInfo("Salvo com sucesso", "");
				}else {
					Mensagem.lancarMensagemFatal("Erro", "Favor escolher o tipo de arquivo");
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public void gerarListaContratoAquivo() {
		ContratoArquivoFacade contratoArquivoFacade = new ContratoArquivoFacade();
		if (contrato != null) {
			listaContratoArquivo = contratoArquivoFacade.lista("Select c From Contratoarquivo c WHERE c.contrato.idcontrato=" 
					+ contrato.getIdcontrato());
		}
		if (listaContratoArquivo == null) {
			listaContratoArquivo = new ArrayList<Contratoarquivo>();
		}
	}
	
	
	public void excluirArquivo(String ilinha) {
		int linha = Integer.parseInt(ilinha);
		ContratoArquivoFacade contratoArquivoFacade = new ContratoArquivoFacade();
		contratoArquivoFacade.excluir(listaContratoArquivo.get(linha).getIdcontratoarquivo());
		if (linha >= 0) {
			listaContratoArquivo.remove(linha);
		}
	}
	
	
	
	public void baixarArquivoFTP(Contratoarquivo contratoarquivo) {
 
		Ftp ftp = new Ftp("sistemadeltafinanceira.acessotemporario.net", 
				"deltafinanceiraftp@sistemadeltafinanceira.acessotemporario.net", "780920**Delta");
		try {
			if (!ftp.conectar()) {
				Mensagem.lancarMensagemInfo("Erro", "conectar FTP");
			}
		} catch (IOException ex) {
			Logger.getLogger(AnexarArquivoMB.class.getName()).log(Level.SEVERE, null, ex);
			Mensagem.lancarMensagemInfo("Erro", "conectar FTP");
		}    
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			InputStream is = ftp.receberArquivo(contratoarquivo.getNomearquivo(), contratoarquivo.getNomearquivo(), "");
//			fileDownload = new DefaultStreamedContent(is, "image/jpg", ""+contratoarquivo.getNomearquivo());
//			System.out.println(""+ contratoarquivo.getNomearquivo());
//			fileDownload = new DefaultStreamedContent(is, "image/png", "testenovo.png");
//			ftp.desconectar();
//			System.out.println("" + fileDownload.getContentType());
            ExternalContext externalContext = context.getExternalContext();

            externalContext.responseReset();
           // externalContext.setResponseContentType("image/jpg");
            externalContext.setResponseHeader("Content-Disposition", "attachment;filename=" + contratoarquivo.getNomearquivo());
			 OutputStream outputStream = externalContext.getResponseOutputStream();

	            byte[] buffer = new byte[1024];
	            int length;
	            while ((length = is.read(buffer)) > 0) {
	                outputStream.write(buffer, 0, length);
	            }

	            is.close();
	            context.responseComplete();
		} catch (IOException ex) {
			Logger.getLogger(AnexarArquivoMB.class.getName()).log(Level.SEVERE, null, ex);
			Mensagem.lancarMensagemInfo("Erro Salvar Arquivo", "" + ex);
		}
		try {
			ftp.desconectar();
		} catch (IOException ex) {
			Logger.getLogger(AnexarArquivoMB.class.getName()).log(Level.SEVERE, null, ex);
			Mensagem.lancarMensagemInfo("Erro", "desconectar FTP");
		}
	}
	
	
	

}
