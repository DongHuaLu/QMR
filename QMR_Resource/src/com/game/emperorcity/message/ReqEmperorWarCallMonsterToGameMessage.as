package com.game.emperorcity.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 各国国王召唤援军
	 */
	public class ReqEmperorWarCallMonsterToGameMessage extends Message {
	
		//召唤援军类型
		private var _type: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//召唤援军类型
			writeInt(_type);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//召唤援军类型
			_type = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 169207;
		}
		
		/**
		 * get 召唤援军类型
		 * @return 
		 */
		public function get type(): int{
			return _type;
		}
		
		/**
		 * set 召唤援军类型
		 */
		public function set type(value: int): void{
			this._type = value;
		}
		
	}
}