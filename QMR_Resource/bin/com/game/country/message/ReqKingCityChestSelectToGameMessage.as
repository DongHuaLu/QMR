package com.game.country.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 王城宝箱奖励选择
	 */
	public class ReqKingCityChestSelectToGameMessage extends Message {
	
		//选择编号
		private var _idx: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//选择编号
			writeInt(_idx);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//选择编号
			_idx = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 146205;
		}
		
		/**
		 * get 选择编号
		 * @return 
		 */
		public function get idx(): int{
			return _idx;
		}
		
		/**
		 * set 选择编号
		 */
		public function set idx(value: int): void{
			this._idx = value;
		}
		
	}
}