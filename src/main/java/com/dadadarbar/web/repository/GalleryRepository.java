package com.dadadarbar.web.repository;

import com.dadadarbar.web.dto.GalleryYearProjection;
import com.dadadarbar.web.entity.Gallery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface GalleryRepository extends JpaRepository<Gallery, Long> {

    long countByYear(Integer year);


    Page<Gallery> findByYearOrderByCreatedAtDesc(Integer year, Pageable pageable);

    Optional<Gallery> findFirstByYearOrderByCreatedAtDesc(Integer year);
}
