package br.com.casadocodigo.loja.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.com.casadocodigo.loja.models.Produto;

@Repository
@Transactional
public class ProdutoDAO {
	
	@PersistenceContext
	private EntityManager manager;
	
	public void gravar(Produto produto){
		manager.persist(produto);
	}

	public List<Produto> listar() {		
		return manager.createQuery("Select p from Produto p", Produto.class)
				.getResultList();		
	}

	public Produto find(int id) {
		
		String jpql = "select distinct(p) from Produto p left join fetch p.precos precos where p.id = :pProduto";		
		
		TypedQuery<Produto> query = manager.createQuery(jpql, Produto.class);
		query.setParameter("pProduto", id);
		
		return query.getSingleResult();
	}

}
