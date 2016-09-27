package com.game.skill.bean{
	import com.game.skill.bean.SkillLevelInfo;
	import com.game.utils.long;
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 技能信息类
	 */
	public class SkillInfo extends Bean {
	
		//技能Id
		private var _skillId: long;
		
		//技能模板Id
		private var _skillModelId: int;
		
		//技能等级
		private var _skillLevel: int;
		
		//加成等级
		private var _skillAddLevels: Vector.<SkillLevelInfo> = new Vector.<SkillLevelInfo>();
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//技能Id
			writeLong(_skillId);
			//技能模板Id
			writeInt(_skillModelId);
			//技能等级
			writeInt(_skillLevel);
			//加成等级
			writeShort(_skillAddLevels.length);
			for (var i: int = 0; i < _skillAddLevels.length; i++) {
				writeBean(_skillAddLevels[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//技能Id
			_skillId = readLong();
			//技能模板Id
			_skillModelId = readInt();
			//技能等级
			_skillLevel = readInt();
			//加成等级
			var skillAddLevels_length : int = readShort();
			for (var i: int = 0; i < skillAddLevels_length; i++) {
				_skillAddLevels[i] = readBean(SkillLevelInfo) as SkillLevelInfo;
			}
			return true;
		}
		
		/**
		 * get 技能Id
		 * @return 
		 */
		public function get skillId(): long{
			return _skillId;
		}
		
		/**
		 * set 技能Id
		 */
		public function set skillId(value: long): void{
			this._skillId = value;
		}
		
		/**
		 * get 技能模板Id
		 * @return 
		 */
		public function get skillModelId(): int{
			return _skillModelId;
		}
		
		/**
		 * set 技能模板Id
		 */
		public function set skillModelId(value: int): void{
			this._skillModelId = value;
		}
		
		/**
		 * get 技能等级
		 * @return 
		 */
		public function get skillLevel(): int{
			return _skillLevel;
		}
		
		/**
		 * set 技能等级
		 */
		public function set skillLevel(value: int): void{
			this._skillLevel = value;
		}
		
		/**
		 * get 加成等级
		 * @return 
		 */
		public function get skillAddLevels(): Vector.<SkillLevelInfo>{
			return _skillAddLevels;
		}
		
		/**
		 * set 加成等级
		 */
		public function set skillAddLevels(value: Vector.<SkillLevelInfo>): void{
			this._skillAddLevels = value;
		}
		
	}
}