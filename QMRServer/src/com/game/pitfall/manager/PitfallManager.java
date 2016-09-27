package com.game.pitfall.manager;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import com.game.data.bean.Q_special_eventBean;
import com.game.manager.ManagerPool;
import com.game.map.script.IEnterGridScript;
import com.game.structs.Grid;
import com.game.utils.MapUtils;
import com.game.utils.Symbol;

public class PitfallManager {

	private ConcurrentHashMap<Integer, ConcurrentHashMap<Integer, Vector<IEnterGridScript>>> pitfalls = new ConcurrentHashMap<Integer, ConcurrentHashMap<Integer, Vector<IEnterGridScript>>>();
	
	private Logger log = Logger.getLogger(PitfallManager.class);
	
	private static Object obj = new Object();
	//管理类实例
	private static PitfallManager manager;

	private PitfallManager() {
		initPitfall();
	}

	public static PitfallManager getInstance() {
		synchronized (obj) {
			if (manager == null) {
				manager = new PitfallManager();
			}
		}
		return manager;
	}
	
	public void initPitfall(){
		List<Q_special_eventBean> events = ManagerPool.dataManager.q_special_eventContainer.getList();
		Iterator<Q_special_eventBean> iter = events.iterator();
		while (iter.hasNext()) {
			Q_special_eventBean q_special_eventBean = (Q_special_eventBean) iter
					.next();
			if(q_special_eventBean.getQ_event_pos()==null || ("").equals(q_special_eventBean.getQ_event_pos())){
				continue;
			}
			//初始化所有陷阱点
			IEnterGridScript script = (IEnterGridScript)ManagerPool.scriptManager.getScript(q_special_eventBean.getQ_event_scriptid());
			if(script==null){
				continue;
			}
			try{
				String[] strs = q_special_eventBean.getQ_event_pos().split(Symbol.XIAHUAXIAN_REG);
				int mapModelId = Integer.parseInt(strs[0]);
				ConcurrentHashMap<Integer, Vector<IEnterGridScript>> mapPitfalls = null;
				if(pitfalls.containsKey(mapModelId)){
					mapPitfalls = pitfalls.get(mapModelId);
				}else{
					mapPitfalls = new ConcurrentHashMap<Integer, Vector<IEnterGridScript>>();
					pitfalls.put(mapModelId, mapPitfalls);
				}
				Grid[][] blocks = ManagerPool.mapManager.getMapBlocks(mapModelId);
				if(blocks==null){
					continue;
				}
				short x = Short.parseShort(strs[1]);
				short y = Short.parseShort(strs[2]);
				int radius = Integer.parseInt(strs[3]);
				List<Grid> grids = MapUtils.getRoundGridByGridRadius(x, y, radius, blocks);
				for (Grid grid : grids) {
					Vector<IEnterGridScript> scripts = null;
					if(mapPitfalls.containsKey(grid.getKey())){
						scripts = mapPitfalls.get(grid.getKey());
					}else{
						scripts = new Vector<IEnterGridScript>();
						mapPitfalls.put(grid.getKey(), scripts);
					}
					if(!scripts.contains(script)) scripts.add(script);
				}
			}catch (Exception e) {
				log.error(e, e);
			}
		}
		
	}

	public ConcurrentHashMap<Integer, ConcurrentHashMap<Integer, Vector<IEnterGridScript>>> getPitfalls() {
		return pitfalls;
	}

	public void setPitfalls(
			ConcurrentHashMap<Integer, ConcurrentHashMap<Integer, Vector<IEnterGridScript>>> pitfalls) {
		this.pitfalls = pitfalls;
	}
	
	
}
