package com.game.spirittree.message{
	import com.game.utils.long;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 摘果实，奖励展示
	 */
	public class ResShowToRewardListToClientMessage extends Message {
	
		//果实ID
		private var _fruitid: long;
		
		//果实类型：0普通果实，1银色奇异果，2金色奇异果
		private var _type: int;
		
		//奖励数据索引
		private var _index: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//果实ID
			writeLong(_fruitid);
			//果实类型：0普通果实，1银色奇异果，2金色奇异果
			writeByte(_type);
			//奖励数据索引
			writeInt(_index);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//果实ID
			_fruitid = readLong();
			//果实类型：0普通果实，1银色奇异果，2金色奇异果
			_type = readByte();
			//奖励数据索引
			_index = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 133104;
		}
		
		/**
		 * get 果实ID
		 * @return 
		 */
		public function get fruitid(): long{
			return _fruitid;
		}
		
		/**
		 * set 果实ID
		 */
		public function set fruitid(value: long): void{
			this._fruitid = value;
		}
		
		/**
		 * get 果实类型：0普通果实，1银色奇异果，2金色奇异果
		 * @return 
		 */
		public function get type(): int{
			return _type;
		}
		
		/**
		 * set 果实类型：0普通果实，1银色奇异果，2金色奇异果
		 */
		public function set type(value: int): void{
			this._type = value;
		}
		
		/**
		 * get 奖励数据索引
		 * @return 
		 */
		public function get index(): int{
			return _index;
		}
		
		/**
		 * set 奖励数据索引
		 */
		public function set index(value: int): void{
			this._index = value;
		}
		
	}
}