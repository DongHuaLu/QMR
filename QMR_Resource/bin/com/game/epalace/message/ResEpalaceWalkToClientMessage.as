package com.game.epalace.message{
	import com.game.epalace.bean.EpalaceInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 地宫行走消息
	 */
	public class ResEpalaceWalkToClientMessage extends Message {
	
		//目标格子信息
		private var _epalaceInfo: EpalaceInfo;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//目标格子信息
			writeBean(_epalaceInfo);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//目标格子信息
			_epalaceInfo = readBean(EpalaceInfo) as EpalaceInfo;
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 143101;
		}
		
		/**
		 * get 目标格子信息
		 * @return 
		 */
		public function get epalaceInfo(): EpalaceInfo{
			return _epalaceInfo;
		}
		
		/**
		 * set 目标格子信息
		 */
		public function set epalaceInfo(value: EpalaceInfo): void{
			this._epalaceInfo = value;
		}
		
	}
}