package com.techoffice.aastock.stock.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.techoffice.aastock.stock.model.Industry;

@Repository
public class IndustryDao {
	
	@PersistenceContext
	private EntityManager em;
	
	@Transactional
	public List<Industry> list(){
		TypedQuery<Industry> query = em.createQuery("from Industry", Industry.class);
		return query.getResultList();
	}
	
	@Transactional
	public void add(List<Industry> industries){
		for (Industry industry: industries){
			em.persist(industry);
		}
	}
	
	@Transactional
	public void clear(){
		Query query = em.createQuery("DELETE FROM Industry");
		query.executeUpdate();
	}
}
