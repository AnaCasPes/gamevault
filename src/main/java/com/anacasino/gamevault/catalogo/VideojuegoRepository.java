package com.anacasino.gamevault.catalogo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VideojuegoRepository extends 
        JpaRepository<Videojuego, Long>, 
        JpaSpecificationExecutor<Videojuego> {
             List<Videojuego> findTop5ByOrderByFechaLanzamientoDesc();
             @Query("SELECT v FROM Videojuego v WHERE v.estudio.nombre = :nombreEstudio")
             List<Videojuego> buscarPorNombreDeEstudio(@Param("nombreEstudio") String nombreEstudio);

             @Modifying
             @Query("UPDATE Videojuego v SET v.precio = v.precio * 0.90 WHERE v.estudio.id = :estudioId")
             int aplicarDescuento(@Param("estudioId") Long estudioId);

             @Query(
                value = "SELECT * FROM videojuego WHERE jsonb_exists(detalles_plataforma, LOWER(CAST(:plataforma AS text)))",
                nativeQuery = true)
                List<Videojuego> buscarPorPlataformaNativo(@Param("plataforma") String plataforma);

                @Query(
        value = "SELECT * FROM videojuego WHERE detalles_plataforma -> 'switch' ->> 'idApp' = CAST(:idApp AS text)",
        nativeQuery = true
)
List<Videojuego> buscarPorIdAppSwitchNativo(@Param("idApp") String idApp);
}
