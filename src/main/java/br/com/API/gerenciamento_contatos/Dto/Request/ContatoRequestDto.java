package br.com.API.gerenciamento_contatos.Dto.Request;

public record ContatoRequestDto(Long id, String nome, String celular, String email) {
}
