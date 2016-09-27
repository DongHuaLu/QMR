package com.game.fight.message{
	import com.game.utils.long;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 攻击侍宠请求
	 */
	public class ReqAttackPetMessage extends Message {
	
		//攻击类型
		private var _fightType: int;
		
		//攻击朝向
		private var _fightDirection: int;
		
		//攻击目标
		private var _fightTarget: long;
		
		//主人ID
		private var _owherId: long;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//攻击类型
			writeInt(_fightType);
			//攻击朝向
			writeInt(_fightDirection);
			//攻击目标
			writeLong(_fightTarget);
			//主人ID
			writeLong(_owherId);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//攻击类型
			_fightType = readInt();
			//攻击朝向
			_fightDirection = readInt();
			//攻击目标
			_fightTarget = readLong();
			//主人ID
			_owherId = readLong();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 102204;
		}
		
		/**
		 * get 攻击类型
		 * @return 
		 */
		public function get fightType(): int{
			return _fightType;
		}
		
		/**
		 * set 攻击类型
		 */
		public function set fightType(value: int): void{
			this._fightType = value;
		}
		
		/**
		 * get 攻击朝向
		 * @return 
		 */
		public function get fightDirection(): int{
			return _fightDirection;
		}
		
		/**
		 * set 攻击朝向
		 */
		public function set fightDirection(value: int): void{
			this._fightDirection = value;
		}
		
		/**
		 * get 攻击目标
		 * @return 
		 */
		public function get fightTarget(): long{
			return _fightTarget;
		}
		
		/**
		 * set 攻击目标
		 */
		public function set fightTarget(value: long): void{
			this._fightTarget = value;
		}
		
		/**
		 * get 主人ID
		 * @return 
		 */
		public function get owherId(): long{
			return _owherId;
		}
		
		/**
		 * set 主人ID
		 */
		public function set owherId(value: long): void{
			this._owherId = value;
		}
		
	}
}