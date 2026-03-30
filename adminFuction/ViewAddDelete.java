package adminFuction;
import coursecatalogue.CourseCatalogue;

public class ViewAddDelete extends CourseCatalogue {
    public void view(String dep,int sem) throws DepInvalid, SemInvalid {
        if(catalogue.containsKey(dep)){
                if(catalogue.get(dep).containsKey(sem)){

                     display(dep, sem);
                }
                else{
                    throw new SemInvalid("Semester entered is invalid: " + sem);
            }
                }
        
        else{
             throw new DepInvalid("Department entered is invalid: " + dep);
        }
       
    }
     public void add(String dep,int sem) throws DepInvalid, SemInvalid {
        
        if(catalogue.containsKey(dep)){
                if(catalogue.get(dep).containsKey(sem)){
                     
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
  

    

