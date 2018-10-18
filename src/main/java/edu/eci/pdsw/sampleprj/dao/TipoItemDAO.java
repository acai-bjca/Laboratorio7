package edu.eci.pdsw.sampleprj.dao;

import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import edu.eci.pdsw.samples.entities.Item;
import edu.eci.pdsw.samples.entities.TipoItem;

/**
 * Objetos de Acceso a los Datos (DAO) de TipoItem
 * @author amalia
 *
 */
public interface TipoItemDAO {
	public TipoItem loadTipoItem (int iditem) throws PersistenceException;
	public List<TipoItem> loadTiposItem () throws PersistenceException;
}

