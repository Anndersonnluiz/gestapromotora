package br.com.deltafinanceira.bean;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import br.eti.rogerioaguilar.minhasclasses.util.excel.leitor.LeitorExcel;
import br.eti.rogerioaguilar.minhasclasses.util.excel.leitor.exception.ListenerException;
import br.eti.rogerioaguilar.minhasclasses.util.excel.leitor.exception.PlanilhaNaoEncontradaException;
import br.eti.rogerioaguilar.minhasclasses.util.excel.leitor.gramatica.impl.ParseException;
import br.eti.rogerioaguilar.minhasclasses.util.excel.leitor.listener.ColunaListener;
import br.eti.rogerioaguilar.minhasclasses.util.excel.leitor.util.LinhaColunaListenerVo;

public class ImportExcelClienteBean {

	public List<ClienteBean> importar(InputStream is) {
		LeitorExcel leitor = null;

		// Lista que irá guardar os dados da planilha
		final List<ClienteBean> listaClienteBean = new LinkedList<ClienteBean>();

		leitor = new LeitorExcel("[*,*]", 1, is, null,

				new ColunaListener() {
					ClienteBean clienteVO = null;

					@Override
					public boolean lendoColuna(int linha, int coluna, @SuppressWarnings("rawtypes") Map dadosColuna) throws ListenerException {
						LinhaColunaListenerVo voAtual = (LinhaColunaListenerVo) dadosColuna
								.get(ColunaListener.CHAVE_VO_COLUNA);
						if (linha > 1) { // Pula primeira linha pois é a linha que possui o título
							switch (coluna) {
							case 1: //Coluna
								if (clienteVO == null) {
									clienteVO = new ClienteBean();
								}
								String nome = voAtual.getCelulaAtual().getStringCellValue();
								clienteVO.setNome(nome);
								break;
							case 2: //Coluna
								if (clienteVO == null) {
									clienteVO = new ClienteBean();
								}
								String cpf = voAtual.getCelulaAtual().getStringCellValue();
								clienteVO.setCpf(cpf);
								break;
							case 3:// Coluna
								if (clienteVO == null) {
									clienteVO = new ClienteBean();
								}
								Date nascimento = voAtual.getCelulaAtual().getDateCellValue();
								clienteVO.setNascimento(nascimento);
								break;
							case 4:// Coluna
								if (clienteVO == null) {
									clienteVO = new ClienteBean();
								}
								String rg = voAtual.getCelulaAtual().getStringCellValue();
								clienteVO.setRg(rg);
								break;
							case 5:// Coluna
								if (clienteVO == null) {
									clienteVO = new ClienteBean();
								}
								String ufrg = voAtual.getCelulaAtual().getStringCellValue();
								clienteVO.setUfrg(ufrg);
								break;
							case 6:// Coluna
								if (clienteVO == null) {
									clienteVO = new ClienteBean();
								}
								String nomepai = voAtual.getCelulaAtual().getStringCellValue();
								clienteVO.setNomepai(nomepai);
								break;
							case 7:// Coluna
								if (clienteVO == null) {
									clienteVO = new ClienteBean();
								}
								String nomemae = voAtual.getCelulaAtual().getStringCellValue();
								clienteVO.setNomemae(nomemae);
							case 8:// Coluna
								if (clienteVO == null) {
									clienteVO = new ClienteBean();
								}
								String cep = voAtual.getCelulaAtual().getStringCellValue();
								clienteVO.setCep(cep);
								break;
							case 9:// Coluna
								if (clienteVO == null) {
									clienteVO = new ClienteBean();
								}
								String endereco = voAtual.getCelulaAtual().getStringCellValue();
								clienteVO.setLogradouro(endereco);
								break;
							case 10:// Coluna
								if (clienteVO == null) {
									clienteVO = new ClienteBean();
								}
								String telefone = voAtual.getCelulaAtual().getStringCellValue();
								clienteVO.setTelefonecelular(telefone);
								break;
							case 11:// Coluna
								if (clienteVO == null) {
									clienteVO = new ClienteBean();
								}
								String email = voAtual.getCelulaAtual().getStringCellValue();
								clienteVO.setEmail(email);
								
								listaClienteBean.add(clienteVO);
								clienteVO = null;
							}
						}

						return true;

					}
				});

		try {
			leitor.processarLeituraPlanilha();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (PlanilhaNaoEncontradaException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ListenerException e) {
			e.printStackTrace();
		}

		return listaClienteBean;
	}
}
