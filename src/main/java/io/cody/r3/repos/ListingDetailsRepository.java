package io.cody.r3.repos;

import io.cody.r3.domain.ListingDetails;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ListingDetailsRepository extends JpaRepository<ListingDetails, Long> {
}
