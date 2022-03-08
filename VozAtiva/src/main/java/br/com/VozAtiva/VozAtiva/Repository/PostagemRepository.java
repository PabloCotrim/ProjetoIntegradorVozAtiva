package br.com.VozAtiva.VozAtiva.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.VozAtiva.VozAtiva.model.PostagemModel;

@Repository
public interface PostagemRepository extends JpaRepository<PostagemModel, Long > {
	
	public List<PostagemModel> findAllByTituloContainingIgnoreCase(String titulo);

}
