package br.com.lucrolivre.dto;

import br.com.lucrolivre.model.Origem;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.time.LocalDate;

public record LancamentoRequestDTO(
        @NotBlank(message = "O ID do motorista é obrigatório e não pode estar em branco.")
        String motoristaId,

        @NotBlank(message = "A data do lançamento é obrigatória.")
        String data,

        @NotNull(message = "A origem do lançamento é obrigatória (Ex: UBER, 99).")
        Origem origin,

        @NotNull(message = "O valor bruto é obrigatório.")
        @PositiveOrZero(message = "O valor bruto deve ser maior ou igual a zero.")
        BigDecimal valorBruto,

        @NotNull(message = "O gasto com combustível é obrigatório.")
        @PositiveOrZero(message = "O gasto com combustível deve ser maior ou igual a zero.")
        BigDecimal gastoCombustivel,

        @NotNull(message = "O gasto com manutenção é obrigatório.")
        @PositiveOrZero(message = "O gasto com manutenção deve ser maior ou igual a zero.")
        BigDecimal gastoManutencao
){}