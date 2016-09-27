package com.game.scripts.bean{
	import com.game.scripts.bean.PanelTxtInfo;
	import com.game.scripts.bean.PanelItemInfo;
	import com.game.scripts.bean.ButtonInfo;
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 面板信息
	 */
	public class PanelInfo extends Bean {
	
		//面板信息
		private var _panelmap: String;
		
		//面板按钮信息列表
		private var _buttoninfolist: Vector.<ButtonInfo> = new Vector.<ButtonInfo>();
		//面板文字信息列表
		private var _paneltxtinfolist: Vector.<PanelTxtInfo> = new Vector.<PanelTxtInfo>();
		//面板道具框信息列表
		private var _paneliteminfolist: Vector.<PanelItemInfo> = new Vector.<PanelItemInfo>();
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//面板信息
			writeString(_panelmap);
			//面板按钮信息列表
			writeShort(_buttoninfolist.length);
			for (var i: int = 0; i < _buttoninfolist.length; i++) {
				writeBean(_buttoninfolist[i]);
			}
			//面板文字信息列表
			writeShort(_paneltxtinfolist.length);
			for (var i: int = 0; i < _paneltxtinfolist.length; i++) {
				writeBean(_paneltxtinfolist[i]);
			}
			//面板道具框信息列表
			writeShort(_paneliteminfolist.length);
			for (var i: int = 0; i < _paneliteminfolist.length; i++) {
				writeBean(_paneliteminfolist[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//面板信息
			_panelmap = readString();
			//面板按钮信息列表
			var buttoninfolist_length : int = readShort();
			for (var i: int = 0; i < buttoninfolist_length; i++) {
				_buttoninfolist[i] = readBean(ButtonInfo) as ButtonInfo;
			}
			//面板文字信息列表
			var paneltxtinfolist_length : int = readShort();
			for (var i: int = 0; i < paneltxtinfolist_length; i++) {
				_paneltxtinfolist[i] = readBean(PanelTxtInfo) as PanelTxtInfo;
			}
			//面板道具框信息列表
			var paneliteminfolist_length : int = readShort();
			for (var i: int = 0; i < paneliteminfolist_length; i++) {
				_paneliteminfolist[i] = readBean(PanelItemInfo) as PanelItemInfo;
			}
			return true;
		}
		
		/**
		 * get 面板信息
		 * @return 
		 */
		public function get panelmap(): String{
			return _panelmap;
		}
		
		/**
		 * set 面板信息
		 */
		public function set panelmap(value: String): void{
			this._panelmap = value;
		}
		
		/**
		 * get 面板按钮信息列表
		 * @return 
		 */
		public function get buttoninfolist(): Vector.<ButtonInfo>{
			return _buttoninfolist;
		}
		
		/**
		 * set 面板按钮信息列表
		 */
		public function set buttoninfolist(value: Vector.<ButtonInfo>): void{
			this._buttoninfolist = value;
		}
		
		/**
		 * get 面板文字信息列表
		 * @return 
		 */
		public function get paneltxtinfolist(): Vector.<PanelTxtInfo>{
			return _paneltxtinfolist;
		}
		
		/**
		 * set 面板文字信息列表
		 */
		public function set paneltxtinfolist(value: Vector.<PanelTxtInfo>): void{
			this._paneltxtinfolist = value;
		}
		
		/**
		 * get 面板道具框信息列表
		 * @return 
		 */
		public function get paneliteminfolist(): Vector.<PanelItemInfo>{
			return _paneliteminfolist;
		}
		
		/**
		 * set 面板道具框信息列表
		 */
		public function set paneliteminfolist(value: Vector.<PanelItemInfo>): void{
			this._paneliteminfolist = value;
		}
		
	}
}