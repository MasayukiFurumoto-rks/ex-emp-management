package jp.co.sample.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import jp.co.sample.domain.Administrator;
import jp.co.sample.form.InsertAdministratorForm;
import jp.co.sample.service.AdministratorSecvice;

/**
 * 管理者アカウントに関する機能を担当するコントローラークラスです。<br>
 * 
 * @author cyjoh
 */
@Controller
@RequestMapping("/")
public class AdministratorController {

	@Autowired
	private AdministratorSecvice service;

	/**
	 * 管理者を追加する用の入力画面に使うフォームクラスをインスタンス化してリクエストスコープに格納するメソッドです。<br>
	 * 
	 * @return インスタンス化したInsertAdministratorForm
	 */
	@ModelAttribute
	public InsertAdministratorForm setUpForm() {
		return new InsertAdministratorForm();
	}

	/**
	 * 管理者を追加する画面を表示するためのメソッドです。<br>
	 * 
	 * @return 管理者アカウント追加用入力画面を返します。
	 */
	@RequestMapping("/toInsert")
	public String toInsert() {
		return "administrator/insert";
	}

	/**
	 * 管理者を追加する指示を行うメソッドです。<br>
	 * 
	 * @param form 管理者登録の情報入力用画面から、InsertAdministratorFormが受け取った内容です。
	 * @return　ログイン画面を表示するためのURLを返します。
	 */
	@RequestMapping("/insert")
	public String insert(InsertAdministratorForm form) {
		Administrator administrator = new Administrator();
		BeanUtils.copyProperties(form, administrator);
		service.insert(administrator);
		return "redirect:/";
	}

}
