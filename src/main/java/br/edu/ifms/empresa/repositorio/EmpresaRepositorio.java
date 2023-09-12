package br.edu.ifms.empresa.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifms.empresa.modelo.Empresa;

public interface EmpresaRepositorio extends JpaRepository<Empresa, Long> {
	List<Empresa> findByNomeContainingIgnoreCase(String modelo);
}
