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
	 * 购买商品后，更新列表
	 */
	public class ResStallsBuyMessage extends Message {
	
		//查看指定摊位信息
		private var _stallsinfo: StallsInfo;
		
		//是否允许评分，0允许，1不允许
		private var _israting: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//查看指定摊位信息
			writeBean(_stallsinfo);
			//是否允许评分，0允许，1不允许
			writeByte(_israting);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//查看指定摊位信息
			_stallsinfo = readBean(StallsInfo) as StallsInfo;
			//是否允许评分，0允许，1不允许
			_israting = readByte();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 123104;
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
		
		/**
		 * get 是否允许评分，0允许，1不允许
		 * @return 
		 */
		public function get israting(): int{
			return _israting;
		}
		
		/**
		 * set 是否允许评分，0允许，1不允许
		 */
		public function set israting(value: int): void{
			this._israting = value;
		}
		
	}
}