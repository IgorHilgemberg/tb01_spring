package mensalistas.futebol.tb01_spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import mensalistas.futebol.tb01_spring.models.Jogador;

public interface JogadorRepository extends JpaRepository<Jogador, Integer> {
    List<Jogador> findByNomeContaining(String nome);
}
