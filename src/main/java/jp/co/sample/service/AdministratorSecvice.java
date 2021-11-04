package jp.co.sample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sample.domain.Administrator;
import jp.co.sample.repository.AdministratorRepository;

/**
 *　管理者情報に関係する処理を実行するサービスクラスです。<br>
 * @author cyjoh
 */
@Service
@Transactional
public class AdministratorSecvice {

	@Autowired
	private AdministratorRepository repository;

	/**
	 * 管理者情報をレポジトリクラスに渡し、追加指示を行うメソッドです。
	 * @param administrator 外部で指定された管理者情報
	 */
	public void insert(Administrator administrator) {
		repository.insert(administrator);
	}
	
}
