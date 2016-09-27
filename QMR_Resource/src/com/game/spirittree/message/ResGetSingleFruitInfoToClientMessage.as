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
	 * 发送灵树单个果实消息
	 */
	public class ResGetSingleFruitInfoToClientMessage extends Message {
	
		//果实信息
		private var _fruitinfo: FruitInfo;
		
		//类型：0刷新，1保底，2浇水
		private var _type: int;
		
		//浇水获得经验
		private var _exp: int;
		
		//摘取数量
		private var _num: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//果实信息
			writeBean(_fruitinfo);
			//类型：0刷新，1保底，2浇水
			writeByte(_type);
			//浇水获得经验
			writeInt(_exp);
			//摘取数量
			writeInt(_num);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//果实信息
			_fruitinfo = readBean(FruitInfo) as FruitInfo;
			//类型：0刷新，1保底，2浇水
			_type = readByte();
			//浇水获得经验
			_exp = readInt();
			//摘取数量
			_num = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 133102;
		}
		
		/**
		 * get 果实信息
		 * @return 
		 */
		public function get fruitinfo(): FruitInfo{
			return _fruitinfo;
		}
		
		/**
		 * set 果实信息
		 */
		public function set fruitinfo(value: FruitInfo): void{
			this._fruitinfo = value;
		}
		
		/**
		 * get 类型：0刷新，1保底，2浇水
		 * @return 
		 */
		public function get type(): int{
			return _type;
		}
		
		/**
		 * set 类型：0刷新，1保底，2浇水
		 */
		public function set type(value: int): void{
			this._type = value;
		}
		
		/**
		 * get 浇水获得经验
		 * @return 
		 */
		public function get exp(): int{
			return _exp;
		}
		
		/**
		 * set 浇水获得经验
		 */
		public function set exp(value: int): void{
			this._exp = value;
		}
		
		/**
		 * get 摘取数量
		 * @return 
		 */
		public function get num(): int{
			return _num;
		}
		
		/**
		 * set 摘取数量
		 */
		public function set num(value: int): void{
			this._num = value;
		}
		
	}
}