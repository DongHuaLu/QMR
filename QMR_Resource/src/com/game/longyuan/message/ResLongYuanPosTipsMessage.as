package com.game.longyuan.message{
	import com.game.longyuan.bean.LongYuanPosTipsInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 鼠标悬停提示信息(星位)
	 */
	public class ResLongYuanPosTipsMessage extends Message {
	
		//龙元心法星位tips
		private var _longyuanpostipsinfo: LongYuanPosTipsInfo;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//龙元心法星位tips
			writeBean(_longyuanpostipsinfo);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//龙元心法星位tips
			_longyuanpostipsinfo = readBean(LongYuanPosTipsInfo) as LongYuanPosTipsInfo;
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 115103;
		}
		
		/**
		 * get 龙元心法星位tips
		 * @return 
		 */
		public function get longyuanpostipsinfo(): LongYuanPosTipsInfo{
			return _longyuanpostipsinfo;
		}
		
		/**
		 * set 龙元心法星位tips
		 */
		public function set longyuanpostipsinfo(value: LongYuanPosTipsInfo): void{
			this._longyuanpostipsinfo = value;
		}
		
	}
}