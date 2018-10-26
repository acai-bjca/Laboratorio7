package edu.eci.pdsw.samples.services.impl;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;

import com.google.inject.Inject;
import com.mysql.jdbc.integration.jboss.ExtendedMysqlExceptionSorter;

import edu.eci.pdsw.sampleprj.dao.ClienteDAO;
import edu.eci.pdsw.sampleprj.dao.ItemDAO;
import edu.eci.pdsw.sampleprj.dao.TipoItemDAO;
import edu.eci.pdsw.samples.entities.Cliente;
import edu.eci.pdsw.samples.entities.Item;
import edu.eci.pdsw.samples.entities.ItemRentado;
import edu.eci.pdsw.samples.entities.TipoItem;
import edu.eci.pdsw.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.pdsw.samples.services.ServiciosAlquiler;

public class ServiciosAlquilerImpl implements ServiciosAlquiler {
	@Inject 
	private ClienteDAO clienteDAO;
	@Inject 
	private ItemDAO itemDAO;
	@Inject 
	private TipoItemDAO tipoItemDAO;
	
	@Override
	public long valorMultaRetrasoxDia(int itemId) {		
		Item item= itemDAO.load(itemId);
		return (long)item.getTarifaxDia();		 
	}

	@Override
	public Cliente consultarCliente(long docu) throws ExcepcionServiciosAlquiler {
		try {
			return clienteDAO.load((int) docu);
		} catch (PersistenceException e1) {
			throw new ExcepcionServiciosAlquiler(e1.getMessage());
		}		
	}
	

	@Override
	public List<ItemRentado> consultarItemsCliente(long idcliente) throws ExcepcionServiciosAlquiler {
		try {
			return clienteDAO.load(idcliente).getRentados();
		} catch (PersistenceException e1) {
			throw new ExcepcionServiciosAlquiler(e1.getMessage());
		}			
	}
	
	@Override
	public List<Item> consultarItemsClienteSinDevoler(long idcliente) throws ExcepcionServiciosAlquiler {
		try {
			return itemDAO.loadItemsRentadosCliente(idcliente);
		} catch (PersistenceException e1) {
			throw new ExcepcionServiciosAlquiler(e1.getMessage());
		}			
	}
	
	@Override
	public List<Cliente> consultarClientes() throws ExcepcionServiciosAlquiler {
		try {
			return clienteDAO.loadClientes();
		} catch (PersistenceException e1) {
			throw new ExcepcionServiciosAlquiler(e1.getMessage());
		}		
	}

	@Override
	public Item consultarItem(int id) throws ExcepcionServiciosAlquiler {
		try {
			return itemDAO.load(id);
		} catch (PersistenceException e1) {
			throw new ExcepcionServiciosAlquiler(e1.getMessage());
		}
	}

	@Override
	public List<Item> consultarItemsDisponibles() throws ExcepcionServiciosAlquiler{
		try {
			return itemDAO.loadItemsDisponibles();
		} catch (PersistenceException e1) {
			throw new ExcepcionServiciosAlquiler(e1.getMessage());
		}	
	}

	@Override
	public long consultarMultaAlquiler(int iditem, Date fechaDevolucion) throws ExcepcionServiciosAlquiler {	
		try {
			return itemDAO.loadMultaItemAlquilado(iditem, fechaDevolucion);
		} catch (PersistenceException e1) {
			throw new ExcepcionServiciosAlquiler(e1.getMessage());
		}
	}

	@Override
	public TipoItem consultarTipoItem(int id) throws ExcepcionServiciosAlquiler {	
		try {
			return tipoItemDAO.loadTipoItem(id);
		} catch (PersistenceException e1) {
			throw new ExcepcionServiciosAlquiler(e1.getMessage());
		}
		
	}

	@Override
	public List<TipoItem> consultarTiposItem() throws ExcepcionServiciosAlquiler {	
		try {
			return tipoItemDAO.loadTiposItem();
		} catch (PersistenceException e1) {
			throw new ExcepcionServiciosAlquiler(e1.getMessage());
		}		
	}

	@Override
	public void registrarAlquilerCliente(Date date, long docu, Item item, int numdias) throws ExcepcionServiciosAlquiler {	
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date); 
			calendar.add(Calendar.DAY_OF_YEAR, numdias);  
			clienteDAO.saveAlquilerItem(new ItemRentado(0, item, date, (Date)calendar.getTime()), docu);
		} catch (PersistenceException e1) {
			throw new ExcepcionServiciosAlquiler(e1.getMessage());
		}			
	}

	@Override
	public void registrarCliente(Cliente p) throws ExcepcionServiciosAlquiler {
		try {
			clienteDAO.save(p);
		} catch (PersistenceException e1) {
			throw new ExcepcionServiciosAlquiler(e1.getMessage());
		}		
	}

	@Override
	public long consultarCostoAlquiler(int iditem, int numdias) throws ExcepcionServiciosAlquiler {	
		try {
			return valorMultaRetrasoxDia(iditem)*numdias;	
		} catch (PersistenceException e1) {
			throw new ExcepcionServiciosAlquiler(e1.getMessage());
		}			
	}

	@Override
	public void actualizarTarifaItem(int id, long tarifa) throws ExcepcionServiciosAlquiler {
		try {
			itemDAO.saveTarifaItem(id, tarifa);
		} catch (PersistenceException e1) {
			throw new ExcepcionServiciosAlquiler(e1.getMessage());
		}		
	}

	@Override
	public void registrarItem(Item i) throws ExcepcionServiciosAlquiler {
		try {
			itemDAO.save(i);
		} catch (PersistenceException e1) {
			throw new ExcepcionServiciosAlquiler(e1.getMessage());
		}			
	}

	@Override
	public void vetarCliente(long docu, boolean estado) throws ExcepcionServiciosAlquiler {
		try {
			clienteDAO.saveEstadoCliente(docu, estado);
		} catch (PersistenceException e1) {
			throw new ExcepcionServiciosAlquiler(e1.getMessage());
		}			
	}

}
