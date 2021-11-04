package jp.co.sample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Employee;
import jp.co.sample.service.EmployeeService;

/**
 * Employeeドメインに関わる処理を実施するためのコントローラークラスです。<br>
 * 
 * @author cyjoh
 *
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService service ;
	
	/**
	 * すべての従業員情報をサービスクラスからもらってきて、それをリクエストスコープに格納するメソッドです。<br>
	 * @param model　リクエストスコープ
	 * @return 従業員情報を表示するためのページを返します。
	 */
	@RequestMapping("/showList")
	public String showList(Model model) {
		List<Employee> employeeList = service.showList();
		model.addAttribute("employeeList", employeeList);
		return "employee/list";
	}
}