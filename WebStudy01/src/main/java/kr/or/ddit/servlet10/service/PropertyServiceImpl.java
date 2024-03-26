package kr.or.ddit.servlet10.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.servlet10.dao.PropertyDAO;
import kr.or.ddit.servlet10.dao.PropertyDAOImpl;
import kr.or.ddit.vo.DbVO;

public class PropertyServiceImpl implements PropertyService{
//	private PropertyDAO dao = new PropertyDAOImpl();
	private PropertyDAO dao;
		@Override
		public void readProperties(Map<String, Object> paramMap) {
			dao.selectProperties(paramMap);
		}

	
}
