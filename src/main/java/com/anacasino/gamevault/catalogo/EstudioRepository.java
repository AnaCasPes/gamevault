package com.anacasino.gamevault.catalogo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;


public interface EstudioRepository extends JpaRepository<Estudio, Long> {
    
    @Query("SELECT e FROM Estudio e JOIN e.videojuegos v GROUP BY e ORDER BY COUNT(v) DESC, e.nombre ASC")
    List<Estudio> rankingPorNumeroDeVideojuegos();

    @Query(value = """
        SELECT e.nombre, COUNT(v.id), ROW_NUMBER() OVER (ORDER BY COUNT(v.id) DESC) AS posicion
        FROM estudio e LEFT JOIN videojuego v ON v.estudio_id = e.id
        GROUP BY e.id, e.nombre
        """, nativeQuery = true)
        List<Object[]> rankingConPosicion();


}