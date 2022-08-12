package com.projeto_integrador_03.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.projeto_integrador_03.model.Temas;
import com.projeto_integrador_03.repository.TemasRepository;



@RestController
@RequestMapping("/temas")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TemasController {

	@Autowired
	private TemasRepository temasRepository;
	
	@GetMapping
	public ResponseEntity<List<Temas>> getAll(){
		return ResponseEntity.ok(temasRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Temas> getById(@PathVariable Long id){
		return temasRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@GetMapping("/descricao/{descricao}")
	public ResponseEntity<List<Temas>> getByDescricao(@PathVariable String descricao){
		return ResponseEntity.ok(temasRepository.findAllByDescricaoContainingIgnoreCase(descricao));
	}
	
	@PostMapping
	public ResponseEntity<Temas> post(@Valid @RequestBody Temas temas){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(temasRepository.save(temas));
	}
	
	@PutMapping
	public ResponseEntity<Temas> put(@Valid @RequestBody Temas temas){
		return temasRepository.findById(temas.getId())
				.map(resposta -> ResponseEntity.status(HttpStatus.OK)
				.body(temasRepository.save(temas)))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete (@PathVariable Long id) {
		Optional<Temas> temas = temasRepository.findById(id);
		
		if(temas.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		
		temasRepository.deleteById(id);
	}
}
