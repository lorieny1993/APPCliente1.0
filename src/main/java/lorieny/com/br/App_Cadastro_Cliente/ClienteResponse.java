package lorieny.com.br.App_Cadastro_Cliente;

import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class ClienteResponse {
        private Long id;
        private String nome;
        @Email
        private String email;
        @CPF
        private String cpf;
        @Valid
        @Embedded
        private Endereco endereco;
    }
