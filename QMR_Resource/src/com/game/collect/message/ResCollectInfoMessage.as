package com.game.collect.message{
	import com.game.collect.bean.CollectInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 个人藏品信息
	 */
	public class ResCollectInfoMessage extends Message {
	
		//0 初始 1物品提交 2批量提交
		private var _type: int;
		
		//藏品信息
		private var _collectinfo: Vector.<CollectInfo> = new Vector.<CollectInfo>();
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//0 初始 1物品提交 2批量提交
			writeByte(_type);
			//藏品信息
			writeShort(_collectinfo.length);
			for (i = 0; i < _collectinfo.length; i++) {
				writeBean(_collectinfo[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			var i: int = 0;
			//0 初始 1物品提交 2批量提交
			_type = readByte();
			//藏品信息
			var collectinfo_length : int = readShort();
			for (i = 0; i < collectinfo_length; i++) {
				_collectinfo[i] = readBean(CollectInfo) as CollectInfo;
			}
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 153101;
		}
		
		/**
		 * get 0 初始 1物品提交 2批量提交
		 * @return 
		 */
		public function get type(): int{
			return _type;
		}
		
		/**
		 * set 0 初始 1物品提交 2批量提交
		 */
		public function set type(value: int): void{
			this._type = value;
		}
		
		/**
		 * get 藏品信息
		 * @return 
		 */
		public function get collectinfo(): Vector.<CollectInfo>{
			return _collectinfo;
		}
		
		/**
		 * set 藏品信息
		 */
		public function set collectinfo(value: Vector.<CollectInfo>): void{
			this._collectinfo = value;
		}
		
	}
}