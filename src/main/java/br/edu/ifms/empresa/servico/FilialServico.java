package br.edu.ifms.empresa.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifms.empresa.modelo.Filial;
import br.edu.ifms.empresa.repositorio.FilialRepositorio;

@Service
public class FilialServico {
	
	@Autowired
	private FilialRepositorio filialRepositorio;
	
	public Filial criarFilial(Filial filial) {
		return filialRepositorio.save(filial);
	}
	
	public Filial alterarFilial(Filial filial) {
		return filialRepositorio.save(filial);
	}
}
