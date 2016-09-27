package com.game.signwage.message{
	import com.game.spirittree.bean.FruitRewardinfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 摇奖得到奖励
	 */
	public class ResWageERNIEinofMessage extends Message {
	
		//位置
		private var _pos: Vector.<int> = new Vector.<int>();
		//道具奖励
		private var _fruitRewardinfo: Vector.<com.game.spirittree.bean.FruitRewardinfo> = new Vector.<com.game.spirittree.bean.FruitRewardinfo>();
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//位置
			writeShort(_pos.length);
			for (i = 0; i < _pos.length; i++) {
				writeInt(_pos[i]);
			}
			//道具奖励
			writeShort(_fruitRewardinfo.length);
			for (i = 0; i < _fruitRewardinfo.length; i++) {
				writeBean(_fruitRewardinfo[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			var i: int = 0;
			//位置
			var pos_length : int = readShort();
			for (i = 0; i < pos_length; i++) {
				_pos[i] = readInt();
			}
			//道具奖励
			var fruitRewardinfo_length : int = readShort();
			for (i = 0; i < fruitRewardinfo_length; i++) {
				_fruitRewardinfo[i] = readBean(com.game.spirittree.bean.FruitRewardinfo) as com.game.spirittree.bean.FruitRewardinfo;
			}
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 152103;
		}
		
		/**
		 * get 位置
		 * @return 
		 */
		public function get pos(): Vector.<int>{
			return _pos;
		}
		
		/**
		 * set 位置
		 */
		public function set pos(value: Vector.<int>): void{
			this._pos = value;
		}
		
		/**
		 * get 道具奖励
		 * @return 
		 */
		public function get fruitRewardinfo(): Vector.<com.game.spirittree.bean.FruitRewardinfo>{
			return _fruitRewardinfo;
		}
		
		/**
		 * set 道具奖励
		 */
		public function set fruitRewardinfo(value: Vector.<com.game.spirittree.bean.FruitRewardinfo>): void{
			this._fruitRewardinfo = value;
		}
		
	}
}