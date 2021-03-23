package br.com.deltafinanceira.bean;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import br.eti.rogerioaguilar.minhasclasses.util.excel.leitor.LeitorExcel;
import br.eti.rogerioaguilar.minhasclasses.util.excel.leitor.exception.ListenerException;
import br.eti.rogerioaguilar.minhasclasses.util.excel.leitor.exception.PlanilhaNaoEncontradaException;
import br.eti.rogerioaguilar.minhasclasses.util.excel.leitor.gramatica.impl.ParseException;
import br.eti.rogerioaguilar.minhasclasses.util.excel.leitor.listener.ColunaListener;
import br.eti.rogerioaguilar.minhasclasses.util.excel.leitor.util.LinhaColunaListenerVo;

public class ImportExcelCoeficienteBean {
	

	public List<DadosBean> importar(InputStream is) {
		LeitorExcel leitor = null;

		// Lista que irá guardar os dados da planilha
		final List<DadosBean> listaDadosBean = new LinkedList<DadosBean>();

		leitor = new LeitorExcel("[*,*]", 1, is, null,

				new ColunaListener() {
					DadosBean dadosVO = null;

					@Override
					public boolean lendoColuna(int linha, int coluna, @SuppressWarnings("rawtypes") Map dadosColuna) throws ListenerException {
						LinhaColunaListenerVo voAtual = (LinhaColunaListenerVo) dadosColuna
								.get(ColunaListener.CHAVE_VO_COLUNA);
						if (linha > 1) { // Pula primeira linha pois é a linha que possui o título
							switch (coluna) {
							case 1: //Coluna
								if (dadosVO == null) {
									dadosVO = new DadosBean();
								}
								String nomeBanco = voAtual.getCelulaAtual().getStringCellValue();
								dadosVO.setNomeBanco(nomeBanco);
								break;
							case 2: //Coluna
								if (dadosVO == null) {
									dadosVO = new DadosBean();
								}
								String nomeOrgao = voAtual.getCelulaAtual().getStringCellValue();
								dadosVO.setNomeOrgao(nomeOrgao);
								break;
							case 3:// Coluna
								if (dadosVO == null) {
									dadosVO = new DadosBean();
								}
								String nomeTabela = voAtual.getCelulaAtual().getStringCellValue();
								dadosVO.setNomeTabela(nomeTabela);
								break;
							case 4:// Coluna
								if (dadosVO == null) {
									dadosVO = new DadosBean();
								}
								Double coeficiente = voAtual.getCelulaAtual().getNumericCellValue();
								dadosVO.setCoeficiente(coeficiente.floatValue());
								break;
							case 5:// Coluna
								if (dadosVO == null) {
									dadosVO = new DadosBean();
								}
								Double comissaoloja = voAtual.getCelulaAtual().getNumericCellValue();
								dadosVO.setComissaoloja(comissaoloja.floatValue());
								break;
							case 6:// Coluna
								if (dadosVO == null) {
									dadosVO = new DadosBean();
								}
								Double comissaocorretor = voAtual.getCelulaAtual().getNumericCellValue();
								dadosVO.setComissaocorretor(comissaocorretor.floatValue());
								break;
							case 7:// Coluna
								if (dadosVO == null) {
									dadosVO = new DadosBean();
								}
								String tipoOperacao = voAtual.getCelulaAtual().getStringCellValue();
								dadosVO.setTipoOperacao(tipoOperacao);
								
								listaDadosBean.add(dadosVO);
								dadosVO = null;
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

		return listaDadosBean;
	}
	
	
	
	
	
}
