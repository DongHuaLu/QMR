package com.game.pet.message{
	import com.game.skill.bean.SkillInfo;
	import com.game.utils.long;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 宠物技能变更
	 */
	public class ResPetSkillChangeMessage extends Message {
	
		//宠物Id
		private var _petId: long;
		
		//技能列表
		private var _skillInfos: Vector.<com.game.skill.bean.SkillInfo> = new Vector.<com.game.skill.bean.SkillInfo>();
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//宠物Id
			writeLong(_petId);
			//技能列表
			writeShort(_skillInfos.length);
			for (i = 0; i < _skillInfos.length; i++) {
				writeBean(_skillInfos[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			var i: int = 0;
			//宠物Id
			_petId = readLong();
			//技能列表
			var skillInfos_length : int = readShort();
			for (i = 0; i < skillInfos_length; i++) {
				_skillInfos[i] = readBean(com.game.skill.bean.SkillInfo) as com.game.skill.bean.SkillInfo;
			}
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 110110;
		}
		
		/**
		 * get 宠物Id
		 * @return 
		 */
		public function get petId(): long{
			return _petId;
		}
		
		/**
		 * set 宠物Id
		 */
		public function set petId(value: long): void{
			this._petId = value;
		}
		
		/**
		 * get 技能列表
		 * @return 
		 */
		public function get skillInfos(): Vector.<com.game.skill.bean.SkillInfo>{
			return _skillInfos;
		}
		
		/**
		 * set 技能列表
		 */
		public function set skillInfos(value: Vector.<com.game.skill.bean.SkillInfo>): void{
			this._skillInfos = value;
		}
		
	}
}