package com.anacasino.gamevault.catalogo.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

public record VideojuegoResponseDTO(
        Long id,
        String titulo,
        BigDecimal precio,
        LocalDate fechaLanzamiento,
        String nombreEstudio,

        Map<String, Object> detallesPlataforma
) {}
