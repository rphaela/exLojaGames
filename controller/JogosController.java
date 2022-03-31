package org.generation.exGames.controller;

import java.math.BigDecimal;
import java.util.List;

import org.generation.exGames.model.Jogos;
import org.generation.exGames.repository.JogosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/jogos")
public class JogosController {
	
	@Autowired
	public JogosRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Jogos>> GetAll() {
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Jogos> GetById(@PathVariable Long id) {
		return repository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());	
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Jogos>> GetByNome(@PathVariable String nome) {
		return ResponseEntity.ok(repository.findAllByNomeContainingIgnoreCase(nome));
	}
	
	@GetMapping("/console/{console}")
	public ResponseEntity<List<Jogos>> GetByConsole(@PathVariable String console) {
		return ResponseEntity.ok(repository.findAllByConsoleContainingIgnoreCase(console));
	}
	
	@GetMapping("/preco_inicial/{inicio}/preco_final/{fim}")
	public ResponseEntity<List<Jogos>> getByPrecoEntre(@PathVariable BigDecimal inicio, @PathVariable BigDecimal fim){
		return ResponseEntity.ok(repository.findByPrecoBetween(inicio, fim));
	}
	
	@PostMapping
	public ResponseEntity<Jogos> post (@RequestBody Jogos jogos) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(jogos));
	}
	
	@PutMapping
	public ResponseEntity<Jogos> put (@RequestBody Jogos jogos) {
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(jogos));
	}

	@DeleteMapping("/{id}")
	public void delete (@PathVariable Long id) {
		repository.deleteById(id);
	}
}
