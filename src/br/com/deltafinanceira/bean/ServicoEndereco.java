package br.com.deltafinanceira.bean;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.Serializable;

public class ServicoEndereco implements Serializable {
  private static final long serialVersionUID = 1L;
  
  public EnderecoBean buscarEnderecoPor(String urlJson) {
    GsonBuilder gsonBuilder = new GsonBuilder();
    Gson gson = gsonBuilder.create();
    EnderecoBean retornoEndereco = (EnderecoBean)gson.fromJson(urlJson, EnderecoBean.class);
    return retornoEndereco;
  }
}
