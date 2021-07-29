package it.polito.det.springTemplate.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class BannedRefreshToken {
    @Id
    private String refreshToken;
    private Timestamp expiryDate;
}
