package br.com.deltafinanceira.managebean.notificacao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.deltafinanceira.dao.ClienteDao;
import br.com.deltafinanceira.model.Cliente;
import br.com.deltafinanceira.util.Formatacao;
import br.com.deltafinanceira.util.UsuarioLogadoMB;

@Named
@ViewScoped
public class AniversariantesMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private UsuarioLogadoMB usuarioLogadoMB;
	private List<Cliente> listaCliente;
	
	
	
	@PostConstruct
	public void init() {
		gerarListaCliente();
	}



	public List<Cliente> getListaCliente() {
		return listaCliente;
	}



	public void setListaCliente(List<Cliente> listaCliente) {
		this.listaCliente = listaCliente;
	}
	
	
	
	public void gerarListaCliente() {
		ClienteDao clienteDao = new ClienteDao();
		String diames = "" + Formatacao.getDiaData(new Date()) +  (Formatacao.getMesData(new Date()) + 1);
		String sql = "Select c From Cliente c WHERE c.diames=" + diames;
		if (!this.usuarioLogadoMB.getUsuario().isAcessogeral() && !this.usuarioLogadoMB.getUsuario().isSupervisao()) {
			sql = sql + " AND c.usuario.idusuario=" + this.usuarioLogadoMB.getUsuario().getIdusuario();
		}
		listaCliente = clienteDao.lista(sql);
		if (listaCliente == null) {
			listaCliente = new ArrayList<Cliente>();
		}
	}

}
