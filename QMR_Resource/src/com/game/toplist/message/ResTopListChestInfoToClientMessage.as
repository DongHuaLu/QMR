package com.game.toplist.message{
	import com.game.toplist.bean.ChestInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 发送角色宝箱信息
	 */
	public class ResTopListChestInfoToClientMessage extends Message {
	
		//玩家当前宝箱列表
		private var _chestinfolist: Vector.<ChestInfo> = new Vector.<ChestInfo>();
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//玩家当前宝箱列表
			writeShort(_chestinfolist.length);
			for (i = 0; i < _chestinfolist.length; i++) {
				writeBean(_chestinfolist[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			var i: int = 0;
			//玩家当前宝箱列表
			var chestinfolist_length : int = readShort();
			for (i = 0; i < chestinfolist_length; i++) {
				_chestinfolist[i] = readBean(ChestInfo) as ChestInfo;
			}
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 142151;
		}
		
		/**
		 * get 玩家当前宝箱列表
		 * @return 
		 */
		public function get chestinfolist(): Vector.<ChestInfo>{
			return _chestinfolist;
		}
		
		/**
		 * set 玩家当前宝箱列表
		 */
		public function set chestinfolist(value: Vector.<ChestInfo>): void{
			this._chestinfolist = value;
		}
		
	}
}