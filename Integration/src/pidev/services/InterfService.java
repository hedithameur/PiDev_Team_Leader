
package pidev.services;

import java.util.List;

public interface InterfService <U> {
    
    public void ajouter(U u);
    public List<U> getAll();
    public List<String> getPassword(String email);
     public List<String> getNom();
    //public List<U> findById(int id);
    
    
}
