package jp.co.sample.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Administrator;

/**
 * administratorsテーブルとデータをやり取りするレポジトリクラスです。<br>
 * @author cyjoh
 *
 */
@Repository
public class AdministratorRepository {
//	RowMapperを一行で記載する場合は以下。
//	private static final RowMapper<Administrator> ADMINISTRATOR_ROW_MAPPER 
//							= new BeanPropertyRowMapper<>(Administrator.class);

	private static final RowMapper<Administrator> ADMINISTRATOR_ROW_MAPPER = (rs,i)->{
		Administrator administrator = new Administrator();
		administrator.setId(rs.getInt("id"));
		administrator.setName(rs.getString("name"));
		administrator.setMailAddress(rs.getString("mail_address"));
		administrator.setPassword(rs.getString("password"));
		return administrator;
	};
	
	private static final String TABLE_NAME = "administrators";
	
	@Autowired
	private NamedParameterJdbcTemplate template ;


	/**
	 * administratorsテーブルにドメイン(1行)を追加するメソッド。<br>
	 * @param administrator　外部で作成されたadministratorドメイン。
	 */
	public void insert(Administrator administrator) {
		String sql = "INSERT INTO " + TABLE_NAME + " (name,mail_address,password) VALUES (:name,:mailAddress,:password);";
		SqlParameterSource param = new BeanPropertySqlParameterSource(administrator);
		template.update(sql, param);
	}
	
	/**
	 * administratorsテーブルから、引数で受け取ったメールアドレスとパスワードを持つ管理者アカウントを抽出するメソッド。<br>
	 * 
	 * @param mailAddress　	外部で指定されたメールアドレス
	 * @param password		外部で指定されたパスワード
	 * @return				引数で受け取ったメールアドレスとパスワードを持つ管理者アカウントのリスト
	 */
	public Administrator findByMailAddressAndPassword(String mailAddress,String password) {
		String sql = "SELECT * FROM " +TABLE_NAME + " WHERE mail_address = :mailAddress  and password = :password;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("mailAddress", mailAddress).addValue("password", password);
		
		List<Administrator> administratorList = template.query(sql, param, ADMINISTRATOR_ROW_MAPPER);
		if(administratorList.size()==0) {
			return null;
		}
		return administratorList.get(0);
	}
}
