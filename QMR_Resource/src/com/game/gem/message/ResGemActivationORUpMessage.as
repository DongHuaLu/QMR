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
	 * 宝石激活或者升级结果
	 */
	public class ResGemActivationORUpMessage extends Message {
	
		//装备部位
		private var _pos: int;
		
		//升级的宝石信息
		private var _geminfo: GemInfo;
		
		//结果，0失败，1成功
		private var _result: int;
		
		//类型：0激活，1升级
		private var _type: int;
		
		//获得的经验(升级才有用)
		private var _exp: int;
		
		//经验类型(升级才有用)：0普通，1小暴击，2大暴击
		private var _exptype: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//装备部位
			writeByte(_pos);
			//升级的宝石信息
			writeBean(_geminfo);
			//结果，0失败，1成功
			writeByte(_result);
			//类型：0激活，1升级
			writeByte(_type);
			//获得的经验(升级才有用)
			writeInt(_exp);
			//经验类型(升级才有用)：0普通，1小暴击，2大暴击
			writeByte(_exptype);
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
			//结果，0失败，1成功
			_result = readByte();
			//类型：0激活，1升级
			_type = readByte();
			//获得的经验(升级才有用)
			_exp = readInt();
			//经验类型(升级才有用)：0普通，1小暴击，2大暴击
			_exptype = readByte();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 132103;
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
		 * get 结果，0失败，1成功
		 * @return 
		 */
		public function get result(): int{
			return _result;
		}
		
		/**
		 * set 结果，0失败，1成功
		 */
		public function set result(value: int): void{
			this._result = value;
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
		
		/**
		 * get 获得的经验(升级才有用)
		 * @return 
		 */
		public function get exp(): int{
			return _exp;
		}
		
		/**
		 * set 获得的经验(升级才有用)
		 */
		public function set exp(value: int): void{
			this._exp = value;
		}
		
		/**
		 * get 经验类型(升级才有用)：0普通，1小暴击，2大暴击
		 * @return 
		 */
		public function get exptype(): int{
			return _exptype;
		}
		
		/**
		 * set 经验类型(升级才有用)：0普通，1小暴击，2大暴击
		 */
		public function set exptype(value: int): void{
			this._exptype = value;
		}
		
	}
}