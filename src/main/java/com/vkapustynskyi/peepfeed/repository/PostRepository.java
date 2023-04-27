package com.vkapustynskyi.peepfeed.repository;

import com.vkapustynskyi.peepfeed.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
