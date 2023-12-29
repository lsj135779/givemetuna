package com.sparta.givemetuna.domain.checklist.repository;

import com.sparta.givemetuna.domain.checklist.entity.Checklist;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChecklistRepository extends JpaRepository<Checklist, Long> {

	Optional<Checklist> findFirstByAssignee(Long id);
}
