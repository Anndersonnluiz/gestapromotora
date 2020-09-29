package br.com.gestapromotora.facade;

import java.util.List;

import br.com.gestapromotora.dao.TipoArquivoDao;
import br.com.gestapromotora.model.Tipoarquivo;


public class TipoArquivoFacade {

	TipoArquivoDao tipoArquivoDao;
	
	public Tipoarquivo salvar(Tipoarquivo tipoarquivo) {
		tipoArquivoDao = new TipoArquivoDao();
		return tipoArquivoDao.salvar(tipoarquivo);
	}

	public List<Tipoarquivo> lista(String sql) {
		tipoArquivoDao = new TipoArquivoDao();
		return tipoArquivoDao.listar(sql);
	}
}
