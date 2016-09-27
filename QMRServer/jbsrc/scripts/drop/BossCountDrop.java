package scripts.drop;

import java.util.ArrayList;
import java.util.List;

import com.game.backpack.structs.Item;
import com.game.drop.structs.MapDropInfo;
import com.game.fight.structs.Fighter;
import com.game.manager.ManagerPool;
import com.game.map.bean.DropGoodsInfo;
import com.game.monster.script.IMonsterDieDropScript;
import com.game.monster.structs.Monster;
import com.game.player.structs.Player;
import com.game.server.impl.WServer;
import com.game.utils.RandomUtils;
import com.game.utils.TimeUtil;

/**BOSS额外计数掉落道具
 * 
 * @author zhangrong
 *
 */
public class BossCountDrop implements IMonsterDieDropScript{

	@Override
	public int getId() {
		return 7002;
	}
	
	int bosscount = 0;
	int[] bossids = {391,441,541,581,631,681};
	int bossday = 0;
	int dropnum = 0;
	List<Integer> bossidlist=new ArrayList<Integer>();
	
	int[][] itemids={
			//ID，几率
			{9136,2200},
			{9137,2200},
			{9138,300},
			{9139,300},
			{9140,2200},
			{9141,2200},
	};
	
	
	public BossCountDrop(){
		for (int i = 0; i < bossids.length; i++) {
			bossidlist.add(bossids[i]);
		}
	}
	
	
	@Override
	public void onMonsterDie(Monster monster, Fighter killer) {
		if (killer instanceof Player) {
			if (bossidlist.contains(monster.getModelId())) {
				if (Math.abs(killer.getLevel() - monster.getLevel()) > 20 ) {
					return;
				}
				String platform = WServer.getInstance().getServerWeb();
				if (platform.equals("mlxyrms") || platform.equals("twgmei")) {	//马来西亚和台湾不执行
					return;
				}
				
				int day = TimeUtil.GetSeriesDay();
				if (bossday != day) {//初始化
					bossday = day ;
					bosscount = 0;
					dropnum = 0;
				}
				bosscount =bosscount+1;
				if (dropnum >= 4) {	//每天掉落上限
					return;
				}
				if (bosscount%10 == 0) {
					List<Integer> ranlist  =new ArrayList<Integer>();
					for (int i = 0; i < itemids.length; i++) {
						ranlist.add(itemids[i][1]);
					}
					int idx = RandomUtils.randomIndexByProb(ranlist);
					List<Item> items = Item.createItems(itemids[idx][0], 1, true,0);
					if (!items.isEmpty()) {
						Item item = items.get(0);
						item.setGridId(0);
						DropGoodsInfo info = new DropGoodsInfo();
						info.setDropGoodsId(item.getId());
						info.setItemModelId(item.getItemModelId());
						info.setNum(item.getNum());
						info.setX(monster.getPosition().getX());
						info.setY(monster.getPosition().getY());
						info.setDropTime(System.currentTimeMillis());
						MapDropInfo mapDropInfo = new MapDropInfo(info, item, ManagerPool.mapManager.getMap(monster), System.currentTimeMillis() + 60 * 5 * 1000);
						ManagerPool.mapManager.enterMap(mapDropInfo);
						dropnum = dropnum + 1;
					}
				}
			}
		}
	}

	
}
