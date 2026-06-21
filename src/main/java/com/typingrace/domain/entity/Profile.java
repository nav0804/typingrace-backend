package com.typingrace.domain.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "profile_master")
public class Profile extends BaseAuditEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    // Fixed: Properly mapped entity relationship targeting the actual account entity lifecycle
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, unique = true)
    private User user;

    @Column(nullable = false)
    private String name;

    @Column(name = "dob", nullable = true)
    private LocalDate dob;

    @Column(nullable = true, columnDefinition = "TEXT")
    private String bio;

    @Column(nullable = true, length = 30)
    private String gender;

    @Column(name = "player_rank", nullable = true) // Protected against SQL Reserved Keyword errors
    private Integer rank;

    @Column(name = "profile_image_url", nullable = true)
    private String profileImageUrl;
}