package com.dauphine.Work_Nest_backend.repository;

import com.dauphine.Work_Nest_backend.entity.Selection;
import com.dauphine.Work_Nest_backend.enums.Decision;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SelectionRepository extends JpaRepository<Selection, Integer> {
    List<Selection> findByApplicationId(Integer applicationId);
    List<Selection> findByDecision(Decision decision);
}