package br.com.API.gerenciamento_contatos.Controller;

import br.com.API.gerenciamento_contatos.Dto.Create.ContatoCreateDto;
import br.com.API.gerenciamento_contatos.Dto.Request.ContatoRequestDto;
import br.com.API.gerenciamento_contatos.Exception.Contato.ContatoNotFoundException;
import br.com.API.gerenciamento_contatos.Service.ContatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contatos")
public class ContatoController {
    @Autowired
    private ContatoService contatoService;

    // Endpoint para criar um novo contato
    @PostMapping
    public ResponseEntity<String> save (@RequestBody ContatoCreateDto contatoCreateDto){
        try{
            // Retorna uma resposta com status 200 (OK) e a mensagem de sucesso.
            return ResponseEntity.ok(contatoService.save(contatoCreateDto));
        } catch (Exception e){
            // Retorna uma resposta com status 400 (Bad Request) em caso de erro.
            return ResponseEntity.badRequest().build();
        }
    }

    // Endpoint para atualizar um contato existente
    @PutMapping("/{id}")
    public ResponseEntity<String> update(@RequestBody ContatoCreateDto contatoCreateDto, @PathVariable Long id){
        try{
            return ResponseEntity.ok(contatoService.update(contatoCreateDto, id));
        } catch (ContatoNotFoundException e) {
            // Retorna status 404 (Not Found) se o contato não for encontrado.
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contato não encontrado.");
        }catch (Exception e){
            // Retorna uma resposta com status 400 (Bad Request) em caso de erro.
            return ResponseEntity.badRequest().build();
        }
    }

    // Endpoint para deletar um contato pelo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        try {
            return ResponseEntity.ok(contatoService.delete(id));
        } catch (ContatoNotFoundException e){
            // Retorna status 404 (Not Found) se o contato não for encontrado.
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contato não encontrado.");
        } catch (Exception e) {
            // Retorna uma resposta com status 400 (Bad Request) em caso de erro.
            return ResponseEntity.badRequest().build();
        }
    }

    // Endpoint para buscar um contato pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        try {
            return ResponseEntity.ok(contatoService.findById(id));
        } catch (ContatoNotFoundException e){
            // Retorna status 404 (Not Found) se o contato não for encontrado.
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contato não encontrado");
        } catch (Exception e){
            // Retorna uma resposta com status 400 (Bad Request) em caso de erro.
            return ResponseEntity.badRequest().build();
        }
    }

    // Endpoint para listar todos os contatos
    @GetMapping
    public ResponseEntity<List<ContatoRequestDto>> findAll(){
        try {
            return ResponseEntity.ok(contatoService.findAll());
        } catch (Exception e){
            // Retorna uma resposta com status 400 (Bad Request) em caso de erro.
            return ResponseEntity.badRequest().build();
        }
    }
}
