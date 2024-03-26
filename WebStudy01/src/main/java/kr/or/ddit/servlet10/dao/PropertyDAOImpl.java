package kr.or.ddit.servlet10.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.exception.PersistenceException;
import kr.or.ddit.utils.NamingUtils;
import kr.or.ddit.vo.DbVO;

public class PropertyDAOImpl implements PropertyDAO{

	@Override
	public List<DbVO> selectProperties(Map<String, Object> paramMap) {
		try(
				Connection conn = ConnectionFactory.getConnection();
				Statement stmt = conn.createStatement();
			) {
				StringBuffer sql = new StringBuffer();
				
				sql.append("select property_name, property_value, description ");
				sql.append("from database_properties                          ");
				ResultSet rs = stmt.executeQuery(sql.toString());
				ResultSetMetaData a = rs.getMetaData();
				
				int count = a.getColumnCount();
				String[] headers = new String[count];
				for(int idx = 1; idx <= count; idx++) {
					headers[idx-1] = NamingUtils.snakeToCamel(a.getColumnName(idx));
					
				}
				List<DbVO> list = new ArrayList<>();
				while(rs.next()){
					
					String propertyName = rs.getString(1);
					String propertyValue = rs.getString(2);
					String description = rs.getString(3);
					DbVO vo = new DbVO();
					vo.setPropertyName(propertyName);
					vo.setPropertyValue(propertyValue);
					vo.setDescription(description);
					list.add(vo);
				}
				
				paramMap.put("headers", headers);
				paramMap.put("list", list);
				
				//Conection full 구조이기 때문에 로직이 종료된 후에 연결을 종료 시켜주어야 한다
				rs.close();
				return list;
			}catch (SQLException e) {
				throw new PersistenceException(e);
			}
		
		
	}

}
