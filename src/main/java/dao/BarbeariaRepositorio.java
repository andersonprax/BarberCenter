package dao;

import java.util.List;

import javax.persistence.TypedQuery;

import entidades.Barbearia;
import jpaUtil.JpaUtil;

public class BarbeariaRepositorio extends BasicoAbstratoRepositorio<Barbearia, Integer>{
	
	public List<Barbearia> consultarTodasBarbearias(){
		List<Barbearia> resultSet;
		try {
			String querySelector = "select b from Barbearia b";
			TypedQuery<Barbearia> typedQuery = JpaUtil.getEntityManager().createQuery(querySelector, Barbearia.class);
			resultSet = typedQuery.getResultList();
			return resultSet;
		}catch (Exception e) {
			e.getStackTrace();
		}
		return null;
	}

}