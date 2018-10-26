package edu.eci.pdsw.sampleprj.dao;

import java.util.List;
import org.apache.ibatis.exceptions.PersistenceException;

import edu.eci.pdsw.samples.entities.Cliente;
import edu.eci.pdsw.samples.entities.ItemRentado;

/**
 * Objetos de Acceso a los Datos (DAO) de Cliente
 * @author amalia
 *
 */
public interface ClienteDAO {
	public void save(Cliente cliente) throws PersistenceException;
	public void saveAlquilerItem(ItemRentado itr, long doc) throws PersistenceException;
	public void saveEstadoCliente(long docu, boolean estado) throws PersistenceException;
	public Cliente load(long id) throws PersistenceException;
	public List<Cliente> loadClientes() throws PersistenceException;	
}
