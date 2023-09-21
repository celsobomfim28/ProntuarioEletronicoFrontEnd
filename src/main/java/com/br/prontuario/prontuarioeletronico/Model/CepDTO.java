package com.br.prontuario.prontuarioeletronico.Model;

public record CepDTO(
        String cep,
        String state,
        String city,
        String neighborhood,
        String street,
        String service
) {
}
