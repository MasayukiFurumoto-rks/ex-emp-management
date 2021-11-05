package jp.co.sample.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @author cyjoh 管理者を追加する際に、必要な情報を受け取るためのフォームクラスです。<br>
 * 
 */
public class InsertAdministratorForm {
	/** 氏名 */
	@NotBlank(message="名前は必須です。")
	private String name;

	/** メールアドレス */
	@NotBlank(message="メールアドレスは必須です。")
	@Email(message="メールアドレスは正しい形式で入力してください。")
	private String mailAddress;

	/** パスワード */
	@NotBlank(message="パスワードは必須です。")
	private String password;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "InsertAdministratorForm [name=" + name + ", mailAddress=" + mailAddress + ", password=" + password
				+ "]";
	}

}
