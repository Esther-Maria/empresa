package br.edu.ifms.empresa.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import br.edu.ifms.empresa.modelo.Filial;

public interface FilialRepositorio extends JpaRepository<Filial, Long> {

}
