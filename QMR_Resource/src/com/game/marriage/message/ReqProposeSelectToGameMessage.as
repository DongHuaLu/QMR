package com.game.marriage.message{
	import com.game.utils.long;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 玩家对求婚做选择
	 */
	public class ReqProposeSelectToGameMessage extends Message {
	
		//求婚者ID
		private var _playerid: long;
		
		//对求婚做选择，1同意，0拒绝（取消）
		private var _select: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//求婚者ID
			writeLong(_playerid);
			//对求婚做选择，1同意，0拒绝（取消）
			writeByte(_select);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//求婚者ID
			_playerid = readLong();
			//对求婚做选择，1同意，0拒绝（取消）
			_select = readByte();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 163203;
		}
		
		/**
		 * get 求婚者ID
		 * @return 
		 */
		public function get playerid(): long{
			return _playerid;
		}
		
		/**
		 * set 求婚者ID
		 */
		public function set playerid(value: long): void{
			this._playerid = value;
		}
		
		/**
		 * get 对求婚做选择，1同意，0拒绝（取消）
		 * @return 
		 */
		public function get select(): int{
			return _select;
		}
		
		/**
		 * set 对求婚做选择，1同意，0拒绝（取消）
		 */
		public function set select(value: int): void{
			this._select = value;
		}
		
	}
}