package br.com.alura.case_tecnico.service;

import br.com.alura.case_tecnico.dto.FeedbackRequestDTO;
import br.com.alura.case_tecnico.entity.enrollment.Enrollment;
import br.com.alura.case_tecnico.entity.feedback.Feedback;
import br.com.alura.case_tecnico.repository.FeedbackRepository;
import br.com.alura.case_tecnico.utils.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedbackService {

    @Autowired
    private final FeedbackRepository feedbackRepository;

    @Autowired
    private final EnrollmentService enrollmentService;

    private final EmailSender emailSender;

    public FeedbackService(FeedbackRepository feedbackRepository, EnrollmentService enrollmentService, EmailSender emailSender) {
        this.feedbackRepository = feedbackRepository;
        this.enrollmentService = enrollmentService;
        this.emailSender = emailSender;
    }

    public boolean createFeedback(FeedbackRequestDTO feedbackRequestDTO) throws Exception {
        Enrollment enrollment = this.enrollmentService.findById(feedbackRequestDTO.enrollmentId());

        if (enrollment != null) {
            Feedback feedback = new Feedback(feedbackRequestDTO, enrollment);
            this.feedbackRepository.save(feedback);

            if (feedbackRequestDTO.rating() < 6) {
                String instructorEmail = enrollment.getUser().getEmail();
                String subject = "Low Course Rating Alert";
                String body = "The course you taught received a low rating.\n\nRating: " + feedbackRequestDTO.rating() +
                        "\nComment: " + feedbackRequestDTO.comment();

                emailSender.sendEmail(instructorEmail, subject, body);
            }

            return true;
        }

        return false;
    }

}
