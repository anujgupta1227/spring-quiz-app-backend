package com.anuj.quizapp.da;

import com.anuj.quizapp.model.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//JpaRepository takes 2 arguments 1 is class name (table) and primary key datatype here it is Integer.

public interface QuestionDao extends JpaRepository<Questions,Integer>{

    List<Questions> findByCategory(String category);

    @Query(value= "SELECT * FROM questions q where q.category=:category ORDER BY RANDOM() LIMIT :numQ",nativeQuery = true)
    List<Questions> findRandomQuestionByCategory(String category, int numQ);
}
