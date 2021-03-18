package br.com.deltafinanceira.bean;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ControladorCEPBean implements Serializable {
  private static final long serialVersionUID = 1L;
  
  private List<EnderecoBean> listagem = new ArrayList<>();
  
  private EnderecoBean endereco;
  
  private String cep;
  
  private ServicoEndereco servico = new ServicoEndereco();
  
  public EnderecoBean carregarEndereco() {
    this.endereco = new EnderecoBean();
    Client c = Client.create();
    WebResource wr = c.resource("http://viacep.com.br/ws/" + getCep() + "/json/");
    this.endereco = this.servico.buscarEnderecoPor((String)wr.get(String.class));
    return this.endereco;
  }
  
  public List<EnderecoBean> getListagem() {
    return this.listagem;
  }
  
  public void setListagem(List<EnderecoBean> listagem) {
    this.listagem = listagem;
  }
  
  public EnderecoBean getEndereco() {
    return this.endereco;
  }
  
  public void setEndereco(EnderecoBean endereco) {
    this.endereco = endereco;
  }
  
  public String getCep() {
    return this.cep;
  }
  
  public void setCep(String cep) {
    this.cep = cep;
  }
  
  public ServicoEndereco getServico() {
    return this.servico;
  }
  
  public void setServico(ServicoEndereco servico) {
    this.servico = servico;
  }
}
