package com.sparta.givemetuna.domain.checklist.repository;

import com.sparta.givemetuna.domain.checklist.entity.Checklist;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ChecklistRepository extends JpaRepository<Checklist, Long>, ChecklistCustomRepository {

	@Query(value = "SELECT c FROM Checklist c WHERE c.user.id = :id")
	List<Checklist> findByUserId(long id);
}
