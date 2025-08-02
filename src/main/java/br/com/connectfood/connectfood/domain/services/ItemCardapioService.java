package br.com.connectfood.connectfood.domain.services;

import br.com.connectfood.connectfood.domain.models.ItemCardapio;
import br.com.connectfood.connectfood.domain.repositories.ItemCardapioRepository;
import br.com.connectfood.connectfood.domain.services.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemCardapioService {

    private final ItemCardapioRepository repository;

    public ItemCardapioService(ItemCardapioRepository repository) {
        this.repository = repository;
    }

    public List<ItemCardapio> findAll() {
        return repository.findAll();
    }

    public ItemCardapio findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item com ID " + id + " não encontrado"));
    }

    public ItemCardapio save(ItemCardapio item) {
        return repository.save(item);
    }

    public ItemCardapio update(Long id, ItemCardapio novo) {
        ItemCardapio atual = findById(id);
        atual.setNome(novo.getNome());
        atual.setDescricao(novo.getDescricao());
        atual.setPreco(novo.getPreco());
        atual.setSomenteLocal(novo.getSomenteLocal());
        atual.setCaminhoFoto(novo.getCaminhoFoto());
        atual.setRestauranteId(novo.getRestauranteId());
        return repository.save(atual);
    }

    public void delete(Long id) {
        ItemCardapio item = findById(id);
        repository.delete(item);
    }
}
