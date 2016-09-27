package com.game.backpack.message{
	import com.game.utils.long;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 拾取成功
	 */
	public class ResTakeUpSuccessMessage extends Message {
	
		//物品ID
		private var _goodsId: long;
		
		//物品模型ID
		private var _goodModelId: int;
		
		//产生效果类型
		private var _effectType: int;
		
		//产生效果值
		private var _effectValue: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//物品ID
			writeLong(_goodsId);
			//物品模型ID
			writeInt(_goodModelId);
			//产生效果类型
			writeInt(_effectType);
			//产生效果值
			writeInt(_effectValue);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//物品ID
			_goodsId = readLong();
			//物品模型ID
			_goodModelId = readInt();
			//产生效果类型
			_effectType = readInt();
			//产生效果值
			_effectValue = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 104212;
		}
		
		/**
		 * get 物品ID
		 * @return 
		 */
		public function get goodsId(): long{
			return _goodsId;
		}
		
		/**
		 * set 物品ID
		 */
		public function set goodsId(value: long): void{
			this._goodsId = value;
		}
		
		/**
		 * get 物品模型ID
		 * @return 
		 */
		public function get goodModelId(): int{
			return _goodModelId;
		}
		
		/**
		 * set 物品模型ID
		 */
		public function set goodModelId(value: int): void{
			this._goodModelId = value;
		}
		
		/**
		 * get 产生效果类型
		 * @return 
		 */
		public function get effectType(): int{
			return _effectType;
		}
		
		/**
		 * set 产生效果类型
		 */
		public function set effectType(value: int): void{
			this._effectType = value;
		}
		
		/**
		 * get 产生效果值
		 * @return 
		 */
		public function get effectValue(): int{
			return _effectValue;
		}
		
		/**
		 * set 产生效果值
		 */
		public function set effectValue(value: int): void{
			this._effectValue = value;
		}
		
	}
}