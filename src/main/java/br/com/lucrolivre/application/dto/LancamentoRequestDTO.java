package br.com.lucrolivre.application.dto;

import br.com.lucrolivre.domain.model.Origem;
import java.math.BigDecimal;
import java.time.LocalDate;

public record LancamentoRequestDTO(
    String usuarioId,
    LocalDate data,
    Origem origem,
    BigDecimal valorBruto,
    BigDecimal gastoCombustivel,
    BigDecimal gastoManutencao
) {}