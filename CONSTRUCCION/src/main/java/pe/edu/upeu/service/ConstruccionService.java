package pe.edu.upeu.service;

import pe.edu.upeu.model.Construccion;

import java.util.List;

public interface ConstruccionService {
    void save(Construccion c);
    List<Construccion> findAll();
    void update(Construccion c, int index);
    void delete(int index);
}
