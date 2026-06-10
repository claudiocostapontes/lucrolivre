package dto;

import model.Origem;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * DTO para validação de entrada de novos lançamentos.
 * Implementa restrições de negócio para garantir a integridade dos dados no DynamoDB.
 */
public record LancamentoRequestDTO(

        @NotBlank(message = "O ID do motorista é obrigatório")
        String motoristaId,

        @NotNull(message = "A data é obrigatória")
        @PastOrPresent(message = "A data não pode ser futura")
        LocalDate data,

        @NotNull(message = "A origem é obrigatória")
        Origem origem,

        @NotNull(message = "O valor bruto é obrigatório")
        @DecimalMin(value = "0.01", message = "O valor bruto deve ser maior que zero")
        BigDecimal valorBruto,

        @NotNull(message = "O gasto com combustível é obrigatório")
        @DecimalMin(value = "0.00", message = "O gasto não pode ser negativo")
        BigDecimal gastoCombustivel,

        @NotNull(message = "O gasto com manutenção é obrigatório")
        @DecimalMin(value = "0.00", message = "O gasto não pode ser negativo")
        BigDecimal gastoManutencao
) {}