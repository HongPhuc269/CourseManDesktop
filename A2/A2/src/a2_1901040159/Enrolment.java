package a2_1901040159;

public class Enrolment {
    private int id;
    private Student student;
    private Module module;
    private double internal;
    private double examination;

    private double finalScore;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Enrolment(Student student, Module module) {
        this.student = student;
        this.module = module;
        this.internal = 0;
        this.examination = 0;
        this.finalScore = 0;
    }

    public char finalResult() {
        finalScore = Double.valueOf((0.4 * internal) + (0.6 * examination));
        return (finalScore >= 8.0 && finalScore <= 10.0) ? 'E' : (finalScore >= 6.5) ? 'G' : (finalScore >= 5.0) ? 'P'
                : 'F';
    }

    public char getFinal(double internal,double examination) {
        finalScore = Double.valueOf((0.4 * internal) + (0.6 * examination));
        return (finalScore >= 8.0 && finalScore <= 10.0) ? 'E' : (finalScore >= 6.5) ? 'G' : (finalScore >= 5.0) ? 'P'
                : 'F';
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public double getInternal() {
        return internal;
    }

    public void setInternal(double internal) {
        if (internal >= 0.0 && internal <= 10.0) this.internal = internal;
    }

    public double getExamination() {
        return examination;
    }

    public void setExamination(double examination) {
        if (examination >= 0 && examination <= 10.0) this.examination = examination;
    }

    public double getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(double finalScore) {
        this.finalScore = finalScore;
    }


    public String report() {
        return "Enrolment{" +
                "student=" + getStudent().toString() +
                ", moduleCode=" + getModule().toString() +
                '}';
    }


    @Override
    public String toString() {
        return "Enrolment{" +
                "student=" + getStudent().toString() +
                ", module=" + getModule().toString() +
                ", internal=" + internal +
                ", examination=" + examination +
                ", finalScore=" + finalResult() +
                '}';
    }

    public static void main(String[] args) {
//        Student s1 = new Student("A", LocalDate.now(), "", "");
//        CompulsoryModule module1 = new CompulsoryModule("JSD", 1, 3);
//        Enrolment e1 = new Enrolment(s1, module1);
////        e1.setInternal(8.2f);
////        e1.setExamination(8.6f);
//
//        e1.finalScore = 8.1 + 0.2;
//        System.out.println(e1.getStudent().getId() + " - " + e1.getModule().getCode());
//        System.out.println(e1.finalResult());
    }
}
