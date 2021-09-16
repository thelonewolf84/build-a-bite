package com.buildabitemvc.buildabitemvc.data;

import com.buildabitemvc.buildabitemvc.models.Tag;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TagRepository extends CrudRepository<Tag, Integer> {
    Optional<Tag> findByTagName(String tagName);

    Tag findById(int id);
}
