package com.game.equipstreng.message{
	import com.game.equip.bean.EquipInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 发送强化结果
	 */
	public class ResStrengItemToClientMessage extends Message {
	
		//装备详细信息
		private var _equipInfo: com.game.equip.bean.EquipInfo;
		
		//是否成功：0失败，1成功
		private var _issuccess: int;
		
		//当前的强化等级
		private var _itemlevel: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//装备详细信息
			writeBean(_equipInfo);
			//是否成功：0失败，1成功
			writeByte(_issuccess);
			//当前的强化等级
			writeByte(_itemlevel);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//装备详细信息
			_equipInfo = readBean(com.game.equip.bean.EquipInfo) as com.game.equip.bean.EquipInfo;
			//是否成功：0失败，1成功
			_issuccess = readByte();
			//当前的强化等级
			_itemlevel = readByte();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 130101;
		}
		
		/**
		 * get 装备详细信息
		 * @return 
		 */
		public function get equipInfo(): com.game.equip.bean.EquipInfo{
			return _equipInfo;
		}
		
		/**
		 * set 装备详细信息
		 */
		public function set equipInfo(value: com.game.equip.bean.EquipInfo): void{
			this._equipInfo = value;
		}
		
		/**
		 * get 是否成功：0失败，1成功
		 * @return 
		 */
		public function get issuccess(): int{
			return _issuccess;
		}
		
		/**
		 * set 是否成功：0失败，1成功
		 */
		public function set issuccess(value: int): void{
			this._issuccess = value;
		}
		
		/**
		 * get 当前的强化等级
		 * @return 
		 */
		public function get itemlevel(): int{
			return _itemlevel;
		}
		
		/**
		 * set 当前的强化等级
		 */
		public function set itemlevel(value: int): void{
			this._itemlevel = value;
		}
		
	}
}