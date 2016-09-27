package com.game.longyuan.message{
	import com.game.longyuan.bean.LongYuanStarMapTipsInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 鼠标悬停提示信息(星图)
	 */
	public class ResLongYuanStarMapTipsMessage extends Message {
	
		//当前的龙元心法星图tips
		private var _starmaptipsinfo: LongYuanStarMapTipsInfo;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//当前的龙元心法星图tips
			writeBean(_starmaptipsinfo);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//当前的龙元心法星图tips
			_starmaptipsinfo = readBean(LongYuanStarMapTipsInfo) as LongYuanStarMapTipsInfo;
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 115104;
		}
		
		/**
		 * get 当前的龙元心法星图tips
		 * @return 
		 */
		public function get starmaptipsinfo(): LongYuanStarMapTipsInfo{
			return _starmaptipsinfo;
		}
		
		/**
		 * set 当前的龙元心法星图tips
		 */
		public function set starmaptipsinfo(value: LongYuanStarMapTipsInfo): void{
			this._starmaptipsinfo = value;
		}
		
	}
}