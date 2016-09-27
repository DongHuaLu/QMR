package com.game.arrow.message{
	import com.game.arrow.bean.FightSpiritInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 返回战魂信息
	 */
	public class ResFightSpiritInfoMessage extends Message {
	
		//通知类型
		private var _notifytype: int;
		
		//战魂信息列表
		private var _fightspiritList: Vector.<FightSpiritInfo> = new Vector.<FightSpiritInfo>();
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//通知类型
			writeInt(_notifytype);
			//战魂信息列表
			writeShort(_fightspiritList.length);
			for (i = 0; i < _fightspiritList.length; i++) {
				writeBean(_fightspiritList[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			var i: int = 0;
			//通知类型
			_notifytype = readInt();
			//战魂信息列表
			var fightspiritList_length : int = readShort();
			for (i = 0; i < fightspiritList_length; i++) {
				_fightspiritList[i] = readBean(FightSpiritInfo) as FightSpiritInfo;
			}
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 151104;
		}
		
		/**
		 * get 通知类型
		 * @return 
		 */
		public function get notifytype(): int{
			return _notifytype;
		}
		
		/**
		 * set 通知类型
		 */
		public function set notifytype(value: int): void{
			this._notifytype = value;
		}
		
		/**
		 * get 战魂信息列表
		 * @return 
		 */
		public function get fightspiritList(): Vector.<FightSpiritInfo>{
			return _fightspiritList;
		}
		
		/**
		 * set 战魂信息列表
		 */
		public function set fightspiritList(value: Vector.<FightSpiritInfo>): void{
			this._fightspiritList = value;
		}
		
	}
}