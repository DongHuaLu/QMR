package com.game.spirittree.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 连续催熟果实
	 */
	public class ReqContinuousRipeningToGameMessage extends Message {
	
		//果实类型：0普通果实，1银色奇异果，2金色奇异果
		private var _type: int;
		
		//连续催熟果实次数
		private var _num: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//果实类型：0普通果实，1银色奇异果，2金色奇异果
			writeByte(_type);
			//连续催熟果实次数
			writeInt(_num);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//果实类型：0普通果实，1银色奇异果，2金色奇异果
			_type = readByte();
			//连续催熟果实次数
			_num = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 133207;
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
		 * get 连续催熟果实次数
		 * @return 
		 */
		public function get num(): int{
			return _num;
		}
		
		/**
		 * set 连续催熟果实次数
		 */
		public function set num(value: int): void{
			this._num = value;
		}
		
	}
}