package com.dadadarbar.web.repository;

import com.dadadarbar.web.entity.Gallery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GalleryRepository extends JpaRepository<Gallery, Long> {

    boolean existsByYearAndIsCoverTrue(Integer year);

    Optional<Gallery> findByYearAndIsCoverTrue(Integer year);

    long countByYear(Integer year);
}
