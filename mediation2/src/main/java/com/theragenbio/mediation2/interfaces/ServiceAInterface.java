package com.theragenbio.mediation2.interfaces;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import lombok.extern.slf4j.Slf4j;

@FeignClient(name = "serviceA", fallbackFactory = ServiceAFeignClientFallbackFactory.class)
public interface ServiceAInterface {
	@GetMapping("/user/")
	String getAllUsers() throws Exception;

	@GetMapping("/user/{userId}")
	String getUserWithUserId(@PathVariable("userId") int userId) throws Exception;
}

@Slf4j
@Component
class ServiceAFeignClientFallbackFactory implements FallbackFactory<ServiceAInterface> {
	@Override
	public ServiceAInterface create(Throwable t) {
		return new ServiceAInterface() {

			@Override
			public String getAllUsers() throws Exception {
				t.printStackTrace();
				return null;
			}

			@Override
			public String getUserWithUserId(int userId) throws Exception {
				t.printStackTrace();
				return null;
			}
		};
	}
}