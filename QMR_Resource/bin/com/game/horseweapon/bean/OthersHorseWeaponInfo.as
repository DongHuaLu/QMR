package com.game.horseweapon.bean{
	import com.game.horseweapon.bean.HorseWeaponSkillInfo;
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 查看他人骑乘兵器详细信息
	 */
	public class OthersHorseWeaponInfo extends Bean {
	
		//当前最高骑乘兵器阶层
		private var _layer: int;
		
		//当前使用的骑乘兵器阶层
		private var _curlayer: int;
		
		//技能
		private var _skills: Vector.<HorseWeaponSkillInfo> = new Vector.<HorseWeaponSkillInfo>();
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//当前最高骑乘兵器阶层
			writeShort(_layer);
			//当前使用的骑乘兵器阶层
			writeShort(_curlayer);
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
			//当前最高骑乘兵器阶层
			_layer = readShort();
			//当前使用的骑乘兵器阶层
			_curlayer = readShort();
			//技能
			var skills_length : int = readShort();
			for (var i: int = 0; i < skills_length; i++) {
				_skills[i] = readBean(HorseWeaponSkillInfo) as HorseWeaponSkillInfo;
			}
			return true;
		}
		
		/**
		 * get 当前最高骑乘兵器阶层
		 * @return 
		 */
		public function get layer(): int{
			return _layer;
		}
		
		/**
		 * set 当前最高骑乘兵器阶层
		 */
		public function set layer(value: int): void{
			this._layer = value;
		}
		
		/**
		 * get 当前使用的骑乘兵器阶层
		 * @return 
		 */
		public function get curlayer(): int{
			return _curlayer;
		}
		
		/**
		 * set 当前使用的骑乘兵器阶层
		 */
		public function set curlayer(value: int): void{
			this._curlayer = value;
		}
		
		/**
		 * get 技能
		 * @return 
		 */
		public function get skills(): Vector.<HorseWeaponSkillInfo>{
			return _skills;
		}
		
		/**
		 * set 技能
		 */
		public function set skills(value: Vector.<HorseWeaponSkillInfo>): void{
			this._skills = value;
		}
		
	}
}