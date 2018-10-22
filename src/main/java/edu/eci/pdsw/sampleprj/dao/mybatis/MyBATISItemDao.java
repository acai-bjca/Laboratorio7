package edu.eci.pdsw.sampleprj.dao.mybatis;
import com.google.inject.Inject;
import edu.eci.pdsw.sampleprj.dao.ItemDAO;
import org.apache.ibatis.exceptions.PersistenceException;
import edu.eci.pdsw.samples.entities.Item;
import edu.eci.pdsw.sampleprj.dao.mybatis.mappers.ItemMapper;
import java.util.Date;
import java.util.List;

public class MyBATISItemDao implements ItemDAO{
	  @Inject
	  private ItemMapper itemMapper;    
	
	  @Override
	  public void save(Item it) throws PersistenceException{
		  try{
		      itemMapper.insertarItem(it);
		  }catch(org.apache.ibatis.exceptions.PersistenceException e){
		      throw new PersistenceException("Error al registrar el item "+it.toString(),e);
		  }        
	  }
	
	  @Override
	  public Item load(int id) throws PersistenceException {
		  try{
		      return itemMapper.consultarItem(id);
		  }catch(org.apache.ibatis.exceptions.PersistenceException e){
		      throw new PersistenceException("Error al consultar el item "+id,e);
		  }
	  }
	  
	  @Override
	  public List<Item> loadItemsDisponibles() throws PersistenceException {
		  try{
		      return itemMapper.consultarItemsDisponibles();
		  }catch(org.apache.ibatis.exceptions.PersistenceException e){
		      throw new PersistenceException("Error al consultar los items disponibles",e);
		  }
	  }
	  
	  @Override
	  public long loadMultaItemAlquilado(int iditem, Date fechaDevolucion) throws PersistenceException {
		  try{
		      return itemMapper.consultarMultaAlquiler(iditem, fechaDevolucion);
		  }catch(org.apache.ibatis.exceptions.PersistenceException e){
		      throw new PersistenceException("Error al consultar la multa del item alquilado "+iditem,e);
		  }
	  } 
	  
	  @Override
	  public void saveTarifaItem(int iditem, long tarifa) throws PersistenceException {
		  try{
		      itemMapper.actualizarTarifaItem(iditem, tarifa);
		  }catch(org.apache.ibatis.exceptions.PersistenceException e){
		      throw new PersistenceException("Error al actualizar la tarifa del item: "+iditem,e);
		  }
	  } 
}