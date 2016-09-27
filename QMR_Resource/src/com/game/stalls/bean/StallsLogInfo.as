package com.game.stalls.bean{
	import com.game.stalls.bean.StallsSingleLogInfo;
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 摊位交易日志列表（发送前端）
	 */
	public class StallsLogInfo extends Bean {
	
		//摊位物品信息列表（最大20条）
		private var _stallsloglist: Vector.<StallsSingleLogInfo> = new Vector.<StallsSingleLogInfo>();
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//摊位物品信息列表（最大20条）
			writeShort(_stallsloglist.length);
			for (var i: int = 0; i < _stallsloglist.length; i++) {
				writeBean(_stallsloglist[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//摊位物品信息列表（最大20条）
			var stallsloglist_length : int = readShort();
			for (var i: int = 0; i < stallsloglist_length; i++) {
				_stallsloglist[i] = readBean(StallsSingleLogInfo) as StallsSingleLogInfo;
			}
			return true;
		}
		
		/**
		 * get 摊位物品信息列表（最大20条）
		 * @return 
		 */
		public function get stallsloglist(): Vector.<StallsSingleLogInfo>{
			return _stallsloglist;
		}
		
		/**
		 * set 摊位物品信息列表（最大20条）
		 */
		public function set stallsloglist(value: Vector.<StallsSingleLogInfo>): void{
			this._stallsloglist = value;
		}
		
	}
}