package com.minegocio.data.adapters;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.minegocio.data.entities.ClienteEntity;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {

    @Query("SELECT c FROM ClienteEntity c JOIN c.direcciones WHERE c.numIdentificacion LIKE '%' || :query || '%'")
    List<ClienteEntity> findByNumIdentificacion(String query);

    @Query("SELECT c FROM ClienteEntity c JOIN c.direcciones WHERE lower(c.nombres) LIKE '%' || lower(:query) || '%'")
    List<ClienteEntity> findByNombres(String query);

}
