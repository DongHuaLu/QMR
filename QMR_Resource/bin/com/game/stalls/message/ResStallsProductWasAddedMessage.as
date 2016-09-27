package com.game.stalls.message{
	import com.game.stalls.bean.StallsInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 商品上架后，更新列表
	 */
	public class ResStallsProductWasAddedMessage extends Message {
	
		//查看指定摊位信息
		private var _stallsinfo: StallsInfo;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//查看指定摊位信息
			writeBean(_stallsinfo);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//查看指定摊位信息
			_stallsinfo = readBean(StallsInfo) as StallsInfo;
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 123105;
		}
		
		/**
		 * get 查看指定摊位信息
		 * @return 
		 */
		public function get stallsinfo(): StallsInfo{
			return _stallsinfo;
		}
		
		/**
		 * set 查看指定摊位信息
		 */
		public function set stallsinfo(value: StallsInfo): void{
			this._stallsinfo = value;
		}
		
	}
}