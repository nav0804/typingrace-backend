package com.typingrace.domain.entity;

import com.typingrace.domain.enums.DifficultyLevel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "word_pack")
public class WordPack extends BaseAuditEntry{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(unique = true)
    private String name;

    @Column(nullable = false, length = 10)
    private String language = "en";

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DifficultyLevel level;

    @Column(nullable = false)
    private Boolean isActive = true;

    @OneToMany(mappedBy = "wordPack", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Word> words = new ArrayList<>();

    public void addWord(Word word){
        words.add(word);
        word.setWordPack(this);
    }
}
