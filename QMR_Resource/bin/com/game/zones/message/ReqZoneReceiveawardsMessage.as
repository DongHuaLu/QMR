package com.game.zones.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 前端手动请求领取奖励
	 */
	public class ReqZoneReceiveawardsMessage extends Message {
	
		//类型:0手动，1自动扫荡
		private var _type: int;
		
		//副本ID，0没选中
		private var _zid: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//类型:0手动，1自动扫荡
			writeByte(_type);
			//副本ID，0没选中
			writeInt(_zid);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//类型:0手动，1自动扫荡
			_type = readByte();
			//副本ID，0没选中
			_zid = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 128207;
		}
		
		/**
		 * get 类型:0手动，1自动扫荡
		 * @return 
		 */
		public function get type(): int{
			return _type;
		}
		
		/**
		 * set 类型:0手动，1自动扫荡
		 */
		public function set type(value: int): void{
			this._type = value;
		}
		
		/**
		 * get 副本ID，0没选中
		 * @return 
		 */
		public function get zid(): int{
			return _zid;
		}
		
		/**
		 * set 副本ID，0没选中
		 */
		public function set zid(value: int): void{
			this._zid = value;
		}
		
	}
}