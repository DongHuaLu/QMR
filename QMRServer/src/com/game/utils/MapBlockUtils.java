package com.game.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.game.data.bean.Q_mapBean;
import com.game.manager.ManagerPool;
import com.game.structs.Grid;

public class MapBlockUtils {

	public static void countBlock(Grid[][] mapInfos){
		int block = 1;
//		for (int i = 0; i < mapInfos.length; i++) {
//			for (int j = 0; j < mapInfos[i].length; j++) {
//				Grid grid = mapInfos[i][j];
//				System.out.print(grid.getBlock());
//			}
//			System.out.println();
//		}
//		System.out.println("原始结束");
		for (int i = 0; i < mapInfos.length; i++) {
			for (int j = 0; j < mapInfos[i].length; j++) {
				Grid grid = mapInfos[i][j];
				if(grid.getBlock()==1){
					grid.setBlock(-1);
				}
			}
		}
		for (int i = 0; i < mapInfos.length; i++) {
			for (int j = 0; j < mapInfos[i].length; j++) {
				Grid grid = mapInfos[i][j];
				if(grid.getBlock()==-1){
					countBlockArea(grid, mapInfos, block++);
				}
			}
		}
//		for (int i = 0; i < mapInfos.length; i++) {
//			for (int j = 0; j < mapInfos[i].length; j++) {
//				Grid grid = mapInfos[i][j];
//				System.out.print(grid.getBlock());
//			}
//			System.out.println();
//		}
	}
	
	private static void countBlockArea(Grid grid, Grid[][] mapInfos, int index){
		List<Grid> waitting = new ArrayList<Grid>();
		HashSet<Integer> counted = new HashSet<Integer>();
		waitting.add(grid);
		counted.add(grid.getKey());
		while(waitting.size() > 0){
			Grid _grid = waitting.remove(0);
			_grid.setBlock(index);
			//获取周围格子信息
			List<Grid> rounds = MapUtils.getRoundGrid(_grid, mapInfos);
			
			for (int i = 0; i < rounds.size(); i++) {
				Grid round = rounds.get(i);
				
				//在地图内是不可行走点
				if(round.getBlock() != -1){
					continue;
				}
				
				else if(counted.contains(round.getKey())){
					continue;
				}
				//斜边判断相邻方向是否都为阻挡
				else if(round.getY()!=_grid.getY() && round.getX()!=_grid.getX()){
					if(mapInfos[round.getY()][_grid.getX()].getBlock() != 0 && mapInfos[_grid.getY()][round.getX()].getBlock() != 0){
						continue;
					}
				}
				//插入到待计算列表
				waitting.add(round);
				counted.add(round.getKey());
			}
		}
	}
	
	public static void main(String[] args) {
		Q_mapBean bean = ManagerPool.dataManager.q_mapContainer.getMap().get(20001);
		ManagerPool.mapManager.initMapBlock(bean);
	}
}
