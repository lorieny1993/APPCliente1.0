package lorieny.com.br.App_Cadastro_Cliente;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class Endereco {
    @Pattern(regexp = "\\d{5}-\\d{3}", message = "CEP inválido ou vazio! Preencha novamente corretamente no formato 00000-000")
    private String cep;
    @NotBlank
    private String rua;
    @NotBlank
    private String numero;
    @NotBlank
    private String bairro;
    private String complemento;
    @NotBlank
    private String cidade;
    @NotBlank
    private String estado;
}