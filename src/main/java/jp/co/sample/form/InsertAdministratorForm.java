package jp.co.sample.form;

/**
 * @author cyjoh 管理者を追加する際に、必要な情報を受け取るためのフォームクラスです。<br>
 * 
 */
public class InsertAdministratorForm {
	/** 氏名 */
	private String name;

	/** メールアドレス */
	private String mailAddress;

	/** パスワード */
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
