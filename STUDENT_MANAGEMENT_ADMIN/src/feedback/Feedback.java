package feedback;

public class Feedback<T> {
    private int studentId;
    private int courseId;
    private T feedbackValue;

    public Feedback(int studentId, int courseId, T feedbackValue) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.feedbackValue = feedbackValue;
    }

    public int getStudentId() {
        return studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public T getFeedbackValue() {
        return feedbackValue;
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "studentId=" + studentId +
                ", courseId=" + courseId +
                ", feedbackValue=" + feedbackValue +
                '}';
    }
}