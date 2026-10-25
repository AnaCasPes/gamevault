package com.anacasino.gamevault.catalogo;

import com.anacasino.gamevault.catalogo.dto.VideojuegoResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import com.anacasino.gamevault.catalogo.dto.VideojuegoCreateDTO;

@Service
@RequiredArgsConstructor 
public class VideojuegoService {
    private final VideojuegoRepository videojuegoRepository;
    private final EstudioRepository estudioRepository;

    @Transactional(readOnly = true)
    public VideojuegoResponseDTO findById(Long id) {
        return videojuegoRepository.findById(id)
                .map(this::mapToDTO)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Videojuego no encontrado"));
    }

    @Transactional(readOnly = true)
    public List<VideojuegoResponseDTO> findAll() {
        return videojuegoRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }
    @Transactional
    public VideojuegoResponseDTO create(VideojuegoCreateDTO dto) {

        Estudio estudio = estudioRepository.findById(dto.estudioId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Estudio no encontrado"
                ));

        Videojuego videojuego = new Videojuego();

        videojuego.setTitulo(dto.titulo());
        videojuego.setPrecio(dto.precio());
        videojuego.setFechaLanzamiento(dto.fechaLanzamiento());
        videojuego.setEstudio(estudio);

        Videojuego guardado = videojuegoRepository.save(videojuego);

        return mapToDTO(guardado);
    }
    @Transactional
    public VideojuegoResponseDTO update(
        Long id,
        VideojuegoCreateDTO dto
) {
    Videojuego videojuego = videojuegoRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Videojuego no encontrado"
            ));

    Estudio estudio = estudioRepository.findById(dto.estudioId())
            .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Estudio no encontrado"
            ));

    videojuego.setTitulo(dto.titulo());
    videojuego.setPrecio(dto.precio());
    videojuego.setFechaLanzamiento(dto.fechaLanzamiento());
    videojuego.setEstudio(estudio);

    Videojuego actualizado = videojuegoRepository.save(videojuego);

    return mapToDTO(actualizado);
}
 @Transactional
    public void delete(Long id) {
        Videojuego videojuego = videojuegoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Videojuego no encontrado"
                ));

        videojuegoRepository.delete(videojuego);
    }
    private VideojuegoResponseDTO mapToDTO(Videojuego v) {
        return new VideojuegoResponseDTO(
                v.getId(), v.getTitulo(), v.getPrecio(), v.getFechaLanzamiento(), v.getEstudio().getNombre()
        );
    }
    @Transactional(readOnly = true)
public List<VideojuegoResponseDTO> getTopNovedades() {
        try {
        Thread.sleep(2000); // simula una consulta costosa — lo quitas al final de la actividad
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
    }
    return videojuegoRepository.findTop5ByOrderByFechaLanzamientoDesc()
            .stream()
            .map(this::mapToDTO)
            .toList();
}
}