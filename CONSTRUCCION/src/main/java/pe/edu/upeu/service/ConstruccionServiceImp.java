package pe.edu.upeu.service;

import pe.edu.upeu.model.Construccion;
import pe.edu.upeu.repository.ConstruccionRepository;

import java.util.List;

public class ConstruccionServiceImp implements ConstruccionService{

    ConstruccionRepository cr=ConstruccionRepository.getInstance();

    private static ConstruccionService instance=new ConstruccionServiceImp();

    public static ConstruccionService getInstance(){
        if(instance==null){
            instance=new ConstruccionServiceImp();
        }
        return instance;
    }
    
    @Override
    public void save(Construccion c) {
        cr.save(c);
    }

    @Override
    public List<Construccion> findAll() {
        return cr.findAll();
    }
    
    @Override
    public void update(Construccion c, int index) {
        cr.update(c, index);
    }

    @Override
    public void delete(int index) {
        cr.delete(index);
    }
}
