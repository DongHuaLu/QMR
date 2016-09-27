package com.game.buff.message{
	import com.game.utils.long;
	import com.game.buff.bean.BuffInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * Buff改变
	 */
	public class ResChangeBuffMessage extends Message {
	
		//角色Id
		private var _personId: long;
		
		//来源Id
		private var _sourceId: long;
		
		//战斗状态
		private var _fightState: int;
		
		//buff
		private var _buff: BuffInfo;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//角色Id
			writeLong(_personId);
			//来源Id
			writeLong(_sourceId);
			//战斗状态
			writeInt(_fightState);
			//buff
			writeBean(_buff);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//角色Id
			_personId = readLong();
			//来源Id
			_sourceId = readLong();
			//战斗状态
			_fightState = readInt();
			//buff
			_buff = readBean(BuffInfo) as BuffInfo;
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 113105;
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
		 * get 来源Id
		 * @return 
		 */
		public function get sourceId(): long{
			return _sourceId;
		}
		
		/**
		 * set 来源Id
		 */
		public function set sourceId(value: long): void{
			this._sourceId = value;
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
		public function get buff(): BuffInfo{
			return _buff;
		}
		
		/**
		 * set buff
		 */
		public function set buff(value: BuffInfo): void{
			this._buff = value;
		}
		
	}
}