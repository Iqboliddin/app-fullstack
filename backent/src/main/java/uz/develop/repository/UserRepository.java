package uz.develop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.develop.entity.UserEntity;

import javax.validation.constraints.Email;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    boolean existsByEmail(@Email String email);

    boolean existsByIdNot(Integer id);
}
