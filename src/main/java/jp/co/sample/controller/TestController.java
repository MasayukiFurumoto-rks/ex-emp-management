package jp.co.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Administrator;
import jp.co.sample.domain.Employee;
import jp.co.sample.repository.AdministratorRepository;
import jp.co.sample.repository.EmployeeRepository;

@Controller
@RequestMapping("/test")
public class TestController {

	@Autowired
	private AdministratorRepository adminRepository;

	@Autowired
	private EmployeeRepository empRepository;

	@RequestMapping("/admin-repository-insert")
	public String adminRepositoryInsert() {
		Administrator administrator = new Administrator();
		administrator.setName("古本昌幸");
		administrator.setMailAddress("masayuki@rakus-p.co.jp");
		administrator.setPassword("masayuki");
		adminRepository.insert(administrator);

		return "/administrator/test-finished";
	}

	@RequestMapping("/admin-repository-find")
	public String adminRepositoryFind() {
		String mailAddress = "masayuki@rakus-p.co.jp";
		String password = "masayuki";

		Administrator admin = adminRepository.findByMailAddressAndPassword(mailAddress, password);
			System.out.println(admin.getName());

		return "/administrator/test-finished";
	}

	@RequestMapping("/emp-repository-find-all")
	public String empRepositoryFindAll() {
		for (Employee emp : empRepository.findAll()) {
			System.out.println(emp.getName());
		}

		return "/administrator/test-finished";
	}

	@RequestMapping("/emp-repository-load")
	public String empRepositoryLoad() {
		Employee emp = empRepository.load(1);
		System.out.println(emp.getName());

		return "/administrator/test-finished";
	}

	@RequestMapping("/emp-repository-update")
	public String empRepositoryUpdate() {
		Employee emp = empRepository.load(1);
		System.out.println(emp.getName());
		emp.setName("山田太郎");
		empRepository.update(emp);

		System.out.println(emp.getName());
		return "/administrator/test-finished";
	}
}
