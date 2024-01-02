package com.sparta.givemetuna.domain.checklist.repository;

import com.sparta.givemetuna.global.config.QueryDslConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ChecklistCustomRepositoryImpl implements ChecklistCustomRepository {

	private final QueryDslConfig qd;

//	@Override
//	public Optional<Checklist> findFirstByAssigneeAndCardId(long assignee, long cardId) {
//		return Optional.of(
//			qd.query()
//				.selectFrom(checklist)
//				.where(checklist.user.id.eq(assignee), checklist.card.id.eq(cardId))
//				.leftJoin(checklist.user, user)
//				.leftJoin(checklist.card, card)
//				.fetchFirst());
//	}

}
