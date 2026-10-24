package com.anacasino.gamevault;

import com.anacasino.gamevault.catalogo.EstudioController;
import com.anacasino.gamevault.catalogo.EstudioService;
import com.anacasino.gamevault.catalogo.dto.EstudioResponseDTO;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;

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

@WebMvcTest(EstudioController.class)
@AutoConfigureMockMvc(addFilters = false)
class EstudioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EstudioService estudioService;

    @Test
    void getAll_DebeDevolverListaDeEstudios() throws Exception {
        var dto = new EstudioResponseDTO(
                1L,
                "Supergiant Games",
                "USA"
        );

        when(estudioService.findAll()).thenReturn(List.of(dto));

        mockMvc.perform(get("/api/v1/estudios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre")
                        .value("Ghibli"));
    }

    @Test
    void getById_DebeDevolver404_CuandoNoExiste() throws Exception {
        when(estudioService.findById(999L))
                .thenThrow(new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Estudio no encontrado"
                ));

        mockMvc.perform(get("/api/v1/estudios/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void create_DebeDevolver201_CuandoEsValido() throws Exception {
        var dto = new EstudioResponseDTO(
                1L,
                "Valve",
                "USA"
        );

        when(estudioService.create(any())).thenReturn(dto);

        mockMvc.perform(post("/api/v1/estudios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "nombre": "Valve",
                                    "pais": "USA"
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre").value("Valve"));
    }

    @Test
    void update_DebeDevolver200_CuandoEsValido() throws Exception {
        var dto = new EstudioResponseDTO(
                1L,
                "Valve Updated",
                "USA"
        );

        when(estudioService.update(eq(1L), any()))
                .thenReturn(dto);

        mockMvc.perform(put("/api/v1/estudios/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "nombre": "Valve Updated",
                                    "pais": "USA"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre")
                        .value("Valve Updated"));
    }

    @Test
    void delete_DebeDevolver204_CuandoExiste() throws Exception {
        mockMvc.perform(delete("/api/v1/estudios/1"))
                .andExpect(status().isNoContent());
    }
}
