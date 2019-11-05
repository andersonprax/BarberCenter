package dao;

import javax.persistence.EntityManager;
import jpaUtil.JpaUtil;

public abstract class BasicoAbstratoRepositorio<T, I> implements IBasicoRepositorio<T, I> {

	EntityManager em = JpaUtil.getEntityManager();

	/**
	 * Metodo generico para salvar Entidades
	 * 
	 * @param entity
	 * @param b      True, para fechar a transação do managerFactory, false para
	 *               deixar aberto e fazer mais alterações
	 * @return Objeto
	 */
	public T salvar(T entity, boolean b) {

		em.getTransaction().begin();
		em.persist(entity);
		em.getTransaction().commit();
		if (b)
			em.close();
		return entity;
	}

	/**
	 * Metodo generico para salvar/atualizar Entidades
	 * 
	 * @param entity
	 * @param b      True, para fechar a transação do managerFactory, false para
	 *               deixar aberto e fazer mais alterações
	 * @return Objeto
	 */
	public T atualizar(T entity, boolean b) {
		if(em==null || !em.isOpen())
			em = JpaUtil.getEntityManager();
		em.getTransaction().begin();
		em.merge(entity);
		em.getTransaction().commit();
		if (b)
			em.close();
		
		return entity;
	}

	/**
	 * 
	 * @param entity Passa a Classe do tipo do objeto
	 * @param id     Int com o id do objeto.
	 * @param b      True, para fechar a transação do managerFactory, false para
	 *               deixar aberto e fazer mais alterações
	 * @return Objeto
	 */
	public T encontrar(Class<T> entity, I id, boolean b) {
		return em.find(entity, id);
	}

	/**
	 * @param entity Passa a Classe do tipo do objeto
	 * @param id     Int com o id do objeto.
	 * @param b      True, para fechar a transação do managerFactory, false para
	 *               deixar aberto e fazer mais alterações
	 * @return Objeto
	 */
	public void remover(Class<T> entity, I id, boolean b) {
		T entidade = em.find(entity, id);
		em.getTransaction().begin();
		em.remove(entidade);
		em.getTransaction().commit();
	}
}
