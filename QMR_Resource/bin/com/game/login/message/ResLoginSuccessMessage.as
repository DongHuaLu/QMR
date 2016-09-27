package com.game.login.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 登陆成功
	 */
	public class ResLoginSuccessMessage extends Message {
	
		//角色所在地图
		private var _mapId: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//角色所在地图
			writeInt(_mapId);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//角色所在地图
			_mapId = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 100102;
		}
		
		/**
		 * get 角色所在地图
		 * @return 
		 */
		public function get mapId(): int{
			return _mapId;
		}
		
		/**
		 * set 角色所在地图
		 */
		public function set mapId(value: int): void{
			this._mapId = value;
		}
		
	}
}