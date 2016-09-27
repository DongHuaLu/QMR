package com.game.collect.bean{
	import com.game.collect.bean.FragInfo;
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 藏品信息
	 */
	public class CollectInfo extends Bean {
	
		//藏品模型
		private var _modelid: int;
		
		//碎片信息
		private var _fragList: Vector.<FragInfo> = new Vector.<FragInfo>();
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//藏品模型
			writeInt(_modelid);
			//碎片信息
			writeShort(_fragList.length);
			for (var i: int = 0; i < _fragList.length; i++) {
				writeBean(_fragList[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//藏品模型
			_modelid = readInt();
			//碎片信息
			var fragList_length : int = readShort();
			for (var i: int = 0; i < fragList_length; i++) {
				_fragList[i] = readBean(FragInfo) as FragInfo;
			}
			return true;
		}
		
		/**
		 * get 藏品模型
		 * @return 
		 */
		public function get modelid(): int{
			return _modelid;
		}
		
		/**
		 * set 藏品模型
		 */
		public function set modelid(value: int): void{
			this._modelid = value;
		}
		
		/**
		 * get 碎片信息
		 * @return 
		 */
		public function get fragList(): Vector.<FragInfo>{
			return _fragList;
		}
		
		/**
		 * set 碎片信息
		 */
		public function set fragList(value: Vector.<FragInfo>): void{
			this._fragList = value;
		}
		
	}
}