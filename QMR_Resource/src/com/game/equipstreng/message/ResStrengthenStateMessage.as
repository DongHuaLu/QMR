package com.game.equipstreng.message{
	import com.game.utils.long;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 发送当前强化状态
	 */
	public class ResStrengthenStateMessage extends Message {
	
		//道具唯一ID，0表示没有道具在强化
		private var _itemid: long;
		
		//强化完成剩余时间
		private var _time: int;
		
		//强化完成需要的总时间
		private var _timesum: int;
		
		//消耗的元宝基础值
		private var _yuanbao: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//道具唯一ID，0表示没有道具在强化
			writeLong(_itemid);
			//强化完成剩余时间
			writeInt(_time);
			//强化完成需要的总时间
			writeInt(_timesum);
			//消耗的元宝基础值
			writeInt(_yuanbao);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//道具唯一ID，0表示没有道具在强化
			_itemid = readLong();
			//强化完成剩余时间
			_time = readInt();
			//强化完成需要的总时间
			_timesum = readInt();
			//消耗的元宝基础值
			_yuanbao = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 130104;
		}
		
		/**
		 * get 道具唯一ID，0表示没有道具在强化
		 * @return 
		 */
		public function get itemid(): long{
			return _itemid;
		}
		
		/**
		 * set 道具唯一ID，0表示没有道具在强化
		 */
		public function set itemid(value: long): void{
			this._itemid = value;
		}
		
		/**
		 * get 强化完成剩余时间
		 * @return 
		 */
		public function get time(): int{
			return _time;
		}
		
		/**
		 * set 强化完成剩余时间
		 */
		public function set time(value: int): void{
			this._time = value;
		}
		
		/**
		 * get 强化完成需要的总时间
		 * @return 
		 */
		public function get timesum(): int{
			return _timesum;
		}
		
		/**
		 * set 强化完成需要的总时间
		 */
		public function set timesum(value: int): void{
			this._timesum = value;
		}
		
		/**
		 * get 消耗的元宝基础值
		 * @return 
		 */
		public function get yuanbao(): int{
			return _yuanbao;
		}
		
		/**
		 * set 消耗的元宝基础值
		 */
		public function set yuanbao(value: int): void{
			this._yuanbao = value;
		}
		
	}
}