package com.game.guild.manager;

import java.util.HashMap;
import java.util.Map;

import com.game.data.bean.Q_buffBean;
import com.game.data.bean.Q_guildbannerBean;
import com.game.data.manager.DataManager;
import com.game.json.JSONserializable;
import com.game.player.structs.Player;
import com.game.player.structs.PlayerAttribute;
import com.game.player.structs.PlayerAttributeCalculator;
import com.game.player.structs.PlayerAttributeType;
import com.game.utils.CommonString;
import com.game.utils.StringUtil;

/**
 * 帮旗属性计算器
 *
 * @author 杨洪岚
 */
public class GuildBannerAttributeCalculator implements PlayerAttributeCalculator {

	@Override
	public int getType() {
		return PlayerAttributeType.GUILDBANNER;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PlayerAttribute getPlayerAttribute(Player player) {
		PlayerAttribute patt = new PlayerAttribute();
		if (player.getGuildId() > 0 && player.getGuildInfo().getBannerLevel() != 0) {
			Q_guildbannerBean q_guildbannerBean = DataManager.getInstance().q_guildbannerContainer.getMap().get((int) player.getGuildInfo().getBannerLevel());
			if (q_guildbannerBean != null) {
				Q_buffBean q_buffBean = DataManager.getInstance().q_buffContainer.getMap().get(q_guildbannerBean.getBuffid());
				if (q_buffBean != null) {
					HashMap<String, Integer> powers = null;
					powers = (HashMap<String, Integer>) JSONserializable.toObject(StringUtil.formatToJson(q_buffBean.getQ_Bonus_skill()), HashMap.class);
					if (powers != null) {
						for (Map.Entry<String, Integer> entry : powers.entrySet()) {
							String attString = entry.getKey();
							Integer attInteger = entry.getValue();
							if (attString.equalsIgnoreCase(CommonString.ATTACK)) {
								patt.setAttack(attInteger);
							} else if (attString.equalsIgnoreCase(CommonString.ATTACKPERCENT)) {
								patt.setAttackPercent(attInteger);
							} else if (attString.equalsIgnoreCase(CommonString.DEFENSE)) {
								patt.setDefense(attInteger);
							} else if (attString.equalsIgnoreCase(CommonString.DEFENSEPERCENT)) {
								patt.setDefensePercent(attInteger);
							} else if (attString.equalsIgnoreCase(CommonString.CRIT)) {
								patt.setCrit(attInteger);
							} else if (attString.equalsIgnoreCase(CommonString.CRITPERCENT)) {
								patt.setCritPercent(attInteger);
							} else if (attString.equalsIgnoreCase(CommonString.DODGE)) {
								patt.setDodge(attInteger);
							} else if (attString.equalsIgnoreCase(CommonString.DODGEPERCENT)) {
								patt.setDodgePercent(attInteger);
							} else if (attString.equalsIgnoreCase(CommonString.MAXHP)) {
								patt.setMaxHp(attInteger);
							} else if (attString.equalsIgnoreCase(CommonString.MAXHPPERCENT)) {
								patt.setMaxHpPercent(attInteger);
							} else if (attString.equalsIgnoreCase(CommonString.MAXMP)) {
								patt.setMaxMp(attInteger);
							} else if (attString.equalsIgnoreCase(CommonString.MAXMPPERCENT)) {
								patt.setMaxMpPercent(attInteger);
							} else if (attString.equalsIgnoreCase(CommonString.MAXSP)) {
								patt.setMaxSp(attInteger);
							} else if (attString.equalsIgnoreCase(CommonString.MAXSPPERCENT)) {
								patt.setMaxSpPercent(attInteger);
							} else if (attString.equalsIgnoreCase(CommonString.ATTACKSPEED)) {
								patt.setAttackSpeed(attInteger);
							} else if (attString.equalsIgnoreCase(CommonString.ATTACKSPEEDPERCENT)) {
								patt.setAttackSpeedPercent(attInteger);
							} else if (attString.equalsIgnoreCase(CommonString.SPEED)) {
								patt.setSpeed(attInteger);
							} else if (attString.equalsIgnoreCase(CommonString.SPEEDPERCENT)) {
								patt.setSpeedPercent(attInteger);
							} else if (attString.equalsIgnoreCase(CommonString.LUCK)) {
								patt.setLuck(attInteger);
							} else if (attString.equalsIgnoreCase(CommonString.LUCKPERCENT)) {
								patt.setLuckPercent(attInteger);
							}
						}
					}
				}
			}
		}
		return patt;
	}
}
