
package service;

import java.util.List;

public interface InterfService <U> {
    
    public void ajouter(U u);
    public List<U> getAll();
    //public List<U> findById(int id);
    
    
}
