package com.game.chestbox.bean{
	import com.game.chestbox.bean.ChestGridInfo;
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 宝箱盒子信息
	 */
	public class ChestBoxInfo extends Bean {
	
		//开启次数
		private var _opennum: int;
		
		//是否自动
		private var _autoopen: int;
		
		//内圈选中编号
		private var _inchooseidx: int;
		
		//外圈选中编号
		private var _outchooseidx: int;
		
		//内圈格子列表
		private var _ingridlist: Vector.<ChestGridInfo> = new Vector.<ChestGridInfo>();
		//外圈格子列表
		private var _outgridlist: Vector.<ChestGridInfo> = new Vector.<ChestGridInfo>();
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//开启次数
			writeInt(_opennum);
			//是否自动
			writeInt(_autoopen);
			//内圈选中编号
			writeInt(_inchooseidx);
			//外圈选中编号
			writeInt(_outchooseidx);
			//内圈格子列表
			writeShort(_ingridlist.length);
			for (var i: int = 0; i < _ingridlist.length; i++) {
				writeBean(_ingridlist[i]);
			}
			//外圈格子列表
			writeShort(_outgridlist.length);
			for (var i: int = 0; i < _outgridlist.length; i++) {
				writeBean(_outgridlist[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//开启次数
			_opennum = readInt();
			//是否自动
			_autoopen = readInt();
			//内圈选中编号
			_inchooseidx = readInt();
			//外圈选中编号
			_outchooseidx = readInt();
			//内圈格子列表
			var ingridlist_length : int = readShort();
			for (var i: int = 0; i < ingridlist_length; i++) {
				_ingridlist[i] = readBean(ChestGridInfo) as ChestGridInfo;
			}
			//外圈格子列表
			var outgridlist_length : int = readShort();
			for (var i: int = 0; i < outgridlist_length; i++) {
				_outgridlist[i] = readBean(ChestGridInfo) as ChestGridInfo;
			}
			return true;
		}
		
		/**
		 * get 开启次数
		 * @return 
		 */
		public function get opennum(): int{
			return _opennum;
		}
		
		/**
		 * set 开启次数
		 */
		public function set opennum(value: int): void{
			this._opennum = value;
		}
		
		/**
		 * get 是否自动
		 * @return 
		 */
		public function get autoopen(): int{
			return _autoopen;
		}
		
		/**
		 * set 是否自动
		 */
		public function set autoopen(value: int): void{
			this._autoopen = value;
		}
		
		/**
		 * get 内圈选中编号
		 * @return 
		 */
		public function get inchooseidx(): int{
			return _inchooseidx;
		}
		
		/**
		 * set 内圈选中编号
		 */
		public function set inchooseidx(value: int): void{
			this._inchooseidx = value;
		}
		
		/**
		 * get 外圈选中编号
		 * @return 
		 */
		public function get outchooseidx(): int{
			return _outchooseidx;
		}
		
		/**
		 * set 外圈选中编号
		 */
		public function set outchooseidx(value: int): void{
			this._outchooseidx = value;
		}
		
		/**
		 * get 内圈格子列表
		 * @return 
		 */
		public function get ingridlist(): Vector.<ChestGridInfo>{
			return _ingridlist;
		}
		
		/**
		 * set 内圈格子列表
		 */
		public function set ingridlist(value: Vector.<ChestGridInfo>): void{
			this._ingridlist = value;
		}
		
		/**
		 * get 外圈格子列表
		 * @return 
		 */
		public function get outgridlist(): Vector.<ChestGridInfo>{
			return _outgridlist;
		}
		
		/**
		 * set 外圈格子列表
		 */
		public function set outgridlist(value: Vector.<ChestGridInfo>): void{
			this._outgridlist = value;
		}
		
	}
}