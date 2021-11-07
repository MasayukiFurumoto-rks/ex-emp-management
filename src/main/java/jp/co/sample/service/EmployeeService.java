package jp.co.sample.service;

import java.util.ArrayList;
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
	 * 従業員情報を10件ごとに全件取得するメソッド。<br>
	 * 
	 * @return 従業員情報を全件取得したリスト。
	 */
	public List<List<Employee>> showListAsPage() {
		
		List<List<Employee>> employeePageList = new ArrayList<>();
		List<Employee> employeeAllList = repository.findAll();
		List<Employee> employeePage = new ArrayList<>();
		
		for(int i = 0 ; i < employeeAllList.size() ; i ++ ) {
			// ①リストの0,10・・・番目（ページの先頭）のとき、ページリストを作る
			if(i % 10 == 0) {
				employeePage = new ArrayList<>();
			}
			// ②ページに追加に入れていく
			employeePage.add(employeeAllList.get(i));

			// ③ページに10番目の従業員が入ったか、最後の従業員だった時にページをページリストに追加
			if(i % 10 == 9 || i == employeeAllList.size()-1) {
				employeePageList.add(employeePage);
			}
			
		}

		// ③そのリストからpageNum-1でgetしてリターンする
		
		return employeePageList;
		
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
