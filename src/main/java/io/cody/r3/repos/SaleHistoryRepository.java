package io.cody.r3.repos;

import io.cody.r3.domain.SaleHistory;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SaleHistoryRepository extends JpaRepository<SaleHistory, Long> {
}
