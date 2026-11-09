package com.anacasino.gamevault.catalogo;

import com.anacasino.gamevault.catalogo.dto.EstudioCreateDTO;
import com.anacasino.gamevault.catalogo.dto.EstudioRankingConPosicionDTO;
import com.anacasino.gamevault.catalogo.dto.EstudioRankingDTO;
import com.anacasino.gamevault.catalogo.dto.EstudioResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EstudioService {

    private final EstudioRepository estudioRepository;

    @Transactional(readOnly = true)
    public List<EstudioResponseDTO> findAll() {
        return estudioRepository.findAll().stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public EstudioResponseDTO findById(Long id) {
        return estudioRepository.findById(id)
                .map(this::mapToDTO)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Estudio no encontrado"));
    }

    @Transactional
    public EstudioResponseDTO create(EstudioCreateDTO dto) {
        Estudio estudio = new Estudio();
        estudio.setNombre(dto.nombre());
        estudio.setPais(dto.pais());

        Estudio saved = estudioRepository.save(estudio);
        return mapToDTO(saved);
    }

    @Transactional
    public EstudioResponseDTO update(Long id, EstudioCreateDTO dto) {
        Estudio estudio = estudioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Estudio no encontrado"));

        estudio.setNombre(dto.nombre());
        estudio.setPais(dto.pais());

        Estudio updated = estudioRepository.save(estudio);
        return mapToDTO(updated);
    }

    @Transactional
    public void delete(Long id) {
        if (!estudioRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Estudio no encontrado");
        }
        estudioRepository.deleteById(id);
    }

    private EstudioResponseDTO mapToDTO(Estudio e) {
        return new EstudioResponseDTO(e.getId(), e.getNombre(), e.getPais());
    }

    // En EstudioService
@Transactional(readOnly = true)
public List<EstudioRankingDTO> obtenerRanking() {
    return estudioRepository.rankingPorNumeroDeVideojuegos()
            .stream()
            .map(e -> new EstudioRankingDTO(e.getNombre(), e.getVideojuegos().size()))
            .toList();
}

// En EstudioService
@Transactional(readOnly = true)
public List<EstudioRankingConPosicionDTO> obtenerRankingConPosicion() {
    return estudioRepository.rankingConPosicion().stream()
            .map(fila -> new EstudioRankingConPosicionDTO(
                    /* rellena aquí el mapeo, según el orden de tu SELECT */
                    (String) fila[0],                         
                    ((Number) fila[1]).intValue(),             
                    ((Number) fila[2]).intValue()              
            ))
            .toList();
}
}
