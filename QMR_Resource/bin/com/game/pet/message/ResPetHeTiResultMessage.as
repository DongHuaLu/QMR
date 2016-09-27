package com.game.pet.message{
	import com.game.utils.long;
	import com.game.player.bean.PlayerAttributeItem;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 合体结果
	 */
	public class ResPetHeTiResultMessage extends Message {
	
		//宠物Id
		private var _petId: long;
		
		//合体次数
		private var _htCount: int;
		
		//今日合体次数
		private var _dayCount: int;
		
		//合体冷确时间
		private var _htCoolDownTime: int;
		
		//1成功  0失败
		private var _isSuccess: int;
		
		//合体加成
		private var _htAddition: Vector.<com.game.player.bean.PlayerAttributeItem> = new Vector.<com.game.player.bean.PlayerAttributeItem>();
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//宠物Id
			writeLong(_petId);
			//合体次数
			writeInt(_htCount);
			//今日合体次数
			writeInt(_dayCount);
			//合体冷确时间
			writeInt(_htCoolDownTime);
			//1成功  0失败
			writeByte(_isSuccess);
			//合体加成
			writeShort(_htAddition.length);
			for (i = 0; i < _htAddition.length; i++) {
				writeBean(_htAddition[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			var i: int = 0;
			//宠物Id
			_petId = readLong();
			//合体次数
			_htCount = readInt();
			//今日合体次数
			_dayCount = readInt();
			//合体冷确时间
			_htCoolDownTime = readInt();
			//1成功  0失败
			_isSuccess = readByte();
			//合体加成
			var htAddition_length : int = readShort();
			for (i = 0; i < htAddition_length; i++) {
				_htAddition[i] = readBean(com.game.player.bean.PlayerAttributeItem) as com.game.player.bean.PlayerAttributeItem;
			}
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 110104;
		}
		
		/**
		 * get 宠物Id
		 * @return 
		 */
		public function get petId(): long{
			return _petId;
		}
		
		/**
		 * set 宠物Id
		 */
		public function set petId(value: long): void{
			this._petId = value;
		}
		
		/**
		 * get 合体次数
		 * @return 
		 */
		public function get htCount(): int{
			return _htCount;
		}
		
		/**
		 * set 合体次数
		 */
		public function set htCount(value: int): void{
			this._htCount = value;
		}
		
		/**
		 * get 今日合体次数
		 * @return 
		 */
		public function get dayCount(): int{
			return _dayCount;
		}
		
		/**
		 * set 今日合体次数
		 */
		public function set dayCount(value: int): void{
			this._dayCount = value;
		}
		
		/**
		 * get 合体冷确时间
		 * @return 
		 */
		public function get htCoolDownTime(): int{
			return _htCoolDownTime;
		}
		
		/**
		 * set 合体冷确时间
		 */
		public function set htCoolDownTime(value: int): void{
			this._htCoolDownTime = value;
		}
		
		/**
		 * get 1成功  0失败
		 * @return 
		 */
		public function get isSuccess(): int{
			return _isSuccess;
		}
		
		/**
		 * set 1成功  0失败
		 */
		public function set isSuccess(value: int): void{
			this._isSuccess = value;
		}
		
		/**
		 * get 合体加成
		 * @return 
		 */
		public function get htAddition(): Vector.<com.game.player.bean.PlayerAttributeItem>{
			return _htAddition;
		}
		
		/**
		 * set 合体加成
		 */
		public function set htAddition(value: Vector.<com.game.player.bean.PlayerAttributeItem>): void{
			this._htAddition = value;
		}
		
	}
}