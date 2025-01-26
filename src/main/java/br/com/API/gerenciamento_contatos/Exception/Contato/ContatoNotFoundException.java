package br.com.API.gerenciamento_contatos.Exception.Contato;

public class ContatoNotFoundException extends RuntimeException{
    public ContatoNotFoundException(){
        super("Contato não encontrado");
    }
}
