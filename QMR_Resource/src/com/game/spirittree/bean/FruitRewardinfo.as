package com.game.spirittree.bean{
	import com.game.spirittree.bean.TempAttributes;
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 单个果实奖励
	 */
	public class FruitRewardinfo extends Bean {
	
		//奖励数据索引
		private var _index: int;
		
		//道具模型ID，-1铜币，-2元宝，-3经验，-4真气
		private var _itemModelid: int;
		
		//当前数量
		private var _num: int;
		
		//总数量
		private var _sum: int;
		
		//道具强化等级
		private var _strenglevel: int;
		
		//奖励道具附加属性
		private var _tempAttributes: Vector.<TempAttributes> = new Vector.<TempAttributes>();
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//奖励数据索引
			writeInt(_index);
			//道具模型ID，-1铜币，-2元宝，-3经验，-4真气
			writeInt(_itemModelid);
			//当前数量
			writeInt(_num);
			//总数量
			writeInt(_sum);
			//道具强化等级
			writeInt(_strenglevel);
			//奖励道具附加属性
			writeShort(_tempAttributes.length);
			for (var i: int = 0; i < _tempAttributes.length; i++) {
				writeBean(_tempAttributes[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//奖励数据索引
			_index = readInt();
			//道具模型ID，-1铜币，-2元宝，-3经验，-4真气
			_itemModelid = readInt();
			//当前数量
			_num = readInt();
			//总数量
			_sum = readInt();
			//道具强化等级
			_strenglevel = readInt();
			//奖励道具附加属性
			var tempAttributes_length : int = readShort();
			for (var i: int = 0; i < tempAttributes_length; i++) {
				_tempAttributes[i] = readBean(TempAttributes) as TempAttributes;
			}
			return true;
		}
		
		/**
		 * get 奖励数据索引
		 * @return 
		 */
		public function get index(): int{
			return _index;
		}
		
		/**
		 * set 奖励数据索引
		 */
		public function set index(value: int): void{
			this._index = value;
		}
		
		/**
		 * get 道具模型ID，-1铜币，-2元宝，-3经验，-4真气
		 * @return 
		 */
		public function get itemModelid(): int{
			return _itemModelid;
		}
		
		/**
		 * set 道具模型ID，-1铜币，-2元宝，-3经验，-4真气
		 */
		public function set itemModelid(value: int): void{
			this._itemModelid = value;
		}
		
		/**
		 * get 当前数量
		 * @return 
		 */
		public function get num(): int{
			return _num;
		}
		
		/**
		 * set 当前数量
		 */
		public function set num(value: int): void{
			this._num = value;
		}
		
		/**
		 * get 总数量
		 * @return 
		 */
		public function get sum(): int{
			return _sum;
		}
		
		/**
		 * set 总数量
		 */
		public function set sum(value: int): void{
			this._sum = value;
		}
		
		/**
		 * get 道具强化等级
		 * @return 
		 */
		public function get strenglevel(): int{
			return _strenglevel;
		}
		
		/**
		 * set 道具强化等级
		 */
		public function set strenglevel(value: int): void{
			this._strenglevel = value;
		}
		
		/**
		 * get 奖励道具附加属性
		 * @return 
		 */
		public function get tempAttributes(): Vector.<TempAttributes>{
			return _tempAttributes;
		}
		
		/**
		 * set 奖励道具附加属性
		 */
		public function set tempAttributes(value: Vector.<TempAttributes>): void{
			this._tempAttributes = value;
		}
		
	}
}