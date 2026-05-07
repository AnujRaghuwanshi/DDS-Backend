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

    boolean existsByYearAndIsCoverTrue(Integer year);

    Optional<Gallery> findByYearAndIsCoverTrue(Integer year);

    long countByYear(Integer year);

    @Query("""
        SELECT g.year as year,
               g.thumbnailUrl as thumbnailUrl,
               (SELECT COUNT(g2.id) FROM Gallery g2 WHERE g2.year = g.year) as totalImages
        FROM Gallery g
        WHERE g.isCover = true
        GROUP BY g.year, g.thumbnailUrl
        ORDER BY g.year DESC
    """)
    List<GalleryYearProjection> fetchGalleryYears();

    Page<Gallery> findByYearOrderByCreatedAtDesc(Integer year, Pageable pageable);

    Optional<Gallery> findFirstByYearOrderByCreatedAtDesc(Integer year);
}
