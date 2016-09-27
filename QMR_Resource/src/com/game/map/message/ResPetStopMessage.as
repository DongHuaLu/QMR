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
	 * 宠物停止移动
	 */
	public class ResPetStopMessage extends Message {
	
		//宠物Id
		private var _petId: long;
		
		//修正坐标
		private var _position: com.game.structs.Position;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//宠物Id
			writeLong(_petId);
			//修正坐标
			writeBean(_position);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//宠物Id
			_petId = readLong();
			//修正坐标
			_position = readBean(com.game.structs.Position) as com.game.structs.Position;
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 101121;
		}
		
		/**
		 * get 宠物Id
		 * @return 
		 */
		public function get petId(): long{
			return _petId;
		}
		
		/**
		 * set 宠物Id
		 */
		public function set petId(value: long): void{
			this._petId = value;
		}
		
		/**
		 * get 修正坐标
		 * @return 
		 */
		public function get position(): com.game.structs.Position{
			return _position;
		}
		
		/**
		 * set 修正坐标
		 */
		public function set position(value: com.game.structs.Position): void{
			this._position = value;
		}
		
	}
}