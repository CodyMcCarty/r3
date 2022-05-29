package io.cody.r3.repos;

import io.cody.r3.domain.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
}
