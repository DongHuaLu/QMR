package com.game.amulet.bean{
	import com.game.amulet.bean.AmuletSkillInfo;
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 护身符信息
	 */
	public class AmuletInfo extends Bean {
	
		//配偶的护身符model
		private var _spouseModel: int;
		
		//护身符model
		private var _model: int;
		
		//剩余过期时间
		private var _time: int;
		
		//当前祝福值
		private var _bless: int;
		
		//清空剩余时间
		private var _clearTime: int;
		
		//技能
		private var _skills: Vector.<AmuletSkillInfo> = new Vector.<AmuletSkillInfo>();
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//配偶的护身符model
			writeInt(_spouseModel);
			//护身符model
			writeInt(_model);
			//剩余过期时间
			writeInt(_time);
			//当前祝福值
			writeInt(_bless);
			//清空剩余时间
			writeInt(_clearTime);
			//技能
			writeShort(_skills.length);
			for (var i: int = 0; i < _skills.length; i++) {
				writeBean(_skills[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//配偶的护身符model
			_spouseModel = readInt();
			//护身符model
			_model = readInt();
			//剩余过期时间
			_time = readInt();
			//当前祝福值
			_bless = readInt();
			//清空剩余时间
			_clearTime = readInt();
			//技能
			var skills_length : int = readShort();
			for (var i: int = 0; i < skills_length; i++) {
				_skills[i] = readBean(AmuletSkillInfo) as AmuletSkillInfo;
			}
			return true;
		}
		
		/**
		 * get 配偶的护身符model
		 * @return 
		 */
		public function get spouseModel(): int{
			return _spouseModel;
		}
		
		/**
		 * set 配偶的护身符model
		 */
		public function set spouseModel(value: int): void{
			this._spouseModel = value;
		}
		
		/**
		 * get 护身符model
		 * @return 
		 */
		public function get model(): int{
			return _model;
		}
		
		/**
		 * set 护身符model
		 */
		public function set model(value: int): void{
			this._model = value;
		}
		
		/**
		 * get 剩余过期时间
		 * @return 
		 */
		public function get time(): int{
			return _time;
		}
		
		/**
		 * set 剩余过期时间
		 */
		public function set time(value: int): void{
			this._time = value;
		}
		
		/**
		 * get 当前祝福值
		 * @return 
		 */
		public function get bless(): int{
			return _bless;
		}
		
		/**
		 * set 当前祝福值
		 */
		public function set bless(value: int): void{
			this._bless = value;
		}
		
		/**
		 * get 清空剩余时间
		 * @return 
		 */
		public function get clearTime(): int{
			return _clearTime;
		}
		
		/**
		 * set 清空剩余时间
		 */
		public function set clearTime(value: int): void{
			this._clearTime = value;
		}
		
		/**
		 * get 技能
		 * @return 
		 */
		public function get skills(): Vector.<AmuletSkillInfo>{
			return _skills;
		}
		
		/**
		 * set 技能
		 */
		public function set skills(value: Vector.<AmuletSkillInfo>): void{
			this._skills = value;
		}
		
	}
}