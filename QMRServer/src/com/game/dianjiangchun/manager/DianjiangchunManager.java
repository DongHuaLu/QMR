/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.game.dianjiangchun.manager;

import com.game.backpack.manager.BackpackManager;
import com.game.config.Config;
import com.game.data.manager.DataManager;
import com.game.dianjiangchun.bean.DianjiangchunInfo;
import com.game.dianjiangchun.message.SendDianjiangchunInfoToClientMessage;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.player.manager.PlayerManager;
import com.game.player.structs.Player;
import com.game.player.structs.AttributeChangeReason;
import com.game.prompt.structs.Notifys;
import com.game.structs.Reasons;
import com.game.task.manager.TaskManager;
import com.game.task.struts.RankTaskEnum;
import com.game.task.struts.Task;
import com.game.utils.CommonConfig;
import com.game.utils.MessageUtil;
import com.game.utils.RandomUtils;
import com.game.utils.TimeUtil;

/**
 *
 * @author Administrator
 */
public class DianjiangchunManager {

	//private Logger log = Logger.getLogger(DianjiangchunManager.class);
	private static Object obj = new Object();
	//管理类实例
	private static DianjiangchunManager manager;

	private DianjiangchunManager() {
		setS_btRmbCount((byte) DataManager.getInstance().q_globalContainer.getMap().get(CommonConfig.DIANJIANGCHUN_RMB_COUNT.getValue()).getQ_int_value());
		setS_btMaxUserCount((byte) DataManager.getInstance().q_globalContainer.getMap().get(CommonConfig.DIANJIANGCHUN_MAXCOUNT.getValue()).getQ_int_value());
		setS_btMaxFreeChangeLuckCount((byte) DataManager.getInstance().q_globalContainer.getMap().get(CommonConfig.DIANJIANGCHUN_FREECHANGELUCK_MAXCOUNT.getValue()).getQ_int_value());
	}

	public static DianjiangchunManager getInstance() {
		synchronized (obj) {
			if (manager == null) {
				manager = new DianjiangchunManager();
			}
		}
		return manager;
	}
	private static byte s_btRmbCount = 10;			//点绛唇默认消耗元宝数
	private static byte s_btMaxUserCount = 20;		//点绛唇默认最大使用次数
	private static byte s_btMaxFreeChangeLuckCount = 10;	//点绛唇默认最大免费改运次数
	private static byte s_btChun = 0;			//香唇
	private static byte s_btMaxBosonValue = 5;		//色子最大值
	private static byte s_btMaxBosonCount = 6;		//色子最大数
	private static int s_nDefaultCof = 1666;		//默认色子几率
	private static int s_nChunZhenQiValue = 10000;		//香唇获得真气值
	private static int s_nBananaZhenQiValue = 300;		//香蕉获得真气值
	private static int s_nGrapesZhenQiValue = 250;		//葡萄获得真气值
	private static int s_nOrangeZhenQiValue = 200;		//橘子获得真气值
	private static int s_nMangoZhenQiValue = 150;		//芒果获得真气值
	private static int s_nCucumberZhenQiValue = 100;	//黄瓜获得真气值
	//private DianjiangchunInfo stSaveDianjiangchunInfo = new DianjiangchunInfo();	//点绛唇信息
	private static int Status_View = 0;		//查看
	private static int Status_Start = 1;		//开始
	private static int Status_Change = 2;		//改变
	private static int Status_Receive = 3;		//领取
	private static int Status_Clear = 4;		//清除

//	public DianjiangchunInfo getStSaveDianjiangchunInfo() {
//		return stSaveDianjiangchunInfo;
//	}
//
//	public void setStSaveDianjiangchunInfo(DianjiangchunInfo stSaveDianjiangchunInfo) {
//		this.stSaveDianjiangchunInfo = stSaveDianjiangchunInfo;
//	}

	public static byte getS_btRmbCount() {
		return s_btRmbCount;
	}

	public static void setS_btRmbCount(byte s_btRmbCount) {
		DianjiangchunManager.s_btRmbCount = s_btRmbCount;
	}

	public static byte getS_btMaxFreeChangeLuckCount() {
		return s_btMaxFreeChangeLuckCount;
	}

	public static void setS_btMaxFreeChangeLuckCount(byte s_btMaxFreeChangeLuckCount) {
		DianjiangchunManager.s_btMaxFreeChangeLuckCount = s_btMaxFreeChangeLuckCount;
	}

	public static byte getS_btMaxUserCount() {
		return s_btMaxUserCount;
	}

	public static void setS_btMaxUserCount(byte s_btMaxUserCount) {
		DianjiangchunManager.s_btMaxUserCount = s_btMaxUserCount;
	}

	public static int getS_nChunZhenQiValue() {
		if (DataManager.getInstance().q_globalContainer.getMap().containsKey(CommonConfig.DIANJIANGCHUN_CHUN.getValue())) {
			return DataManager.getInstance().q_globalContainer.getMap().get(CommonConfig.DIANJIANGCHUN_CHUN.getValue()).getQ_int_value();
		}
		return s_nChunZhenQiValue;
	}

	public static int getS_nBananaZhenQiValue() {
		if (DataManager.getInstance().q_globalContainer.getMap().containsKey(CommonConfig.DIANJIANGCHUN_XIANGJIAO.getValue())) {
			return DataManager.getInstance().q_globalContainer.getMap().get(CommonConfig.DIANJIANGCHUN_XIANGJIAO.getValue()).getQ_int_value();
		}
		return s_nBananaZhenQiValue;
	}

	public static int getS_nCucumberZhenQiValue() {
		if (DataManager.getInstance().q_globalContainer.getMap().containsKey(CommonConfig.DIANJIANGCHUN_HUANGGUA.getValue())) {
			return DataManager.getInstance().q_globalContainer.getMap().get(CommonConfig.DIANJIANGCHUN_HUANGGUA.getValue()).getQ_int_value();
		}
		return s_nCucumberZhenQiValue;
	}

	public static int getS_nGrapesZhenQiValue() {
		if (DataManager.getInstance().q_globalContainer.getMap().containsKey(CommonConfig.DIANJIANGCHUN_PUTOU.getValue())) {
			return DataManager.getInstance().q_globalContainer.getMap().get(CommonConfig.DIANJIANGCHUN_PUTOU.getValue()).getQ_int_value();
		}
		return s_nGrapesZhenQiValue;
	}

	public static int getS_nMangoZhenQiValue() {
		if (DataManager.getInstance().q_globalContainer.getMap().containsKey(CommonConfig.DIANJIANGCHUN_MANGGUO.getValue())) {
			return DataManager.getInstance().q_globalContainer.getMap().get(CommonConfig.DIANJIANGCHUN_MANGGUO.getValue()).getQ_int_value();
		}
		return s_nMangoZhenQiValue;
	}

	public static int getS_nOrangeZhenQiValue() {
		if (DataManager.getInstance().q_globalContainer.getMap().containsKey(CommonConfig.DIANJIANGCHUN_JUZI.getValue())) {
			return DataManager.getInstance().q_globalContainer.getMap().get(CommonConfig.DIANJIANGCHUN_JUZI.getValue()).getQ_int_value();
		}
		return s_nOrangeZhenQiValue;
	}

	public static int getS_nDefaultCof() {
		return s_nDefaultCof;
	}

	public static byte getS_btChun() {
		return s_btChun;
	}

	public static byte getS_btMaxBosonCount() {
		return s_btMaxBosonCount;
	}

	public static byte getS_btMaxBosonValue() {
		return s_btMaxBosonValue;
	}

	/**
	 * 判断是否达到最大允许次数
	 */
	public boolean isMaxCount(Player vPlayer) {
		if (vPlayer.getStDianjiangchunSaveData().getBtUsecount() < getS_btMaxUserCount()) {
			return false;
		}
		return true;
	}

	/**
	 * 判断是否满足免费改运
	 */
	public boolean isNoFreeChangeLuck(Player vPlayer) {
		if (vPlayer.getStDianjiangchunSaveData().getBtFreechangeluckcount() < getS_btMaxFreeChangeLuckCount()) {
			return false;
		}
		return true;
	}

	/**
	 * 判断元宝是否足够
	 */
	public boolean isRmbEnough(Player vPlayer) {
		if (BackpackManager.getInstance().checkGold(vPlayer, getS_btRmbCount())) {
			return BackpackManager.getInstance().changeGold(vPlayer, -getS_btRmbCount(), Reasons.DIANJIANGCHUNCHANGLUCK, Config.getId());
		}
		return false;
	}

	/**
	 * 获得每个色子几率
	 */
	public int GetBosonCof(int vBosonIdx) {
		switch (vBosonIdx) {
			case 0:
				return DataManager.getInstance().q_globalContainer.getMap().get(CommonConfig.DIANJIANGCHUN_COF1.getValue()).getQ_int_value();
			case 1:
				return DataManager.getInstance().q_globalContainer.getMap().get(CommonConfig.DIANJIANGCHUN_COF2.getValue()).getQ_int_value();
			case 2:
				return DataManager.getInstance().q_globalContainer.getMap().get(CommonConfig.DIANJIANGCHUN_COF3.getValue()).getQ_int_value();
			case 3:
				return DataManager.getInstance().q_globalContainer.getMap().get(CommonConfig.DIANJIANGCHUN_COF4.getValue()).getQ_int_value();
			case 4:
				return DataManager.getInstance().q_globalContainer.getMap().get(CommonConfig.DIANJIANGCHUN_COF5.getValue()).getQ_int_value();
			case 5:
				return DataManager.getInstance().q_globalContainer.getMap().get(CommonConfig.DIANJIANGCHUN_COF6.getValue()).getQ_int_value();
			default:
				return getS_nDefaultCof();
		}
	}

	/**
	 * 随即获得色子值
	 */
	public int RandomGetBoson(int vBosonIdx) {
		if (RandomUtils.randomIntValue(0, 10000) < GetBosonCof(vBosonIdx)) {
			return getS_btChun();
		} else {
			return RandomUtils.randomIntValue(getS_btChun() + 1, getS_btMaxBosonValue());
		}
	}

	/**
	 * 计算真气值
	 */
	public int CalInfuriatingvalue(Player vPlayer) {
		int result = 0;
		for (int i = s_btChun; i <= s_btMaxBosonValue; i++) {
			switch (i) {
				case 0: {
					result = result + GetChunCount(vPlayer, i) * getS_nChunZhenQiValue();
				}
				break;
				case 1: {
					result = result + GetChunCount(vPlayer, i) * getS_nBananaZhenQiValue();
				}
				break;
				case 2: {
					result = result + GetChunCount(vPlayer, i) * getS_nGrapesZhenQiValue();
				}
				break;
				case 3: {
					result = result + GetChunCount(vPlayer, i) * getS_nOrangeZhenQiValue();
				}
				break;
				case 4: {
					result = result + GetChunCount(vPlayer, i) * getS_nMangoZhenQiValue();
				}
				break;
				case 5: {
					result = result + GetChunCount(vPlayer, i) * getS_nCucumberZhenQiValue();
				}
				break;
				default: {
					result = result + 0;
				}
				break;
			}
		}
		return result;
	}

	/**
	 * 获得色子香唇数目
	 */
	public int GetChunCount(Player vPlayer, int BosonType) {
		int ret = 0;
		for (int i = 0; i < vPlayer.getStDianjiangchunSaveData().getBosonList().size(); i++) {
			int btValue = vPlayer.getStDianjiangchunSaveData().getBosonList().get(i);
			if (btValue == BosonType) {
				ret++;
			}
		}
		return ret;
	}

	/**
	 * 获得每个色子是否都是香唇
	 */
	public boolean isAllChun(Player vPlayer) {
		for (int i = 0; i < vPlayer.getStDianjiangchunSaveData().getBosonList().size(); i++) {
			int btValue = vPlayer.getStDianjiangchunSaveData().getBosonList().get(i);
			if (btValue != getS_btChun()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 点绛唇数据是否在同一天
	 */
	public boolean isDianjiangchunSameDay(Player vPlayer) {
		if (TimeUtil.isSameDay(vPlayer.getLgDianjiangchunDay(), System.currentTimeMillis())) {
			return true;
		} else {
			ClearDianjiangchunInfo(vPlayer);
		}
		return false;
	}

	/**
	 * 玩家开始点绛唇
	 */
	public void GetBeginDianjiangchun(Player vPlayer) {
		if (vPlayer.getLevel() < 42) {
			MessageUtil.notify_player(vPlayer, Notifys.MOUSEPOS, ResManager.getInstance().getString("42级以后可以使用此功能"));
			return;
		}
		
		isDianjiangchunSameDay(vPlayer);
		if (!isMaxCount(vPlayer)) {
			if (vPlayer.getStDianjiangchunSaveData().getnInfuriatingvalue() == 0) {
				vPlayer.getStDianjiangchunSaveData().getBosonList().clear();
				for (int i = 0; i < getS_btMaxBosonCount(); i++) {
					vPlayer.getStDianjiangchunSaveData().getBosonList().add(RandomGetBoson(i));
				}
				vPlayer.getStDianjiangchunSaveData().setnInfuriatingvalue(vPlayer.getStDianjiangchunSaveData().getnInfuriatingvalue() + CalInfuriatingvalue(vPlayer));
				vPlayer.getStDianjiangchunSaveData().setBtUsecount((byte) (vPlayer.getStDianjiangchunSaveData().getBtUsecount() + 1));
				SendDianjiangchunInfo(vPlayer, Status_Start);
			} else {
				MessageUtil.notify_player(vPlayer, Notifys.ERROR, ResManager.getInstance().getString("您的真气还没有领取，请领取后再开始点绛唇"));
			}
		} else {
			MessageUtil.notify_player(vPlayer, Notifys.ERROR, ResManager.getInstance().getString("今日点绛唇次数使用完毕"));
		}
	}

	/**
	 * 执行改运
	 */
	public boolean ExecuteChangeLuck(Player vPlayer) {
		if (!isAllChun(vPlayer)) {
			vPlayer.getStDianjiangchunSaveData().setnInfuriatingvalue(vPlayer.getStDianjiangchunSaveData().getnInfuriatingvalue() - CalInfuriatingvalue(vPlayer));
			for (int i = 0; i < vPlayer.getStDianjiangchunSaveData().getBosonList().size(); i++) {
				int btValue = vPlayer.getStDianjiangchunSaveData().getBosonList().get(i);
				if (btValue != getS_btChun()) {
					vPlayer.getStDianjiangchunSaveData().getBosonList().set(i, RandomGetBoson(i));
				}
			}
			vPlayer.getStDianjiangchunSaveData().setnInfuriatingvalue(vPlayer.getStDianjiangchunSaveData().getnInfuriatingvalue() + CalInfuriatingvalue(vPlayer));
			if (!isNoFreeChangeLuck(vPlayer)) {
				vPlayer.getStDianjiangchunSaveData().setBtFreechangeluckcount((byte) (vPlayer.getStDianjiangchunSaveData().getBtFreechangeluckcount() + 1));
			}
			if (isAllChun(vPlayer)) {
				MessageUtil.notify_player(vPlayer, Notifys.SUCCESS, ResManager.getInstance().getString("恭喜您累积满{1}个香唇，获得海量真气"), String.valueOf(getS_btMaxBosonCount()));
				MessageUtil.notify_All_player(Notifys.CUTOUT, ResManager.getInstance().getString("恭喜玩家【{1}】获得{2}个香唇，获得海量真气，真是羡煞旁人"), vPlayer.getName(), String.valueOf(getS_btMaxBosonCount()));
//				MessageUtil.boardcastWorld("恭喜玩家【{1}】获得{2}个香唇，获得海量真气，真是羡煞旁人", vPlayer.getName(), String.valueOf(getS_btMaxBosonCount()));
			}
			return true;
		} else {
			MessageUtil.notify_player(vPlayer, Notifys.NORMAL, ResManager.getInstance().getString("您已经累积满{1}个香唇，可以获得海量真气"), String.valueOf(getS_btMaxBosonCount()));
		}
		return false;
	}

	/**
	 * 玩家申请改运
	 */
	public void GetChangeLuck(Player vPlayer) {
		isDianjiangchunSameDay(vPlayer);
//		if (!isMaxCount(vPlayer)) {
		if (!isAllChun(vPlayer)) {
			if (!isNoFreeChangeLuck(vPlayer)) {
				if (ExecuteChangeLuck(vPlayer)) {
					MessageUtil.notify_player(vPlayer, Notifys.SUCCESS, ResManager.getInstance().getString("免费改运成功，当前剩余{1}次免费机会"),
						String.valueOf(getS_btMaxFreeChangeLuckCount() - vPlayer.getStDianjiangchunSaveData().getBtFreechangeluckcount()));
					SendDianjiangchunInfo(vPlayer, Status_Change);
				}
			} else {
				if (isRmbEnough(vPlayer)) {
					if (ExecuteChangeLuck(vPlayer)) {
						MessageUtil.notify_player(vPlayer, Notifys.SUCCESS, ResManager.getInstance().getString("花费{1}元宝，逆天改运成功"), String.valueOf(getS_btRmbCount()));
						SendDianjiangchunInfo(vPlayer, Status_Change);
					}
				} else {
					MessageUtil.notify_player(vPlayer, Notifys.ERROR, ResManager.getInstance().getString("对不起，您当前元宝不足，无法逆天改运！是否前往充值"));
					//TODO 客户端提示弹窗
				}
			}
		} else {
			MessageUtil.notify_player(vPlayer, Notifys.NORMAL, ResManager.getInstance().getString("您已经累积满{1}个香唇，可以获得海量真气"), String.valueOf(getS_btMaxBosonCount()));
		}
//		} else {
//			MessageUtil.notify_player(vPlayer, Notifys.ERROR, "今日点绛唇次数使用完毕");
//		}
	}

	/**
	 * 玩家获得点绛唇数据
	 */
	public void GetDianjiangchunInfo(Player vPlayer) {
		isDianjiangchunSameDay(vPlayer);
		SendDianjiangchunInfo(vPlayer, Status_View);
	}

	/**
	 * 玩家领取点绛唇真气值
	 */
	public void GetReceiveintinfuriatingvalue(Player vPlayer) {
		isDianjiangchunSameDay(vPlayer);
		if (vPlayer.getStDianjiangchunSaveData().getnInfuriatingvalue() != 0) {
			int max = ManagerPool.dataManager.q_globalContainer.getMap().get(CommonConfig.ZHENGQI_MAX.getValue()).getQ_int_value();
			if (vPlayer.getZhenqi() + vPlayer.getStDianjiangchunSaveData().getnInfuriatingvalue() > max) {
				MessageUtil.notify_player(vPlayer, Notifys.ERROR, ResManager.getInstance().getString("提取真气后将超过上限，请消耗一些真气后再提取"));
				return;
			}
			int nOldZhenqi = vPlayer.getZhenqi();
			PlayerManager.getInstance().addZhenqi(vPlayer, vPlayer.getStDianjiangchunSaveData().getnInfuriatingvalue(),AttributeChangeReason.DIANJIANGCHUN);
			int nAddZhenqi = vPlayer.getZhenqi() - nOldZhenqi;
			vPlayer.getStDianjiangchunSaveData().setnInfuriatingvalue(vPlayer.getStDianjiangchunSaveData().getnInfuriatingvalue() - nAddZhenqi);
			if (vPlayer.getStDianjiangchunSaveData().getnInfuriatingvalue() < 0) {
				vPlayer.getStDianjiangchunSaveData().setnInfuriatingvalue(0);
			}
			if (vPlayer.getStDianjiangchunSaveData().getnInfuriatingvalue() == 0) {
				TaskManager.getInstance().action(vPlayer, Task.ACTION_TYPE_RANK, RankTaskEnum.INCENSELIP, GetChunCount(vPlayer, getS_btChun()));
				vPlayer.getStDianjiangchunSaveData().getBosonList().clear();
			}
			vPlayer.getStDianjiangchunSaveData().setnReceiveintinfuriatingvalue(vPlayer.getStDianjiangchunSaveData().getnReceiveintinfuriatingvalue() + nAddZhenqi);
			SendDianjiangchunInfo(vPlayer, Status_Receive);
		} else {
			MessageUtil.notify_player(vPlayer, Notifys.ERROR, ResManager.getInstance().getString("您的点绛唇真气值为零"));
		}
	}

	/**
	 * 服务器发送点绛唇数据给玩家
	 *
	 * @param status the value of status
	 */
	public void SendDianjiangchunInfo(Player vPlayer, int status) {
		SendDianjiangchunInfoToClientMessage msInfoToClientMessage = new SendDianjiangchunInfoToClientMessage();
		DianjiangchunInfo saveDianjiangchunInfo = new DianjiangchunInfo();	//点绛唇信息
		saveDianjiangchunInfo.setStatus(status);
		saveDianjiangchunInfo.setBtMaxcount(getS_btMaxUserCount());
		saveDianjiangchunInfo.setBtFreechangeluckMaxcount(getS_btMaxFreeChangeLuckCount());
		vPlayer.getStDianjiangchunSaveData().LoadInfo(saveDianjiangchunInfo);
		msInfoToClientMessage.setStCurInfo(saveDianjiangchunInfo);
		MessageUtil.tell_player_message(vPlayer, msInfoToClientMessage);
		PlayerManager.getInstance().savePlayer(vPlayer);
	}

	/**
	 * 服务器清理玩家点绛唇数据
	 */
	public void ClearDianjiangchunInfo(Player vPlayer) {
		vPlayer.setLgDianjiangchunDay(System.currentTimeMillis());
		vPlayer.getStDianjiangchunSaveData().setBtFreechangeluckcount((byte) 0);
		vPlayer.getStDianjiangchunSaveData().setBtUsecount((byte) 0);
		vPlayer.getStDianjiangchunSaveData().setnInfuriatingvalue(0);
		vPlayer.getStDianjiangchunSaveData().setnReceiveintinfuriatingvalue(0);
		vPlayer.getStDianjiangchunSaveData().getBosonList().clear();
		SendDianjiangchunInfo(vPlayer, Status_Clear);
	}
}
