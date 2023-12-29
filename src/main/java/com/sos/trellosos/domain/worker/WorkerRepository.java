package com.sos.trellosos.domain.worker;

import com.sos.trellosos.domain.card.Card;
import com.sos.trellosos.domain.user.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkerRepository extends JpaRepository<Worker, Long> {

  Optional<Worker> findByUserAndCard(User user, Card card);
}
