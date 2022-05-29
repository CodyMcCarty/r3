package io.cody.r3.repos;

import io.cody.r3.domain.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {
}
