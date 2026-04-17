package feedback;

import java.util.List;

public class FeedbackService 
{

    private FeedbackDAO feedbackDAO = new FeedbackDAO();
    public <T> void giveFeedback(int studentId, int courseId, T value) {
        Feedback<T> feedback = new Feedback<>(studentId, courseId, value);
        feedbackDAO.addFeedback(feedback);
    }
    public List<Feedback<?>> viewFeedback(int courseId) {
        return feedbackDAO.getFeedbackByCourse(courseId);
    }
}