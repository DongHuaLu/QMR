package com.game.zones.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 组队多人队员选择
	 */
	public class ReqZoneTeamSelectToGameMessage extends Message {
	
		//选项，1拒绝，2同意
		private var _select: int;
		
		//副本ID
		private var _zoneid: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//选项，1拒绝，2同意
			writeByte(_select);
			//副本ID
			writeInt(_zoneid);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//选项，1拒绝，2同意
			_select = readByte();
			//副本ID
			_zoneid = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 128212;
		}
		
		/**
		 * get 选项，1拒绝，2同意
		 * @return 
		 */
		public function get select(): int{
			return _select;
		}
		
		/**
		 * set 选项，1拒绝，2同意
		 */
		public function set select(value: int): void{
			this._select = value;
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