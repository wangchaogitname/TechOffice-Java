package com.techoffice.example;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.techoffice.example.model.Student;

public class Appl {
	public static void main(String[] args){
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory( "com.techoffice.example.hibernatejpa" );
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();

		Student student = new Student();
		student.setStudentName("Test 1");
		
		entityManager.persist(student);
		
		List<Student> results = entityManager.createNamedQuery("Student.getStudent", Student.class).getResultList();
		for (Student result: results){
			System.out.println(result.getStudentName());
		}
		entityManager.getTransaction().commit();
		entityManager.close();
		
		// close the factory and release any resource it holds.
		entityManagerFactory.close();
	}
}
