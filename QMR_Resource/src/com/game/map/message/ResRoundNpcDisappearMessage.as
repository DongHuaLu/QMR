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
	 * 周围消失NPC
	 */
	public class ResRoundNpcDisappearMessage extends Message {
	
		//消失NPC列表
		private var _npcids: Vector.<long> = new Vector.<long>();
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//消失NPC列表
			writeShort(_npcids.length);
			for (i = 0; i < _npcids.length; i++) {
				writeLong(_npcids[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			var i: int = 0;
			//消失NPC列表
			var npcids_length : int = readShort();
			for (i = 0; i < npcids_length; i++) {
				_npcids[i] = readLong();
			}
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 101130;
		}
		
		/**
		 * get 消失NPC列表
		 * @return 
		 */
		public function get npcids(): Vector.<long>{
			return _npcids;
		}
		
		/**
		 * set 消失NPC列表
		 */
		public function set npcids(value: Vector.<long>): void{
			this._npcids = value;
		}
		
	}
}