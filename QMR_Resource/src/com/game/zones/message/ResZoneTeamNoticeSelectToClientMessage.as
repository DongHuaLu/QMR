package com.game.zones.message{
	import com.game.utils.long;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 组队多人副本进入选择通知其他队员
	 */
	public class ResZoneTeamNoticeSelectToClientMessage extends Message {
	
		//选项（默认是0等待选择），1拒绝，2同意
		private var _select: int;
		
		//队员ID
		private var _memberid: long;
		
		//副本ID
		private var _zoneid: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//选项（默认是0等待选择），1拒绝，2同意
			writeByte(_select);
			//队员ID
			writeLong(_memberid);
			//副本ID
			writeInt(_zoneid);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//选项（默认是0等待选择），1拒绝，2同意
			_select = readByte();
			//队员ID
			_memberid = readLong();
			//副本ID
			_zoneid = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 128118;
		}
		
		/**
		 * get 选项（默认是0等待选择），1拒绝，2同意
		 * @return 
		 */
		public function get select(): int{
			return _select;
		}
		
		/**
		 * set 选项（默认是0等待选择），1拒绝，2同意
		 */
		public function set select(value: int): void{
			this._select = value;
		}
		
		/**
		 * get 队员ID
		 * @return 
		 */
		public function get memberid(): long{
			return _memberid;
		}
		
		/**
		 * set 队员ID
		 */
		public function set memberid(value: long): void{
			this._memberid = value;
		}
		
		/**
		 * get 副本ID
		 * @return 
		 */
		public function get zoneid(): int{
			return _zoneid;
		}
		
		/**
		 * set 副本ID
		 */
		public function set zoneid(value: int): void{
			this._zoneid = value;
		}
		
	}
}