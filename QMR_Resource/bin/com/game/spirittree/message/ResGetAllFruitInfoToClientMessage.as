package com.game.spirittree.message{
	import com.game.spirittree.bean.FruitInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 发送个人灵树全部果实消息
	 */
	public class ResGetAllFruitInfoToClientMessage extends Message {
	
		//果实信息列表
		private var _fruitinfo: Vector.<FruitInfo> = new Vector.<FruitInfo>();
		//下一个果实成熟的时间
		private var _nexttime: int;
		
		//下次仙露浇灌时间
		private var _nextdew: int;
		
		//仙露浇灌次数
		private var _dewnum: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//果实信息列表
			writeShort(_fruitinfo.length);
			for (i = 0; i < _fruitinfo.length; i++) {
				writeBean(_fruitinfo[i]);
			}
			//下一个果实成熟的时间
			writeInt(_nexttime);
			//下次仙露浇灌时间
			writeInt(_nextdew);
			//仙露浇灌次数
			writeInt(_dewnum);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			var i: int = 0;
			//果实信息列表
			var fruitinfo_length : int = readShort();
			for (i = 0; i < fruitinfo_length; i++) {
				_fruitinfo[i] = readBean(FruitInfo) as FruitInfo;
			}
			//下一个果实成熟的时间
			_nexttime = readInt();
			//下次仙露浇灌时间
			_nextdew = readInt();
			//仙露浇灌次数
			_dewnum = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 133101;
		}
		
		/**
		 * get 果实信息列表
		 * @return 
		 */
		public function get fruitinfo(): Vector.<FruitInfo>{
			return _fruitinfo;
		}
		
		/**
		 * set 果实信息列表
		 */
		public function set fruitinfo(value: Vector.<FruitInfo>): void{
			this._fruitinfo = value;
		}
		
		/**
		 * get 下一个果实成熟的时间
		 * @return 
		 */
		public function get nexttime(): int{
			return _nexttime;
		}
		
		/**
		 * set 下一个果实成熟的时间
		 */
		public function set nexttime(value: int): void{
			this._nexttime = value;
		}
		
		/**
		 * get 下次仙露浇灌时间
		 * @return 
		 */
		public function get nextdew(): int{
			return _nextdew;
		}
		
		/**
		 * set 下次仙露浇灌时间
		 */
		public function set nextdew(value: int): void{
			this._nextdew = value;
		}
		
		/**
		 * get 仙露浇灌次数
		 * @return 
		 */
		public function get dewnum(): int{
			return _dewnum;
		}
		
		/**
		 * set 仙露浇灌次数
		 */
		public function set dewnum(value: int): void{
			this._dewnum = value;
		}
		
	}
}