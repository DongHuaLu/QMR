package com.game.fight.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 攻击范围(测试专用)
	 */
	public class ResAttackRangeMessage extends Message {
	
		//格子列表
		private var _grids: Vector.<int> = new Vector.<int>();
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//格子列表
			writeShort(_grids.length);
			for (i = 0; i < _grids.length; i++) {
				writeInt(_grids[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			var i: int = 0;
			//格子列表
			var grids_length : int = readShort();
			for (i = 0; i < grids_length; i++) {
				_grids[i] = readInt();
			}
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 102103;
		}
		
		/**
		 * get 格子列表
		 * @return 
		 */
		public function get grids(): Vector.<int>{
			return _grids;
		}
		
		/**
		 * set 格子列表
		 */
		public function set grids(value: Vector.<int>): void{
			this._grids = value;
		}
		
	}
}