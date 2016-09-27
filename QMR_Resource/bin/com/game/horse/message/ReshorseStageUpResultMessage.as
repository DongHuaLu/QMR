package com.game.horse.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 发送坐骑升阶结果
	 */
	public class ReshorseStageUpResultMessage extends Message {
	
		//升阶结果，0失败，1成功
		private var _type: int;
		
		//失败后更新当前祝福值
		private var _dayblessvalue: int;
		
		//失败后是否暴击经验，0正常，1小暴击，2大暴击
		private var _crit: int;
		
		//失败后加的exp
		private var _exp: int;
		
		//进阶使用道具模组ID
		private var _itemmodelid: int;
		
		//进阶使用道具数量
		private var _itemnum: int;
		
		//铜币数量
		private var _money: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//升阶结果，0失败，1成功
			writeByte(_type);
			//失败后更新当前祝福值
			writeInt(_dayblessvalue);
			//失败后是否暴击经验，0正常，1小暴击，2大暴击
			writeByte(_crit);
			//失败后加的exp
			writeInt(_exp);
			//进阶使用道具模组ID
			writeInt(_itemmodelid);
			//进阶使用道具数量
			writeInt(_itemnum);
			//铜币数量
			writeInt(_money);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//升阶结果，0失败，1成功
			_type = readByte();
			//失败后更新当前祝福值
			_dayblessvalue = readInt();
			//失败后是否暴击经验，0正常，1小暴击，2大暴击
			_crit = readByte();
			//失败后加的exp
			_exp = readInt();
			//进阶使用道具模组ID
			_itemmodelid = readInt();
			//进阶使用道具数量
			_itemnum = readInt();
			//铜币数量
			_money = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 126105;
		}
		
		/**
		 * get 升阶结果，0失败，1成功
		 * @return 
		 */
		public function get type(): int{
			return _type;
		}
		
		/**
		 * set 升阶结果，0失败，1成功
		 */
		public function set type(value: int): void{
			this._type = value;
		}
		
		/**
		 * get 失败后更新当前祝福值
		 * @return 
		 */
		public function get dayblessvalue(): int{
			return _dayblessvalue;
		}
		
		/**
		 * set 失败后更新当前祝福值
		 */
		public function set dayblessvalue(value: int): void{
			this._dayblessvalue = value;
		}
		
		/**
		 * get 失败后是否暴击经验，0正常，1小暴击，2大暴击
		 * @return 
		 */
		public function get crit(): int{
			return _crit;
		}
		
		/**
		 * set 失败后是否暴击经验，0正常，1小暴击，2大暴击
		 */
		public function set crit(value: int): void{
			this._crit = value;
		}
		
		/**
		 * get 失败后加的exp
		 * @return 
		 */
		public function get exp(): int{
			return _exp;
		}
		
		/**
		 * set 失败后加的exp
		 */
		public function set exp(value: int): void{
			this._exp = value;
		}
		
		/**
		 * get 进阶使用道具模组ID
		 * @return 
		 */
		public function get itemmodelid(): int{
			return _itemmodelid;
		}
		
		/**
		 * set 进阶使用道具模组ID
		 */
		public function set itemmodelid(value: int): void{
			this._itemmodelid = value;
		}
		
		/**
		 * get 进阶使用道具数量
		 * @return 
		 */
		public function get itemnum(): int{
			return _itemnum;
		}
		
		/**
		 * set 进阶使用道具数量
		 */
		public function set itemnum(value: int): void{
			this._itemnum = value;
		}
		
		/**
		 * get 铜币数量
		 * @return 
		 */
		public function get money(): int{
			return _money;
		}
		
		/**
		 * set 铜币数量
		 */
		public function set money(value: int): void{
			this._money = value;
		}
		
	}
}