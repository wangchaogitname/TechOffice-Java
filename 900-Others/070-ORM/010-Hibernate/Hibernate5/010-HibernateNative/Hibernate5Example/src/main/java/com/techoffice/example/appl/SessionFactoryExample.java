package com.techoffice.example.appl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.techoffice.example.model.TestEntity;

/*
 * SessionFactory
 * The SessionFactory is a thread-safe object that is instantiated once to serve the entire application.
 * 
 * The SessionFactory acts as a factory for org.hibernate.Session instances, 
 * which should be consoidered as a "unit of work".\
 * 
 * 
 */

public class SessionFactoryExample {
	public static void main(String[] args){


		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
		        .configure( "hibernate.cfg.xml" )
		        .build();
		
		try {
			SessionFactory sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();

			Session session = sessionFactory.openSession();
			
			session.beginTransaction();
			TestEntity testEntity = new TestEntity();
			testEntity.setName("Test Entity name 1");
			testEntity.setDesc("Test Entity desc 1");
			session.save(testEntity);
			session.getTransaction().commit();
			session.close();
			session = sessionFactory.openSession();
			session.beginTransaction();
			List<TestEntity> result = session.createQuery( "from TestEntity" ).list();
			for ( TestEntity event : result ) {
			    System.out.println( "TestEntity (" + event.getName() + ") : " + event.getDesc() );
			}
			session.getTransaction().commit();
			session.close();			

		}catch (Exception e) {
			// The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
			// so destroy it manually.
			StandardServiceRegistryBuilder.destroy( registry );
			e.printStackTrace();
		}
	}
}
