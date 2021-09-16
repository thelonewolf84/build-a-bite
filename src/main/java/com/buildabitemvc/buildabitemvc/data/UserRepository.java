package com.buildabitemvc.buildabitemvc.data;

import com.buildabitemvc.buildabitemvc.models.Recipe;
import com.buildabitemvc.buildabitemvc.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    User findByEmail(String email);

    //List<Recipe> findAllRecipesByUserId(Integer id);
}
