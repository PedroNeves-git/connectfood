package br.com.connectfood.connectfood.domain.repositories;

import br.com.connectfood.connectfood.domain.models.ItemCardapio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemCardapioRepository extends JpaRepository<ItemCardapio, Long> {
}
