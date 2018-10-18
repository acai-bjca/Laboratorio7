package edu.eci.pdsw.sampleprj.dao.mybatis;

import org.apache.ibatis.exceptions.PersistenceException;
import com.google.inject.Inject;
import edu.eci.pdsw.sampleprj.dao.ItemRentadoDAO;
import edu.eci.pdsw.sampleprj.dao.mybatis.mappers.ClienteMapper;
import edu.eci.pdsw.sampleprj.dao.mybatis.mappers.ItemRentadoMapper;
import edu.eci.pdsw.samples.entities.ItemRentado;

public class MyBATISItemRentadoDao implements ItemRentadoDAO {	
	@Inject
	private ItemRentadoMapper itemRentadoMapper;
	
	@Override
	public ItemRentado load(int id) throws PersistenceException {
		try{
			itemRentadoMapper.consultarItemRentado(id);
		}
		catch(org.apache.ibatis.exceptions.PersistenceException e){
			throw new PersistenceException("Error al consultar el itemRentado con id de Item"+ id,e);
		}
		return null;
	}
	

}
