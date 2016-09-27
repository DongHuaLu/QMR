package com.game.player.message{
	import com.game.player.bean.PlayerAttributeItem;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 个人属性值变化
	 */
	public class ResPlayerAttributeChangeMessage extends Message {
	
		//变化的属性原因
		private var _attributeChangeReason: int;
		
		//模型Id
		private var _modelId: int;
		
		//变化的属性列表
		private var _attributeChangeList: Vector.<PlayerAttributeItem> = new Vector.<PlayerAttributeItem>();
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//变化的属性原因
			writeInt(_attributeChangeReason);
			//模型Id
			writeInt(_modelId);
			//变化的属性列表
			writeShort(_attributeChangeList.length);
			for (i = 0; i < _attributeChangeList.length; i++) {
				writeBean(_attributeChangeList[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			var i: int = 0;
			//变化的属性原因
			_attributeChangeReason = readInt();
			//模型Id
			_modelId = readInt();
			//变化的属性列表
			var attributeChangeList_length : int = readShort();
			for (i = 0; i < attributeChangeList_length; i++) {
				_attributeChangeList[i] = readBean(PlayerAttributeItem) as PlayerAttributeItem;
			}
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 103119;
		}
		
		/**
		 * get 变化的属性原因
		 * @return 
		 */
		public function get attributeChangeReason(): int{
			return _attributeChangeReason;
		}
		
		/**
		 * set 变化的属性原因
		 */
		public function set attributeChangeReason(value: int): void{
			this._attributeChangeReason = value;
		}
		
		/**
		 * get 模型Id
		 * @return 
		 */
		public function get modelId(): int{
			return _modelId;
		}
		
		/**
		 * set 模型Id
		 */
		public function set modelId(value: int): void{
			this._modelId = value;
		}
		
		/**
		 * get 变化的属性列表
		 * @return 
		 */
		public function get attributeChangeList(): Vector.<PlayerAttributeItem>{
			return _attributeChangeList;
		}
		
		/**
		 * set 变化的属性列表
		 */
		public function set attributeChangeList(value: Vector.<PlayerAttributeItem>): void{
			this._attributeChangeList = value;
		}
		
	}
}