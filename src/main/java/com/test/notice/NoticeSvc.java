package com.test.notice;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.test.file.FileVO;

@Service
public class NoticeSvc {

	@Autowired
	private SqlSessionTemplate sqlSession;

	@Autowired
	private DataSourceTransactionManager txManager;

	public NoticeVO selectNoticeOne(String ntcNo) {
		return sqlSession.selectOne("selectNoticeOne", ntcNo);
	}

	public List<?> selectNoticeFile(String ntcNo) {
		return sqlSession.selectList("selectNoticeFile", ntcNo);
	}

	public void insertNotice(NoticeVO noticeInfo, List<FileVO> filelist, String[] fileno) {

		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status = txManager.getTransaction(def);

		try {
			if (noticeInfo.getNtcNo() == null || "".equals(noticeInfo.getNtcNo())) {
				sqlSession.insert("insertNotice", noticeInfo);
			} else {
				sqlSession.update("updateNotice", noticeInfo);
			}

			if (fileno != null) {
				HashMap p = new HashMap();
				p.put("fileno", fileno);
				sqlSession.insert("deleteNoticeFile", p);
			}

			for (FileVO f : filelist) {
				f.setParentPK(noticeInfo.getNtcNo());
				sqlSession.insert("insertNoticeFile", f);
			}
			txManager.commit(status);
		} catch (Exception ex) {
			txManager.rollback(status);
		}
	}

	public List<?> selectNoticeList() {
		return sqlSession.selectList("selectNoticeList");
	}

	public void deleteNoticeOne(String ntcNo) {
		sqlSession.delete("deleteNoticeOne", ntcNo);	
		sqlSession.delete("deleteNoticeFile", ntcNo);
	}
}
