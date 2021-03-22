package br.com.deltafinanceira.managebean.banco;

import br.com.deltafinanceira.facade.BancoFacade;
import br.com.deltafinanceira.model.Banco;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

@Named
@ViewScoped
public class BancoMB implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<Banco> listaBanco;

	private Banco banco;

	@PostConstruct
	public void init() {
		gerarListaBanco();
	}

	public List<Banco> getListaBanco() {
		return this.listaBanco;
	}

	public void setListaBanco(List<Banco> listaBanco) {
		this.listaBanco = listaBanco;
	}

	public Banco getBanco() {
		return this.banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}

	public void gerarListaBanco() {
		BancoFacade bancoFacade = new BancoFacade();
		this.listaBanco = bancoFacade.lista("Select b From Banco b Where b.visualizar=true ORDER BY b.nome");
		if (this.listaBanco == null)
			this.listaBanco = new ArrayList<>();
	}

	public String novoBanco() {
		return "cadBanco";
	}

	public String editar(Banco banco) {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("banco", banco);
		return "cadBanco";
	}

	public String listaOrgaos(Banco banco) {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("banco", banco);
		return "consOrgaoBanco";
	}

	public String listaOrgaosRoteiro(Banco banco) {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("banco", banco);
		return "consOrgaoBancoRoteiro";
	}

	public String novaImportacao() {
		return "importacaoCoeficiente";
	}
}
