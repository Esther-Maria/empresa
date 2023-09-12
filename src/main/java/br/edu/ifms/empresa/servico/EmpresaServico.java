package br.edu.ifms.empresa.servico;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifms.empresa.excecao.EmpresaNotFoundException;
import br.edu.ifms.empresa.modelo.Empresa;
import br.edu.ifms.empresa.repositorio.EmpresaRepositorio;

@Service
public class EmpresaServico {
	@Autowired
	private EmpresaRepositorio empresaRepositorio;

	public Empresa gravar(Empresa empresa) {
		return empresaRepositorio.save(empresa);
	}

	public List<Empresa> buscarTodasEmpresas() {
		return empresaRepositorio.findAll();
	}

	public Empresa buscarEmpresaPorId(Long id) throws EmpresaNotFoundException {
		Optional<Empresa> opt = empresaRepositorio.findById(id);
		if (opt.isPresent()) {
			return opt.get();
		} else {
			throw new EmpresaNotFoundException("Empresa com id: " + id + " não existe");
		}
	}

	public void apagarEmpresa(Long id) throws EmpresaNotFoundException {
		Empresa empresa = buscarEmpresaPorId(id);
		empresaRepositorio.delete(empresa);
	}
	public Empresa editar(Empresa empresa) {
		return empresaRepositorio.save(empresa);
	}
	
	public List<Empresa> buscaEmpresaPorNome(String nome){
		return empresaRepositorio.findByNomeContainingIgnoreCase(nome);
	}

}
