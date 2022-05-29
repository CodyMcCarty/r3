package io.cody.r3.repos;

import io.cody.r3.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ImageRepository extends JpaRepository<Image, Long> {
}
