package com.game.biwudao.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 选择是否参加比武岛
	 */
	public class ReqBiWuDaoSelectToGameMessage extends Message {
	
		//0进入，1离开
		private var _type: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//0进入，1离开
			writeInt(_type);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//0进入，1离开
			_type = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 157201;
		}
		
		/**
		 * get 0进入，1离开
		 * @return 
		 */
		public function get type(): int{
			return _type;
		}
		
		/**
		 * set 0进入，1离开
		 */
		public function set type(value: int): void{
			this._type = value;
		}
		
	}
}