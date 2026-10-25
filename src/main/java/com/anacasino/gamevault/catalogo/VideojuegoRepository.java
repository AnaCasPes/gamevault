package com.anacasino.gamevault.catalogo;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface VideojuegoRepository extends JpaRepository<Videojuego, Long> {List<Videojuego> findTop5ByOrderByFechaLanzamientoDesc();}
