package com.mcq.quizapp.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.mcq.quizapp.model.UserResult;

public interface UserResultRepository extends JpaRepository<UserResult, Long> {

    // ✅ block multiple attempts per category
    boolean existsByUserEmailAndCategory(String userEmail, String category);

    // ✅ to calculate final
    List<UserResult> findByUserEmail(String userEmail);
}