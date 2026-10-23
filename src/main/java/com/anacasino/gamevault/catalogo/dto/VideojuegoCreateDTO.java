package com.anacasino.gamevault.catalogo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import jakarta.validation.constraints.PastOrPresent;

public record VideojuegoCreateDTO(
        @NotBlank @Size(max = 150) String titulo,
        @NotNull @PositiveOrZero BigDecimal precio,
        @NotNull @PastOrPresent LocalDate fechaLanzamiento,
        @NotNull Long estudioId
) {}