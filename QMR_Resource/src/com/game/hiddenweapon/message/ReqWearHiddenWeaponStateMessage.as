package com.game.hiddenweapon.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 改变暗器装备状态
	 */
	public class ReqWearHiddenWeaponStateMessage extends Message {
	
		//当前使用的暗器阶层
		private var _curlayer: int;
		
		//是否装备，1装备，0卸下
		private var _status: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//当前使用的暗器阶层
			writeShort(_curlayer);
			//是否装备，1装备，0卸下
			writeByte(_status);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//当前使用的暗器阶层
			_curlayer = readShort();
			//是否装备，1装备，0卸下
			_status = readByte();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 162202;
		}
		
		/**
		 * get 当前使用的暗器阶层
		 * @return 
		 */
		public function get curlayer(): int{
			return _curlayer;
		}
		
		/**
		 * set 当前使用的暗器阶层
		 */
		public function set curlayer(value: int): void{
			this._curlayer = value;
		}
		
		/**
		 * get 是否装备，1装备，0卸下
		 * @return 
		 */
		public function get status(): int{
			return _status;
		}
		
		/**
		 * set 是否装备，1装备，0卸下
		 */
		public function set status(value: int): void{
			this._status = value;
		}
		
	}
}