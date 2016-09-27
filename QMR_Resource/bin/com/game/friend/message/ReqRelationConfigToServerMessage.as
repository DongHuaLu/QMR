package com.game.friend.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 请求关系设置
	 */
	public class ReqRelationConfigToServerMessage extends Message {
	
		//是否公开我的地图位置
		private var _openMapLocation: int;
		
		//是否显示不在线的好友或仇人
		private var _showrelation: int;
		
		//是否在列表中显示头像
		private var _showicon: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//是否公开我的地图位置
			writeByte(_openMapLocation);
			//是否显示不在线的好友或仇人
			writeByte(_showrelation);
			//是否在列表中显示头像
			writeByte(_showicon);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//是否公开我的地图位置
			_openMapLocation = readByte();
			//是否显示不在线的好友或仇人
			_showrelation = readByte();
			//是否在列表中显示头像
			_showicon = readByte();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 119206;
		}
		
		/**
		 * get 是否公开我的地图位置
		 * @return 
		 */
		public function get openMapLocation(): int{
			return _openMapLocation;
		}
		
		/**
		 * set 是否公开我的地图位置
		 */
		public function set openMapLocation(value: int): void{
			this._openMapLocation = value;
		}
		
		/**
		 * get 是否显示不在线的好友或仇人
		 * @return 
		 */
		public function get showrelation(): int{
			return _showrelation;
		}
		
		/**
		 * set 是否显示不在线的好友或仇人
		 */
		public function set showrelation(value: int): void{
			this._showrelation = value;
		}
		
		/**
		 * get 是否在列表中显示头像
		 * @return 
		 */
		public function get showicon(): int{
			return _showicon;
		}
		
		/**
		 * set 是否在列表中显示头像
		 */
		public function set showicon(value: int): void{
			this._showicon = value;
		}
		
	}
}