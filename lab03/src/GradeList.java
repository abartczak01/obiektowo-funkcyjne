import java.util.ArrayList;

public class GradeList {
    private ArrayList<Double> gradesList = new ArrayList<>();

    public double getAverage() {
        if (gradesList.isEmpty()) {
            return -1;
        }
        
        double sum = 0.0;
        for (double grade : gradesList) {
            sum += grade;
        }
        return sum / gradesList.size();
    }

    public double getBestGrade() {
        if (gradesList.isEmpty()) {
            return -1;
        }

        double max = gradesList.get(0);
        for (int i = 1; i < gradesList.size(); i++) {
            if (gradesList.get(i) > max) {
                max = gradesList.get(i);
            }
        }
        return max;
    }

    public double getWorstGrade() {
        if (gradesList.isEmpty()) {
            return -1;
        }
        double min = gradesList.get(0);
        for (int i = 1; i < gradesList.size(); i++) {
            if (gradesList.get(i) < min) {
                min = gradesList.get(i);
            }
        }
        return min;
    }

    public void addGrade(double grade) {
        gradesList.add(grade);
    }
}
