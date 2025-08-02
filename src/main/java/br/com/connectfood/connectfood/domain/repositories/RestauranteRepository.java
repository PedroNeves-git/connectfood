package br.com.connectfood.connectfood.domain.repositories;

import br.com.connectfood.connectfood.domain.models.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {

}
