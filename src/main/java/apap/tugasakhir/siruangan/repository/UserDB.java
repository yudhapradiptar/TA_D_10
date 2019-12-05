package apap.tugasakhir.siruangan.repository;

import apap.tugasakhir.siruangan.model.UserModel;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDB extends JpaRepository<UserModel, Long> {
    UserModel findByUsername(String username);
    Optional<UserModel> findByIdUser(String uuid);
}
