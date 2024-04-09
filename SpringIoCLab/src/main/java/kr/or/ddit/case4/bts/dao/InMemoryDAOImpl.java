package kr.or.ddit.case4.bts.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.BtsVO;
import lombok.Data;

@Data
@Repository
public class InMemoryDAOImpl implements BtsDAO {
	
	@Resource(name = "btsMap")
	private final Map<String, Object[]> btsMap;
	
	//객체 생성 이후에 실행
	@PostConstruct
	public void init() {
		btsMap.put("B001", new Object[] {"뷔", "bts/bui", 100});
		btsMap.put("B002", new Object[] {"제이홉", "bts/jhop", 200});
		btsMap.put("B003", new Object[] {"지민", "bts/jimin", 300});
		btsMap.put("B004", new Object[] {"진", "bts/jin", 0});
		btsMap.put("B005", new Object[] {"정국", "bts/jungkuk", 120});
		btsMap.put("B006", new Object[] {"RM", "bts/rm", 30});
		btsMap.put("B007", new Object[] {"슈가", "bts/suga", 40});
	}
	


	@Override
	public BtsVO selectBts(String code) {
		Object[] values = btsMap.get(code);
		BtsVO bts = null;
		if(values != null) {
			bts = new BtsVO(code, (String)values[0], (String)values[1], (Integer)values[2]);
		}
		return bts;		
	}

	@Override
	public List<BtsVO> selectBtsList() {
		List<BtsVO> btsList = new ArrayList<BtsVO>();
		
		for(Entry<String, Object[]> entry : btsMap.entrySet()) {
			String code = entry.getKey();
			String name = (String)entry.getValue()[0];
			String path = (String)entry.getValue()[1];
			int count = (Integer)entry.getValue()[2];
			
			BtsVO vo = new BtsVO(code, name, path, count);
			btsList.add(vo);
		}
		
		Collections.sort(btsList, new BtsVO().reversed());
		return btsList;
	}

	@Override
	public void incrementHit(String code) {
		Object[] bts = btsMap.get(code);
		if(bts != null) {
			int count = (Integer)bts[2];
			bts[2] = count + 1;
		}
			
		//btsMap.put(bts.getCode(), new Object[] {bts.getName(), bts.getPath(), bts.getCount()});
	}

}
