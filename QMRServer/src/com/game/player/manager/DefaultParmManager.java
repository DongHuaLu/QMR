//package com.game.player.manager;
//
//import java.util.List;
//
//import com.game.backpack.manager.BackpackManager;
//import com.game.backpack.structs.Equip;
//import com.game.backpack.structs.Item;
//import com.game.config.Config;
//import com.game.data.bean.Q_characterBean;
//import com.game.data.bean.Q_newrole_defaultvalueBean;
//import com.game.data.bean.Q_skill_modelBean;
//import com.game.data.manager.DataManager;
//import com.game.manager.ManagerPool;
//import com.game.player.message.ReqSyncPlayerLevelMessage;
//import com.game.player.message.ResPlayerLevelUpMessage;
//import com.game.player.structs.Player;
//import com.game.player.structs.PlayerAttributeType;
//import com.game.shortcut.manager.ShortCutManager;
//import com.game.skill.manager.SkillManager;
//import com.game.skill.structs.Skill;
//import com.game.structs.Reasons;
//import com.game.task.manager.TaskManager;
//import com.game.utils.Global;
//import com.game.utils.MessageUtil;
//import com.game.utils.StringUtil;
//import com.game.utils.Symbol;
//
//public class DefaultParmManager {
//	public static void buildDefaultValue(Player player) {
//		try{
//			//出生等级
//			Q_newrole_defaultvalueBean model = DataManager.getInstance().q_newrole_defaultvalueContainer.getMap().get((int)player.getSex());
//			if(model==null){
//				return;
//			}
//			int q_initlevel = model.getQ_initlevel();
//			if(q_initlevel>1){
//				setLevel(player, q_initlevel);
//			}
//			//默认装备
//			addEquip(player,model.getQ_body1(),0);
//			addEquip(player,model.getQ_body2(),1);
//			addEquip(player,model.getQ_body3(),2);
//			addEquip(player,model.getQ_body4(),3);
//			addEquip(player,model.getQ_body5(),4);
//			addEquip(player,model.getQ_body6(),5);
//			addEquip(player,model.getQ_body7(),6);
//			addEquip(player,model.getQ_body8(),7);
//			addEquip(player,model.getQ_body9(),8);
//			
//			//包裹 物品
//			String q_bageitems = model.getQ_bageitems();
//			buildBagItems(player,q_bageitems);
//			
//			//默认学会技能
//			String q_skills = model.getQ_skills();
//			if(!StringUtil.isBlank(q_skills)){
//				String[] split = q_skills.split(Symbol.FENHAO_REG);
//				for (String string : split) {
//					if(!StringUtil.isBlank(string)){
//						String[] split2 = string.split(Symbol.DOUHAO_REG);
//						int modelId = Integer.parseInt(split2[0]);int level = Integer.parseInt(split2[1]);
//						SkillManager.getInstance().addSkill(player,modelId);
//						if(level>1){
//							Skill skill = SkillManager.getInstance().getSkillByModelId(player, modelId);
//							SkillManager.getInstance().endUpLevel(player, skill,level, true);	
//						}
//					}
//				}
//			}
//			//快捷键
//			String q_short_cut = model.getQ_short_cut();
//			buildShortCut(player, q_short_cut);	
//		}catch(Exception e){
////			log.error(e,e);
//		}
//	}
//
//	private static void setLevel(Player player,int level){
//		if (level > Global.MAX_LEVEL) {
//			return;
//		}
//		//设置等级
//		player.setLevel(level);
//
//		if (level == 30) {
//			//30级自动切换一次全体pk
//			PlayerManager.getInstance().changePkState(player, 3);
//			//30级自动接受日常任务
//			TaskManager.getInstance().acceptDailyTask(player);
//		}
//		
//		ReqSyncPlayerLevelMessage syncmsg= new ReqSyncPlayerLevelMessage();
//		syncmsg.setPlayerId(player.getId());
//		syncmsg.setLevel(level);
//		MessageUtil.send_to_world(syncmsg);
//		//升级自动学会的
//		SkillManager.getInstance().autoStudySkill(player);
//		
//		int level = player.getLevel();
//		Q_characterBean model = ManagerPool.dataManager.q_characterContainer.getMap().get(level);
//		if(model==null) return;
//		int q_skill = model.getQ_skill();
//		if(q_skill!=0){
//			Q_skill_modelBean skillModel= ManagerPool.dataManager.q_skill_modelContainer.getMap().get(q_skill+"_"+1);
//			if(skillModel!=null&&!SkillManager.getInstance().isHaveSkill(player, q_skill)){
//				SkillManager.getInstance().study(player, q_skill, skillModel.getQ_study_needbook());
//			}
//		}		
//		
//		
//		//升级触发军衔升级
//		ManagerPool.rankManager.rankup(player);
//		//重新计算属性
//		ManagerPool.playerAttributeManager.countPlayerAttribute(player, PlayerAttributeType.BASE);
//		player.setHp(player.getMaxHp());
//		player.setMp(player.getMaxMp());
//		player.setSp(player.getMaxSp());
//	}
//	
//	private static void addEquip(Player player, String q_body1, int i) {
//		//		装备1(模型ID,是否绑定,强化等级,属性类型|属性值;属性类型|属性值）
//		if(!StringUtil.isBlank(q_body1)){
//			String[] split = q_body1.split(Symbol.DOUHAO);
//			int modelId=Integer.parseInt(split[0]);
//			boolean isbind=split[1].equals("1");
//			int gradenum=Integer.parseInt(split[2]);
//			String append="";
//			if(split.length>4)
//			append=split[3];
//			List<Item> createItems = Item.createItems(modelId,1,isbind,0,gradenum, append);
//			Item item = createItems.get(0);
//			if (item instanceof Equip) {
//				player.getEquips()[i] = (Equip) item;
//			}
//		}
//	}
//	
//	private static void buildBagItems(Player player,String items){
//		if(!StringUtil.isBlank(items)){
////			包裹物品（模型ID,数量,是否绑定,强化等级,属性类型|属性值;属性类型|属性值:模型ID,数量,是否绑定,强化等级,属性类型|属性值;属性类型|属性值)
//			long id = Config.getId();
//			String[] split = items.split(Symbol.MAOHAO_REG);
//			for (String string : split) {
//				if(!StringUtil.isBlank(string)){
//					String[] itemparm = string.split(Symbol.DOUHAO_REG);
//					int modelId = Integer.parseInt(itemparm[0]);
//					int num = Integer.parseInt(itemparm[1]);
//					boolean isbind =itemparm[2].equals("1");
//					int gradenum=Integer.parseInt(itemparm[3]);
//					String append="";
//					if(itemparm.length>4){
//						append=itemparm[4];	
//					}
//					List<Item> createItems = Item.createItems(modelId, num, isbind, 0,gradenum,append);
//					BackpackManager.getInstance().addItems(player, createItems, Reasons.SYSTEM_GIFT, id);
//				}
//			}
//		}
//	}
//	
//	private static void buildShortCut(Player player,String cuts){
//		if(!StringUtil.isBlank(cuts)){
//			String[] split = cuts.split(Symbol.FENHAO_REG);
//			for (int i = 0; i < split.length; i++) {
//				String string = split[i];
//				int parseInt = Integer.parseInt(string);
//				Skill skillByModelId = SkillManager.getInstance().getSkillByModelId(player, parseInt);
//				if(skillByModelId!=null){
//					ShortCutManager.getInstance().addShortCut(player, 2, skillByModelId.getId(), parseInt, i+1);	
//				}
//			}	
//		}
//		
//	}
//
//}
