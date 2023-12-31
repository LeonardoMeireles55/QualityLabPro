package leonardo.labutilities.qualitylabpro.repository;

import leonardo.labutilities.qualitylabpro.domain.entitys.DefaultValues;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DefaultValuesRepositoryCustom extends JpaRepository<DefaultValues, Long> {
    void deleteByName(String name);
    boolean existsByName(String name);
}
