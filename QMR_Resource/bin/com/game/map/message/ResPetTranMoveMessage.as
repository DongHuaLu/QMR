package com.game.map.message{
	import com.game.utils.long;
	import com.game.structs.Position;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 宠物传送
	 */
	public class ResPetTranMoveMessage extends Message {
	
		//侍宠Id
		private var _petId: long;
		
		//坐标
		private var _position: com.game.structs.Position;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//侍宠Id
			writeLong(_petId);
			//坐标
			writeBean(_position);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//侍宠Id
			_petId = readLong();
			//坐标
			_position = readBean(com.game.structs.Position) as com.game.structs.Position;
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 101134;
		}
		
		/**
		 * get 侍宠Id
		 * @return 
		 */
		public function get petId(): long{
			return _petId;
		}
		
		/**
		 * set 侍宠Id
		 */
		public function set petId(value: long): void{
			this._petId = value;
		}
		
		/**
		 * get 坐标
		 * @return 
		 */
		public function get position(): com.game.structs.Position{
			return _position;
		}
		
		/**
		 * set 坐标
		 */
		public function set position(value: com.game.structs.Position): void{
			this._position = value;
		}
		
	}
}