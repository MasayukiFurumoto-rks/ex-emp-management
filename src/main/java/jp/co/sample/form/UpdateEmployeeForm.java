package jp.co.sample.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Range;

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
	@Range(min=0,max=10,message="扶養人数は0人以上10人以下で入力してください。")
	@Pattern(regexp="^[0-9０-９]+$",message="扶養人数は整数で入力してください。")
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
