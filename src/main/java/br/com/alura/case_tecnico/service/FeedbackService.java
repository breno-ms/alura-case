package br.com.alura.case_tecnico.service;

import br.com.alura.case_tecnico.dto.FeedbackRequestDTO;
import br.com.alura.case_tecnico.entity.enrollment.Enrollment;
import br.com.alura.case_tecnico.entity.feedback.Feedback;
import br.com.alura.case_tecnico.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedbackService {

    @Autowired
    private final FeedbackRepository feedbackRepository;

    @Autowired
    private final EnrollmentService enrollmentService;

    public FeedbackService(FeedbackRepository feedbackRepository, EnrollmentService enrollmentService) {
        this.feedbackRepository = feedbackRepository;
        this.enrollmentService = enrollmentService;
    }

    public boolean createFeedback(FeedbackRequestDTO feedbackRequestDTO) throws Exception {
        Enrollment enrollment = this.enrollmentService.findById(feedbackRequestDTO.enrollmentId());

        if (enrollment != null) {
            Feedback feedback = new Feedback(feedbackRequestDTO, enrollment);
            this.feedbackRepository.save(feedback);

            return true;
        }

        return false;
    }

}
