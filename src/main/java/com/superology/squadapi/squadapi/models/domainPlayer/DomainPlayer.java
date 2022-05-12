package com.superology.squadapi.squadapi.models.domainPlayer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="domain_player")
public class DomainPlayer {
    @Id
    private Long id;
    private Long version;
    @OneToOne
    private Names names;
}
