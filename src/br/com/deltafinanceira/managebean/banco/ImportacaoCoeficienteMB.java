package br.com.deltafinanceira.managebean.banco;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import br.com.deltafinanceira.bean.DadosBean;
import br.com.deltafinanceira.bean.ImportExcelCoeficienteBean;
import br.com.deltafinanceira.facade.BancoFacade;
import br.com.deltafinanceira.facade.CoeficienteFacade;
import br.com.deltafinanceira.facade.OrgaoBancoFacade;
import br.com.deltafinanceira.facade.TipoOperacaoFacade;
import br.com.deltafinanceira.model.Banco;
import br.com.deltafinanceira.model.Coeficiente;
import br.com.deltafinanceira.model.OrgaoBanco;
import br.com.deltafinanceira.model.Tipooperacao;
import br.com.deltafinanceira.util.Mensagem;

@Named
@ViewScoped
public class ImportacaoCoeficienteMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private UploadedFile file;
	private List<Coeficiente> listaCoeficiente;
	private List<DadosBean> listaDados;

	@PostConstruct
	public void init() {

	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public List<Coeficiente> getListaCoeficiente() {
		return listaCoeficiente;
	}

	public void setListaCoeficiente(List<Coeficiente> listaCoeficiente) {
		this.listaCoeficiente = listaCoeficiente;
	}

	public List<DadosBean> getListaDados() {
		return listaDados;
	}

	public void setListaDados(List<DadosBean> listaDados) {
		this.listaDados = listaDados;
	}

	public void fileUploadListener(FileUploadEvent e) {
		this.file = e.getFile();
		ImportExcelCoeficienteBean importarExcelBean = new ImportExcelCoeficienteBean();
		try {
			listaDados = importarExcelBean.importar(file.getInputstream());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		if (listaDados == null) {
			listaDados = new ArrayList<DadosBean>();
		}
		Mensagem.lancarMensagemInfo("Importação feita com sucesso", "");
	}

	public String salvar() {
		CoeficienteFacade coeficienteFacade = new CoeficienteFacade();
		for (int i = 0; i < listaDados.size(); i++) {
			Coeficiente coeficiente = new Coeficiente();
			coeficiente.setNometabela(listaDados.get(i).getNomeTabela());
			coeficiente.setComissaocorretor(listaDados.get(0).getComissaocorretor());
			coeficiente.setComissaoloja(listaDados.get(i).getComissaoloja());
			Banco banco = buscarBanco(listaDados.get(i).getNomeOrgao(), listaDados.get(i).getNomeBanco());
			coeficiente.setOrgaoBanco(
					buscarOrgaoBanco(listaDados.get(i).getNomeOrgao(), banco));
			coeficiente.setTipooperacao(buscarOperacao(listaDados.get(i).getTipoOperacao()));
			coeficiente.setCoeficientevalor(listaDados.get(i).getCoeficiente());
			coeficienteFacade.salvar(coeficiente);
		}
		Mensagem.lancarMensagemInfo("Salvo com sucesso!!", "");
		return "importacaoCoeficiente";
	}

	public Banco buscarBanco(String nomeOrgao, String nomeBanco) {
		BancoFacade bancoFacade = new BancoFacade();
		List<Banco> listaBanco = bancoFacade
				.lista("Select b From Banco b Where" + " b.nome like '%" + nomeBanco + "%'");
		if (listaBanco == null || listaBanco.isEmpty()) {
			Banco novoBanco = new Banco();
			novoBanco.setCodigo(000);
			novoBanco.setNome(nomeBanco);
			novoBanco.setVisualizar(true);
			novoBanco = bancoFacade.salvar(novoBanco);
			return novoBanco;
		} else {
			return listaBanco.get(0);
		}
	}

	public OrgaoBanco buscarOrgaoBanco(String nomeOrgao, Banco banco) {
		OrgaoBancoFacade orgaoBancoFacade = new OrgaoBancoFacade();
		List<OrgaoBanco> listaOrgaoBanco = orgaoBancoFacade
				.lista("Select o From OrgaoBanco" + " o Where o.nome like '%" + nomeOrgao + "%'"
						+ " and o.banco.idbanco=" + banco.getIdbanco());
		if (listaOrgaoBanco == null || listaOrgaoBanco.isEmpty()) {
			OrgaoBanco novoOrgaoBanco = new OrgaoBanco();
			novoOrgaoBanco.setDemaisopeinss(false);
			novoOrgaoBanco.setLink("");
			novoOrgaoBanco.setNome(nomeOrgao);
			novoOrgaoBanco.setBanco(banco);
			novoOrgaoBanco = orgaoBancoFacade.salvar(novoOrgaoBanco);
			return novoOrgaoBanco;
		} else {
			return listaOrgaoBanco.get(0);
		}
	}

	public Tipooperacao buscarOperacao(String nomeOperacao) {
		TipoOperacaoFacade tipoOperacaoFacade = new TipoOperacaoFacade();
		List<Tipooperacao> listaOperacao = tipoOperacaoFacade
				.lista("Select b From " + "Tipooperacao b Where b.descricao like '%" + nomeOperacao + "%'");
		if (listaOperacao != null && !listaOperacao.isEmpty()) {
			Tipooperacao novaOperacao = new Tipooperacao();
			novaOperacao.setDescricao(nomeOperacao);
			novaOperacao.setMargem(true);
			novaOperacao = tipoOperacaoFacade.salvar(novaOperacao);
			return novaOperacao;
		}
		return null;
	}

	public String cancelar() {
		return "consBanco";
	}

}
