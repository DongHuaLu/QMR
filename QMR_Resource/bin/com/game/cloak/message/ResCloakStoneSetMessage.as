package com.game.cloak.message{
	import com.game.utils.long;
	import com.game.cloak.bean.CloakStoneInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 披风镶嵌返回
	 */
	public class ResCloakStoneSetMessage extends Message {
	
		//新镶嵌的石头所在的格子编号,从1开始
		private var _index: int;
		
		//技能
		private var _stones: Vector.<CloakStoneInfo> = new Vector.<CloakStoneInfo>();
		//如果是替换的则为物品id,否则为0
		private var _itemid: long;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//新镶嵌的石头所在的格子编号,从1开始
			writeByte(_index);
			//技能
			writeShort(_stones.length);
			for (i = 0; i < _stones.length; i++) {
				writeBean(_stones[i]);
			}
			//如果是替换的则为物品id,否则为0
			writeLong(_itemid);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			var i: int = 0;
			//新镶嵌的石头所在的格子编号,从1开始
			_index = readByte();
			//技能
			var stones_length : int = readShort();
			for (i = 0; i < stones_length; i++) {
				_stones[i] = readBean(CloakStoneInfo) as CloakStoneInfo;
			}
			//如果是替换的则为物品id,否则为0
			_itemid = readLong();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 170103;
		}
		
		/**
		 * get 新镶嵌的石头所在的格子编号,从1开始
		 * @return 
		 */
		public function get index(): int{
			return _index;
		}
		
		/**
		 * set 新镶嵌的石头所在的格子编号,从1开始
		 */
		public function set index(value: int): void{
			this._index = value;
		}
		
		/**
		 * get 技能
		 * @return 
		 */
		public function get stones(): Vector.<CloakStoneInfo>{
			return _stones;
		}
		
		/**
		 * set 技能
		 */
		public function set stones(value: Vector.<CloakStoneInfo>): void{
			this._stones = value;
		}
		
		/**
		 * get 如果是替换的则为物品id,否则为0
		 * @return 
		 */
		public function get itemid(): long{
			return _itemid;
		}
		
		/**
		 * set 如果是替换的则为物品id,否则为0
		 */
		public function set itemid(value: long): void{
			this._itemid = value;
		}
		
	}
}