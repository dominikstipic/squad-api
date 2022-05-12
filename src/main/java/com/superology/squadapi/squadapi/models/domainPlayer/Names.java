package com.superology.squadapi.squadapi.models.domainPlayer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Names {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Stats> stats;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Stats> unified;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Stats> livescout;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Stats> livescore;
}
