package com.typingrace.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "leaderboard", indexes = {
        @Index(name = "idx_lb_score", columnList = "score DESC")
})
public class Leaderboard extends BaseAuditEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true, foreignKey = @ForeignKey(name = "fk_lb_user"))
    private User user;

    @Column(name = "best_wpm", nullable = false)
    private Integer bestWpm = 0;

    @Column(name = "avg_wpm", nullable = false, precision = 6, scale = 2)
    private BigDecimal avgWpm = BigDecimal.ZERO;

    @Column(name = "total_races", nullable = false)
    private Integer totalRaces = 0;

    @Column(name = "total_wins", nullable = false)
    private Integer totalWins = 0;

    @Column(name = "score", nullable = false)
    private Integer score = 0;

    @Column(name = "rank_val")
    private Integer rank;

}