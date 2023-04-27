package com.vkapustynskyi.peepfeed.repository;

import com.vkapustynskyi.peepfeed.entity.MainUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MainUserRepository extends JpaRepository<MainUser, Long> {
}
