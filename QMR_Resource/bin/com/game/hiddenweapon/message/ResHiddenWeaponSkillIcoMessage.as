package com.game.hiddenweapon.message{
	import com.game.hiddenweapon.bean.HiddenWeaponSkillIco;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 暗器技能Ico
	 */
	public class ResHiddenWeaponSkillIcoMessage extends Message {
	
		//暗器技能ico
		private var _ico: HiddenWeaponSkillIco;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//暗器技能ico
			writeBean(_ico);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//暗器技能ico
			_ico = readBean(HiddenWeaponSkillIco) as HiddenWeaponSkillIco;
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 162108;
		}
		
		/**
		 * get 暗器技能ico
		 * @return 
		 */
		public function get ico(): HiddenWeaponSkillIco{
			return _ico;
		}
		
		/**
		 * set 暗器技能ico
		 */
		public function set ico(value: HiddenWeaponSkillIco): void{
			this._ico = value;
		}
		
	}
}