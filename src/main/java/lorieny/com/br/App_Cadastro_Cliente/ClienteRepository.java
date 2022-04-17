package lorieny.com.br.App_Cadastro_Cliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    void deleteByCpf(String cpf);
    Optional<Cliente> findByCpf(String cpf);

    boolean existsByCpf(String cpf);
}
