package com.typingrace.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "race_participants",
        uniqueConstraints = {
                @UniqueConstraint(name = "uq_room_user", columnNames = {"race_room_id", "user_id"})
        },
        indexes = {
                @Index(name = "idx_participant_room", columnList = "race_room_id"),
                @Index(name = "idx_participant_user", columnList = "user_id")
        }
)
public class RaceParticipant extends BaseAuditEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "race_room_id", nullable = false, foreignKey = @ForeignKey(name = "fk_part_room"))
    private RaceRoom raceRoom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "fk_part_user"))
    private User user;

    @Column(name = "position")
    private Integer position;

    @Column(name = "wpm")
    private Integer wpm;

    @Column(name = "accuracy", precision = 5, scale = 2)
    private BigDecimal accuracy;

    @Column(name = "progress", nullable = false)
    private Integer progress = 0;

    @Column(name = "finished_at")
    private LocalDateTime finishedAt;

    @Column(name = "joined_at", nullable = false, updatable = false)
    private LocalDateTime joinedAt = LocalDateTime.now();
}