package jp.co.sample.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.sample.domain.Employee;
import jp.co.sample.form.UpdateEmployeeForm;
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
	private EmployeeService service;

	@Autowired
	private HttpSession session;

	@ModelAttribute
	public UpdateEmployeeForm setUpUpdateEmployeeForm() {
		return new UpdateEmployeeForm();
	}

	/**
	 * すべての従業員情報をサービスクラスからもらってきて、それをリクエストスコープに格納するメソッドです。<br>
	 * 
	 * @param model リクエストスコープ
	 * @return 従業員情報を表示するためのページを返します。
	 */
	@RequestMapping("/showList")
	public String showList(Model model, RedirectAttributes redirectAttributes) {
		if (session.getAttribute("administratorName") == null) {
			redirectAttributes.addFlashAttribute("needsLogin", "ログインしてください。");
			return "redirect:/";
		}

		List<Employee> employeeList = service.showList();
		model.addAttribute("employeeList", employeeList);
		return "employee/list";
	}

	/**
	 * すべての従業員情報をサービスクラスからもらってきて、それをリクエストスコープに格納するメソッドです。<br>
	 * 
	 * @param model リクエストスコープ
	 * @return 従業員情報を表示するためのページを返します。
	 */
	@RequestMapping("/showListAsPage")
	public String showListAsPage(Integer page,Model model, RedirectAttributes redirectAttributes) {
		if (session.getAttribute("administratorName") == null) {
			redirectAttributes.addFlashAttribute("needsLogin", "ログインしてください。");
			return "redirect:/";
		}
		
		if (page == null) {
			page = 1;
		}
		
		List<List<Employee>> employeePageList = service.showListAsPage();
		model.addAttribute("employeePageList", employeePageList);
		
		List<Employee> employeePage = employeePageList.get(page-1);
		model.addAttribute("employeePage", employeePage);
		return "employee/list-as-page";
	}
	
	
	
	/**
	 * 特定の従業員情報をサービスクラスからもらってきて、それをリクエストスコープに格納、その後表示画面へ遷移させるメソッドです。<br>
	 * 
	 * @param id    主キー
	 * @param model リクエストスコープ
	 * @return 従業員の詳細情報を表示するためのページを返します。
	 */
	@RequestMapping("/showDetail")
	public String showDetail(String id, Model model, RedirectAttributes redirectAttributes) {
		if (session.getAttribute("administratorName") == null) {
			redirectAttributes.addFlashAttribute("needsLogin", "ログインしてください。");
			return "redirect:/";
		}

		Employee employee = service.showDetail(Integer.parseInt(id));
		model.addAttribute("employee", employee);
		return "employee/detail";
	}

	/**
	 * 従業員情報を更新するためのメソッド。
	 * 
	 * @param form 従業員情報更新用の画面から送られてきた情報
	 * @return 従業員一覧の画面にリダイレクトさせるURL
	 */
	@RequestMapping("/update")
	public String update(@Validated UpdateEmployeeForm form, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {

		if (session.getAttribute("administratorName") == null) {
			redirectAttributes.addFlashAttribute("needsLogin", "ログインしてください。");
			return "redirect:/";
		}

		if (result.hasErrors()) {
			return showDetail(form.getId(), model, redirectAttributes);
		}

		Employee employee = new Employee();
		// formから送られてきたidをもとに、データベースから従業員情報を取得
		employee = service.showDetail(Integer.parseInt(form.getId()));

		// formから送られてきた扶養人数を、先程取得した従業員情報に上書き
		employee.setDependentsCount(Integer.parseInt(form.getDependentsCount()));

		// その情報をデータベースに反映
		service.update(employee);

		return "redirect:/employee/showList";
	}
}
