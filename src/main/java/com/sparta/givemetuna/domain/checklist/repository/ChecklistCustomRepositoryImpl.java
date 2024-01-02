package com.sparta.givemetuna.domain.checklist.repository;

import static com.sparta.givemetuna.domain.card.entity.QCard.card;
import static com.sparta.givemetuna.domain.checklist.entity.QChecklist.checklist;
import static com.sparta.givemetuna.domain.user.entity.QUser.user;

import com.sparta.givemetuna.domain.checklist.entity.Checklist;
import com.sparta.givemetuna.domain.configuration.QueryDslConfig;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ChecklistCustomRepositoryImpl implements ChecklistCustomRepository {

	private final QueryDslConfig qd;

	@Override
	public Optional<Checklist> findFirstByAssignee(long id) {
		return Optional.of(
			qd.query()
				.selectFrom(checklist)
				.where(checklist.user.id.eq(id))
				.leftJoin(checklist.user, user)
				.leftJoin(checklist.card, card)
				.fetchFirst());
	}

}
