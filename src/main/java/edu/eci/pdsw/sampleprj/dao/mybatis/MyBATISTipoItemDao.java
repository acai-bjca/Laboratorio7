package edu.eci.pdsw.sampleprj.dao.mybatis;

import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;

import com.google.inject.Inject;

import edu.eci.pdsw.sampleprj.dao.TipoItemDAO;
import edu.eci.pdsw.sampleprj.dao.mybatis.mappers.ItemMapper;
import edu.eci.pdsw.sampleprj.dao.mybatis.mappers.TipoItemMapper;
import edu.eci.pdsw.samples.entities.Item;
import edu.eci.pdsw.samples.entities.TipoItem;

public class MyBATISTipoItemDao implements TipoItemDAO {
	@Inject
	private TipoItemMapper tipoItemMapper; 
	
	@Override
	  public TipoItem loadTipoItem(int iditem) throws PersistenceException {
		  try{
		      return tipoItemMapper.getTipoItem(iditem);
		  }
		  catch(org.apache.ibatis.exceptions.PersistenceException e){
		      throw new PersistenceException("Error al consultar el tipo de item "+iditem,e);
		  }
	  }
	
	@Override
	public List<TipoItem> loadTiposItem() throws PersistenceException {
		try{
			return tipoItemMapper.getTiposItems();
		}
		catch(org.apache.ibatis.exceptions.PersistenceException e){
		    throw new PersistenceException("Error al consultar los tipso de item. ",e);
		}

	}

}
