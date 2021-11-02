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
	public String adminRepositoryInsert () {
		Administrator administrator = new Administrator();
		administrator.setName("古本昌幸");
		administrator.setMailAddress("masayuki@rakus-p.co.jp");
		administrator.setPassword("masayuki");
		repository.insert(administrator);
		
		return "/administrator/test-finished";
	}
	
	@RequestMapping("/admin-repository-find")
	public String adminRepositoryFind () {
		String mailAddress = "masayuki@rakus-p.co.jp";
		String password = "masayuki" ;
		
		for(Administrator admin:repository.findByMailAddressAndPassword(mailAddress, password)) {
			System.out.println(admin.getName());
		}
		
		
		return "/administrator/test-finished";
	}
}
