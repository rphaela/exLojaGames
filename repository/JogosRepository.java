package org.generation.exGames.repository;

import java.math.BigDecimal;
import java.util.List;

import org.generation.exGames.model.Jogos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface JogosRepository extends JpaRepository<Jogos, Long>{
	
	public List<Jogos> findAllByNomeContainingIgnoreCase (String nome);
	
	public List<Jogos> findAllByConsoleContainingIgnoreCase (String console);
	
	@Query(value = "select * from tb_jogos where preco between :inicio and :fim", nativeQuery = true)
	public List<Jogos> findByPrecoBetween(BigDecimal inicio, BigDecimal fim);
	
}
