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

import com.projeto_integrador_03.model.Postagens;
//import com.projeto_integrador_03.model.Temas;
import com.projeto_integrador_03.repository.PostagensRepository;
import com.projeto_integrador_03.repository.TemasRepository;

@RestController
@RequestMapping("/postagens")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostagensController {
	
	
	@Autowired
	private PostagensRepository postagensRepository;
	
	@Autowired
	private TemasRepository temasRepository;
	
	@GetMapping
	public ResponseEntity<List<Postagens>> getAll(){
		return ResponseEntity.ok(postagensRepository.findAll());	
	}
	
	@GetMapping("/{îd}")
	public ResponseEntity<Postagens> getById(@PathVariable Long id){
		return postagensRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	@PostMapping
	public ResponseEntity<Postagens> postPostagem (@Valid @RequestBody Postagens postagens){
		
		/** Checa antes de Persistir o Objeto Postagem se o Tema existe 
		 *  Se o Objeto Tema não existir, o status devolvido será Bad Request (400).
		*/

		if (temasRepository.existsById(postagens.getTemas().getId()))
			return ResponseEntity.status(HttpStatus.CREATED).body(postagensRepository.save(postagens));
	
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	
	}

    @PutMapping
    public ResponseEntity<Postagens> put(@Valid @RequestBody Postagens postagens){
        if (postagensRepository.existsById(postagens.getId())){

            if (temasRepository.existsById(postagens.getTemas().getId()))
                return ResponseEntity.status(HttpStatus.OK)
                        .body(postagensRepository.save(postagens));

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
	
    
    
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete (@PathVariable Long id) {
    	Optional<Postagens> postagens = postagensRepository.findById(id);
    	
    	if(postagens.isEmpty())
    		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    	
    	postagensRepository.deleteById(id);
    }
}

