package com.anacasino.gamevault.catalogo;

import com.anacasino.gamevault.catalogo.dto.EstudioCreateDTO;
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
}
