package com.anacasino.gamevault.catalogo;

import com.anacasino.gamevault.catalogo.dto.EstudioCreateDTO;
import com.anacasino.gamevault.catalogo.dto.EstudioResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/estudios")
public class EstudioController {

    private final EstudioService estudioService;

    public EstudioController(EstudioService estudioService) {
        this.estudioService = estudioService;
    }

    @Operation(summary = "Listar todos los estudios")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de estudios (puede estar vacía)")
    })
    @GetMapping
    public ResponseEntity<List<EstudioResponseDTO>> getAll() {
        return ResponseEntity.ok(estudioService.findAll());
    }

    @Operation(summary = "Obtener un estudio por su id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Estudio encontrado"),
            @ApiResponse(responseCode = "404", description = "No existe ningún estudio con ese id")
    })
    @GetMapping("/{id}")
    public ResponseEntity<EstudioResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(estudioService.findById(id));
    }

    @Operation(summary = "Crear un estudio nuevo")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Estudio creado correctamente"),
            @ApiResponse(responseCode = "400", description = "El cuerpo de la petición no supera las validaciones")
    })
    @PostMapping
    public ResponseEntity<EstudioResponseDTO> create(@Valid @RequestBody EstudioCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(estudioService.create(dto));
    }

    @Operation(summary = "Actualizar un estudio existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Estudio actualizado correctamente"),
            @ApiResponse(responseCode = "400", description = "El cuerpo de la petición no supera las validaciones"),
            @ApiResponse(responseCode = "404", description = "No existe ningún estudio con ese id")
    })
    @PutMapping("/{id}")
    public ResponseEntity<EstudioResponseDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody EstudioCreateDTO dto
    ) {
        return ResponseEntity.ok(estudioService.update(id, dto));
    }

    @Operation(summary = "Borrar un estudio")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Estudio borrado correctamente"),
            @ApiResponse(responseCode = "404", description = "No existe ningún estudio con ese id")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        estudioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
