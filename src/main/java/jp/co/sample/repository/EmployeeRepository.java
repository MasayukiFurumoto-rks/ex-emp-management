package jp.co.sample.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Employee;

/**
 * employeesテーブルとデータをやり取りするレポジトリクラス<br>
 * @author cyjoh
 *
 */
@Repository
public class EmployeeRepository {

	private static final RowMapper<Employee> EMPLOYEE_ROW_MAPPER = new BeanPropertyRowMapper<>(Employee.class);
	private static final String TABLE_NAME = "employees";

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * 従業員一覧情報を入社日の降順で取得するメソッド<br>
	 * （従業員が存在しない場合はサイズ0件の従業員一覧を返します。）
	 * @return List<Employee> 従業員一覧情報
	 */
	public List<Employee> findAll() {
		String findAllSql = "SELECT * FROM " + TABLE_NAME + " ORDER BY hire_date desc;";
		List<Employee> employeeList = template.query(findAllSql, EMPLOYEE_ROW_MAPPER);

		// if(employeeList.size()==0) {
		//　return ;
		//　}
		return employeeList;
	}
	
	
	/**
	 * 主キーから従業員情報を取得するメソッド<br>
	 * （従業員が存在しない場合はSpringが自動的に例外を発生します。）
	 * @param id　外部で指定されたID(主キー)
	 * @return employee 該当する従業員情報
 	 */
	public Employee load(Integer id) {
		String loadSql = "SELECT * FROM " + TABLE_NAME + " WHERE id = :id ;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		Employee employee = template.queryForObject(loadSql,param, EMPLOYEE_ROW_MAPPER);
		return employee;
	}

	/**
	 * 外部で指定された従業員情報をもとに、そのIDを持つ従業員情報を指定された内容に変更するメソッド<br>
	 * 
	 * @param employee 外部で作成された従業員情報（Employeeドメイン）
	 */
	public void update(Employee employee) {
		String updateSql = "UPDATE " + TABLE_NAME + " SET "
				+ "name = :name, "
				+ "image = :image, "
				+ "gender = :gender, "
				+ "hire_date = :hireDate, "
				+ "mail_address = :mailAddress, "
				+ "zip_code = :zipCode, "
				+ "address = :address, "
				+ "telephone = :telephone, "
				+ "salary = :salary, "
				+ "characteristics = :characteristics, "
				+ "dependents_count = :dependentsCount "
				+ "WHERE id = :id ;";
		SqlParameterSource param = new BeanPropertySqlParameterSource(employee);
		
		 template.update(updateSql,param);
	}

}
