package feedback;

import java.util.List;

public class FeedbackService 
{

    private static FeedbackDAO feedbackDAO = new FeedbackDAO();
    public static <T> void giveFeedback(int studentId, int courseId, T value) {
        Feedback<T> feedback = new Feedback<>(studentId, courseId, value);
        try {
            feedbackDAO.addFeedback(feedback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static List<Feedback<?>> viewFeedback(int courseId) {
        return feedbackDAO.getFeedbackByCourse(courseId);
    }
}