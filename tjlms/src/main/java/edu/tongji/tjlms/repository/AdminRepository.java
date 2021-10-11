package edu.tongji.tjlms.repository;

import edu.tongji.tjlms.model.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("AdminRepository")
public interface AdminRepository extends JpaRepository<AdminEntity, String> {
    Optional<AdminEntity> findByEmailAddrAndPassword(String emailAddr, String password);
}
