package com.game.gem.message{
	import com.game.gem.bean.GemInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 经验溢出后给另外一颗宝石加经验
	 */
	public class ResGemExtraExpMessage extends Message {
	
		//装备部位
		private var _pos: int;
		
		//升级的宝石信息
		private var _geminfo: GemInfo;
		
		//获得的经验
		private var _exp: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//装备部位
			writeByte(_pos);
			//升级的宝石信息
			writeBean(_geminfo);
			//获得的经验
			writeInt(_exp);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//装备部位
			_pos = readByte();
			//升级的宝石信息
			_geminfo = readBean(GemInfo) as GemInfo;
			//获得的经验
			_exp = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 132104;
		}
		
		/**
		 * get 装备部位
		 * @return 
		 */
		public function get pos(): int{
			return _pos;
		}
		
		/**
		 * set 装备部位
		 */
		public function set pos(value: int): void{
			this._pos = value;
		}
		
		/**
		 * get 升级的宝石信息
		 * @return 
		 */
		public function get geminfo(): GemInfo{
			return _geminfo;
		}
		
		/**
		 * set 升级的宝石信息
		 */
		public function set geminfo(value: GemInfo): void{
			this._geminfo = value;
		}
		
		/**
		 * get 获得的经验
		 * @return 
		 */
		public function get exp(): int{
			return _exp;
		}
		
		/**
		 * set 获得的经验
		 */
		public function set exp(value: int): void{
			this._exp = value;
		}
		
	}
}