package pe.edu.upeu.repository;

import pe.edu.upeu.model.Construccion;
import java.util.ArrayList;
import java.util.List;

public class ConstruccionRepository {
    public static ConstruccionRepository instance=new ConstruccionRepository();

    public static ConstruccionRepository getInstance(){
        if(instance==null){
            instance=new ConstruccionRepository();
        }
        return instance;
    }
    List<Construccion> construcciones=new ArrayList<>();
    
    public void save(Construccion c){
        construcciones.add(c);
    }
    
    public List<Construccion> findAll(){
        return construcciones;
    }
    
    public void update(Construccion c, int index){
        construcciones.set(index, c);
    }
    
    public void delete(int index){
        construcciones.remove(index);
    }
}
