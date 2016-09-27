package com.game.epalace.message{
	import com.game.spirittree.bean.FruitRewardinfo;
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
	public class ResEpalaceRewardInfoToClientMessage extends Message {
	
		//奖励类型，和格子事件对应
		private var _type: int;
		
		//奖励内容
		private var _fruitrewardinfo: Vector.<com.game.spirittree.bean.FruitRewardinfo> = new Vector.<com.game.spirittree.bean.FruitRewardinfo>();
		//普通奖励的BUFFID，如果是0，表示给其他数值奖励
		private var _buffid: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//奖励类型，和格子事件对应
			writeInt(_type);
			//奖励内容
			writeShort(_fruitrewardinfo.length);
			for (i = 0; i < _fruitrewardinfo.length; i++) {
				writeBean(_fruitrewardinfo[i]);
			}
			//普通奖励的BUFFID，如果是0，表示给其他数值奖励
			writeInt(_buffid);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			var i: int = 0;
			//奖励类型，和格子事件对应
			_type = readInt();
			//奖励内容
			var fruitrewardinfo_length : int = readShort();
			for (i = 0; i < fruitrewardinfo_length; i++) {
				_fruitrewardinfo[i] = readBean(com.game.spirittree.bean.FruitRewardinfo) as com.game.spirittree.bean.FruitRewardinfo;
			}
			//普通奖励的BUFFID，如果是0，表示给其他数值奖励
			_buffid = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 143106;
		}
		
		/**
		 * get 奖励类型，和格子事件对应
		 * @return 
		 */
		public function get type(): int{
			return _type;
		}
		
		/**
		 * set 奖励类型，和格子事件对应
		 */
		public function set type(value: int): void{
			this._type = value;
		}
		
		/**
		 * get 奖励内容
		 * @return 
		 */
		public function get fruitrewardinfo(): Vector.<com.game.spirittree.bean.FruitRewardinfo>{
			return _fruitrewardinfo;
		}
		
		/**
		 * set 奖励内容
		 */
		public function set fruitrewardinfo(value: Vector.<com.game.spirittree.bean.FruitRewardinfo>): void{
			this._fruitrewardinfo = value;
		}
		
		/**
		 * get 普通奖励的BUFFID，如果是0，表示给其他数值奖励
		 * @return 
		 */
		public function get buffid(): int{
			return _buffid;
		}
		
		/**
		 * set 普通奖励的BUFFID，如果是0，表示给其他数值奖励
		 */
		public function set buffid(value: int): void{
			this._buffid = value;
		}
		
	}
}