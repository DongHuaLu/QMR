package com.game.map.message{
	import com.game.map.bean.NpcInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 周围NPC
	 */
	public class ResRoundNpcMessage extends Message {
	
		//周围NPC信息
		private var _npc: NpcInfo;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//周围NPC信息
			writeBean(_npc);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//周围NPC信息
			_npc = readBean(NpcInfo) as NpcInfo;
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 101129;
		}
		
		/**
		 * get 周围NPC信息
		 * @return 
		 */
		public function get npc(): NpcInfo{
			return _npc;
		}
		
		/**
		 * set 周围NPC信息
		 */
		public function set npc(value: NpcInfo): void{
			this._npc = value;
		}
		
	}
}