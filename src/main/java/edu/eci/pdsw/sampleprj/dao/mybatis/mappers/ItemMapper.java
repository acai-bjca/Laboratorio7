package edu.eci.pdsw.sampleprj.dao.mybatis.mappers;


import edu.eci.pdsw.samples.entities.Item;
import edu.eci.pdsw.samples.entities.TipoItem;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 *
 * @author 2106913
 */
public interface ItemMapper {   
    
    public List<Item> consultarItems();        
    
    public Item consultarItem(@Param("iditem") int id);
    
    public List<Item> consultarItemsDisponibles();
    
    public void insertarItem(@Param("item") Item it);
    
    public long consultarMultaAlquiler(@Param("iditem") int iditem, @Param("fechaDevolucion")Date fechaDevolucion);
    
    public void actualizarTarifaItem(@Param("iditem") int iditem, @Param("tarifa")long tarifa);
     
}
