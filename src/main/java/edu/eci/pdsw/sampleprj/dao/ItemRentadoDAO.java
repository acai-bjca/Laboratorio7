package edu.eci.pdsw.sampleprj.dao;

import org.apache.ibatis.exceptions.PersistenceException;
import edu.eci.pdsw.samples.entities.ItemRentado;

/**
 * Objetos de Acceso a los Datos (DAO) de ItemRentado
 * @author amalia
 *
 */
public interface ItemRentadoDAO {
	public ItemRentado load(int id) throws PersistenceException;
}
