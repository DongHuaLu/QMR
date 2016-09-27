package com.game.marriage.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 更换婚戒刷新对方面板
	 */
	public class ResReplaceRingToClientMessage extends Message {
	
		//戒指模版ID
		private var _ringmodelid: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//戒指模版ID
			writeInt(_ringmodelid);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//戒指模版ID
			_ringmodelid = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 163103;
		}
		
		/**
		 * get 戒指模版ID
		 * @return 
		 */
		public function get ringmodelid(): int{
			return _ringmodelid;
		}
		
		/**
		 * set 戒指模版ID
		 */
		public function set ringmodelid(value: int): void{
			this._ringmodelid = value;
		}
		
	}
}