package dao;

import java.util.HashMap;
import java.util.Map;
import javax.persistence.FetchType;

import entidades.Cliente;
import jpaUtil.JpaUtil;

public class ClienteRepositorio extends BasicoAbstratoRepositorio<Cliente, Integer> {

	/**
	 * Metodo especifico do cliente para fazer a busca
	 * @param cliente
	 * @param b True para fechar a conexao do manager, false para deixar aberto
	 * @return
	 */
	public Cliente buscarCliente(Cliente cliente, boolean b) {

		// Pegando instancia do manager
		if (em == null || !em.isOpen())
			em = JpaUtil.getEntityManager();

		// Usando map para passar o tipo busca apressada
		Map<String, Object> map = new HashMap<String, Object>();
		// Aqui seto fetchType eager (Rapido) 
		map.put("fetch =", FetchType.EAGER);
		em.getTransaction().begin();
		// buscando o objeto na base
		cliente = em.find(Cliente.class, cliente.getCpf(), map);
		if (b)
			em.close();
		// retornando objeto
		return cliente;
	}
	
	/**
	 * Metodo que faz a deleção do cliente da base de dados
	 * @param cliente
	 */
	public void removerCliente(Cliente cliente) {
		// Pegando instancia do manager
		if (em == null || !em.isOpen())
			em = JpaUtil.getEntityManager();
		try {
			em.getTransaction().begin();
			em.remove(em.find(Cliente.class, cliente.getPrimaryKey()));			
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
		}		
	}
}
