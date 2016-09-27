package com.game.spirittree.message{
	import com.game.spirittree.bean.FruitInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 发送行会灵树消息
	 */
	public class ResOpenGuildFruitToClientMessage extends Message {
	
		//成熟果实信息列表
		private var _fruitinfo: Vector.<FruitInfo> = new Vector.<FruitInfo>();
		//干旱果实列表
		private var _aridfruitinfo: Vector.<FruitInfo> = new Vector.<FruitInfo>();
		//抢摘次数
		private var _theftnum: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//成熟果实信息列表
			writeShort(_fruitinfo.length);
			for (i = 0; i < _fruitinfo.length; i++) {
				writeBean(_fruitinfo[i]);
			}
			//干旱果实列表
			writeShort(_aridfruitinfo.length);
			for (i = 0; i < _aridfruitinfo.length; i++) {
				writeBean(_aridfruitinfo[i]);
			}
			//抢摘次数
			writeInt(_theftnum);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			var i: int = 0;
			//成熟果实信息列表
			var fruitinfo_length : int = readShort();
			for (i = 0; i < fruitinfo_length; i++) {
				_fruitinfo[i] = readBean(FruitInfo) as FruitInfo;
			}
			//干旱果实列表
			var aridfruitinfo_length : int = readShort();
			for (i = 0; i < aridfruitinfo_length; i++) {
				_aridfruitinfo[i] = readBean(FruitInfo) as FruitInfo;
			}
			//抢摘次数
			_theftnum = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 133103;
		}
		
		/**
		 * get 成熟果实信息列表
		 * @return 
		 */
		public function get fruitinfo(): Vector.<FruitInfo>{
			return _fruitinfo;
		}
		
		/**
		 * set 成熟果实信息列表
		 */
		public function set fruitinfo(value: Vector.<FruitInfo>): void{
			this._fruitinfo = value;
		}
		
		/**
		 * get 干旱果实列表
		 * @return 
		 */
		public function get aridfruitinfo(): Vector.<FruitInfo>{
			return _aridfruitinfo;
		}
		
		/**
		 * set 干旱果实列表
		 */
		public function set aridfruitinfo(value: Vector.<FruitInfo>): void{
			this._aridfruitinfo = value;
		}
		
		/**
		 * get 抢摘次数
		 * @return 
		 */
		public function get theftnum(): int{
			return _theftnum;
		}
		
		/**
		 * set 抢摘次数
		 */
		public function set theftnum(value: int): void{
			this._theftnum = value;
		}
		
	}
}