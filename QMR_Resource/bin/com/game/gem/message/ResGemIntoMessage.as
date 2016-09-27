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
	 * 告诉前端要升级或激活的宝石
	 */
	public class ResGemIntoMessage extends Message {
	
		//装备部位
		private var _pos: int;
		
		//升级的宝石信息
		private var _geminfo: GemInfo;
		
		//类型：0激活，1升级
		private var _type: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//装备部位
			writeByte(_pos);
			//升级的宝石信息
			writeBean(_geminfo);
			//类型：0激活，1升级
			writeByte(_type);
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
			//类型：0激活，1升级
			_type = readByte();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 132102;
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
		 * get 类型：0激活，1升级
		 * @return 
		 */
		public function get type(): int{
			return _type;
		}
		
		/**
		 * set 类型：0激活，1升级
		 */
		public function set type(value: int): void{
			this._type = value;
		}
		
	}
}