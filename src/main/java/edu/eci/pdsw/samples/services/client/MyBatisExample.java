/*
 * Copyright (C) 2015 hcadavid
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package edu.eci.pdsw.samples.services.client;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Date;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import edu.eci.pdsw.sampleprj.dao.mybatis.mappers.ClienteMapper;
import edu.eci.pdsw.sampleprj.dao.mybatis.mappers.ItemMapper;
import edu.eci.pdsw.sampleprj.dao.mybatis.mappers.TipoItemMapper;
import edu.eci.pdsw.samples.entities.*;
import edu.eci.pdsw.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.pdsw.samples.services.ServiciosAlquiler;
import edu.eci.pdsw.samples.services.ServiciosAlquilerFactory;
/**
 * @author hcadavid
 */
public class MyBatisExample {

    /**
     * Método que construye una fábrica de sesiones de MyBatis a partir del
     * archivo de configuración ubicado en src/main/resources
     *
     * @return instancia de SQLSessionFactory
     */
    public static SqlSessionFactory getSqlSessionFactory() {
        SqlSessionFactory sqlSessionFactory = null;
        if (sqlSessionFactory == null) {
            InputStream inputStream;
            try {
                inputStream = Resources.getResourceAsStream("mybatis-config.xml");
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            } catch (IOException e) {
                throw new RuntimeException(e.getCause());
            }
        }return sqlSessionFactory;
    }

    /**
     * Programa principal de ejempo de uso de MyBATIS
     * @param args
     * @throws SQLException 
     */
    public static void main(String args[]) throws SQLException {
        SqlSessionFactory sessionfact = getSqlSessionFactory();
        SqlSession sqlss = sessionfact.openSession(); 
        
        /*CLIENTE*/        
        //Crear el mapper y usarlo: 
        ClienteMapper cm=sqlss.getMapper(ClienteMapper.class);
        //System.out.println("Consultar clientes: \n"+cm.consultarClientes()); //consultar clientes              
        //System.out.println("Consultar cliente dado su documento: \n"+cm.consultarCliente(1)); //Consultar cliente(id)                
        //Insertar item rentado
        Date fechainicio = new Date(2018,9,10);        
        java.util. Date fechafin = new Date(2018,10,10); 
        //cm.agregarItemRentadoACliente(1026588472, 212, fechainicio, fechafin);              
        //System.out.println("Consultar item rentado con documento del cliente y id del item: \n"+cm.consultarItemRentado(1026588472, 212)); //Consultar item rentado
        //Insertar cliente
        //cm.insertarCliente(new Cliente("Miguel", 1030697423, "2648787", "Calle 9 A sur", "miguel_castellano@hotmail.com"));
        
        /*ITEM*/
        //Crear el mapper y usarlo: 
        ItemMapper im = sqlss.getMapper(ItemMapper.class);
        //Insertar item
        Date date = new Date(14,6,17);
        Item item = new Item(new TipoItem(2, "juego"), 17, "Fireboy and Watergirl", "Fireboy and Watergirl es un juego en donde los personajes"
        		+ "deben trabajar juntos para viajar a través de plataformas", date, 0, "plataformas", "2");
        //im.insertarItem(item);        
        //System.out.println("Consultar item agregado dado su id: \n"+im.consultarItem(17)); //Consultar item rentado(id)
        //System.out.println("Consultar todos los items: \n"+ im.consultarItems());	//Consultar items
        
        
        //System.out.println("Items Disponibles: \n"+im.consultarItemsDisponibles()); 
        //System.out.println("Multa de item alquilado: \n"+im.consultarMultaAlquiler(3030, new Date("2018/10/10")));
        
        /*TIPO ITEM*/
        TipoItemMapper tim = sqlss.getMapper(TipoItemMapper.class);
        //System.out.println("Tipo de Item: \n"+tim.getTipoItem(1));
        //System.out.println("Tipos de Item: \n"+tim.getTiposItems());
        //im.actualizarTarifaItem(3030, 2000);
        //cm.actualizarEstadoDeCliente(2133541, true);     
        sqlss.commit();      
        sqlss.close();
        
        ServiciosAlquilerFactory serviciosAlquilerFactory = ServiciosAlquilerFactory.getInstance();
        ServiciosAlquiler serviciosAlquiler= serviciosAlquilerFactory.getServiciosAlquiler();
        
        try {
			System.out.println(serviciosAlquiler.consultarCliente(1030697423));
		} catch (ExcepcionServiciosAlquiler e) {			
			System.out.println(e);
		}
        
    }


}
