package edu.eci.pdsw.sampleprj.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import edu.eci.pdsw.samples.entities.Item;
import edu.eci.pdsw.samples.entities.ItemRentado;

/**
 * Objetos de Acceso a los Datos (DAO) de Item
 * @author amalia
 *
 */
public interface ItemDAO {

   public void save(Item it) throws PersistenceException;
   public Item load(int id) throws PersistenceException;
   public List<Item> loadItemsDisponibles() throws PersistenceException;
   public long loadMultaItemAlquilado(int iditem, Date fechaDevolucion) throws PersistenceException;
   public void saveTarifaItem(int iditem, long tarifa) throws PersistenceException;
   public List<Item> loadItemsRentadosCliente(long doc) throws PersistenceException;
}