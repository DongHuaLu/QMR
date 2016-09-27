package com.game.team.message{
	import com.game.team.bean.MapPlayerInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 前端请求搜索本地图玩家信息
	 */
	public class ResMapSearchPlayerInfoClientMessage extends Message {
	
		//当前地图玩家列表
		private var _mapplayerinfo: Vector.<MapPlayerInfo> = new Vector.<MapPlayerInfo>();
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//当前地图玩家列表
			writeShort(_mapplayerinfo.length);
			for (i = 0; i < _mapplayerinfo.length; i++) {
				writeBean(_mapplayerinfo[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			var i: int = 0;
			//当前地图玩家列表
			var mapplayerinfo_length : int = readShort();
			for (i = 0; i < mapplayerinfo_length; i++) {
				_mapplayerinfo[i] = readBean(MapPlayerInfo) as MapPlayerInfo;
			}
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 118109;
		}
		
		/**
		 * get 当前地图玩家列表
		 * @return 
		 */
		public function get mapplayerinfo(): Vector.<MapPlayerInfo>{
			return _mapplayerinfo;
		}
		
		/**
		 * set 当前地图玩家列表
		 */
		public function set mapplayerinfo(value: Vector.<MapPlayerInfo>): void{
			this._mapplayerinfo = value;
		}
		
	}
}