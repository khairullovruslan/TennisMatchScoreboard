package org.tomato.tennismatchscoreboardweb.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "matches")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Player player1;

    @ManyToOne
    private Player player2;

    @ManyToOne
    private Player winner;

}
