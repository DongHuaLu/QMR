package com.game.maze.message{
	import com.game.utils.long;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 发送奖励消息
	 */
	public class ResRewardMessage extends Message {
	
		//NPC ID
		private var _npc: long;
		
		//排名
		private var _sort: int;
		
		//花费时间
		private var _time: int;
		
		//礼金
		private var _bindgold: int;
		
		//经验
		private var _exp: int;
		
		//真气
		private var _zhenqi: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//NPC ID
			writeLong(_npc);
			//排名
			writeInt(_sort);
			//花费时间
			writeInt(_time);
			//礼金
			writeInt(_bindgold);
			//经验
			writeInt(_exp);
			//真气
			writeInt(_zhenqi);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//NPC ID
			_npc = readLong();
			//排名
			_sort = readInt();
			//花费时间
			_time = readInt();
			//礼金
			_bindgold = readInt();
			//经验
			_exp = readInt();
			//真气
			_zhenqi = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 145102;
		}
		
		/**
		 * get NPC ID
		 * @return 
		 */
		public function get npc(): long{
			return _npc;
		}
		
		/**
		 * set NPC ID
		 */
		public function set npc(value: long): void{
			this._npc = value;
		}
		
		/**
		 * get 排名
		 * @return 
		 */
		public function get sort(): int{
			return _sort;
		}
		
		/**
		 * set 排名
		 */
		public function set sort(value: int): void{
			this._sort = value;
		}
		
		/**
		 * get 花费时间
		 * @return 
		 */
		public function get time(): int{
			return _time;
		}
		
		/**
		 * set 花费时间
		 */
		public function set time(value: int): void{
			this._time = value;
		}
		
		/**
		 * get 礼金
		 * @return 
		 */
		public function get bindgold(): int{
			return _bindgold;
		}
		
		/**
		 * set 礼金
		 */
		public function set bindgold(value: int): void{
			this._bindgold = value;
		}
		
		/**
		 * get 经验
		 * @return 
		 */
		public function get exp(): int{
			return _exp;
		}
		
		/**
		 * set 经验
		 */
		public function set exp(value: int): void{
			this._exp = value;
		}
		
		/**
		 * get 真气
		 * @return 
		 */
		public function get zhenqi(): int{
			return _zhenqi;
		}
		
		/**
		 * set 真气
		 */
		public function set zhenqi(value: int): void{
			this._zhenqi = value;
		}
		
	}
}