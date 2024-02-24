package com.anuj.quizapp.service;

import com.anuj.quizapp.da.QuestionDao;
import com.anuj.quizapp.da.QuizDao;
import com.anuj.quizapp.model.QuestionWrapper;
import com.anuj.quizapp.model.Questions;
import com.anuj.quizapp.model.Quiz;

import com.anuj.quizapp.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    QuizDao quizDao;

    @Autowired
    QuestionDao questionDao;

    //http://localhost:8080/quiz/create?category=JAVA&numQ=2&title=JQuiz
    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        List<Questions> questions= questionDao.findRandomQuestionByCategory(category,numQ);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);

        quizDao.save(quiz);

        return new ResponseEntity<>("Success", HttpStatus.CREATED);

    }

    //http://localhost:8080/quiz/get/1

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
       Optional<Quiz> quiz = quizDao.findById(id);

       List<Questions> questionsFromDB =quiz.get().getQuestions();
       List<QuestionWrapper> questionForUser =new ArrayList<>();

       for(Questions q : questionsFromDB){
           QuestionWrapper qb = new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3());
           questionForUser.add(qb);
       }
       return new ResponseEntity<>(questionForUser,HttpStatus.OK);
    }


    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {

        //if data is not optional then we can use like findbyid().get();
        Quiz quiz= quizDao.findById(id).get();
        List<Questions> questions=quiz.getQuestions();

        int right =0;
        int i=0;
        for(Response response: responses){
            if(response.getResponse().equals(questions.get(i).getRightAnswer()))
                right++;

            i++;
        }
        return new ResponseEntity<>(right,HttpStatus.OK);
    }
}
