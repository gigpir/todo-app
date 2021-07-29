package it.polito.det.springTemplate.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue("ROLE_ADMIN")
public class Admin extends User {
}
