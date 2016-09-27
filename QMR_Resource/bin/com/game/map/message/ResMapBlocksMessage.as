package com.game.map.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 阻挡点信息
	 */
	public class ResMapBlocksMessage extends Message {
	
		//阻挡点集合
		private var _blocks: Vector.<int> = new Vector.<int>();
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//阻挡点集合
			writeShort(_blocks.length);
			for (i = 0; i < _blocks.length; i++) {
				writeInt(_blocks[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			var i: int = 0;
			//阻挡点集合
			var blocks_length : int = readShort();
			for (i = 0; i < blocks_length; i++) {
				_blocks[i] = readInt();
			}
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 101703;
		}
		
		/**
		 * get 阻挡点集合
		 * @return 
		 */
		public function get blocks(): Vector.<int>{
			return _blocks;
		}
		
		/**
		 * set 阻挡点集合
		 */
		public function set blocks(value: Vector.<int>): void{
			this._blocks = value;
		}
		
	}
}