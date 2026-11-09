
package com.anacasino.gamevault.catalogo;

import com.anacasino.gamevault.catalogo.dto.VideojuegoCreateDTO;
import com.anacasino.gamevault.catalogo.dto.VideojuegoFiltroDTO;
import com.anacasino.gamevault.catalogo.dto.VideojuegoResponseDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/videojuegos")
public class VideojuegoController {

    private final VideojuegoService videojuegoService;

    public VideojuegoController(VideojuegoService videojuegoService) {
        this.videojuegoService = videojuegoService;
    }

    @Operation(summary = "Obtener un videojuego por su id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Videojuego encontrado"),
            @ApiResponse(responseCode = "404", description = "No existe ningún videojuego con ese id")
    })
    @GetMapping("/{id}")
    public ResponseEntity<VideojuegoResponseDTO> getById(
        @PathVariable Long id
) {
    

     return ResponseEntity.ok(videojuegoService.findById(id));
}

    @Operation(summary = "Crear un videojuego nuevo")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Videojuego creado correctamente"),
            @ApiResponse(responseCode = "400", description = "El cuerpo de la petición no supera las validaciones"),
            @ApiResponse(responseCode = "404", description = "El estudio indicado no existe")
    })
    @PostMapping
    public ResponseEntity<VideojuegoResponseDTO> create(
            @Valid @RequestBody VideojuegoCreateDTO dto
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(videojuegoService.create(dto));
    }

    @Operation(summary = "Actualizar un videojuego existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Videojuego actualizado correctamente"),
            @ApiResponse(responseCode = "400", description = "El cuerpo de la petición no supera las validaciones"),
            @ApiResponse(responseCode = "404", description = "No existe ningún videojuego con ese id, o el estudio indicado no existe")
    })
    @PutMapping("/{id}")
    public ResponseEntity<VideojuegoResponseDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody VideojuegoCreateDTO dto
    ) {
        return ResponseEntity.ok(
                videojuegoService.update(id, dto)
        );
    }

    @Operation(summary = "Borrar un videojuego")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Videojuego borrado correctamente"),
            @ApiResponse(responseCode = "404", description = "No existe ningún videojuego con ese id")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        videojuegoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Listar los 5 videojuegos más recientes")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Listado obtenido correctamente")
    })
    @GetMapping("/top")
    public ResponseEntity<List<VideojuegoResponseDTO>> getTopNovedades() {
        return ResponseEntity.ok(videojuegoService.getTopNovedades());
    }

    @Operation(summary = "Listar videojuegos, con filtros opcionales y paginación")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Página de videojuegos que cumplen los filtros indicados (puede estar vacía)")
    })
    @GetMapping
    public ResponseEntity<Page<VideojuegoResponseDTO>> getAll(
            @ParameterObject @ModelAttribute VideojuegoFiltroDTO filtro,
            @ParameterObject @PageableDefault(size = 5) Pageable pageable
    ) {
        return ResponseEntity.ok(
                videojuegoService.findAllPaginated(filtro, pageable)
        );
    }
    // En VideojuegoController
    @Operation(summary = "Listar los videojuegos publicados por un estudio, buscando por su nombre exacto")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Videojuegos del estudio indicado (vacía si no tiene ninguno, o si el nombre no existe)")})
        @GetMapping("/por-nombre-de-estudio/{nombre}")
        public ResponseEntity<List<VideojuegoResponseDTO>> getPorEstudio(@PathVariable String nombre) {

                return ResponseEntity.ok(videojuegoService.buscarPorEstudio(nombre));
        }
        
        @Operation(summary = "Aplicar un descuento del 10% a todos los videojuegos de un estudio")
        @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Descuento aplicado; el cuerpo indica cuántos videojuegos se han visto afectados")
        })
        
        @PostMapping("/descuento/{estudioId}")
        public ResponseEntity<Map<String, Integer>> aplicarDescuento(@PathVariable Long estudioId) {
                int actualizados = videojuegoService.aplicarDescuento(estudioId);
                return ResponseEntity.ok(Map.of("videojuegosActualizados", actualizados));
}
}
