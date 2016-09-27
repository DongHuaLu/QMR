package com.game.spirittree.message{
	import com.game.utils.long;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 催熟果实成功后通知前端
	 */
	public class ResRipeningDecYBToClientMessage extends Message {
	
		//果实ID
		private var _fruitid: long;
		
		//需要的元宝数量
		private var _yuanbao: int;
		
		//类型：1扣元宝成功，0失败
		private var _type: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//果实ID
			writeLong(_fruitid);
			//需要的元宝数量
			writeInt(_yuanbao);
			//类型：1扣元宝成功，0失败
			writeByte(_type);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//果实ID
			_fruitid = readLong();
			//需要的元宝数量
			_yuanbao = readInt();
			//类型：1扣元宝成功，0失败
			_type = readByte();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 133106;
		}
		
		/**
		 * get 果实ID
		 * @return 
		 */
		public function get fruitid(): long{
			return _fruitid;
		}
		
		/**
		 * set 果实ID
		 */
		public function set fruitid(value: long): void{
			this._fruitid = value;
		}
		
		/**
		 * get 需要的元宝数量
		 * @return 
		 */
		public function get yuanbao(): int{
			return _yuanbao;
		}
		
		/**
		 * set 需要的元宝数量
		 */
		public function set yuanbao(value: int): void{
			this._yuanbao = value;
		}
		
		/**
		 * get 类型：1扣元宝成功，0失败
		 * @return 
		 */
		public function get type(): int{
			return _type;
		}
		
		/**
		 * set 类型：1扣元宝成功，0失败
		 */
		public function set type(value: int): void{
			this._type = value;
		}
		
	}
}