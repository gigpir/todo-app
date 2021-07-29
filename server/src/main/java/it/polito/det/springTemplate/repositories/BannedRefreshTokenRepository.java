package it.polito.det.springTemplate.repositories;

import it.polito.det.springTemplate.entities.BannedRefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface BannedRefreshTokenRepository extends JpaRepository<BannedRefreshToken, String> {
    List<BannedRefreshToken> findAllByExpiryDateBefore(Timestamp timestamp);
}
