package com.theragenbio.serviceB.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.theragenbio.serviceB.entity.Department;
import com.theragenbio.serviceB.repository.DepartmentRepository;

@Service
public class DepartmentService {

	@Autowired
	private DepartmentRepository departmentRepository;


	public Department saveDepartment(Department department)
	{
		return departmentRepository.save(department);
	}

	public List<Department> getAllDepartments()
	{
		return departmentRepository.findAll();
	}

	public Department findByDepartmentId(Long deptId) {
		return departmentRepository.findByDepartmentId(deptId);
	}

}
