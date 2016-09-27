package com.game.map.message{
	import com.game.utils.long;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 装备换装
	 */
	public class ResArmorChangeMessage extends Message {
	
		//角色Id
		private var _personId: long;
		
		//衣服模板Id
		private var _armorId: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//角色Id
			writeLong(_personId);
			//衣服模板Id
			writeInt(_armorId);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//角色Id
			_personId = readLong();
			//衣服模板Id
			_armorId = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 101119;
		}
		
		/**
		 * get 角色Id
		 * @return 
		 */
		public function get personId(): long{
			return _personId;
		}
		
		/**
		 * set 角色Id
		 */
		public function set personId(value: long): void{
			this._personId = value;
		}
		
		/**
		 * get 衣服模板Id
		 * @return 
		 */
		public function get armorId(): int{
			return _armorId;
		}
		
		/**
		 * set 衣服模板Id
		 */
		public function set armorId(value: int): void{
			this._armorId = value;
		}
		
	}
}