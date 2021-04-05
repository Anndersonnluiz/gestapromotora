package br.com.deltafinanceira.managebean.funcionarios;

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

import br.com.deltafinanceira.bean.ControladorCEPBean;
import br.com.deltafinanceira.bean.EnderecoBean;
import br.com.deltafinanceira.facade.BancoFacade;
import br.com.deltafinanceira.facade.ContratoArquivoFacade;
import br.com.deltafinanceira.facade.DadosBancarioFacade;
import br.com.deltafinanceira.facade.FuncionarioArquivoFacade;
import br.com.deltafinanceira.facade.FuncionarioFacade;
import br.com.deltafinanceira.managebean.contrato.AnexarArquivoMB;
import br.com.deltafinanceira.model.Banco;
import br.com.deltafinanceira.model.Dadosbancario;
import br.com.deltafinanceira.model.Funcionario;
import br.com.deltafinanceira.model.Funcionarioarquivo;
import br.com.deltafinanceira.util.Ftp;
import br.com.deltafinanceira.util.Mensagem;

@Named
@ViewScoped
public class CadFuncionariosMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Funcionario funcionario;
	private Dadosbancario dadosbancario;
	private Banco bancoDadosBancario;
	private List<Banco> listaBanco;
	private StreamedContent fileDownload;
	private List<String> listaNomeArquivo;
	private UploadedFile file;
	private boolean arquivoEnviado;
	private List<Funcionarioarquivo> listaFuncionarioArquivo;
	private Funcionarioarquivo funcionarioarquivo;

	@PostConstruct
	public void init() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		funcionario = (Funcionario) session.getAttribute("funcionario");
		session.removeAttribute("funcionario");
		if (funcionario == null) {
			funcionario = new Funcionario();
			dadosbancario = new Dadosbancario();
		} else {
			dadosbancario = funcionario.getDadosbancario();
			bancoDadosBancario = dadosbancario.getBanco();
			gerarListaArquivos();
		}
		gerarListaBanco();
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Dadosbancario getDadosbancario() {
		return dadosbancario;
	}

	public void setDadosbancario(Dadosbancario dadosbancario) {
		this.dadosbancario = dadosbancario;
	}

	public Banco getBancoDadosBancario() {
		return bancoDadosBancario;
	}

	public void setBancoDadosBancario(Banco bancoDadosBancario) {
		this.bancoDadosBancario = bancoDadosBancario;
	}

	public List<Banco> getListaBanco() {
		return listaBanco;
	}

	public void setListaBanco(List<Banco> listaBanco) {
		this.listaBanco = listaBanco;
	}

	public StreamedContent getFileDownload() {
		return fileDownload;
	}

	public void setFileDownload(StreamedContent fileDownload) {
		this.fileDownload = fileDownload;
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

	public List<Funcionarioarquivo> getListaFuncionarioArquivo() {
		return listaFuncionarioArquivo;
	}

	public void setListaFuncionarioArquivo(List<Funcionarioarquivo> listaFuncionarioArquivo) {
		this.listaFuncionarioArquivo = listaFuncionarioArquivo;
	}

	public Funcionarioarquivo getFuncionarioarquivo() {
		return funcionarioarquivo;
	}

	public void setFuncionarioarquivo(Funcionarioarquivo funcionarioarquivo) {
		this.funcionarioarquivo = funcionarioarquivo;
	}

	public String cancelar() {
		return "consFuncionarios";
	}

	public String salvar() {
		FuncionarioFacade funcionarioFacade = new FuncionarioFacade();
		if (bancoDadosBancario == null || bancoDadosBancario.getIdbanco() == null) {
			BancoFacade bancoFacade = new BancoFacade();
			List<Banco> listaBanco = bancoFacade.lista("Select b From Banco b");
			if (listaBanco == null) {
				listaBanco = new ArrayList<Banco>();
			}
			bancoDadosBancario = listaBanco.get(0);
		}
		salvarDadosBancarios();
		funcionario.setDadosbancario(dadosbancario);
		funcionario = funcionarioFacade.salvar(funcionario);
		verificarUpload();
		return "consFuncionarios";
	}

	public void buscarendereco() {
		ControladorCEPBean cep = new ControladorCEPBean();
		cep.setCep(funcionario.getCep());
		EnderecoBean endereco = cep.carregarEndereco();
		if (endereco.getLogradouro() != null) {
			funcionario.setBairro(endereco.getBairro());
			funcionario.setUfestado(endereco.getUf());
			funcionario.setCidade(endereco.getLocalidade());
			funcionario.setComplemento(endereco.getComplemento());
			String logradouro = endereco.getLogradouro().substring(endereco.getLogradouro().indexOf(" "),
					endereco.getLogradouro().length());
			int posicao = endereco.getLogradouro().length();
			for (int i = 0; i <= logradouro.length(); i++) {
				posicao = posicao - 1;
			}
			String tipo = endereco.getLogradouro().substring(0, posicao + 1);
			funcionario.setLogradouro(logradouro);
			funcionario.setTipologradouro(tipo);
		}
	}

	public void salvarDadosBancarios() {
		DadosBancarioFacade dadosBancarioFacade = new DadosBancarioFacade();
		dadosbancario.setBanco(bancoDadosBancario);
		dadosbancario = dadosBancarioFacade.salvar(dadosbancario);
	}

	public void gerarListaBanco() {
		BancoFacade bancoFacade = new BancoFacade();
		listaBanco = bancoFacade.lista("Select b From Banco b WHERE b.visualizar=true ORDER BY b.nome");
		if (listaBanco == null) {
			listaBanco = new ArrayList<Banco>();
		}
	}

	public void verificarUpload() {
		if (listaFuncionarioArquivo == null) {
			listaFuncionarioArquivo = new ArrayList<Funcionarioarquivo>();
		}
		for (int i = 0; i < listaFuncionarioArquivo.size(); i++) {
			FuncionarioArquivoFacade funcionarioArquivoFacade = new FuncionarioArquivoFacade();
			listaFuncionarioArquivo.get(i).setFuncionario(funcionario);
			funcionarioArquivoFacade.salvar(listaFuncionarioArquivo.get(i));
		}
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
			salvarUpload();
		}
	}

	public boolean salvarArquivoFTP() {
		String msg = "";
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
			String nomeArquivoFTP = "";
			arquivoEnviado = ftp.enviarArquivoDOCS(file, nomeArquivoFTP, "");
			if (arquivoEnviado) {
				msg = "Arquivo: " + nomeArquivoFTP + " enviado com sucesso";
				salvarUpload();
			} else {
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

	public void salvarUpload() {
		if (arquivoEnviado) {
			try {
				funcionarioarquivo = new Funcionarioarquivo();
				funcionarioarquivo.setDataupload(new Date());
				funcionarioarquivo.setNomearquivo(new String(file.getFileName().trim().getBytes("ISO-8859-1"), "UTF-8"));
				if (listaFuncionarioArquivo == null) {
					listaFuncionarioArquivo = new ArrayList<Funcionarioarquivo>();
				}
				listaFuncionarioArquivo.add(funcionarioarquivo);
				funcionarioarquivo = new Funcionarioarquivo();
				listaNomeArquivo = new ArrayList<String>();
				file = null;
				arquivoEnviado = false;
				Mensagem.lancarMensagemInfo("Salvo com sucesso", "");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public void excluirArquivo(String ilinha) {
		int linha = Integer.parseInt(ilinha);
		ContratoArquivoFacade contratoArquivoFacade = new ContratoArquivoFacade();
		contratoArquivoFacade.excluir(listaFuncionarioArquivo.get(linha).getIdfuncionarioarquivo());
		if (linha >= 0) {
			listaFuncionarioArquivo.remove(linha);
		}
	}
	
	
	
	public void baixarArquivoFTP(Funcionarioarquivo funcionarioarquivo) {
 
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
			InputStream is = ftp.receberArquivo(funcionarioarquivo.getNomearquivo(), funcionarioarquivo.getNomearquivo(), "");
            ExternalContext externalContext = context.getExternalContext();

            externalContext.responseReset();
            externalContext.setResponseHeader("Content-Disposition", "attachment;filename=" + funcionarioarquivo.getNomearquivo());
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
	
	
	public void gerarListaArquivos() {
		FuncionarioArquivoFacade funcionarioArquivoFacade = new FuncionarioArquivoFacade();
		listaFuncionarioArquivo = funcionarioArquivoFacade.lista("Select f From Funcionarioarquivo f "
				+ "Where f.funcionario.idfuncionario=" + funcionario.getIdfuncionario());
	}
	
	
	
	

}
