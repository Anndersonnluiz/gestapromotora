package br.com.deltafinanceira.managebean.contrato;

import br.com.deltafinanceira.dao.TipoArquivoDao;
import br.com.deltafinanceira.facade.ContratoArquivoFacade;
import br.com.deltafinanceira.model.Contrato;
import br.com.deltafinanceira.model.Contratoarquivo;
import br.com.deltafinanceira.model.Tipoarquivo;
import br.com.deltafinanceira.util.Ftp;
import br.com.deltafinanceira.util.Mensagem;
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

@Named
@ViewScoped
public class AnexarArquivoMB implements Serializable {
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
  
  private String voltarTela;
  
  @PostConstruct
  public void init() {
    FacesContext fc = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
    this.contrato = (Contrato)session.getAttribute("contrato");
    this.voltarTela = (String)session.getAttribute("voltarTela");
    session.removeAttribute("contrato");
    session.removeAttribute("voltarTela");
    gerarListaTipoArquivo();
    gerarListaContratoAquivo();
  }
  
  public List<String> getListaNomeArquivo() {
    return this.listaNomeArquivo;
  }
  
  public void setListaNomeArquivo(List<String> listaNomeArquivo) {
    this.listaNomeArquivo = listaNomeArquivo;
  }
  
  public UploadedFile getFile() {
    return this.file;
  }
  
  public void setFile(UploadedFile file) {
    this.file = file;
  }
  
  public boolean isArquivoEnviado() {
    return this.arquivoEnviado;
  }
  
  public void setArquivoEnviado(boolean arquivoEnviado) {
    this.arquivoEnviado = arquivoEnviado;
  }
  
  public String getTipoDocumento() {
    return this.tipoDocumento;
  }
  
  public void setTipoDocumento(String tipoDocumento) {
    this.tipoDocumento = tipoDocumento;
  }
  
  public List<Tipoarquivo> getListaTipoArquivo() {
    return this.listaTipoArquivo;
  }
  
  public void setListaTipoArquivo(List<Tipoarquivo> listaTipoArquivo) {
    this.listaTipoArquivo = listaTipoArquivo;
  }
  
  public Contratoarquivo getContratoarquivo() {
    return this.contratoarquivo;
  }
  
  public void setContratoarquivo(Contratoarquivo contratoarquivo) {
    this.contratoarquivo = contratoarquivo;
  }
  
  public Contrato getContrato() {
    return this.contrato;
  }
  
  public void setContrato(Contrato contrato) {
    this.contrato = contrato;
  }
  
  public List<Contratoarquivo> getListaContratoArquivo() {
    return this.listaContratoArquivo;
  }
  
  public void setListaContratoArquivo(List<Contratoarquivo> listaContratoArquivo) {
    this.listaContratoArquivo = listaContratoArquivo;
  }
  
  public Tipoarquivo getTipoarquivo() {
    return this.tipoarquivo;
  }
  
  public void setTipoarquivo(Tipoarquivo tipoarquivo) {
    this.tipoarquivo = tipoarquivo;
  }
  
  public StreamedContent getFileDownload() {
    return this.fileDownload;
  }
  
  public void setFileDownload(StreamedContent fileDownload) {
    this.fileDownload = fileDownload;
  }
  
  public String voltar() {
    return this.voltarTela;
  }
  
  public void fileUploadListener(FileUploadEvent e) {
    this.file = e.getFile();
    salvarArquivoFTP();
    if (this.arquivoEnviado) {
      String nome = e.getFile().getFileName();
      try {
        nome = new String(nome.getBytes(Charset.defaultCharset()), "UTF-8");
      } catch (UnsupportedEncodingException e1) {
        e1.printStackTrace();
      } 
      if (this.listaNomeArquivo == null)
        this.listaNomeArquivo = new ArrayList<>(); 
      this.listaNomeArquivo.add(String.valueOf(this.contrato.getCliente().getCpf()) + "_" + nome);
    } 
  }
  
  public boolean salvarArquivoFTP() {
    String msg = "";
    Ftp ftp = new Ftp("sistemasdelta.com.br", 
        "deltafinanceiraftp@sistemadeltafinanceira.acessotemporario.net", "780920**Delta");
    try {
      if (!ftp.conectar()) {
        Mensagem.lancarMensagemInfo("Erro", "conectar FTP");
        return false;
      } 
    } catch (IOException ex) {
      Logger.getLogger(AnexarArquivoMB.class.getName()).log(Level.SEVERE, (String)null, ex);
      Mensagem.lancarMensagemInfo("Erro", "conectar FTP");
    } 
    try {
      String nomeArquivoFTP = "";
      this.arquivoEnviado = ftp.enviarArquivoDOCS(this.file, nomeArquivoFTP, "");
      if (this.arquivoEnviado) {
        msg = "Arquivo: " + nomeArquivoFTP + " enviado com sucesso";
      } else {
        msg = " Erro no nome do arquivo";
      } 
      FacesContext context = FacesContext.getCurrentInstance();
      context.addMessage(null, new FacesMessage(msg, ""));
      ftp.desconectar();
      return true;
    } catch (IOException ex) {
      Logger.getLogger(AnexarArquivoMB.class.getName()).log(Level.SEVERE, (String)null, ex);
      Mensagem.lancarMensagemInfo("Erro Salvar Arquivo", "" + ex);
      try {
        ftp.desconectar();
      } catch (IOException iOException) {
        Logger.getLogger(AnexarArquivoMB.class.getName()).log(Level.SEVERE, (String)null, iOException);
        Mensagem.lancarMensagemInfo("Erro", "desconectar FTP");
      } 
      return false;
    } 
  }
  
  public void gerarListaTipoArquivo() {
    TipoArquivoDao tipoArquivoDao = new TipoArquivoDao();
    this.listaTipoArquivo = tipoArquivoDao.listar("Select t From Tipoarquivo t order by t.descricao");
    if (this.listaTipoArquivo == null)
      this.listaTipoArquivo = new ArrayList<>(); 
  }
  
  public void salvar() {
    if (this.arquivoEnviado)
      try {
        if (this.tipoarquivo != null && this.tipoarquivo.getIdtipoarquivo() != null) {
          this.contratoarquivo = new Contratoarquivo();
          this.contratoarquivo.setDataupload(new Date());
          this.contratoarquivo.setNomearquivo(String.valueOf(this.contrato.getCliente().getCpf()) + "_" + 
              new String(this.file.getFileName().trim().getBytes("ISO-8859-1"), "UTF-8"));
          this.contratoarquivo.setContrato(this.contrato);
          this.contratoarquivo.setTipoarquivo(this.tipoarquivo);
          ContratoArquivoFacade contratoArquivoFacade = new ContratoArquivoFacade();
          this.contratoarquivo = contratoArquivoFacade.salvar(this.contratoarquivo);
          if (this.listaContratoArquivo == null)
            this.listaContratoArquivo = new ArrayList<>(); 
          this.listaContratoArquivo.add(this.contratoarquivo);
          this.contratoarquivo = new Contratoarquivo();
          this.listaNomeArquivo = new ArrayList<>();
          this.file = null;
          Mensagem.lancarMensagemInfo("Salvo com sucesso", "");
        } else {
          Mensagem.lancarMensagemFatal("Erro", "Favor escolher o tipo de arquivo");
        } 
      } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
      }  
  }
  
  public void gerarListaContratoAquivo() {
    ContratoArquivoFacade contratoArquivoFacade = new ContratoArquivoFacade();
    if (this.contrato != null)
      this.listaContratoArquivo = contratoArquivoFacade.lista("Select c From Contratoarquivo c WHERE c.contrato.idcontrato=" + 
          this.contrato.getIdcontrato()); 
    if (this.listaContratoArquivo == null)
      this.listaContratoArquivo = new ArrayList<>(); 
  }
  
  public void excluirArquivo(String ilinha) {
    int linha = Integer.parseInt(ilinha);
    ContratoArquivoFacade contratoArquivoFacade = new ContratoArquivoFacade();
    contratoArquivoFacade.excluir(((Contratoarquivo)this.listaContratoArquivo.get(linha)).getIdcontratoarquivo().intValue());
    if (linha >= 0)
      this.listaContratoArquivo.remove(linha); 
  }
  
  public void baixarArquivoFTP(Contratoarquivo contratoarquivo) {
    Ftp ftp = new Ftp("sistemasdelta.com.br", 
        "deltafinanceiraftp@sistemadeltafinanceira.acessotemporario.net", "780920**Delta");
    try {
      if (!ftp.conectar())
        Mensagem.lancarMensagemInfo("Erro", "conectar FTP"); 
    } catch (IOException ex) {
      Logger.getLogger(AnexarArquivoMB.class.getName()).log(Level.SEVERE, (String)null, ex);
      Mensagem.lancarMensagemInfo("Erro", "conectar FTP");
    } 
    try {
      FacesContext context = FacesContext.getCurrentInstance();
      InputStream is = ftp.receberArquivo(contratoarquivo.getNomearquivo(), contratoarquivo.getNomearquivo(), "");
      ExternalContext externalContext = context.getExternalContext();
      externalContext.responseReset();
      externalContext.setResponseHeader("Content-Disposition", "attachment;filename=" + contratoarquivo.getNomearquivo());
      OutputStream outputStream = externalContext.getResponseOutputStream();
      byte[] buffer = new byte[1024];
      int length;
      while ((length = is.read(buffer)) > 0)
        outputStream.write(buffer, 0, length); 
      is.close();
      context.responseComplete();
    } catch (IOException ex) {
      Logger.getLogger(AnexarArquivoMB.class.getName()).log(Level.SEVERE, (String)null, ex);
      Mensagem.lancarMensagemInfo("Erro Salvar Arquivo", "" + ex);
    } 
    try {
      ftp.desconectar();
    } catch (IOException ex) {
      Logger.getLogger(AnexarArquivoMB.class.getName()).log(Level.SEVERE, (String)null, ex);
      Mensagem.lancarMensagemInfo("Erro", "desconectar FTP");
    } 
  }
}
