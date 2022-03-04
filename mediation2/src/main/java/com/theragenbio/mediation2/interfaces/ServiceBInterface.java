package com.theragenbio.mediation2.interfaces;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "serviceB")
public interface ServiceBInterface {
	@GetMapping("/dept/")
	String getAllDepartments() throws Exception;

	@GetMapping("/dept/{deptId}")
	String findByDepartmentId(@PathVariable("deptId") int userId) throws Exception;
}
