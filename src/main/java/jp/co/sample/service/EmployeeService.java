package jp.co.sample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sample.domain.Employee;
import jp.co.sample.repository.EmployeeRepository;

/**
 * Employeeドメインに関わる業務処理を行うためのサービスクラス。<br>
 * 
 * @author cyjoh
 *　
 */
@Service
@Transactional
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository repository;
	
	/**
	 * 従業員情報を全件取得するメソッド。<br>
	 * @return 従業員情報を全件取得したリスト。
	 */
	public List<Employee> showList(){
		return repository.findAll();
	}
	
}
