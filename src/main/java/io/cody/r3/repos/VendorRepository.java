package io.cody.r3.repos;

import io.cody.r3.domain.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;


public interface VendorRepository extends JpaRepository<Vendor, Long> {
}
