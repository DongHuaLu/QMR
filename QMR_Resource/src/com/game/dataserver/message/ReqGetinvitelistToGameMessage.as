package com.game.dataserver.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 请求获取邀请列表
	 */
	public class ReqGetinvitelistToGameMessage extends Message {
	
		//列表类型，0好友列表，1帮派成员列表
		private var _type: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//列表类型，0好友列表，1帮派成员列表
			writeInt(_type);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//列表类型，0好友列表，1帮派成员列表
			_type = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 203208;
		}
		
		/**
		 * get 列表类型，0好友列表，1帮派成员列表
		 * @return 
		 */
		public function get type(): int{
			return _type;
		}
		
		/**
		 * set 列表类型，0好友列表，1帮派成员列表
		 */
		public function set type(value: int): void{
			this._type = value;
		}
		
	}
}