package com.typingrace.domain.entity;

import com.typingrace.domain.enums.RaceStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "race_rooms", indexes = {
        @Index(name = "idx_room_code", columnList = "room_code"),
        @Index(name = "idx_room_status", columnList = "status")
})
public class RaceRoom extends BaseAuditEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "room_code", nullable = false, unique = true, length = 8)
    private String roomCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "host_user_id", nullable = false, foreignKey = @ForeignKey(name = "fk_room_host"))
    private User hostUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "word_pack_id", foreignKey = @ForeignKey(name = "fk_room_pack"))
    private WordPack wordPack;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RaceStatus status = RaceStatus.WAITING;

    @Column(name = "word_count", nullable = false)
    private Integer wordCount = 50;

    @Column(name = "text_snapshot", nullable = false, columnDefinition = "TEXT")
    private String textSnapshot;

    @Column(name = "max_players", nullable = false)
    private Integer maxPlayers = 2;

    @Column(name = "started_at")
    private LocalDateTime startedAt;

    @Column(name = "finished_at")
    private LocalDateTime finishedAt;

}