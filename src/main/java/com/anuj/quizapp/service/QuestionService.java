package com.anuj.quizapp.service;

import com.anuj.quizapp.model.Questions;
import com.anuj.quizapp.da.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<List<Questions>>getAllQuestions() {

        System.out.println("test");

        try {
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Questions>> getQuestionByCategory(String category) {
        try {
            return new ResponseEntity<>(questionDao.findByCategory(category),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<String> addQuestion(Questions question) {

         questionDao.save(question);
         return new ResponseEntity<>("success",HttpStatus.CREATED);


    }

    public ResponseEntity<String> deleteQuestion(Integer id) {
        questionDao.deleteById(id);
        return new ResponseEntity<>("Success",HttpStatus.OK);
    }

    public ResponseEntity<String> updateQuestion(Integer id, Questions question) {

        Optional<Questions> questions= questionDao.findById(id);
        if (questions.get().getId().equals(id)){
            questionDao.deleteById(id);
            questionDao.save(question);
        }
        return new ResponseEntity<>("Success",HttpStatus.OK);
    }
}
