package leonardo.labutilities.qualitylabpro.repositories;

import leonardo.labutilities.qualitylabpro.domain.entitys.Lot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LotRepository extends JpaRepository<Lot, Long> {

}
