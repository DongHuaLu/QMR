package com.game.equip.message{
	import com.game.equip.bean.EquipInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 穿着装备信息
	 */
	public class WearEquipItemMessage extends Message {
	
		//装备信息
		private var _equip: EquipInfo;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//装备信息
			writeBean(_equip);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//装备信息
			_equip = readBean(EquipInfo) as EquipInfo;
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 106101;
		}
		
		/**
		 * get 装备信息
		 * @return 
		 */
		public function get equip(): EquipInfo{
			return _equip;
		}
		
		/**
		 * set 装备信息
		 */
		public function set equip(value: EquipInfo): void{
			this._equip = value;
		}
		
	}
}