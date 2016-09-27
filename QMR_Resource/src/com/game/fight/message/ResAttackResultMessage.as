package com.game.fight.message{
	import com.game.utils.long;
	import com.game.fight.bean.AttackResultInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 攻击结果
	 */
	public class ResAttackResultMessage extends Message {
	
		//战斗Id
		private var _fightId: long;
		
		//攻击结果
		private var _state: AttackResultInfo;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//战斗Id
			writeLong(_fightId);
			//攻击结果
			writeBean(_state);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//战斗Id
			_fightId = readLong();
			//攻击结果
			_state = readBean(AttackResultInfo) as AttackResultInfo;
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 102102;
		}
		
		/**
		 * get 战斗Id
		 * @return 
		 */
		public function get fightId(): long{
			return _fightId;
		}
		
		/**
		 * set 战斗Id
		 */
		public function set fightId(value: long): void{
			this._fightId = value;
		}
		
		/**
		 * get 攻击结果
		 * @return 
		 */
		public function get state(): AttackResultInfo{
			return _state;
		}
		
		/**
		 * set 攻击结果
		 */
		public function set state(value: AttackResultInfo): void{
			this._state = value;
		}
		
	}
}