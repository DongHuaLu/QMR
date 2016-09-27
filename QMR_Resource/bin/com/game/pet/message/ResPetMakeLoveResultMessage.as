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
	public class ResPetMakeLoveResultMessage extends Message {
	
		//宠物Id
		private var _petId: long;
		
		//亲热次数
		private var _makeLoveCount: int;
		
		//今日亲热次数
		private var _dayMakeLoveCount: int;
		
		//亲热冷确时间
		private var _makeLoveCoolDownTime: int;
		
		//亲热加成
		private var _makeLoveAddition: Vector.<com.game.player.bean.PlayerAttributeItem> = new Vector.<com.game.player.bean.PlayerAttributeItem>();
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//宠物Id
			writeLong(_petId);
			//亲热次数
			writeInt(_makeLoveCount);
			//今日亲热次数
			writeInt(_dayMakeLoveCount);
			//亲热冷确时间
			writeInt(_makeLoveCoolDownTime);
			//亲热加成
			writeShort(_makeLoveAddition.length);
			for (i = 0; i < _makeLoveAddition.length; i++) {
				writeBean(_makeLoveAddition[i]);
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
			//亲热次数
			_makeLoveCount = readInt();
			//今日亲热次数
			_dayMakeLoveCount = readInt();
			//亲热冷确时间
			_makeLoveCoolDownTime = readInt();
			//亲热加成
			var makeLoveAddition_length : int = readShort();
			for (i = 0; i < makeLoveAddition_length; i++) {
				_makeLoveAddition[i] = readBean(com.game.player.bean.PlayerAttributeItem) as com.game.player.bean.PlayerAttributeItem;
			}
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 110115;
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
		 * get 亲热次数
		 * @return 
		 */
		public function get makeLoveCount(): int{
			return _makeLoveCount;
		}
		
		/**
		 * set 亲热次数
		 */
		public function set makeLoveCount(value: int): void{
			this._makeLoveCount = value;
		}
		
		/**
		 * get 今日亲热次数
		 * @return 
		 */
		public function get dayMakeLoveCount(): int{
			return _dayMakeLoveCount;
		}
		
		/**
		 * set 今日亲热次数
		 */
		public function set dayMakeLoveCount(value: int): void{
			this._dayMakeLoveCount = value;
		}
		
		/**
		 * get 亲热冷确时间
		 * @return 
		 */
		public function get makeLoveCoolDownTime(): int{
			return _makeLoveCoolDownTime;
		}
		
		/**
		 * set 亲热冷确时间
		 */
		public function set makeLoveCoolDownTime(value: int): void{
			this._makeLoveCoolDownTime = value;
		}
		
		/**
		 * get 亲热加成
		 * @return 
		 */
		public function get makeLoveAddition(): Vector.<com.game.player.bean.PlayerAttributeItem>{
			return _makeLoveAddition;
		}
		
		/**
		 * set 亲热加成
		 */
		public function set makeLoveAddition(value: Vector.<com.game.player.bean.PlayerAttributeItem>): void{
			this._makeLoveAddition = value;
		}
		
	}
}