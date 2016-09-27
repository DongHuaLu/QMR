package com.game.marriage.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 获取配偶其他资料
	 */
	public class ReqGetSpouseOtherToGameMessage extends Message {
	
		//类型：1获取配偶龙元心法，2获取配偶武功
		private var _type: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//类型：1获取配偶龙元心法，2获取配偶武功
			writeInt(_type);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//类型：1获取配偶龙元心法，2获取配偶武功
			_type = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 163217;
		}
		
		/**
		 * get 类型：1获取配偶龙元心法，2获取配偶武功
		 * @return 
		 */
		public function get type(): int{
			return _type;
		}
		
		/**
		 * set 类型：1获取配偶龙元心法，2获取配偶武功
		 */
		public function set type(value: int): void{
			this._type = value;
		}
		
	}
}