package leonardo.labutilities.qualitylabpro.repository;

import leonardo.labutilities.qualitylabpro.domain.entitys.GenericAnalytics;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GenericAnalyticsRepositoryCustom extends JpaRepository<GenericAnalytics, Long> {
    boolean existsByName(String name);

    List<GenericAnalytics> findAllByName(Pageable pageable, String name);
    boolean existsByDateAndLevelAndName(String date, String level, String value);

    List<GenericAnalytics> findAllByNameOrderByDateAsc(String name);
    List<GenericAnalytics> findAllByNameOrderByDateDesc(String name);

    List<GenericAnalytics> findAllByLevel(String level);

    List<GenericAnalytics> findAllByNameAndLevel(Pageable pageable, String name, String Level);
}
