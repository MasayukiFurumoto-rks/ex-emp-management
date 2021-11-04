package jp.co.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.form.InsertAdministratorForm;
import jp.co.sample.service.AdministratorSecvice;

/**
 * @author cyjoh
 * 管理者アカウントに関する機能を担当するコントローラークラスです。<br>
 */
@Controller
@RequestMapping("/")
public class AdministratorController {

	@Autowired
	private AdministratorSecvice service;

	/**
	 * 管理者を追加する用の入力画面に使うフォームクラスをインスタンス化してリクエストスコープに格納するメソッドです。
	 * 
	 * @return インスタンス化したInsertAdministratorForm
	 */
	@ModelAttribute
	public InsertAdministratorForm setUpForm() {
		return new InsertAdministratorForm();
	}

	/**
	 * 管理者を追加する画面を表示するためのメソッドです。
	 * @return　管理者アカウント追加用入力画面を返します。
	 */
	@RequestMapping("/toInsert")
	public String toInsert() {
		return "administrator/insert";
	}

}


