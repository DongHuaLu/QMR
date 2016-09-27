package com.game.marriage.message{
	import com.game.marriage.bean.WeddingInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 展示婚宴列表
	 */
	public class ResWeddingListToClientMessage extends Message {
	
		//婚宴信息列表
		private var _weddingInfolist: Vector.<WeddingInfo> = new Vector.<WeddingInfo>();
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//婚宴信息列表
			writeShort(_weddingInfolist.length);
			for (i = 0; i < _weddingInfolist.length; i++) {
				writeBean(_weddingInfolist[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			var i: int = 0;
			//婚宴信息列表
			var weddingInfolist_length : int = readShort();
			for (i = 0; i < weddingInfolist_length; i++) {
				_weddingInfolist[i] = readBean(WeddingInfo) as WeddingInfo;
			}
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 163108;
		}
		
		/**
		 * get 婚宴信息列表
		 * @return 
		 */
		public function get weddingInfolist(): Vector.<WeddingInfo>{
			return _weddingInfolist;
		}
		
		/**
		 * set 婚宴信息列表
		 */
		public function set weddingInfolist(value: Vector.<WeddingInfo>): void{
			this._weddingInfolist = value;
		}
		
	}
}