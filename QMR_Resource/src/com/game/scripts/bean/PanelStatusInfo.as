package com.game.scripts.bean{
	import com.game.scripts.bean.ButtonInfo;
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 面板改变状态信息
	 */
	public class PanelStatusInfo extends Bean {
	
		//面板ID名字
		private var _panelname: String;
		
		//1关闭
		private var _type: int;
		
		//按钮列表
		private var _buttoninfolist: Vector.<ButtonInfo> = new Vector.<ButtonInfo>();
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//面板ID名字
			writeString(_panelname);
			//1关闭
			writeByte(_type);
			//按钮列表
			writeShort(_buttoninfolist.length);
			for (var i: int = 0; i < _buttoninfolist.length; i++) {
				writeBean(_buttoninfolist[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//面板ID名字
			_panelname = readString();
			//1关闭
			_type = readByte();
			//按钮列表
			var buttoninfolist_length : int = readShort();
			for (var i: int = 0; i < buttoninfolist_length; i++) {
				_buttoninfolist[i] = readBean(ButtonInfo) as ButtonInfo;
			}
			return true;
		}
		
		/**
		 * get 面板ID名字
		 * @return 
		 */
		public function get panelname(): String{
			return _panelname;
		}
		
		/**
		 * set 面板ID名字
		 */
		public function set panelname(value: String): void{
			this._panelname = value;
		}
		
		/**
		 * get 1关闭
		 * @return 
		 */
		public function get type(): int{
			return _type;
		}
		
		/**
		 * set 1关闭
		 */
		public function set type(value: int): void{
			this._type = value;
		}
		
		/**
		 * get 按钮列表
		 * @return 
		 */
		public function get buttoninfolist(): Vector.<ButtonInfo>{
			return _buttoninfolist;
		}
		
		/**
		 * set 按钮列表
		 */
		public function set buttoninfolist(value: Vector.<ButtonInfo>): void{
			this._buttoninfolist = value;
		}
		
	}
}