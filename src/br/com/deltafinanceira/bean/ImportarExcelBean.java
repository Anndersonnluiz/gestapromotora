package br.com.deltafinanceira.bean;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import br.com.deltafinanceira.model.Coeficiente;
import br.eti.rogerioaguilar.minhasclasses.util.excel.leitor.LeitorExcel;
import br.eti.rogerioaguilar.minhasclasses.util.excel.leitor.exception.ListenerException;
import br.eti.rogerioaguilar.minhasclasses.util.excel.leitor.exception.PlanilhaNaoEncontradaException;
import br.eti.rogerioaguilar.minhasclasses.util.excel.leitor.gramatica.impl.ParseException;
import br.eti.rogerioaguilar.minhasclasses.util.excel.leitor.listener.ColunaListener;
import br.eti.rogerioaguilar.minhasclasses.util.excel.leitor.util.LinhaColunaListenerVo;

public class ImportarExcelBean {
	
	private List<Coeficiente> listaCoeficiente;

	public List<Coeficiente> importar(InputStream is) {
		LeitorExcel leitor = null;

		// Lista que irá guardar os dados da planilha
		final List<DadosBean> listaDadosBean = new LinkedList<DadosBean>();

		leitor = new LeitorExcel("[*,*]", 1, is, null,

				new ColunaListener() {
					DadosBean dadosVO = null;

					@Override
					public boolean lendoColuna(int linha, int coluna, Map dadosColuna) throws ListenerException {
						LinhaColunaListenerVo voAtual = (LinhaColunaListenerVo) dadosColuna
								.get(ColunaListener.CHAVE_VO_COLUNA);
						if (linha > 1) { // Pula primeira linha pois é a linha que possui o título
							switch (coluna) {
							case 1:// Coluna data
								if (dadosVO == null) {
									dadosVO = new DadosBean();
								}
								String nomeTabela = voAtual.getCelulaAtual().getStringCellValue();
								dadosVO.setNomeTabela(nomeTabela);
								break;
							case 2:// Coluna meta
								if (dadosVO == null) {
									dadosVO = new DadosBean();
								}
								String coeficiente = voAtual.getCelulaAtual().getStringCellValue();
								dadosVO.setNomeTabela(coeficiente);
								break;
							case 3:// Coluna meta
								if (dadosVO == null) {
									dadosVO = new DadosBean();
								}
								String comissaoloja = voAtual.getCelulaAtual().getStringCellValue();
								dadosVO.setNomeTabela(comissaoloja);
								break;
							case 4:// Coluna meta
								if (dadosVO == null) {
									dadosVO = new DadosBean();
								}
								String comissaocorretor = voAtual.getCelulaAtual().getStringCellValue();
								dadosVO.setNomeTabela(comissaocorretor);
								
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

		// Agora faço o que quiser com os dados da planilha
		for (DadosBean vo : listaDadosBean) {
			System.out.println(vo);
		}
		return listaCoeficiente;
	}
}
