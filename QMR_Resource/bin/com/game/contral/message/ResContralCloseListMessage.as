package com.game.contral.message{
	import com.game.contral.bean.StateInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 功能状态列表
	 */
	public class ResContralCloseListMessage extends Message {
	
		//功能状态列表
		private var _contralList: Vector.<StateInfo> = new Vector.<StateInfo>();
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//功能状态列表
			writeShort(_contralList.length);
			for (i = 0; i < _contralList.length; i++) {
				writeBean(_contralList[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			var i: int = 0;
			//功能状态列表
			var contralList_length : int = readShort();
			for (i = 0; i < contralList_length; i++) {
				_contralList[i] = readBean(StateInfo) as StateInfo;
			}
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 167101;
		}
		
		/**
		 * get 功能状态列表
		 * @return 
		 */
		public function get contralList(): Vector.<StateInfo>{
			return _contralList;
		}
		
		/**
		 * set 功能状态列表
		 */
		public function set contralList(value: Vector.<StateInfo>): void{
			this._contralList = value;
		}
		
	}
}