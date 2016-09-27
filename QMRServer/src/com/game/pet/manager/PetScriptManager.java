package com.game.pet.manager;

import java.util.HashMap;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.game.count.manager.CountManager;
import com.game.count.structs.CountTypes;
import com.game.data.bean.Q_petinfoBean;
import com.game.data.manager.DataManager;
import com.game.pet.message.ResPetChatMessage;
import com.game.pet.struts.Pet;
import com.game.player.manager.PlayerManager;
import com.game.player.structs.Person;
import com.game.player.structs.Player;
import com.game.skill.structs.Skill;
import com.game.utils.MessageUtil;
import com.game.utils.RandomUtils;
import com.game.utils.StringUtil;

/**
 * AI字符串配置 互动对话
 * @author 赵聪慧
 * @2012-8-31 下午9:38:18
 */
public class PetScriptManager {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(PetScriptManager.class);

	private static PetScriptManager instance=new PetScriptManager();
	public static PetScriptManager getInstance(){
		return instance;
	}
	public static final String CHAT_AI_PROB="发言万分比概率";
	public static final String CHAT_AI_SHOWPET="侍宠出战";
	public static final String CHAT_AI_USESKILL="侍宠施放特殊技能";
	public static final String CHAT_AI_ARTICULO="侍宠生命垂危";
	public static final String CHAT_AI_SHUANGXIU="玩家与侍宠双修";
	public static final String CHAT_AI_HIDEPET="侍宠休息";
	public static final String CHAT_AI_ONWER_FINSHTASK="主角完成任务";
	public static final String CHAT_AI_SHOWTIME_ONEHOUR="出战时间超过1小时";
	public static final String CHAT_AI_PET_KILLTARGET="侍宠击杀目标";
	public static final String CHAT_AI_ONWER_KILLTARGET="主角击杀目标";
	public static final String CHAT_AI_PET_FIRST_SHOW="侍宠第一次出战";
	public static final String CHAT_AI_PET_HUNDRED_SHOW="出战一百次";
	
	public void petChatAI(String key,Pet pet){
		try{
			Player player = PlayerManager.getInstance().getOnLinePlayer(pet.getOwnerId());
			JSONObject aiValueByAction = getAiValueByAction(key, pet);
			if(aiValueByAction==null||aiValueByAction.get(key)==null||player==null){
				return;
			}
			Object probObj= aiValueByAction.get(CHAT_AI_PROB);
			if(probObj==null){
				return;
			}
			int probability = Integer.parseInt(probObj.toString());
			if(RandomUtils.defaultIsGenerate(probability)){
				ResPetChatMessage msg=new ResPetChatMessage();
				msg.setPetId(pet.getId());
				msg.setSaycontent(aiValueByAction.get(key)+"");
				MessageUtil.tell_player_message(player,msg);
			}	
		}catch (Exception e) {
			logger.error(e,e);
		}
	}
	
//	
	/**
	 * 宠物出战
	 * @param pet
	 */
	public void showPet(Pet pet){
		petChatAI(CHAT_AI_SHOWPET, pet);
		Player player = PlayerManager.getInstance().getPlayer(pet.getOwnerId());
		if(player!=null){
			long count = CountManager.getInstance().getCount(player, CountTypes.PET_SHOW, pet.getModelId()+"");
			if(count==0){
				petChatAI(CHAT_AI_PET_FIRST_SHOW, pet);
			}
			if(count==100){
				petChatAI(CHAT_AI_PET_HUNDRED_SHOW, pet);
			}
			//超过一万次就不记了
			if(count<=10000){
				CountManager.getInstance().addCount(player, CountTypes.PET_SHOW,pet.getModelId()+"",CountManager.COUNT_PERSISTENT,1,0);
			}
		}
	}
	
	/**
	 * 宠物休息
	 * @param pet
	 */
	public void hidePet(Pet pet){
		pet.getTagSet().remove(CHAT_AI_SHOWTIME_ONEHOUR);
		petChatAI(CHAT_AI_HIDEPET, pet);
		//美人休息
		
	}
	
	/**
	 * 宠物血量变化
	 * @param pet
	 */
	public void hpChange(Pet pet){
		if(pet.getMaxHp()*0.3<=pet.getHp()&&!pet.getTagSet().contains(CHAT_AI_ARTICULO)){
			petChatAI(CHAT_AI_ARTICULO, pet);
		}else{
			pet.getTagSet().remove(CHAT_AI_ARTICULO);
		}
		//美人生命垂危
	}
	/**
	 * 宠物使用技能
	 * @param pet
	 * @param skill
	 */
	public void useSkill(Pet pet,Skill skill){
		//美人使用特殊技能
		if(skill.getSkillModelId()!=3){
			petChatAI(CHAT_AI_USESKILL, pet);	
		}
	}
	
	/**
	 * 宠物双修 
	 */
	public void petShuangXiu(Pet pet){
		//美人双修
		petChatAI(CHAT_AI_SHUANGXIU, pet);
	}
	
	/**
	 * 主人完成任务
	 * @param player
	 */
	public void finshTask(Player player){
		Pet showPet = PetInfoManager.getInstance().getShowPet(player);
		if(showPet!=null){
			petChatAI(CHAT_AI_ONWER_FINSHTASK, showPet);	
		}		
	}
	
	/**
	 * 宠物时间AI
	 * @param pet
	 */
	public void petTimmerAction(Pet pet){
		//出战时间超过1小时
		if(System.currentTimeMillis()-pet.getShowTime()>60*60*1000&&!pet.getTagSet().contains(CHAT_AI_SHOWTIME_ONEHOUR)){
			petChatAI(CHAT_AI_SHOWTIME_ONEHOUR, pet);
			pet.getTagSet().add(CHAT_AI_SHOWTIME_ONEHOUR);
		}
	}
	
	/**
	 * 宠物击杀目标
	 * @param pet
	 * @param persion
	 */
	public void petKillTarget(Pet pet,Person persion){
		//美人击杀目标
		petChatAI(CHAT_AI_PET_KILLTARGET, pet);
		
	}
	
	/**
	 * 玩家击杀目标
	 * @param player
	 * @param person
	 */
	public void playerKillTarget(Player player,Person person){
		//主人击杀目标
		Pet showPet = PetInfoManager.getInstance().getShowPet(player);
		if(showPet==null){
			return;
		}
		petChatAI(CHAT_AI_ONWER_KILLTARGET, showPet);
	}

	private JSONObject getAiValueByAction(String action,Pet pet){
		Q_petinfoBean model= DataManager.getInstance().q_petinfoContainer.getMap().get(pet.getModelId());
		String objString = model.getQ_chat_ai();
		if(StringUtil.isBlank(objString)){
			return null;
		}
		net.sf.json.JSONArray aiarray = net.sf.json.JSONArray.fromObject(objString);
		for (int i = 0; i < aiarray.size(); i++) {
			
			net.sf.json.JSONObject chatAI= aiarray.getJSONObject(i);
			if(chatAI.containsKey(action)){
				return chatAI;
			}
		}
		return null;
	}
	
	public static void main(String args[]){
		JSONArray fromObject = net.sf.json.JSONArray.fromObject("[{}]");
		JSONObject jsonObject = fromObject.getJSONObject(0);
		System.out.println(jsonObject.get("1")+"");
		
	}
	
	
}
