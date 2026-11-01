package com.anacasino.gamevault.catalogo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.util.List;

public interface VideojuegoRepository extends 
        JpaRepository<Videojuego, Long>, 
        JpaSpecificationExecutor<Videojuego> {
             List<Videojuego> findTop5ByOrderByFechaLanzamientoDesc();
}