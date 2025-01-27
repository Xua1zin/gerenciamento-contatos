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


    // Salva um novo contato no banco de dados.
    public String save(ContatoCreateDto contatoDto){
        try{
            // Criando uma entidade de contato e populando seus campos
            ContatoEntity contatoEntity = new ContatoEntity();

            contatoEntity.setNome(contatoDto.nome());
            contatoEntity.setCelular(contatoDto.celular());
            contatoEntity.setEmail(contatoDto.email());

            // Salvando o contato no banco de dados
            contatoRepository.save(contatoEntity);

            return "Contato criado com sucesso.";
        } catch (Exception e) {
            // Lançando uma exceção em caso de erro
            throw new RuntimeException("Problema ao criar contato: " + e.getMessage());
        }
    }

    // Atualiza um contato existente.
    public String update(ContatoCreateDto contatoDto, Long id){
        try{
            // Buscando o contato pelo ID e lança exceção se não for encontrado
            ContatoEntity contatoEntity = contatoRepository.findById(id)
                    .orElseThrow(ContatoNotFoundException::new);

            // Atualizando somente os campos que não são nulos
            if(contatoDto.nome() != null) {
                contatoEntity.setNome(contatoDto.nome());
            }
            if (contatoDto.celular() != null) {
                contatoEntity.setCelular(contatoDto.celular());
            }
            if(contatoDto.email() != null) {
                contatoEntity.setEmail(contatoDto.email());
            }

            // Salvando as alterações no banco de dados
            contatoRepository.save(contatoEntity);

            return "Contato atualizado com sucesso.";
        } catch(ContatoNotFoundException e){
            // Lançando a exceção específica se o contato não for encontrado
            throw e;
        } catch (Exception e){
            // Lançando uma exceção genérica em caso de erro
            throw new RuntimeException("Problema ao editar contato: " + e.getMessage());
        }
    }

    //Exclui um contato do banco de dados.
    public String delete(Long id) {
        try {
            // Buscando o contato pelo ID e lança exceção se não for encontrado
            ContatoEntity contatoEntity = contatoRepository.findById(id)
                    .orElseThrow(ContatoNotFoundException::new);

            // Excluindo o contato pelo ID
            contatoRepository.deleteById(contatoEntity.getId());

            return "Contato deletado com sucesso.";
        } catch (ContatoNotFoundException e){
            // Lançando a exceção específica se o contato não for encontrado
            throw e;
        } catch (Exception e){
            // Lançando uma exceção genérica em caso de erro
            throw new RuntimeException("Problema ao deletar contato: " + e.getMessage());
        }
    }

    // Busca um contato pelo ID.
    public ContatoRequestDto findById(Long id){
        try {
            // Buscando o contato pelo ID e lança exceção se não for encontrado
            ContatoEntity contatoEntity = contatoRepository.findById(id)
                    .orElseThrow(ContatoNotFoundException::new);

            return ContatoMapperDto.toContatoDto(contatoEntity);
        } catch (ContatoNotFoundException e){
            // Lançando a exceção específica se o contato não for encontrado
            throw e;
        } catch (Exception e){
            // Lançando uma exceção genérica em caso de erro
            throw new RuntimeException("Problema ao procurar contato: " + e.getMessage());
        }
    }

    //Retorna a lista de todos os contatos cadastrados.
    public List<ContatoRequestDto> findAll(){
        try {
            // Buscando todos os contatos no banco de dados
            List<ContatoEntity> contatos = contatoRepository.findAll();
            List<ContatoRequestDto> contatosDto = new ArrayList<>();

            // Convertendo cada entidade em um DTO e adicionando à lista
            for(ContatoEntity contato : contatos){
                ContatoRequestDto contatoRequestDto = ContatoMapperDto.toContatoDto(contato);
                contatosDto.add(contatoRequestDto);
            }

            return contatosDto;
        } catch (Exception e){
            // Lançando uma exceção genérica em caso de erro
            throw new RuntimeException("Problema ao procurar contatos: " + e.getMessage());
        }
    }
}
