package com.game.buff.message{
	import com.game.buff.bean.BuffInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * Buff列表
	 */
	public class ResBuffsMessage extends Message {
	
		//战斗状态
		private var _fightState: int;
		
		//buff
		private var _buff: Vector.<BuffInfo> = new Vector.<BuffInfo>();
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//战斗状态
			writeInt(_fightState);
			//buff
			writeShort(_buff.length);
			for (i = 0; i < _buff.length; i++) {
				writeBean(_buff[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			var i: int = 0;
			//战斗状态
			_fightState = readInt();
			//buff
			var buff_length : int = readShort();
			for (i = 0; i < buff_length; i++) {
				_buff[i] = readBean(BuffInfo) as BuffInfo;
			}
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 113101;
		}
		
		/**
		 * get 战斗状态
		 * @return 
		 */
		public function get fightState(): int{
			return _fightState;
		}
		
		/**
		 * set 战斗状态
		 */
		public function set fightState(value: int): void{
			this._fightState = value;
		}
		
		/**
		 * get buff
		 * @return 
		 */
		public function get buff(): Vector.<BuffInfo>{
			return _buff;
		}
		
		/**
		 * set buff
		 */
		public function set buff(value: Vector.<BuffInfo>): void{
			this._buff = value;
		}
		
	}
}