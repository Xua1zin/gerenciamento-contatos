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
@RequestMapping("")
public class ContatoController {
    @Autowired
    private ContatoService contatoService;

    @PostMapping("/create")
    public ResponseEntity<String> save (@RequestBody ContatoCreateDto contatoCreateDto){
        try{
            return ResponseEntity.ok(contatoService.save(contatoCreateDto));
        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@RequestBody ContatoCreateDto contatoCreateDto, @PathVariable Long id){
        try{
            return ResponseEntity.ok(contatoService.update(contatoCreateDto, id));
        } catch (ContatoNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contato não encontrado.");
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        try {
            return ResponseEntity.ok(contatoService.delete(id));
        } catch (ContatoNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contato não encontrado.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        try {
            return ResponseEntity.ok(contatoService.findById(id));
        } catch (ContatoNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contato não encontrado");
        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/*")
    public ResponseEntity<List<ContatoRequestDto>> findAll(){
        try {
            return ResponseEntity.ok(contatoService.findAll());
        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}
