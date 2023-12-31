package com.sparta.givemetuna.domain.card.entity;

import com.sparta.givemetuna.domain.checklist.entity.Checklist;
import com.sparta.givemetuna.domain.common.BaseEntity;
import com.sparta.givemetuna.domain.stage.entity.Stage;
import com.sparta.givemetuna.domain.user.entity.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "card")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Card extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User creator;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User assignor;

    @Column(nullable = false)
    private String title;

    @Column
    private Integer priority;

    @Column
    private Boolean isDone;

    @Column
    private Timestamp startedAt;

    @Column
    private Timestamp closedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stage_id")
    private Stage stage;

    @OneToMany(mappedBy = "card", targetEntity = Checklist.class, cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Checklist> checklists = new ArrayList<>();

    @Builder
    private Card(Long id, User creator, User assignor, Stage stage, String title, Integer priority,
            Boolean isDone, Timestamp startedAt, Timestamp closedAt) {
        this.id = id;
        this.creator = creator;
        this.assignor = assignor;
        this.stage = stage;
        this.title = title;
        this.priority = priority;
        this.isDone = isDone;
        this.startedAt = startedAt;
        this.closedAt = closedAt;
    }

    public void updateStage(Stage stage) {
        this.stage = stage;
    }

    public void updateTitle(String title) {
        this.title = title;
    }

//  완성하고나서 쓸모 없으면 지우기
//    public static Card of(Card card) {
//        return Card.builder()
//                .creator(card.getCreator())
//                .assignor(card.getAssignor())
//                .stage(card.getStage())
//                .title(card.getTitle())
//                .priority(card.getPriority())
//                .isDone(card.getIsDone())
//                .startedAt(card.getStartedAt())
//                .closedAt(card.getClosedAt())
//                .build();
//    }
}
