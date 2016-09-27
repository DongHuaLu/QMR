package com.game.offline.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 返回闭关信息
	 */
	public class ResRetreatInfoMessage extends Message {
	
		//通知类型
		private var _notifyType: int;
		
		//当前闭关时间
		private var _curTime: int;
		
		//当前闭关经验
		private var _curExp: int;
		
		//当前闭关真气
		private var _curZhenqi: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//通知类型
			writeInt(_notifyType);
			//当前闭关时间
			writeInt(_curTime);
			//当前闭关经验
			writeInt(_curExp);
			//当前闭关真气
			writeInt(_curZhenqi);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//通知类型
			_notifyType = readInt();
			//当前闭关时间
			_curTime = readInt();
			//当前闭关经验
			_curExp = readInt();
			//当前闭关真气
			_curZhenqi = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 150101;
		}
		
		/**
		 * get 通知类型
		 * @return 
		 */
		public function get notifyType(): int{
			return _notifyType;
		}
		
		/**
		 * set 通知类型
		 */
		public function set notifyType(value: int): void{
			this._notifyType = value;
		}
		
		/**
		 * get 当前闭关时间
		 * @return 
		 */
		public function get curTime(): int{
			return _curTime;
		}
		
		/**
		 * set 当前闭关时间
		 */
		public function set curTime(value: int): void{
			this._curTime = value;
		}
		
		/**
		 * get 当前闭关经验
		 * @return 
		 */
		public function get curExp(): int{
			return _curExp;
		}
		
		/**
		 * set 当前闭关经验
		 */
		public function set curExp(value: int): void{
			this._curExp = value;
		}
		
		/**
		 * get 当前闭关真气
		 * @return 
		 */
		public function get curZhenqi(): int{
			return _curZhenqi;
		}
		
		/**
		 * set 当前闭关真气
		 */
		public function set curZhenqi(value: int): void{
			this._curZhenqi = value;
		}
		
	}
}