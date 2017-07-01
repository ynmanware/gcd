package com.ynm.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.ynm.crud.Employee;

import com.ynm.model.Parameters;

/**
 * @author Yogesh.Manware
 *
 */
@Repository
public class GCDRepositoryImpl implements GCDRepository {

	protected EntityManager entityManager;

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public void persistParameters(Parameters params) {
		Query q = entityManager.createQuery("select t from Employee t");

		List<Employee> employeeList = q.getResultList();

		if (employeeList.isEmpty()) {
			return;
		}

		for (Employee employee : employeeList) {
			System.out.println("employee Id: " + employee.getId());
		}
	}

	@Override
	public List<Parameters> getAllParameters(String key) {
		Query q = entityManager.createQuery("select t from Employee t");

		List<Employee> employeeList = q.getResultList();

		if (employeeList.isEmpty()) {
			
		}

		for (Employee employee : employeeList) {
			System.out.println("employee Id: " + employee.getId());
		}
		return new ArrayList<Parameters>();
	}
}
