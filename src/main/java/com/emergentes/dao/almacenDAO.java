
package com.emergentes.dao;

import com.emergentes.modelo.almacen;
import java.util.List;

public interface almacenDAO {
    public void insert (almacen Almacen) throws Exception;
    public void update (almacen Almacen) throws Exception;
    public void delete (int id) throws Exception;
    public almacen getById(int id)throws Exception;
    public List<almacen> getAll()throws Exception;
    
}
