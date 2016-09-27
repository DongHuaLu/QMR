package com.game.dataserver.message{
	import com.game.dataserver.bean.BfPlayerInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 发送战斗小组成员信息
	 */
	public class ResGetBattlefieldTeamMemberInfoToGameMessage extends Message {
	
		//队伍成员战场信息列表
		private var _bfplayerInfolist: Vector.<BfPlayerInfo> = new Vector.<BfPlayerInfo>();
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//队伍成员战场信息列表
			writeShort(_bfplayerInfolist.length);
			for (i = 0; i < _bfplayerInfolist.length; i++) {
				writeBean(_bfplayerInfolist[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			var i: int = 0;
			//队伍成员战场信息列表
			var bfplayerInfolist_length : int = readShort();
			for (i = 0; i < bfplayerInfolist_length; i++) {
				_bfplayerInfolist[i] = readBean(BfPlayerInfo) as BfPlayerInfo;
			}
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 203114;
		}
		
		/**
		 * get 队伍成员战场信息列表
		 * @return 
		 */
		public function get bfplayerInfolist(): Vector.<BfPlayerInfo>{
			return _bfplayerInfolist;
		}
		
		/**
		 * set 队伍成员战场信息列表
		 */
		public function set bfplayerInfolist(value: Vector.<BfPlayerInfo>): void{
			this._bfplayerInfolist = value;
		}
		
	}
}