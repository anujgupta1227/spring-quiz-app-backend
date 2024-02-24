package com.anuj.quizapp.controller;

import com.anuj.quizapp.model.Questions;
import com.anuj.quizapp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "question")
public class QuestionController {

    @Autowired
    QuestionService questionService;
    @GetMapping(value = "allQuestions")
    public ResponseEntity<List<Questions>> getAllQuestion(){

        return questionService.getAllQuestions();
    }

    @GetMapping(value="category/{category}")
    // @pathvariable is used when we need to pass same varible as the url variable.
    public ResponseEntity<List<Questions> >getQuestionByCategory(@PathVariable String category){
        return questionService.getQuestionByCategory(category);

    }

    //@requestbody is used when we need to add value from client side to the body of server side.

    @PostMapping(value = "add")
    public ResponseEntity<String> addQuestion(@RequestBody Questions question){
       return questionService.addQuestion(question);

    }

    @DeleteMapping(value ="delete/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable Integer id){
        return questionService.deleteQuestion(id);
    }

    @PatchMapping(value = "update/{id}")
    public ResponseEntity<String> updateQuestion(@RequestBody Questions question , @PathVariable Integer id){
        return questionService.updateQuestion(id,question);
    }
}
