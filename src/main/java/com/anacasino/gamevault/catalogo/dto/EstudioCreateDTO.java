package com.anacasino.gamevault.catalogo.dto;

import jakarta.validation.constraints.NotBlank;

public record EstudioCreateDTO(
    @NotBlank String nombre,
    @NotBlank String pais
) {}