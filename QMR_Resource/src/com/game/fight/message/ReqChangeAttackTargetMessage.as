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
	 * 切换攻击锁定目标
	 */
	public class ReqChangeAttackTargetMessage extends Message {
	
		//攻击目标类型 1玩家 2怪物 3侍宠
		private var _targetType: int;
		
		//目标唯一标识
		private var _targetId: long;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//攻击目标类型 1玩家 2怪物 3侍宠
			writeInt(_targetType);
			//目标唯一标识
			writeLong(_targetId);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//攻击目标类型 1玩家 2怪物 3侍宠
			_targetType = readInt();
			//目标唯一标识
			_targetId = readLong();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 102203;
		}
		
		/**
		 * get 攻击目标类型 1玩家 2怪物 3侍宠
		 * @return 
		 */
		public function get targetType(): int{
			return _targetType;
		}
		
		/**
		 * set 攻击目标类型 1玩家 2怪物 3侍宠
		 */
		public function set targetType(value: int): void{
			this._targetType = value;
		}
		
		/**
		 * get 目标唯一标识
		 * @return 
		 */
		public function get targetId(): long{
			return _targetId;
		}
		
		/**
		 * set 目标唯一标识
		 */
		public function set targetId(value: long): void{
			this._targetId = value;
		}
		
	}
}