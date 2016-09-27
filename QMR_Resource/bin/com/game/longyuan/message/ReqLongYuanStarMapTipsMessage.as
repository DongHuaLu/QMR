package com.game.longyuan.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 请求鼠标悬停星图提示信息（星图）
	 */
	public class ReqLongYuanStarMapTipsMessage extends Message {
	
		//当前的龙元心法阶段（星图）
		private var _longyuanactlv: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//当前的龙元心法阶段（星图）
			writeByte(_longyuanactlv);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//当前的龙元心法阶段（星图）
			_longyuanactlv = readByte();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 115204;
		}
		
		/**
		 * get 当前的龙元心法阶段（星图）
		 * @return 
		 */
		public function get longyuanactlv(): int{
			return _longyuanactlv;
		}
		
		/**
		 * set 当前的龙元心法阶段（星图）
		 */
		public function set longyuanactlv(value: int): void{
			this._longyuanactlv = value;
		}
		
	}
}