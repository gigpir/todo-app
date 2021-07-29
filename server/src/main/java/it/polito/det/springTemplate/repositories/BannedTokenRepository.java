package it.polito.det.springTemplate.repositories;

import it.polito.det.springTemplate.entities.BannedToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface BannedTokenRepository extends JpaRepository<BannedToken, String> {
    List<BannedToken> findAllByExpiryDateBefore(Timestamp timestamp);
}
