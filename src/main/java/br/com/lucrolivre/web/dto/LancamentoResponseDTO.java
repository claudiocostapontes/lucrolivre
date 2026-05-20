package br.com.lucrolivre.web.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record LancamentoResponseDTO(
    String id,
    String motorista,
    LocalDate data,
    String origem,
    BigDecimal valorBruto,
    BigDecimal lucroLiquido
) {}