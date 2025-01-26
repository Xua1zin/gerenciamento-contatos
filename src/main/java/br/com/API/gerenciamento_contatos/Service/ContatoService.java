package br.com.API.gerenciamento_contatos.Service;

import br.com.API.gerenciamento_contatos.Dto.Create.ContatoCreateDto;
import br.com.API.gerenciamento_contatos.Dto.Mapper.ContatoMapperDto;
import br.com.API.gerenciamento_contatos.Dto.Request.ContatoRequestDto;
import br.com.API.gerenciamento_contatos.Entity.ContatoEntity;
import br.com.API.gerenciamento_contatos.Exception.Contato.ContatoNotFoundException;
import br.com.API.gerenciamento_contatos.Repository.ContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContatoService {
    @Autowired
    private ContatoRepository contatoRepository;

    public String save(ContatoCreateDto contatoDto){
        try{
            ContatoEntity contatoEntity = new ContatoEntity();

            contatoEntity.setNome(contatoDto.nome());
            contatoEntity.setCelular(contatoDto.celular());
            contatoEntity.setEmail(contatoDto.email());

            contatoRepository.save(contatoEntity);

            return "Contato criado com sucesso";
        } catch (Exception e) {
            throw new RuntimeException("Problema ao criar contato: " + e.getMessage());
        }
    }

    public String update(ContatoCreateDto contatoDto, Long id){
        try{
            ContatoEntity contatoEntity = contatoRepository.findById(id)
                    .orElseThrow(ContatoNotFoundException::new);

            contatoEntity.setNome(contatoDto.nome());
            contatoEntity.setCelular(contatoDto.celular());
            contatoEntity.setEmail(contatoDto.email());

            contatoRepository.save(contatoEntity);
            return "Contato editado com sucesso";
        } catch(ContatoNotFoundException e){
            throw e;
        } catch (Exception e){
            throw new RuntimeException("Problema ao editar contato: " + e.getMessage());
        }
    }

    public String delete(Long id) {
        try {
            ContatoEntity contatoEntity = contatoRepository.findById(id)
                    .orElseThrow(ContatoNotFoundException::new);

            contatoRepository.deleteById(contatoEntity.getId());

            return "Contato deletado com sucesso";
        } catch (ContatoNotFoundException e){
            throw e;
        } catch (Exception e){
            throw new RuntimeException("Problema ao deletar contato: " + e.getMessage());
        }
    }

    public ContatoRequestDto findById(Long id){
        try {
            ContatoEntity contatoEntity = contatoRepository.findById(id)
                    .orElseThrow(ContatoNotFoundException::new);
            return ContatoMapperDto.toContatoDto(contatoEntity);
        } catch (ContatoNotFoundException e){
            throw e;
        } catch (Exception e){
            throw new RuntimeException("Problema ao procurar contato: " + e.getMessage());
        }
    }

    public List<ContatoRequestDto> findAll(){
        try {
            List<ContatoEntity> contatos = contatoRepository.findAll();
            List<ContatoRequestDto> contatosDto = new ArrayList<>();

            for(ContatoEntity contato : contatos){
                ContatoRequestDto contatoRequestDto = ContatoMapperDto.toContatoDto(contato);
                contatosDto.add(contatoRequestDto);
            }

            return contatosDto;
        } catch (Exception e){
            throw new RuntimeException("Problema ao procurar contatos: " + e.getMessage());
        }
    }
}
