package dao;

import java.util.HashMap;
import java.util.Map;
import javax.persistence.FetchType;
import javax.persistence.TypedQuery;
import entidades.Cliente;
import jpaUtil.JpaUtil;

/**
 * 
 * @author pedro.silva
 *
 */
public class ClienteRepositorio extends BasicoAbstratoRepositorio<Cliente, Integer> {

	// Construtor que seta o tipo de classe no Dao Abstrato, podendo agora fazer a
	// busca no metodo listar Todos
	public ClienteRepositorio() {
		setarEntidade(Cliente.class);
	}

	/**
	 * Metodo especifico do cliente para fazer a busca
	 * 
	 * @param cliente
	 * @param b       True para fechar a conexao do manager, false para deixar
	 *                aberto
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
	 * Metodo que retorna o cliente a partir do email e senha fornecia na tela de login
	 * @param cliente
	 * @param b True para fechar conexao e false para nao
	 * @return Cliente
	 */
	public Cliente login(Cliente cliente, boolean b) {
		// Pegando instancia do manager
		if (em == null || !em.isOpen())
			em = JpaUtil.getEntityManager();
		
		String query = "select c from Cliente c where email like '%" + cliente.getEmail() + "%' and senha like '%" + cliente.getSenha()+"%'";
		
		em.getTransaction().begin();
		TypedQuery<Cliente> typedQuery = em.createQuery(query, Cliente.class);
		cliente = typedQuery.getSingleResult();
		if (b)
			em.close();
		return cliente;
	}

	/**
	 * Metodo que faz a deleção do cliente da base de dados
	 * 
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
