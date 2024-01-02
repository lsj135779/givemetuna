package com.sparta.givemetuna.domain.checklist.repository;

import com.sparta.givemetuna.domain.checklist.entity.Checklist;
import java.util.Optional;

public interface ChecklistCustomRepository {

	Optional<Checklist> findFirstByAssigneeAndCardId(long assignee, long cardId);
}
