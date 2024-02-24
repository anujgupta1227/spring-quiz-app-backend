package com.anuj.quizapp.controller;

import com.anuj.quizapp.model.QuestionWrapper;
import com.anuj.quizapp.model.Questions;
import com.anuj.quizapp.model.Response;
import com.anuj.quizapp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="quiz")
public class QuizController {

    @Autowired
    QuizService quizService;
    @PostMapping(value="create")
    public ResponseEntity<String> createQuiz(@RequestParam String category,@RequestParam int numQ,@RequestParam String title ){

        return quizService.createQuiz(category,numQ,title);
    }

    @GetMapping(value="get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id){

        return quizService.getQuizQuestions(id);
    }


    @PostMapping(value = "submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> responses){
        return quizService.calculateResult(id,responses);
    }


}
