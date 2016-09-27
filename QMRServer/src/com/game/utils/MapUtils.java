package com.game.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.map.manager.MapManager;
import com.game.structs.Grid;
import com.game.structs.Position;
import com.game.structs.RoadGrid;

public class MapUtils {

	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(MapUtils.class);
	public static int GRID_BORDER = 25;
	private static int mapId = 0;
	private static Object obj = new Object();

	public synchronized static int getMapId() {
		synchronized (obj) {
			return 1000000 + (mapId++);
		}
	}

	/**
	 * 寻路算法
	 *
	 * @param mapInfos 地图阻挡信息
	 * @param start 开始节点
	 * @param end 结束节点
	 * @param steps 计算步数
	 * @return
	 */
	public static List<Position> findRoads(Grid[][] mapInfos, Position start, Position end, int steps, boolean canSwim) {
		//返回的结果路径
		List<Position> result = new ArrayList<Position>();
		//开始所在地图格子
		Grid startGrid = getGrid(start, mapInfos);
		//结束所在地图格子
		Grid endGrid = getGrid(end, mapInfos);
		//未移动，格子相同
		if (startGrid.equal(endGrid)) {
			return result;
		}

		RoadGrid startRoadGrid = new RoadGrid();
		startRoadGrid.setGrid(startGrid);

		RoadGrid endRoadGrid = new RoadGrid();
		endRoadGrid.setGrid(endGrid);

		//待计算格子
		List<RoadGrid> waitting = new ArrayList<RoadGrid>();
		//已计算格子
		HashMap<Integer, RoadGrid> counted = new HashMap<Integer, RoadGrid>();
		//遍历过的格子
		HashSet<Integer> passed = new HashSet<Integer>();

		waitting.add(startRoadGrid);
		passed.add(startGrid.getKey());

		int step = 0;

		while (waitting.size() > 0 && (step < steps || steps == -1)) {
			//取出优先级最高的格子(权值最小)
			RoadGrid grid = waitting.get(0);
			waitting.remove(0);
			step++;

			//到达终点
			if (grid.equal(endRoadGrid)) {
				endRoadGrid = grid;
				break;
			}

			//加入已计算中
			counted.put(grid.getGrid().getKey(), grid);

			//获取周围格子信息
			List<Grid> rounds = getRoundGrid(grid.getGrid(), mapInfos);

			for (int i = 0; i < rounds.size(); i++) {
				Grid round = rounds.get(i);

				//已经遍历过
				if (passed.contains(round.getKey())) {
					continue;
				} //在地图内是不可行走点
				else if (round.getBlock() == 0) {
					continue;
				} //斜边判断相邻方向是否都为阻挡
				else if (round.getY() != grid.getGrid().getY() && round.getX() != grid.getGrid().getX()) {
					if (mapInfos[round.getY()][grid.getGrid().getX()].getBlock() == 0 && mapInfos[grid.getGrid().getY()][round.getX()].getBlock() == 0) {
						continue;
					}
				} 
				//在地图内是游泳点
				if (ManagerPool.mapManager.isSwimGrid(round) && !canSwim) {
					continue;
				} 

				RoadGrid roundGrid = new RoadGrid();

				roundGrid.setGrid(round);

				roundGrid.setFarther(grid.getGrid().getKey());

				//加入遍历过格子
				passed.add(round.getKey());

				//计算权值
				roundGrid.setWeight(countWeight(round, endGrid));

				//插入到待计算列表
				insert(waitting, roundGrid);

			}
		}

		//计算路径
		if (endRoadGrid.getFarther() != -1) {
			//已经找到终点
			RoadGrid _grid = endRoadGrid;
			result.add(0, end);
			while (_grid.getFarther() != -1) {
				_grid = counted.get(_grid.getFarther());
				result.add(0, _grid.getGrid().getCenter());
			}
		} else if (step == steps && waitting.size() > 0) {
			//到达寻路最大步数
			RoadGrid _grid = waitting.get(0);
			result.add(0, _grid.getGrid().getCenter());
			while (_grid.getFarther() != -1) {
				_grid = counted.get(_grid.getFarther());
				//invert.add(_grid);
				result.add(0, _grid.getGrid().getCenter());
			}
		}

		return result;
	}

	/**
	 * 根据坐标获得所在地图格子
	 *
	 * @param pos 坐标
	 * @return
	 */
	public static Grid getGrid(Position pos, Grid[][] mapInfos) {
		int y = pos.getY() / GRID_BORDER;
		int x = pos.getX() / GRID_BORDER;
		if (x < 0 || x >= mapInfos[0].length || y < 0 || y >= mapInfos.length) {
			return null;
		}
		return mapInfos[y][x];
	}

	/**
	 * 获得周围格子（包含本格子，共9个）
	 *
	 * @param grid 格子
	 * @return
	 */
	public static List<Grid> getRoundGrid(Grid grid, Grid[][] mapInfos) {
		List<Grid> grids = new ArrayList<Grid>();
		for (int i = 0; i < 9; i++) {
			int x = grid.getX() + i % 3 - 1;
			int y = grid.getY() + i / 3 - 1;
			if (x < 0 || x >= mapInfos[0].length || y < 0 || y >= mapInfos.length) {
				continue;
			}
			Grid _grid = mapInfos[y][x];
			grids.add(_grid);
		}
		return grids;
	}
	
	/**
	 * 获得周围格子
	 *
	 * @param grid 格子
	 * @param radius 格子距离
	 * @return
	 */
	public static List<Grid> getRoundGridByGridRadius(int gridx, int gridy, int radius, Grid[][] mapInfos) {
		int round = 2 * radius + 1;
		int num = round * round;
		List<Grid> grids = new ArrayList<Grid>();
		for (int i = 0; i < num; i++) {
			int x = gridx + i % round - radius;
			int y = gridy + i / round - radius;
			if (x < 0 || x >= mapInfos[0].length || y < 0 || y >= mapInfos.length) {
				continue;
			}
			Grid _grid = mapInfos[y][x];
			grids.add(_grid);
		}
		return grids;
	}

	
	/**
	 * 获得周围格子
	 *
	 * @param grid 格子
	 * @param minradius 最小格子距离
	 * @param maxradius 最大格子距离
	 * @param block 是否包含阻挡 false不包含阻挡
	 * @param swim 是否包含游泳 false不包含游泳点
	 * @return
	 */
	public static List<Grid> getRoundGridByGridRadius(int gridx, int gridy, int minradius, int maxradius, boolean block, boolean swim, Grid[][] mapInfos) {
		int round = 2 * maxradius + 1;
		int num = round * round;
		List<Grid> grids = new ArrayList<Grid>();
		for (int i = 0; i < num; i++) {
			int x = gridx + i % round - maxradius;
			int y = gridy + i / round - maxradius;
			if (x < 0 || x >= mapInfos[0].length || y < 0 || y >= mapInfos.length) {
				continue;
			}
			if(Math.max(x, y) < minradius){
				continue;
			}
			Grid _grid = mapInfos[y][x];
			if(_grid==null){
				continue;
			}
			if(!block && _grid.getBlock()==0){
				continue;
			}
			if(!swim && ManagerPool.mapManager.isSwimGrid(_grid)){
				continue;
			}
			grids.add(_grid);
		}
		return grids;
	}
	
	/**
	 * 获得周围一可站立点
	 *
	 * @param grid 格子
	 * @param minradius 最小格子距离
	 * @param maxradius 最大格子距离
	 * @param block 是否包含阻挡 false不包含阻挡
	 * @param swim 是否包含游泳 false不包含游泳点
	 * @return
	 */
	public static Position getRoundPosByGridRadius(int gridx, int gridy, int minradius, int maxradius, boolean block, boolean swim, int mapmodelid) {
		Grid[][] mapInfos = MapManager.getInstance().getMapBlocks(mapmodelid);
		if (mapInfos == null) {
			return null;
		}
		List<Grid> grids = getRoundGridByGridRadius(gridx, gridy, minradius, maxradius, block, swim, mapInfos);
		if (grids.isEmpty()) {
			return null;
		}else{
			return grids.get(RandomUtils.random(grids.size())).getCenter();
		}
	}

	/**
	 * 获得周围的格子
	 *
	 * @param position
	 * @param mapInfos
	 * @return
	 */
	public static List<Grid> getRoundGrid(Position position, Grid[][] mapInfos) {
		return getRoundGrid(getGrid(position, mapInfos), mapInfos);
	}

	/**
	 * 获得周围格子（包含本格子）
	 *
	 * @param grid 格子
	 * @param radius 范围
	 * @return
	 */
	public static List<Grid> getRoundGrid(Grid grid, int radius, Grid[][] mapInfos) {
		return getRoundGrid(grid.getCenter(), radius, mapInfos);
	}

	/**
	 * 获取指定范围不是阻挡点的格子列表
	 *
	 * @param grid
	 * @param radius
	 * @param mapModelId
	 * @return
	 */
	public static List<Grid> getRoundNoBlockGrid(Position position, int radius, int mapModelId) {
		Grid[][] mapBlocks = MapManager.getInstance().getMapBlocks(mapModelId);
		List<Grid> roundGrid = getRoundGrid(position, radius, mapBlocks);
		Iterator<Grid> iterator = roundGrid.iterator();
		while (iterator.hasNext()) {
			Grid grid2 = (Grid) iterator.next();
			if (grid2.getBlock() == 0) {
				iterator.remove();
			}
		}
		return roundGrid;
	}

	/**
	 * 获取指定范围不是阻挡点的格子列表
	 *
	 * @param grid
	 * @param radius
	 * @param mapModelId
	 * @return
	 */
	public static List<Grid> getRoundNoBlockGrid(Grid grid, int radius, int mapModelId) {
		Grid[][] mapBlocks = MapManager.getInstance().getMapBlocks(mapModelId);
		List<Grid> roundGrid = getRoundGrid(grid, radius, mapBlocks);
		Iterator<Grid> iterator = roundGrid.iterator();
		while (iterator.hasNext()) {
			Grid grid2 = (Grid) iterator.next();
			if (grid2.getBlock() == 0) {
				iterator.remove();
			}
		}
		return roundGrid;
	}
	
	/**
	 * 获取指定范围不是阻挡点或游泳点的格子列表
	 *
	 * @param grid
	 * @param radius
	 * @param mapModelId
	 * @return
	 */
	public static List<Grid> getRoundNoBlockAndSwimGrid(Grid grid, int radius, int mapModelId) {
		Grid[][] mapBlocks = MapManager.getInstance().getMapBlocks(mapModelId);
		List<Grid> roundGrid = getRoundGrid(grid, radius, mapBlocks);
		Iterator<Grid> iterator = roundGrid.iterator();
		while (iterator.hasNext()) {
			Grid grid2 = (Grid) iterator.next();
			if (grid2.getBlock() == 0 ||  ManagerPool.mapManager.isSwimGrid(grid2)) {
				iterator.remove();
			}
		}
		return roundGrid;
	}

	public static List<Grid> getAllNoBlockGrid(int mapModelId) {
		List<Grid> list = new ArrayList<Grid>();
		Grid[][] mapBlocks = MapManager.getInstance().getMapBlocks(mapModelId);
		for (Grid[] grids : mapBlocks) {
			for (Grid grid : grids) {
				if (grid.getBlock() != 0) {
					list.add(grid);
				}
			}
		}
		return list;
	}
	
	public static List<Grid> getAllNoBlockAndSwimGrid(int mapModelId) {
		List<Grid> list = new ArrayList<Grid>();
		Grid[][] mapBlocks = MapManager.getInstance().getMapBlocks(mapModelId);
		for (Grid[] grids : mapBlocks) {
			for (Grid grid : grids) {
				if (grid.getBlock() != 0 && !ManagerPool.mapManager.isSwimGrid(grid)) {
					list.add(grid);
				}
			}
		}
		return list;
	}

	/**
	 * 获得周围的格子
	 *
	 * @param position
	 * @param radius
	 * @param mapInfos
	 * @return
	 */
	public static List<Grid> getRoundGrid(Position position, int radius, Grid[][] mapInfos) {
		List<Grid> grids = new ArrayList<Grid>();
		int x = position.getX() - radius;
		if (x < 0) {
			x = 0;
		}
		int left = x / GRID_BORDER;
		x = position.getX() + radius;
		int right = x / GRID_BORDER;
		if (right >= mapInfos[0].length) {
			right = mapInfos[0].length - 1;
		}
		int y = position.getY() - radius;
		if (y < 0) {
			y = 0;
		}
		int top = y / GRID_BORDER;
		y = position.getY() + radius;
		int buttom = y / GRID_BORDER;
		if (buttom >= mapInfos.length) {
			buttom = mapInfos.length - 1;
		}

		for (int i = top; i <= buttom; i++) {
			for (int j = left; j <= right; j++) {
				Grid _grid = mapInfos[i][j];
				grids.add(_grid);
			}
		}
		return grids;
	}

	/**
	 * 获取中心周围可站立点
	 *
	 * @param position
	 * @param radius
	 * @param mapInfos
	 * @return
	 */
	public static Grid getRandomGrid(Position position, int radius, int mapModelId) {
		Grid[][] mapInfos = MapManager.getInstance().getMapBlocks(mapModelId);
		List<Grid> grids = getRoundGrid(position, radius, mapInfos);
		Grid stand = getGrid(position, mapInfos);
		Grid grid = null;
		while (grids.size() > 0 && (grid == null || grid.getBlock() != stand.getBlock())) {
			grid = grids.get(RandomUtils.random(grids.size()));
		}

		return grid;
	}

	/**
	 * 获得格子
	 * 传入格子坐标
	 * @param x 格子x
	 * @param y 格子y
	 * @return
	 */
	public static Grid getGrid(int x, int y, Grid[][] mapInfos) {
		if (x < 0 || x >= mapInfos[0].length || y < 0 || y >= mapInfos.length) {
			return null;
		}
		return mapInfos[y][x];
	}

	/**
	 * 获得格子
	 * 传入格子坐标
	 * @param x 格子x
	 * @param y 格子y
	 * @return
	 */
	public static Grid getGrid(int x, int y, int mapModelId) {
		Grid[][] mapInfos = ManagerPool.mapManager.getMapBlocks(mapModelId);
		return getGrid(x, y, mapInfos);
	}

	/**
	 * 构建格子
	 *
	 * @param x
	 * @param y
	 * @return
	 */
	public static Grid buidGrid(int x, int y) {
		return new Grid(x, y);
	}

	/**
	 * 判断是否阻挡点
	 *
	 * @param position
	 * @param mapInfos
	 * @return
	 */
	public static boolean isBlock(Position position, Grid[][] mapInfos) {
		Grid grid = getGrid(position, mapInfos);
		if (mapInfos[grid.getY()][grid.getX()].getBlock() == 0) {
			return true;
		}
		return false;
	}

	public static boolean isBlock(Grid grid) {
		return grid.getBlock() == 0;
	}

	/**
	 * 计算权值
	 *
	 * @param start 开始格子
	 * @param end 结束给子
	 * @return
	 */
	private static int countWeight(Grid start, Grid end) {
		return Math.abs(end.getX() - start.getX()) + Math.abs(end.getY() - start.getY());
	}

	/**
	 * 按权值插入队列
	 *
	 * @param waitting 队列
	 * @param grid 格子
	 */
	private static void insert(List<RoadGrid> waitting, RoadGrid grid) {

		if (waitting.size() == 0) {
			waitting.add(grid);
			return;
		}

		//头部插入
		for (int i = 0; i < waitting.size(); i++) {
			RoadGrid _grid = waitting.get(i);
			if (_grid.getWeight() < grid.getWeight()) {
				continue;
			}
			waitting.add(i, grid);
			return;
		}

		waitting.add(grid);
	}

	/**
	 * 计算2点间距离(返回像素)
	 *
	 * @param pos1
	 * @param pos2
	 * @return
	 */
	public static double countDistance(Position pos1, Position pos2) {
		return countDistance(pos1.getX(), pos1.getY(), pos2.getX(), pos2.getY());
	}

	/**
	 * 计算2点间距离(返回格子数)
	 *
	 * @param pos1
	 * @param pos2
	 * @return
	 */
	public static double countGridDistance(Position pos1, Position pos2, Grid[][] mapinfos) {
		return countDistance(getGrid(pos1, mapinfos), getGrid(pos2, mapinfos));
	}

	/**
	 * 计算2格子之间距离(返回格子x，y差值中得最大值)
	 *
	 * @param grid1
	 * @param grid2
	 * @return
	 */
	public static int countDistance(Grid grid1, Grid grid2) {
		return Math.max(Math.abs(grid2.getX() - grid1.getX()), Math.abs(grid2.getY() - grid1.getY()));
	}

	/**
	 * 计算2点间距离(返回像素)
	 *
	 * @param sx	开始点X
	 * @param sy	开始点Y
	 * @param tx	结束点X
	 * @param ty	结束点Y
	 * @return
	 */
	public static double countDistance(short sx, short sy, short tx, short ty) {
		return Math.sqrt(Math.pow((sx - tx), 2) + Math.pow((sy - ty), 2));
	}

	public static Position buildPosition(short x, short y) {
		return new Position(x, y);
	}

	public static Position buildPosition(int x, int y) {
		return new Position((short) x, (short) y);
	}

	/**
	 * 地图中 获取不重复的随机可刷怪点()
	 *
	 * @param mapModelId 地图模板id
	 * @return
	 */
	public static Position getMapRandPoint(int mapModelId) {
		Grid[][] mapBlocks = MapManager.getInstance().getMapBlocks(mapModelId);
		if (mapBlocks == null) {
			logger.error("找不到地图" + mapModelId + "的阻挡点数据");
			return null;
		}
		int ranidx = 0;
		Grid rangrid = null;
		while (ranidx < 50 && (rangrid == null || rangrid.getBlock() == 0 || rangrid.getEffect() == 1)) {
			rangrid = mapBlocks[RandomUtils.random(mapBlocks.length)][RandomUtils.random(mapBlocks[0].length)];
			if (rangrid != null && rangrid.getBlock() != 0 && rangrid.getEffect() != 1) {
				List<Grid> roundGrid = getRoundGrid(rangrid.getCenter(), mapBlocks);
				while (roundGrid.size() > 0) {
					Grid grid = roundGrid.remove(RandomUtils.random(roundGrid.size()));
					if (grid.getBlock() != 0 && grid.getEffect() != 1) {
						return grid.getCenter();
					}
				}
				return rangrid.getCenter();
			}
			ranidx++;
		}
		return null;
	}

	/**
	 * 以一个点为中心radius为半径的圈内 获取不重复的随机落点
	 *
	 * @param basePoint 中心点
	 * @param radius 像素半径
	 * @return
	 */
	public static Position getRandRoundPoint(Position basePoint, int mapModelId) {
		Grid[][] mapBlocks = MapManager.getInstance().getMapBlocks(mapModelId);
		if (mapBlocks == null) {
			logger.error("找不到地图" + mapModelId + "的阻挡点数据");
			return basePoint;
		}
		Grid basegrid = getGrid(basePoint, mapBlocks);
		List<Grid> roundGrid = getRoundGrid(basePoint, mapBlocks);
		while (roundGrid.size() > 0) {
			Grid grid = roundGrid.remove(RandomUtils.random(roundGrid.size()));
			if(grid.equal(basegrid)){
				continue;
			}
			if (grid.getBlock() != 0 && grid.getBlock() == basegrid.getBlock()) {
				return grid.getCenter();
			}
		}
		return basePoint;
	}

	/**
	 * 以一个点为中心radius为半径的圈内 获取不重复的随机落点
	 *
	 * @param basePoint 中心点
	 * @param dir 方向 7：↖， 6：←， 5：↙， 4：↓， 3：↘， 2：→，1：↗，0：↑
	 * @param mapModelId 地图模板Id
	 * @return
	 */
	public static Position getBackPoint(Position basePoint, int dir, int mapModelId) {
		Grid[][] mapBlocks = MapManager.getInstance().getMapBlocks(mapModelId);
		if (mapBlocks == null) {
			logger.error("找不到地图" + mapModelId + "的阻挡点数据");
			return basePoint;
		}
		Grid basegrid = getGrid(basePoint, mapBlocks);
		Grid backgrid = null;

		switch (dir) {
			case 0:
				backgrid = getGrid(basegrid.getX(), basegrid.getY() + 1, mapBlocks);
				break;
			case 1:
				backgrid = getGrid(basegrid.getX() - 1, basegrid.getY() + 1, mapBlocks);
				break;
			case 2:
				backgrid = getGrid(basegrid.getX() - 1, basegrid.getY(), mapBlocks);
				break;
			case 3:
				backgrid = getGrid(basegrid.getX() - 1, basegrid.getY() - 1, mapBlocks);
				break;
			case 4:
				backgrid = getGrid(basegrid.getX(), basegrid.getY() - 1, mapBlocks);
				break;
			case 5:
				backgrid = getGrid(basegrid.getX() + 1, basegrid.getY() - 1, mapBlocks);
				break;
			case 6:
				backgrid = getGrid(basegrid.getX() + 1, basegrid.getY(), mapBlocks);
				break;
			case 7:
				backgrid = getGrid(basegrid.getX() + 1, basegrid.getY() + 1, mapBlocks);
				break;

		}

		if (backgrid != null) {
			if (backgrid.getBlock() != 0 && backgrid.getBlock() == basegrid.getBlock()) {
				return backgrid.getCenter();
			} else {
				return basePoint;
			}
		} else {
			return basePoint;
		}
	}

	/**
	 * 找到一个点后方一定距离的一个点距离
	 *
	 * @param basePoint 中心点
	 * @param dir 方向 7：↖， 6：←， 5：↙， 4：↓， 3：↘， 2：→，1：↗，0：↑
	 * @param mapModelId 地图模板Id
	 * @param radius 距离
	 * @return
	 */
	public static Position getBackPoint(Position basePoint, int dir, int mapModelId, int radius) {
		Grid[][] mapBlocks = MapManager.getInstance().getMapBlocks(mapModelId);
		if (mapBlocks == null) {
			logger.error("找不到地图" + mapModelId + "的阻挡点数据");
			return basePoint;
		}
		Grid basegrid = getGrid(basePoint, mapBlocks);
		Grid backgrid = null;

		int k = radius / GRID_BORDER;
		while (backgrid == null && k > 0) {
			switch (dir) {
				case 0:
					backgrid = getGrid(basegrid.getX(), basegrid.getY() + k, mapBlocks);
					break;
				case 1:
					backgrid = getGrid(basegrid.getX() - k, basegrid.getY() + k, mapBlocks);
					break;
				case 2:
					backgrid = getGrid(basegrid.getX() - k, basegrid.getY(), mapBlocks);
					break;
				case 3:
					backgrid = getGrid(basegrid.getX() - k, basegrid.getY() - k, mapBlocks);
					break;
				case 4:
					backgrid = getGrid(basegrid.getX(), basegrid.getY() - k, mapBlocks);
					break;
				case 5:
					backgrid = getGrid(basegrid.getX() + k, basegrid.getY() - k, mapBlocks);
					break;
				case 6:
					backgrid = getGrid(basegrid.getX() + k, basegrid.getY(), mapBlocks);
					break;
				case 7:
					backgrid = getGrid(basegrid.getX() + k, basegrid.getY() + k, mapBlocks);
					break;

			}
			k--;
			if (backgrid != null) {
				if (backgrid.getBlock() == 0 || backgrid.getBlock() != basegrid.getBlock()) {
					backgrid = null;
				}
			}
		}
		if (backgrid != null) {
			return backgrid.getCenter();
		} else {
			return basePoint;
		}
	}

	public static List<Grid> getLineGrids(Position pos1, Position pos2, int mapModelId) {
		List<Grid> grids = new ArrayList<Grid>();
		Grid[][] mapBlocks = MapManager.getInstance().getMapBlocks(mapModelId);
		if (mapBlocks == null) {
			logger.error("找不到地图" + mapModelId + "的阻挡点数据");
			return grids;
		}
		Grid start = getGrid(pos1, mapBlocks);
		Grid end = getGrid(pos2, mapBlocks);
		if (start == null || end == null) {
			return grids;
		}
		grids.add(start);
		if (!start.equal(end)) {
			int y = end.getY() - start.getY();
			int x = end.getX() - start.getX();

			if (Math.abs(y) >= Math.abs(x)) {
				int step = 1;
				if (y < 0) {
					step = -1;
				}
				double per = (double) x / y;
				int posY = start.getY();
				for (int i = 0; i < Math.abs(y); i++) {
					posY = posY + step;
					int posX = start.getX() + (int) Math.round(per * step * (i + 1));
					Grid grid = getGrid(posX, posY, mapBlocks);
					if (grid != null) {
						grids.add(grid);
					}
				}
			} else {
				int step = 1;
				if (x < 0) {
					step = -1;
				}
				double per = (double) y / x;
				int posX = start.getX();
				for (int i = 0; i < Math.abs(x); i++) {
					posX = posX + step;
					int posY = start.getY() + (int) Math.round(per * step * (i + 1));
					Grid grid = getGrid(posX, posY, mapBlocks);
					if (grid != null) {
						grids.add(grid);
					}
				}
			}
		}
		return grids;
	}

	/**
	 * 计算角度
	 *
	 * @return 坐标2相对坐标1的角度 7：↖， 6：←， 5：↙， 4：↓， 3：↘， 2：→，1：↗，0：↑
	 */
	public static int countDirection(Position pos1, Position pos2) {
		if (pos1.getY() == pos2.getY() && pos1.getX() == pos2.getX()) {
			return 4;
		} else if (pos1.getY() == pos2.getY()) {
			if (pos1.getX() < pos2.getX()) {
				return 2;
			} else {
				return 6;
			}
		} else if (pos1.getX() == pos2.getX()) {
			if (pos1.getY() < pos2.getY()) {
				return 4;
			} else {
				return 0;
			}
		} else {
			double angle = Math.atan(((double) (pos2.getY() - pos1.getY())) / -(pos2.getX() - pos1.getX()));
			double angle22d5 = Math.PI / 8;
			double angle67d5 = Math.PI / 2 - angle22d5;
			if (angle > -angle22d5 && angle <= angle22d5) {
				if (pos1.getX() < pos2.getX()) {
					return 2;
				} else {
					return 6;
				}
			} else if (angle > angle22d5 && angle <= angle67d5) {
				if (pos1.getX() < pos2.getX()) {
					return 1;
				} else {
					return 5;
				}
			} else if (angle > -angle67d5 && angle <= -angle22d5) {
				if (pos1.getX() < pos2.getX()) {
					return 3;
				} else {
					return 7;
				}
			} else {
				if (pos1.getY() < pos2.getY()) {
					return 4;
				} else {
					return 0;
				}
			}
		}
	}

	/**
	 * 计算角度（格子1和格子2相邻）
	 *
	 * @return 格子2相对格子1的角度 7：↖， 6：←， 5：↙， 4：↓， 3：↘， 2：→，1：↗，0：↑
	 */
	public static int countDirection(Grid grid1, Grid grid2) {
		if (grid1.getY() == grid2.getY() && grid1.getX() == grid2.getX()) {
			return 4;
		} else if (grid1.getY() == grid2.getY()) {
			if (grid1.getX() < grid2.getX()) {
				return 2;
			} else {
				return 6;
			}
		} else if (grid1.getX() == grid2.getX()) {
			if (grid1.getY() < grid2.getY()) {
				return 4;
			} else {
				return 0;
			}
		} else if (grid1.getX() < grid2.getX()) {
			if (grid1.getY() < grid2.getY()) {
				return 3;
			} else {
				return 1;
			}
		} else {
			if (grid1.getY() < grid2.getY()) {
				return 5;
			} else {
				return 7;
			}
		}
	}

	public static int[] countDirectionAddtion(int dir) {
		int[] add = new int[2];
		//坐标2相对坐标1的角度 7：↖， 6：←， 5：↙， 4：↓， 3：↘， 2：→，1：↗，0：↑
		switch (dir) {
			case 0:
				add[1] = -1;
				break;
			case 1:
				add[1] = -1;
				add[0] = 1;
				break;
			case 2:
				add[0] = 1;
				break;
			case 3:
				add[1] = 1;
				add[0] = 1;
				break;
			case 4:
				add[1] = 1;
				break;
			case 5:
				add[1] = 1;
				add[0] = -1;
				break;
			case 6:
				add[0] = -1;
				break;
			case 7:
				add[1] = -1;
				add[0] = -1;
				break;
		}

		return add;
	}

	public static void main(String args[]) {
//		long currentTimeMillis = System.currentTimeMillis();
//		double sqrt = Math.sqrt(Double.MAX_VALUE);
//		System.out.println(sqrt+" time:"+(System.currentTimeMillis()-currentTimeMillis));
	}
}
