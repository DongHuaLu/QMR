package com.game.melting.message{
	import com.game.backpack.bean.GoodsAttribute;
	import com.game.backpack.bean.ItemInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 发送熔炼结果
	 */
	public class ResMeltingItemToClientMessage extends Message {
	
		//装备详细信息
		private var _equipInfo: com.game.backpack.bean.ItemInfo;
		
		//替换的位置
		private var _idx: int;
		
		//替换的属性
		private var _repattr: com.game.backpack.bean.GoodsAttribute;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//装备详细信息
			writeBean(_equipInfo);
			//替换的位置
			writeByte(_idx);
			//替换的属性
			writeBean(_repattr);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//装备详细信息
			_equipInfo = readBean(com.game.backpack.bean.ItemInfo) as com.game.backpack.bean.ItemInfo;
			//替换的位置
			_idx = readByte();
			//替换的属性
			_repattr = readBean(com.game.backpack.bean.GoodsAttribute) as com.game.backpack.bean.GoodsAttribute;
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 154101;
		}
		
		/**
		 * get 装备详细信息
		 * @return 
		 */
		public function get equipInfo(): com.game.backpack.bean.ItemInfo{
			return _equipInfo;
		}
		
		/**
		 * set 装备详细信息
		 */
		public function set equipInfo(value: com.game.backpack.bean.ItemInfo): void{
			this._equipInfo = value;
		}
		
		/**
		 * get 替换的位置
		 * @return 
		 */
		public function get idx(): int{
			return _idx;
		}
		
		/**
		 * set 替换的位置
		 */
		public function set idx(value: int): void{
			this._idx = value;
		}
		
		/**
		 * get 替换的属性
		 * @return 
		 */
		public function get repattr(): com.game.backpack.bean.GoodsAttribute{
			return _repattr;
		}
		
		/**
		 * set 替换的属性
		 */
		public function set repattr(value: com.game.backpack.bean.GoodsAttribute): void{
			this._repattr = value;
		}
		
	}
}