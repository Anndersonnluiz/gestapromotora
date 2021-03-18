package br.com.deltafinanceira.managebean.usuario;

import br.com.deltafinanceira.dao.UsuarioDao;
import br.com.deltafinanceira.facade.ContratoFacade;
import br.com.deltafinanceira.facade.HistoricoComissaoFacade;
import br.com.deltafinanceira.facade.UsuarioFacade;
import br.com.deltafinanceira.model.Departamento;
import br.com.deltafinanceira.model.Filial;
import br.com.deltafinanceira.model.Historicocomissao;
import br.com.deltafinanceira.model.Usuario;
import br.com.deltafinanceira.util.Mensagem;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

@Named
@ViewScoped
public class UsuarioMB implements Serializable {
  private static final long serialVersionUID = 1L;
  
  private List<Usuario> listaUsuario;
  
  private Usuario usuario;
  
  private Departamento departamento;
  
  private Filial filial;
  
  @PostConstruct
  public void init() {
    FacesContext fc = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
    this.departamento = (Departamento)session.getAttribute("departamento");
    this.filial = (Filial)session.getAttribute("filial");
    session.removeAttribute("departamento");
    gerarListaUsuario();
  }
  
  public List<Usuario> getListaUsuario() {
    return this.listaUsuario;
  }
  
  public void setListaUsuario(List<Usuario> listaUsuario) {
    this.listaUsuario = listaUsuario;
  }
  
  public Usuario getUsuario() {
    return this.usuario;
  }
  
  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  }
  
  public Departamento getDepartamento() {
    return this.departamento;
  }
  
  public void setDepartamento(Departamento departamento) {
    this.departamento = departamento;
  }
  
  public Filial getFilial() {
    return this.filial;
  }
  
  public void setFilial(Filial filial) {
    this.filial = filial;
  }
  
  public void gerarListaUsuario() {
    UsuarioFacade usuarioFacade = new UsuarioFacade();
    this.listaUsuario = usuarioFacade.listar("Select u From Usuario u Where u.departamento.iddepartamento=" + 
        this.departamento.getIddepartamento() + " order by u.nome");
    if (this.listaUsuario == null)
      this.listaUsuario = new ArrayList<>(); 
  }
  
  public String novoUsuario() {
    FacesContext fc = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
    session.setAttribute("departamento", this.departamento);
    return "cadUsuario";
  }
  
  public String editar(Usuario usuario) {
    FacesContext fc = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
    session.setAttribute("usuario", usuario);
    session.setAttribute("departamento", this.departamento);
    return "cadUsuario";
  }
  
  public void resetarSenhaUsuario(Usuario usuario) {}
  
  public void ativarDesativarUsuario(Usuario usuario) {
    UsuarioFacade usuarioFacade = new UsuarioFacade();
    if (usuario.isAtivo()) {
      usuario.setAtivo(false);
      usuario.setDescricaoativo("x-circle");
    } else {
      usuario.setAtivo(true);
      usuario.setDescricaoativo("check");
    } 
    usuarioFacade.salvar(usuario);
  }
  
  public void resetSenha(Usuario usuario) {
    usuario.setSenha("t+lL5RPpboxFzSPRYideWhLr3pEApCXE683X+k3NiXw=");
    UsuarioDao usuarioDao = new UsuarioDao();
    try {
      usuarioDao.salvar(usuario);
      Mensagem.lancarMensagemInfo("Reset de Senha", "com sucesso!!");
    } catch (SQLException e) {
      e.printStackTrace();
    } 
  }
  
  public void excluirUsuario(Usuario usuario) {
    if (usuario.getIdusuario().intValue() != 13) {
      UsuarioFacade usuarioFacade = new UsuarioFacade();
      Usuario usuarioDelta = usuarioFacade.consultar(13);
      alterarDados(usuario, usuarioDelta);
      usuarioFacade.excluir(usuario.getIdusuario().intValue());
      this.listaUsuario.remove(usuario);
      Mensagem.lancarMensagemInfo("Usuario excluido com sucesso", "");
    } else {
      Mensagem.lancarMensagemFatal("Atenção", "Este usunpode ser excluido");
    } 
  }
  
  public void alterarDados(Usuario usuario, Usuario usuarioDelta) {
    HistoricoComissaoFacade historicoComissaoFacade = new HistoricoComissaoFacade();
    ContratoFacade contratoFacade = new ContratoFacade();
    List<Historicocomissao> listaComissao = historicoComissaoFacade.lista(
        "SELECT h FROM Historicocomissao h WHERE h.contrato.usuario.idusuario=" + usuario.getIdusuario());
    if (listaComissao == null)
      listaComissao = new ArrayList<>(); 
    for (int i = 0; i < listaComissao.size(); i++) {
      ((Historicocomissao)listaComissao.get(i)).getContrato().setUsuario(usuarioDelta);
      contratoFacade.salvar(((Historicocomissao)listaComissao.get(i)).getContrato());
      ((Historicocomissao)listaComissao.get(i)).setUsuario(usuarioDelta);
      historicoComissaoFacade.salvar(listaComissao.get(i));
    } 
  }
  
  public String voltar() {
    FacesContext fc = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
    session.setAttribute("filial", this.filial);
    return "consDepartamento";
  }
}
