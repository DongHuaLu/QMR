package com.game.gem.bean{
	import com.game.gem.bean.GemInfo;
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 每个部位宝石信息
	 */
	public class PosGemInfo extends Bean {
	
		//装备部位（0-9）
		private var _pos: int;
		
		//每个部位宝石信息（最多5个）
		private var _geminfo: Vector.<GemInfo> = new Vector.<GemInfo>();
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//装备部位（0-9）
			writeByte(_pos);
			//每个部位宝石信息（最多5个）
			writeShort(_geminfo.length);
			for (var i: int = 0; i < _geminfo.length; i++) {
				writeBean(_geminfo[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//装备部位（0-9）
			_pos = readByte();
			//每个部位宝石信息（最多5个）
			var geminfo_length : int = readShort();
			for (var i: int = 0; i < geminfo_length; i++) {
				_geminfo[i] = readBean(GemInfo) as GemInfo;
			}
			return true;
		}
		
		/**
		 * get 装备部位（0-9）
		 * @return 
		 */
		public function get pos(): int{
			return _pos;
		}
		
		/**
		 * set 装备部位（0-9）
		 */
		public function set pos(value: int): void{
			this._pos = value;
		}
		
		/**
		 * get 每个部位宝石信息（最多5个）
		 * @return 
		 */
		public function get geminfo(): Vector.<GemInfo>{
			return _geminfo;
		}
		
		/**
		 * set 每个部位宝石信息（最多5个）
		 */
		public function set geminfo(value: Vector.<GemInfo>): void{
			this._geminfo = value;
		}
		
	}
}