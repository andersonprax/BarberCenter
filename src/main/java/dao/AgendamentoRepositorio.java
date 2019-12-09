package dao;

import java.util.List;

import javax.persistence.TypedQuery;

import entidades.Agendamento;
import entidades.Barbearia;
import jpaUtil.JpaUtil;

public class AgendamentoRepositorio extends BasicoAbstratoRepositorio<Agendamento, Integer>{
	public List<Agendamento> consultarPorCliente(String cpf){
		List<Agendamento> resultSet;
		try {
			String querySelector = "select a from Agendamento a where cliente_cpf = " + "'"+ cpf + "'";
			TypedQuery<Agendamento> typedQuery = JpaUtil.getEntityManager().createQuery(querySelector, Agendamento.class);
			resultSet = typedQuery.getResultList();
			return resultSet;
		}catch (Exception e) {
			e.getStackTrace();
		}
		return null;
	}
}
