package com.typingrace.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "words")
public class Word extends BaseAuditEntry{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String words;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "word_pack_id", referencedColumnName = "id", nullable = false)
    private WordPack wordPack;
}
