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
		if(em==null || !em.isOpen())
			em = JpaUtil.getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(entity);
			em.getTransaction().commit();
			if (b)
				em.close();
		} catch (Exception e) {
			em.getTransaction().rollback();
		}
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
		try {
			em.getTransaction().begin();
			em.merge(entity);
			em.getTransaction().commit();
			if (b)
				em.close();			
		} catch (Exception e) {
			em.getTransaction().rollback();
		}
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
		if(em==null || !em.isOpen())
			em = JpaUtil.getEntityManager();		
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
		if(em==null || !em.isOpen())
			em = JpaUtil.getEntityManager();
		try {
			T entidade = em.find(entity, id);
			em.getTransaction().begin();
			em.remove(entidade);
			em.getTransaction().commit();
			if(b)
				em.close();
		} catch (Exception e) {
			em.getTransaction().rollback();
		}
		
	}
}
