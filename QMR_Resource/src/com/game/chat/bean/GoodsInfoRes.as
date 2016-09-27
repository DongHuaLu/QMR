package com.game.chat.bean{
	import com.game.gem.bean.GemInfo;
	import com.game.backpack.bean.ItemInfo;
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 装备信息类
	 */
	public class GoodsInfoRes extends Bean {
	
		//请求类型 0物品 1其它
		private var _queryType: int;
		
		//索引位置
		private var _index: int;
		
		//物品内容
		private var _ItemInfo: com.game.backpack.bean.ItemInfo;
		
		//装备部位宝石信息
		private var _geminfo: Vector.<com.game.gem.bean.GemInfo> = new Vector.<com.game.gem.bean.GemInfo>();
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//请求类型 0物品 1其它
			writeInt(_queryType);
			//索引位置
			writeInt(_index);
			//物品内容
			writeBean(_ItemInfo);
			//装备部位宝石信息
			writeShort(_geminfo.length);
			for (var i: int = 0; i < _geminfo.length; i++) {
				writeBean(_geminfo[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//请求类型 0物品 1其它
			_queryType = readInt();
			//索引位置
			_index = readInt();
			//物品内容
			_ItemInfo = readBean(com.game.backpack.bean.ItemInfo) as com.game.backpack.bean.ItemInfo;
			//装备部位宝石信息
			var geminfo_length : int = readShort();
			for (var i: int = 0; i < geminfo_length; i++) {
				_geminfo[i] = readBean(com.game.gem.bean.GemInfo) as com.game.gem.bean.GemInfo;
			}
			return true;
		}
		
		/**
		 * get 请求类型 0物品 1其它
		 * @return 
		 */
		public function get queryType(): int{
			return _queryType;
		}
		
		/**
		 * set 请求类型 0物品 1其它
		 */
		public function set queryType(value: int): void{
			this._queryType = value;
		}
		
		/**
		 * get 索引位置
		 * @return 
		 */
		public function get index(): int{
			return _index;
		}
		
		/**
		 * set 索引位置
		 */
		public function set index(value: int): void{
			this._index = value;
		}
		
		/**
		 * get 物品内容
		 * @return 
		 */
		public function get ItemInfo(): com.game.backpack.bean.ItemInfo{
			return _ItemInfo;
		}
		
		/**
		 * set 物品内容
		 */
		public function set ItemInfo(value: com.game.backpack.bean.ItemInfo): void{
			this._ItemInfo = value;
		}
		
		/**
		 * get 装备部位宝石信息
		 * @return 
		 */
		public function get geminfo(): Vector.<com.game.gem.bean.GemInfo>{
			return _geminfo;
		}
		
		/**
		 * set 装备部位宝石信息
		 */
		public function set geminfo(value: Vector.<com.game.gem.bean.GemInfo>): void{
			this._geminfo = value;
		}
		
	}
}