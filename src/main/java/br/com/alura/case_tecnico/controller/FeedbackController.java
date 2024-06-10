package br.com.alura.case_tecnico.controller;

import br.com.alura.case_tecnico.dto.FeedbackRequestDTO;
import br.com.alura.case_tecnico.service.FeedbackService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @PostMapping("/createFeedback")
    public ResponseEntity<String> createFeedback(@RequestBody @Valid FeedbackRequestDTO body) throws Exception {
        boolean wasFeedbackCreated = this.feedbackService.createFeedback(body);

        if (wasFeedbackCreated) {
            return new ResponseEntity<>("Feedback created successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cannot create feedback", HttpStatus.BAD_REQUEST);
        }
    }

}
