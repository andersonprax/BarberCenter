package dao;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import entidades.Barbearia;
import entidades.Servicos;
import jpaUtil.JpaUtil;

public class ServicoRepositorio extends BasicoAbstratoRepositorio<Servicos, Integer>{
	
	public List<Servicos> consultarServicosPorBarbearia(Integer idBarbearia){
		List<Servicos> resultSet;
		try {
			String querySelector = "select s from Servicos s where barbearia_id = "+idBarbearia;
			TypedQuery<Servicos> typedQuery = JpaUtil.getEntityManager().createQuery(querySelector, Servicos.class);
			resultSet = typedQuery.getResultList();
			return resultSet;
		}catch (Exception e) {
			e.getStackTrace();
		}
		return null;
	}

}
