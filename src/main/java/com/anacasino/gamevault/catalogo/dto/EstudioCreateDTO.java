
package com.anacasino.gamevault.catalogo.dto;

import jakarta.validation.constraints.NotBlank;

public record EstudioCreateDTO(

        @NotBlank(message = "El nombre del estudio no puede estar vacío")
        String nombre,

        @NotBlank(message = "El país del estudio no puede estar vacío")
        String pais

) {}
