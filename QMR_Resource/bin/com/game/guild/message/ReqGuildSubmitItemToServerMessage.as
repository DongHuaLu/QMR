package com.game.guild.message{
	import com.game.utils.long;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 提交帮会贡献物品
	 */
	public class ReqGuildSubmitItemToServerMessage extends Message {
	
		//帮会Id
		private var _guildId: long;
		
		//物品类型
		private var _itemType: int;
		
		//物品数目
		private var _itemNum: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//帮会Id
			writeLong(_guildId);
			//物品类型
			writeByte(_itemType);
			//物品数目
			writeInt(_itemNum);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//帮会Id
			_guildId = readLong();
			//物品类型
			_itemType = readByte();
			//物品数目
			_itemNum = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 121214;
		}
		
		/**
		 * get 帮会Id
		 * @return 
		 */
		public function get guildId(): long{
			return _guildId;
		}
		
		/**
		 * set 帮会Id
		 */
		public function set guildId(value: long): void{
			this._guildId = value;
		}
		
		/**
		 * get 物品类型
		 * @return 
		 */
		public function get itemType(): int{
			return _itemType;
		}
		
		/**
		 * set 物品类型
		 */
		public function set itemType(value: int): void{
			this._itemType = value;
		}
		
		/**
		 * get 物品数目
		 * @return 
		 */
		public function get itemNum(): int{
			return _itemNum;
		}
		
		/**
		 * set 物品数目
		 */
		public function set itemNum(value: int): void{
			this._itemNum = value;
		}
		
	}
}