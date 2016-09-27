package com.game.scripts.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 脚本列表
	 */
	public class ResScriptPaneleMessage extends Message {
	
		//脚本Id
		private var _scriptId: int;
		
		//面板信息
		private var _panel: String;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//脚本Id
			writeInt(_scriptId);
			//面板信息
			writeString(_panel);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//脚本Id
			_scriptId = readInt();
			//面板信息
			_panel = readString();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 148101;
		}
		
		/**
		 * get 脚本Id
		 * @return 
		 */
		public function get scriptId(): int{
			return _scriptId;
		}
		
		/**
		 * set 脚本Id
		 */
		public function set scriptId(value: int): void{
			this._scriptId = value;
		}
		
		/**
		 * get 面板信息
		 * @return 
		 */
		public function get panel(): String{
			return _panel;
		}
		
		/**
		 * set 面板信息
		 */
		public function set panel(value: String): void{
			this._panel = value;
		}
		
	}
}