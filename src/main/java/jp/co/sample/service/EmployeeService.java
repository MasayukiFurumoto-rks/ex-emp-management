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
 * 
 */
@Service
@Transactional
public class EmployeeService {

	@Autowired
	private EmployeeRepository repository;

	/**
	 * 従業員情報を全件取得するメソッド。<br>
	 * 
	 * @return 従業員情報を全件取得したリスト。
	 */
	public List<Employee> showList() {
		return repository.findAll();
	}

	/**
	 * 従業員IDで指定された従業員の情報を返すメソッド。
	 * 
	 * @param id 外部で指定された従業員ID
	 * @return 指定された従業員IDに対応する従業員情報を返します。
	 */
	public Employee showDetail(Integer id) {
		return repository.load(id);
	}

	/**
	 * 従業員情報を更新するメソッド。<br>
	 * 
	 * @param employee 外部で指定された従業員情報
	 */
	public void update(Employee employee) {
		repository.update(employee);
	}
}
