package com.anacasino.gamevault.catalogo;

import com.anacasino.gamevault.catalogo.dto.VideojuegoCreateDTO;
import com.anacasino.gamevault.catalogo.dto.VideojuegoResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/videojuegos")
public class VideojuegoController {

    private final VideojuegoService videojuegoService;

    public VideojuegoController(VideojuegoService videojuegoService) {
        this.videojuegoService = videojuegoService;
    }

    @Operation(summary = "Listar todos los videojuegos")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de videojuegos (puede estar vacía)")
    })
    @GetMapping
    public ResponseEntity<List<VideojuegoResponseDTO>> getAll() {
        return ResponseEntity.ok(videojuegoService.findAll());
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
}