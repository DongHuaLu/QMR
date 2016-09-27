package com.game.ybcard.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 打开公测元宝卡后展示窗口
	 */
	public class ResYBCardToClientMessage extends Message {
	
		//公测元宝总数量
		private var _yuanbaonum: int;
		
		//抽到的元宝数量
		private var _num: int;
		
		//类型，0打开面板，1使用元宝卡，2领取元宝
		private var _type: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//公测元宝总数量
			writeInt(_yuanbaonum);
			//抽到的元宝数量
			writeInt(_num);
			//类型，0打开面板，1使用元宝卡，2领取元宝
			writeByte(_type);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//公测元宝总数量
			_yuanbaonum = readInt();
			//抽到的元宝数量
			_num = readInt();
			//类型，0打开面板，1使用元宝卡，2领取元宝
			_type = readByte();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 139101;
		}
		
		/**
		 * get 公测元宝总数量
		 * @return 
		 */
		public function get yuanbaonum(): int{
			return _yuanbaonum;
		}
		
		/**
		 * set 公测元宝总数量
		 */
		public function set yuanbaonum(value: int): void{
			this._yuanbaonum = value;
		}
		
		/**
		 * get 抽到的元宝数量
		 * @return 
		 */
		public function get num(): int{
			return _num;
		}
		
		/**
		 * set 抽到的元宝数量
		 */
		public function set num(value: int): void{
			this._num = value;
		}
		
		/**
		 * get 类型，0打开面板，1使用元宝卡，2领取元宝
		 * @return 
		 */
		public function get type(): int{
			return _type;
		}
		
		/**
		 * set 类型，0打开面板，1使用元宝卡，2领取元宝
		 */
		public function set type(value: int): void{
			this._type = value;
		}
		
	}
}