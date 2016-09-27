package com.game.horse.bean{
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 坐骑单个技能信息
	 */
	public class HorseSkillInfo extends Bean {
	
		//技能等级
		private var _skilllevel: int;
		
		//技能模板Id
		private var _skillmodelid: int;
		
		//技能经验
		private var _skillexp: int;
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//技能等级
			writeShort(_skilllevel);
			//技能模板Id
			writeInt(_skillmodelid);
			//技能经验
			writeInt(_skillexp);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//技能等级
			_skilllevel = readShort();
			//技能模板Id
			_skillmodelid = readInt();
			//技能经验
			_skillexp = readInt();
			return true;
		}
		
		/**
		 * get 技能等级
		 * @return 
		 */
		public function get skilllevel(): int{
			return _skilllevel;
		}
		
		/**
		 * set 技能等级
		 */
		public function set skilllevel(value: int): void{
			this._skilllevel = value;
		}
		
		/**
		 * get 技能模板Id
		 * @return 
		 */
		public function get skillmodelid(): int{
			return _skillmodelid;
		}
		
		/**
		 * set 技能模板Id
		 */
		public function set skillmodelid(value: int): void{
			this._skillmodelid = value;
		}
		
		/**
		 * get 技能经验
		 * @return 
		 */
		public function get skillexp(): int{
			return _skillexp;
		}
		
		/**
		 * set 技能经验
		 */
		public function set skillexp(value: int): void{
			this._skillexp = value;
		}
		
	}
}