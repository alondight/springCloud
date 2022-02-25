package com.theragenbio.serviceB.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.theragenbio.serviceB.entity.Department;
import com.theragenbio.serviceB.service.DepartmentService;

@RestController
@RequestMapping("/dept")
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;

	@PostMapping(path = "/", produces="application/json")
	public Department saveDepartment(@RequestBody Department department)
	{
		return departmentService.saveDepartment(department);
	}

	@GetMapping(path = "/", produces="application/json")
	public List<Department> getAllDepartments()
	{
		return departmentService.getAllDepartments();
	}

	@GetMapping(path = "/{deptId}", produces="application/json")
	public Department findByDepartmentId(@PathVariable(name = "deptId") Long deptId)
	{
		return departmentService.findByDepartmentId(deptId);
	}
}
