package adminFuction;
import connection.*;
import java.util.List;
import java.util.ArrayList;

public class ViewAddDelete{
    public void view(String dep,int sem) throws DepInvalid, SemInvalid {
        dep= dep.trim().toLowerCase();
       
       
    }
    public void add(List<Course> c,String dep,int sem) throws DepInvalid, SemInvalid {
        dep=dep.trim().toLowerCase();
       
       
     }
     public void delete(String code,String dep,int sem)throws DepInvalid, SemInvalid{
               dep= dep.trim().toLowerCase();
        if(catalogue.containsKey(dep)){
                if(catalogue.get(dep).containsKey(sem)){
                    boolean found = false;
                    List<Course> C = new ArrayList<>(catalogue.get(dep).get(sem)); 
                    for(Course c:C ){
                        if((c.getCode()).equals(code.toUpperCase())){
                            C.remove(c);
                            System.out.println("Course with code: "+code+" is deleted");
                            catalogue.get(dep).put(sem, C);
                            found = true;
                            break;
                        }
                    }
                    if(!found){
                        System.out.println("There is no course with code: "+ code);
                    }
                }
                else{
                    throw new SemInvalid("Semester entered is invalid: " + sem);
            }
                }
        
        else{
             throw new DepInvalid("Department entered is invalid: " + dep);
        }
     }

}

  
  

    

