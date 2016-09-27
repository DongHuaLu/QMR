package com.game.dataserver.message{
	import com.game.dataserver.bean.BfPlayerInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 返回个人重新排位
	 */
	public class ResPlayerResetToClientMessage extends Message {
	
		//匹配剩余秒数
		private var _time: int;
		
		//玩家战场信息
		private var _bfplayerInfo: BfPlayerInfo;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//匹配剩余秒数
			writeInt(_time);
			//玩家战场信息
			writeBean(_bfplayerInfo);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//匹配剩余秒数
			_time = readInt();
			//玩家战场信息
			_bfplayerInfo = readBean(BfPlayerInfo) as BfPlayerInfo;
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 203107;
		}
		
		/**
		 * get 匹配剩余秒数
		 * @return 
		 */
		public function get time(): int{
			return _time;
		}
		
		/**
		 * set 匹配剩余秒数
		 */
		public function set time(value: int): void{
			this._time = value;
		}
		
		/**
		 * get 玩家战场信息
		 * @return 
		 */
		public function get bfplayerInfo(): BfPlayerInfo{
			return _bfplayerInfo;
		}
		
		/**
		 * set 玩家战场信息
		 */
		public function set bfplayerInfo(value: BfPlayerInfo): void{
			this._bfplayerInfo = value;
		}
		
	}
}