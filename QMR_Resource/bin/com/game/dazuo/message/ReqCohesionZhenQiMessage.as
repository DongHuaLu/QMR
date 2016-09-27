package com.game.dazuo.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 凝聚 真气凝丹
	 */
	public class ReqCohesionZhenQiMessage extends Message {
	
		//凝丹数量
		private var _num: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//凝丹数量
			writeInt(_num);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//凝丹数量
			_num = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 136205;
		}
		
		/**
		 * get 凝丹数量
		 * @return 
		 */
		public function get num(): int{
			return _num;
		}
		
		/**
		 * set 凝丹数量
		 */
		public function set num(value: int): void{
			this._num = value;
		}
		
	}
}