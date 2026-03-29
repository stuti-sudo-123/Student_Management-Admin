import java.util.HashMap;
import java.util.List;
class Course{
    private String code;
    private String name;
    private int credits;
    Course(String code, String name, int credits){
        this.code=code;
        this.name=name;
        this.credits=credits;
    }
    public String toString(){
        return code + " - " + name + " (" + credits + ")";
    }  
}
public class CourseCatalogue {

    HashMap<String, HashMap<Integer, List<Course>>> catalogue = new HashMap<>();

    public CourseCatalogue() {

        // ---------- AI ----------
        HashMap<Integer, List<Course>> AI = new HashMap<>();
        AI.put(1, List.of(new Course("MA101","Maths-I",4), new Course("CS101","Programming",4), new Course("PH101","Physics",3)));
        AI.put(2, List.of(new Course("MA102","Maths-II",4), new Course("CS102","OOP",4), new Course("EE101","Basic Electrical",3)));
        AI.put(3, List.of(new Course("CS201","Data Structures",4), new Course("AI201","ML Basics",4)));
        AI.put(4, List.of(new Course("CS301","Algorithms",4), new Course("AI301","Deep Learning",4)));
        AI.put(5, List.of(new Course("AI401","NLP",4), new Course("CS401","DBMS",4)));
        AI.put(6, List.of(new Course("AI501","Computer Vision",4), new Course("CS402","Operating Systems",4)));
        AI.put(7, List.of(new Course("AI601","AI Project",6)));
        AI.put(8, List.of(new Course("AI701","Internship",6)));
        catalogue.put("AI", AI);

        // ---------- CSE ----------
        HashMap<Integer, List<Course>> CSE = new HashMap<>();
        CSE.put(1, List.of(new Course("MA101","Maths-I",4), new Course("CS101","Programming",4)));
        CSE.put(2, List.of(new Course("CS102","OOP",4), new Course("MA102","Maths-II",4)));
        CSE.put(3, List.of(new Course("CS201","Data Structures",4), new Course("CS202","Discrete Math",4)));
        CSE.put(4, List.of(new Course("CS301","DBMS",4), new Course("CS302","OS",4)));
        CSE.put(5, List.of(new Course("CS401","CN",4), new Course("CS402","Software Engg",4)));
        CSE.put(6, List.of(new Course("CS501","Compiler",4), new Course("CS502","AI",4)));
        CSE.put(7, List.of(new Course("CS601","Project",6)));
        CSE.put(8, List.of(new Course("CS701","Internship",6)));
        catalogue.put("CSE", CSE);

        // ---------- ELECTRICAL ----------
        HashMap<Integer, List<Course>> EE = new HashMap<>();
        EE.put(1, List.of(new Course("MA101","Maths-I",4), new Course("EE101","Basic Electrical",4)));
        EE.put(2, List.of(new Course("EE201","Circuits",4), new Course("MA102","Maths-II",4)));
        EE.put(3, List.of(new Course("EE301","Machines",4), new Course("EE302","Electronics",4)));
        EE.put(4, List.of(new Course("EE401","Power Systems",4), new Course("EE402","Signals",4)));
        EE.put(5, List.of(new Course("EE501","Control Systems",4)));
        EE.put(6, List.of(new Course("EE601","Power Electronics",4)));
        EE.put(7, List.of(new Course("EE701","Project",6)));
        EE.put(8, List.of(new Course("EE801","Internship",6)));
        catalogue.put("Electrical", EE);

        // ---------- ELECTRONICS ----------
        HashMap<Integer, List<Course>> ECE = new HashMap<>();
        ECE.put(1, List.of(new Course("MA101","Maths-I",4), new Course("EC101","Basic Electronics",4)));
        ECE.put(2, List.of(new Course("EC201","Circuits",4), new Course("MA102","Maths-II",4)));
        ECE.put(3, List.of(new Course("EC301","Analog",4), new Course("EC302","Digital",4)));
        ECE.put(4, List.of(new Course("EC401","Signals",4), new Course("EC402","Communication",4)));
        ECE.put(5, List.of(new Course("EC501","Microprocessors",4)));
        ECE.put(6, List.of(new Course("EC601","VLSI",4)));
        ECE.put(7, List.of(new Course("EC701","Project",6)));
        ECE.put(8, List.of(new Course("EC801","Internship",6)));
        catalogue.put("Electronics", ECE);

        // ---------- MECHANICAL ----------
        HashMap<Integer, List<Course>> ME = new HashMap<>();
        ME.put(1, List.of(new Course("MA101","Maths-I",4), new Course("ME101","Engineering Mechanics",4)));
        ME.put(2, List.of(new Course("ME201","Thermodynamics",4)));
        ME.put(3, List.of(new Course("ME301","Fluid Mechanics",4)));
        ME.put(4, List.of(new Course("ME401","Machine Design",4)));
        ME.put(5, List.of(new Course("ME501","Heat Transfer",4)));
        ME.put(6, List.of(new Course("ME601","Manufacturing",4)));
        ME.put(7, List.of(new Course("ME701","Project",6)));
        ME.put(8, List.of(new Course("ME801","Internship",6)));
        catalogue.put("Mechanical", ME);

        // ---------- CIVIL ----------
        HashMap<Integer, List<Course>> CE = new HashMap<>();
        CE.put(1, List.of(new Course("MA101","Maths-I",4), new Course("CE101","Basic Civil",4)));
        CE.put(2, List.of(new Course("CE201","Surveying",4)));
        CE.put(3, List.of(new Course("CE301","Structural Analysis",4)));
        CE.put(4, List.of(new Course("CE401","Geotechnical",4)));
        CE.put(5, List.of(new Course("CE501","Transportation",4)));
        CE.put(6, List.of(new Course("CE601","Environmental Engg",4)));
        CE.put(7, List.of(new Course("CE701","Project",6)));
        CE.put(8, List.of(new Course("CE801","Internship",6)));
        catalogue.put("Civil", CE);

        // ---------- CHEMICAL ----------
        HashMap<Integer, List<Course>> CHE = new HashMap<>();
        CHE.put(1, List.of(new Course("MA101","Maths-I",4), new Course("CH101","Chemistry",4)));
        CHE.put(2, List.of(new Course("CH201","Process Calc",4)));
        CHE.put(3, List.of(new Course("CH301","Thermodynamics",4)));
        CHE.put(4, List.of(new Course("CH401","Fluid Flow",4)));
        CHE.put(5, List.of(new Course("CH501","Heat Transfer",4)));
        CHE.put(6, List.of(new Course("CH601","Mass Transfer",4)));
        CHE.put(7, List.of(new Course("CH701","Project",6)));
        CHE.put(8, List.of(new Course("CH801","Internship",6)));
        catalogue.put("Chemical", CHE);
    }
    void display(String dept, int sem) {
        System.out.println("\nDepartment: " + dept + " | Semester: " + sem);
        List<Course> courses = catalogue.get(dept).get(sem);
        for(Course c : courses){
            System.out.println(c);
        }
    }
        public static void main(String[] args) {
        CourseCatalogue cc = new CourseCatalogue();
        cc.display("AI", 1);
        cc.display("CSE", 4);
    }
}

