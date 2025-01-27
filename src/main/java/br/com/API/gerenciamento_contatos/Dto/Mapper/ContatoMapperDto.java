package br.com.API.gerenciamento_contatos.Dto.Mapper;

import br.com.API.gerenciamento_contatos.Dto.Request.ContatoRequestDto;
import br.com.API.gerenciamento_contatos.Entity.ContatoEntity;

public class ContatoMapperDto {
    public static ContatoRequestDto toContatoDto(ContatoEntity contatoEntity){
        return new ContatoRequestDto(
                contatoEntity.getId(),
                contatoEntity.getNome(),
                contatoEntity.getCelular(),
                contatoEntity.getEmail()
        );
    }
}
