package apap.tugasakhir.siruangan.repository;

import apap.tugasakhir.siruangan.model.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDB extends JpaRepository<RoleModel, Long> {
}
