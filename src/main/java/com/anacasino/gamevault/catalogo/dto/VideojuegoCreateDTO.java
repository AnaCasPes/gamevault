
package com.anacasino.gamevault.catalogo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.PastOrPresent;

import java.math.BigDecimal;
import java.time.LocalDate;

public record VideojuegoCreateDTO(

        @NotBlank(message = "El título del videojuego no puede estar vacío")
        @Size(max = 150, message = "El título no puede superar los 150 caracteres")
        String titulo,

        @NotNull(message = "El precio es obligatorio")
        @PositiveOrZero(message = "El precio no puede ser negativo")
        BigDecimal precio,

        @NotNull(message = "La fecha de lanzamiento es obligatoria")
        @PastOrPresent(message = "La fecha de lanzamiento debe ser igual o anterior a la fecha actual")
        LocalDate fechaLanzamiento,

        @NotNull(message = "Debes indicar el estudio del videojuego")
        Long estudioId

) {}
