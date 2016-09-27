package com.game.chestbox.message{
	import com.game.chestbox.bean.ChestGridInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 宝箱盒子生成新物品信息发送到客户端
	 */
	public class ResChestBoxNewGridInfoToClientMessage extends Message {
	
		//新内圈格子信息
		private var _newingridlist: Vector.<ChestGridInfo> = new Vector.<ChestGridInfo>();
		//新外圈格子信息
		private var _newoutgridlist: Vector.<ChestGridInfo> = new Vector.<ChestGridInfo>();
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//新内圈格子信息
			writeShort(_newingridlist.length);
			for (i = 0; i < _newingridlist.length; i++) {
				writeBean(_newingridlist[i]);
			}
			//新外圈格子信息
			writeShort(_newoutgridlist.length);
			for (i = 0; i < _newoutgridlist.length; i++) {
				writeBean(_newoutgridlist[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			var i: int = 0;
			//新内圈格子信息
			var newingridlist_length : int = readShort();
			for (i = 0; i < newingridlist_length; i++) {
				_newingridlist[i] = readBean(ChestGridInfo) as ChestGridInfo;
			}
			//新外圈格子信息
			var newoutgridlist_length : int = readShort();
			for (i = 0; i < newoutgridlist_length; i++) {
				_newoutgridlist[i] = readBean(ChestGridInfo) as ChestGridInfo;
			}
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 156103;
		}
		
		/**
		 * get 新内圈格子信息
		 * @return 
		 */
		public function get newingridlist(): Vector.<ChestGridInfo>{
			return _newingridlist;
		}
		
		/**
		 * set 新内圈格子信息
		 */
		public function set newingridlist(value: Vector.<ChestGridInfo>): void{
			this._newingridlist = value;
		}
		
		/**
		 * get 新外圈格子信息
		 * @return 
		 */
		public function get newoutgridlist(): Vector.<ChestGridInfo>{
			return _newoutgridlist;
		}
		
		/**
		 * set 新外圈格子信息
		 */
		public function set newoutgridlist(value: Vector.<ChestGridInfo>): void{
			this._newoutgridlist = value;
		}
		
	}
}