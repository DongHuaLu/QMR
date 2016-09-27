package com.game.amulet.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 技能书重新鉴定返回
	 */
	public class ResAmuletItemRebuildMessage extends Message {
	
		//0:成功 1:失败
		private var _result: int;
		
		//原来的道具
		private var _srcItem: int;
		
		//获得的道具
		private var _dstItem: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//0:成功 1:失败
			writeByte(_result);
			//原来的道具
			writeInt(_srcItem);
			//获得的道具
			writeInt(_dstItem);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//0:成功 1:失败
			_result = readByte();
			//原来的道具
			_srcItem = readInt();
			//获得的道具
			_dstItem = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 166105;
		}
		
		/**
		 * get 0:成功 1:失败
		 * @return 
		 */
		public function get result(): int{
			return _result;
		}
		
		/**
		 * set 0:成功 1:失败
		 */
		public function set result(value: int): void{
			this._result = value;
		}
		
		/**
		 * get 原来的道具
		 * @return 
		 */
		public function get srcItem(): int{
			return _srcItem;
		}
		
		/**
		 * set 原来的道具
		 */
		public function set srcItem(value: int): void{
			this._srcItem = value;
		}
		
		/**
		 * get 获得的道具
		 * @return 
		 */
		public function get dstItem(): int{
			return _dstItem;
		}
		
		/**
		 * set 获得的道具
		 */
		public function set dstItem(value: int): void{
			this._dstItem = value;
		}
		
	}
}