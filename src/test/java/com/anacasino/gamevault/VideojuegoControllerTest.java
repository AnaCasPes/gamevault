package com.anacasino.gamevault;

import com.anacasino.gamevault.catalogo.VideojuegoController;
import com.anacasino.gamevault.catalogo.VideojuegoService;
import com.anacasino.gamevault.catalogo.dto.VideojuegoResponseDTO;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(VideojuegoController.class)
@AutoConfigureMockMvc(addFilters = false)
class VideojuegoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private VideojuegoService videojuegoService;

    @Test
    void getAll_DebeDevolverListaDeVideojuegos() throws Exception {
        var dto = new VideojuegoResponseDTO(
                1L,
                "Hades",
                new BigDecimal("24.99"),
                LocalDate.of(2020, 9, 17),
                "Supergiant Games"
        );

        when(videojuegoService.findAll()).thenReturn(List.of(dto));

        mockMvc.perform(get("/api/v1/videojuegos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].titulo").value("Hades"));
    }

    @Test
    void getById_DebeDevolver404_CuandoNoExiste() throws Exception {
        when(videojuegoService.findById(999L))
                .thenThrow(new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Videojuego no encontrado"
                ));

        mockMvc.perform(get("/api/v1/videojuegos/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void create_DebeDevolver201_CuandoEsValido() throws Exception {
        var dto = new VideojuegoResponseDTO(
                1L,
                "Hades",
                new BigDecimal("24.99"),
                LocalDate.of(2020, 9, 17),
                "Supergiant Games"
        );

        when(videojuegoService.create(any())).thenReturn(dto);

        mockMvc.perform(post("/api/v1/videojuegos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "titulo": "Hades",
                                    "precio": 24.99,
                                    "fechaLanzamiento": "2020-09-17",
                                    "estudioId": 1
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.titulo").value("Hades"));
    }

    @Test
    void update_DebeDevolver200_CuandoEsValido() throws Exception {
        var dto = new VideojuegoResponseDTO(
                1L,
                "Hades Updated",
                new BigDecimal("29.99"),
                LocalDate.of(2020, 9, 17),
                "Supergiant Games"
        );

        when(videojuegoService.update(eq(1L), any()))
                .thenReturn(dto);

        mockMvc.perform(put("/api/v1/videojuegos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "titulo": "Hades Updated",
                                    "precio": 29.99,
                                    "fechaLanzamiento": "2020-09-17",
                                    "estudioId": 1
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value("Hades Updated"));
    }

    @Test
    void delete_DebeDevolver204_CuandoExiste() throws Exception {
        mockMvc.perform(delete("/api/v1/videojuegos/1"))
                .andExpect(status().isNoContent());
    }
}
