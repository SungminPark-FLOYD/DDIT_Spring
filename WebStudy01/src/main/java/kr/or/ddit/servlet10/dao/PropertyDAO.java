package kr.or.ddit.servlet10.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.vo.DbVO;

public interface PropertyDAO {
	public List<DbVO> selectProperties(Map<String, Object> paramMap);
}
