package com.superology.squadapi.squadapi.models.domainSquad;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="player")
public class Player {
    @Id
    private Long player_id;
    private Integer weight;
    private Integer shirt_number;
    private Integer preferred_foot;
    private Integer position;
    private String last_name;
    private Integer height;
    private String full_name;
    private String first_name;
    private String country_code;
    private Date birth_date;
}
