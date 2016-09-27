package com.game.map.message{
	import com.game.utils.long;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 周围消失怪物
	 */
	public class ResRoundMonsterDisappearMessage extends Message {
	
		//消失怪物列表
		private var _monstersIds: Vector.<long> = new Vector.<long>();
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//消失怪物列表
			writeShort(_monstersIds.length);
			for (i = 0; i < _monstersIds.length; i++) {
				writeLong(_monstersIds[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			var i: int = 0;
			//消失怪物列表
			var monstersIds_length : int = readShort();
			for (i = 0; i < monstersIds_length; i++) {
				_monstersIds[i] = readLong();
			}
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 101106;
		}
		
		/**
		 * get 消失怪物列表
		 * @return 
		 */
		public function get monstersIds(): Vector.<long>{
			return _monstersIds;
		}
		
		/**
		 * set 消失怪物列表
		 */
		public function set monstersIds(value: Vector.<long>): void{
			this._monstersIds = value;
		}
		
	}
}