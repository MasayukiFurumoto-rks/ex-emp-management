package jp.co.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Administrator;
import jp.co.sample.repository.AdministratorRepository;

@Controller
@RequestMapping("/test")
public class TestController {
	
	@Autowired
	private AdministratorRepository repository;
	
	@RequestMapping("/admin-repository-insert")
	public String insert () {
		Administrator administrator = new Administrator();
		administrator.setName("古本昌幸");
		administrator.setMailAddress("masayuki@rakus-p.co.jp");
		administrator.setPassword("masayuki");
		repository.insert(administrator);
		
		return "/administrator/test-finished";
	}
}
