package com.game.shop.bean{
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 商品动态信息
	 */
	public class ShopItemInfo extends Bean {
	
		//商品ID
		private var _sellId: int;
		
		//物品模型ID
		private var _modelId: int;
		
		//排序号
		private var _index: int;
		
		//货币类型 1游戏币，2元宝，4绑定元宝，6元宝与绑定元宝可购买，7三种货币均可购买
		private var _moneyType: int;
		
		//金币价格
		private var _coin: int;
		
		//元宝价格
		private var _gold: int;
		
		//绑定元宝价格
		private var _bindgold: int;
		
		//原金币价格
		private var _originalCoin: int;
		
		//原元宝价格
		private var _originalGold: int;
		
		//原绑定元宝价格
		private var _originalBindGold: int;
		
		//热销标识 0无热销，1热销中，2折扣，3热销+折扣
		private var _hot: int;
		
		//物品强化等级定义
		private var _strengthen: int;
		
		//物品附加属性定义（类型|百分比的分子;类型|百分比的分子
		private var _append: String;
		
		//过期时间 点（格式 yyyy-mm-dd hh:mm:ss）
		private var _lostTime: String;
		
		//购买后离自动失效前的存在时间（单位：秒）
		private var _duration: int;
		
		//购买时是否立刻绑定（0不是，1是立刻绑定）
		private var _buybind: int;
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//商品ID
			writeInt(_sellId);
			//物品模型ID
			writeInt(_modelId);
			//排序号
			writeInt(_index);
			//货币类型 1游戏币，2元宝，4绑定元宝，6元宝与绑定元宝可购买，7三种货币均可购买
			writeByte(_moneyType);
			//金币价格
			writeInt(_coin);
			//元宝价格
			writeInt(_gold);
			//绑定元宝价格
			writeInt(_bindgold);
			//原金币价格
			writeInt(_originalCoin);
			//原元宝价格
			writeInt(_originalGold);
			//原绑定元宝价格
			writeInt(_originalBindGold);
			//热销标识 0无热销，1热销中，2折扣，3热销+折扣
			writeByte(_hot);
			//物品强化等级定义
			writeInt(_strengthen);
			//物品附加属性定义（类型|百分比的分子;类型|百分比的分子
			writeString(_append);
			//过期时间 点（格式 yyyy-mm-dd hh:mm:ss）
			writeString(_lostTime);
			//购买后离自动失效前的存在时间（单位：秒）
			writeInt(_duration);
			//购买时是否立刻绑定（0不是，1是立刻绑定）
			writeByte(_buybind);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//商品ID
			_sellId = readInt();
			//物品模型ID
			_modelId = readInt();
			//排序号
			_index = readInt();
			//货币类型 1游戏币，2元宝，4绑定元宝，6元宝与绑定元宝可购买，7三种货币均可购买
			_moneyType = readByte();
			//金币价格
			_coin = readInt();
			//元宝价格
			_gold = readInt();
			//绑定元宝价格
			_bindgold = readInt();
			//原金币价格
			_originalCoin = readInt();
			//原元宝价格
			_originalGold = readInt();
			//原绑定元宝价格
			_originalBindGold = readInt();
			//热销标识 0无热销，1热销中，2折扣，3热销+折扣
			_hot = readByte();
			//物品强化等级定义
			_strengthen = readInt();
			//物品附加属性定义（类型|百分比的分子;类型|百分比的分子
			_append = readString();
			//过期时间 点（格式 yyyy-mm-dd hh:mm:ss）
			_lostTime = readString();
			//购买后离自动失效前的存在时间（单位：秒）
			_duration = readInt();
			//购买时是否立刻绑定（0不是，1是立刻绑定）
			_buybind = readByte();
			return true;
		}
		
		/**
		 * get 商品ID
		 * @return 
		 */
		public function get sellId(): int{
			return _sellId;
		}
		
		/**
		 * set 商品ID
		 */
		public function set sellId(value: int): void{
			this._sellId = value;
		}
		
		/**
		 * get 物品模型ID
		 * @return 
		 */
		public function get modelId(): int{
			return _modelId;
		}
		
		/**
		 * set 物品模型ID
		 */
		public function set modelId(value: int): void{
			this._modelId = value;
		}
		
		/**
		 * get 排序号
		 * @return 
		 */
		public function get index(): int{
			return _index;
		}
		
		/**
		 * set 排序号
		 */
		public function set index(value: int): void{
			this._index = value;
		}
		
		/**
		 * get 货币类型 1游戏币，2元宝，4绑定元宝，6元宝与绑定元宝可购买，7三种货币均可购买
		 * @return 
		 */
		public function get moneyType(): int{
			return _moneyType;
		}
		
		/**
		 * set 货币类型 1游戏币，2元宝，4绑定元宝，6元宝与绑定元宝可购买，7三种货币均可购买
		 */
		public function set moneyType(value: int): void{
			this._moneyType = value;
		}
		
		/**
		 * get 金币价格
		 * @return 
		 */
		public function get coin(): int{
			return _coin;
		}
		
		/**
		 * set 金币价格
		 */
		public function set coin(value: int): void{
			this._coin = value;
		}
		
		/**
		 * get 元宝价格
		 * @return 
		 */
		public function get gold(): int{
			return _gold;
		}
		
		/**
		 * set 元宝价格
		 */
		public function set gold(value: int): void{
			this._gold = value;
		}
		
		/**
		 * get 绑定元宝价格
		 * @return 
		 */
		public function get bindgold(): int{
			return _bindgold;
		}
		
		/**
		 * set 绑定元宝价格
		 */
		public function set bindgold(value: int): void{
			this._bindgold = value;
		}
		
		/**
		 * get 原金币价格
		 * @return 
		 */
		public function get originalCoin(): int{
			return _originalCoin;
		}
		
		/**
		 * set 原金币价格
		 */
		public function set originalCoin(value: int): void{
			this._originalCoin = value;
		}
		
		/**
		 * get 原元宝价格
		 * @return 
		 */
		public function get originalGold(): int{
			return _originalGold;
		}
		
		/**
		 * set 原元宝价格
		 */
		public function set originalGold(value: int): void{
			this._originalGold = value;
		}
		
		/**
		 * get 原绑定元宝价格
		 * @return 
		 */
		public function get originalBindGold(): int{
			return _originalBindGold;
		}
		
		/**
		 * set 原绑定元宝价格
		 */
		public function set originalBindGold(value: int): void{
			this._originalBindGold = value;
		}
		
		/**
		 * get 热销标识 0无热销，1热销中，2折扣，3热销+折扣
		 * @return 
		 */
		public function get hot(): int{
			return _hot;
		}
		
		/**
		 * set 热销标识 0无热销，1热销中，2折扣，3热销+折扣
		 */
		public function set hot(value: int): void{
			this._hot = value;
		}
		
		/**
		 * get 物品强化等级定义
		 * @return 
		 */
		public function get strengthen(): int{
			return _strengthen;
		}
		
		/**
		 * set 物品强化等级定义
		 */
		public function set strengthen(value: int): void{
			this._strengthen = value;
		}
		
		/**
		 * get 物品附加属性定义（类型|百分比的分子;类型|百分比的分子
		 * @return 
		 */
		public function get append(): String{
			return _append;
		}
		
		/**
		 * set 物品附加属性定义（类型|百分比的分子;类型|百分比的分子
		 */
		public function set append(value: String): void{
			this._append = value;
		}
		
		/**
		 * get 过期时间 点（格式 yyyy-mm-dd hh:mm:ss）
		 * @return 
		 */
		public function get lostTime(): String{
			return _lostTime;
		}
		
		/**
		 * set 过期时间 点（格式 yyyy-mm-dd hh:mm:ss）
		 */
		public function set lostTime(value: String): void{
			this._lostTime = value;
		}
		
		/**
		 * get 购买后离自动失效前的存在时间（单位：秒）
		 * @return 
		 */
		public function get duration(): int{
			return _duration;
		}
		
		/**
		 * set 购买后离自动失效前的存在时间（单位：秒）
		 */
		public function set duration(value: int): void{
			this._duration = value;
		}
		
		/**
		 * get 购买时是否立刻绑定（0不是，1是立刻绑定）
		 * @return 
		 */
		public function get buybind(): int{
			return _buybind;
		}
		
		/**
		 * set 购买时是否立刻绑定（0不是，1是立刻绑定）
		 */
		public function set buybind(value: int): void{
			this._buybind = value;
		}
		
	}
}