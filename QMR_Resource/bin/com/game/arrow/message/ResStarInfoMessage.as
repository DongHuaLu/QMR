package com.game.arrow.message{
	import com.game.arrow.bean.StarInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 返回星斗信息
	 */
	public class ResStarInfoMessage extends Message {
	
		//通知类型
		private var _notifytype: int;
		
		//星斗信息
		private var _starinfo: StarInfo;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//通知类型
			writeInt(_notifytype);
			//星斗信息
			writeBean(_starinfo);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//通知类型
			_notifytype = readInt();
			//星斗信息
			_starinfo = readBean(StarInfo) as StarInfo;
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 151102;
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
		 * get 星斗信息
		 * @return 
		 */
		public function get starinfo(): StarInfo{
			return _starinfo;
		}
		
		/**
		 * set 星斗信息
		 */
		public function set starinfo(value: StarInfo): void{
			this._starinfo = value;
		}
		
	}
}