package io.cody.r3.repos;

import io.cody.r3.domain.Staff;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StaffRepository extends JpaRepository<Staff, Long> {
}
