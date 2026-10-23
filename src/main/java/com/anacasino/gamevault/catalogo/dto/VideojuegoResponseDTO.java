package com.anacasino.gamevault.catalogo.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record VideojuegoResponseDTO(
        Long id,
        String titulo,
        BigDecimal precio,
        LocalDate fechaLanzamiento,
        String nombreEstudio
) {}
