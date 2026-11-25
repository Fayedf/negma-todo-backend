package com.negma.Repository;

import com.negma.Entity.Category;
import com.negma.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CategoryRepo extends JpaRepository<Category, UUID> {


    Optional<Category> findByIdAndUser(UUID id , User user);

    void deleteByCategoryAndUser(Category category ,  User user);

    List<Category> findAllByUser(User user);
}
