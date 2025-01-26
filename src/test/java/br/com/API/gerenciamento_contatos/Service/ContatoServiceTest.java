package br.com.API.gerenciamento_contatos.Service;

import br.com.API.gerenciamento_contatos.Dto.Create.ContatoCreateDto;
import br.com.API.gerenciamento_contatos.Dto.Request.ContatoRequestDto;
import br.com.API.gerenciamento_contatos.Entity.ContatoEntity;
import br.com.API.gerenciamento_contatos.Exception.Contato.ContatoNotFoundException;
import br.com.API.gerenciamento_contatos.Repository.ContatoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ContatoServiceTest {

    @InjectMocks
    ContatoService contatoService;;

    @Mock
    ContatoRepository contatoRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    //=======================TESTES DE SAVE=======================
    @Test
    void saveSuccess() {
        ContatoCreateDto contatoDto = new ContatoCreateDto(null, "Teste","(99) 99999-9999","teste@example.com.br");
        ContatoEntity contato = new ContatoEntity();

        when(contatoRepository.save(contato)).thenReturn(contato);
        String result =contatoService.save(contatoDto);

        assertEquals("Contato criado com sucesso.", result);
        verify(contatoRepository, times(1)).save(any(ContatoEntity.class));

    }

    @Test
    void saveException(){
        ContatoCreateDto contatoDto = new ContatoCreateDto(null, "Teste","(99) 99999-9999","teste@example.com.br");
        doThrow(new RuntimeException("Database error")).when(contatoRepository).save(any(ContatoEntity.class));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> contatoService.save(contatoDto));
        assertEquals("Problema ao criar contato: Database error", exception.getMessage());
    }

    //=======================TESTES DE UPDATE=======================
    @Test
    void updateSuccess() {
        ContatoCreateDto contatoDto = new ContatoCreateDto(null, "Nome atualizado","(99) 99999-9999","emailAtualizado@example.com.br");
        ContatoEntity contato = new ContatoEntity();
        contato.setId(1L);
        contato.setNome("Nome");
        contato.setCelular("(11) 11111-1111");
        contato.setEmail("email@example.com.br");

        when(contatoRepository.findById(1L)).thenReturn(Optional.of(contato));
        when(contatoRepository.save(contato)).thenReturn(contato);
        String result = contatoService.update(contatoDto, contato.getId());

        assertEquals("Contato atualizado com sucesso.", result);
        assertEquals(contato.getNome(), contatoDto.nome());
        assertEquals(contato.getCelular(), contatoDto.celular());
        assertEquals(contato.getEmail(), contatoDto.email());
        verify(contatoRepository,times(1)).save(contato);
    }

    @Test
    void updateContatoNotFoundException(){
        ContatoCreateDto contatoDto = new ContatoCreateDto(null, "Nome atualizado","(99) 99999-9999","emailAtualizado@example.com.br");
        ContatoEntity contato = new ContatoEntity();

        when(contatoRepository.findById(1L)).thenReturn(Optional.empty());
        ContatoNotFoundException result = assertThrows(ContatoNotFoundException.class, () -> contatoService.update(contatoDto, 1L));

        assertEquals("Contato não encontrado.", result.getMessage());
        verify(contatoRepository, never()).save(contato);
    }

    @Test
    void updateException(){
        ContatoCreateDto contatoDto = new ContatoCreateDto(null, "Nome atualizado","(99) 99999-9999","emailAtualizado@example.com.br");
        ContatoEntity contato = new ContatoEntity();
        contato.setId(1L);
        contato.setNome("Nome");
        contato.setCelular("(11) 11111-1111");
        contato.setEmail("email@example.com.br");

        when(contatoRepository.findById(1L)).thenReturn(Optional.of(contato));
        when(contatoRepository.save(any(ContatoEntity.class))).thenThrow(new RuntimeException("Database error"));

        RuntimeException result = assertThrows(RuntimeException.class, () -> contatoService.update(contatoDto, 1L));

        assertEquals("Problema ao editar contato: Database error", result.getMessage());
    }

    //=======================TESTES DE DELETE=======================
    @Test
    void deleteSuccess() {
        ContatoEntity contato = new ContatoEntity();
        contato.setId(2L);
        contato.setNome("Nome");
        contato.setCelular("(11) 11111-1111");
        contato.setEmail("email@example.com.br");

        when(contatoRepository.findById(2L)).thenReturn(Optional.of(contato));
        doNothing().when(contatoRepository).deleteById(2L);
        String result = contatoService.delete(2L);

        assertEquals("Contato deletado com sucesso.", result);
        verify(contatoRepository, times(1)).deleteById(2L);
    }

    @Test
    void deleteContatoNotFoundException(){
        Long id = 2L;
        when(contatoRepository.findById(id)).thenReturn(Optional.empty());
        ContatoNotFoundException result = assertThrows(ContatoNotFoundException.class, () -> contatoService.delete(id));

        assertEquals("Contato não encontrado.", result.getMessage());
    }

    @Test
    void deleteException(){
        Long id = 2L;
        ContatoEntity contato = new ContatoEntity();
        contato.setId(id);

        when(contatoRepository.findById(id)).thenThrow(new RuntimeException("Database error"));

        RuntimeException result = assertThrows(RuntimeException.class, () -> contatoService.delete(id));

        assertEquals("Problema ao deletar contato: Database error", result.getMessage());
    }

    //=======================TESTES DE CONSULTA=======================
    @Test
    void findByIdSuccess() {
        ContatoEntity contato = new ContatoEntity();
        contato.setId(2L);
        contato.setNome("Nome");
        contato.setCelular("(11) 11111-1111");
        contato.setEmail("email@example.com.br");

        when(contatoRepository.findById(2L)).thenReturn(Optional.of(contato));
        ContatoRequestDto result = contatoService.findById(2L);

        assertEquals("Nome", result.nome());
        assertEquals("(11) 11111-1111", result.celular());
        assertEquals("email@example.com.br", result.email());
        verify(contatoRepository, times(1)).findById(2L);
    }

    @Test
    void findByIdContatoNotFoundException() {
        Long id = 2L;

        when(contatoRepository.findById(2L)).thenReturn(Optional.empty());
        ContatoNotFoundException result = assertThrows(ContatoNotFoundException.class, () -> contatoService.findById(2L));

        assertEquals("Contato não encontrado.", result.getMessage());
    }

    @Test
    void findByIdException(){
        when(contatoRepository.findById(2L)).thenThrow(new RuntimeException("Database error"));

        RuntimeException result =assertThrows(RuntimeException.class, () -> contatoService.findById(2L));
        assertEquals("Problema ao procurar contato: Database error", result.getMessage());
    }

    @Test
    void findAllSuccess() {
        ContatoEntity contato = new ContatoEntity();
        contato.setId(2L);
        contato.setNome("Nome");
        contato.setCelular("(11) 11111-1111");
        contato.setEmail("email@example.com.br");

        ContatoEntity contato2 = new ContatoEntity();
        contato2.setId(4L);
        contato2.setNome("Nome 2");
        contato2.setCelular("(22) 22222-2222");
        contato2.setEmail("email2@example.com.br");


        List<ContatoEntity> contatos = Arrays.asList(contato, contato2);
        when(contatoRepository.findAll()).thenReturn(contatos);

        List<ContatoRequestDto> result =contatoService.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());

        ContatoRequestDto dto1 = result.get(0);
        ContatoRequestDto dto2 = result.get(1);

        assertEquals("Nome", dto1.nome());
        assertEquals("(11) 11111-1111", dto1.celular());
        assertEquals("email@example.com.br", dto1.email());

        assertEquals("Nome 2", dto2.nome());
        assertEquals("(22) 22222-2222", dto2.celular());
        assertEquals("email2@example.com.br", dto2.email());
    }

    @Test
    void findAllException(){
        when(contatoRepository.findAll()).thenThrow(new RuntimeException("Database error"));

        RuntimeException result =assertThrows(RuntimeException.class, () -> contatoService.findAll());

        assertEquals("Problema ao procurar contatos: Database error", result.getMessage());
    }
}