package br.com.VozAtiva.VozAtiva.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.VozAtiva.VozAtiva.model.TemaModel;

@Repository
public interface TemaRepository extends JpaRepository<TemaModel, Long> {
	public List<TemaModel> findAllByTipoContainingIgnoreCase(String tipo);

}
