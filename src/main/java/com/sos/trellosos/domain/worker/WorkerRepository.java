package com.sos.trellosos.domain.worker;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkerRepository extends JpaRepository<Worker, Long> {

  Optional<Worker> findByUserIdAndCardId(Long userId, Long cardId);
}
