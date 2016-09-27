package com.game.hiddenweapon.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 发送暗器升阶结果
	 */
	public class ResHiddenWeaponStageUpResultMessage extends Message {
	
		//升阶结果，0未升级，1升级
		private var _type: int;
		
		//当前祝福值
		private var _bless: int;
		
		//获得祝福值
		private var _gotbless: int;
		
		//获得经验
		private var _gotexp: int;
		
		//是否暴击经验，0正常，1小暴击，2大暴击
		private var _crit: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//升阶结果，0未升级，1升级
			writeByte(_type);
			//当前祝福值
			writeInt(_bless);
			//获得祝福值
			writeInt(_gotbless);
			//获得经验
			writeInt(_gotexp);
			//是否暴击经验，0正常，1小暴击，2大暴击
			writeByte(_crit);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//升阶结果，0未升级，1升级
			_type = readByte();
			//当前祝福值
			_bless = readInt();
			//获得祝福值
			_gotbless = readInt();
			//获得经验
			_gotexp = readInt();
			//是否暴击经验，0正常，1小暴击，2大暴击
			_crit = readByte();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 162104;
		}
		
		/**
		 * get 升阶结果，0未升级，1升级
		 * @return 
		 */
		public function get type(): int{
			return _type;
		}
		
		/**
		 * set 升阶结果，0未升级，1升级
		 */
		public function set type(value: int): void{
			this._type = value;
		}
		
		/**
		 * get 当前祝福值
		 * @return 
		 */
		public function get bless(): int{
			return _bless;
		}
		
		/**
		 * set 当前祝福值
		 */
		public function set bless(value: int): void{
			this._bless = value;
		}
		
		/**
		 * get 获得祝福值
		 * @return 
		 */
		public function get gotbless(): int{
			return _gotbless;
		}
		
		/**
		 * set 获得祝福值
		 */
		public function set gotbless(value: int): void{
			this._gotbless = value;
		}
		
		/**
		 * get 获得经验
		 * @return 
		 */
		public function get gotexp(): int{
			return _gotexp;
		}
		
		/**
		 * set 获得经验
		 */
		public function set gotexp(value: int): void{
			this._gotexp = value;
		}
		
		/**
		 * get 是否暴击经验，0正常，1小暴击，2大暴击
		 * @return 
		 */
		public function get crit(): int{
			return _crit;
		}
		
		/**
		 * set 是否暴击经验，0正常，1小暴击，2大暴击
		 */
		public function set crit(value: int): void{
			this._crit = value;
		}
		
	}
}