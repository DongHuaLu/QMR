package com.game.shop.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 购买物品
	 */
	public class BuyItemMessage extends Message {
	
		//NPC Id
		private var _npcId: int;
		
		//销售Id
		private var _sellId: int;
		
		//物品数量
		private var _num: int;
		
		//花费类型
		private var _costType: int;
		
		//物品模型ID
		private var _modelId: int;
		
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
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//NPC Id
			writeInt(_npcId);
			//销售Id
			writeInt(_sellId);
			//物品数量
			writeInt(_num);
			//花费类型
			writeInt(_costType);
			//物品模型ID
			writeInt(_modelId);
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
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//NPC Id
			_npcId = readInt();
			//销售Id
			_sellId = readInt();
			//物品数量
			_num = readInt();
			//花费类型
			_costType = readInt();
			//物品模型ID
			_modelId = readInt();
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
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 105201;
		}
		
		/**
		 * get NPC Id
		 * @return 
		 */
		public function get npcId(): int{
			return _npcId;
		}
		
		/**
		 * set NPC Id
		 */
		public function set npcId(value: int): void{
			this._npcId = value;
		}
		
		/**
		 * get 销售Id
		 * @return 
		 */
		public function get sellId(): int{
			return _sellId;
		}
		
		/**
		 * set 销售Id
		 */
		public function set sellId(value: int): void{
			this._sellId = value;
		}
		
		/**
		 * get 物品数量
		 * @return 
		 */
		public function get num(): int{
			return _num;
		}
		
		/**
		 * set 物品数量
		 */
		public function set num(value: int): void{
			this._num = value;
		}
		
		/**
		 * get 花费类型
		 * @return 
		 */
		public function get costType(): int{
			return _costType;
		}
		
		/**
		 * set 花费类型
		 */
		public function set costType(value: int): void{
			this._costType = value;
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
		
	}
}