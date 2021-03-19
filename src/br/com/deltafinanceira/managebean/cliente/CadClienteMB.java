package br.com.deltafinanceira.managebean.cliente;

import br.com.deltafinanceira.bean.ControladorCEPBean;
import br.com.deltafinanceira.bean.EnderecoBean;
import br.com.deltafinanceira.facade.BancoFacade;
import br.com.deltafinanceira.facade.ClienteFacade;
import br.com.deltafinanceira.facade.DadosBancarioFacade;
import br.com.deltafinanceira.model.Banco;
import br.com.deltafinanceira.model.Cliente;
import br.com.deltafinanceira.model.Dadosbancario;
import br.com.deltafinanceira.util.Formatacao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

@Named
@ViewScoped
public class CadClienteMB implements Serializable {
  private static final long serialVersionUID = 1L;
  
  private Cliente cliente;
  
  private Dadosbancario dadosbancario;
  
  private Banco bancoDadosBancario;
  
  private List<Banco> listaBanco;
  
  @PostConstruct
  public void init() {
    FacesContext fc = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
    this.cliente = (Cliente)session.getAttribute("cliente");
    session.removeAttribute("cliente");
    gerarListaBanco();
    if (this.cliente == null) {
      this.cliente = new Cliente();
      this.dadosbancario = new Dadosbancario();
    } else {
      buscarDadosBancarios(this.cliente.getDadosbancario());
      this.bancoDadosBancario = this.dadosbancario.getBanco();
    } 
  }
  
  public Cliente getCliente() {
    return this.cliente;
  }
  
  public void setCliente(Cliente cliente) {
    this.cliente = cliente;
  }
  
  public Dadosbancario getDadosbancario() {
    return this.dadosbancario;
  }
  
  public void setDadosbancario(Dadosbancario dadosbancario) {
    this.dadosbancario = dadosbancario;
  }
  
  public Banco getBancoDadosBancario() {
    return this.bancoDadosBancario;
  }
  
  public void setBancoDadosBancario(Banco bancoDadosBancario) {
    this.bancoDadosBancario = bancoDadosBancario;
  }
  
  public List<Banco> getListaBanco() {
    return this.listaBanco;
  }
  
  public void setListaBanco(List<Banco> listaBanco) {
    this.listaBanco = listaBanco;
  }
  
  public String cancelar() {
    return "consCliente";
  }
  
  public String salvar() {
    ClienteFacade clienteFacade = new ClienteFacade();
    if (this.bancoDadosBancario == null || this.bancoDadosBancario.getIdbanco() == null) {
      BancoFacade bancoFacade = new BancoFacade();
      List<Banco> listaBanco = bancoFacade.lista("Select b From Banco b");
      if (listaBanco == null)
        listaBanco = new ArrayList<>(); 
      this.bancoDadosBancario = listaBanco.get(0);
    } 
    salvarDadosBancarios();
    this.cliente.setDadosbancario(this.dadosbancario);
    if (this.cliente.getNascimento() == null)
      this.cliente.setNascimento(new Date()); 
    String diames = "" + Formatacao.getDiaData(this.cliente.getNascimento()) + (Formatacao.getMesData(this.cliente.getNascimento()) + 1);
    this.cliente.setDiames(Integer.parseInt(diames));
    clienteFacade.salvar(this.cliente);
    return "consCliente";
  }
  
  public void salvarDadosBancarios() {
    DadosBancarioFacade dadosBancarioFacade = new DadosBancarioFacade();
    this.dadosbancario.setBanco(this.bancoDadosBancario);
    this.dadosbancario = dadosBancarioFacade.salvar(this.dadosbancario);
  }
  
  public void buscarDadosBancarios(Dadosbancario dadosbancario) {
    DadosBancarioFacade dadosBancarioFacade = new DadosBancarioFacade();
    this.dadosbancario = dadosBancarioFacade.consultar(dadosbancario.getIddadosbancario().intValue());
    if (this.dadosbancario == null)
      this.dadosbancario = new Dadosbancario(); 
  }
  
  public void buscarendereco() {
    ControladorCEPBean cep = new ControladorCEPBean();
    cep.setCep(this.cliente.getCep());
    EnderecoBean endereco = cep.carregarEndereco();
    if (endereco.getLogradouro() != null) {
      this.cliente.setBairro(endereco.getBairro());
      this.cliente.setUfestado(endereco.getUf());
      this.cliente.setCidade(endereco.getLocalidade());
      this.cliente.setComplemento(endereco.getComplemento());
      String logradouro = endereco.getLogradouro().substring(endereco.getLogradouro().indexOf(" "), endereco.getLogradouro().length());
      int posicao = endereco.getLogradouro().length();
      for (int i = 0; i <= logradouro.length(); i++)
        posicao--; 
      String tipo = endereco.getLogradouro().substring(0, posicao + 1);
      this.cliente.setLogradouro(logradouro);
      this.cliente.setTipologradouro(tipo);
    } 
  }
  
  public void gerarListaBanco() {
    BancoFacade bancoFacade = new BancoFacade();
    this.listaBanco = bancoFacade.lista("Select b From Banco b WHERE b.visualizar=true ORDER BY b.nome");
    if (this.listaBanco == null)
      this.listaBanco = new ArrayList<>(); 
  }
}
