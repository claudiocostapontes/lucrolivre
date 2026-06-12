package br.com.lucrolivre.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record LancamentoResponseDTO(
    String id,
    String motoristaId,
    LocalDate data,
    String origem,
    BigDecimal valorBruto,
    BigDecimal lucroLiquido
) {}