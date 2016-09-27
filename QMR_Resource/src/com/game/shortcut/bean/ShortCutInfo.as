package com.game.shortcut.bean{
	import com.game.utils.long;
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 快捷信息类
	 */
	public class ShortCutInfo extends Bean {
	
		//快捷Id
		private var _shortcutId: long;
		
		//快捷类型
		private var _shortcutType: int;
		
		//快捷对象
		private var _shortcutSource: long;
		
		//快捷对象模板
		private var _shortcutSourceModel: int;
		
		//快捷位置
		private var _shortcutGrid: int;
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//快捷Id
			writeLong(_shortcutId);
			//快捷类型
			writeInt(_shortcutType);
			//快捷对象
			writeLong(_shortcutSource);
			//快捷对象模板
			writeInt(_shortcutSourceModel);
			//快捷位置
			writeInt(_shortcutGrid);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//快捷Id
			_shortcutId = readLong();
			//快捷类型
			_shortcutType = readInt();
			//快捷对象
			_shortcutSource = readLong();
			//快捷对象模板
			_shortcutSourceModel = readInt();
			//快捷位置
			_shortcutGrid = readInt();
			return true;
		}
		
		/**
		 * get 快捷Id
		 * @return 
		 */
		public function get shortcutId(): long{
			return _shortcutId;
		}
		
		/**
		 * set 快捷Id
		 */
		public function set shortcutId(value: long): void{
			this._shortcutId = value;
		}
		
		/**
		 * get 快捷类型
		 * @return 
		 */
		public function get shortcutType(): int{
			return _shortcutType;
		}
		
		/**
		 * set 快捷类型
		 */
		public function set shortcutType(value: int): void{
			this._shortcutType = value;
		}
		
		/**
		 * get 快捷对象
		 * @return 
		 */
		public function get shortcutSource(): long{
			return _shortcutSource;
		}
		
		/**
		 * set 快捷对象
		 */
		public function set shortcutSource(value: long): void{
			this._shortcutSource = value;
		}
		
		/**
		 * get 快捷对象模板
		 * @return 
		 */
		public function get shortcutSourceModel(): int{
			return _shortcutSourceModel;
		}
		
		/**
		 * set 快捷对象模板
		 */
		public function set shortcutSourceModel(value: int): void{
			this._shortcutSourceModel = value;
		}
		
		/**
		 * get 快捷位置
		 * @return 
		 */
		public function get shortcutGrid(): int{
			return _shortcutGrid;
		}
		
		/**
		 * set 快捷位置
		 */
		public function set shortcutGrid(value: int): void{
			this._shortcutGrid = value;
		}
		
	}
}