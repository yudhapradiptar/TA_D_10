package apap.tugasakhir.siruangan.repository;

import apap.tugasakhir.siruangan.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDB extends JpaRepository<UserModel, String> {
    UserModel findByUsername(String username);
}
