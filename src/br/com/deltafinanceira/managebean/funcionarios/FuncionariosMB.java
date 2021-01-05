package br.com.deltafinanceira.managebean.funcionarios;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.deltafinanceira.facade.FuncionarioFacade;
import br.com.deltafinanceira.facade.UsuarioFacade;
import br.com.deltafinanceira.model.Funcionario;
import br.com.deltafinanceira.model.Usuario;
import br.com.deltafinanceira.util.Mensagem;

@Named
@ViewScoped
public class FuncionariosMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nomeFuncionario;
	private String cpf;
	private List<Funcionario> listaFuncionarios;

	@PostConstruct
	public void init() {
		listagemFuncioarios();
	}

	public String getNomeFuncionario() {
		return nomeFuncionario;
	}

	public void setNomeFuncionario(String nomeFuncionario) {
		this.nomeFuncionario = nomeFuncionario;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public List<Funcionario> getListaFuncionarios() {
		return listaFuncionarios;
	}

	public void setListaFuncionarios(List<Funcionario> listaFuncionarios) {
		this.listaFuncionarios = listaFuncionarios;
	}

	public void listagemFuncioarios() {
		FuncionarioFacade funcionarioFacade = new FuncionarioFacade();
		listaFuncionarios = funcionarioFacade.listar("Select f From Funcionario f");
		if (listaFuncionarios == null) {
			listaFuncionarios = new ArrayList<Funcionario>();
		}
	}

	public String novo() {
		return "cadFuncionarios";
	}

	public void pesquisar() {
		String sql = "Select f From Funcionario f WHERE f.nome like '%" + nomeFuncionario + "%' and f.cpf like '%" + cpf
				+ "%'";
		sql = sql + " ORDER BY f.nome";
		FuncionarioFacade funcionarioFacade = new FuncionarioFacade();
		listaFuncionarios = funcionarioFacade.listar(sql);
		if (listaFuncionarios == null) {
			listaFuncionarios = new ArrayList<Funcionario>();
		}
	}

	public void limpar() {
		listagemFuncioarios();
		nomeFuncionario = "";
		cpf = "";
	}

	public String editar(Funcionario funcionario) {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("funcionario", funcionario);
		return "cadFuncionarios";
	}

	public void excluir(Funcionario funcionario) {
		FuncionarioFacade funcionarioFacade = new FuncionarioFacade();
		funcionarioFacade.excluir(funcionario.getIdfuncionario());
		listaFuncionarios.remove(funcionario);
		Mensagem.lancarMensagemInfo("Funcionário excluido com sucesso", "");
	}

}
