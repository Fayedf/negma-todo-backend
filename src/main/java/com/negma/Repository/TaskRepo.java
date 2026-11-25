package com.negma.Repository;

import com.negma.Entity.Task;
import com.negma.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TaskRepo extends JpaRepository<Task, UUID> {

    List<Task> findAllByUser(User user);
    Optional<Task> findByIdAndUser(UUID uuid,User user);

}
