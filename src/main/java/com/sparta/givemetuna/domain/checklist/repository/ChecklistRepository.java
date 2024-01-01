package com.sparta.givemetuna.domain.checklist.repository;

import com.sparta.givemetuna.domain.checklist.entity.Checklist;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ChecklistRepository extends JpaRepository<Checklist, Long> {

	/**
	 * @param id
	 * @return
	 * @apiNote @임지훈 -> @이승준 하나의 사용자에 대해 여러 체크리스트가 있을 수 있다보니 List로 반환해야하지 않을까요??
	 */
	@Query("SELECT c FROM Checklist c WHERE c.user.id = :id")
	Optional<Checklist> findFirstByAssignee(Long id);

	@Query("SELECT c FROM Checklist c WHERE c.user.id = :id")
	List<Checklist> findByUserId(long id);
}
