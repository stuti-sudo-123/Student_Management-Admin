package coursecatalogue;
import java.util.HashMap;
import java.util.List;

public class CourseCatalogue {

    protected HashMap<String, HashMap<Integer, List<Course>>> catalogue = new HashMap<>();

    public CourseCatalogue() {

        // ---------- AI ----------
        HashMap<Integer, List<Course>> ai = new HashMap<>();          
        ai.put(1, List.of(new Course("MA101","Maths-I",4), new Course("CS101","Programming",4), new Course("PH101","Physics",3)));
        ai.put(2, List.of(new Course("MA102","Maths-II",4), new Course("CS102","OOP",4), new Course("EE101","Basic Electrical",3)));
        ai.put(3, List.of(new Course("CS201","Data Structures",4), new Course("AI201","ML Basics",4)));
        ai.put(4, List.of(new Course("CS301","Algorithms",4), new Course("AI301","Deep Learning",4)));
        ai.put(5, List.of(new Course("AI401","NLP",4), new Course("CS401","DBMS",4)));
        ai.put(6, List.of(new Course("AI501","Computer Vision",4), new Course("CS402","Operating Systems",4)));
        ai.put(7, List.of(new Course("AI601","AI Project",6)));
        ai.put(8, List.of(new Course("AI701","Internship",6)));
        catalogue.put("ai", ai);                                       

        // ---------- CSE ----------
        HashMap<Integer, List<Course>> cse = new HashMap<>();         
        cse.put(1, List.of(new Course("MA101","Maths-I",4), new Course("CS101","Programming",4)));
        cse.put(2, List.of(new Course("CS102","OOP",4), new Course("MA102","Maths-II",4)));
        cse.put(3, List.of(new Course("CS201","Data Structures",4), new Course("CS202","Discrete Math",4)));
        cse.put(4, List.of(new Course("CS301","DBMS",4), new Course("CS302","OS",4)));
        cse.put(5, List.of(new Course("CS401","CN",4), new Course("CS402","Software Engg",4)));
        cse.put(6, List.of(new Course("CS501","Compiler",4), new Course("CS502","AI",4)));
        cse.put(7, List.of(new Course("CS601","Project",6)));
        cse.put(8, List.of(new Course("CS701","Internship",6)));
        catalogue.put("cse", cse);                                 

        // ---------- ELECTRICAL ----------
        HashMap<Integer, List<Course>> electrical = new HashMap<>(); 
        electrical.put(1, List.of(new Course("MA101","Maths-I",4), new Course("EE101","Basic Electrical",4)));
        electrical.put(2, List.of(new Course("EE201","Circuits",4), new Course("MA102","Maths-II",4)));
        electrical.put(3, List.of(new Course("EE301","Machines",4), new Course("EE302","Electronics",4)));
        electrical.put(4, List.of(new Course("EE401","Power Systems",4), new Course("EE402","Signals",4)));
        electrical.put(5, List.of(new Course("EE501","Control Systems",4)));
        electrical.put(6, List.of(new Course("EE601","Power Electronics",4)));
        electrical.put(7, List.of(new Course("EE701","Project",6)));
        electrical.put(8, List.of(new Course("EE801","Internship",6)));
        catalogue.put("electrical", electrical);                      

        // ---------- ELECTRONICS ----------
        HashMap<Integer, List<Course>> ece = new HashMap<>();        
        ece.put(1, List.of(new Course("MA101","Maths-I",4), new Course("EC101","Basic Electronics",4)));
        ece.put(2, List.of(new Course("EC201","Circuits",4), new Course("MA102","Maths-II",4)));
        ece.put(3, List.of(new Course("EC301","Analog",4), new Course("EC302","Digital",4)));
        ece.put(4, List.of(new Course("EC401","Signals",4), new Course("EC402","Communication",4)));
        ece.put(5, List.of(new Course("EC501","Microprocessors",4)));
        ece.put(6, List.of(new Course("EC601","VLSI",4)));
        ece.put(7, List.of(new Course("EC701","Project",6)));
        ece.put(8, List.of(new Course("EC801","Internship",6)));
        catalogue.put("ece", ece);                                    

        // ---------- MECHANICAL ----------
        HashMap<Integer, List<Course>> mechanical = new HashMap<>();  
        mechanical.put(1, List.of(new Course("MA101","Maths-I",4), new Course("ME101","Engineering Mechanics",4)));
        mechanical.put(2, List.of(new Course("ME201","Thermodynamics",4)));
        mechanical.put(3, List.of(new Course("ME301","Fluid Mechanics",4)));
        mechanical.put(4, List.of(new Course("ME401","Machine Design",4)));
        mechanical.put(5, List.of(new Course("ME501","Heat Transfer",4)));
        mechanical.put(6, List.of(new Course("ME601","Manufacturing",4)));
        mechanical.put(7, List.of(new Course("ME701","Project",6)));
        mechanical.put(8, List.of(new Course("ME801","Internship",6)));
        catalogue.put("mechanical", mechanical);                       

        // ---------- CIVIL ----------
        HashMap<Integer, List<Course>> civil = new HashMap<>();       
        civil.put(1, List.of(new Course("MA101","Maths-I",4), new Course("CE101","Basic Civil",4)));
        civil.put(2, List.of(new Course("CE201","Surveying",4)));
        civil.put(3, List.of(new Course("CE301","Structural Analysis",4)));
        civil.put(4, List.of(new Course("CE401","Geotechnical",4)));
        civil.put(5, List.of(new Course("CE501","Transportation",4)));
        civil.put(6, List.of(new Course("CE601","Environmental Engg",4)));
        civil.put(7, List.of(new Course("CE701","Project",6)));
        civil.put(8, List.of(new Course("CE801","Internship",6)));
        catalogue.put("civil", civil);                                

        // ---------- CHEMICAL ----------
        HashMap<Integer, List<Course>> chemical = new HashMap<>();    
        chemical.put(1, List.of(new Course("MA101","Maths-I",4), new Course("CH101","Chemistry",4)));
        chemical.put(2, List.of(new Course("CH201","Process Calc",4)));
        chemical.put(3, List.of(new Course("CH301","Thermodynamics",4)));
        chemical.put(4, List.of(new Course("CH401","Fluid Flow",4)));
        chemical.put(5, List.of(new Course("CH501","Heat Transfer",4)));
        chemical.put(6, List.of(new Course("CH601","Mass Transfer",4)));
        chemical.put(7, List.of(new Course("CH701","Project",6)));
        chemical.put(8, List.of(new Course("CH801","Internship",6)));
        catalogue.put("chemical", chemical);                          
    }

    public void display(String dept, int sem) {
        System.out.println("\nDepartment: " + dept + " | Semester: " + sem);
        List<Course> courses = catalogue.get(dept).get(sem);
        for (Course c : courses) {
            System.out.println(c);
        }
    }
}