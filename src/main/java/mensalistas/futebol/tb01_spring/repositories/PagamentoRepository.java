package mensalistas.futebol.tb01_spring.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import mensalistas.futebol.tb01_spring.models.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {
}