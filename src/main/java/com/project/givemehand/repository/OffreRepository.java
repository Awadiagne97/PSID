package com.project.givemehand.repository;

import com.project.givemehand.models.entity.Offre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Le repository de l'offre, elle herite de JPA
 */
@Repository
public interface OffreRepository extends JpaRepository<Offre,Long> {

}
