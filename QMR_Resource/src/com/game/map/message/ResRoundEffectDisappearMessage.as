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
	 * 周围消失效果
	 */
	public class ResRoundEffectDisappearMessage extends Message {
	
		//消失效果列表
		private var _effectid: Vector.<long> = new Vector.<long>();
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//消失效果列表
			writeShort(_effectid.length);
			for (i = 0; i < _effectid.length; i++) {
				writeLong(_effectid[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			var i: int = 0;
			//消失效果列表
			var effectid_length : int = readShort();
			for (i = 0; i < effectid_length; i++) {
				_effectid[i] = readLong();
			}
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 101132;
		}
		
		/**
		 * get 消失效果列表
		 * @return 
		 */
		public function get effectid(): Vector.<long>{
			return _effectid;
		}
		
		/**
		 * set 消失效果列表
		 */
		public function set effectid(value: Vector.<long>): void{
			this._effectid = value;
		}
		
	}
}