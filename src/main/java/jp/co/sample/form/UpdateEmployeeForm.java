package jp.co.sample.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * 従業員情報を更新するための情報を受け取るためのフォームクラスです。<br>
 * 
 * @author cyjoh
 */
public class UpdateEmployeeForm {

	/** 従業員ID */
	private String id;

	/** 扶養人数 */
	@NotBlank(message="扶養人数を入力してください。")
	@Pattern(regexp="^[0-9]+$",message="扶養人数は1桁以上の半角数字で入力してください。")
	private String dependentsCount;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDependentsCount() {
		return dependentsCount;
	}

	public void setDependentsCount(String dependentsCount) {
		this.dependentsCount = dependentsCount;
	}

	@Override
	public String toString() {
		return "UpdateEmployeeForm [id=" + id + ", dependentsCount=" + dependentsCount + "]";
	}

}
